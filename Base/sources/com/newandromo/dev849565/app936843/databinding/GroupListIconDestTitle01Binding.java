package com.newandromo.dev849565.app936843.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.ViewDataBinding;
import android.databinding.generated.callback.OnClickListener;
import android.databinding.generated.callback.OnLongClickListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Guideline;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.newandromo.dev849565.app936843.DashboardItem;
import com.newandromo.dev849565.app936843.ItemViewHolder;
import com.newandromo.dev849565.app936843.R;
import com.newandromo.dev849565.app936843.TintableImageView;

public class GroupListIconDestTitle01Binding extends ViewDataBinding implements OnLongClickListener.Listener, OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final Guideline guideline;
    @NonNull
    public final TintableImageView itemImage;
    @NonNull
    public final ConstraintLayout itemRoot;
    @NonNull
    public final TextView itemTitle;
    @Nullable
    private final View.OnClickListener mCallback1;
    @Nullable
    private final View.OnLongClickListener mCallback2;
    @Nullable
    private GroupListIconDestTitle01Binding mDefaultLayoutBinding;
    private long mDirtyFlags = -1;
    @Nullable
    private ItemViewHolder mHolder;
    @Nullable
    private DashboardItem mItem;
    @Nullable
    private int mPosition;

    static {
        sViewsWithIds.put(R.id.guideline, 3);
    }

    public GroupListIconDestTitle01Binding(@NonNull DataBindingComponent dataBindingComponent, @NonNull View view) {
        super(dataBindingComponent, view, 2);
        Object[] mapBindings = mapBindings(dataBindingComponent, view, 4, sIncludes, sViewsWithIds);
        this.guideline = (Guideline) mapBindings[3];
        this.itemImage = (TintableImageView) mapBindings[1];
        this.itemImage.setTag((Object) null);
        this.itemRoot = (ConstraintLayout) mapBindings[0];
        this.itemRoot.setTag((Object) null);
        this.itemTitle = (TextView) mapBindings[2];
        this.itemTitle.setTag((Object) null);
        setRootTag(view);
        this.mCallback2 = new OnLongClickListener(this, 2);
        this.mCallback1 = new OnClickListener(this, 1);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32;
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
            setItem((DashboardItem) obj);
        } else if (35 == i) {
            setDefaultLayoutBinding((GroupListIconDestTitle01Binding) obj);
        } else if (14 == i) {
            setPosition(((Integer) obj).intValue());
        } else if (49 != i) {
            return false;
        } else {
            setHolder((ItemViewHolder) obj);
        }
        return true;
    }

    public void setItem(@Nullable DashboardItem dashboardItem) {
        updateRegistration(0, (Observable) dashboardItem);
        this.mItem = dashboardItem;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(9);
        super.requestRebind();
    }

    @Nullable
    public DashboardItem getItem() {
        return this.mItem;
    }

    public void setDefaultLayoutBinding(@Nullable GroupListIconDestTitle01Binding groupListIconDestTitle01Binding) {
        this.mDefaultLayoutBinding = groupListIconDestTitle01Binding;
    }

    @Nullable
    public GroupListIconDestTitle01Binding getDefaultLayoutBinding() {
        return this.mDefaultLayoutBinding;
    }

    public void setPosition(int i) {
        this.mPosition = i;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public void setHolder(@Nullable ItemViewHolder itemViewHolder) {
        this.mHolder = itemViewHolder;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(49);
        super.requestRebind();
    }

    @Nullable
    public ItemViewHolder getHolder() {
        return this.mHolder;
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeItem((DashboardItem) obj, i2);
            case 1:
                return onChangeDefaultLayoutBinding((GroupListIconDestTitle01Binding) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeItem(DashboardItem dashboardItem, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i != 2) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
    }

    private boolean onChangeDefaultLayoutBinding(GroupListIconDestTitle01Binding groupListIconDestTitle01Binding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r15 = this;
            monitor-enter(r15)
            long r0 = r15.mDirtyFlags     // Catch:{ all -> 0x005f }
            r2 = 0
            r15.mDirtyFlags = r2     // Catch:{ all -> 0x005f }
            monitor-exit(r15)     // Catch:{ all -> 0x005f }
            com.newandromo.dev849565.app936843.DashboardItem r4 = r15.mItem
            r5 = 0
            com.newandromo.dev849565.app936843.ItemViewHolder r6 = r15.mHolder
            r6 = 49
            long r6 = r6 & r0
            r8 = 33
            r10 = 0
            int r11 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r11 == 0) goto L_0x0031
            long r11 = r0 & r8
            int r13 = (r11 > r2 ? 1 : (r11 == r2 ? 0 : -1))
            if (r13 == 0) goto L_0x0023
            if (r4 == 0) goto L_0x0023
            java.lang.CharSequence r5 = r4.getTitle()
        L_0x0023:
            if (r4 == 0) goto L_0x0031
            int r10 = r4.getPrimaryColor()
            int r4 = r4.getIcon()
            r14 = r10
            r10 = r4
            r4 = r14
            goto L_0x0032
        L_0x0031:
            r4 = 0
        L_0x0032:
            int r11 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r11 == 0) goto L_0x003f
            com.newandromo.dev849565.app936843.TintableImageView r6 = r15.itemImage
            android.content.res.ColorStateList r4 = android.databinding.adapters.Converters.convertColorToColorStateList(r4)
            com.newandromo.dev849565.app936843.TintableImageView.setImageResourceAndTint(r6, r10, r4)
        L_0x003f:
            r6 = 32
            long r6 = r6 & r0
            int r4 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x0054
            android.support.constraint.ConstraintLayout r4 = r15.itemRoot
            android.view.View$OnClickListener r6 = r15.mCallback1
            r4.setOnClickListener(r6)
            android.support.constraint.ConstraintLayout r4 = r15.itemRoot
            android.view.View$OnLongClickListener r6 = r15.mCallback2
            r4.setOnLongClickListener(r6)
        L_0x0054:
            long r0 = r0 & r8
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x005e
            android.widget.TextView r0 = r15.itemTitle
            android.databinding.adapters.TextViewBindingAdapter.setText(r0, r5)
        L_0x005e:
            return
        L_0x005f:
            r0 = move-exception
            monitor-exit(r15)     // Catch:{ all -> 0x005f }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newandromo.dev849565.app936843.databinding.GroupListIconDestTitle01Binding.executeBindings():void");
    }

    public final boolean _internalCallbackOnLongClick(int i, View view) {
        DashboardItem dashboardItem = this.mItem;
        ItemViewHolder itemViewHolder = this.mHolder;
        if (dashboardItem != null) {
            return dashboardItem.onLongClick(view, itemViewHolder);
        }
        return false;
    }

    public final void _internalCallbackOnClick(int i, View view) {
        DashboardItem dashboardItem = this.mItem;
        ItemViewHolder itemViewHolder = this.mHolder;
        if (dashboardItem != null) {
            dashboardItem.onClick(view, itemViewHolder);
        }
    }

    @NonNull
    public static GroupListIconDestTitle01Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static GroupListIconDestTitle01Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable DataBindingComponent dataBindingComponent) {
        return (GroupListIconDestTitle01Binding) DataBindingUtil.inflate(layoutInflater, R.layout.group_list_icon_dest_title_01, viewGroup, z, dataBindingComponent);
    }

    @NonNull
    public static GroupListIconDestTitle01Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static GroupListIconDestTitle01Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable DataBindingComponent dataBindingComponent) {
        return bind(layoutInflater.inflate(R.layout.group_list_icon_dest_title_01, (ViewGroup) null, false), dataBindingComponent);
    }

    @NonNull
    public static GroupListIconDestTitle01Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static GroupListIconDestTitle01Binding bind(@NonNull View view, @Nullable DataBindingComponent dataBindingComponent) {
        if ("layout/group_list_icon_dest_title_01_0".equals(view.getTag())) {
            return new GroupListIconDestTitle01Binding(dataBindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
