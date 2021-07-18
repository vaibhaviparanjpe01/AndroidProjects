package com.newandromo.dev849565.app936843;

import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.text.TextUtils;
import java.util.ArrayList;

public final class BackgroundEffect {
    public static final int DARKEN = 1;
    public static final int GREYSCALE = 4;
    public static final int LIGHTEN = 2;
    public static final int NONE = 0;
    private static final String TAG = "BackgroundEffect";
    public static final int TINT = 8;

    public static boolean isDarkenEnabled(int i) {
        return (i & 1) != 0;
    }

    public static boolean isGreyscaleEnabled(int i) {
        return (i & 4) != 0;
    }

    public static boolean isLightenEnabled(int i) {
        return (i & 2) != 0;
    }

    public static boolean isTintEnabled(int i) {
        return (i & 8) != 0;
    }

    public static String describe(int i) {
        if (i == 0) {
            return "none";
        }
        ArrayList arrayList = new ArrayList();
        if ((i & 4) != 0) {
            arrayList.add("greyscale");
        }
        if ((i & 1) != 0) {
            arrayList.add("darken");
        } else if ((i & 2) != 0) {
            arrayList.add("lighten");
        }
        if ((i & 8) != 0) {
            arrayList.add("tint");
        }
        return TextUtils.join(" + ", arrayList);
    }

    public static String describe(int i, int i2) {
        if (i == 0) {
            return "none";
        }
        ArrayList arrayList = new ArrayList();
        if ((i & 4) != 0) {
            arrayList.add("greyscale");
        }
        if ((i & 1) != 0) {
            arrayList.add("darken");
        } else if ((i & 2) != 0) {
            arrayList.add("lighten");
        }
        if (!((i & 8) == 0 || i2 == 0)) {
            arrayList.add("tint(" + ColorUtils.toString(i2) + ")");
        }
        return TextUtils.join(" + ", arrayList);
    }

    public static ColorMatrix darkenMatrix() {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setScale(0.6f, 0.6f, 0.6f, 1.0f);
        return colorMatrix;
    }

    public static ColorMatrix lightenMatrix() {
        return new ColorMatrix(new float[]{0.4f, 0.0f, 0.0f, 0.0f, 153.0f, 0.0f, 0.4f, 0.0f, 0.0f, 153.0f, 0.0f, 0.0f, 0.4f, 0.0f, 153.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
    }

    public static ColorMatrix greyscaleMatrix() {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        return colorMatrix;
    }

    public static ColorMatrix tintMatrix(int i) {
        float red = (float) Color.red(i);
        float green = (float) Color.green(i);
        float blue = (float) Color.blue(i);
        Color.alpha(i);
        float f = 1.0f - ((((red / 255.0f) * 0.2126f) + ((green / 255.0f) * 0.7152f)) + ((blue / 255.0f) * 0.0722f));
        float f2 = 1.0f - (0.5f * f);
        return new ColorMatrix(new float[]{f, 0.0f, 0.0f, 0.0f, red * f2, 0.0f, f, 0.0f, 0.0f, green * f2, 0.0f, 0.0f, f, 0.0f, blue * f2, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
    }

    public static ColorMatrix getMatrix(int i, int i2) {
        boolean z = false;
        boolean z2 = (i & 1) != 0;
        boolean z3 = (i & 2) != 0;
        boolean z4 = (i & 4) != 0;
        if ((i & 8) != 0) {
            z = true;
        }
        ColorMatrix colorMatrix = new ColorMatrix();
        if (z4) {
            colorMatrix.setSaturation(0.0f);
        }
        if (z2) {
            colorMatrix.postConcat(darkenMatrix());
        } else if (z3) {
            colorMatrix.postConcat(lightenMatrix());
        }
        if (z && i2 != 0) {
            colorMatrix.postConcat(tintMatrix(i2));
        }
        return colorMatrix;
    }
}
