package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.content.res.ColorStateList;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.ImageView;
import com.bumptech.glide.load.Key;
import com.newandromo.dev849565.app936843.Item;
import java.util.Date;

public class DashboardItem extends Item {
    private static final boolean DEBUG = true;
    public static final DashboardItem EMPTY_ITEM = new Builder().build();
    private static final String TAG = "DashboardItem";
    /* access modifiers changed from: private */
    public final String description;
    /* access modifiers changed from: private */
    public final int iconResId;
    /* access modifiers changed from: private */
    public final int squareImageFallback;
    /* access modifiers changed from: private */
    public final Key squareImageKey;
    /* access modifiers changed from: private */
    public final String squareImageUrl;
    /* access modifiers changed from: private */
    public final String subtitle;
    /* access modifiers changed from: private */
    public final int tallImageFallback;
    /* access modifiers changed from: private */
    public final Key tallImageKey;
    /* access modifiers changed from: private */
    public final String tallImageUrl;
    /* access modifiers changed from: private */
    public final String title;
    /* access modifiers changed from: private */
    public final int wideImageFallback;
    /* access modifiers changed from: private */
    public final Key wideImageKey;
    /* access modifiers changed from: private */
    public final String wideImageUrl;

    public Date getPubDate() {
        return null;
    }

    public static final DashboardItem emptyItem() {
        return EMPTY_ITEM;
    }

    public CharSequence getTitle() {
        return this.title;
    }

    public CharSequence getSubtitle() {
        return this.subtitle;
    }

    public CharSequence getDescription() {
        return this.description;
    }

    public int getIcon() {
        return this.iconResId;
    }

    @BindingAdapter({"android:src"})
    public static void loadImageSrc(ImageView imageView, int i) {
        if (imageView != null) {
            imageView.setImageResource(i);
            return;
        }
        throw new IllegalArgumentException("target imageView is null");
    }

    @BindingAdapter({"android:src", "android:tint"})
    public static void loadImageSrc(ImageView imageView, int i, ColorStateList colorStateList) {
        if (imageView == null) {
            throw new IllegalArgumentException("target imageView is null");
        } else if (i != 0) {
            Drawable drawable = ContextCompat.getDrawable(imageView.getContext(), i);
            if (drawable != null) {
                Drawable wrap = DrawableCompat.wrap(drawable);
                DrawableCompat.setTintList(wrap, colorStateList);
                imageView.setImageDrawable(wrap);
            }
        } else {
            imageView.setImageDrawable((Drawable) null);
        }
    }

    @Bindable
    public int getAutoBackgroundColor() {
        AutoColor autoColor;
        if (!this.isAutoEnabled || this.autoColors == null || (autoColor = this.autoColors.get(0)) == null) {
            return 0;
        }
        return autoColor.getBackgroundColor();
    }

    @Bindable
    public int getAutoPrimaryTextColor() {
        AutoColor autoColor;
        if (!this.isAutoEnabled || this.autoColors == null || (autoColor = this.autoColors.get(0)) == null) {
            return 0;
        }
        return autoColor.getPrimaryTextColor();
    }

    @Bindable
    public int getAutoSecondaryTextColor() {
        AutoColor autoColor;
        if (!this.isAutoEnabled || this.autoColors == null || (autoColor = this.autoColors.get(0)) == null) {
            return 0;
        }
        return autoColor.getSecondaryTextColor();
    }

    @Bindable
    public int getAutoBodyTextColor() {
        AutoColor autoColor;
        if (!this.isAutoEnabled || this.autoColors == null || (autoColor = this.autoColors.get(0)) == null) {
            return 0;
        }
        return autoColor.getBodyTextColor();
    }

    @Bindable
    public int getAutoTitleTextColor() {
        AutoColor autoColor;
        if (!this.isAutoEnabled || this.autoColors == null || (autoColor = this.autoColors.get(0)) == null) {
            return 0;
        }
        return autoColor.getTitleTextColor();
    }

    private <T extends DashboardItem, B extends AbstractBuilder<T, B>> DashboardItem(AbstractBuilder<T, B> abstractBuilder) {
        super(abstractBuilder);
        this.title = abstractBuilder.title;
        this.subtitle = abstractBuilder.subtitle;
        this.description = abstractBuilder.description;
        this.wideImageUrl = abstractBuilder.wideImageUrl;
        this.wideImageKey = abstractBuilder.wideImageKey;
        this.wideImageFallback = abstractBuilder.wideImageFallback;
        this.tallImageUrl = abstractBuilder.tallImageUrl;
        this.tallImageKey = abstractBuilder.tallImageKey;
        this.tallImageFallback = abstractBuilder.tallImageFallback;
        this.squareImageUrl = abstractBuilder.squareImageUrl;
        this.squareImageKey = abstractBuilder.squareImageKey;
        this.squareImageFallback = abstractBuilder.squareImageFallback;
        this.iconResId = abstractBuilder.iconResId;
    }

    public Item.ImageInfo.Builder wideImage(Context context, int i) {
        return new Item.ImageInfo.Builder().item(this).url(this.wideImageUrl).key(this.wideImageKey).fallbackResource(this.wideImageFallback).fallbackAsResult().index(i);
    }

    public Item.ImageInfo.Builder squareImage(Context context, int i) {
        return new Item.ImageInfo.Builder().item(this).url(this.squareImageUrl).key(this.squareImageKey).fallbackResource(this.squareImageFallback).fallbackAsResult().index(i);
    }

    public String toString() {
        return getClass().getName() + "[ title: '" + this.title + "', subtitle: '" + this.subtitle + "', description: '" + this.description + ", extending: " + super.toString() + "]";
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public static class Builder extends AbstractBuilder<DashboardItem, Builder> {
        private static String TAG = "DashboardItem.Builder";

        /* access modifiers changed from: protected */
        public Builder self() {
            return this;
        }

        public Builder() {
        }

        public Builder(DashboardItem dashboardItem) {
            super(dashboardItem);
        }

        public DashboardItem build() {
            return new DashboardItem(this);
        }
    }

    public static abstract class AbstractBuilder<T extends DashboardItem, B extends AbstractBuilder<T, B>> extends Item.AbstractBuilder<T, B> {
        private static String TAG = "DashboardItem.AbstractBuilder";
        /* access modifiers changed from: private */
        public String description = "";
        /* access modifiers changed from: private */
        public int iconResId;
        /* access modifiers changed from: private */
        public int squareImageFallback;
        /* access modifiers changed from: private */
        public Key squareImageKey;
        /* access modifiers changed from: private */
        public String squareImageUrl;
        /* access modifiers changed from: private */
        public String subtitle = "";
        /* access modifiers changed from: private */
        public int tallImageFallback;
        /* access modifiers changed from: private */
        public Key tallImageKey;
        /* access modifiers changed from: private */
        public String tallImageUrl;
        /* access modifiers changed from: private */
        public String title = "";
        /* access modifiers changed from: private */
        public int wideImageFallback;
        /* access modifiers changed from: private */
        public Key wideImageKey;
        /* access modifiers changed from: private */
        public String wideImageUrl;

        public AbstractBuilder() {
        }

        public AbstractBuilder(DashboardItem dashboardItem) {
            super(dashboardItem);
            this.title = dashboardItem.title;
            this.subtitle = dashboardItem.subtitle;
            this.description = dashboardItem.description;
            this.wideImageUrl = dashboardItem.wideImageUrl;
            this.wideImageKey = dashboardItem.wideImageKey;
            this.wideImageFallback = dashboardItem.wideImageFallback;
            this.tallImageUrl = dashboardItem.tallImageUrl;
            this.tallImageKey = dashboardItem.tallImageKey;
            this.tallImageFallback = dashboardItem.tallImageFallback;
            this.squareImageUrl = dashboardItem.squareImageUrl;
            this.squareImageKey = dashboardItem.squareImageKey;
            this.squareImageFallback = dashboardItem.squareImageFallback;
            this.iconResId = dashboardItem.iconResId;
        }

        public B title(String str) {
            this.title = str;
            return (AbstractBuilder) self();
        }

        public B subtitle(String str) {
            this.subtitle = str;
            return (AbstractBuilder) self();
        }

        public B description(String str) {
            this.description = str;
            return (AbstractBuilder) self();
        }

        public B wideImage(String str, Key key) {
            return wideImage(str, key, 0);
        }

        public B wideImage(String str, Key key, int i) {
            this.wideImageUrl = str;
            this.wideImageKey = key;
            this.wideImageFallback = i;
            return (AbstractBuilder) self();
        }

        public B tallImage(String str, Key key) {
            return tallImage(str, key, 0);
        }

        public B tallImage(String str, Key key, int i) {
            this.tallImageUrl = str;
            this.tallImageKey = key;
            this.tallImageFallback = i;
            return (AbstractBuilder) self();
        }

        public B squareImage(String str, Key key) {
            return squareImage(str, key, 0);
        }

        public B squareImage(String str, Key key, int i) {
            this.squareImageUrl = str;
            this.squareImageKey = key;
            this.squareImageFallback = i;
            return (AbstractBuilder) self();
        }

        public B icon(int i) {
            this.iconResId = i;
            return (AbstractBuilder) self();
        }
    }
}
