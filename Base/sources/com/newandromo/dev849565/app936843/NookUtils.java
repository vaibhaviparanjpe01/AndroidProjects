package com.newandromo.dev849565.app936843;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

public final class NookUtils {
    private static final String TAG = "NookUtils";

    public static boolean isSomeKindOfNook() {
        return isOldNook() || isNewNook();
    }

    public static boolean isOldNook() {
        return Build.MODEL.equals("BNRV200") || Build.MODEL.equals("BNTV250") || Build.MODEL.equals("BNTV250A");
    }

    public static boolean isNewNook() {
        return Build.MODEL.equals("BNTV600") || Build.MODEL.equals("BNTV400") || "NOOK".equals(System.getProperty("ro.nook.manufacturer"));
    }

    public static void setWallpaper(Context context) {
        if (context != null) {
            Intent intent = new Intent();
            if (isOldNook()) {
                intent.setAction("com.bn.nook.CHANGE_WALLPAPER");
            } else if (isNewNook()) {
                intent.setAction("android.intent.action.SET_WALLPAPER");
            }
            try {
                context.startActivity(intent);
            } catch (ActivityNotFoundException unused) {
                Toast.makeText(context, R.string.failed_to_set_wallpaper, 1).show();
            }
        }
    }
}
