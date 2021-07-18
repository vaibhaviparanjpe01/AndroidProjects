package com.newandromo.dev849565.app936843;

import android.content.res.ColorStateList;
import android.databinding.ObservableInt;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;

public final class ColorUtils {
    static final int[] ACTIVATED_STATE_SET = {16843518};
    static final int[] CHECKED_AND_ENABLED_STATE_SET = {16842912, 16842910};
    static final int[] CHECKED_STATE_SET = {16842912};
    public static final float DEFAULT_DISABLED_ALPHA_DARK = 0.3f;
    public static final float DEFAULT_DISABLED_ALPHA_LIGHT = 0.26f;
    static final int[] DISABLED_STATE_SET = {-16842910};
    static final int[] EMPTY_STATE_SET = new int[0];
    static final int[] FOCUSED_AND_ENABLED_STATE_SET = {16842908, 16842910};
    static final int[] FOCUSED_STATE_SET = {16842908};
    static final int[] NOT_PRESSED_OR_FOCUSED_STATE_SET = {-16842919, -16842908};
    static final int[] PRESSED_AND_ENABLED_STATE_SET = {16842919, 16842910};
    static final int[] PRESSED_STATE_SET = {16842919};
    static final int[] SELECTED_AND_ENABLED_STATE_SET = {16842913, 16842910};
    static final int[] SELECTED_STATE_SET = {16842913};
    private static final String TAG = "ColorUtils";

    public static int getAlmostPrimaryTextColor(boolean z) {
        return z ? -436207617 : -570425344;
    }

    public static int getPrimaryTextColor(boolean z) {
        return z ? -1 : -570425344;
    }

    public static int getSecondaryTextColor(boolean z) {
        return z ? -1275068417 : -1979711488;
    }

    public static int setAlpha(int i, float f) {
        return (i & ViewCompat.MEASURED_SIZE_MASK) | (((int) ((f * 255.0f) + 0.5f)) << 24);
    }

    public static int setAlpha(int i, int i2) {
        return (i & ViewCompat.MEASURED_SIZE_MASK) | (i2 << 24);
    }

    public static String expandShorthandColors(String str) {
        int length;
        if (!(str == null || str.charAt(0) != '#' || (length = str.length()) == 7 || length == 9)) {
            switch (length) {
                case 4:
                case 5:
                    String str2 = "#";
                    for (int i = 1; i < str.length(); i++) {
                        char charAt = str.charAt(i);
                        str2 = (str2 + charAt) + charAt;
                    }
                    return str2;
            }
        }
        return str;
    }

    public static String toHexStringArgb(@ColorInt int i) {
        return String.format("%08X", new Object[]{Integer.valueOf(i)});
    }

    public static String toHexStringRgb(@ColorInt int i) {
        return String.format("%06X", new Object[]{Integer.valueOf(i & ViewCompat.MEASURED_SIZE_MASK)});
    }

    public static String toString(@ColorInt int i) {
        return String.format("#%08X", new Object[]{Integer.valueOf(i)});
    }

    public static String toString(ColorStateList colorStateList) {
        if (colorStateList == null) {
            return "ColorStateList:null";
        }
        int defaultColor = colorStateList.getDefaultColor();
        if (colorStateList.isStateful()) {
            String str = "ColorStateList(stateful:true, default:" + toHexStringArgb(defaultColor);
            int colorForState = colorStateList.getColorForState(DISABLED_STATE_SET, defaultColor);
            if (colorForState != defaultColor) {
                str = str + ", disabled:" + toHexStringArgb(colorForState);
            }
            int colorForState2 = colorStateList.getColorForState(PRESSED_AND_ENABLED_STATE_SET, defaultColor);
            if (colorForState2 != defaultColor) {
                str = str + ", pressed:" + toHexStringArgb(colorForState2);
            }
            int colorForState3 = colorStateList.getColorForState(FOCUSED_AND_ENABLED_STATE_SET, defaultColor);
            if (colorForState3 != defaultColor) {
                str = str + ", focused:" + toHexStringArgb(colorForState3);
            }
            int colorForState4 = colorStateList.getColorForState(SELECTED_AND_ENABLED_STATE_SET, defaultColor);
            if (colorForState4 != defaultColor) {
                str = str + ", selected:" + toHexStringArgb(colorForState4);
            }
            if (colorStateList.getColorForState(CHECKED_AND_ENABLED_STATE_SET, defaultColor) != defaultColor) {
                str = str + ", checked:" + toHexStringArgb(colorForState4);
            }
            return str + ")";
        }
        return "ColorStateList(stateful:false, default:" + toHexStringArgb(defaultColor) + ")";
    }

    public static ColorDrawable colorToDrawable(int i) {
        if (i != 0) {
            return new ColorDrawable(i);
        }
        return null;
    }

    public static int parseColor(String str) {
        return Color.parseColor(expandShorthandColors(str));
    }

    public static int darkerRgb(int i, float f) {
        return Color.argb(Color.alpha(i), Math.max((int) (((float) Color.red(i)) * f), 0), Math.max((int) (((float) Color.green(i)) * f), 0), Math.max((int) (((float) Color.blue(i)) * f), 0));
    }

    public static int darker(int i) {
        return darker(i, 0.8f);
    }

    public static int darker(int i, float f) {
        float[] fArr = new float[3];
        Color.colorToHSV(i, fArr);
        fArr[2] = fArr[2] * f;
        return Color.HSVToColor(fArr);
    }

    public static int offsetValue(int i, float f) {
        float[] fArr = new float[3];
        Color.colorToHSV(i, fArr);
        fArr[2] = fArr[2] + f;
        return Color.HSVToColor(fArr);
    }

    public static int offset(int i, int i2, int i3, int i4, int i5) {
        int alpha = Color.alpha(i) + i5;
        int red = Color.red(i) + i2;
        int green = Color.green(i) + i3;
        int blue = Color.blue(i) + i4;
        if (alpha < 0) {
            alpha = 0;
        }
        if (red < 0) {
            red = 0;
        }
        if (green < 0) {
            green = 0;
        }
        if (blue < 0) {
            blue = 0;
        }
        if (alpha > 255) {
            alpha = 255;
        }
        if (red > 255) {
            red = 255;
        }
        if (green > 255) {
            green = 255;
        }
        if (blue > 255) {
            blue = 255;
        }
        return Color.argb(alpha, red, green, blue);
    }

    public static int offset(int i, int i2, int i3, int i4) {
        int alpha = Color.alpha(i);
        int red = Color.red(i) + i2;
        int green = Color.green(i) + i3;
        int blue = Color.blue(i) + i4;
        if (red < 0) {
            red = 0;
        }
        if (green < 0) {
            green = 0;
        }
        if (blue < 0) {
            blue = 0;
        }
        if (red > 255) {
            red = 255;
        }
        if (green > 255) {
            green = 255;
        }
        if (blue > 255) {
            blue = 255;
        }
        return Color.argb(alpha, red, green, blue);
    }

    public static int offset(int i, int i2) {
        return offset(i, i2, i2, i2);
    }

    public static int getPerceivedBrightness(int i) {
        int red = Color.red(i);
        int green = Color.green(i);
        int blue = Color.blue(i);
        double d = (double) (red * red);
        Double.isNaN(d);
        double d2 = (double) (green * green);
        Double.isNaN(d2);
        double d3 = (d * 0.241d) + (d2 * 0.691d);
        double d4 = (double) (blue * blue);
        Double.isNaN(d4);
        return (int) Math.sqrt(d3 + (d4 * 0.068d));
    }

    public static int getPerceivedBrightnessSquared(int i) {
        int red = Color.red(i);
        int green = Color.green(i);
        int blue = Color.blue(i);
        double d = (double) (red * red);
        Double.isNaN(d);
        double d2 = (double) (green * green);
        Double.isNaN(d2);
        double d3 = (d * 0.241d) + (d2 * 0.691d);
        double d4 = (double) (blue * blue);
        Double.isNaN(d4);
        return (int) (d3 + (d4 * 0.068d));
    }

    public static boolean isDark(int i) {
        return getPerceivedBrightnessSquared(i) < 16900;
    }

    public static boolean isDark(ObservableInt observableInt) {
        return isDark(observableInt, false);
    }

    public static boolean isDark(ObservableInt observableInt, boolean z) {
        if (observableInt != null) {
            return getPerceivedBrightnessSquared(observableInt.get()) < 16900;
        }
        return z;
    }

    public static int getAppropriateForegroundColor(int i) {
        if (getPerceivedBrightness(i) < 130) {
            return -1;
        }
        return ViewCompat.MEASURED_STATE_MASK;
    }

    public static int getAlmostPrimaryTextColor(boolean z, int i) {
        if (z) {
            return setAlpha(i, 229);
        }
        return setAlpha(i, 222);
    }

    public static int getPrimaryTextColor(boolean z, int i) {
        if (z) {
            return setAlpha(i, 255);
        }
        return setAlpha(i, 222);
    }

    public static int getSecondaryTextColor(boolean z, int i) {
        if (z) {
            return setAlpha(i, 179);
        }
        return setAlpha(i, 138);
    }

    public static int setAlpha(ObservableInt observableInt, int i) {
        if (observableInt != null) {
            return (observableInt.get() & ViewCompat.MEASURED_SIZE_MASK) | (i << 24);
        }
        return 0;
    }

    public static int setAlpha(ObservableInt observableInt, float f) {
        if (observableInt != null) {
            return (observableInt.get() & ViewCompat.MEASURED_SIZE_MASK) | (((int) ((f * 255.0f) + 0.5f)) << 24);
        }
        return 0;
    }

    public static int adjustAlpha(int i, float f) {
        return adjustAlpha(i, f, 0, 255);
    }

    public static int adjustAlpha(int i, float f, int i2, int i3) {
        double d = (double) (((float) (i >>> 24)) * f);
        Double.isNaN(d);
        int i4 = (int) (d + 0.5d);
        if (i4 > i3) {
            i4 = i3;
        }
        if (i4 < i2) {
            i4 = i2;
        }
        return (i & ViewCompat.MEASURED_SIZE_MASK) | (i4 << 24);
    }

    public static double distanceEuclideanSquared(@NonNull double[] dArr, @NonNull double[] dArr2) {
        return Math.pow(dArr[0] - dArr2[0], 2.0d) + Math.pow(dArr[1] - dArr2[1], 2.0d) + Math.pow(dArr[2] - dArr2[2], 2.0d);
    }

    public static double distanceEuclidean(@NonNull double[] dArr, @NonNull double[] dArr2) {
        return Math.sqrt(Math.pow(dArr[0] - dArr2[0], 2.0d) + Math.pow(dArr[1] - dArr2[1], 2.0d) + Math.pow(dArr[2] - dArr2[2], 2.0d));
    }

    public static double calculateDeltaE(@NonNull double[] dArr, @NonNull double[] dArr2) {
        return calculateDeltaE(dArr[0], dArr[1], dArr[2], dArr2[0], dArr2[1], dArr2[2]);
    }

    public static double calculateDeltaE(double d, double d2, double d3, double d4, double d5, double d6) {
        return Math.sqrt(calculateDeltaESquared(d, d2, d3, d4, d5, d6));
    }

    public static double calculateDeltaESquared(@NonNull double[] dArr, @NonNull double[] dArr2) {
        return calculateDeltaESquared(dArr[0], dArr[1], dArr[2], dArr2[0], dArr2[1], dArr2[2]);
    }

    public static double calculateDeltaESquared(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7;
        double d8;
        double d9 = d3;
        double d10 = d6;
        double d11 = d9 * d9;
        double d12 = d10 * d10;
        double d13 = (d + d4) / 2.0d;
        double sqrt = (Math.sqrt((d2 * d2) + d11) + Math.sqrt((d5 * d5) + d12)) / 2.0d;
        double sqrt2 = ((1.0d - Math.sqrt(Math.pow(sqrt, 7.0d) / (Math.pow(sqrt, 7.0d) + Math.pow(25.0d, 7.0d)))) / 2.0d) + 1.0d;
        double d14 = d2 * sqrt2;
        double d15 = d5 * sqrt2;
        double sqrt3 = Math.sqrt((d14 * d14) + d11);
        double sqrt4 = Math.sqrt((d15 * d15) + d12);
        double d16 = (sqrt3 + sqrt4) / 2.0d;
        double atan2 = Math.atan2(d9, d14);
        double atan22 = Math.atan2(d9, d14);
        int i = 0;
        double d17 = (double) (atan22 < 0.0d ? 1 : 0);
        Double.isNaN(d17);
        double d18 = atan2 + (d17 * 6.283185307179586d);
        double atan23 = Math.atan2(d10, d15);
        if (Math.atan2(d10, d15) < 0.0d) {
            i = 1;
        }
        double d19 = (double) i;
        Double.isNaN(d19);
        double d20 = atan23 + (d19 * 6.283185307179586d);
        double d21 = d18 - d20;
        if (Math.abs(d21) > 3.141592653589793d) {
            d8 = d18 + d20 + 6.283185307179586d;
            d7 = 2.0d;
        } else {
            d7 = 2.0d;
            d8 = d18 + d20;
        }
        double d22 = d8 / d7;
        double cos = (((1.0d - (Math.cos(d22 - 0.5235987755982988d) * 0.17d)) + (Math.cos(d22 * d7) * 0.24d)) + (Math.cos((3.0d * d22) + 0.10471975511965977d) * 0.32d)) - (Math.cos((4.0d * d22) - 1.0995574287564276d) * 0.2d);
        double d23 = sqrt4 - sqrt3;
        double sqrt5 = Math.sqrt(sqrt3 * sqrt4) * 2.0d * Math.sin((Math.abs(d21) <= 3.141592653589793d ? d20 - d18 : d20 <= d18 ? (d20 - d18) + 6.283185307179586d : (d20 - d18) - 6.283185307179586d) / 2.0d);
        double d24 = d13 - 50.0d;
        double d25 = ((d22 * 57.29577951308232d) - 275.0d) / 25.0d;
        double sqrt6 = (d4 - d) / (((((d24 * 0.015d) * d24) / Math.sqrt((d24 * d24) + 20.0d)) + 1.0d) * 1.0d);
        double d26 = d23 / (((0.045d * d16) + 1.0d) * 1.0d);
        double d27 = sqrt5 / ((((0.015d * d16) * cos) + 1.0d) * 1.0d);
        return (sqrt6 * sqrt6) + (d26 * d26) + (d27 * d27) + ((-(Math.sqrt(Math.pow(d16, 7.0d) / (Math.pow(d16, 7.0d) + Math.pow(25.0d, 7.0d))) * 2.0d)) * Math.sin(Math.exp((-d25) * d25) * 0.5235987755982988d * 2.0d) * d26 * d27);
    }

    public static ColorStateList createDisabledStateList(int i, int i2) {
        return new ColorStateList(new int[][]{DISABLED_STATE_SET, EMPTY_STATE_SET}, new int[]{i2, i});
    }

    public static ColorStateList createDisabledStateList(int i, int i2, int i3) {
        return new ColorStateList(new int[][]{DISABLED_STATE_SET, PRESSED_STATE_SET, EMPTY_STATE_SET}, new int[]{i3, i2, i});
    }
}
