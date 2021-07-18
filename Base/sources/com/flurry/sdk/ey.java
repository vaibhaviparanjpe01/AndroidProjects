package com.flurry.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

final class ey {
    private static final String a = "ey";
    private static String b;

    private ey() {
    }

    static String a(Context context) {
        if (b != null) {
            return b;
        }
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
        ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 0);
        String str = resolveActivity != null ? resolveActivity.activityInfo.packageName : null;
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo next : queryIntentActivities) {
            Intent intent2 = new Intent();
            intent2.setAction("android.support.customtabs.action.CustomTabsService");
            intent2.setPackage(next.activityInfo.packageName);
            if (packageManager.resolveService(intent2, 0) != null) {
                arrayList.add(next.activityInfo.packageName);
            }
        }
        if (arrayList.isEmpty()) {
            b = null;
        } else if (arrayList.size() == 1) {
            b = (String) arrayList.get(0);
        } else if (!TextUtils.isEmpty(str) && !a(context, intent) && arrayList.contains(str)) {
            b = str;
        } else if (arrayList.contains("com.android.chrome")) {
            b = "com.android.chrome";
        } else if (arrayList.contains("com.chrome.beta")) {
            b = "com.chrome.beta";
        } else if (arrayList.contains("com.chrome.dev")) {
            b = "com.chrome.dev";
        } else if (arrayList.contains("com.google.android.apps.chrome")) {
            b = "com.google.android.apps.chrome";
        }
        return b;
    }

    private static boolean a(Context context, Intent intent) {
        try {
            List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 64);
            if (queryIntentActivities != null) {
                if (queryIntentActivities.size() != 0) {
                    for (ResolveInfo next : queryIntentActivities) {
                        IntentFilter intentFilter = next.filter;
                        if (intentFilter != null && intentFilter.countDataAuthorities() != 0 && intentFilter.countDataPaths() != 0 && next.activityInfo != null) {
                            return true;
                        }
                    }
                    return false;
                }
            }
            return false;
        } catch (RuntimeException unused) {
            db.b(a, "Runtime exception while getting specialized handlers");
        }
    }
}
