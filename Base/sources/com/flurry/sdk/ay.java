package com.flurry.sdk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.widget.Toast;
import com.flurry.sdk.az;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ay {
    public static final String a = "ay";
    public static int b;
    public static int c;
    public static AtomicInteger d;
    static ct<List<az>> e;
    private static ay f;
    private static Map<Integer, az> g;
    private final AtomicInteger h;
    private long i;
    private cv<bx> j = new cv<bx>() {
        public final /* synthetic */ void a(cu cuVar) {
            bx bxVar = (bx) cuVar;
            String str = ay.a;
            db.a(4, str, "onNetworkStateChanged : isNetworkEnable = " + bxVar.a);
            if (bxVar.a) {
                ck.a().b(new Runnable() {
                    public final void run() {
                        ba.a().b();
                    }
                });
            }
        }
    };

    @SuppressLint({"UseSparseArrays"})
    private ay() {
        g = new ConcurrentHashMap();
        this.h = new AtomicInteger(0);
        d = new AtomicInteger(0);
        if (c == 0) {
            c = 600000;
        }
        if (b == 0) {
            b = 15;
        }
        this.i = ck.a().a.getSharedPreferences("FLURRY_SHARED_PREFERENCES", 0).getLong("timeToSendNextPulseReport", 0);
        if (e == null) {
            g();
        }
        cw.a().a("com.flurry.android.sdk.NetworkStateEvent", this.j);
    }

    /* access modifiers changed from: private */
    public static void g() {
        e = new ct<>(ck.a().a.getFileStreamPath(".yflurryanongoingpulsecallbackreporter"), ".yflurryanongoingpulsecallbackreporter", 2, new dz<List<az>>() {
            public final dw<List<az>> a(int i) {
                return new dv(new az.a());
            }
        });
    }

    public static void a() {
        if (f != null) {
            cw.a().b("com.flurry.android.sdk.NetworkStateEvent", f.j);
            g.clear();
            g = null;
            f = null;
        }
    }

    public static void a(int i2) {
        b = i2;
    }

    public static void b(int i2) {
        c = i2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x007f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void a(com.flurry.sdk.az r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            r0 = 3
            if (r6 != 0) goto L_0x000f
            java.lang.String r6 = a     // Catch:{ all -> 0x000d }
            java.lang.String r1 = "Must add valid PulseCallbackAsyncReportInfo"
            com.flurry.sdk.db.a((int) r0, (java.lang.String) r6, (java.lang.String) r1)     // Catch:{ all -> 0x000d }
            monitor-exit(r5)
            return
        L_0x000d:
            r6 = move-exception
            goto L_0x0080
        L_0x000f:
            java.lang.String r1 = a     // Catch:{ all -> 0x000d }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x000d }
            java.lang.String r3 = "Adding and sending "
            r2.<init>(r3)     // Catch:{ all -> 0x000d }
            java.lang.String r3 = r6.d     // Catch:{ all -> 0x000d }
            r2.append(r3)     // Catch:{ all -> 0x000d }
            java.lang.String r3 = " report to PulseCallbackManager."
            r2.append(r3)     // Catch:{ all -> 0x000d }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x000d }
            com.flurry.sdk.db.a((int) r0, (java.lang.String) r1, (java.lang.String) r2)     // Catch:{ all -> 0x000d }
            java.util.List r0 = r6.a()     // Catch:{ all -> 0x000d }
            int r0 = r0.size()     // Catch:{ all -> 0x000d }
            if (r0 == 0) goto L_0x007e
            long r0 = r5.i     // Catch:{ all -> 0x000d }
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L_0x0051
            long r0 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x000d }
            int r2 = c     // Catch:{ all -> 0x000d }
            long r2 = (long) r2     // Catch:{ all -> 0x000d }
            long r0 = r0 + r2
            r5.i = r0     // Catch:{ all -> 0x000d }
            com.flurry.sdk.ck r0 = com.flurry.sdk.ck.a()     // Catch:{ all -> 0x000d }
            com.flurry.sdk.ay$3 r1 = new com.flurry.sdk.ay$3     // Catch:{ all -> 0x000d }
            r1.<init>()     // Catch:{ all -> 0x000d }
            r0.b(r1)     // Catch:{ all -> 0x000d }
        L_0x0051:
            int r0 = r5.i()     // Catch:{ all -> 0x000d }
            r6.c = r0     // Catch:{ all -> 0x000d }
            java.util.Map<java.lang.Integer, com.flurry.sdk.az> r1 = g     // Catch:{ all -> 0x000d }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x000d }
            r1.put(r0, r6)     // Catch:{ all -> 0x000d }
            java.util.List r6 = r6.a()     // Catch:{ all -> 0x000d }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ all -> 0x000d }
        L_0x0068:
            boolean r0 = r6.hasNext()     // Catch:{ all -> 0x000d }
            if (r0 == 0) goto L_0x007e
            java.lang.Object r0 = r6.next()     // Catch:{ all -> 0x000d }
            com.flurry.sdk.av r0 = (com.flurry.sdk.av) r0     // Catch:{ all -> 0x000d }
            com.flurry.sdk.x r1 = com.flurry.sdk.x.a()     // Catch:{ all -> 0x000d }
            com.flurry.sdk.au r1 = r1.c     // Catch:{ all -> 0x000d }
            r1.b(r0)     // Catch:{ all -> 0x000d }
            goto L_0x0068
        L_0x007e:
            monitor-exit(r5)
            return
        L_0x0080:
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.ay.a(com.flurry.sdk.az):void");
    }

    /* access modifiers changed from: private */
    public void h() {
        SharedPreferences.Editor edit = ck.a().a.getSharedPreferences("FLURRY_SHARED_PREFERENCES", 0).edit();
        edit.putLong("timeToSendNextPulseReport", this.i);
        edit.apply();
    }

    private synchronized int i() {
        return this.h.incrementAndGet();
    }

    public final synchronized void b(az azVar) {
        if (azVar == null) {
            db.a(3, a, "Must add valid PulseCallbackAsyncReportInfo");
            return;
        }
        if (this.i == 0) {
            this.i = System.currentTimeMillis() + ((long) c);
            ck.a().b(new Runnable() {
                public final void run() {
                    ay.this.h();
                }
            });
        }
        int i2 = i();
        azVar.c = i2;
        g.put(Integer.valueOf(i2), azVar);
        for (av avVar : azVar.a()) {
            Iterator<aw> it = avVar.f.iterator();
            while (it.hasNext()) {
                it.next();
                d.incrementAndGet();
                if (k()) {
                    db.a(3, a, "Max Callback Attempts threshold reached. Sending callback logging reports");
                    m();
                }
            }
        }
        if (l()) {
            db.a(3, a, "Time threshold reached. Sending callback logging reports");
            m();
        }
        String str = a;
        db.a(3, str, "Restoring " + azVar.d + " report to PulseCallbackManager. Number of stored completed callbacks: " + d.get());
    }

    public final synchronized void a(final aw awVar) {
        String str = a;
        db.a(3, str, awVar.l.g.d + " report sent successfully to " + awVar.l.l);
        awVar.f = ax.COMPLETE;
        awVar.g = "";
        c(awVar);
        if (db.c() <= 3 && db.d()) {
            ck.a().a((Runnable) new Runnable() {
                public final void run() {
                    Context context = ck.a().a;
                    Toast.makeText(context, "PulseCallbackReportInfo HTTP Response Code: " + awVar.e + " for url: " + awVar.l.r, 1).show();
                }
            });
        }
    }

    private void c(aw awVar) {
        awVar.d = true;
        awVar.a();
        d.incrementAndGet();
        awVar.l.b();
        String str = a;
        db.a(3, str, awVar.l.g.d + " report to " + awVar.l.l + " finalized.");
        b();
        j();
    }

    public final void b() {
        ck.a().b(new Runnable() {
            public final void run() {
                ay.d();
                List<az> c = ay.c();
                if (ay.e == null) {
                    ay.g();
                }
                ay.e.a(c);
            }
        });
    }

    private void j() {
        if (k() || l()) {
            db.a(3, a, "Threshold reached. Sending callback logging reports");
            m();
        }
    }

    public static List<az> c() {
        return new ArrayList(g.values());
    }

    public static synchronized ay d() {
        ay ayVar;
        synchronized (ay.class) {
            if (f == null) {
                f = new ay();
            }
            ayVar = f;
        }
        return ayVar;
    }

    private static boolean k() {
        return d.intValue() >= b;
    }

    private boolean l() {
        return System.currentTimeMillis() > this.i;
    }

    private void m() {
        Iterator<az> it = c().iterator();
        while (true) {
            boolean z = false;
            if (it.hasNext()) {
                az next = it.next();
                for (av avVar : next.a()) {
                    Iterator<aw> it2 = avVar.f.iterator();
                    while (it2.hasNext()) {
                        aw next2 = it2.next();
                        if (next2.j) {
                            it2.remove();
                        } else if (!next2.f.equals(ax.PENDING_COMPLETION)) {
                            next2.j = true;
                            z = true;
                        }
                    }
                }
                if (z) {
                    ba.a().a(next);
                }
            } else {
                ba.a().b();
                this.i = System.currentTimeMillis() + ((long) c);
                h();
                n();
                d = new AtomicInteger(0);
                b();
                return;
            }
        }
    }

    private void n() {
        for (az next : c()) {
            if (next.b()) {
                c(next.c);
            } else {
                for (av next2 : next.a()) {
                    if (next2.m) {
                        next.e.remove(Long.valueOf(next2.a));
                    } else {
                        Iterator<aw> it = next2.f.iterator();
                        while (it.hasNext()) {
                            if (it.next().j) {
                                it.remove();
                            }
                        }
                    }
                }
            }
        }
    }

    private synchronized void c(int i2) {
        String str = a;
        db.a(3, str, "Removing report " + i2 + " from PulseCallbackManager");
        g.remove(Integer.valueOf(i2));
    }

    public final synchronized boolean a(aw awVar, String str) {
        awVar.h++;
        awVar.i = System.currentTimeMillis();
        if (!(awVar.h > awVar.l.c)) {
            if (!TextUtils.isEmpty(str)) {
                db.a(3, a, "Report to " + awVar.l.l + " redirecting to url: " + str);
                awVar.l.r = str;
                b();
                return true;
            }
        }
        db.a(3, a, "Maximum number of redirects attempted. Aborting: " + awVar.l.g.d + " report to " + awVar.l.l);
        awVar.f = ax.INVALID_RESPONSE;
        awVar.g = "";
        c(awVar);
        return false;
    }

    public final synchronized void b(aw awVar) {
        String str = a;
        db.a(3, str, "Maximum number of attempts reached. Aborting: " + awVar.l.g.d);
        awVar.f = ax.TIMEOUT;
        awVar.i = System.currentTimeMillis();
        awVar.g = "";
        c(awVar);
    }

    public final synchronized boolean b(aw awVar, String str) {
        boolean z;
        awVar.f = ax.INVALID_RESPONSE;
        awVar.i = System.currentTimeMillis();
        if (str == null) {
            str = "";
        }
        awVar.g = str;
        av avVar = awVar.l;
        z = false;
        if (avVar.p >= avVar.b) {
            String str2 = a;
            db.a(3, str2, "Maximum number of attempts reached. Aborting: " + awVar.l.g.d + " report to " + awVar.l.l);
            c(awVar);
        } else if (!er.a(awVar.l.r)) {
            String str3 = a;
            db.a(3, str3, "Url: " + awVar.l.r + " is invalid.");
            c(awVar);
        } else {
            String str4 = a;
            db.a(3, str4, "Retrying callback to " + awVar.l.g.d + " in: " + (awVar.l.h / 1000) + " seconds.");
            awVar.a();
            d.incrementAndGet();
            b();
            j();
            z = true;
        }
        return z;
    }

    public static List<az> e() {
        if (e == null) {
            g();
        }
        return e.a();
    }
}
