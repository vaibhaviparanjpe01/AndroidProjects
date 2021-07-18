package com.flurry.sdk;

public final class i {
    private static i b;
    public d a = d.a();

    public static synchronized i a() {
        i iVar;
        synchronized (i.class) {
            if (b == null) {
                if (ck.a() != null) {
                    b = new i();
                } else {
                    throw new IllegalStateException("Flurry SDK must be initialized before starting config");
                }
            }
            iVar = b;
        }
        return iVar;
    }

    private i() {
    }

    public final String a(String str, String str2, j jVar) {
        return this.a.c().a(str, str2, jVar);
    }
}
