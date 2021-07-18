package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.content.res.Resources;

public class IntercomHelper {
    public static void init(Context context) {
    }

    public static void launch() {
    }

    public static void showHelper(boolean z) {
    }

    public static boolean showInDashboard() {
        return false;
    }

    public static boolean showInDrawer() {
        return false;
    }

    public static boolean showInMenu() {
        return false;
    }

    public static float convertDpToPixel(float f) {
        return (float) Math.round(f * (((float) Resources.getSystem().getDisplayMetrics().densityDpi) / 160.0f));
    }
}
