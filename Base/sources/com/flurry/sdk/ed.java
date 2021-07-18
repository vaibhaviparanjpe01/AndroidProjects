package com.flurry.sdk;

import android.app.Activity;
import android.content.Context;
import com.flurry.sdk.cn;
import com.flurry.sdk.eb;
import com.flurry.sdk.ec;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class ed {
    /* access modifiers changed from: private */
    public static final String b = "ed";
    private static ed c;
    public long a = 0;
    private final Map<Context, eb> d = new WeakHashMap();
    private final ee e = new ee();
    private final Object f = new Object();
    private eb g;
    /* access modifiers changed from: private */
    public boolean h;
    private AtomicBoolean i = new AtomicBoolean(false);
    private cv<ef> j = new cv<ef>() {
        public final /* bridge */ /* synthetic */ void a(cu cuVar) {
            ed.this.h();
        }
    };
    private cv<cn> k = new cv<cn>() {
        public final /* synthetic */ void a(cu cuVar) {
            cn cnVar = (cn) cuVar;
            Activity activity = (Activity) cnVar.a.get();
            if (activity == null) {
                db.a(ed.b, "Activity has been destroyed, can't pass ActivityLifecycleEvent to adobject.");
                return;
            }
            switch (AnonymousClass8.a[cnVar.b - 1]) {
                case 1:
                    String g = ed.b;
                    db.a(3, g, "Automatic onStartSession for context:" + cnVar.a);
                    ed.this.e(activity);
                    return;
                case 2:
                    String g2 = ed.b;
                    db.a(3, g2, "Automatic onEndSession for context:" + cnVar.a);
                    ed.this.d(activity);
                    return;
                case 3:
                    String g3 = ed.b;
                    db.a(3, g3, "Automatic onEndSession (destroyed) for context:" + cnVar.a);
                    ed.this.d(activity);
                    return;
                default:
                    return;
            }
        }
    };

    /* renamed from: com.flurry.sdk.ed$8  reason: invalid class name */
    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] a = new int[cn.a.a().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0011 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                int[] r0 = com.flurry.sdk.cn.a.a()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                r0 = 1
                int[] r1 = a     // Catch:{ NoSuchFieldError -> 0x0011 }
                int r2 = com.flurry.sdk.cn.a.e     // Catch:{ NoSuchFieldError -> 0x0011 }
                int r2 = r2 - r0
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0011 }
            L_0x0011:
                int[] r1 = a     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r2 = com.flurry.sdk.cn.a.f     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r2 = r2 - r0
                r3 = 2
                r1[r2] = r3     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                int[] r1 = a     // Catch:{ NoSuchFieldError -> 0x0021 }
                int r2 = com.flurry.sdk.cn.a.b     // Catch:{ NoSuchFieldError -> 0x0021 }
                int r2 = r2 - r0
                r0 = 3
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0021 }
            L_0x0021:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.ed.AnonymousClass8.<clinit>():void");
        }
    }

    private ed() {
        cw.a().a("com.flurry.android.sdk.ActivityLifecycleEvent", this.k);
        cw.a().a("com.flurry.android.sdk.FlurrySessionTimerEvent", this.j);
    }

    public static synchronized ed a() {
        ed edVar;
        synchronized (ed.class) {
            if (c == null) {
                c = new ed();
            }
            edVar = c;
        }
        return edVar;
    }

    public static synchronized void b() {
        synchronized (ed.class) {
            if (c != null) {
                cw.a().a((cv<?>) c.j);
                cw.a().a((cv<?>) c.k);
            }
            c = null;
        }
    }

    public final synchronized void a(Context context) {
        if (context instanceof Activity) {
            if (co.a().c()) {
                db.a(3, b, "bootstrap for context:".concat(String.valueOf(context)));
                e(context);
            }
        }
    }

    public final synchronized void b(Context context) {
        a(context, false, false);
    }

    public final synchronized void a(Context context, boolean z, boolean z2) {
        if (!co.a().c() || !(context instanceof Activity)) {
            if (z && z2) {
                this.h = z2;
            }
            db.a(3, b, "Manual onStartSession for context:".concat(String.valueOf(context)));
            a(context, z);
        }
    }

    /* access modifiers changed from: private */
    public synchronized void e(Context context) {
        a(context, false);
    }

    private synchronized void a(final Context context, boolean z) {
        eb ebVar;
        if (f() != null && f().a() && z) {
            if (!this.e.a()) {
                db.a(3, b, "A background session has already started. Not storing in context map because we use application context only.");
                return;
            }
            db.a(3, b, "Returning from a paused background session.");
        }
        if (f() == null || f().a() || !z) {
            boolean z2 = true;
            if (f() != null && f().a() && !z) {
                db.a(b, "New session started while background session is running.  Ending background session, then will create foreground session.");
                this.i.set(true);
                b(ck.a().a, true);
                ck.a().b(new Runnable() {
                    public final void run() {
                        ed.this.e(context);
                    }
                });
            } else if (this.d.get(context) == null) {
                this.e.b();
                final eb f2 = f();
                if (f2 == null) {
                    if (z) {
                        ebVar = new ea();
                    } else {
                        ebVar = new eb();
                    }
                    f2 = ebVar;
                    f2.a(eb.a.b);
                    db.e(b, "Flurry session started for context:".concat(String.valueOf(context)));
                    ec ecVar = new ec();
                    ecVar.a = new WeakReference<>(context);
                    ecVar.b = f2;
                    ecVar.d = ec.a.a;
                    ecVar.b();
                } else {
                    z2 = false;
                }
                this.d.put(context, f2);
                synchronized (this.f) {
                    this.g = f2;
                }
                this.i.set(false);
                db.e(b, "Flurry session resumed for context:".concat(String.valueOf(context)));
                ec ecVar2 = new ec();
                ecVar2.a = new WeakReference<>(context);
                ecVar2.b = f2;
                ecVar2.d = ec.a.b;
                ecVar2.b();
                if (z2) {
                    ck.a().b(new eo() {
                        public final void a() {
                            f2.a(eb.a.c);
                            ec ecVar = new ec();
                            ecVar.a = new WeakReference<>(context);
                            ecVar.b = f2;
                            ecVar.d = ec.a.e;
                            ecVar.b();
                        }
                    });
                }
                this.a = 0;
            } else if (co.a().c()) {
                db.a(3, b, "Session already started with context:".concat(String.valueOf(context)));
            } else {
                db.e(b, "Session already started with context:".concat(String.valueOf(context)));
            }
        } else {
            db.a(b, "A Flurry background session can't be started while a foreground session is running.");
        }
    }

    public final synchronized void a(final Context context, final String str) {
        for (Context b2 : new ArrayList(this.d.keySet())) {
            b(b2, true);
        }
        ck.a().b(new eo() {
            public final void a() {
                ed.this.e(context);
                bq.a();
                bq.a(str);
            }
        });
    }

    public final synchronized void c(Context context) {
        b(context, false, false);
    }

    public final synchronized void b(Context context, boolean z, boolean z2) {
        if (co.a().c() && (context instanceof Activity)) {
            return;
        }
        if (f() == null || f().a() || !z) {
            if (z) {
                if (this.h && !z2) {
                    return;
                }
            }
            db.a(3, b, "Manual onEndSession for context:".concat(String.valueOf(context)));
            d(context);
            return;
        }
        db.a(b, "No background session running, can't end session.");
    }

    /* access modifiers changed from: package-private */
    public final synchronized void d(Context context) {
        b(context, false);
    }

    private synchronized void b(Context context, boolean z) {
        eb remove = this.d.remove(context);
        if (z && f() != null && f().a() && this.e.a()) {
            h();
        } else if (remove != null) {
            db.e(b, "Flurry session paused for context:".concat(String.valueOf(context)));
            ec ecVar = new ec();
            ecVar.a = new WeakReference<>(context);
            ecVar.b = remove;
            bq.a();
            ecVar.e = bq.d();
            ecVar.d = ec.a.c;
            ecVar.b();
            if (i() == 0) {
                if (z) {
                    h();
                } else {
                    this.e.a(remove.b());
                }
                this.a = System.currentTimeMillis();
                return;
            }
            this.a = 0;
        } else if (co.a().c()) {
            db.a(3, b, "Session cannot be ended, session not found for context:".concat(String.valueOf(context)));
        } else {
            db.e(b, "Session cannot be ended, session not found for context:".concat(String.valueOf(context)));
        }
    }

    /* access modifiers changed from: private */
    public synchronized void h() {
        int i2 = i();
        if (i2 > 0) {
            db.a(5, b, "Session cannot be finalized, sessionContextCount:".concat(String.valueOf(i2)));
            return;
        }
        final eb f2 = f();
        if (f2 == null) {
            db.a(5, b, "Session cannot be finalized, current session not found");
            return;
        }
        String str = b;
        StringBuilder sb = new StringBuilder("Flurry ");
        sb.append(f2.a() ? "background" : "");
        sb.append(" session ended");
        db.e(str, sb.toString());
        ec ecVar = new ec();
        ecVar.b = f2;
        ecVar.d = ec.a.d;
        bq.a();
        ecVar.e = bq.d();
        ecVar.b();
        ck.a().b(new eo() {
            public final void a() {
                ed.a(ed.this, f2);
                boolean unused = ed.this.h = false;
            }
        });
    }

    public final synchronized void c() {
        for (Map.Entry next : this.d.entrySet()) {
            ec ecVar = new ec();
            ecVar.a = new WeakReference<>(next.getKey());
            ecVar.b = (eb) next.getValue();
            ecVar.d = ec.a.c;
            bq.a();
            ecVar.e = bq.d();
            ecVar.b();
        }
        this.d.clear();
        ck.a().b(new eo() {
            public final void a() {
                ed.this.h();
            }
        });
    }

    private synchronized int i() {
        return this.d.size();
    }

    public final synchronized boolean d() {
        if (f() != null) {
            return true;
        }
        db.a(2, b, "Session not found. No active session");
        return false;
    }

    public final synchronized int e() {
        if (this.i.get()) {
            return eb.a.b;
        }
        eb f2 = f();
        if (f2 == null) {
            db.a(2, b, "Session not found. No active session");
            return eb.a.a;
        }
        return f2.c();
    }

    public final eb f() {
        eb ebVar;
        synchronized (this.f) {
            ebVar = this.g;
        }
        return ebVar;
    }

    static /* synthetic */ void a(ed edVar, eb ebVar) {
        synchronized (edVar.f) {
            if (edVar.g == ebVar) {
                eb ebVar2 = edVar.g;
                eg.a().b("ContinueSessionMillis", ebVar2);
                ebVar2.a(eb.a.a);
                edVar.g = null;
            }
        }
    }
}
