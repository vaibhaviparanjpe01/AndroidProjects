package com.flurry.sdk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;

public class ck {
    private static final String c = "ck";
    @SuppressLint({"StaticFieldLeak"})
    private static ck d = null;
    private static boolean i = false;
    public final Context a;
    public final String b;
    private final Handler e = new Handler(Looper.getMainLooper());
    private final Handler f;
    private final HandlerThread g = new HandlerThread("FlurryAgent");
    private final de h;

    private ck(Context context, String str) {
        this.a = context.getApplicationContext();
        this.g.start();
        this.f = new Handler(this.g.getLooper());
        this.b = str;
        this.h = new de();
    }

    public static ck a() {
        return d;
    }

    public static synchronized void a(Context context, String str) {
        synchronized (ck.class) {
            if (d != null) {
                if (d.b.equals(str)) {
                    db.e(c, "Flurry is already initialized");
                    return;
                }
                throw new IllegalStateException("Only one API key per application is supported!");
            } else if (context == null) {
                throw new IllegalArgumentException("Context cannot be null");
            } else if (!TextUtils.isEmpty(str)) {
                ck ckVar = new ck(context, str);
                d = ckVar;
                ckVar.h.a(context);
            } else {
                throw new IllegalArgumentException("API key must be specified");
            }
        }
    }

    public static synchronized void b() {
        synchronized (ck.class) {
            if (d != null) {
                ck ckVar = d;
                fd.b();
                ckVar.h.b();
                ckVar.g.quit();
                d = null;
            }
        }
    }

    public final void a(Runnable runnable) {
        this.e.post(runnable);
    }

    public final void b(Runnable runnable) {
        this.f.post(runnable);
    }

    public final void a(Runnable runnable, long j) {
        if (runnable != null) {
            this.f.postDelayed(runnable, j);
        }
    }

    public final void c(Runnable runnable) {
        if (runnable != null) {
            this.f.removeCallbacks(runnable);
        }
    }

    public final df a(Class<? extends df> cls) {
        return this.h.b(cls);
    }

    public static synchronized void a(boolean z) {
        synchronized (ck.class) {
            i = z;
        }
    }

    public static synchronized boolean c() {
        boolean z;
        synchronized (ck.class) {
            z = i;
        }
        return z;
    }
}
