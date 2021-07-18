package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.content.res.ColorStateList;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.annotation.ColorInt;
import android.support.v4.view.ViewCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.model.stream.StreamStringLoader;
import com.bumptech.glide.load.resource.bitmap.Downsampler;
import com.bumptech.glide.load.resource.bitmap.StreamBitmapDecoder;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.ApplicationVersionSignature;
import com.bumptech.glide.signature.EmptySignature;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.Set;

public abstract class Item extends BaseObservable implements PaletteListener {
    private static final boolean DEBUG = true;
    private static final float DEFAULT_PLACEHOLDER_ALPHA = 0.25f;
    private static final String TAG = "Item";
    private static int almostAlpha = 229;
    /* access modifiers changed from: private */
    public final int autoColorCapacity;
    public AutoColorHolder autoColors;
    /* access modifiers changed from: private */
    public final int backgroundType;
    /* access modifiers changed from: private */
    public ItemClickListener clickListener;
    protected final boolean isAutoEnabled;
    /* access modifiers changed from: private */
    public final boolean isDarkTheme;
    public final int layoutId;
    /* access modifiers changed from: private */
    public ItemLongClickListener longClickListener;
    /* access modifiers changed from: private */
    public final int paletteCapacity;
    private GenericRequestBuilder<String, InputStream, Palette, Palette> paletteRequest;
    private StatelessPaletteTarget paletteTarget;
    public Set<PaletteViewTarget> paletteTargets;
    public PaletteHolder palettes;
    /* access modifiers changed from: private */
    public final int primaryColor;
    private WeakReference<Context> weakContext;

    /* access modifiers changed from: private */
    public void onImageReady(GlideDrawable glideDrawable) {
    }

    @Bindable
    public CharSequence getDescription() {
        return null;
    }

    @Bindable
    public int getIcon() {
        return 0;
    }

    @Bindable
    public Date getPubDate() {
        return null;
    }

    @Bindable
    public CharSequence getSubtitle() {
        return null;
    }

    @Bindable
    public CharSequence getTitle() {
        return null;
    }

    public void onPaletteReady(Palette palette) {
    }

    public ImageInfo.Builder squareImage(Context context, int i) {
        return null;
    }

    public int textColor(@ColorInt int i, @ColorInt int i2) {
        return i != 0 ? i : i2;
    }

    public ColorStateList textColor(ColorStateList colorStateList, ColorStateList colorStateList2) {
        return colorStateList != null ? colorStateList : colorStateList2;
    }

    public ImageInfo.Builder thumbnail(Context context, int i) {
        return null;
    }

    public ImageInfo.Builder wideImage(Context context, int i) {
        return null;
    }

    public void setContext(Context context) {
        this.weakContext = new WeakReference<>(context);
    }

    public Context getContext() {
        if (this.weakContext != null) {
            return (Context) this.weakContext.get();
        }
        return null;
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public void setLongClickListener(ItemLongClickListener itemLongClickListener) {
        this.longClickListener = itemLongClickListener;
    }

    public void onClick(View view, ItemViewHolder itemViewHolder) {
        if (this.clickListener != null) {
            this.clickListener.onItemClick(view, itemViewHolder);
        }
    }

    public boolean onLongClick(View view, ItemViewHolder itemViewHolder) {
        if (this.longClickListener != null) {
            return this.longClickListener.onItemLongClick(view, itemViewHolder);
        }
        return false;
    }

    protected <T extends Item, B extends AbstractBuilder<T, B>> Item(AbstractBuilder<T, B> abstractBuilder) {
        this.layoutId = abstractBuilder.layoutId;
        this.primaryColor = abstractBuilder.primaryColor;
        this.isAutoEnabled = abstractBuilder.isAutoEnabled;
        this.isDarkTheme = abstractBuilder.isDarkTheme;
        this.backgroundType = abstractBuilder.backgroundType;
        this.paletteCapacity = abstractBuilder.paletteCapacity;
        this.autoColorCapacity = abstractBuilder.autoColorCapacity;
        this.clickListener = abstractBuilder.clickListener;
        this.longClickListener = abstractBuilder.longClickListener;
        if (abstractBuilder.context != null) {
            setContext(abstractBuilder.context);
        }
    }

    @Bindable
    public boolean getHasSubtitle() {
        CharSequence subtitle = getSubtitle();
        if (subtitle == null || "".equals(subtitle.toString())) {
            return false;
        }
        return DEBUG;
    }

    @Bindable
    public boolean getHasDescription() {
        CharSequence description = getDescription();
        if (description == null || "".equals(description.toString())) {
            return false;
        }
        return DEBUG;
    }

    @Bindable
    public boolean getHasPubDate() {
        if (getPubDate() != null) {
            return DEBUG;
        }
        return false;
    }

    @Bindable
    public String getPrettyPubDate() {
        Date pubDate = getPubDate();
        if (pubDate != null) {
            return DateHelper.getPrettyTime(pubDate, new Date());
        }
        return null;
    }

    @Bindable
    public String getTimeAgo() {
        Date pubDate = getPubDate();
        if (pubDate != null) {
            return DateHelper.getTimeAgo(pubDate, new Date());
        }
        return null;
    }

    @Bindable
    public String getCompactTimeAgo() {
        Date pubDate = getPubDate();
        if (pubDate != null) {
            return DateHelper.getCompactTimeAgo(pubDate, new Date());
        }
        return null;
    }

    @Bindable
    public int getPrimaryColor() {
        return this.primaryColor;
    }

    @Bindable
    public int getBackgroundIndex() {
        return BackgroundType.isAutoColorType(this.backgroundType) ? 0 : -1;
    }

    @Bindable
    public int getBackgroundType() {
        return this.backgroundType;
    }

    @Bindable
    public AutoColor getBackgroundColor() {
        if (this.backgroundType == 2) {
            return auto(getPalette(0), 0);
        }
        float f = -0.047f;
        if (this.backgroundType == 3) {
            AutoColor auto = auto(getPalette(0), 0);
            if (auto != null) {
                return auto;
            }
            int color = ThemeUtils.getColor(getContext(), 16842801);
            if (color == 0) {
                color = this.isDarkTheme ? -13619152 : -328966;
            }
            if (this.isDarkTheme) {
                f = 0.047f;
            }
            return new AutoColor(ColorUtils.offsetValue(color, f));
        } else if (this.backgroundType == 4) {
            AutoColor auto2 = auto(getPalette(0), 0);
            if (auto2 != null) {
                return new AutoColor(ColorUtils.setAlpha(auto2.getBackgroundColor(), 204));
            }
            int color2 = ThemeUtils.getColor(getContext(), 16842801);
            if (color2 == 0) {
                color2 = this.isDarkTheme ? -13619152 : -328966;
            } else if (ColorUtils.getPerceivedBrightnessSquared(color2) > 57600) {
                color2 = ColorUtils.offsetValue(color2, -0.047f);
            }
            return new AutoColor(ColorUtils.setAlpha(color2, 204));
        } else if (this.backgroundType == 1) {
            return new AutoColor(this.isDarkTheme ? 1711276032 : -1711276033);
        } else {
            int i = this.backgroundType;
            return null;
        }
    }

    @Bindable
    public ImageInfo.Builder getSquareImage() {
        return squareImage(-1);
    }

    @Bindable
    public ImageInfo.Builder getWideImage() {
        return wideImage(-1);
    }

    @Bindable
    public ImageInfo.Builder getThumbnail() {
        return thumbnail(-1);
    }

    public ImageInfo.Builder wideImage(Context context) {
        return wideImage(context, -1);
    }

    public ImageInfo.Builder squareImage(Context context) {
        return squareImage(context, -1);
    }

    public ImageInfo.Builder thumbnail(Context context) {
        return thumbnail(context, -1);
    }

    public ImageInfo.Builder wideImage(int i) {
        return wideImage(getContext(), i);
    }

    public ImageInfo.Builder squareImage(int i) {
        return squareImage(getContext(), i);
    }

    public ImageInfo.Builder thumbnail(int i) {
        return thumbnail(getContext(), i);
    }

    public ImageInfo.Builder image(String str) {
        return image(getContext(), str, -1);
    }

    public ImageInfo.Builder image(int i) {
        return image(getContext(), i, -1);
    }

    public ImageInfo.Builder image(String str, int i) {
        return image(getContext(), str, i);
    }

    public ImageInfo.Builder image(int i, int i2) {
        return image(getContext(), i, i2);
    }

    public ImageInfo.Builder image(Context context, String str) {
        return image(context, str, -1);
    }

    public ImageInfo.Builder image(Context context, int i) {
        return image(context, i, -1);
    }

    public ImageInfo.Builder image(Context context, String str, int i) {
        return new ImageInfo.Builder().item(this).url(str).key(getSignatureForImageLoadFromUrl()).index(i);
    }

    public ImageInfo.Builder image(Context context, int i, int i2) {
        return new ImageInfo.Builder().item(this).url(ThemeUtils.resourceIdToUrlString(context, i)).key(getSignatureForImageLoadFromResource(context)).index(i2);
    }

    public Drawable background(@ColorInt int i, View view) {
        return background(i, view, (ViewDataBinding) null);
    }

    public Drawable background(@ColorInt int i, View view, ViewDataBinding viewDataBinding) {
        Drawable drawable;
        if (i != 0) {
            drawable = new ColorDrawable(i);
        } else if (viewDataBinding != null) {
            drawable = ThemeUtils.getEquivalentBackgroundFromBinding(view, viewDataBinding);
        } else {
            drawable = view != null ? ThemeUtils.getDefaultBackgroundByInflation(view, this.layoutId) : null;
        }
        boolean z = drawable instanceof ColorDrawable;
        return drawable;
    }

    public Drawable background(AutoColor autoColor, View view) {
        return background(autoColor, view, (ViewDataBinding) null);
    }

    public Drawable background(AutoColor autoColor, View view, ViewDataBinding viewDataBinding) {
        Drawable drawable;
        if (autoColor != null) {
            drawable = new ColorDrawable(autoColor.getBackgroundColor());
        } else if (viewDataBinding != null) {
            drawable = ThemeUtils.getEquivalentBackgroundFromBinding(view, viewDataBinding);
        } else {
            drawable = view != null ? ThemeUtils.getDefaultBackgroundByInflation(view, this.layoutId) : null;
        }
        boolean z = drawable instanceof ColorDrawable;
        return drawable;
    }

    public AutoColor auto(Palette palette, int i) {
        return autoColor(palette, i, (String) null);
    }

    public AutoColor auto(Palette palette, int i, String str) {
        return autoColor(palette, i, str);
    }

    public AutoColor auto(Palette palette, int i, int... iArr) {
        AutoColor autoColor = autoColor(palette, i, (String) null);
        for (int auto : iArr) {
            setAuto(auto, autoColor);
        }
        return autoColor;
    }

    public AutoColor autoColor(Palette palette, int i) {
        return autoColor(palette, i, (String) null);
    }

    public AutoColor autoColor(Palette palette, int i, String str) {
        AutoColor autoColor;
        if (!this.isAutoEnabled) {
            return null;
        }
        if (palette != null) {
            if (str == null || str.isEmpty()) {
                str = PaletteUtils.SWATCH_RULES_AUTOMATIC;
            }
            Palette.Swatch swatch = PaletteUtils.getSwatch(palette, str, PaletteUtils.getPaletteDarknessByPopulation(palette));
            if (swatch == null) {
                PaletteUtils.logSwatches(palette);
                return null;
            }
            AutoColor autoColor2 = new AutoColor(swatch.getRgb(), 0, 0, 0, 0);
            if (i >= 0) {
                setAuto(i, autoColor2);
            }
            return autoColor2;
        } else if (this.autoColors == null || (autoColor = this.autoColors.get(i)) == null) {
            return null;
        } else {
            return autoColor;
        }
    }

    private void setAuto(int i, AutoColor autoColor) {
        setAuto(i, autoColor, false);
    }

    /* access modifiers changed from: private */
    public void setAuto(int i, AutoColor autoColor, boolean z) {
        if ((this.isAutoEnabled || z) && i >= 0 && i <= 9) {
            if (this.autoColors == null) {
                this.autoColors = new AutoColorArray(this.autoColorCapacity);
            }
            AutoColor autoColor2 = this.autoColors.get(i);
            boolean z2 = DEBUG;
            if (autoColor2 != null) {
                z2 = true ^ autoColor2.equals(autoColor);
            }
            this.autoColors.set(i, autoColor);
            if (z2) {
                notifyAutoChanged(i);
            }
        }
    }

    private void notifyAutoChanged(int i) {
        switch (i) {
            case 0:
                notifyPropertyChanged(51);
                notifyPropertyChanged(1);
                notifyPropertyChanged(39);
                return;
            case 1:
                notifyPropertyChanged(54);
                return;
            case 2:
                notifyPropertyChanged(53);
                return;
            case 3:
                notifyPropertyChanged(47);
                return;
            case 4:
                notifyPropertyChanged(45);
                return;
            case 5:
                notifyPropertyChanged(50);
                return;
            case 6:
                notifyPropertyChanged(48);
                return;
            case 7:
                notifyPropertyChanged(43);
                return;
            case 8:
                notifyPropertyChanged(41);
                return;
            case 9:
                notifyPropertyChanged(44);
                return;
            default:
                return;
        }
    }

    private void notifyPaletteChanged(int i) {
        switch (i) {
            case 0:
                notifyPropertyChanged(17);
                notifyPropertyChanged(5);
                notifyPropertyChanged(39);
                return;
            case 1:
                notifyPropertyChanged(19);
                return;
            case 2:
                notifyPropertyChanged(18);
                return;
            case 3:
                notifyPropertyChanged(23);
                return;
            case 4:
                notifyPropertyChanged(21);
                return;
            case 5:
                notifyPropertyChanged(26);
                return;
            case 6:
                notifyPropertyChanged(24);
                return;
            case 7:
                notifyPropertyChanged(29);
                return;
            case 8:
                notifyPropertyChanged(28);
                return;
            case 9:
                notifyPropertyChanged(31);
                return;
            default:
                return;
        }
    }

    public ColorStateList cardBackgroundColor(@ColorInt int i, CardView cardView, ViewDataBinding viewDataBinding) {
        if (i != 0) {
            return ColorStateList.valueOf(i);
        }
        if (viewDataBinding != null) {
            return ThemeUtils.getEquivalentBackgroundFromBinding(cardView, viewDataBinding);
        }
        if (cardView != null) {
            return ThemeUtils.getDefaultBackgroundByInflation(cardView, this.layoutId);
        }
        return null;
    }

    public ColorStateList cardBackgroundColor(@ColorInt int i, CardView cardView) {
        return cardBackgroundColor(i, cardView, (ViewDataBinding) null);
    }

    public ColorStateList cardBackgroundColor(AutoColor autoColor, CardView cardView, ViewDataBinding viewDataBinding) {
        return cardBackgroundColor(AutoColor.background(autoColor), cardView, viewDataBinding);
    }

    public ColorStateList textColor(@ColorInt int i, ColorStateList colorStateList) {
        return i != 0 ? ColorStateList.valueOf(i) : colorStateList;
    }

    public ColorStateList textColor(ColorStateList colorStateList, @ColorInt int i) {
        return colorStateList != null ? colorStateList : ColorStateList.valueOf(i);
    }

    public ColorStateList textColor(@ColorInt int i, TextView textView) {
        return textColor(i, textView, (ViewDataBinding) null);
    }

    public ColorStateList textColor(ColorStateList colorStateList, TextView textView) {
        return textColor(colorStateList, textView, (ViewDataBinding) null);
    }

    public ColorStateList textColor(@ColorInt int i, TextView textView, ViewDataBinding viewDataBinding) {
        if (i != 0) {
            return ColorUtils.createDisabledStateList(i, ThemeUtils.getDisabledColor((View) textView, i));
        }
        if (viewDataBinding != null) {
            return ThemeUtils.getEquivalentTextColorsFromBinding(textView, viewDataBinding);
        }
        return ThemeUtils.getDefaultTextColorsByInflation(textView, this.layoutId);
    }

    public ColorStateList textColor(ColorStateList colorStateList, TextView textView, ViewDataBinding viewDataBinding) {
        if (colorStateList != null) {
            return colorStateList;
        }
        if (viewDataBinding != null) {
            return ThemeUtils.getEquivalentTextColorsFromBinding(textView, viewDataBinding);
        }
        return ThemeUtils.getDefaultTextColorsByInflation(textView, this.layoutId);
    }

    private static Context getContext(View view) {
        if (view != null) {
            return view.getContext();
        }
        return null;
    }

    public ColorStateList placeholderColor(int i, Context context) {
        return foregroundColor(ColorUtils.isDark(i), context, (float) DEFAULT_PLACEHOLDER_ALPHA);
    }

    public ColorStateList placeholderColor(Context context) {
        return foregroundColor(context, DEFAULT_PLACEHOLDER_ALPHA);
    }

    public ColorStateList placeholderColor(AutoColor autoColor, Context context) {
        return foregroundColor(autoColor, context, (float) DEFAULT_PLACEHOLDER_ALPHA);
    }

    public ColorStateList foregroundColor(Context context, float f) {
        int alpha = ColorUtils.setAlpha(ThemeUtils.getColor(context, 16842800), f);
        return ColorUtils.createDisabledStateList(alpha, ThemeUtils.getDisabledColor(context, alpha));
    }

    public ColorStateList foregroundColor(AutoColor autoColor, Context context, float f) {
        if (autoColor != null) {
            return foregroundColor(AutoColor.isDark(autoColor), context, f);
        }
        return foregroundColor(context, f);
    }

    public ColorStateList foregroundColor(int i, Context context, float f) {
        return foregroundColor(ColorUtils.isDark(i), context, f);
    }

    public ColorStateList foregroundColor(boolean z, Context context, float f) {
        int alpha = ColorUtils.setAlpha(z ? -1 : ViewCompat.MEASURED_STATE_MASK, f);
        return ColorUtils.createDisabledStateList(alpha, ThemeUtils.getDisabledColor(context, alpha));
    }

    public ColorStateList bodyTextColor(AutoColor autoColor, TextView textView, ViewDataBinding viewDataBinding) {
        return textColor(ThemeUtils.createDisabledStateList(getContext(textView), AutoColor.bodyText(autoColor), AutoColor.defaultDisabledAlpha(autoColor)), textView, viewDataBinding);
    }

    public ColorStateList titleTextColor(AutoColor autoColor, TextView textView, ViewDataBinding viewDataBinding) {
        return textColor(ThemeUtils.createDisabledStateList(getContext(textView), AutoColor.titleText(autoColor), AutoColor.defaultDisabledAlpha(autoColor)), textView, viewDataBinding);
    }

    public ColorStateList primaryTextColor(AutoColor autoColor, TextView textView, ViewDataBinding viewDataBinding) {
        return textColor(ThemeUtils.createDisabledStateList(getContext(textView), AutoColor.primaryText(autoColor), AutoColor.defaultDisabledAlpha(autoColor)), textView, viewDataBinding);
    }

    public ColorStateList primaryTextColor(AutoColor autoColor, TextView textView) {
        Context context = getContext(textView);
        if (autoColor != null) {
            return ThemeUtils.createDisabledStateList(context, AutoColor.primaryText(autoColor), AutoColor.defaultDisabledAlpha(autoColor));
        }
        return ThemeUtils.createDisabledStateList(context, ThemeUtils.getColor(context, 16842806));
    }

    public ColorStateList secondaryTextColor(AutoColor autoColor, TextView textView, ViewDataBinding viewDataBinding) {
        return textColor(ThemeUtils.createDisabledStateList(getContext(textView), AutoColor.secondaryText(autoColor), AutoColor.defaultDisabledAlpha(autoColor)), textView, viewDataBinding);
    }

    public ColorStateList secondaryTextColor(AutoColor autoColor, TextView textView) {
        Context context = getContext(textView);
        if (autoColor != null) {
            return ThemeUtils.createDisabledStateList(context, AutoColor.secondaryText(autoColor), AutoColor.defaultDisabledAlpha(autoColor));
        }
        return ThemeUtils.createDisabledStateList(context, ThemeUtils.getColor(context, 16842808));
    }

    public ColorStateList almostPrimaryTextColor(AutoColor autoColor, TextView textView, ViewDataBinding viewDataBinding) {
        return textColor(ThemeUtils.createDisabledStateList(getContext(textView), almostPrimary(AutoColor.primaryText(autoColor)), AutoColor.defaultDisabledAlpha(autoColor)), textView, viewDataBinding);
    }

    public ColorStateList almostPrimaryTextColor(AutoColor autoColor, TextView textView) {
        Context context = getContext(textView);
        if (autoColor != null) {
            return ThemeUtils.createDisabledStateList(context, almostPrimary(AutoColor.primaryText(autoColor)), AutoColor.defaultDisabledAlpha(autoColor));
        }
        return ThemeUtils.createDisabledStateList(context, almostPrimary(ThemeUtils.getColor(context, 16842806)));
    }

    private static int almostPrimary(int i) {
        return Color.alpha(i) == 255 ? ColorUtils.setAlpha(i, almostAlpha) : i;
    }

    private GenericRequestBuilder<String, InputStream, Palette, Palette> getPaletteRequestBuilder(Context context) {
        if (this.paletteRequest != null) {
            return this.paletteRequest.clone();
        }
        this.paletteRequest = Glide.with(context).using(new StreamStringLoader(context), InputStream.class).from(String.class).as(Palette.class).diskCacheStrategy(DiskCacheStrategy.ALL).encoder(new PaletteCacheEncoder(new PaletteEncoder())).sourceEncoder(new StreamEncoder()).cacheDecoder(new FileToStreamDecoder(new PaletteCacheDecoder(new PaletteDecoder(), new StreamBitmapDecoder(Downsampler.AT_MOST, Glide.get(context).getBitmapPool(), DecodeFormat.DEFAULT)))).override(256, 256).dontTransform().dontAnimate();
        return this.paletteRequest;
    }

    private Key getSignatureForImageLoadFromUrl() {
        return EmptySignature.obtain();
    }

    /* access modifiers changed from: protected */
    public Key getSignatureForImageLoadFromResource(Context context) {
        if (context != null) {
            return ApplicationVersionSignature.obtain(context);
        }
        return null;
    }

    private void getPaletteForImage(View view, ImageInfo imageInfo) {
        if (imageInfo != null) {
            String str = imageInfo.url;
            Key key = imageInfo.signature;
            if (str != null && key != null) {
                if (this.paletteTarget != null) {
                    Glide.clear((Target<?>) this.paletteTarget);
                }
                Request request = PaletteViewTarget.getRequest(view);
                if (request != null) {
                    request.clear();
                }
                PaletteViewTarget paletteViewTarget = new PaletteViewTarget(view, this, imageInfo.index);
                if (this.paletteTargets != null) {
                    this.paletteTargets.add(paletteViewTarget);
                }
                getPaletteRequestBuilder(view.getContext()).signature(key).load(str).into(paletteViewTarget);
            }
        }
    }

    private DrawableRequestBuilder<String> getImageRequestBuilder(Context context, ImageInfo imageInfo) {
        return Glide.with(context).from(String.class).diskCacheStrategy(ThemeUtils.isResourceUrl(imageInfo.url) ? DiskCacheStrategy.NONE : DiskCacheStrategy.ALL).crossFade(150).placeholder(imageInfo.placeholderResId).fallback(imageInfo.fallbackResId).fitCenter();
    }

    public void onViewRecycled(View view) {
        if (this.paletteTarget != null) {
            Glide.clear((Target<?>) this.paletteTarget);
            Glide.clear(view);
        }
    }

    /* access modifiers changed from: private */
    public void onImageLoadFailed(ImageView imageView, ImageInfo imageInfo) {
        handleImageFallback(imageView, imageInfo);
    }

    private AutoColor getAuto(int i) {
        if (this.autoColors != null) {
            return this.autoColors.get(i);
        }
        return null;
    }

    @Bindable
    public AutoColor getAuto() {
        return getAuto(0);
    }

    @Bindable
    public AutoColor getAuto0() {
        return getAuto(0);
    }

    @Bindable
    public AutoColor getAuto1() {
        return getAuto(1);
    }

    @Bindable
    public AutoColor getAuto2() {
        return getAuto(2);
    }

    @Bindable
    public AutoColor getAuto3() {
        return getAuto(3);
    }

    @Bindable
    public AutoColor getAuto4() {
        return getAuto(4);
    }

    @Bindable
    public AutoColor getAuto5() {
        return getAuto(5);
    }

    @Bindable
    public AutoColor getAuto6() {
        return getAuto(6);
    }

    @Bindable
    public AutoColor getAuto7() {
        return getAuto(7);
    }

    @Bindable
    public AutoColor getAuto8() {
        return getAuto(8);
    }

    @Bindable
    public AutoColor getAuto9() {
        return getAuto(9);
    }

    private void unsetPalette(int i) {
        if (i >= 0 && i < this.paletteCapacity && this.palettes != null) {
            this.palettes.set(i, (Palette) null);
        }
    }

    private void setPalette(int i, Palette palette) {
        if (this.isAutoEnabled && i >= 0 && i < this.paletteCapacity) {
            if (this.palettes == null) {
                this.palettes = new PaletteArray(this.paletteCapacity);
            }
            Palette palette2 = this.palettes.get(i);
            boolean z = DEBUG;
            if (palette2 != null) {
                z = true ^ PaletteUtils.haveSameSwatches(palette2, palette);
            }
            this.palettes.set(i, palette);
            if (z) {
                notifyPaletteChanged(i);
            }
        }
    }

    private boolean havePalette(int i) {
        if (this.palettes == null || this.palettes.get(i) == null) {
            return false;
        }
        return DEBUG;
    }

    private Palette getPalette(int i) {
        if (this.palettes != null) {
            return this.palettes.get(i);
        }
        if (!this.isAutoEnabled) {
            return null;
        }
        this.palettes = new PaletteArray(this.paletteCapacity);
        return null;
    }

    @Bindable
    public Palette getPalette() {
        return getPalette(0);
    }

    @Bindable
    public Palette getPalette0() {
        return getPalette(0);
    }

    @Bindable
    public Palette getPalette1() {
        return getPalette(1);
    }

    @Bindable
    public Palette getPalette2() {
        return getPalette(2);
    }

    @Bindable
    public Palette getPalette3() {
        return getPalette(3);
    }

    @Bindable
    public Palette getPalette4() {
        return getPalette(4);
    }

    @Bindable
    public Palette getPalette5() {
        return getPalette(5);
    }

    @Bindable
    public Palette getPalette6() {
        return getPalette(6);
    }

    @Bindable
    public Palette getPalette7() {
        return getPalette(7);
    }

    @Bindable
    public Palette getPalette8() {
        return getPalette(8);
    }

    @Bindable
    public Palette getPalette9() {
        return getPalette(9);
    }

    public static class ImageInfo {
        private static final int DEFAULT_FLAGS = 9225;
        public static final ImageInfo EMPTY_IMAGE_INFO = new Builder().build();
        public final int backgroundColor;
        public final int backgroundIndex;
        public final int errorBackgroundColor;
        public final int errorResId;
        public final ColorStateList errorTint;
        public final ImageInfo fallback;
        public final int fallbackBackgroundColor;
        public final int fallbackColor;
        public final int fallbackResId;
        public final ColorStateList fallbackTint;
        public final int flags;
        public final int index;
        public final Item item;
        public final int placeholderBackgroundColor;
        public final int placeholderResId;
        public final ColorStateList placeholderTint;
        public final Key signature;
        public final ColorStateList tint;
        public final String url;

        public static final class Flag {
            public static final int CLEAR_FILTER_ON_NULL = 135300;
            public static final int FALLBACK_AS_RESULT = 1048576;
            public static final int NO_TINT = 67650;
            public static final int OPAQUE = 270600;
            public static final int PALETTE_ENABLED = 33825;
            public static final int TINTABLE = 541200;
        }

        public static final class Mask {
            public static final int ALL = 2097151;
            public static final int ERROR = 1015808;
            public static final int FALLBACK = 31744;
            public static final int PLACEHOLDER = 992;
            public static final int RESULT = 31;
        }

        public static final ImageInfo emptyImageInfo() {
            return EMPTY_IMAGE_INFO;
        }

        public ImageInfo(Item item2, String str, Key key, int i, int i2) {
            this.item = item2;
            this.url = str;
            this.signature = key == null ? EmptySignature.obtain() : key;
            this.index = i2;
            this.placeholderResId = 0;
            this.errorResId = 0;
            this.fallbackResId = i;
            this.fallbackColor = 0;
            this.fallback = null;
            this.tint = null;
            this.placeholderTint = null;
            this.fallbackTint = null;
            this.errorTint = null;
            this.backgroundIndex = -1;
            this.backgroundColor = 0;
            this.placeholderBackgroundColor = 0;
            this.fallbackBackgroundColor = 0;
            this.errorBackgroundColor = 0;
            this.flags = DEFAULT_FLAGS;
        }

        private ImageInfo(Builder builder) {
            this.item = builder.item;
            this.url = builder.url;
            this.signature = builder.signature != null ? builder.signature : EmptySignature.obtain();
            this.index = builder.index;
            this.placeholderResId = builder.placeholderResId;
            this.errorResId = builder.errorResId;
            this.fallbackColor = builder.fallbackColor;
            this.fallbackResId = builder.fallbackResId;
            this.fallback = builder.fallback;
            this.tint = builder.tint;
            this.placeholderTint = builder.placeholderTint;
            this.fallbackTint = builder.fallbackTint;
            this.errorTint = builder.errorTint;
            this.backgroundIndex = builder.backgroundIndex;
            this.backgroundColor = builder.backgroundColor;
            this.placeholderBackgroundColor = builder.placeholderBackgroundColor;
            this.fallbackBackgroundColor = builder.fallbackBackgroundColor;
            this.errorBackgroundColor = builder.errorBackgroundColor;
            this.flags = builder.flags;
        }

        public static class Builder {
            /* access modifiers changed from: private */
            public int backgroundColor;
            /* access modifiers changed from: private */
            public int backgroundIndex;
            /* access modifiers changed from: private */
            public int errorBackgroundColor;
            /* access modifiers changed from: private */
            public int errorResId;
            /* access modifiers changed from: private */
            public ColorStateList errorTint;
            /* access modifiers changed from: private */
            public ImageInfo fallback;
            /* access modifiers changed from: private */
            public int fallbackBackgroundColor;
            /* access modifiers changed from: private */
            public int fallbackColor;
            /* access modifiers changed from: private */
            public int fallbackResId;
            /* access modifiers changed from: private */
            public ColorStateList fallbackTint;
            /* access modifiers changed from: private */
            public int flags;
            /* access modifiers changed from: private */
            public int index;
            /* access modifiers changed from: private */
            public Item item;
            /* access modifiers changed from: private */
            public int placeholderBackgroundColor;
            /* access modifiers changed from: private */
            public int placeholderResId;
            /* access modifiers changed from: private */
            public ColorStateList placeholderTint;
            /* access modifiers changed from: private */
            public Key signature;
            /* access modifiers changed from: private */
            public ColorStateList tint;
            /* access modifiers changed from: private */
            public String url;

            public Builder() {
                this.flags = ImageInfo.DEFAULT_FLAGS;
                this.index = -1;
                this.backgroundIndex = -1;
                this.flags = ImageInfo.DEFAULT_FLAGS;
            }

            public Builder(ImageInfo imageInfo) {
                this.flags = ImageInfo.DEFAULT_FLAGS;
                this.item = imageInfo.item;
                this.url = imageInfo.url;
                this.signature = imageInfo.signature != null ? imageInfo.signature : EmptySignature.obtain();
                this.index = imageInfo.index;
                this.placeholderResId = imageInfo.placeholderResId;
                this.errorResId = imageInfo.errorResId;
                this.fallbackColor = imageInfo.fallbackColor;
                this.fallbackResId = imageInfo.fallbackResId;
                this.fallback = imageInfo.fallback;
                this.tint = imageInfo.tint;
                this.placeholderTint = imageInfo.placeholderTint;
                this.fallbackTint = imageInfo.fallbackTint;
                this.errorTint = imageInfo.errorTint;
                this.backgroundIndex = imageInfo.backgroundIndex;
                this.backgroundColor = imageInfo.backgroundColor;
                this.placeholderBackgroundColor = imageInfo.placeholderBackgroundColor;
                this.fallbackBackgroundColor = imageInfo.fallbackBackgroundColor;
                this.errorBackgroundColor = imageInfo.errorBackgroundColor;
                this.flags = imageInfo.flags;
            }

            public Builder item(Item item2) {
                this.item = item2;
                return this;
            }

            public Builder url(String str) {
                this.url = str;
                return this;
            }

            public Builder signature(Key key) {
                this.signature = key;
                return this;
            }

            public Builder key(Key key) {
                this.signature = key;
                return this;
            }

            public Builder index(int i) {
                this.index = i;
                return this;
            }

            public Builder index(int i, int i2) {
                this.index = i;
                set(Flag.PALETTE_ENABLED, i2, i != -1 ? Item.DEBUG : false);
                return this;
            }

            public Builder tintable() {
                set(Flag.TINTABLE, 31);
                return this;
            }

            public Builder tintable(boolean z) {
                set(Flag.TINTABLE, 31, z);
                return this;
            }

            public Builder fallbackAsResult() {
                set(1048576, Mask.ALL);
                return this;
            }

            public Builder fallbackAsResult(boolean z) {
                set(1048576, Mask.ALL, z);
                return this;
            }

            public Builder placeholder(int i) {
                this.placeholderResId = i;
                return this;
            }

            public Builder placeholder(int i, ColorStateList colorStateList) {
                this.placeholderResId = i;
                this.placeholderTint = colorStateList;
                unset(Flag.NO_TINT, Mask.PLACEHOLDER);
                return this;
            }

            public Builder placeholder(int i, int i2) {
                this.placeholderResId = i;
                this.placeholderTint = ColorStateList.valueOf(i2);
                unset(Flag.NO_TINT, Mask.PLACEHOLDER);
                return this;
            }

            public Builder placeholderTintable() {
                set(Flag.TINTABLE, Mask.PLACEHOLDER);
                return this;
            }

            public Builder placeholderTintable(boolean z) {
                set(Flag.TINTABLE, Mask.PLACEHOLDER, z);
                return this;
            }

            public Builder error(int i) {
                this.errorResId = i;
                return this;
            }

            public Builder error(int i, ColorStateList colorStateList) {
                this.errorResId = i;
                this.errorTint = colorStateList;
                unset(Flag.NO_TINT, Mask.ERROR);
                return this;
            }

            public Builder error(int i, int i2) {
                this.errorResId = i;
                this.errorTint = ColorStateList.valueOf(i2);
                unset(Flag.NO_TINT, Mask.ERROR);
                return this;
            }

            public Builder errorTintable() {
                set(Flag.TINTABLE, Mask.ERROR);
                return this;
            }

            public Builder errorTintable(boolean z) {
                set(Flag.TINTABLE, Mask.ERROR, z);
                return this;
            }

            public Builder fallbackResource(int i) {
                this.fallbackResId = i;
                return this;
            }

            public Builder fallbackResource(int i, ColorStateList colorStateList) {
                this.fallbackResId = i;
                this.fallbackTint = colorStateList;
                unset(Flag.NO_TINT, Mask.FALLBACK);
                return this;
            }

            public Builder fallbackResource(int i, int i2) {
                this.fallbackResId = i;
                this.fallbackTint = ColorStateList.valueOf(i2);
                unset(Flag.NO_TINT, Mask.FALLBACK);
                return this;
            }

            public Builder fallback(int i) {
                this.fallbackResId = i;
                return this;
            }

            public Builder fallback(int i, ColorStateList colorStateList) {
                this.fallbackResId = i;
                this.fallbackTint = colorStateList;
                unset(Flag.NO_TINT, Mask.FALLBACK);
                return this;
            }

            public Builder fallback(int i, int i2) {
                this.fallbackResId = i;
                this.fallbackTint = ColorStateList.valueOf(i2);
                unset(Flag.NO_TINT, Mask.FALLBACK);
                return this;
            }

            public Builder fallbackTintable() {
                set(Flag.TINTABLE, Mask.FALLBACK);
                return this;
            }

            public Builder fallbackTintable(boolean z) {
                set(Flag.TINTABLE, Mask.FALLBACK, z);
                return this;
            }

            public Builder noFallback() {
                this.fallback = null;
                return this;
            }

            public Builder fallback(ImageInfo imageInfo) {
                this.fallback = imageInfo;
                return this;
            }

            public Builder fallback(Builder builder) {
                this.fallback = builder != null ? builder.build() : null;
                return this;
            }

            public Builder fallbackColor(int i) {
                this.fallbackColor = i;
                return this;
            }

            public Builder tint(ColorStateList colorStateList) {
                this.tint = colorStateList;
                unset(Flag.NO_TINT, 31);
                return this;
            }

            public Builder tint(int i) {
                this.tint = ColorStateList.valueOf(i);
                unset(Flag.NO_TINT, 31);
                return this;
            }

            public Builder placeholderTint(ColorStateList colorStateList) {
                this.placeholderTint = colorStateList;
                unset(Flag.NO_TINT, Mask.PLACEHOLDER);
                return this;
            }

            public Builder placeholderTint(int i) {
                this.placeholderTint = ColorStateList.valueOf(i);
                unset(Flag.NO_TINT, Mask.PLACEHOLDER);
                return this;
            }

            public Builder noPlaceholderTint() {
                this.placeholderTint = null;
                set(Flag.NO_TINT, Mask.PLACEHOLDER);
                return this;
            }

            public Builder fallbackTint(ColorStateList colorStateList) {
                this.fallbackTint = colorStateList;
                unset(Flag.NO_TINT, Mask.FALLBACK);
                return this;
            }

            public Builder fallbackTint(int i) {
                this.fallbackTint = ColorStateList.valueOf(i);
                unset(Flag.NO_TINT, Mask.FALLBACK);
                return this;
            }

            public Builder noFallbackTint() {
                this.fallbackTint = null;
                set(Flag.NO_TINT, Mask.FALLBACK);
                return this;
            }

            public Builder errorTint(ColorStateList colorStateList) {
                this.errorTint = colorStateList;
                unset(Flag.NO_TINT, Mask.ERROR);
                return this;
            }

            public Builder errorTint(int i) {
                this.errorTint = ColorStateList.valueOf(i);
                unset(Flag.NO_TINT, Mask.ERROR);
                return this;
            }

            public Builder noErrorTint() {
                this.errorTint = null;
                set(Flag.NO_TINT, Mask.ERROR);
                return this;
            }

            public Builder placeholderAndFallbackTint(ColorStateList colorStateList) {
                this.placeholderTint = colorStateList;
                this.fallbackTint = colorStateList;
                unset(Flag.NO_TINT, 32736);
                return this;
            }

            public Builder placeholderAndFallbackTint(int i) {
                this.placeholderTint = ColorStateList.valueOf(i);
                this.fallbackTint = ColorStateList.valueOf(i);
                unset(Flag.NO_TINT, 32736);
                return this;
            }

            public Builder setPaletteEnabled(boolean z, int i) {
                set(Flag.PALETTE_ENABLED, i, z);
                return this;
            }

            public Builder noPalette(int i) {
                unset(Flag.PALETTE_ENABLED, i);
                return this;
            }

            public Builder setPaletteEnabled(boolean z) {
                set(Flag.PALETTE_ENABLED, 31, z);
                return this;
            }

            public Builder noPalette() {
                unset(Flag.PALETTE_ENABLED, 31);
                return this;
            }

            public Builder setFallbackPaletteEnabled(boolean z) {
                set(Flag.PALETTE_ENABLED, Mask.FALLBACK, z);
                return this;
            }

            public Builder noFallbackPalette() {
                unset(Flag.PALETTE_ENABLED, Mask.FALLBACK);
                return this;
            }

            public Builder setErrorPaletteEnabled(boolean z) {
                set(Flag.PALETTE_ENABLED, Mask.ERROR, z);
                return this;
            }

            public Builder noErrorPalette() {
                unset(Flag.PALETTE_ENABLED, Mask.ERROR);
                return this;
            }

            public Builder clearFilterOnNull(int i) {
                set(Flag.CLEAR_FILTER_ON_NULL, i);
                return this;
            }

            public Builder retainFilterOnNull(int i) {
                unset(Flag.CLEAR_FILTER_ON_NULL, i);
                return this;
            }

            public Builder opaque(int i) {
                set(Flag.OPAQUE, i);
                return this;
            }

            public Builder opaque(boolean z, int i) {
                set(Flag.OPAQUE, i, z);
                return this;
            }

            public Builder opaque() {
                set(Flag.OPAQUE, 31);
                return this;
            }

            public Builder opaque(boolean z) {
                set(Flag.OPAQUE, 31, z);
                return this;
            }

            public Builder placeholderOpaque() {
                set(Flag.OPAQUE, Mask.PLACEHOLDER);
                return this;
            }

            public Builder placeholderOpaque(boolean z) {
                set(Flag.OPAQUE, Mask.PLACEHOLDER, z);
                return this;
            }

            public Builder fallbackOpaque() {
                set(Flag.OPAQUE, Mask.FALLBACK);
                return this;
            }

            public Builder fallbackOpaque(boolean z) {
                set(Flag.OPAQUE, Mask.FALLBACK, z);
                return this;
            }

            public Builder errorOpaque() {
                set(Flag.OPAQUE, Mask.ERROR);
                return this;
            }

            public Builder errorOpaque(boolean z) {
                set(Flag.OPAQUE, Mask.ERROR, z);
                return this;
            }

            public Builder backgroundIndex(int i) {
                this.backgroundIndex = i;
                return this;
            }

            public Builder backgroundColors(int i, int i2, int i3, int i4, int i5) {
                this.backgroundIndex = i;
                this.placeholderBackgroundColor = i2;
                this.backgroundColor = i3;
                this.fallbackBackgroundColor = i4;
                this.errorBackgroundColor = i5;
                return this;
            }

            public Builder backgroundColor(int i) {
                this.backgroundColor = i;
                return this;
            }

            public Builder fallbackBackgroundColor(int i) {
                this.fallbackBackgroundColor = i;
                return this;
            }

            public Builder fallbackBackgroundColorAndTint(int i, Item item2, Context context) {
                this.fallbackBackgroundColor = i;
                fallbackTint(item2.placeholderColor(i, context));
                return this;
            }

            public Builder placeholderBackgroundColor(int i) {
                this.placeholderBackgroundColor = i;
                return this;
            }

            public Builder errorBackgroundColor(int i) {
                this.errorBackgroundColor = i;
                return this;
            }

            private void set(int i, int i2) {
                this.flags = (i & i2) | this.flags;
            }

            private void set(int i, int i2, boolean z) {
                if (z) {
                    this.flags = (i & i2) | this.flags;
                    return;
                }
                this.flags = ((i & i2) ^ -1) & this.flags;
            }

            private void unset(int i, int i2) {
                this.flags = ((i & i2) ^ -1) & this.flags;
            }

            public ImageInfo build() {
                return new ImageInfo(this);
            }
        }

        public ImageInfo withItem(Item item2) {
            return new Builder(this).item(item2).build();
        }

        public boolean isSet(int i, int i2) {
            if ((i & i2 & this.flags) != 0) {
                return Item.DEBUG;
            }
            return false;
        }

        public boolean noPlaceholderTint() {
            if (this.placeholderTint != null || !isSet(Flag.NO_TINT, Mask.PLACEHOLDER)) {
                return false;
            }
            return Item.DEBUG;
        }

        public boolean noFallbackTint() {
            if (this.fallbackTint != null || !isSet(Flag.NO_TINT, Mask.FALLBACK)) {
                return false;
            }
            return Item.DEBUG;
        }

        public boolean noErrorTint() {
            if (this.errorTint != null || !isSet(Flag.NO_TINT, Mask.ERROR)) {
                return false;
            }
            return Item.DEBUG;
        }

        public boolean noTint() {
            if (this.tint != null || !isSet(Flag.NO_TINT, 31)) {
                return false;
            }
            return Item.DEBUG;
        }

        public boolean hasAnyTint() {
            if (this.tint == null && this.placeholderTint == null && this.fallbackTint == null && this.errorTint == null) {
                return false;
            }
            return Item.DEBUG;
        }

        public boolean isAnyTintAllowed() {
            return isSet(Flag.NO_TINT, Mask.ALL) ^ Item.DEBUG;
        }

        public boolean hasAnyTintable() {
            return isSet(Flag.TINTABLE, Mask.ALL);
        }

        public boolean isTintable() {
            return isSet(Flag.TINTABLE, 31);
        }

        public boolean isPlaceholderTintable() {
            return isSet(Flag.TINTABLE, Mask.PLACEHOLDER);
        }

        public boolean isFallbackTintable() {
            return isSet(Flag.TINTABLE, Mask.FALLBACK);
        }

        public boolean isErrorTintable() {
            return isSet(Flag.TINTABLE, Mask.ERROR);
        }

        public boolean isOpaque() {
            return isSet(Flag.OPAQUE, 31);
        }

        public boolean isPlaceholderOpaque() {
            return isSet(Flag.OPAQUE, Mask.PLACEHOLDER);
        }

        public boolean isFallbackOpaque() {
            return isSet(Flag.OPAQUE, Mask.FALLBACK);
        }

        public boolean isErrorOpaque() {
            return isSet(Flag.OPAQUE, Mask.ERROR);
        }

        public boolean fallbackAsResult() {
            return isSet(1048576, Mask.ALL);
        }

        public String toString() {
            return getClass().getName() + "[ item: '" + this.item + "', url: '" + this.url + "', signature: '" + this.signature + ", index: " + this.index + ", placeholderResId: " + this.placeholderResId + ", placeholderTint: " + ColorUtils.toString(this.placeholderTint) + ", tint: " + ColorUtils.toString(this.tint) + ", errorResId: " + this.errorResId + ", errorTint: " + ColorUtils.toString(this.errorTint) + ", fallbackColor: " + this.fallbackColor + ", fallbackResId: " + this.fallbackResId + ", fallbackTint: " + ColorUtils.toString(this.fallbackTint) + ", fallback: " + this.fallback + "]";
        }

        public boolean isPaletteEnabled() {
            if (this.index <= -1 || !isSet(Flag.PALETTE_ENABLED, 31)) {
                return false;
            }
            return Item.DEBUG;
        }

        public boolean isFallbackPaletteEnabled() {
            if (this.index == -1 || !isSet(Flag.PALETTE_ENABLED, Mask.FALLBACK)) {
                return false;
            }
            return Item.DEBUG;
        }

        public boolean isErrorPaletteEnabled() {
            if (this.index == -1 || !isSet(Flag.PALETTE_ENABLED, Mask.ERROR)) {
                return false;
            }
            return Item.DEBUG;
        }

        public boolean hasUrl() {
            if (this.url == null || this.url.isEmpty()) {
                return false;
            }
            return Item.DEBUG;
        }

        public static String whyInvalid(ImageInfo imageInfo) {
            if (imageInfo == null) {
                return "imageInfo is null";
            }
            if (imageInfo.url == null) {
                return "imageInfo.url is null";
            }
            if (imageInfo.url.isEmpty()) {
                return "imageInfo.url is empty";
            }
            if (imageInfo.item == null) {
                return "imageInfo.item is null";
            }
            return "";
        }
    }

    private void loadImage(ImageView imageView, ImageInfo imageInfo) {
        if (imageView != null) {
            ColorStateList colorStateList = null;
            if (imageInfo == null) {
                imageView.setImageDrawable((Drawable) null);
            } else if (imageInfo.hasUrl()) {
                Context context = imageView.getContext();
                if (this.isAutoEnabled && imageInfo.isPaletteEnabled()) {
                    if (!havePalette(imageInfo.index)) {
                        getPaletteForImage(imageView, imageInfo);
                    } else {
                        notifyPaletteChanged(imageInfo.index);
                    }
                }
                if (!(imageInfo.backgroundIndex == -1 || imageInfo.placeholderBackgroundColor == 0)) {
                    imageInfo.item.setAuto(imageInfo.backgroundIndex, new AutoColor(imageInfo.placeholderBackgroundColor), DEBUG);
                }
                DrawableRequestBuilder<String> load = getImageRequestBuilder(context, imageInfo).signature(imageInfo.signature).load(imageInfo.url);
                ColorStateList colorStateList2 = imageInfo.isTintable() ? imageInfo.tint : null;
                ColorStateList colorStateList3 = imageInfo.isPlaceholderTintable() ? imageInfo.placeholderTint : null;
                if (imageInfo.isErrorTintable()) {
                    colorStateList = imageInfo.errorTint;
                }
                final ImageInfo imageInfo2 = imageInfo;
                final ImageView imageView2 = imageView;
                load.into(new TintTarget(imageView, colorStateList2, colorStateList3, colorStateList, imageInfo.flags & ImageInfo.Flag.CLEAR_FILTER_ON_NULL & TintTarget.CLEAR_ALL) {
                    public void onResourceReady(GlideDrawable glideDrawable, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        super.onResourceReady(glideDrawable, glideAnimation);
                        if (!(imageInfo2.backgroundIndex == -1 || imageInfo2.backgroundColor == 0)) {
                            imageInfo2.item.setAuto(imageInfo2.backgroundIndex, new AutoColor(imageInfo2.backgroundColor), Item.DEBUG);
                        }
                        Item.this.onImageReady(glideDrawable);
                    }

                    public void onLoadFailed(Exception exc, Drawable drawable) {
                        super.onLoadFailed(exc, drawable);
                        if (!(imageInfo2.backgroundIndex == -1 || imageInfo2.errorBackgroundColor == 0)) {
                            imageInfo2.item.setAuto(imageInfo2.backgroundIndex, new AutoColor(imageInfo2.errorBackgroundColor), Item.DEBUG);
                        }
                        Item.this.onImageLoadFailed(imageView2, imageInfo2);
                    }

                    public void setDrawable(Drawable drawable) {
                        if (drawable != null) {
                            boolean z = drawable.getOpacity() == -1 ? Item.DEBUG : false;
                            if (imageInfo2.isOpaque() && z && (drawable instanceof TransitionDrawable)) {
                                ((TransitionDrawable) drawable).setCrossFadeEnabled(false);
                            }
                        }
                        super.setDrawable(drawable);
                    }
                });
            } else {
                clearAnyPreviousPaletteRequest(imageView);
                handleImageFallback(imageView, imageInfo);
            }
        }
    }

    private void handleImageFallback(ImageView imageView, ImageInfo imageInfo) {
        ImageInfo imageInfo2;
        if (imageView != null) {
            if (imageInfo == null) {
                imageView.setImageDrawable((Drawable) null);
            } else if (imageInfo.fallback != null && (imageInfo.fallback instanceof ImageInfo)) {
                if (imageInfo.hasAnyTint() && imageInfo.fallback.isAnyTintAllowed() && imageInfo.fallback.hasAnyTintable()) {
                    ImageInfo.Builder builder = new ImageInfo.Builder(imageInfo.fallback);
                    if (imageInfo.fallback.item != this) {
                        builder.item(this);
                    }
                    if (imageInfo.fallback.placeholderTint == null && !imageInfo.fallback.noPlaceholderTint() && imageInfo.fallback.isPlaceholderTintable()) {
                        builder.placeholderTint(imageInfo.placeholderTint);
                    }
                    if (imageInfo.fallback.fallbackTint == null && !imageInfo.fallback.noFallbackTint() && imageInfo.fallback.isFallbackTintable()) {
                        builder.fallbackTint(imageInfo.fallbackTint);
                    }
                    if (imageInfo.fallback.errorTint == null && !imageInfo.fallback.noErrorTint() && imageInfo.fallback.isErrorTintable()) {
                        builder.errorTint(imageInfo.errorTint);
                    }
                    if (imageInfo.fallback.tint == null && !imageInfo.fallback.noTint() && imageInfo.fallback.isTintable()) {
                        builder.tint(imageInfo.tint);
                    }
                    if (imageInfo.fallback.backgroundIndex == -1) {
                        builder.backgroundIndex(imageInfo.backgroundIndex);
                    }
                    if (imageInfo.fallback.backgroundColor == 0) {
                        builder.fallbackBackgroundColor(imageInfo.fallbackBackgroundColor);
                    }
                    imageInfo2 = builder.build();
                } else if (imageInfo.fallback.item != this) {
                    imageInfo2 = imageInfo.fallback.withItem(this);
                } else {
                    imageInfo2 = imageInfo.fallback;
                }
                unsetPalette(imageInfo.index);
                loadImage(imageView, imageInfo2);
            } else if (imageInfo.fallbackResId != 0) {
                ImageInfo.Builder fallbackColor = new ImageInfo.Builder(imageInfo).item(this).url(ThemeUtils.resourceIdToUrlString(imageView.getContext(), imageInfo.fallbackResId)).key(getSignatureForImageLoadFromResource(imageView.getContext())).tint(imageInfo.fallbackTint).tintable(imageInfo.isFallbackTintable()).fallbackColor(imageInfo.fallbackColor);
                if (!imageInfo.fallbackAsResult()) {
                    fallbackColor.opaque(imageInfo.isFallbackOpaque()).backgroundColor(imageInfo.fallbackBackgroundColor).setPaletteEnabled(imageInfo.isFallbackPaletteEnabled()).tint(imageInfo.fallbackTint).tintable(imageInfo.isFallbackTintable());
                }
                fallbackColor.fallbackAsResult(false);
                unsetPalette(imageInfo.index);
                loadImage(imageView, fallbackColor.build());
            } else if (imageInfo.fallbackColor != 0) {
                imageView.setImageDrawable(new ColorDrawable(imageInfo.fallbackColor));
            } else if (imageInfo.errorResId == 0 || !imageInfo.hasUrl()) {
                imageView.setImageDrawable((Drawable) null);
                if (imageInfo.backgroundIndex != -1 && imageInfo.fallbackBackgroundColor != 0) {
                    imageInfo.item.setAuto(imageInfo.backgroundIndex, new AutoColor(imageInfo.fallbackBackgroundColor), DEBUG);
                }
            } else {
                ImageInfo build = new ImageInfo.Builder(imageInfo).item(this).url(ThemeUtils.resourceIdToUrlString(imageView.getContext(), imageInfo.errorResId)).key(getSignatureForImageLoadFromResource(imageView.getContext())).tint(imageInfo.errorTint).tintable(imageInfo.isErrorTintable()).opaque(imageInfo.isErrorOpaque()).backgroundColor(imageInfo.errorBackgroundColor).index(imageInfo.index).placeholder(imageInfo.placeholderResId).setPaletteEnabled(imageInfo.isErrorPaletteEnabled()).build();
                unsetPalette(imageInfo.index);
                loadImage(imageView, build);
            }
        }
    }

    private void clearAnyPreviousPaletteRequest(ImageView imageView) {
        Request request = PaletteViewTarget.getRequest(imageView);
        if (request != null) {
            request.clear();
        }
    }

    @BindingAdapter({"android:src"})
    public static void loadImageSrc(ImageView imageView, ImageInfo imageInfo) {
        if (imageView == null) {
            throw new IllegalArgumentException("target imageView is null");
        } else if (imageInfo != null) {
            if (imageInfo.item != null) {
                imageInfo.item.loadImage(imageView, imageInfo);
                return;
            }
            throw new IllegalArgumentException("imageInfo.item is null");
        }
    }

    @BindingAdapter({"android:src"})
    public static void loadImageSrc(ImageView imageView, ImageInfo.Builder builder) {
        if (builder != null) {
            loadImageSrc(imageView, builder.build());
        }
    }

    public void onPaletteReady(Palette palette, View view, int i) {
        setPalette(i, palette);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append("[ layoutId: ");
        sb.append(this.layoutId);
        sb.append(", primaryColor: ");
        sb.append(ColorUtils.toHexStringArgb(this.primaryColor));
        sb.append(", isDarkTheme: ");
        sb.append(this.isDarkTheme);
        sb.append(", backgroundType: ");
        sb.append(BackgroundType.toString(this.backgroundType));
        sb.append(", isAutoEnabled: ");
        sb.append(this.isAutoEnabled);
        sb.append(", paletteCapacity: ");
        sb.append(this.paletteCapacity);
        sb.append(", autoColorCapacity: ");
        sb.append(this.autoColorCapacity);
        sb.append(", palettes: ");
        sb.append(this.palettes != null ? this.palettes.toString() : "null");
        sb.append(", autoColors: ");
        sb.append(this.autoColors != null ? this.autoColors.toString() : "null");
        sb.append(", clickListener: '");
        sb.append(this.clickListener != null ? "present" : "null");
        sb.append(", longClickListener: '");
        sb.append(this.longClickListener != null ? "present" : "null");
        sb.append("]");
        return sb.toString();
    }

    static String toString(@ColorInt int i) {
        return ColorUtils.toString(i);
    }

    static String toString(ColorStateList colorStateList) {
        return ColorUtils.toString(colorStateList);
    }

    public static abstract class AbstractBuilder<T extends Item, B extends AbstractBuilder<T, B>> {
        /* access modifiers changed from: private */
        public int autoColorCapacity = 10;
        /* access modifiers changed from: private */
        public int backgroundType;
        /* access modifiers changed from: private */
        public ItemClickListener clickListener;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public boolean isAutoEnabled;
        /* access modifiers changed from: private */
        public boolean isDarkTheme;
        /* access modifiers changed from: private */
        public int layoutId;
        /* access modifiers changed from: private */
        public ItemLongClickListener longClickListener;
        /* access modifiers changed from: private */
        public int paletteCapacity = 10;
        /* access modifiers changed from: private */
        public int primaryColor;

        public abstract T build();

        /* access modifiers changed from: protected */
        public abstract B self();

        public AbstractBuilder() {
        }

        public AbstractBuilder(Item item) {
            this.layoutId = item.layoutId;
            this.primaryColor = item.primaryColor;
            this.isAutoEnabled = item.isAutoEnabled;
            this.isDarkTheme = item.isDarkTheme;
            this.backgroundType = item.backgroundType;
            this.paletteCapacity = item.paletteCapacity;
            this.autoColorCapacity = item.autoColorCapacity;
            this.clickListener = item.clickListener;
            this.longClickListener = item.longClickListener;
            this.context = item.getContext();
        }

        public B layoutId(int i) {
            this.layoutId = i;
            return self();
        }

        public B autoColorsEnabled(boolean z) {
            this.isAutoEnabled = z;
            return self();
        }

        public B isDarkTheme(boolean z) {
            this.isDarkTheme = z;
            return self();
        }

        public B backgroundType(int i) {
            this.backgroundType = i;
            return self();
        }

        public B primaryColor(int i) {
            this.primaryColor = i;
            return self();
        }

        public B autoColorCapacity(int i) {
            this.autoColorCapacity = i;
            return self();
        }

        public B palettesCapacity(int i) {
            this.paletteCapacity = i;
            return self();
        }

        public B clickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
            return self();
        }

        public B longClickListener(ItemLongClickListener itemLongClickListener) {
            this.longClickListener = itemLongClickListener;
            return self();
        }

        public B context(Context context2) {
            this.context = context2;
            return self();
        }
    }
}
