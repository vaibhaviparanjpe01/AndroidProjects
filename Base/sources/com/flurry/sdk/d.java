package com.flurry.sdk;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Pair;
import com.flurry.android.FlurryConfigListener;
import com.flurry.sdk.b;
import com.flurry.sdk.g;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class d {
    /* access modifiers changed from: private */
    public static final String b = "d";
    private static volatile d c;
    private static final Object j = new Object();
    private static n o;
    public e a;
    private a d;
    private h e;
    /* access modifiers changed from: private */
    public m f;
    private s g;
    private final Map<FlurryConfigListener, Pair<j, WeakReference<Handler>>> h;
    /* access modifiers changed from: private */
    public final Map<j, Pair<Boolean, Boolean>> i;
    private volatile boolean k;
    /* access modifiers changed from: private */
    public volatile boolean l;
    /* access modifiers changed from: private */
    public volatile boolean m;
    /* access modifiers changed from: private */
    public a n;

    enum a {
        Complete("Complete", 3),
        CompleteNoChange("No Change", 2),
        Fail("Fail", 1),
        None("None", 0);
        
        int e;
        private String f;

        private a(String str, int i) {
            this.f = str;
            this.e = i;
        }

        public final String toString() {
            return this.f;
        }
    }

    public static synchronized d a() {
        d h2;
        synchronized (d.class) {
            h2 = h();
        }
        return h2;
    }

    private static synchronized d h() {
        d dVar;
        synchronized (d.class) {
            if (c == null) {
                c = new d((byte) 0);
            }
            dVar = c;
        }
        return dVar;
    }

    private d() {
        this((byte) 0);
    }

    private d(byte b2) {
        this.h = new ConcurrentHashMap();
        this.i = new HashMap();
        this.k = false;
        this.l = false;
        this.m = false;
        this.n = a.None;
        o = null;
        for (j put : j.a()) {
            Map<j, Pair<Boolean, Boolean>> map = this.i;
            Boolean bool = Boolean.FALSE;
            map.put(put, new Pair(bool, bool));
        }
        this.e = new h();
        this.f = new m();
        this.a = new e();
        this.g = new s();
        r.a((Runnable) new Runnable() {
            /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
                jadx.core.utils.exceptions.JadxOverflowException: 
                	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
                	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
                */
            public final void run() {
                /*
                    r8 = this;
                    r0 = 1
                    com.flurry.sdk.ck r1 = com.flurry.sdk.ck.a()     // Catch:{ Exception -> 0x00cc }
                    android.content.Context r1 = r1.a     // Catch:{ Exception -> 0x00cc }
                    java.lang.String r1 = com.flurry.sdk.t.b(r1)     // Catch:{ Exception -> 0x00cc }
                    java.lang.String r2 = com.flurry.sdk.d.b     // Catch:{ Exception -> 0x00cc }
                    java.lang.String r3 = "Cached Data: "
                    java.lang.String r4 = java.lang.String.valueOf(r1)     // Catch:{ Exception -> 0x00cc }
                    java.lang.String r3 = r3.concat(r4)     // Catch:{ Exception -> 0x00cc }
                    com.flurry.sdk.db.a(r2, r3)     // Catch:{ Exception -> 0x00cc }
                    if (r1 == 0) goto L_0x0086
                    com.flurry.sdk.d r2 = com.flurry.sdk.d.this     // Catch:{ Exception -> 0x00cc }
                    com.flurry.sdk.e r2 = r2.a     // Catch:{ Exception -> 0x00cc }
                    java.lang.String r2 = r2.d()     // Catch:{ Exception -> 0x00cc }
                    com.flurry.sdk.d r3 = com.flurry.sdk.d.this     // Catch:{ Exception -> 0x00cc }
                    com.flurry.sdk.e r3 = r3.a     // Catch:{ Exception -> 0x00cc }
                    android.content.SharedPreferences r4 = r3.b     // Catch:{ Exception -> 0x00cc }
                    r5 = 0
                    if (r4 == 0) goto L_0x003b
                    android.content.SharedPreferences r3 = r3.b     // Catch:{ Exception -> 0x00cc }
                    java.lang.String r4 = "lastRSA"
                    java.lang.String r5 = r3.getString(r4, r5)     // Catch:{ Exception -> 0x00cc }
                L_0x003b:
                    boolean r2 = com.flurry.sdk.t.a(r2, r1, r5)     // Catch:{ Exception -> 0x00cc }
                    if (r2 == 0) goto L_0x006b
                    com.flurry.sdk.d r2 = com.flurry.sdk.d.this     // Catch:{ Exception -> 0x00cc }
                    com.flurry.sdk.m r2 = r2.f     // Catch:{ Exception -> 0x00cc }
                    if (r1 == 0) goto L_0x005e
                    org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ Exception -> 0x0056 }
                    r3.<init>(r1)     // Catch:{ Exception -> 0x0056 }
                    java.util.List r1 = com.flurry.sdk.f.a(r3)     // Catch:{ Exception -> 0x0056 }
                    r2.a((java.util.List<com.flurry.sdk.l>) r1)     // Catch:{ Exception -> 0x0056 }
                    goto L_0x005e
                L_0x0056:
                    r1 = move-exception
                    java.lang.String r3 = com.flurry.sdk.m.a     // Catch:{ Exception -> 0x00cc }
                    java.lang.String r4 = "Cached variants parsing error: "
                    com.flurry.sdk.db.a((java.lang.String) r3, (java.lang.String) r4, (java.lang.Throwable) r1)     // Catch:{ Exception -> 0x00cc }
                L_0x005e:
                    com.flurry.sdk.n r1 = com.flurry.sdk.d.b()     // Catch:{ Exception -> 0x00cc }
                    if (r1 == 0) goto L_0x0086
                    com.flurry.sdk.d.b()     // Catch:{ Exception -> 0x00cc }
                    com.flurry.sdk.n.a(r2)     // Catch:{ Exception -> 0x00cc }
                    goto L_0x0086
                L_0x006b:
                    java.lang.String r1 = com.flurry.sdk.d.b     // Catch:{ Exception -> 0x00cc }
                    java.lang.String r2 = "Incorrect signature for cache."
                    com.flurry.sdk.db.b(r1, r2)     // Catch:{ Exception -> 0x00cc }
                    com.flurry.sdk.ck r1 = com.flurry.sdk.ck.a()     // Catch:{ Exception -> 0x00cc }
                    android.content.Context r1 = r1.a     // Catch:{ Exception -> 0x00cc }
                    com.flurry.sdk.t.c(r1)     // Catch:{ Exception -> 0x00cc }
                    com.flurry.sdk.d r1 = com.flurry.sdk.d.this     // Catch:{ Exception -> 0x00cc }
                    com.flurry.sdk.e r1 = r1.a     // Catch:{ Exception -> 0x00cc }
                    r1.c()     // Catch:{ Exception -> 0x00cc }
                L_0x0086:
                    com.flurry.sdk.d r1 = com.flurry.sdk.d.this
                    com.flurry.sdk.d.c(r1)
                    com.flurry.sdk.d r1 = com.flurry.sdk.d.this
                    com.flurry.sdk.m r1 = r1.f
                    int r1 = r1.e()
                    if (r1 <= 0) goto L_0x011a
                    com.flurry.sdk.d r1 = com.flurry.sdk.d.this
                    com.flurry.sdk.m r1 = r1.f
                    java.util.List r1 = r1.d()
                    java.util.Iterator r1 = r1.iterator()
                L_0x00a5:
                    boolean r2 = r1.hasNext()
                    if (r2 == 0) goto L_0x00c9
                    java.lang.Object r2 = r1.next()
                    com.flurry.sdk.j r2 = (com.flurry.sdk.j) r2
                    com.flurry.sdk.d r3 = com.flurry.sdk.d.this
                    java.util.Map r3 = r3.i
                    android.util.Pair r4 = new android.util.Pair
                    java.lang.Boolean r5 = java.lang.Boolean.TRUE
                    java.lang.Boolean r6 = java.lang.Boolean.FALSE
                    r4.<init>(r5, r6)
                    r3.put(r2, r4)
                    com.flurry.sdk.d r3 = com.flurry.sdk.d.this
                    r3.a((com.flurry.sdk.j) r2, (boolean) r0)
                    goto L_0x00a5
                L_0x00c9:
                    return
                L_0x00ca:
                    r1 = move-exception
                    goto L_0x011b
                L_0x00cc:
                    r1 = move-exception
                    java.lang.String r2 = com.flurry.sdk.d.b     // Catch:{ all -> 0x00ca }
                    java.lang.String r3 = "Exception!"
                    com.flurry.sdk.db.a((java.lang.String) r2, (java.lang.String) r3, (java.lang.Throwable) r1)     // Catch:{ all -> 0x00ca }
                    com.flurry.sdk.d r1 = com.flurry.sdk.d.this
                    com.flurry.sdk.d.c(r1)
                    com.flurry.sdk.d r1 = com.flurry.sdk.d.this
                    com.flurry.sdk.m r1 = r1.f
                    int r1 = r1.e()
                    if (r1 <= 0) goto L_0x011a
                    com.flurry.sdk.d r1 = com.flurry.sdk.d.this
                    com.flurry.sdk.m r1 = r1.f
                    java.util.List r1 = r1.d()
                    java.util.Iterator r1 = r1.iterator()
                L_0x00f5:
                    boolean r2 = r1.hasNext()
                    if (r2 == 0) goto L_0x0119
                    java.lang.Object r2 = r1.next()
                    com.flurry.sdk.j r2 = (com.flurry.sdk.j) r2
                    com.flurry.sdk.d r3 = com.flurry.sdk.d.this
                    java.util.Map r3 = r3.i
                    android.util.Pair r4 = new android.util.Pair
                    java.lang.Boolean r5 = java.lang.Boolean.TRUE
                    java.lang.Boolean r6 = java.lang.Boolean.FALSE
                    r4.<init>(r5, r6)
                    r3.put(r2, r4)
                    com.flurry.sdk.d r3 = com.flurry.sdk.d.this
                    r3.a((com.flurry.sdk.j) r2, (boolean) r0)
                    goto L_0x00f5
                L_0x0119:
                    return
                L_0x011a:
                    return
                L_0x011b:
                    com.flurry.sdk.d r2 = com.flurry.sdk.d.this
                    com.flurry.sdk.d.c(r2)
                    com.flurry.sdk.d r2 = com.flurry.sdk.d.this
                    com.flurry.sdk.m r2 = r2.f
                    int r2 = r2.e()
                    if (r2 <= 0) goto L_0x015e
                    com.flurry.sdk.d r2 = com.flurry.sdk.d.this
                    com.flurry.sdk.m r2 = r2.f
                    java.util.List r2 = r2.d()
                    java.util.Iterator r2 = r2.iterator()
                L_0x013a:
                    boolean r3 = r2.hasNext()
                    if (r3 == 0) goto L_0x015e
                    java.lang.Object r3 = r2.next()
                    com.flurry.sdk.j r3 = (com.flurry.sdk.j) r3
                    com.flurry.sdk.d r4 = com.flurry.sdk.d.this
                    java.util.Map r4 = r4.i
                    android.util.Pair r5 = new android.util.Pair
                    java.lang.Boolean r6 = java.lang.Boolean.TRUE
                    java.lang.Boolean r7 = java.lang.Boolean.FALSE
                    r5.<init>(r6, r7)
                    r4.put(r3, r5)
                    com.flurry.sdk.d r4 = com.flurry.sdk.d.this
                    r4.a((com.flurry.sdk.j) r3, (boolean) r0)
                    goto L_0x013a
                L_0x015e:
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.d.AnonymousClass1.run():void");
            }
        });
    }

    public static n b() {
        return o;
    }

    public final a c() {
        if (this.d == null) {
            i();
            this.d = new a(this.e, this.f);
        }
        return this.d;
    }

    private void i() {
        synchronized (j) {
            while (!this.k) {
                try {
                    j.wait();
                } catch (InterruptedException e2) {
                    db.a(b, "Interrupted Exception!", (Throwable) e2);
                }
            }
        }
    }

    public final void d() {
        if (this.l) {
            db.a(3, b, "Preventing re-entry...");
            return;
        }
        this.l = true;
        db.a(3, b, "Fetch started");
        new b(s.a(ck.a().a, "https://cfg.flurry.com/sdk/v1/config"), new b.a() {
            public final void a(g gVar, boolean z) {
                a aVar;
                if (!z) {
                    boolean unused = d.this.l = false;
                }
                if (gVar.d == g.a.SUCCEED) {
                    db.a(d.b, "Fetch succeeded.");
                    aVar = a.Complete;
                    boolean unused2 = d.this.m = true;
                    for (j next : j.a()) {
                        boolean z2 = false;
                        if (d.this.i.containsKey(next)) {
                            z2 = ((Boolean) ((Pair) d.this.i.get(next)).first).booleanValue();
                        }
                        d.this.i.put(next, new Pair(Boolean.valueOf(z2), Boolean.FALSE));
                    }
                } else if (gVar.d == g.a.NO_CHANGE) {
                    db.a(d.b, "Fetch finished.");
                    aVar = a.CompleteNoChange;
                } else {
                    db.a(d.b, "Error occured while fetching: ".concat(String.valueOf(gVar)));
                    aVar = a.Fail;
                }
                if (d.this.n.e <= aVar.e) {
                    a unused3 = d.this.n = aVar;
                }
                d.b(d.this, aVar);
            }
        }, this.a, this.f).a();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0080, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.flurry.android.FlurryConfigListener r5, com.flurry.sdk.j r6, android.os.Handler r7) {
        /*
            r4 = this;
            if (r5 != 0) goto L_0x0003
            return
        L_0x0003:
            java.util.Map<com.flurry.android.FlurryConfigListener, android.util.Pair<com.flurry.sdk.j, java.lang.ref.WeakReference<android.os.Handler>>> r0 = r4.h
            monitor-enter(r0)
            java.util.Map<com.flurry.android.FlurryConfigListener, android.util.Pair<com.flurry.sdk.j, java.lang.ref.WeakReference<android.os.Handler>>> r1 = r4.h     // Catch:{ all -> 0x0081 }
            boolean r1 = r1.containsKey(r5)     // Catch:{ all -> 0x0081 }
            if (r1 == 0) goto L_0x0017
            java.lang.String r5 = b     // Catch:{ all -> 0x0081 }
            java.lang.String r6 = "The listener is already registered"
            com.flurry.sdk.db.e(r5, r6)     // Catch:{ all -> 0x0081 }
            monitor-exit(r0)     // Catch:{ all -> 0x0081 }
            return
        L_0x0017:
            java.util.Map<com.flurry.android.FlurryConfigListener, android.util.Pair<com.flurry.sdk.j, java.lang.ref.WeakReference<android.os.Handler>>> r1 = r4.h     // Catch:{ all -> 0x0081 }
            android.util.Pair r2 = new android.util.Pair     // Catch:{ all -> 0x0081 }
            java.lang.ref.WeakReference r3 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x0081 }
            r3.<init>(r7)     // Catch:{ all -> 0x0081 }
            r2.<init>(r6, r3)     // Catch:{ all -> 0x0081 }
            r1.put(r5, r2)     // Catch:{ all -> 0x0081 }
            int[] r7 = com.flurry.sdk.d.AnonymousClass5.a     // Catch:{ all -> 0x0081 }
            com.flurry.sdk.d$a r1 = r4.n     // Catch:{ all -> 0x0081 }
            int r1 = r1.ordinal()     // Catch:{ all -> 0x0081 }
            r7 = r7[r1]     // Catch:{ all -> 0x0081 }
            switch(r7) {
                case 1: goto L_0x0041;
                case 2: goto L_0x003e;
                case 3: goto L_0x003a;
                case 4: goto L_0x0034;
                default: goto L_0x0033;
            }     // Catch:{ all -> 0x0081 }
        L_0x0033:
            goto L_0x0041
        L_0x0034:
            boolean r7 = r4.l     // Catch:{ all -> 0x0081 }
            r5.onFetchError(r7)     // Catch:{ all -> 0x0081 }
            goto L_0x0041
        L_0x003a:
            r5.onFetchNoChange()     // Catch:{ all -> 0x0081 }
            goto L_0x0041
        L_0x003e:
            r5.onFetchSuccess()     // Catch:{ all -> 0x0081 }
        L_0x0041:
            java.util.Map<com.flurry.sdk.j, android.util.Pair<java.lang.Boolean, java.lang.Boolean>> r7 = r4.i     // Catch:{ all -> 0x0081 }
            boolean r7 = r7.containsKey(r6)     // Catch:{ all -> 0x0081 }
            if (r7 == 0) goto L_0x0073
            java.util.Map<com.flurry.sdk.j, android.util.Pair<java.lang.Boolean, java.lang.Boolean>> r7 = r4.i     // Catch:{ all -> 0x0081 }
            java.lang.Object r6 = r7.get(r6)     // Catch:{ all -> 0x0081 }
            android.util.Pair r6 = (android.util.Pair) r6     // Catch:{ all -> 0x0081 }
            java.lang.Object r7 = r6.first     // Catch:{ all -> 0x0081 }
            java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch:{ all -> 0x0081 }
            boolean r7 = r7.booleanValue()     // Catch:{ all -> 0x0081 }
            if (r7 != 0) goto L_0x0065
            java.lang.Object r7 = r6.second     // Catch:{ all -> 0x0081 }
            java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch:{ all -> 0x0081 }
            boolean r7 = r7.booleanValue()     // Catch:{ all -> 0x0081 }
            if (r7 == 0) goto L_0x007f
        L_0x0065:
            java.lang.Object r6 = r6.second     // Catch:{ all -> 0x0081 }
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch:{ all -> 0x0081 }
            boolean r6 = r6.booleanValue()     // Catch:{ all -> 0x0081 }
            r6 = r6 ^ 1
            r5.onActivateComplete(r6)     // Catch:{ all -> 0x0081 }
            goto L_0x007f
        L_0x0073:
            java.util.Map<com.flurry.sdk.j, android.util.Pair<java.lang.Boolean, java.lang.Boolean>> r5 = r4.i     // Catch:{ all -> 0x0081 }
            android.util.Pair r7 = new android.util.Pair     // Catch:{ all -> 0x0081 }
            java.lang.Boolean r1 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x0081 }
            r7.<init>(r1, r1)     // Catch:{ all -> 0x0081 }
            r5.put(r6, r7)     // Catch:{ all -> 0x0081 }
        L_0x007f:
            monitor-exit(r0)     // Catch:{ all -> 0x0081 }
            return
        L_0x0081:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0081 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.d.a(com.flurry.android.FlurryConfigListener, com.flurry.sdk.j, android.os.Handler):void");
    }

    public final void a(FlurryConfigListener flurryConfigListener) {
        if (flurryConfigListener != null) {
            synchronized (this.h) {
                this.h.remove(flurryConfigListener);
            }
        }
    }

    public final void a(j jVar, final boolean z) {
        synchronized (this.h) {
            for (Map.Entry next : this.h.entrySet()) {
                if (jVar == null || jVar == ((Pair) next.getValue()).first) {
                    final FlurryConfigListener flurryConfigListener = (FlurryConfigListener) next.getKey();
                    Handler handler = (Handler) ((WeakReference) ((Pair) next.getValue()).second).get();
                    AnonymousClass4 r4 = new eo() {
                        public final void a() {
                            flurryConfigListener.onActivateComplete(z);
                        }
                    };
                    if (handler == null) {
                        ck.a().a((Runnable) r4);
                    } else {
                        handler.post(r4);
                    }
                }
            }
        }
    }

    public final synchronized void e() {
        t.c(ck.a().a);
        if (this.f != null) {
            this.f.a();
        }
        this.a.c();
        this.l = false;
        this.n = a.None;
        this.m = false;
        for (j put : j.a()) {
            Map<j, Pair<Boolean, Boolean>> map = this.i;
            Boolean bool = Boolean.FALSE;
            map.put(put, new Pair(bool, bool));
        }
    }

    public final List<l> f() {
        if (this.f != null) {
            return this.f.b();
        }
        return null;
    }

    public final boolean a(j jVar) {
        boolean z;
        if (!this.m) {
            return false;
        }
        boolean z2 = true;
        if (jVar == null) {
            boolean z3 = false;
            for (Map.Entry next : this.i.entrySet()) {
                Pair pair = (Pair) next.getValue();
                if (!((Boolean) pair.second).booleanValue()) {
                    next.setValue(new Pair(pair.first, Boolean.TRUE));
                    z3 = true;
                }
            }
            z2 = z3;
        } else {
            Pair pair2 = this.i.get(jVar);
            if (pair2 == null || !((Boolean) pair2.second).booleanValue()) {
                Map<j, Pair<Boolean, Boolean>> map = this.i;
                if (pair2 == null) {
                    z = false;
                } else {
                    z = ((Boolean) pair2.first).booleanValue();
                }
                map.put(jVar, new Pair(Boolean.valueOf(z), Boolean.TRUE));
            } else {
                z2 = false;
            }
        }
        if (!z2) {
            return z2;
        }
        this.f.a(jVar);
        a(jVar, false);
        return z2;
    }

    public String toString() {
        i();
        ArrayList arrayList = new ArrayList();
        List<l> f2 = f();
        if (f2 == null || f2.isEmpty()) {
            return "No variants were found!";
        }
        for (l lVar : f2) {
            arrayList.add(lVar.toString());
        }
        return TextUtils.join(",", arrayList);
    }

    static /* synthetic */ void c(d dVar) {
        synchronized (j) {
            dVar.k = true;
            j.notifyAll();
        }
    }

    static /* synthetic */ void b(d dVar, final a aVar) {
        synchronized (dVar.h) {
            for (Map.Entry next : dVar.h.entrySet()) {
                final FlurryConfigListener flurryConfigListener = (FlurryConfigListener) next.getKey();
                Handler handler = (Handler) ((WeakReference) ((Pair) next.getValue()).second).get();
                AnonymousClass3 r4 = new eo() {
                    public final void a() {
                        switch (AnonymousClass5.a[aVar.ordinal()]) {
                            case 2:
                                flurryConfigListener.onFetchSuccess();
                                return;
                            case 3:
                                flurryConfigListener.onFetchNoChange();
                                return;
                            case 4:
                                flurryConfigListener.onFetchError(d.this.l);
                                return;
                            default:
                                return;
                        }
                    }
                };
                if (handler == null) {
                    ck.a().a((Runnable) r4);
                } else {
                    handler.post(r4);
                }
            }
        }
    }
}
