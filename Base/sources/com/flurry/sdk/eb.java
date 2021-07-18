package com.flurry.sdk;

import com.flurry.sdk.eh;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class eb implements eh.a {
    private static final List<Class<?>> c = new ArrayList();
    long a;
    final Object b = new Object();
    private final String d = eb.class.getSimpleName();
    private final Map<Class<?>, Object> e = new LinkedHashMap();
    private volatile int f = a.a;

    public boolean a() {
        return false;
    }

    public eb() {
        ArrayList<Class> arrayList;
        synchronized (c) {
            arrayList = new ArrayList<>(c);
        }
        for (Class cls : arrayList) {
            try {
                Object newInstance = cls.newInstance();
                synchronized (this.e) {
                    this.e.put(cls, newInstance);
                }
            } catch (Exception e2) {
                String str = this.d;
                db.a(5, str, "Module data " + cls + " is not available:", e2);
            }
        }
        eg a2 = eg.a();
        this.a = ((Long) a2.a("ContinueSessionMillis")).longValue();
        a2.a("ContinueSessionMillis", (eh.a) this);
        String str2 = this.d;
        db.a(4, str2, "initSettings, ContinueSessionMillis = " + this.a);
    }

    public static void a(Class<?> cls) {
        synchronized (c) {
            c.add(cls);
        }
    }

    public static void b(Class<?> cls) {
        synchronized (c) {
            c.remove(cls);
        }
    }

    public final Object c(Class<?> cls) {
        Object obj;
        synchronized (this.e) {
            obj = this.e.get(cls);
        }
        return obj;
    }

    public long b() {
        return this.a;
    }

    public final void a(int i) {
        synchronized (this.b) {
            this.f = i;
        }
    }

    public final int c() {
        int i;
        synchronized (this.b) {
            i = this.f;
        }
        return i;
    }

    public final void a(String str, Object obj) {
        if (str.equals("ContinueSessionMillis")) {
            this.a = ((Long) obj).longValue();
            String str2 = this.d;
            db.a(4, str2, "onSettingUpdate, ContinueSessionMillis = " + this.a);
            return;
        }
        db.a(6, this.d, "onSettingUpdate internal error!");
    }

    public enum a {
        ;
        
        public static final int a = 1;
        public static final int b = 2;
        public static final int c = 3;

        static {
            d = new int[]{a, b, c};
        }

        public static int[] a() {
            return (int[]) d.clone();
        }
    }
}
