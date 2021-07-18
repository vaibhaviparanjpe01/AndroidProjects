package com.flurry.sdk;

import android.telephony.TelephonyManager;

public class bz {
    private static final String a = "bz";
    private static bz b;

    private bz() {
    }

    public static synchronized bz a() {
        bz bzVar;
        synchronized (bz.class) {
            if (b == null) {
                b = new bz();
            }
            bzVar = b;
        }
        return bzVar;
    }

    public static void b() {
        b = null;
    }

    public static String c() {
        TelephonyManager telephonyManager = (TelephonyManager) ck.a().a.getSystemService("phone");
        if (telephonyManager == null) {
            return null;
        }
        return telephonyManager.getNetworkOperatorName();
    }

    public static String d() {
        TelephonyManager telephonyManager = (TelephonyManager) ck.a().a.getSystemService("phone");
        if (telephonyManager == null) {
            return null;
        }
        return telephonyManager.getNetworkOperator();
    }
}
