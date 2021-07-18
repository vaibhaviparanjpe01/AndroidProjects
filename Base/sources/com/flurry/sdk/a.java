package com.flurry.sdk;

public class a {
    private static final String c = "a";
    public final h a;
    public m b;

    public a(h hVar, m mVar) {
        this.a = hVar;
        this.b = mVar;
    }

    public final String a(String str, String str2, j jVar) {
        c a2 = this.b.a(str, jVar);
        if (a2 == null) {
            a2 = this.a.a(str);
        }
        return a2 != null ? a2.a() : str2;
    }

    public final int a(String str, int i, j jVar) {
        c a2 = this.b.a(str, jVar);
        if (a2 == null) {
            a2 = this.a.a(str);
        }
        if (a2 == null) {
            return i;
        }
        try {
            return Integer.decode(a2.a()).intValue();
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public final float a(String str, float f, j jVar) {
        c a2 = this.b.a(str, jVar);
        if (a2 == null) {
            a2 = this.a.a(str);
        }
        if (a2 == null) {
            return f;
        }
        try {
            return Float.parseFloat(a2.a());
        } catch (NumberFormatException unused) {
            return f;
        }
    }

    public final long a(String str, long j, j jVar) {
        c a2 = this.b.a(str, jVar);
        if (a2 == null) {
            a2 = this.a.a(str);
        }
        if (a2 == null) {
            return j;
        }
        try {
            return Long.decode(a2.a()).longValue();
        } catch (NumberFormatException unused) {
            return j;
        }
    }

    public final double a(String str, double d, j jVar) {
        c a2 = this.b.a(str, jVar);
        if (a2 == null) {
            a2 = this.a.a(str);
        }
        if (a2 == null) {
            return d;
        }
        try {
            return Double.parseDouble(a2.a());
        } catch (NumberFormatException unused) {
            return d;
        }
    }
}
