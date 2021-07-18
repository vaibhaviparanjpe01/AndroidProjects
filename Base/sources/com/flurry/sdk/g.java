package com.flurry.sdk;

public final class g {
    public static final g a = new g(a.SUCCEED, (String) null);
    public static final g b = new g(a.NO_CHANGE, (String) null);
    String c;
    a d;

    public g(a aVar, String str) {
        this.d = aVar;
        this.c = str;
    }

    public enum a {
        SUCCEED(1),
        NO_CHANGE(0),
        IO(-1),
        NOT_VALID_JSON(-2),
        AUTHENTICATE(-3),
        UNKNOWN_CERTIFICATE(-4),
        OTHER(-5);
        
        int h;

        private a(int i2) {
            this.h = i2;
        }
    }

    public final String toString() {
        return "[Error:" + this.d.name() + "] " + this.c;
    }
}
