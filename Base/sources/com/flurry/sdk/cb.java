package com.flurry.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.os.EnvironmentCompat;
import android.text.TextUtils;
import com.flurry.sdk.eh;

public class cb implements eh.a {
    private static final String a = "cb";
    private static cb b;
    private String c;
    private String d;

    private cb() {
        eg a2 = eg.a();
        this.c = (String) a2.a("VersionName");
        a2.a("VersionName", (eh.a) this);
        String str = a;
        db.a(4, str, "initSettings, VersionName = " + this.c);
    }

    public static synchronized cb a() {
        cb cbVar;
        synchronized (cb.class) {
            if (b == null) {
                b = new cb();
            }
            cbVar = b;
        }
        return cbVar;
    }

    public static void b() {
        if (b != null) {
            eg.a().b("VersionName", b);
        }
        b = null;
    }

    public static String c() {
        return Build.VERSION.RELEASE;
    }

    public static String d() {
        return Build.DEVICE;
    }

    public static String a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return null;
        }
        try {
            return packageManager.getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
    }

    public static int b(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            try {
                return packageManager.getPackageInfo(context.getPackageName(), 0).versionCode;
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return 0;
    }

    public final synchronized String e() {
        if (!TextUtils.isEmpty(this.c)) {
            return this.c;
        } else if (!TextUtils.isEmpty(this.d)) {
            return this.d;
        } else {
            this.d = f();
            return this.d;
        }
    }

    private static String f() {
        try {
            Context context = ck.a().a;
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (packageInfo.versionName != null) {
                return packageInfo.versionName;
            }
            if (packageInfo.versionCode != 0) {
                return Integer.toString(packageInfo.versionCode);
            }
            return "Unknown";
        } catch (Throwable th) {
            db.a(6, a, "", th);
            return "Unknown";
        }
    }

    public final void a(String str, Object obj) {
        if (str.equals("VersionName")) {
            this.c = (String) obj;
            String str2 = a;
            db.a(4, str2, "onSettingUpdate, VersionName = " + this.c);
            return;
        }
        db.a(6, a, "onSettingUpdate internal error!");
    }
}
