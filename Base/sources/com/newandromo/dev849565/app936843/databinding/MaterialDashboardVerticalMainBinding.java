package com.newandromo.dev849565.app936843.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewStubProxy;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.Space;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.newandromo.dev849565.app936843.R;

public class MaterialDashboardVerticalMainBinding extends ViewDataBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final ViewStubProxy appBar;
    @NonNull
    public final ImageView backgroundImage;
    @NonNull
    public final FrameLayout bannerAdStaticContainer;
    @NonNull
    public final View bannerDivider;
    @NonNull
    public final View bannerScrim;
    @NonNull
    public final Space bannerScrimStart;
    @NonNull
    public final LinearLayout contentAdLayout;
    @NonNull
    public final DrawerLayout drawerLayout;
    private long mDirtyFlags = -1;
    @NonNull
    public final CoordinatorLayout mainContent;
    @NonNull
    public final ViewStubProxy musicControls;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    static {
        sViewsWithIds.put(R.id.background_image, 2);
        sViewsWithIds.put(R.id.app_bar, 3);
        sViewsWithIds.put(R.id.music_controls, 4);
        sViewsWithIds.put(R.id.bannerScrimStart, 5);
        sViewsWithIds.put(R.id.bannerScrim, 6);
        sViewsWithIds.put(R.id.bannerDivider, 7);
        sViewsWithIds.put(R.id.bannerAdStaticContainer, 8);
        sViewsWithIds.put(R.id.contentAdLayout, 9);
    }

    public MaterialDashboardVerticalMainBinding(@NonNull DataBindingComponent dataBindingComponent, @NonNull View view) {
        super(dataBindingComponent, view, 0);
        Object[] mapBindings = mapBindings(dataBindingComponent, view, 10, sIncludes, sViewsWithIds);
        this.appBar = new ViewStubProxy((ViewStub) mapBindings[3]);
        this.appBar.setContainingBinding(this);
        this.backgroundImage = (ImageView) mapBindings[2];
        this.bannerAdStaticContainer = (FrameLayout) mapBindings[8];
        this.bannerDivider = (View) mapBindings[7];
        this.bannerScrim = (View) mapBindings[6];
        this.bannerScrimStart = (Space) mapBindings[5];
        this.contentAdLayout = (LinearLayout) mapBindings[9];
        this.drawerLayout = (DrawerLayout) mapBindings[0];
        this.drawerLayout.setTag((Object) null);
        this.mainContent = (CoordinatorLayout) mapBindings[1];
        this.mainContent.setTag((Object) null);
        this.musicControls = new ViewStubProxy((ViewStub) mapBindings[4]);
        this.musicControls.setContainingBinding(this);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1;
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

    /* access modifiers changed from: protected */
    public void executeBindings() {
        synchronized (this) {
            long j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        if (this.appBar.getBinding() != null) {
            executeBindingsOn(this.appBar.getBinding());
        }
        if (this.musicControls.getBinding() != null) {
            executeBindingsOn(this.musicControls.getBinding());
        }
    }

    @NonNull
    public static MaterialDashboardVerticalMainBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MaterialDashboardVerticalMainBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable DataBindingComponent dataBindingComponent) {
        return (MaterialDashboardVerticalMainBinding) DataBindingUtil.inflate(layoutInflater, R.layout.material_dashboard_vertical_main, viewGroup, z, dataBindingComponent);
    }

    @NonNull
    public static MaterialDashboardVerticalMainBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MaterialDashboardVerticalMainBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable DataBindingComponent dataBindingComponent) {
        return bind(layoutInflater.inflate(R.layout.material_dashboard_vertical_main, (ViewGroup) null, false), dataBindingComponent);
    }

    @NonNull
    public static MaterialDashboardVerticalMainBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MaterialDashboardVerticalMainBinding bind(@NonNull View view, @Nullable DataBindingComponent dataBindingComponent) {
        if ("layout/material_dashboard_vertical_main_0".equals(view.getTag())) {
            return new MaterialDashboardVerticalMainBinding(dataBindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
