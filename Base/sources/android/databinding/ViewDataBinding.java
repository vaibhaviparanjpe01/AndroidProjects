package android.databinding;

import android.annotation.TargetApi;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.res.ColorStateList;
import android.databinding.CallbackRegistry;
import android.databinding.Observable;
import android.databinding.ObservableList;
import android.databinding.ObservableMap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import android.view.Choreographer;
import android.view.View;
import android.view.ViewGroup;
import com.android.databinding.library.R;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

public abstract class ViewDataBinding extends BaseObservable {
    private static final int BINDING_NUMBER_START = BINDING_TAG_PREFIX.length();
    public static final String BINDING_TAG_PREFIX = "binding_";
    private static final CreateWeakListener CREATE_LIST_LISTENER = new CreateWeakListener() {
        public WeakListener create(ViewDataBinding viewDataBinding, int i) {
            return new WeakListListener(viewDataBinding, i).getListener();
        }
    };
    private static final CreateWeakListener CREATE_LIVE_DATA_LISTENER = new CreateWeakListener() {
        public WeakListener create(ViewDataBinding viewDataBinding, int i) {
            return new LiveDataListener(viewDataBinding, i).getListener();
        }
    };
    private static final CreateWeakListener CREATE_MAP_LISTENER = new CreateWeakListener() {
        public WeakListener create(ViewDataBinding viewDataBinding, int i) {
            return new WeakMapListener(viewDataBinding, i).getListener();
        }
    };
    private static final CreateWeakListener CREATE_PROPERTY_LISTENER = new CreateWeakListener() {
        public WeakListener create(ViewDataBinding viewDataBinding, int i) {
            return new WeakPropertyListener(viewDataBinding, i).getListener();
        }
    };
    private static final int HALTED = 2;
    private static final int REBIND = 1;
    private static final CallbackRegistry.NotifierCallback<OnRebindCallback, ViewDataBinding, Void> REBIND_NOTIFIER = new CallbackRegistry.NotifierCallback<OnRebindCallback, ViewDataBinding, Void>() {
        public void onNotifyCallback(OnRebindCallback onRebindCallback, ViewDataBinding viewDataBinding, int i, Void voidR) {
            switch (i) {
                case 1:
                    if (!onRebindCallback.onPreBind(viewDataBinding)) {
                        boolean unused = viewDataBinding.mRebindHalted = true;
                        return;
                    }
                    return;
                case 2:
                    onRebindCallback.onCanceled(viewDataBinding);
                    return;
                case 3:
                    onRebindCallback.onBound(viewDataBinding);
                    return;
                default:
                    return;
            }
        }
    };
    private static final int REBOUND = 3;
    /* access modifiers changed from: private */
    public static final View.OnAttachStateChangeListener ROOT_REATTACHED_LISTENER;
    static int SDK_INT = Build.VERSION.SDK_INT;
    private static final boolean USE_CHOREOGRAPHER = (SDK_INT >= 16);
    /* access modifiers changed from: private */
    public static final ReferenceQueue<ViewDataBinding> sReferenceQueue = new ReferenceQueue<>();
    protected final DataBindingComponent mBindingComponent;
    private Choreographer mChoreographer;
    private ViewDataBinding mContainingBinding;
    private final Choreographer.FrameCallback mFrameCallback;
    private boolean mInLiveDataRegisterObserver;
    private boolean mIsExecutingPendingBindings;
    private LifecycleOwner mLifecycleOwner;
    private WeakListener[] mLocalFieldObservers;
    private OnStartListener mOnStartListener;
    /* access modifiers changed from: private */
    public boolean mPendingRebind = false;
    private CallbackRegistry<OnRebindCallback, ViewDataBinding, Void> mRebindCallbacks;
    /* access modifiers changed from: private */
    public boolean mRebindHalted = false;
    /* access modifiers changed from: private */
    public final Runnable mRebindRunnable = new Runnable() {
        public void run() {
            synchronized (this) {
                boolean unused = ViewDataBinding.this.mPendingRebind = false;
            }
            ViewDataBinding.processReferenceQueue();
            if (Build.VERSION.SDK_INT < 19 || ViewDataBinding.this.mRoot.isAttachedToWindow()) {
                ViewDataBinding.this.executePendingBindings();
                return;
            }
            ViewDataBinding.this.mRoot.removeOnAttachStateChangeListener(ViewDataBinding.ROOT_REATTACHED_LISTENER);
            ViewDataBinding.this.mRoot.addOnAttachStateChangeListener(ViewDataBinding.ROOT_REATTACHED_LISTENER);
        }
    };
    /* access modifiers changed from: private */
    public final View mRoot;
    private Handler mUIThreadHandler;

    private interface CreateWeakListener {
        WeakListener create(ViewDataBinding viewDataBinding, int i);
    }

    private interface ObservableReference<T> {
        void addListener(T t);

        WeakListener<T> getListener();

        void removeListener(T t);

        void setLifecycleOwner(LifecycleOwner lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    public abstract void executeBindings();

    public abstract boolean hasPendingBindings();

    public abstract void invalidateAll();

    /* access modifiers changed from: protected */
    public abstract boolean onFieldChange(int i, Object obj, int i2);

    public abstract boolean setVariable(int i, @Nullable Object obj);

    static {
        if (Build.VERSION.SDK_INT < 19) {
            ROOT_REATTACHED_LISTENER = null;
        } else {
            ROOT_REATTACHED_LISTENER = new View.OnAttachStateChangeListener() {
                public void onViewDetachedFromWindow(View view) {
                }

                @TargetApi(19)
                public void onViewAttachedToWindow(View view) {
                    ViewDataBinding.getBinding(view).mRebindRunnable.run();
                    view.removeOnAttachStateChangeListener(this);
                }
            };
        }
    }

    protected ViewDataBinding(DataBindingComponent dataBindingComponent, View view, int i) {
        this.mBindingComponent = dataBindingComponent;
        this.mLocalFieldObservers = new WeakListener[i];
        this.mRoot = view;
        if (Looper.myLooper() == null) {
            throw new IllegalStateException("DataBinding must be created in view's UI Thread");
        } else if (USE_CHOREOGRAPHER) {
            this.mChoreographer = Choreographer.getInstance();
            this.mFrameCallback = new Choreographer.FrameCallback() {
                public void doFrame(long j) {
                    ViewDataBinding.this.mRebindRunnable.run();
                }
            };
        } else {
            this.mFrameCallback = null;
            this.mUIThreadHandler = new Handler(Looper.myLooper());
        }
    }

    /* access modifiers changed from: protected */
    public void setRootTag(View view) {
        view.setTag(R.id.dataBinding, this);
    }

    /* access modifiers changed from: protected */
    public void setRootTag(View[] viewArr) {
        for (View tag : viewArr) {
            tag.setTag(R.id.dataBinding, this);
        }
    }

    public static int getBuildSdkInt() {
        return SDK_INT;
    }

    @MainThread
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        if (this.mLifecycleOwner != lifecycleOwner) {
            if (this.mLifecycleOwner != null) {
                this.mLifecycleOwner.getLifecycle().removeObserver(this.mOnStartListener);
            }
            this.mLifecycleOwner = lifecycleOwner;
            if (lifecycleOwner != null) {
                if (this.mOnStartListener == null) {
                    this.mOnStartListener = new OnStartListener();
                }
                lifecycleOwner.getLifecycle().addObserver(this.mOnStartListener);
            }
            for (WeakListener weakListener : this.mLocalFieldObservers) {
                if (weakListener != null) {
                    weakListener.setLifecycleOwner(lifecycleOwner);
                }
            }
        }
    }

    public void addOnRebindCallback(@NonNull OnRebindCallback onRebindCallback) {
        if (this.mRebindCallbacks == null) {
            this.mRebindCallbacks = new CallbackRegistry<>(REBIND_NOTIFIER);
        }
        this.mRebindCallbacks.add(onRebindCallback);
    }

    public void removeOnRebindCallback(@NonNull OnRebindCallback onRebindCallback) {
        if (this.mRebindCallbacks != null) {
            this.mRebindCallbacks.remove(onRebindCallback);
        }
    }

    public void executePendingBindings() {
        if (this.mContainingBinding == null) {
            executeBindingsInternal();
        } else {
            this.mContainingBinding.executePendingBindings();
        }
    }

    private void executeBindingsInternal() {
        if (this.mIsExecutingPendingBindings) {
            requestRebind();
        } else if (hasPendingBindings()) {
            this.mIsExecutingPendingBindings = true;
            this.mRebindHalted = false;
            if (this.mRebindCallbacks != null) {
                this.mRebindCallbacks.notifyCallbacks(this, 1, null);
                if (this.mRebindHalted) {
                    this.mRebindCallbacks.notifyCallbacks(this, 2, null);
                }
            }
            if (!this.mRebindHalted) {
                executeBindings();
                if (this.mRebindCallbacks != null) {
                    this.mRebindCallbacks.notifyCallbacks(this, 3, null);
                }
            }
            this.mIsExecutingPendingBindings = false;
        }
    }

    protected static void executeBindingsOn(ViewDataBinding viewDataBinding) {
        viewDataBinding.executeBindingsInternal();
    }

    /* access modifiers changed from: package-private */
    public void forceExecuteBindings() {
        executeBindings();
    }

    public void unbind() {
        for (WeakListener weakListener : this.mLocalFieldObservers) {
            if (weakListener != null) {
                weakListener.unregister();
            }
        }
    }

    static ViewDataBinding getBinding(View view) {
        if (view != null) {
            return (ViewDataBinding) view.getTag(R.id.dataBinding);
        }
        return null;
    }

    @NonNull
    public View getRoot() {
        return this.mRoot;
    }

    /* access modifiers changed from: private */
    public void handleFieldChange(int i, Object obj, int i2) {
        if (!this.mInLiveDataRegisterObserver && onFieldChange(i, obj, i2)) {
            requestRebind();
        }
    }

    /* access modifiers changed from: protected */
    public boolean unregisterFrom(int i) {
        WeakListener weakListener = this.mLocalFieldObservers[i];
        if (weakListener != null) {
            return weakListener.unregister();
        }
        return false;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0017, code lost:
        if (r2.mLifecycleOwner == null) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0029, code lost:
        if (r2.mLifecycleOwner.getLifecycle().getCurrentState().isAtLeast(android.arch.lifecycle.Lifecycle.State.STARTED) != false) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002b, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002e, code lost:
        if (USE_CHOREOGRAPHER == false) goto L_0x0038;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0030, code lost:
        r2.mChoreographer.postFrameCallback(r2.mFrameCallback);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0038, code lost:
        r2.mUIThreadHandler.post(r2.mRebindRunnable);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void requestRebind() {
        /*
            r2 = this;
            android.databinding.ViewDataBinding r0 = r2.mContainingBinding
            if (r0 == 0) goto L_0x000a
            android.databinding.ViewDataBinding r0 = r2.mContainingBinding
            r0.requestRebind()
            goto L_0x003f
        L_0x000a:
            monitor-enter(r2)
            boolean r0 = r2.mPendingRebind     // Catch:{ all -> 0x0040 }
            if (r0 == 0) goto L_0x0011
            monitor-exit(r2)     // Catch:{ all -> 0x0040 }
            return
        L_0x0011:
            r0 = 1
            r2.mPendingRebind = r0     // Catch:{ all -> 0x0040 }
            monitor-exit(r2)     // Catch:{ all -> 0x0040 }
            android.arch.lifecycle.LifecycleOwner r0 = r2.mLifecycleOwner
            if (r0 == 0) goto L_0x002c
            android.arch.lifecycle.LifecycleOwner r0 = r2.mLifecycleOwner
            android.arch.lifecycle.Lifecycle r0 = r0.getLifecycle()
            android.arch.lifecycle.Lifecycle$State r0 = r0.getCurrentState()
            android.arch.lifecycle.Lifecycle$State r1 = android.arch.lifecycle.Lifecycle.State.STARTED
            boolean r0 = r0.isAtLeast(r1)
            if (r0 != 0) goto L_0x002c
            return
        L_0x002c:
            boolean r0 = USE_CHOREOGRAPHER
            if (r0 == 0) goto L_0x0038
            android.view.Choreographer r0 = r2.mChoreographer
            android.view.Choreographer$FrameCallback r1 = r2.mFrameCallback
            r0.postFrameCallback(r1)
            goto L_0x003f
        L_0x0038:
            android.os.Handler r0 = r2.mUIThreadHandler
            java.lang.Runnable r1 = r2.mRebindRunnable
            r0.post(r1)
        L_0x003f:
            return
        L_0x0040:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0040 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.databinding.ViewDataBinding.requestRebind():void");
    }

    /* access modifiers changed from: protected */
    public Object getObservedField(int i) {
        WeakListener weakListener = this.mLocalFieldObservers[i];
        if (weakListener == null) {
            return null;
        }
        return weakListener.getTarget();
    }

    private boolean updateRegistration(int i, Object obj, CreateWeakListener createWeakListener) {
        if (obj == null) {
            return unregisterFrom(i);
        }
        WeakListener weakListener = this.mLocalFieldObservers[i];
        if (weakListener == null) {
            registerTo(i, obj, createWeakListener);
            return true;
        } else if (weakListener.getTarget() == obj) {
            return false;
        } else {
            unregisterFrom(i);
            registerTo(i, obj, createWeakListener);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public boolean updateRegistration(int i, Observable observable) {
        return updateRegistration(i, observable, CREATE_PROPERTY_LISTENER);
    }

    /* access modifiers changed from: protected */
    public boolean updateRegistration(int i, ObservableList observableList) {
        return updateRegistration(i, observableList, CREATE_LIST_LISTENER);
    }

    /* access modifiers changed from: protected */
    public boolean updateRegistration(int i, ObservableMap observableMap) {
        return updateRegistration(i, observableMap, CREATE_MAP_LISTENER);
    }

    /* access modifiers changed from: protected */
    public boolean updateLiveDataRegistration(int i, LiveData<?> liveData) {
        this.mInLiveDataRegisterObserver = true;
        try {
            return updateRegistration(i, liveData, CREATE_LIVE_DATA_LISTENER);
        } finally {
            this.mInLiveDataRegisterObserver = false;
        }
    }

    /* access modifiers changed from: protected */
    public void ensureBindingComponentIsNotNull(Class<?> cls) {
        if (this.mBindingComponent == null) {
            throw new IllegalStateException("Required DataBindingComponent is null in class " + getClass().getSimpleName() + ". A BindingAdapter in " + cls.getCanonicalName() + " is not static and requires an object to use, retrieved from the DataBindingComponent. If you don't use an inflation method taking a DataBindingComponent, use DataBindingUtil.setDefaultComponent or make all BindingAdapter methods static.");
        }
    }

    /* access modifiers changed from: protected */
    public void registerTo(int i, Object obj, CreateWeakListener createWeakListener) {
        if (obj != null) {
            WeakListener weakListener = this.mLocalFieldObservers[i];
            if (weakListener == null) {
                weakListener = createWeakListener.create(this, i);
                this.mLocalFieldObservers[i] = weakListener;
                if (this.mLifecycleOwner != null) {
                    weakListener.setLifecycleOwner(this.mLifecycleOwner);
                }
            }
            weakListener.setTarget(obj);
        }
    }

    protected static ViewDataBinding bind(DataBindingComponent dataBindingComponent, View view, int i) {
        return DataBindingUtil.bind(dataBindingComponent, view, i);
    }

    protected static Object[] mapBindings(DataBindingComponent dataBindingComponent, View view, int i, IncludedLayouts includedLayouts, SparseIntArray sparseIntArray) {
        Object[] objArr = new Object[i];
        mapBindings(dataBindingComponent, view, objArr, includedLayouts, sparseIntArray, true);
        return objArr;
    }

    protected static boolean parse(String str, boolean z) {
        return str == null ? z : Boolean.parseBoolean(str);
    }

    protected static byte parse(String str, byte b) {
        try {
            return Byte.parseByte(str);
        } catch (NumberFormatException unused) {
            return b;
        }
    }

    protected static short parse(String str, short s) {
        try {
            return Short.parseShort(str);
        } catch (NumberFormatException unused) {
            return s;
        }
    }

    protected static int parse(String str, int i) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    protected static long parse(String str, long j) {
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return j;
        }
    }

    protected static float parse(String str, float f) {
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException unused) {
            return f;
        }
    }

    protected static double parse(String str, double d) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException unused) {
            return d;
        }
    }

    protected static char parse(String str, char c) {
        return (str == null || str.isEmpty()) ? c : str.charAt(0);
    }

    protected static int getColorFromResource(View view, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            return view.getContext().getColor(i);
        }
        return view.getResources().getColor(i);
    }

    protected static ColorStateList getColorStateListFromResource(View view, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            return view.getContext().getColorStateList(i);
        }
        return view.getResources().getColorStateList(i);
    }

    protected static Drawable getDrawableFromResource(View view, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getContext().getDrawable(i);
        }
        return view.getResources().getDrawable(i);
    }

    protected static <T> T getFromArray(T[] tArr, int i) {
        if (tArr == null || i < 0 || i >= tArr.length) {
            return null;
        }
        return tArr[i];
    }

    protected static <T> void setTo(T[] tArr, int i, T t) {
        if (tArr != null && i >= 0 && i < tArr.length) {
            tArr[i] = t;
        }
    }

    protected static boolean getFromArray(boolean[] zArr, int i) {
        if (zArr == null || i < 0 || i >= zArr.length) {
            return false;
        }
        return zArr[i];
    }

    protected static void setTo(boolean[] zArr, int i, boolean z) {
        if (zArr != null && i >= 0 && i < zArr.length) {
            zArr[i] = z;
        }
    }

    protected static byte getFromArray(byte[] bArr, int i) {
        if (bArr == null || i < 0 || i >= bArr.length) {
            return 0;
        }
        return bArr[i];
    }

    protected static void setTo(byte[] bArr, int i, byte b) {
        if (bArr != null && i >= 0 && i < bArr.length) {
            bArr[i] = b;
        }
    }

    protected static short getFromArray(short[] sArr, int i) {
        if (sArr == null || i < 0 || i >= sArr.length) {
            return 0;
        }
        return sArr[i];
    }

    protected static void setTo(short[] sArr, int i, short s) {
        if (sArr != null && i >= 0 && i < sArr.length) {
            sArr[i] = s;
        }
    }

    protected static char getFromArray(char[] cArr, int i) {
        if (cArr == null || i < 0 || i >= cArr.length) {
            return 0;
        }
        return cArr[i];
    }

    protected static void setTo(char[] cArr, int i, char c) {
        if (cArr != null && i >= 0 && i < cArr.length) {
            cArr[i] = c;
        }
    }

    protected static int getFromArray(int[] iArr, int i) {
        if (iArr == null || i < 0 || i >= iArr.length) {
            return 0;
        }
        return iArr[i];
    }

    protected static void setTo(int[] iArr, int i, int i2) {
        if (iArr != null && i >= 0 && i < iArr.length) {
            iArr[i] = i2;
        }
    }

    protected static long getFromArray(long[] jArr, int i) {
        if (jArr == null || i < 0 || i >= jArr.length) {
            return 0;
        }
        return jArr[i];
    }

    protected static void setTo(long[] jArr, int i, long j) {
        if (jArr != null && i >= 0 && i < jArr.length) {
            jArr[i] = j;
        }
    }

    protected static float getFromArray(float[] fArr, int i) {
        if (fArr == null || i < 0 || i >= fArr.length) {
            return 0.0f;
        }
        return fArr[i];
    }

    protected static void setTo(float[] fArr, int i, float f) {
        if (fArr != null && i >= 0 && i < fArr.length) {
            fArr[i] = f;
        }
    }

    protected static double getFromArray(double[] dArr, int i) {
        if (dArr == null || i < 0 || i >= dArr.length) {
            return 0.0d;
        }
        return dArr[i];
    }

    protected static void setTo(double[] dArr, int i, double d) {
        if (dArr != null && i >= 0 && i < dArr.length) {
            dArr[i] = d;
        }
    }

    protected static <T> T getFromList(List<T> list, int i) {
        if (list == null || i < 0 || i >= list.size()) {
            return null;
        }
        return list.get(i);
    }

    protected static <T> void setTo(List<T> list, int i, T t) {
        if (list != null && i >= 0 && i < list.size()) {
            list.set(i, t);
        }
    }

    protected static <T> T getFromList(SparseArray<T> sparseArray, int i) {
        if (sparseArray == null || i < 0) {
            return null;
        }
        return sparseArray.get(i);
    }

    protected static <T> void setTo(SparseArray<T> sparseArray, int i, T t) {
        if (sparseArray != null && i >= 0 && i < sparseArray.size()) {
            sparseArray.put(i, t);
        }
    }

    @TargetApi(16)
    protected static <T> T getFromList(LongSparseArray<T> longSparseArray, int i) {
        if (longSparseArray == null || i < 0) {
            return null;
        }
        return longSparseArray.get((long) i);
    }

    @TargetApi(16)
    protected static <T> void setTo(LongSparseArray<T> longSparseArray, int i, T t) {
        if (longSparseArray != null && i >= 0 && i < longSparseArray.size()) {
            longSparseArray.put((long) i, t);
        }
    }

    protected static <T> T getFromList(android.support.v4.util.LongSparseArray<T> longSparseArray, int i) {
        if (longSparseArray == null || i < 0) {
            return null;
        }
        return longSparseArray.get((long) i);
    }

    protected static <T> void setTo(android.support.v4.util.LongSparseArray<T> longSparseArray, int i, T t) {
        if (longSparseArray != null && i >= 0 && i < longSparseArray.size()) {
            longSparseArray.put((long) i, t);
        }
    }

    protected static boolean getFromList(SparseBooleanArray sparseBooleanArray, int i) {
        if (sparseBooleanArray == null || i < 0) {
            return false;
        }
        return sparseBooleanArray.get(i);
    }

    protected static void setTo(SparseBooleanArray sparseBooleanArray, int i, boolean z) {
        if (sparseBooleanArray != null && i >= 0 && i < sparseBooleanArray.size()) {
            sparseBooleanArray.put(i, z);
        }
    }

    protected static int getFromList(SparseIntArray sparseIntArray, int i) {
        if (sparseIntArray == null || i < 0) {
            return 0;
        }
        return sparseIntArray.get(i);
    }

    protected static void setTo(SparseIntArray sparseIntArray, int i, int i2) {
        if (sparseIntArray != null && i >= 0 && i < sparseIntArray.size()) {
            sparseIntArray.put(i, i2);
        }
    }

    @TargetApi(18)
    protected static long getFromList(SparseLongArray sparseLongArray, int i) {
        if (sparseLongArray == null || i < 0) {
            return 0;
        }
        return sparseLongArray.get(i);
    }

    @TargetApi(18)
    protected static void setTo(SparseLongArray sparseLongArray, int i, long j) {
        if (sparseLongArray != null && i >= 0 && i < sparseLongArray.size()) {
            sparseLongArray.put(i, j);
        }
    }

    protected static <K, T> T getFrom(Map<K, T> map, K k) {
        if (map == null) {
            return null;
        }
        return map.get(k);
    }

    protected static <K, T> void setTo(Map<K, T> map, K k, T t) {
        if (map != null) {
            map.put(k, t);
        }
    }

    protected static void setBindingInverseListener(ViewDataBinding viewDataBinding, InverseBindingListener inverseBindingListener, PropertyChangedInverseListener propertyChangedInverseListener) {
        if (inverseBindingListener != propertyChangedInverseListener) {
            if (inverseBindingListener != null) {
                viewDataBinding.removeOnPropertyChangedCallback((PropertyChangedInverseListener) inverseBindingListener);
            }
            if (propertyChangedInverseListener != null) {
                viewDataBinding.addOnPropertyChangedCallback(propertyChangedInverseListener);
            }
        }
    }

    protected static int safeUnbox(Integer num) {
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    protected static long safeUnbox(Long l) {
        if (l == null) {
            return 0;
        }
        return l.longValue();
    }

    protected static short safeUnbox(Short sh) {
        if (sh == null) {
            return 0;
        }
        return sh.shortValue();
    }

    protected static byte safeUnbox(Byte b) {
        if (b == null) {
            return 0;
        }
        return b.byteValue();
    }

    protected static char safeUnbox(Character ch) {
        if (ch == null) {
            return 0;
        }
        return ch.charValue();
    }

    protected static double safeUnbox(Double d) {
        if (d == null) {
            return 0.0d;
        }
        return d.doubleValue();
    }

    protected static float safeUnbox(Float f) {
        if (f == null) {
            return 0.0f;
        }
        return f.floatValue();
    }

    protected static boolean safeUnbox(Boolean bool) {
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    /* access modifiers changed from: protected */
    public void setContainedBinding(ViewDataBinding viewDataBinding) {
        if (viewDataBinding != null) {
            viewDataBinding.mContainingBinding = this;
        }
    }

    protected static Object[] mapBindings(DataBindingComponent dataBindingComponent, View[] viewArr, int i, IncludedLayouts includedLayouts, SparseIntArray sparseIntArray) {
        Object[] objArr = new Object[i];
        for (View mapBindings : viewArr) {
            mapBindings(dataBindingComponent, mapBindings, objArr, includedLayouts, sparseIntArray, true);
        }
        return objArr;
    }

    /* JADX WARNING: Removed duplicated region for block: B:68:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x010e A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void mapBindings(android.databinding.DataBindingComponent r15, android.view.View r16, java.lang.Object[] r17, android.databinding.ViewDataBinding.IncludedLayouts r18, android.util.SparseIntArray r19, boolean r20) {
        /*
            r6 = r15
            r0 = r16
            r7 = r18
            r8 = r19
            android.databinding.ViewDataBinding r1 = getBinding(r16)
            if (r1 == 0) goto L_0x000e
            return
        L_0x000e:
            java.lang.Object r1 = r16.getTag()
            boolean r2 = r1 instanceof java.lang.String
            if (r2 == 0) goto L_0x0019
            java.lang.String r1 = (java.lang.String) r1
            goto L_0x001a
        L_0x0019:
            r1 = 0
        L_0x001a:
            r2 = -1
            r10 = 1
            if (r20 == 0) goto L_0x004b
            if (r1 == 0) goto L_0x004b
            java.lang.String r3 = "layout"
            boolean r3 = r1.startsWith(r3)
            if (r3 == 0) goto L_0x004b
            r3 = 95
            int r3 = r1.lastIndexOf(r3)
            if (r3 <= 0) goto L_0x0047
            int r3 = r3 + r10
            boolean r4 = isNumeric(r1, r3)
            if (r4 == 0) goto L_0x0047
            int r1 = parseTagInt(r1, r3)
            r3 = r17[r1]
            if (r3 != 0) goto L_0x0041
            r17[r1] = r0
        L_0x0041:
            if (r7 != 0) goto L_0x0044
            r1 = -1
        L_0x0044:
            r3 = r1
            r1 = 1
            goto L_0x0049
        L_0x0047:
            r1 = 0
            r3 = -1
        L_0x0049:
            r11 = r3
            goto L_0x0069
        L_0x004b:
            if (r1 == 0) goto L_0x0067
            java.lang.String r3 = "binding_"
            boolean r3 = r1.startsWith(r3)
            if (r3 == 0) goto L_0x0067
            int r3 = BINDING_NUMBER_START
            int r1 = parseTagInt(r1, r3)
            r3 = r17[r1]
            if (r3 != 0) goto L_0x0061
            r17[r1] = r0
        L_0x0061:
            if (r7 != 0) goto L_0x0064
            r1 = -1
        L_0x0064:
            r11 = r1
            r1 = 1
            goto L_0x0069
        L_0x0067:
            r1 = 0
            r11 = -1
        L_0x0069:
            if (r1 != 0) goto L_0x007f
            int r1 = r16.getId()
            if (r1 <= 0) goto L_0x007f
            if (r8 == 0) goto L_0x007f
            int r1 = r8.get(r1, r2)
            if (r1 < 0) goto L_0x007f
            r2 = r17[r1]
            if (r2 != 0) goto L_0x007f
            r17[r1] = r0
        L_0x007f:
            boolean r1 = r0 instanceof android.view.ViewGroup
            if (r1 == 0) goto L_0x0116
            r12 = r0
            android.view.ViewGroup r12 = (android.view.ViewGroup) r12
            int r13 = r12.getChildCount()
            r0 = 0
            r1 = 0
        L_0x008c:
            if (r0 >= r13) goto L_0x0116
            android.view.View r2 = r12.getChildAt(r0)
            if (r11 < 0) goto L_0x00fd
            java.lang.Object r3 = r2.getTag()
            boolean r3 = r3 instanceof java.lang.String
            if (r3 == 0) goto L_0x00fd
            java.lang.Object r3 = r2.getTag()
            java.lang.String r3 = (java.lang.String) r3
            java.lang.String r4 = "_0"
            boolean r4 = r3.endsWith(r4)
            if (r4 == 0) goto L_0x00fd
            java.lang.String r4 = "layout"
            boolean r4 = r3.startsWith(r4)
            if (r4 == 0) goto L_0x00fd
            r4 = 47
            int r4 = r3.indexOf(r4)
            if (r4 <= 0) goto L_0x00fd
            int r3 = findIncludeIndex(r3, r1, r7, r11)
            if (r3 < 0) goto L_0x00fd
            int r1 = r3 + 1
            int[][] r4 = r7.indexes
            r4 = r4[r11]
            r4 = r4[r3]
            int[][] r5 = r7.layoutIds
            r5 = r5[r11]
            r3 = r5[r3]
            int r5 = findLastMatching(r12, r0)
            if (r5 != r0) goto L_0x00dd
            android.databinding.ViewDataBinding r3 = android.databinding.DataBindingUtil.bind((android.databinding.DataBindingComponent) r15, (android.view.View) r2, (int) r3)
            r17[r4] = r3
            r9 = r0
            r14 = r1
            goto L_0x0100
        L_0x00dd:
            int r5 = r5 - r0
            int r5 = r5 + r10
            android.view.View[] r14 = new android.view.View[r5]
            r9 = 0
        L_0x00e2:
            if (r9 >= r5) goto L_0x00f0
            int r10 = r0 + r9
            android.view.View r10 = r12.getChildAt(r10)
            r14[r9] = r10
            int r9 = r9 + 1
            r10 = 1
            goto L_0x00e2
        L_0x00f0:
            android.databinding.ViewDataBinding r3 = android.databinding.DataBindingUtil.bind((android.databinding.DataBindingComponent) r15, (android.view.View[]) r14, (int) r3)
            r17[r4] = r3
            int r5 = r5 + -1
            int r0 = r0 + r5
            r9 = r0
            r14 = r1
            r10 = 1
            goto L_0x0100
        L_0x00fd:
            r9 = r0
            r14 = r1
            r10 = 0
        L_0x0100:
            if (r10 != 0) goto L_0x010e
            r5 = 0
            r0 = r15
            r1 = r2
            r2 = r17
            r3 = r18
            r4 = r19
            mapBindings(r0, r1, r2, r3, r4, r5)
        L_0x010e:
            r0 = 1
            int r1 = r9 + 1
            r0 = r1
            r1 = r14
            r10 = 1
            goto L_0x008c
        L_0x0116:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.databinding.ViewDataBinding.mapBindings(android.databinding.DataBindingComponent, android.view.View, java.lang.Object[], android.databinding.ViewDataBinding$IncludedLayouts, android.util.SparseIntArray, boolean):void");
    }

    private static int findIncludeIndex(String str, int i, IncludedLayouts includedLayouts, int i2) {
        CharSequence subSequence = str.subSequence(str.indexOf(47) + 1, str.length() - 2);
        String[] strArr = includedLayouts.layouts[i2];
        int length = strArr.length;
        while (i < length) {
            if (TextUtils.equals(subSequence, strArr[i])) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private static int findLastMatching(ViewGroup viewGroup, int i) {
        String str = (String) viewGroup.getChildAt(i).getTag();
        String substring = str.substring(0, str.length() - 1);
        int length = substring.length();
        int childCount = viewGroup.getChildCount();
        for (int i2 = i + 1; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            String str2 = childAt.getTag() instanceof String ? (String) childAt.getTag() : null;
            if (str2 != null && str2.startsWith(substring)) {
                if (str2.length() == str.length() && str2.charAt(str2.length() - 1) == '0') {
                    return i;
                }
                if (isNumeric(str2, length)) {
                    i = i2;
                }
            }
        }
        return i;
    }

    private static boolean isNumeric(String str, int i) {
        int length = str.length();
        if (length == i) {
            return false;
        }
        while (i < length) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
            i++;
        }
        return true;
    }

    private static int parseTagInt(String str, int i) {
        int length = str.length();
        int i2 = 0;
        while (i < length) {
            i2 = (i2 * 10) + (str.charAt(i) - '0');
            i++;
        }
        return i2;
    }

    /* access modifiers changed from: private */
    public static void processReferenceQueue() {
        while (true) {
            Reference<? extends ViewDataBinding> poll = sReferenceQueue.poll();
            if (poll == null) {
                return;
            }
            if (poll instanceof WeakListener) {
                ((WeakListener) poll).unregister();
            }
        }
    }

    private static class WeakListener<T> extends WeakReference<ViewDataBinding> {
        protected final int mLocalFieldId;
        private final ObservableReference<T> mObservable;
        private T mTarget;

        public WeakListener(ViewDataBinding viewDataBinding, int i, ObservableReference<T> observableReference) {
            super(viewDataBinding, ViewDataBinding.sReferenceQueue);
            this.mLocalFieldId = i;
            this.mObservable = observableReference;
        }

        public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
            this.mObservable.setLifecycleOwner(lifecycleOwner);
        }

        public void setTarget(T t) {
            unregister();
            this.mTarget = t;
            if (this.mTarget != null) {
                this.mObservable.addListener(this.mTarget);
            }
        }

        public boolean unregister() {
            boolean z;
            if (this.mTarget != null) {
                this.mObservable.removeListener(this.mTarget);
                z = true;
            } else {
                z = false;
            }
            this.mTarget = null;
            return z;
        }

        public T getTarget() {
            return this.mTarget;
        }

        /* access modifiers changed from: protected */
        public ViewDataBinding getBinder() {
            ViewDataBinding viewDataBinding = (ViewDataBinding) get();
            if (viewDataBinding == null) {
                unregister();
            }
            return viewDataBinding;
        }
    }

    private static class WeakPropertyListener extends Observable.OnPropertyChangedCallback implements ObservableReference<Observable> {
        final WeakListener<Observable> mListener;

        public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        }

        public WeakPropertyListener(ViewDataBinding viewDataBinding, int i) {
            this.mListener = new WeakListener<>(viewDataBinding, i, this);
        }

        public WeakListener<Observable> getListener() {
            return this.mListener;
        }

        public void addListener(Observable observable) {
            observable.addOnPropertyChangedCallback(this);
        }

        public void removeListener(Observable observable) {
            observable.removeOnPropertyChangedCallback(this);
        }

        public void onPropertyChanged(Observable observable, int i) {
            ViewDataBinding binder = this.mListener.getBinder();
            if (binder != null && this.mListener.getTarget() == observable) {
                binder.handleFieldChange(this.mListener.mLocalFieldId, observable, i);
            }
        }
    }

    private static class WeakListListener extends ObservableList.OnListChangedCallback implements ObservableReference<ObservableList> {
        final WeakListener<ObservableList> mListener;

        public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        }

        public WeakListListener(ViewDataBinding viewDataBinding, int i) {
            this.mListener = new WeakListener<>(viewDataBinding, i, this);
        }

        public WeakListener<ObservableList> getListener() {
            return this.mListener;
        }

        public void addListener(ObservableList observableList) {
            observableList.addOnListChangedCallback(this);
        }

        public void removeListener(ObservableList observableList) {
            observableList.removeOnListChangedCallback(this);
        }

        public void onChanged(ObservableList observableList) {
            ObservableList target;
            ViewDataBinding binder = this.mListener.getBinder();
            if (binder != null && (target = this.mListener.getTarget()) == observableList) {
                binder.handleFieldChange(this.mListener.mLocalFieldId, target, 0);
            }
        }

        public void onItemRangeChanged(ObservableList observableList, int i, int i2) {
            onChanged(observableList);
        }

        public void onItemRangeInserted(ObservableList observableList, int i, int i2) {
            onChanged(observableList);
        }

        public void onItemRangeMoved(ObservableList observableList, int i, int i2, int i3) {
            onChanged(observableList);
        }

        public void onItemRangeRemoved(ObservableList observableList, int i, int i2) {
            onChanged(observableList);
        }
    }

    private static class WeakMapListener extends ObservableMap.OnMapChangedCallback implements ObservableReference<ObservableMap> {
        final WeakListener<ObservableMap> mListener;

        public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        }

        public WeakMapListener(ViewDataBinding viewDataBinding, int i) {
            this.mListener = new WeakListener<>(viewDataBinding, i, this);
        }

        public WeakListener<ObservableMap> getListener() {
            return this.mListener;
        }

        public void addListener(ObservableMap observableMap) {
            observableMap.addOnMapChangedCallback(this);
        }

        public void removeListener(ObservableMap observableMap) {
            observableMap.removeOnMapChangedCallback(this);
        }

        public void onMapChanged(ObservableMap observableMap, Object obj) {
            ViewDataBinding binder = this.mListener.getBinder();
            if (binder != null && observableMap == this.mListener.getTarget()) {
                binder.handleFieldChange(this.mListener.mLocalFieldId, observableMap, 0);
            }
        }
    }

    private static class LiveDataListener implements Observer, ObservableReference<LiveData<?>> {
        LifecycleOwner mLifecycleOwner;
        final WeakListener<LiveData<?>> mListener;

        public LiveDataListener(ViewDataBinding viewDataBinding, int i) {
            this.mListener = new WeakListener<>(viewDataBinding, i, this);
        }

        public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
            LiveData target = this.mListener.getTarget();
            if (target != null) {
                if (this.mLifecycleOwner != null) {
                    target.removeObserver(this);
                }
                if (lifecycleOwner != null) {
                    target.observe(lifecycleOwner, this);
                }
            }
            this.mLifecycleOwner = lifecycleOwner;
        }

        public WeakListener<LiveData<?>> getListener() {
            return this.mListener;
        }

        public void addListener(LiveData<?> liveData) {
            if (this.mLifecycleOwner != null) {
                liveData.observe(this.mLifecycleOwner, this);
            }
        }

        public void removeListener(LiveData<?> liveData) {
            liveData.removeObserver(this);
        }

        public void onChanged(@Nullable Object obj) {
            this.mListener.getBinder().handleFieldChange(this.mListener.mLocalFieldId, this.mListener.getTarget(), 0);
        }
    }

    protected static class IncludedLayouts {
        public final int[][] indexes;
        public final int[][] layoutIds;
        public final String[][] layouts;

        public IncludedLayouts(int i) {
            this.layouts = new String[i][];
            this.indexes = new int[i][];
            this.layoutIds = new int[i][];
        }

        public void setIncludes(int i, String[] strArr, int[] iArr, int[] iArr2) {
            this.layouts[i] = strArr;
            this.indexes[i] = iArr;
            this.layoutIds[i] = iArr2;
        }
    }

    protected static abstract class PropertyChangedInverseListener extends Observable.OnPropertyChangedCallback implements InverseBindingListener {
        final int mPropertyId;

        public PropertyChangedInverseListener(int i) {
            this.mPropertyId = i;
        }

        public void onPropertyChanged(Observable observable, int i) {
            if (i == this.mPropertyId || i == 0) {
                onChange();
            }
        }
    }

    public class OnStartListener implements LifecycleObserver {
        private OnStartListener() {
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        public void onStart() {
            ViewDataBinding.this.executePendingBindings();
        }
    }
}
