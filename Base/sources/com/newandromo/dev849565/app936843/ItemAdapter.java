package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.newandromo.dev849565.app936843.Item;
import java.util.ArrayList;
import java.util.List;

public abstract class ItemAdapter<T extends Item> extends RecyclerView.Adapter<ItemViewHolder> implements Recycleable, OnToolbarColorsReady {
    private static final int ITEM_MODEL = 2131296259;
    private static final String TAG = "ItemAdapter";
    private int attachedRecyclerViews;
    private AutoColorArrayManager autoColorArrayManager;
    protected boolean autoColorsEnabled;
    private int autoColorsPerItem;
    protected int backgroundType;
    private ViewDataBinding defaultLayoutBinding;
    private final ObservableInt imageFallbackColor;
    private LayoutInflater inflater;
    private final ObservableBoolean isDarkTheme;
    private boolean isImageFallbackColorToolbarColor;
    private final ItemBinder<T> itemBinder;
    private int numBoundViewHolders;
    private PaletteArrayManager paletteArrayManager;
    private int palettesPerItem;
    private List<RecyclerView.AdapterDataObserver> pendingObservers;
    private final PositionBinder positionBinder;
    @Nullable
    protected RecyclerView recyclerView;
    private boolean registered;
    private final ObservableInt toolbarColor;
    private final String wideImageRatio;

    public long getItemId(int i) {
        return -1;
    }

    public abstract T getItemToBind(Context context, int i, int i2);

    /* access modifiers changed from: protected */
    public abstract void onCreateItemBinding(ViewDataBinding viewDataBinding);

    public void restore(Context context) {
    }

    /* access modifiers changed from: protected */
    public void setImageFallbackColor(int i) {
        this.imageFallbackColor.set(i);
    }

    public final void setImageFallbackColorFromToolbar(boolean z) {
        this.isImageFallbackColorToolbarColor = z;
        if (z) {
            this.imageFallbackColor.set(this.toolbarColor.get());
        }
    }

    public void ensureAutoColorArrayCapacity(int i, int i2) {
        if (this.autoColorArrayManager != null) {
            this.autoColorArrayManager.ensureCapacity(i, i2);
        }
    }

    public void ensureAutoColorArrayCapacity(int i) {
        if (this.autoColorArrayManager != null) {
            this.autoColorArrayManager.ensureCapacity(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean isDarkTheme() {
        return this.isDarkTheme.get();
    }

    /* access modifiers changed from: protected */
    public int toolbarColor() {
        return this.toolbarColor.get();
    }

    /* access modifiers changed from: protected */
    public int imageFallbackColor() {
        return this.imageFallbackColor.get();
    }

    public ItemAdapter(ItemBinder<T> itemBinder2, int i, boolean z, String str) {
        this(itemBinder2, (PositionBinder) null, i, z, str);
    }

    public ItemAdapter(PositionBinder positionBinder2, int i, boolean z, String str) {
        this((ItemBinder) null, positionBinder2, i, z, str);
    }

    public ItemAdapter(ItemBinder<T> itemBinder2, PositionBinder positionBinder2, int i, boolean z, String str) {
        this.toolbarColor = new ObservableInt();
        this.imageFallbackColor = new ObservableInt();
        this.isDarkTheme = new ObservableBoolean();
        this.autoColorsPerItem = 10;
        this.palettesPerItem = 10;
        this.attachedRecyclerViews = 0;
        this.itemBinder = itemBinder2;
        this.positionBinder = positionBinder2;
        this.backgroundType = i;
        this.autoColorsEnabled = z;
        if (z) {
            this.autoColorArrayManager = new AutoColorArrayManager(this.autoColorsPerItem);
            this.paletteArrayManager = new PaletteArrayManager(this.palettesPerItem);
        }
        this.wideImageRatio = str;
    }

    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver adapterDataObserver) {
        if (this.registered) {
            super.registerAdapterDataObserver(adapterDataObserver);
            return;
        }
        if (this.pendingObservers == null) {
            this.pendingObservers = new ArrayList();
        }
        this.pendingObservers.add(adapterDataObserver);
    }

    public void registerAdapterDataObservers() {
        if (!this.registered) {
            if (this.autoColorArrayManager != null) {
                registerAdapterDataObserver(this.autoColorArrayManager.changeObserver);
            }
            if (this.paletteArrayManager != null) {
                registerAdapterDataObserver(this.paletteArrayManager.changeObserver);
            }
            registerPendingObservers();
            this.registered = true;
        }
    }

    private void registerPendingObservers() {
        if (!this.registered && this.pendingObservers != null) {
            for (RecyclerView.AdapterDataObserver registerAdapterDataObserver : this.pendingObservers) {
                super.registerAdapterDataObserver(registerAdapterDataObserver);
            }
            this.pendingObservers.clear();
            this.pendingObservers = null;
            this.registered = true;
        }
    }

    private void unregisterAdapterDataObservers() {
        if (this.autoColorsEnabled && this.registered) {
            if (this.paletteArrayManager != null) {
                try {
                    unregisterAdapterDataObserver(this.paletteArrayManager.changeObserver);
                } catch (IllegalStateException unused) {
                }
            }
            if (this.autoColorArrayManager != null) {
                try {
                    unregisterAdapterDataObserver(this.autoColorArrayManager.changeObserver);
                } catch (IllegalStateException unused2) {
                }
            }
            this.registered = false;
        }
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView2) {
        this.recyclerView = recyclerView2;
        this.inflater = null;
        this.attachedRecyclerViews++;
        registerAdapterDataObservers();
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView2) {
        int i = this.attachedRecyclerViews - 1;
        this.attachedRecyclerViews = i;
        if (i <= 0) {
            unregisterAdapterDataObservers();
        }
        this.recyclerView = null;
    }

    public final int getItemViewType(int i) {
        if (this.positionBinder != null) {
            return this.positionBinder.getLayoutResId(i);
        }
        if (this.itemBinder != null) {
            return this.itemBinder.getLayoutResId(getItem(i));
        }
        throw new IllegalStateException("itemBinder and positionBinder are both null");
    }

    public T getItem(int i) {
        throw new IllegalStateException("getItem(int position) needs to be implemented if using an ItemBinder instead of a PositionBinder.");
    }

    @CallSuper
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        if (this.inflater == null) {
            this.inflater = LayoutInflater.from(context);
        }
        ViewDataBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(context), i, viewGroup, false);
        if (this.defaultLayoutBinding == null && !ThemeUtils.isDefaultLayoutBindingWorkaroundNeeded()) {
            this.defaultLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(context), i, viewGroup, false);
        }
        inflate.setVariable(35, this.defaultLayoutBinding);
        inflate.setVariable(4, this.toolbarColor);
        inflate.setVariable(38, this.imageFallbackColor);
        inflate.setVariable(11, this.wideImageRatio);
        this.isDarkTheme.set(!ThemeUtils.getBoolean(context, R.attr.isLightTheme));
        inflate.setVariable(32, this.isDarkTheme);
        onCreateItemBinding(inflate);
        return new ItemViewHolder(inflate);
    }

    public void onToolbarColorsReady(int i, int i2) {
        this.toolbarColor.set(i);
        if (this.isImageFallbackColorToolbarColor) {
            this.imageFallbackColor.set(i);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: com.newandromo.dev849565.app936843.AutoColorArray} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onBindViewHolder(com.newandromo.dev849565.app936843.ItemViewHolder r8, int r9) {
        /*
            r7 = this;
            if (r8 == 0) goto L_0x0114
            int r0 = r7.numBoundViewHolders
            int r0 = r0 + 1
            r7.numBoundViewHolders = r0
            com.newandromo.dev849565.app936843.PaletteArrayManager r0 = r7.paletteArrayManager
            if (r0 == 0) goto L_0x0017
            com.newandromo.dev849565.app936843.PaletteArrayManager r0 = r7.paletteArrayManager
            int r1 = r7.numBoundViewHolders
            int r2 = r7.getItemCount()
            r0.ensureCapacity(r1, r2)
        L_0x0017:
            com.newandromo.dev849565.app936843.AutoColorArrayManager r0 = r7.autoColorArrayManager
            if (r0 == 0) goto L_0x0026
            com.newandromo.dev849565.app936843.AutoColorArrayManager r0 = r7.autoColorArrayManager
            int r1 = r7.numBoundViewHolders
            int r2 = r7.getItemCount()
            r0.ensureCapacity(r1, r2)
        L_0x0026:
            android.databinding.ViewDataBinding r0 = r8.binding
            int r1 = r8.getAdapterPosition()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r2 = 14
            r0.setVariable(r2, r1)
            android.view.View r1 = r0.getRoot()
            android.content.Context r1 = r1.getContext()
            int r3 = r8.getItemViewType()
            boolean r4 = com.newandromo.dev849565.app936843.ThemeUtils.isDefaultLayoutBindingWorkaroundNeeded()
            r5 = 0
            if (r4 == 0) goto L_0x0056
            android.view.LayoutInflater r4 = android.view.LayoutInflater.from(r1)
            r6 = 0
            android.databinding.ViewDataBinding r4 = android.databinding.DataBindingUtil.inflate(r4, r3, r5, r6)
            r6 = 35
            r0.setVariable(r6, r4)
        L_0x0056:
            com.newandromo.dev849565.app936843.PaletteArrayManager r0 = r7.paletteArrayManager
            if (r0 == 0) goto L_0x0063
            com.newandromo.dev849565.app936843.PaletteArrayManager r0 = r7.paletteArrayManager
            java.lang.Object r0 = r0.get(r9)
            com.newandromo.dev849565.app936843.PaletteArray r0 = (com.newandromo.dev849565.app936843.PaletteArray) r0
            goto L_0x0064
        L_0x0063:
            r0 = r5
        L_0x0064:
            com.newandromo.dev849565.app936843.AutoColorArrayManager r4 = r7.autoColorArrayManager
            if (r4 == 0) goto L_0x0071
            com.newandromo.dev849565.app936843.AutoColorArrayManager r4 = r7.autoColorArrayManager
            java.lang.Object r4 = r4.get(r9)
            r5 = r4
            com.newandromo.dev849565.app936843.AutoColorArray r5 = (com.newandromo.dev849565.app936843.AutoColorArray) r5
        L_0x0071:
            com.newandromo.dev849565.app936843.Item r1 = r7.getItemToBind(r1, r9, r3)
            if (r1 == 0) goto L_0x00fd
            boolean r3 = r7.isImageFallbackColorToolbarColor
            if (r3 == 0) goto L_0x0099
            android.databinding.ObservableInt r3 = r7.toolbarColor
            int r3 = r3.get()
            if (r3 == 0) goto L_0x008f
            android.databinding.ObservableInt r3 = r7.imageFallbackColor
            android.databinding.ObservableInt r4 = r7.toolbarColor
            int r4 = r4.get()
            r3.set(r4)
            goto L_0x00a2
        L_0x008f:
            android.databinding.ObservableInt r3 = r7.imageFallbackColor
            int r4 = r1.getPrimaryColor()
            r3.set(r4)
            goto L_0x00a2
        L_0x0099:
            android.databinding.ObservableInt r3 = r7.imageFallbackColor
            int r4 = r1.getPrimaryColor()
            r3.set(r4)
        L_0x00a2:
            android.databinding.ViewDataBinding r3 = r8.binding
            android.view.View r3 = r3.getRoot()
            android.content.Context r3 = r3.getContext()
            r1.setContext(r3)
            r1.autoColors = r5
            r1.palettes = r0
            java.util.Set<com.newandromo.dev849565.app936843.PaletteViewTarget> r0 = r8.paletteTargets
            r1.paletteTargets = r0
            com.newandromo.dev849565.app936843.PositionBinder r0 = r7.positionBinder
            if (r0 == 0) goto L_0x00c7
            android.databinding.ViewDataBinding r0 = r8.binding
            com.newandromo.dev849565.app936843.PositionBinder r3 = r7.positionBinder
            int r9 = r3.getBindingVariableId(r9)
            r0.setVariable(r9, r1)
            goto L_0x00d6
        L_0x00c7:
            com.newandromo.dev849565.app936843.ItemBinder<T> r9 = r7.itemBinder
            if (r9 == 0) goto L_0x00f5
            android.databinding.ViewDataBinding r9 = r8.binding
            com.newandromo.dev849565.app936843.ItemBinder<T> r0 = r7.itemBinder
            int r0 = r0.getBindingVariableId(r1)
            r9.setVariable(r0, r1)
        L_0x00d6:
            android.databinding.ViewDataBinding r9 = r8.binding
            int r0 = r8.getAdapterPosition()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r9.setVariable(r2, r0)
            android.databinding.ViewDataBinding r9 = r8.binding
            android.view.View r9 = r9.getRoot()
            r0 = 2131296259(0x7f090003, float:1.821043E38)
            r9.setTag(r0, r1)
            android.databinding.ViewDataBinding r8 = r8.binding
            r8.executePendingBindings()
            return
        L_0x00f5:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "itemBinder and positionBinder are both null"
            r8.<init>(r9)
            throw r8
        L_0x00fd:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "onBindViewHolder item is null, position: "
            r0.append(r1)
            r0.append(r9)
            java.lang.String r9 = r0.toString()
            r8.<init>(r9)
            throw r8
        L_0x0114:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "ItemViewHolder for position "
            r0.append(r1)
            r0.append(r9)
            java.lang.String r9 = " is null"
            r0.append(r9)
            java.lang.String r9 = r0.toString()
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newandromo.dev849565.app936843.ItemAdapter.onBindViewHolder(com.newandromo.dev849565.app936843.ItemViewHolder, int):void");
    }

    static int getLayoutId(ItemViewHolder itemViewHolder) {
        return itemViewHolder.getItemViewType();
    }

    public boolean onFailedToRecycleView(ItemViewHolder itemViewHolder) {
        return super.onFailedToRecycleView(itemViewHolder);
    }

    public void onViewRecycled(ItemViewHolder itemViewHolder) {
        this.numBoundViewHolders--;
        Glide.clear((Target<?>) new ClearingTarget(itemViewHolder));
        for (PaletteViewTarget clear : itemViewHolder.paletteTargets) {
            Glide.clear((Target<?>) clear);
        }
        itemViewHolder.paletteTargets.clear();
        if (itemViewHolder.binding != null) {
            Context context = itemViewHolder.itemView.getContext();
            if (context instanceof ItemBindingInfo) {
                ImageView imageView = (ImageView) ((ItemBindingInfo) context).getItemImageViewFromBinding(itemViewHolder.binding);
                if (imageView != null) {
                    Glide.clear((View) imageView);
                }
            } else {
                ImageView imageView2 = (ImageView) itemViewHolder.itemView.findViewById(R.id.itemImage);
                if (imageView2 != null) {
                    Glide.clear((View) imageView2);
                }
            }
        }
        super.onViewRecycled(itemViewHolder);
    }

    public void recycle() {
        unregisterAdapterDataObservers();
    }
}
