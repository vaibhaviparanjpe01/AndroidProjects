package com.flurry.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.flurry.sdk.db;
import com.flurry.sdk.em;
import com.flurry.sdk.y;

public final class FlurryInstallReceiver extends BroadcastReceiver {
    private static final String a = "FlurryInstallReceiver";

    public final void onReceive(Context context, Intent intent) {
        String str = a;
        db.a(4, str, "Received an Install notification of " + intent.getAction());
        String string = intent.getExtras().getString("referrer");
        db.a(4, a, "Received an Install referrer of ".concat(String.valueOf(string)));
        if (string == null || !"com.android.vending.INSTALL_REFERRER".equals(intent.getAction())) {
            db.a(5, a, "referrer is null");
            return;
        }
        if (!string.contains("=")) {
            db.a(4, a, "referrer is before decoding: ".concat(String.valueOf(string)));
            string = em.f(string);
            db.a(4, a, "referrer is: ".concat(String.valueOf(string)));
        }
        new y(context).a(string);
    }
}
