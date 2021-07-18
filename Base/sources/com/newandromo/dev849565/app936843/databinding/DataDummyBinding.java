package com.newandromo.dev849565.app936843.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.newandromo.dev849565.app936843.Item;
import com.newandromo.dev849565.app936843.ItemViewHolder;
import com.newandromo.dev849565.app936843.R;

public class DataDummyBinding extends ViewDataBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    @NonNull
    public final LinearLayout itemRoot;
    @Nullable
    private ViewDataBinding mDefaultLayoutBinding;
    private long mDirtyFlags = -1;
    @Nullable
    private ItemViewHolder mHolder;
    @Nullable
    private ObservableInt mImageFallbackColor;
    @Nullable
    private ObservableBoolean mIsDarkTheme;
    @Nullable
    private Item mItem;
    @Nullable
    private int mPosition;
    @Nullable
    private int mTitleGravity;
    @Nullable
    private ObservableInt mToolbarColor;
    @Nullable
    private String mWideImageRatio;

    public DataDummyBinding(@NonNull DataBindingComponent dataBindingComponent, @NonNull View view) {
        super(dataBindingComponent, view, 5);
        this.itemRoot = (LinearLayout) mapBindings(dataBindingComponent, view, 1, sIncludes, sViewsWithIds)[0];
        this.itemRoot.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 512;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    public boolean setVariable(int i, @Nullable Object obj) {
        if (9 == i) {
            setItem((Item) obj);
        } else if (32 == i) {
            setIsDarkTheme((ObservableBoolean) obj);
        } else if (35 == i) {
            setDefaultLayoutBinding((ViewDataBinding) obj);
        } else if (38 == i) {
            setImageFallbackColor((ObservableInt) obj);
        } else if (4 == i) {
            setToolbarColor((ObservableInt) obj);
        } else if (11 == i) {
            setWideImageRatio((String) obj);
        } else if (52 == i) {
            setTitleGravity(((Integer) obj).intValue());
        } else if (14 == i) {
            setPosition(((Integer) obj).intValue());
        } else if (49 != i) {
            return false;
        } else {
            setHolder((ItemViewHolder) obj);
        }
        return true;
    }

    public void setItem(@Nullable Item item) {
        this.mItem = item;
    }

    @Nullable
    public Item getItem() {
        return this.mItem;
    }

    public void setIsDarkTheme(@Nullable ObservableBoolean observableBoolean) {
        this.mIsDarkTheme = observableBoolean;
    }

    @Nullable
    public ObservableBoolean getIsDarkTheme() {
        return this.mIsDarkTheme;
    }

    public void setDefaultLayoutBinding(@Nullable ViewDataBinding viewDataBinding) {
        this.mDefaultLayoutBinding = viewDataBinding;
    }

    @Nullable
    public ViewDataBinding getDefaultLayoutBinding() {
        return this.mDefaultLayoutBinding;
    }

    public void setImageFallbackColor(@Nullable ObservableInt observableInt) {
        this.mImageFallbackColor = observableInt;
    }

    @Nullable
    public ObservableInt getImageFallbackColor() {
        return this.mImageFallbackColor;
    }

    public void setToolbarColor(@Nullable ObservableInt observableInt) {
        this.mToolbarColor = observableInt;
    }

    @Nullable
    public ObservableInt getToolbarColor() {
        return this.mToolbarColor;
    }

    public void setWideImageRatio(@Nullable String str) {
        this.mWideImageRatio = str;
    }

    @Nullable
    public String getWideImageRatio() {
        return this.mWideImageRatio;
    }

    public void setTitleGravity(int i) {
        this.mTitleGravity = i;
    }

    public int getTitleGravity() {
        return this.mTitleGravity;
    }

    public void setPosition(int i) {
        this.mPosition = i;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public void setHolder(@Nullable ItemViewHolder itemViewHolder) {
        this.mHolder = itemViewHolder;
    }

    @Nullable
    public ItemViewHolder getHolder() {
        return this.mHolder;
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeItem((Item) obj, i2);
            case 1:
                return onChangeIsDarkTheme((ObservableBoolean) obj, i2);
            case 2:
                return onChangeDefaultLayoutBinding((ViewDataBinding) obj, i2);
            case 3:
                return onChangeImageFallbackColor((ObservableInt) obj, i2);
            case 4:
                return onChangeToolbarColor((ObservableInt) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeItem(Item item, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeIsDarkTheme(ObservableBoolean observableBoolean, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeDefaultLayoutBinding(ViewDataBinding viewDataBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeImageFallbackColor(ObservableInt observableInt, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeToolbarColor(ObservableInt observableInt, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        synchronized (this) {
            long j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
    }

    @NonNull
    public static DataDummyBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static DataDummyBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable DataBindingComponent dataBindingComponent) {
        return (DataDummyBinding) DataBindingUtil.inflate(layoutInflater, R.layout.data_dummy, viewGroup, z, dataBindingComponent);
    }

    @NonNull
    public static DataDummyBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static DataDummyBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable DataBindingComponent dataBindingComponent) {
        return bind(layoutInflater.inflate(R.layout.data_dummy, (ViewGroup) null, false), dataBindingComponent);
    }

    @NonNull
    public static DataDummyBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static DataDummyBinding bind(@NonNull View view, @Nullable DataBindingComponent dataBindingComponent) {
        if ("layout/data_dummy_0".equals(view.getTag())) {
            return new DataDummyBinding(dataBindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
