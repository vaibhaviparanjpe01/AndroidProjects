package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.newandromo.dev849565.app936843.AndromoActivity;
import com.newandromo.dev849565.app936843.databinding.GroupListIconDestTitle01Binding;

public class Dashboard_000 extends DashboardActivity implements MaterialDashboard, ItemClickListener {
    private static final String TAG = "MaterialDashboard (000)";
    private static final boolean bMainDashboard = "000".equals("000");
    private static GroupListIconDestTitle01Utils itemBindingUtils;
    private static AndromoActivity.ParentCheck mParentCheck = new AndromoActivity.ParentCheck();
    private boolean bEnableShare = true;
    private boolean bShowAdsOnDashboard = true;

    /* access modifiers changed from: protected */
    public String getActivityTypeForAnalytics() {
        return "Dashboard";
    }

    /* access modifiers changed from: protected */
    public int getBackgroundImageDrawableId() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public boolean getBackgroundImageEnabled() {
        return false;
    }

    /* access modifiers changed from: protected */
    public float getDecorationSize() {
        return 4.0f;
    }

    /* access modifiers changed from: protected */
    public int getDecorationType() {
        return 1;
    }

    /* access modifiers changed from: protected */
    public boolean getHandleCustomWindowColor() {
        return false;
    }

    /* access modifiers changed from: protected */
    public int getLayoutColumns() {
        return 4;
    }

    /* access modifiers changed from: protected */
    public boolean getLayoutMatchNaturalSpanSize() {
        return true;
    }

    /* access modifiers changed from: protected */
    public float getLayoutMaxSpanSize() {
        return 0.0f;
    }

    /* access modifiers changed from: protected */
    public int getLayoutMaxSpansLandscape() {
        return 5;
    }

    /* access modifiers changed from: protected */
    public int getLayoutMaxSpansPortrait() {
        return 4;
    }

    /* access modifiers changed from: protected */
    public float getLayoutMinSpanSize() {
        return 180.0f;
    }

    /* access modifiers changed from: protected */
    public int getLayoutMinSpans() {
        return 4;
    }

    /* access modifiers changed from: protected */
    public int getLayoutStyle() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public boolean getShowAdsOnDashboard() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean isDetailActivity() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean isHiddenActivity() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean isShareActionEnabled() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean isToolbarEnabled() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean isRootDashboard() {
        return bMainDashboard;
    }

    /* access modifiers changed from: protected */
    public String getActivityTitleForAnalytics() {
        return getString(R.string.Dashboard_000_activity_title);
    }

    public String[] getClassNamesArray(Context context) {
        return context.getResources().getStringArray(R.array.activity_000_classes);
    }

    public String[] getParentClassNamesArray() {
        return getResources().getStringArray(R.array.activity_000_classes);
    }

    /* access modifiers changed from: protected */
    public boolean isParentReachable() {
        return !isRootDashboard() && mParentCheck.isParentReachable(this, "material");
    }

    /* access modifiers changed from: protected */
    public void setContentView() {
        setContentView((int) R.layout.material_dashboard_vertical_main);
    }

    /* access modifiers changed from: protected */
    public DashboardItemAdapter createAdapter() {
        return new DashboardItemAdapter(this, R.layout.group_list_icon_dest_title_01, R.array.activity_000_titles, R.array.activity_000_subtitles, R.array.activity_000_descriptions, R.array.activity_000_icons_48dp, R.array.activity_000_classes, 0, this, false, "16:9");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setToolbarTitle((int) R.string.Dashboard_000_activity_title);
    }

    public ItemBindingUtils getItemBindingUtils() {
        if (itemBindingUtils == null) {
            itemBindingUtils = new GroupListIconDestTitle01Utils();
        }
        return itemBindingUtils;
    }

    public Class<GroupListIconDestTitle01Binding> getItemBindingClass() {
        return GroupListIconDestTitle01Binding.class;
    }

    public GroupListIconDestTitle01Binding getBinding(ViewDataBinding viewDataBinding) throws ClassCastException {
        return GroupListIconDestTitle01Binding.class.cast(viewDataBinding);
    }

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, Dashboard_000.class);
        intent.addFlags(67108864);
        return intent;
    }

    public void onItemClick(View view, ItemViewHolder itemViewHolder) {
        onItemClick(view, itemViewHolder.getAdapterPosition(), itemViewHolder.getItemId(), itemViewHolder.binding);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0036, code lost:
        r14 = (com.newandromo.dev849565.app936843.databinding.GroupListIconDestTitle01Binding) r14;
        r4 = com.newandromo.dev849565.app936843.GroupListIconDestTitle01Utils.getRootViewFromBinding(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003c, code lost:
        if (r4 == null) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003e, code lost:
        android.support.v4.view.ViewCompat.setTransitionName(r4, "item_root_" + r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0052, code lost:
        r4 = com.newandromo.dev849565.app936843.GroupListIconDestTitle01Utils.getImageViewFromBinding(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0056, code lost:
        if (r4 == null) goto L_0x006c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0058, code lost:
        android.support.v4.view.ViewCompat.setTransitionName(r4, "item_image_" + r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x006c, code lost:
        r5 = findViewById(16908335);
        r6 = findViewById(16908336);
        r7 = new java.util.ArrayList(8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0081, code lost:
        if (r4 == null) goto L_0x00a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x008c, code lost:
        if ((r4.getDrawable() instanceof android.graphics.drawable.ColorDrawable) != false) goto L_0x009b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x008e, code lost:
        if (r2 == false) goto L_0x0091;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0091, code lost:
        r7.add(android.support.v4.util.Pair.create(r4, "activity_image"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x009b, code lost:
        r7.add(android.support.v4.util.Pair.create(r4, "app_bar_layout"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00a4, code lost:
        r14 = com.newandromo.dev849565.app936843.GroupListIconDestTitle01Utils.getTitleViewFromBinding(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00a8, code lost:
        if (r14 == null) goto L_0x00d3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00aa, code lost:
        android.support.v4.view.ViewCompat.setTransitionName(r14, "item_title_" + r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00be, code lost:
        if (r1 == false) goto L_0x00ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00c0, code lost:
        r7.add(android.support.v4.util.Pair.create(r14, "collapsing_toolbar"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00ca, code lost:
        r7.add(android.support.v4.util.Pair.create(r14, "toolbar"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00d3, code lost:
        r7.add(android.support.v4.util.Pair.create(r5, "android:status:background"));
        r7.add(android.support.v4.util.Pair.create(r6, "android:navigation:background"));
        r7.add(android.support.v4.util.Pair.create(r9.toolbar, "toolbar"));
        com.newandromo.dev849565.app936843.AndromoUtils.navigateWithInterstitial(r9, r0[r11], r10[r11], (android.support.v4.util.Pair[]) r7.toArray(new android.support.v4.util.Pair[r7.size()]));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x002f, code lost:
        r2 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onItemClick(android.view.View r10, int r11, long r12, android.databinding.ViewDataBinding r14) {
        /*
            r9 = this;
            android.content.res.Resources r10 = r9.getResources()
            r0 = 2130903045(0x7f030005, float:1.7412897E38)
            java.lang.String[] r10 = r10.getStringArray(r0)
            android.content.res.Resources r0 = r9.getResources()
            r1 = 2130903040(0x7f030000, float:1.7412887E38)
            java.lang.String[] r0 = r0.getStringArray(r1)
            int r1 = r0.length
            if (r11 >= r1) goto L_0x0111
            r1 = r0[r11]
            boolean r2 = r9.bSharedElementTransitionEnabled
            r3 = 0
            if (r2 == 0) goto L_0x0104
            android.view.ContextThemeWrapper r1 = com.newandromo.dev849565.app936843.ThemeUtils.getThemedContext((android.content.Context) r9, (java.lang.String) r1)
            r2 = 2130968637(0x7f04003d, float:1.7545933E38)
            int r1 = com.newandromo.dev849565.app936843.ThemeUtils.getResourceId(r1, r2, r3)
            r2 = 1
            switch(r1) {
                case 2131427360: goto L_0x0035;
                case 2131427361: goto L_0x0033;
                case 2131427421: goto L_0x0031;
                case 2131427423: goto L_0x002e;
                case 2131427424: goto L_0x002e;
                default: goto L_0x002e;
            }
        L_0x002e:
            r1 = 0
        L_0x002f:
            r2 = 0
            goto L_0x0036
        L_0x0031:
            r1 = 0
            goto L_0x0036
        L_0x0033:
            r1 = 1
            goto L_0x002f
        L_0x0035:
            r1 = 1
        L_0x0036:
            com.newandromo.dev849565.app936843.databinding.GroupListIconDestTitle01Binding r14 = (com.newandromo.dev849565.app936843.databinding.GroupListIconDestTitle01Binding) r14
            android.support.constraint.ConstraintLayout r4 = com.newandromo.dev849565.app936843.GroupListIconDestTitle01Utils.getRootViewFromBinding(r14)
            if (r4 == 0) goto L_0x0052
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "item_root_"
            r5.append(r6)
            r5.append(r12)
            java.lang.String r5 = r5.toString()
            android.support.v4.view.ViewCompat.setTransitionName(r4, r5)
        L_0x0052:
            com.newandromo.dev849565.app936843.TintableImageView r4 = com.newandromo.dev849565.app936843.GroupListIconDestTitle01Utils.getImageViewFromBinding(r14)
            if (r4 == 0) goto L_0x006c
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "item_image_"
            r5.append(r6)
            r5.append(r12)
            java.lang.String r5 = r5.toString()
            android.support.v4.view.ViewCompat.setTransitionName(r4, r5)
        L_0x006c:
            r5 = 16908335(0x102002f, float:2.387736E-38)
            android.view.View r5 = r9.findViewById(r5)
            r6 = 16908336(0x1020030, float:2.3877364E-38)
            android.view.View r6 = r9.findViewById(r6)
            java.util.ArrayList r7 = new java.util.ArrayList
            r8 = 8
            r7.<init>(r8)
            if (r4 == 0) goto L_0x00a4
            r8 = r4
            android.widget.ImageView r8 = (android.widget.ImageView) r8
            android.graphics.drawable.Drawable r8 = r8.getDrawable()
            boolean r8 = r8 instanceof android.graphics.drawable.ColorDrawable
            if (r8 != 0) goto L_0x009b
            if (r2 == 0) goto L_0x0091
            goto L_0x009b
        L_0x0091:
            java.lang.String r2 = "activity_image"
            android.support.v4.util.Pair r2 = android.support.v4.util.Pair.create(r4, r2)
            r7.add(r2)
            goto L_0x00a4
        L_0x009b:
            java.lang.String r2 = "app_bar_layout"
            android.support.v4.util.Pair r2 = android.support.v4.util.Pair.create(r4, r2)
            r7.add(r2)
        L_0x00a4:
            android.widget.TextView r14 = com.newandromo.dev849565.app936843.GroupListIconDestTitle01Utils.getTitleViewFromBinding(r14)
            if (r14 == 0) goto L_0x00d3
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "item_title_"
            r2.append(r4)
            r2.append(r12)
            java.lang.String r12 = r2.toString()
            android.support.v4.view.ViewCompat.setTransitionName(r14, r12)
            if (r1 == 0) goto L_0x00ca
            java.lang.String r12 = "collapsing_toolbar"
            android.support.v4.util.Pair r12 = android.support.v4.util.Pair.create(r14, r12)
            r7.add(r12)
            goto L_0x00d3
        L_0x00ca:
            java.lang.String r12 = "toolbar"
            android.support.v4.util.Pair r12 = android.support.v4.util.Pair.create(r14, r12)
            r7.add(r12)
        L_0x00d3:
            java.lang.String r12 = "android:status:background"
            android.support.v4.util.Pair r12 = android.support.v4.util.Pair.create(r5, r12)
            r7.add(r12)
            java.lang.String r12 = "android:navigation:background"
            android.support.v4.util.Pair r12 = android.support.v4.util.Pair.create(r6, r12)
            r7.add(r12)
            android.support.v7.widget.Toolbar r12 = r9.toolbar
            java.lang.String r13 = "toolbar"
            android.support.v4.util.Pair r12 = android.support.v4.util.Pair.create(r12, r13)
            r7.add(r12)
            r12 = r0[r11]
            r10 = r10[r11]
            int r11 = r7.size()
            android.support.v4.util.Pair[] r11 = new android.support.v4.util.Pair[r11]
            java.lang.Object[] r11 = r7.toArray(r11)
            android.support.v4.util.Pair[] r11 = (android.support.v4.util.Pair[]) r11
            com.newandromo.dev849565.app936843.AndromoUtils.navigateWithInterstitial(r9, r12, r10, r11)
            goto L_0x010d
        L_0x0104:
            r12 = r0[r11]
            r10 = r10[r11]
            android.support.v4.util.Pair[] r11 = new android.support.v4.util.Pair[r3]
            com.newandromo.dev849565.app936843.AndromoUtils.navigateWithInterstitial(r9, r12, r10, r11)
        L_0x010d:
            com.newandromo.dev849565.app936843.PaletteUtils.bMaterial = r3
            com.newandromo.dev849565.app936843.PaletteUtils.n = r3
        L_0x0111:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newandromo.dev849565.app936843.Dashboard_000.onItemClick(android.view.View, int, long, android.databinding.ViewDataBinding):void");
    }

    private void unbindDrawables(View view) {
        if (view != null) {
            if (view.getBackground() != null) {
                view.getBackground().setCallback((Drawable.Callback) null);
            }
            if (view instanceof ViewGroup) {
                int i = 0;
                while (true) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    if (i < viewGroup.getChildCount()) {
                        unbindDrawables(viewGroup.getChildAt(i));
                        i++;
                    } else {
                        viewGroup.removeAllViews();
                        return;
                    }
                }
            }
        }
    }
}
