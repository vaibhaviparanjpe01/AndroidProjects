package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.databinding.ViewDataBinding;
import android.view.ContextThemeWrapper;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.signature.ApplicationVersionSignature;
import com.bumptech.glide.signature.EmptySignature;
import com.newandromo.dev849565.app936843.DashboardItem;

public class DashboardItemAdapter extends ItemAdapter<DashboardItem> {
    private static final String TAG = "DashboardItemAdapter";
    private int activityClassesArrayResId;
    private String[] activityDescriptions;
    private TypedArray activityIcons;
    private String[] activitySubtitles;
    private String[] activityTitles;
    private int fakeItemCount;
    private String fakeItemDescription;
    private int fakeItemImagePositionAdjustment;
    private String fakeItemImageUrl;
    private String fakeItemSubtitle;
    private String fakeItemTitle;
    private ItemClickListener itemClickListener;
    private int squareImageDrawableAttributeId = R.attr.activity_square_image_drawable;
    private int squareImageUrlAttributeId = R.attr.activity_square_image_url;
    private int tallImageDrawableAttributeId = 0;
    private int tallImageUrlAttributeId = 0;
    private int wideImageDrawableAttributeId = R.attr.activity_wide_image_drawable;
    private int wideImageUrlAttributeId = R.attr.activity_wide_image_url;

    public long getItemId(int i) {
        return -1;
    }

    /* access modifiers changed from: protected */
    public void onCreateItemBinding(ViewDataBinding viewDataBinding) {
    }

    /* access modifiers changed from: package-private */
    public void setFakeItemCount(int i) {
        this.fakeItemCount = i;
    }

    /* access modifiers changed from: package-private */
    public void setFakeItemTitle(String str) {
        this.fakeItemTitle = str;
    }

    /* access modifiers changed from: package-private */
    public void setFakeItemSubtitle(String str) {
        this.fakeItemSubtitle = str;
    }

    /* access modifiers changed from: package-private */
    public void setFakeItemDescription(String str) {
        this.fakeItemDescription = str;
    }

    /* access modifiers changed from: package-private */
    public void setFakeItemImageUrl(String str) {
        this.fakeItemImageUrl = str;
    }

    /* access modifiers changed from: package-private */
    public void setFakeItemImagePositionAdjustment(int i) {
        this.fakeItemImagePositionAdjustment = i;
    }

    public DashboardItemAdapter(Context context, int i, int i2, int i3, int i4, int i5, int i6, int i7, ItemClickListener itemClickListener2, boolean z, String str) {
        super((PositionBinder) new SingleLayoutPositionBinder(i), i7, z, str);
        this.itemClickListener = itemClickListener2;
        Resources resources = context.getResources();
        if (resources != null) {
            this.activityTitles = resources.getStringArray(i2);
            this.activitySubtitles = resources.getStringArray(i3);
            this.activityDescriptions = resources.getStringArray(i4);
            this.activityClassesArrayResId = i6;
            try {
                this.activityIcons = resources.obtainTypedArray(i5);
            } catch (Resources.NotFoundException unused) {
                this.activityIcons = null;
            }
        }
        if (z) {
            ensureAutoColorArrayCapacity(this.activityTitles.length);
        }
    }

    public DashboardItem getItemToBind(Context context, int i, int i2) {
        if (i < this.activityTitles.length) {
            return getDashboardItem(context, i, i2, this.backgroundType, isDarkTheme(), this.autoColorsEnabled);
        } else if (this.fakeItemCount <= 0) {
            return null;
        } else {
            return ((DashboardItem.Builder) ((DashboardItem.Builder) ((DashboardItem.Builder) ((DashboardItem.Builder) ((DashboardItem.Builder) ((DashboardItem.Builder) ((DashboardItem.Builder) ((DashboardItem.Builder) ((DashboardItem.Builder) ((DashboardItem.Builder) ((DashboardItem.Builder) ((DashboardItem.Builder) new DashboardItem.Builder().autoColorsEnabled(this.autoColorsEnabled)).title(String.format(this.fakeItemTitle, new Object[]{Integer.valueOf(i)}))).subtitle(String.format(this.fakeItemSubtitle, new Object[]{Integer.valueOf(i)}))).description(this.fakeItemDescription)).wideImage(String.format(this.fakeItemImageUrl, new Object[]{Integer.valueOf(this.fakeItemImagePositionAdjustment + i)}), getSignatureForRemoteImage())).squareImage(String.format(this.fakeItemImageUrl, new Object[]{Integer.valueOf(i + this.fakeItemImagePositionAdjustment)}), getSignatureForRemoteImage())).layoutId(i2)).clickListener(this.itemClickListener)).backgroundType(this.backgroundType)).isDarkTheme(isDarkTheme())).context(context)).primaryColor(ThemeUtils.getColor(context, (int) R.attr.colorPrimary))).build();
        }
    }

    private DashboardItem getDashboardItem(Context context, int i, int i2, int i3, boolean z, boolean z2) {
        int i4;
        Key key;
        int i5;
        Key key2;
        Key key3;
        int i6;
        Context context2 = context;
        int i7 = i;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context2, ThemeUtils.getActivityThemeResId(context2, AndromoUtils.getActivityClassName(context2, this.activityClassesArrayResId, i7)));
        int color = ThemeUtils.getColor((Context) contextThemeWrapper, (int) R.attr.colorPrimary);
        String remoteImageUrl = getRemoteImageUrl(contextThemeWrapper, this.wideImageUrlAttributeId);
        int i8 = 0;
        if (remoteImageUrl != null) {
            key = getSignatureForRemoteImage();
            i4 = ThemeUtils.getResourceId(contextThemeWrapper, this.wideImageDrawableAttributeId, 0);
        } else {
            remoteImageUrl = getImageResourceUrl(contextThemeWrapper, this.wideImageDrawableAttributeId);
            key = getSignatureForImageResource(contextThemeWrapper, remoteImageUrl);
            i4 = 0;
        }
        String remoteImageUrl2 = getRemoteImageUrl(contextThemeWrapper, this.squareImageUrlAttributeId);
        if (remoteImageUrl2 != null) {
            key2 = getSignatureForRemoteImage();
            i5 = ThemeUtils.getResourceId(contextThemeWrapper, this.squareImageDrawableAttributeId, 0);
        } else {
            remoteImageUrl2 = getImageResourceUrl(contextThemeWrapper, this.squareImageDrawableAttributeId);
            key2 = getSignatureForImageResource(contextThemeWrapper, remoteImageUrl2);
            i5 = 0;
        }
        String remoteImageUrl3 = getRemoteImageUrl(contextThemeWrapper, this.tallImageUrlAttributeId);
        if (remoteImageUrl3 != null) {
            key3 = getSignatureForRemoteImage();
            i6 = ThemeUtils.getResourceId(contextThemeWrapper, this.tallImageDrawableAttributeId, 0);
        } else {
            remoteImageUrl3 = getImageResourceUrl(contextThemeWrapper, this.tallImageDrawableAttributeId);
            key3 = getSignatureForImageResource(contextThemeWrapper, remoteImageUrl3);
            i6 = 0;
        }
        if (this.activityIcons != null) {
            i8 = this.activityIcons.getResourceId(i7, 0);
        }
        DashboardItem.Builder builder = new DashboardItem.Builder();
        DashboardItem.Builder builder2 = builder;
        ((DashboardItem.Builder) ((DashboardItem.Builder) ((DashboardItem.Builder) ((DashboardItem.Builder) ((DashboardItem.Builder) ((DashboardItem.Builder) ((DashboardItem.Builder) ((DashboardItem.Builder) ((DashboardItem.Builder) ((DashboardItem.Builder) ((DashboardItem.Builder) ((DashboardItem.Builder) ((DashboardItem.Builder) builder.title(this.activityTitles[i7])).subtitle(this.activitySubtitles[i7])).description(this.activityDescriptions[i7])).wideImage(remoteImageUrl, key, i4)).tallImage(remoteImageUrl3, key3, i6)).squareImage(remoteImageUrl2, key2, i5)).icon(i8)).layoutId(i2)).primaryColor(color)).autoColorsEnabled(z2)).clickListener(this.itemClickListener)).backgroundType(i3)).isDarkTheme(z)).context(context2);
        return builder2.build();
    }

    public int getItemCount() {
        return this.activityTitles.length + this.fakeItemCount;
    }

    private static String getRemoteImageUrl(Context context, int i) {
        if (i == 0) {
            return null;
        }
        String resolveString = ThemeUtils.resolveString(context, i);
        if ("".equals(resolveString)) {
            return null;
        }
        return resolveString;
    }

    private static String getImageResourceUrl(Context context, int i) {
        int resourceId;
        if (i == 0 || (resourceId = ThemeUtils.getResourceId(context, i, 0)) == 0) {
            return null;
        }
        return ThemeUtils.resourceIdToUrlString(context, resourceId);
    }

    private static Key getSignatureForRemoteImage() {
        return EmptySignature.obtain();
    }

    private static Key getSignatureForImageResource(Context context) {
        return ApplicationVersionSignature.obtain(context);
    }

    private static Key getSignatureForRemoteImage(String str) {
        if (str != null) {
            return EmptySignature.obtain();
        }
        return null;
    }

    private static Key getSignatureForImageResource(Context context, String str) {
        if (str != null) {
            return ApplicationVersionSignature.obtain(context);
        }
        return null;
    }
}
