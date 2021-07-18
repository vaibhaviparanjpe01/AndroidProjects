package com.flurry.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public final class ej {
    private static final String a = "ej";

    public static String a(Context context) {
        PackageInfo c = c(context);
        return (c == null || c.packageName == null) ? "" : c.packageName;
    }

    private static PackageInfo c(Context context) {
        if (context != null) {
            try {
                return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException unused) {
                String str = a;
                db.a(str, "Cannot find package info for package: " + context.getPackageName());
            }
        }
        return null;
    }

    public static String b(Context context) {
        PackageInfo c = c(context);
        return (c == null || c.versionName == null) ? "" : c.versionName;
    }
}
