package com.newandromo.dev849565.app936843;

import android.content.Context;

public final class DimenUtils {
    private static final String TAG = "DimenUtils";

    private DimenUtils() {
        throw new AssertionError();
    }

    public static float pxToDp(Context context, float f) {
        return f / context.getResources().getDisplayMetrics().density;
    }

    public static float dpToPx(Context context, float f) {
        return f * context.getResources().getDisplayMetrics().density;
    }

    public static int dp(Context context, float f) {
        double d = (double) (f * context.getResources().getDisplayMetrics().density);
        Double.isNaN(d);
        return (int) (d + 0.5d);
    }
}
