package com.flurry.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Timer;
import java.util.TimerTask;

public class e {
    static final String a = "e";
    public final SharedPreferences b;
    int c = 0;
    long d;
    private Timer e;
    private final Object f = new Object();

    public e() {
        Context context = ck.a().a;
        this.b = context.getSharedPreferences("FLURRY_SHARED_PREFERENCES", 0);
        cb.a();
        this.c = cb.b(context);
        this.d = this.b != null ? this.b.getLong("refreshFetch", 604800000) : 604800000;
    }

    public final int a() {
        if (this.b != null) {
            return this.b.getInt("appVersion", 0);
        }
        return 0;
    }

    private void e() {
        if (this.b != null) {
            this.b.edit().remove("appVersion").apply();
        }
    }

    public final synchronized void a(TimerTask timerTask, long j) {
        synchronized (this.f) {
            String str = a;
            db.a(str, "Record retry after " + j + " msecs.");
            this.e = new Timer("retry-scheduler");
            this.e.schedule(timerTask, j);
        }
    }

    public final void b() {
        synchronized (this.f) {
            if (this.e != null) {
                db.a(3, a, "Clear retry.");
                this.e.cancel();
                this.e.purge();
                this.e = null;
            }
        }
    }

    public final void c() {
        db.a(a, "Clear all ConfigMeta data.");
        b();
        e();
        f();
        g();
        h();
        i();
        j();
    }

    private void f() {
        if (this.b != null) {
            this.b.edit().remove("lastFetch").apply();
        }
    }

    public final void a(long j) {
        if (this.b != null) {
            this.b.edit().putLong("lastFetch", j).apply();
        }
    }

    private void g() {
        if (this.b != null) {
            this.b.edit().remove("lastETag").apply();
        }
    }

    public final String d() {
        if (this.b != null) {
            return this.b.getString("lastKeyId", (String) null);
        }
        return null;
    }

    private void h() {
        if (this.b != null) {
            this.b.edit().remove("lastKeyId").apply();
        }
    }

    private void i() {
        if (this.b != null) {
            this.b.edit().remove("lastRSA").apply();
        }
    }

    private void j() {
        if (this.b != null) {
            this.b.edit().remove("com.flurry.sdk.variant_ids").apply();
        }
    }
}
