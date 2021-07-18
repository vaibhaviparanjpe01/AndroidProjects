package com.flurry.sdk;

import android.content.Context;
import java.util.Map;

public class bq implements df {
    public static synchronized bq a() {
        bq bqVar;
        synchronized (bq.class) {
            bqVar = (bq) ck.a().a((Class<? extends df>) bq.class);
        }
        return bqVar;
    }

    public void init(Context context) {
        eb.a((Class<?>) ch.class);
        cw.a();
        eg.a();
        cm.a();
        by.a();
        bs.a();
        bz.a();
        bw.a();
        cb.a();
        bv.a();
        cg.a();
    }

    public void destroy() {
        cg.b();
        bv.a = null;
        cb.b();
        bs.b();
        bw.b();
        bz.b();
        by.b();
        cm.b();
        eg.b();
        cw.b();
        eb.b(ch.class);
    }

    public static String b() {
        ch c = c();
        if (c != null) {
            return Long.toString(c.b);
        }
        return null;
    }

    public static ch c() {
        return a(ed.a().f());
    }

    private static ch a(eb ebVar) {
        if (ebVar == null) {
            return null;
        }
        return (ch) ebVar.c(ch.class);
    }

    public static long d() {
        ch c = c();
        if (c != null) {
            return c.b;
        }
        return 0;
    }

    public static long e() {
        ch c = c();
        if (c != null) {
            return c.c;
        }
        return 0;
    }

    public static long f() {
        ch c = c();
        if (c != null) {
            return c.d;
        }
        return -1;
    }

    public static long g() {
        ch c = c();
        if (c != null) {
            return c.b();
        }
        return 0;
    }

    public static long h() {
        ch c = c();
        if (c != null) {
            return c.e;
        }
        return 0;
    }

    public static String i() {
        ch c = c();
        if (c != null) {
            return c.c();
        }
        return null;
    }

    public static void a(String str) {
        ch c = c();
        if (c != null) {
            c.a(str);
        }
    }

    public static String j() {
        ch c = c();
        if (c != null) {
            return c.d();
        }
        return null;
    }

    public static Map<String, String> k() {
        ch c = c();
        if (c != null) {
            return c.e();
        }
        return null;
    }

    public static int l() {
        return by.a().c();
    }
}
