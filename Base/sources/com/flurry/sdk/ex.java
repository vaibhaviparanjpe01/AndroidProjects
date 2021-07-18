package com.flurry.sdk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.customtabs.CustomTabsIntent;

public final class ex {
    private static final String a = "ex";
    private static Boolean b;

    public interface a {
        void a(Context context);
    }

    private ex() {
    }

    public static void a(Context context, CustomTabsIntent customTabsIntent, Uri uri, a aVar) {
        if (!a(context)) {
            aVar.a(context);
            return;
        }
        if (Build.VERSION.SDK_INT >= 22) {
            Intent intent = customTabsIntent.intent;
            intent.putExtra("android.intent.extra.REFERRER", Uri.parse("2//" + context.getPackageName()));
        }
        customTabsIntent.intent.setPackage(ey.a(context));
        customTabsIntent.launchUrl(context, uri);
    }

    public static boolean a(Context context) {
        if (b != null) {
            return b.booleanValue();
        }
        b = Boolean.TRUE;
        try {
            Class.forName("android.support.customtabs.CustomTabsClient");
        } catch (ClassNotFoundException unused) {
            db.e(a, "Couldn't find Chrome Custom Tab dependency. For better user experience include Chrome Custom Tab dependency in gradle");
            b = Boolean.FALSE;
        }
        Boolean valueOf = Boolean.valueOf(b.booleanValue() && ey.a(context) != null);
        b = valueOf;
        return valueOf.booleanValue();
    }
}
