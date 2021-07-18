package com.flurry.sdk;

import java.util.Locale;

public final class bv {
    public static bv a;

    private bv() {
    }

    public static synchronized bv a() {
        bv bvVar;
        synchronized (bv.class) {
            if (a == null) {
                a = new bv();
            }
            bvVar = a;
        }
        return bvVar;
    }

    public static String b() {
        return Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
    }
}
