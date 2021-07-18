package com.newandromo.dev849565.app936843;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.CardView;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

public final class ThemeUtils {
    private static final int[] CARD_BACKGROUND_ATTR = {R.attr.cardBackgroundColor};
    private static final int[] COLOR_BACKGROUND_ATTR = {16842801};
    private static final boolean DEBUG = false;
    private static final String TAG = "ThemeUtils";
    private static final int[] TEMP_ARRAY = new int[1];
    private static final ThreadLocal<TypedValue> TL_TYPED_VALUE = new ThreadLocal<>();

    public static int getDefaultAccentColor(boolean z) {
        return z ? -16738680 : -8336444;
    }

    public static int getColor(Context context, int i) {
        return getColor(context, i, 0);
    }

    public static int getColor(Context context, int i, int i2) {
        if (context == null) {
            return i2;
        }
        TEMP_ARRAY[0] = i;
        try {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(TEMP_ARRAY);
            int color = obtainStyledAttributes.getColor(0, i2);
            try {
                obtainStyledAttributes.recycle();
            } catch (Resources.NotFoundException unused) {
            }
            return color;
        } catch (Resources.NotFoundException unused2) {
            return i2;
        }
    }

    public static int getColorAdjustingAlpha(Context context, int i, float f) {
        return getColorAdjustingAlpha(context, i, f, 0);
    }

    public static int getColorAdjustingAlpha(Context context, int i, float f, int i2) {
        int color = getColor(context, i, i2);
        return color != i2 ? ColorUtils.setAlpha(color, Math.round(((float) Color.alpha(color)) * f)) : color;
    }

    public static int getDisabledThemeAttrColor(Context context, int i) {
        ColorStateList colorStateList = getColorStateList(context, i);
        if (colorStateList != null && colorStateList.isStateful()) {
            return colorStateList.getColorForState(ColorUtils.DISABLED_STATE_SET, colorStateList.getDefaultColor());
        }
        TypedValue typedValue = getTypedValue();
        context.getTheme().resolveAttribute(16842803, typedValue, true);
        return getColorAdjustingAlpha(context, i, typedValue.getFloat(), 0);
    }

    public static int getDisabledColor(Context context, int i, float f) {
        if (i != 0) {
            return ColorUtils.adjustAlpha(i, getDisabledAlpha(context, f));
        }
        return 0;
    }

    public static int getDisabledColor(View view, int i) {
        return getDisabledColor(view != null ? view.getContext() : null, i);
    }

    public static int getDisabledColor(View view, int i, float f) {
        return getDisabledColor(view != null ? view.getContext() : null, i, f);
    }

    public static int getDisabledColor(Context context, int i) {
        if (context != null) {
            return getDisabledColor(context, i, 0.3f);
        }
        return ColorUtils.adjustAlpha(i, 0.3f);
    }

    public static ColorStateList createDisabledStateList(Context context, int i) {
        return ColorUtils.createDisabledStateList(i, getDisabledColor(context, i));
    }

    public static ColorStateList createDisabledStateList(Context context, int i, float f) {
        if (i != 0) {
            return ColorUtils.createDisabledStateList(i, getDisabledColor(context, i, f));
        }
        return null;
    }

    public static float getDisabledAlpha(Context context, float f) {
        if (context == null) {
            return f;
        }
        TypedValue typedValue = getTypedValue();
        context.getTheme().resolveAttribute(16842803, typedValue, true);
        return typedValue.getFloat();
    }

    public static ColorStateList getColorStateList(Context context, int i) {
        ColorStateList colorStateList;
        ColorStateList colorStateList2 = null;
        if (context != null) {
            TEMP_ARRAY[0] = i;
            try {
                TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, (AttributeSet) null, TEMP_ARRAY);
                int resourceId = obtainStyledAttributes.getResourceId(0, 0);
                if (resourceId != 0) {
                    colorStateList = AppCompatResources.getColorStateList(context, resourceId);
                } else {
                    colorStateList = obtainStyledAttributes.getColorStateList(0);
                }
                colorStateList2 = colorStateList;
                obtainStyledAttributes.recycle();
            } catch (Resources.NotFoundException unused) {
            }
        }
        return colorStateList2;
    }

    public static ColorStateList getCardViewDefaultBackground(Context context) {
        int i;
        ColorStateList colorStateList;
        ColorStateList colorStateList2 = null;
        if (context == null) {
            return null;
        }
        try {
            TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, (AttributeSet) null, CARD_BACKGROUND_ATTR);
            int resourceId = obtainStyledAttributes.getResourceId(0, 0);
            if (resourceId != 0) {
                colorStateList = AppCompatResources.getColorStateList(context, resourceId);
            } else {
                colorStateList = obtainStyledAttributes.getColorStateList(0);
            }
            colorStateList2 = colorStateList;
            obtainStyledAttributes.recycle();
        } catch (Resources.NotFoundException unused) {
        }
        if (colorStateList2 != null) {
            return colorStateList2;
        }
        try {
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(COLOR_BACKGROUND_ATTR);
            int color = obtainStyledAttributes2.getColor(0, 0);
            obtainStyledAttributes2.recycle();
            float[] fArr = new float[3];
            Color.colorToHSV(color, fArr);
            if (fArr[2] > 0.5f) {
                i = context.getResources().getColor(R.color.cardview_light_background);
            } else {
                i = context.getResources().getColor(R.color.cardview_dark_background);
            }
            return ColorStateList.valueOf(i);
        } catch (Resources.NotFoundException unused2) {
            return colorStateList2;
        }
    }

    public static int getInt(Context context, int i) {
        return getInt(context, i, 0);
    }

    public static int getInt(Context context, int i, int i2) {
        if (context == null) {
            return i2;
        }
        TEMP_ARRAY[0] = i;
        try {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(TEMP_ARRAY);
            int i3 = obtainStyledAttributes.getInt(0, i2);
            try {
                obtainStyledAttributes.recycle();
            } catch (Resources.NotFoundException unused) {
            }
            return i3;
        } catch (Resources.NotFoundException unused2) {
            return i2;
        }
    }

    public static boolean getBoolean(Context context, int i) {
        return getBoolean(context, i, false);
    }

    public static boolean getBoolean(Context context, int i, boolean z) {
        if (context == null) {
            return z;
        }
        TEMP_ARRAY[0] = i;
        try {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(TEMP_ARRAY);
            boolean z2 = obtainStyledAttributes.getBoolean(0, z);
            try {
                obtainStyledAttributes.recycle();
            } catch (Resources.NotFoundException unused) {
            }
            return z2;
        } catch (Resources.NotFoundException unused2) {
            return z;
        }
    }

    public static float getFraction(Context context, int i, float f) {
        if (context == null) {
            return f;
        }
        TEMP_ARRAY[0] = i;
        try {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(TEMP_ARRAY);
            float fraction = obtainStyledAttributes.getFraction(0, 1, 1, f);
            try {
                obtainStyledAttributes.recycle();
            } catch (Resources.NotFoundException unused) {
            }
            return fraction;
        } catch (Resources.NotFoundException unused2) {
            return f;
        }
    }

    public static int getColor(Toolbar toolbar, int i) {
        return getColor(toolbar, i, 0);
    }

    public static int getColor(Toolbar toolbar, int i, int i2) {
        return toolbar != null ? getColor(toolbar.getContext(), i, i2) : i2;
    }

    public static int getTextColorPrimary(Context context) {
        return getColor(context, 16842806);
    }

    public static int getTextColorPrimary(Toolbar toolbar) {
        if (toolbar != null) {
            return getTextColorPrimary(toolbar.getContext());
        }
        return 0;
    }

    public static int getActivityThemeResId(Context context, String str) {
        try {
            String packageName = context.getPackageName();
            return context.getPackageManager().getActivityInfo(new ComponentName(packageName, packageName + "." + str), 128).getThemeResource();
        } catch (PackageManager.NameNotFoundException unused) {
            return 0;
        }
    }

    public static ContextThemeWrapper getThemedContext(Context context, int i) {
        return new ContextThemeWrapper(context, i);
    }

    public static ContextThemeWrapper getThemedContext(Context context, String str) {
        return new ContextThemeWrapper(context, getActivityThemeResId(context, str));
    }

    public static int getResourceId(Context context, int i) {
        return getResourceId(context, i, 0);
    }

    public static int getResourceId(Context context, int i, int i2) {
        if (context == null || i == 0) {
            return i2;
        }
        TEMP_ARRAY[0] = i;
        try {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(TEMP_ARRAY);
            int resourceId = obtainStyledAttributes.getResourceId(0, i2);
            try {
                obtainStyledAttributes.recycle();
            } catch (Resources.NotFoundException unused) {
            }
            return resourceId;
        } catch (Resources.NotFoundException unused2) {
            return i2;
        }
    }

    public static String getResourceUrl(Context context, int i) {
        int resourceId = getResourceId(context, i, 0);
        if (resourceId != 0) {
            return resourceIdToUrlString(context, resourceId);
        }
        return null;
    }

    public static String resourceIdToUrlString(Context context, int i) {
        if (context != null) {
            Resources resources = context.getResources();
            try {
                return "android.resource://" + resources.getResourcePackageName(i) + '/' + resources.getResourceTypeName(i) + '/' + resources.getResourceEntryName(i);
            } catch (Resources.NotFoundException unused) {
                Log.isLoggable(TAG, 5);
            }
        }
        return null;
    }

    public static boolean isResourceUrl(String str) {
        return str != null && str.startsWith("android.resource://");
    }

    public static String getString(Context context, int i) {
        if (context == null) {
            return null;
        }
        TEMP_ARRAY[0] = i;
        try {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(TEMP_ARRAY);
            String string = obtainStyledAttributes.getString(0);
            try {
                obtainStyledAttributes.recycle();
            } catch (Resources.NotFoundException unused) {
            }
            return string;
        } catch (Resources.NotFoundException unused2) {
            return null;
        }
    }

    public static String resolveString(Context context, int i) {
        if (context == null || i == 0) {
            return null;
        }
        Resources.Theme theme = context.getTheme();
        TypedValue typedValue = new TypedValue();
        if (!theme.resolveAttribute(i, typedValue, true) || typedValue.string == null) {
            return null;
        }
        return typedValue.string.toString();
    }

    public static CharSequence getText(Context context, int i) {
        if (context == null) {
            return null;
        }
        TEMP_ARRAY[0] = i;
        try {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(TEMP_ARRAY);
            CharSequence text = obtainStyledAttributes.getText(0);
            try {
                obtainStyledAttributes.recycle();
            } catch (Resources.NotFoundException unused) {
            }
            return text;
        } catch (Resources.NotFoundException unused2) {
            return null;
        }
    }

    public static int getDefaultAccentColor(int i) {
        return ColorUtils.isDark(i) ? i != -8336444 ? -8336444 : -8268550 : i != -16738680 ? -16738680 : -16537100;
    }

    public static Drawable findEquivalentBackground(View view, View view2) {
        View findViewById;
        if (view2 == null || (findViewById = view2.findViewById(view.getId())) == null) {
            return null;
        }
        return findViewById.getBackground();
    }

    public static ColorStateList findEquivalentBackground(CardView cardView, View view) {
        View findViewById;
        if (view == null || (findViewById = view.findViewById(cardView.getId())) == null || !(findViewById instanceof CardView)) {
            return null;
        }
        return ((CardView) findViewById).getCardBackgroundColor();
    }

    public static View getEquivalentViewFromBinding(View view, ViewDataBinding viewDataBinding) {
        if (view != null) {
            Context context = view.getContext();
            if (context instanceof ItemBindingInfo) {
                return ((ItemBindingInfo) context).getViewFromBinding(viewDataBinding, view.getId());
            }
        }
        return null;
    }

    public static View getDefaultViewByInflation(View view, int i) {
        View inflate = LayoutInflater.from(view.getContext()).inflate(i, (ViewGroup) null, false);
        if (inflate != null) {
            return inflate.findViewById(view.getId());
        }
        return null;
    }

    public static Drawable getDefaultBackgroundByInflation(View view, int i) {
        View defaultViewByInflation = getDefaultViewByInflation(view, i);
        if (defaultViewByInflation != null) {
            return defaultViewByInflation.getBackground();
        }
        return null;
    }

    public static ColorStateList getDefaultBackgroundByInflation(CardView cardView, int i) {
        View defaultViewByInflation = getDefaultViewByInflation(cardView, i);
        if (defaultViewByInflation == null || !(defaultViewByInflation instanceof CardView)) {
            return null;
        }
        return ((CardView) defaultViewByInflation).getCardBackgroundColor();
    }

    public static ColorStateList getEquivalentBackgroundFromBinding(CardView cardView, ViewDataBinding viewDataBinding) {
        View equivalentViewFromBinding = getEquivalentViewFromBinding(cardView, viewDataBinding);
        if (equivalentViewFromBinding == null || !(equivalentViewFromBinding instanceof CardView)) {
            return findEquivalentBackground(cardView, viewDataBinding.getRoot());
        }
        return ((CardView) equivalentViewFromBinding).getCardBackgroundColor();
    }

    public static Drawable getEquivalentBackgroundFromBinding(View view, ViewDataBinding viewDataBinding) {
        View equivalentViewFromBinding = getEquivalentViewFromBinding(view, viewDataBinding);
        if (equivalentViewFromBinding != null) {
            return equivalentViewFromBinding.getBackground();
        }
        if (viewDataBinding != null) {
            return findEquivalentBackground(view, viewDataBinding.getRoot());
        }
        return null;
    }

    public static ColorStateList findEquivalentTextColors(TextView textView, View view) {
        View findViewById;
        if (view == null || (findViewById = view.findViewById(textView.getId())) == null || !(findViewById instanceof TextView)) {
            return null;
        }
        return ((TextView) findViewById).getTextColors();
    }

    public static ColorStateList getDefaultTextColorsByInflation(TextView textView, int i) {
        View defaultViewByInflation = getDefaultViewByInflation(textView, i);
        if (defaultViewByInflation == null || !(defaultViewByInflation instanceof TextView)) {
            return null;
        }
        return ((TextView) defaultViewByInflation).getTextColors();
    }

    public static ColorStateList getEquivalentTextColorsFromBinding(TextView textView, ViewDataBinding viewDataBinding) {
        View equivalentViewFromBinding = getEquivalentViewFromBinding(textView, viewDataBinding);
        if (equivalentViewFromBinding != null && (equivalentViewFromBinding instanceof TextView)) {
            return ((TextView) equivalentViewFromBinding).getTextColors();
        }
        if (viewDataBinding != null) {
            return findEquivalentTextColors(textView, viewDataBinding.getRoot());
        }
        return null;
    }

    private static boolean isColorInt(Context context, int i) {
        Resources resources = context.getResources();
        TypedValue typedValue = getTypedValue();
        resources.getValue(i, typedValue, true);
        if (typedValue.type < 28 || typedValue.type > 31) {
            return false;
        }
        return true;
    }

    private static TypedValue getTypedValue() {
        TypedValue typedValue = TL_TYPED_VALUE.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        TL_TYPED_VALUE.set(typedValue2);
        return typedValue2;
    }

    public static boolean isDefaultLayoutBindingWorkaroundNeeded() {
        return Build.VERSION.SDK_INT <= 19;
    }

    /* JADX WARNING: Removed duplicated region for block: B:4:0x001e A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isLandscape(android.content.Context r3) {
        /*
            boolean r0 = r3 instanceof android.app.Activity
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0020
            android.graphics.Point r0 = new android.graphics.Point
            r0.<init>()
            android.app.Activity r3 = (android.app.Activity) r3
            android.view.WindowManager r3 = r3.getWindowManager()
            android.view.Display r3 = r3.getDefaultDisplay()
            r3.getSize(r0)
            int r3 = r0.x
            int r0 = r0.y
            if (r3 <= r0) goto L_0x0040
        L_0x001e:
            r1 = 1
            goto L_0x0040
        L_0x0020:
            if (r3 == 0) goto L_0x0031
            android.content.res.Resources r3 = r3.getResources()
            android.util.DisplayMetrics r3 = r3.getDisplayMetrics()
            int r0 = r3.widthPixels
            int r3 = r3.heightPixels
            if (r0 <= r3) goto L_0x0040
            goto L_0x001e
        L_0x0031:
            android.content.res.Resources r3 = android.content.res.Resources.getSystem()
            android.util.DisplayMetrics r3 = r3.getDisplayMetrics()
            int r0 = r3.widthPixels
            int r3 = r3.heightPixels
            if (r0 <= r3) goto L_0x0040
            goto L_0x001e
        L_0x0040:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.newandromo.dev849565.app936843.ThemeUtils.isLandscape(android.content.Context):boolean");
    }

    public static boolean isLandscape() {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        return displayMetrics.widthPixels > displayMetrics.heightPixels;
    }

    @TargetApi(17)
    public static Point getScreenResolution(Context context) {
        if (Build.VERSION.SDK_INT >= 14) {
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            Point point = new Point();
            try {
                defaultDisplay.getRealSize(point);
            } catch (NoSuchMethodError unused) {
                point.set(defaultDisplay.getWidth(), defaultDisplay.getHeight());
            }
            return point;
        }
        throw new RuntimeException("Unsupported Android version.");
    }

    @TargetApi(17)
    public static int getScreenHeight(Context context) {
        if (Build.VERSION.SDK_INT >= 14) {
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            try {
                Point point = new Point();
                defaultDisplay.getRealSize(point);
                return point.y;
            } catch (NoSuchMethodError unused) {
                return defaultDisplay.getHeight();
            }
        } else {
            throw new RuntimeException("Unsupported Android version.");
        }
    }
}
