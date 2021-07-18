package com.flurry.sdk;

import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.text.TextUtils;
import com.flurry.sdk.aq;
import com.flurry.sdk.az;
import com.flurry.sdk.eh;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class as implements eh.a {
    /* access modifiers changed from: private */
    public static final String e = "as";
    public final Runnable a = new eo() {
        public final void a() {
            as.this.e();
        }
    };
    public final cv<bt> b = new cv<bt>() {
        public final /* bridge */ /* synthetic */ void a(cu cuVar) {
            as.this.e();
        }
    };
    public final cv<bu> c = new cv<bu>() {
        public final /* bridge */ /* synthetic */ void a(cu cuVar) {
            as.this.e();
        }
    };
    public final cv<bx> d = new cv<bx>() {
        public final /* bridge */ /* synthetic */ void a(cu cuVar) {
            if (((bx) cuVar).a) {
                as.this.e();
            }
        }
    };
    private final dg<ae> f = new dg<>("proton config request", new be());
    /* access modifiers changed from: private */
    public final dg<af> g = new dg<>("proton config response", new bf());
    private final ar h = new ar();
    private final cq<String, ai> i = new cq<>();
    private final List<az> j = new ArrayList();
    private ct<aq> k;
    private ct<List<az>> l;
    private boolean m;
    private String n;
    private boolean o = true;
    private boolean p;
    /* access modifiers changed from: private */
    public long q = 10000;
    /* access modifiers changed from: private */
    public long r;
    /* access modifiers changed from: private */
    public boolean s;
    /* access modifiers changed from: private */
    public af t;
    /* access modifiers changed from: private */
    public boolean u;

    public as() {
        eg a2 = eg.a();
        this.m = ((Boolean) a2.a("ProtonEnabled")).booleanValue();
        a2.a("ProtonEnabled", (eh.a) this);
        String str = e;
        db.a(4, str, "initSettings, protonEnabled = " + this.m);
        this.n = (String) a2.a("ProtonConfigUrl");
        a2.a("ProtonConfigUrl", (eh.a) this);
        String str2 = e;
        db.a(4, str2, "initSettings, protonConfigUrl = " + this.n);
        this.o = ((Boolean) a2.a("analyticsEnabled")).booleanValue();
        a2.a("analyticsEnabled", (eh.a) this);
        String str3 = e;
        db.a(4, str3, "initSettings, AnalyticsEnabled = " + this.o);
        cw.a().a("com.flurry.android.sdk.IdProviderFinishedEvent", this.b);
        cw.a().a("com.flurry.android.sdk.IdProviderUpdatedAdvertisingId", this.c);
        cw.a().a("com.flurry.android.sdk.NetworkStateEvent", this.d);
        Context context = ck.a().a;
        this.k = new ct<>(context.getFileStreamPath(".yflurryprotonconfig." + Long.toString(em.g(ck.a().b), 16)), ".yflurryprotonconfig.", 1, new dz<aq>() {
            public final dw<aq> a(int i) {
                return new aq.a();
            }
        });
        this.l = new ct<>(context.getFileStreamPath(".yflurryprotonreport." + Long.toString(em.g(ck.a().b), 16)), ".yflurryprotonreport.", 1, new dz<List<az>>() {
            public final dw<List<az>> a(int i) {
                return new dv(new az.a());
            }
        });
        ck.a().b(new eo() {
            public final void a() {
                as.this.l();
            }
        });
        ck.a().b(new eo() {
            public final void a() {
                as.this.m();
            }
        });
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0078  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.lang.String r3, java.lang.Object r4) {
        /*
            r2 = this;
            int r0 = r3.hashCode()
            r1 = -1720015653(0xffffffff997aa4db, float:-1.2957989E-23)
            if (r0 == r1) goto L_0x0028
            r1 = 640941243(0x2633fcbb, float:6.2445614E-16)
            if (r0 == r1) goto L_0x001e
            r1 = 1591403975(0x5edae5c7, float:7.886616E18)
            if (r0 == r1) goto L_0x0014
            goto L_0x0032
        L_0x0014:
            java.lang.String r0 = "ProtonConfigUrl"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0032
            r3 = 1
            goto L_0x0033
        L_0x001e:
            java.lang.String r0 = "ProtonEnabled"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0032
            r3 = 0
            goto L_0x0033
        L_0x0028:
            java.lang.String r0 = "analyticsEnabled"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0032
            r3 = 2
            goto L_0x0033
        L_0x0032:
            r3 = -1
        L_0x0033:
            r0 = 4
            switch(r3) {
                case 0: goto L_0x0078;
                case 1: goto L_0x005e;
                case 2: goto L_0x0040;
                default: goto L_0x0037;
            }
        L_0x0037:
            r3 = 6
            java.lang.String r4 = e
            java.lang.String r0 = "onSettingUpdate internal error!"
            com.flurry.sdk.db.a((int) r3, (java.lang.String) r4, (java.lang.String) r0)
            return
        L_0x0040:
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r3 = r4.booleanValue()
            r2.o = r3
            java.lang.String r3 = e
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r1 = "onSettingUpdate, AnalyticsEnabled = "
            r4.<init>(r1)
            boolean r1 = r2.o
            r4.append(r1)
            java.lang.String r4 = r4.toString()
            com.flurry.sdk.db.a((int) r0, (java.lang.String) r3, (java.lang.String) r4)
            return
        L_0x005e:
            java.lang.String r4 = (java.lang.String) r4
            r2.n = r4
            java.lang.String r3 = e
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r1 = "onSettingUpdate, protonConfigUrl = "
            r4.<init>(r1)
            java.lang.String r1 = r2.n
            r4.append(r1)
            java.lang.String r4 = r4.toString()
            com.flurry.sdk.db.a((int) r0, (java.lang.String) r3, (java.lang.String) r4)
            return
        L_0x0078:
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r3 = r4.booleanValue()
            r2.m = r3
            java.lang.String r3 = e
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r1 = "onSettingUpdate, protonEnabled = "
            r4.<init>(r1)
            boolean r1 = r2.m
            r4.append(r1)
            java.lang.String r4 = r4.toString()
            com.flurry.sdk.db.a((int) r0, (java.lang.String) r3, (java.lang.String) r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.as.a(java.lang.String, java.lang.Object):void");
    }

    public final synchronized void a() {
        if (this.m) {
            em.a();
            bq.a();
            au.a = bq.d();
            this.u = false;
            e();
        }
    }

    public final synchronized void b() {
        if (this.m) {
            em.a();
            bq.a();
            b(bq.d());
            i();
        }
    }

    public final synchronized void a(long j2) {
        if (this.m) {
            em.a();
            b(j2);
            b("flurry.session_end", (Map<String, String>) null);
            ck.a().b(new eo() {
                public final void a() {
                    as.this.k();
                }
            });
        }
    }

    public final synchronized void c() {
        if (this.m) {
            em.a();
            i();
        }
    }

    public final synchronized void a(String str, Map<String, String> map) {
        if (this.m) {
            em.a();
            b(str, map);
        }
    }

    /* JADX WARNING: type inference failed for: r3v3, types: [byte[], RequestObjectType] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0064, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void e() {
        /*
            r15 = this;
            monitor-enter(r15)
            boolean r0 = r15.m     // Catch:{ all -> 0x00f2 }
            if (r0 != 0) goto L_0x0007
            monitor-exit(r15)
            return
        L_0x0007:
            com.flurry.sdk.em.a()     // Catch:{ all -> 0x00f2 }
            boolean r0 = r15.p     // Catch:{ all -> 0x00f2 }
            if (r0 != 0) goto L_0x0010
            monitor-exit(r15)
            return
        L_0x0010:
            com.flurry.sdk.bs r0 = com.flurry.sdk.bs.a()     // Catch:{ all -> 0x00f2 }
            boolean r0 = r0.c()     // Catch:{ all -> 0x00f2 }
            if (r0 != 0) goto L_0x001c
            monitor-exit(r15)
            return
        L_0x001c:
            long r0 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00f2 }
            com.flurry.sdk.bs r2 = com.flurry.sdk.bs.a()     // Catch:{ all -> 0x00f2 }
            boolean r2 = r2.f()     // Catch:{ all -> 0x00f2 }
            r3 = 1
            r2 = r2 ^ r3
            com.flurry.sdk.af r4 = r15.t     // Catch:{ all -> 0x00f2 }
            r5 = 3
            if (r4 == 0) goto L_0x0085
            boolean r4 = r15.s     // Catch:{ all -> 0x00f2 }
            r6 = 0
            if (r4 == r2) goto L_0x003e
            java.lang.String r3 = e     // Catch:{ all -> 0x00f2 }
            java.lang.String r4 = "Limit ad tracking value has changed, purging"
            com.flurry.sdk.db.a((int) r5, (java.lang.String) r3, (java.lang.String) r4)     // Catch:{ all -> 0x00f2 }
            r15.t = r6     // Catch:{ all -> 0x00f2 }
            goto L_0x0085
        L_0x003e:
            long r7 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00f2 }
            long r9 = r15.r     // Catch:{ all -> 0x00f2 }
            com.flurry.sdk.af r4 = r15.t     // Catch:{ all -> 0x00f2 }
            long r11 = r4.b     // Catch:{ all -> 0x00f2 }
            r13 = 1000(0x3e8, double:4.94E-321)
            long r11 = r11 * r13
            long r9 = r9 + r11
            int r4 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r4 >= 0) goto L_0x0065
            java.lang.String r0 = e     // Catch:{ all -> 0x00f2 }
            java.lang.String r1 = "Cached Proton config valid, no need to refresh"
            com.flurry.sdk.db.a((int) r5, (java.lang.String) r0, (java.lang.String) r1)     // Catch:{ all -> 0x00f2 }
            boolean r0 = r15.u     // Catch:{ all -> 0x00f2 }
            if (r0 != 0) goto L_0x0063
            r15.u = r3     // Catch:{ all -> 0x00f2 }
            java.lang.String r0 = "flurry.session_start"
            r15.b((java.lang.String) r0, (java.util.Map<java.lang.String, java.lang.String>) r6)     // Catch:{ all -> 0x00f2 }
        L_0x0063:
            monitor-exit(r15)
            return
        L_0x0065:
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00f2 }
            long r7 = r15.r     // Catch:{ all -> 0x00f2 }
            com.flurry.sdk.af r9 = r15.t     // Catch:{ all -> 0x00f2 }
            long r9 = r9.c     // Catch:{ all -> 0x00f2 }
            long r9 = r9 * r13
            r11 = 0
            long r7 = r7 + r9
            int r9 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r9 < 0) goto L_0x0085
            java.lang.String r3 = e     // Catch:{ all -> 0x00f2 }
            java.lang.String r4 = "Cached Proton config expired, purging"
            com.flurry.sdk.db.a((int) r5, (java.lang.String) r3, (java.lang.String) r4)     // Catch:{ all -> 0x00f2 }
            r15.t = r6     // Catch:{ all -> 0x00f2 }
            com.flurry.sdk.cq<java.lang.String, com.flurry.sdk.ai> r3 = r15.i     // Catch:{ all -> 0x00f2 }
            r3.a()     // Catch:{ all -> 0x00f2 }
        L_0x0085:
            com.flurry.sdk.cg r3 = com.flurry.sdk.cg.a()     // Catch:{ all -> 0x00f2 }
            r3.a((java.lang.Object) r15)     // Catch:{ all -> 0x00f2 }
            java.lang.String r3 = e     // Catch:{ all -> 0x00f2 }
            java.lang.String r4 = "Requesting proton config"
            com.flurry.sdk.db.a((int) r5, (java.lang.String) r3, (java.lang.String) r4)     // Catch:{ all -> 0x00f2 }
            byte[] r3 = r15.f()     // Catch:{ all -> 0x00f2 }
            if (r3 != 0) goto L_0x009b
            monitor-exit(r15)
            return
        L_0x009b:
            com.flurry.sdk.di r4 = new com.flurry.sdk.di     // Catch:{ all -> 0x00f2 }
            r4.<init>()     // Catch:{ all -> 0x00f2 }
            java.lang.String r5 = r15.n     // Catch:{ all -> 0x00f2 }
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x00f2 }
            if (r5 == 0) goto L_0x00ab
            java.lang.String r5 = "https://proton.flurry.com/sdk/v1/config"
            goto L_0x00ad
        L_0x00ab:
            java.lang.String r5 = r15.n     // Catch:{ all -> 0x00f2 }
        L_0x00ad:
            r4.g = r5     // Catch:{ all -> 0x00f2 }
            r5 = 5000(0x1388, float:7.006E-42)
            r4.u = r5     // Catch:{ all -> 0x00f2 }
            com.flurry.sdk.dk$a r5 = com.flurry.sdk.dk.a.kPost     // Catch:{ all -> 0x00f2 }
            r4.h = r5     // Catch:{ all -> 0x00f2 }
            java.lang.String r5 = "application/x-flurry;version=2"
            java.lang.String r6 = "application/x-flurry;version=2"
            int r7 = com.flurry.sdk.dg.a(r3)     // Catch:{ all -> 0x00f2 }
            java.lang.String r7 = java.lang.Integer.toString(r7)     // Catch:{ all -> 0x00f2 }
            java.lang.String r8 = "Content-Type"
            r4.a(r8, r5)     // Catch:{ all -> 0x00f2 }
            java.lang.String r5 = "Accept"
            r4.a(r5, r6)     // Catch:{ all -> 0x00f2 }
            java.lang.String r5 = "FM-Checksum"
            r4.a(r5, r7)     // Catch:{ all -> 0x00f2 }
            com.flurry.sdk.ds r5 = new com.flurry.sdk.ds     // Catch:{ all -> 0x00f2 }
            r5.<init>()     // Catch:{ all -> 0x00f2 }
            r4.c = r5     // Catch:{ all -> 0x00f2 }
            com.flurry.sdk.ds r5 = new com.flurry.sdk.ds     // Catch:{ all -> 0x00f2 }
            r5.<init>()     // Catch:{ all -> 0x00f2 }
            r4.d = r5     // Catch:{ all -> 0x00f2 }
            r4.b = r3     // Catch:{ all -> 0x00f2 }
            com.flurry.sdk.as$2 r3 = new com.flurry.sdk.as$2     // Catch:{ all -> 0x00f2 }
            r3.<init>(r0, r2)     // Catch:{ all -> 0x00f2 }
            r4.a = r3     // Catch:{ all -> 0x00f2 }
            com.flurry.sdk.cg r0 = com.flurry.sdk.cg.a()     // Catch:{ all -> 0x00f2 }
            r0.a((java.lang.Object) r15, r4)     // Catch:{ all -> 0x00f2 }
            monitor-exit(r15)
            return
        L_0x00f2:
            r0 = move-exception
            monitor-exit(r15)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.as.e():void");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0216, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0101  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void b(java.lang.String r34, java.util.Map<java.lang.String, java.lang.String> r35) {
        /*
            r33 = this;
            r1 = r33
            r0 = r34
            r2 = r35
            monitor-enter(r33)
            java.lang.String r3 = e     // Catch:{ all -> 0x0217 }
            java.lang.String r4 = "Event triggered: "
            java.lang.String r5 = java.lang.String.valueOf(r34)     // Catch:{ all -> 0x0217 }
            java.lang.String r4 = r4.concat(r5)     // Catch:{ all -> 0x0217 }
            r11 = 3
            com.flurry.sdk.db.a((int) r11, (java.lang.String) r3, (java.lang.String) r4)     // Catch:{ all -> 0x0217 }
            boolean r3 = r1.o     // Catch:{ all -> 0x0217 }
            if (r3 != 0) goto L_0x0024
            java.lang.String r0 = e     // Catch:{ all -> 0x0217 }
            java.lang.String r2 = "Analytics and pulse have been disabled."
            com.flurry.sdk.db.e(r0, r2)     // Catch:{ all -> 0x0217 }
            monitor-exit(r33)
            return
        L_0x0024:
            com.flurry.sdk.af r3 = r1.t     // Catch:{ all -> 0x0217 }
            if (r3 != 0) goto L_0x0031
            java.lang.String r0 = e     // Catch:{ all -> 0x0217 }
            java.lang.String r2 = "Config response is empty. No events to fire."
            com.flurry.sdk.db.a((int) r11, (java.lang.String) r0, (java.lang.String) r2)     // Catch:{ all -> 0x0217 }
            monitor-exit(r33)
            return
        L_0x0031:
            com.flurry.sdk.em.a()     // Catch:{ all -> 0x0217 }
            boolean r3 = android.text.TextUtils.isEmpty(r34)     // Catch:{ all -> 0x0217 }
            if (r3 == 0) goto L_0x003c
            monitor-exit(r33)
            return
        L_0x003c:
            com.flurry.sdk.cq<java.lang.String, com.flurry.sdk.ai> r3 = r1.i     // Catch:{ all -> 0x0217 }
            java.util.List r3 = r3.a(r0)     // Catch:{ all -> 0x0217 }
            if (r3 != 0) goto L_0x004d
            java.lang.String r0 = e     // Catch:{ all -> 0x0217 }
            java.lang.String r2 = "No events to fire. Returning."
            com.flurry.sdk.db.a((int) r11, (java.lang.String) r0, (java.lang.String) r2)     // Catch:{ all -> 0x0217 }
            monitor-exit(r33)
            return
        L_0x004d:
            int r4 = r3.size()     // Catch:{ all -> 0x0217 }
            if (r4 != 0) goto L_0x005c
            java.lang.String r0 = e     // Catch:{ all -> 0x0217 }
            java.lang.String r2 = "No events to fire. Returning."
            com.flurry.sdk.db.a((int) r11, (java.lang.String) r0, (java.lang.String) r2)     // Catch:{ all -> 0x0217 }
            monitor-exit(r33)
            return
        L_0x005c:
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0217 }
            if (r2 == 0) goto L_0x0064
            r8 = 1
            goto L_0x0065
        L_0x0064:
            r8 = 0
        L_0x0065:
            r9 = -1
            int r10 = r34.hashCode()     // Catch:{ all -> 0x0217 }
            r12 = 645204782(0x26750b2e, float:8.50166E-16)
            if (r10 == r12) goto L_0x008e
            r12 = 1371447545(0x51bea0f9, float:1.02343057E11)
            if (r10 == r12) goto L_0x0084
            r12 = 1579613685(0x5e26fdf5, float:3.00826079E18)
            if (r10 == r12) goto L_0x007a
            goto L_0x0097
        L_0x007a:
            java.lang.String r10 = "flurry.session_start"
            boolean r10 = r0.equals(r10)     // Catch:{ all -> 0x0217 }
            if (r10 == 0) goto L_0x0097
            r9 = 0
            goto L_0x0097
        L_0x0084:
            java.lang.String r10 = "flurry.app_install"
            boolean r10 = r0.equals(r10)     // Catch:{ all -> 0x0217 }
            if (r10 == 0) goto L_0x0097
            r9 = 2
            goto L_0x0097
        L_0x008e:
            java.lang.String r10 = "flurry.session_end"
            boolean r10 = r0.equals(r10)     // Catch:{ all -> 0x0217 }
            if (r10 == 0) goto L_0x0097
            r9 = 1
        L_0x0097:
            switch(r9) {
                case 0: goto L_0x00a3;
                case 1: goto L_0x00a0;
                case 2: goto L_0x009d;
                default: goto L_0x009a;
            }     // Catch:{ all -> 0x0217 }
        L_0x009a:
            com.flurry.sdk.bd r9 = com.flurry.sdk.bd.APPLICATION_EVENT     // Catch:{ all -> 0x0217 }
            goto L_0x00a5
        L_0x009d:
            com.flurry.sdk.bd r9 = com.flurry.sdk.bd.INSTALL     // Catch:{ all -> 0x0217 }
            goto L_0x00a5
        L_0x00a0:
            com.flurry.sdk.bd r9 = com.flurry.sdk.bd.SESSION_END     // Catch:{ all -> 0x0217 }
            goto L_0x00a5
        L_0x00a3:
            com.flurry.sdk.bd r9 = com.flurry.sdk.bd.SESSION_START     // Catch:{ all -> 0x0217 }
        L_0x00a5:
            java.util.HashMap r10 = new java.util.HashMap     // Catch:{ all -> 0x0217 }
            r10.<init>()     // Catch:{ all -> 0x0217 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x0217 }
        L_0x00ae:
            boolean r12 = r3.hasNext()     // Catch:{ all -> 0x0217 }
            if (r12 == 0) goto L_0x01c1
            java.lang.Object r12 = r3.next()     // Catch:{ all -> 0x0217 }
            com.flurry.sdk.ai r12 = (com.flurry.sdk.ai) r12     // Catch:{ all -> 0x0217 }
            boolean r13 = r12 instanceof com.flurry.sdk.aj     // Catch:{ all -> 0x0217 }
            if (r13 == 0) goto L_0x0126
            java.lang.String r13 = e     // Catch:{ all -> 0x0217 }
            java.lang.String r14 = "Event contains triggers."
            r15 = 4
            com.flurry.sdk.db.a((int) r15, (java.lang.String) r13, (java.lang.String) r14)     // Catch:{ all -> 0x0217 }
            r13 = r12
            com.flurry.sdk.aj r13 = (com.flurry.sdk.aj) r13     // Catch:{ all -> 0x0217 }
            java.lang.String[] r13 = r13.d     // Catch:{ all -> 0x0217 }
            if (r13 != 0) goto L_0x00d6
            java.lang.String r14 = e     // Catch:{ all -> 0x0217 }
            java.lang.String r6 = "Template does not contain trigger values. Firing."
            com.flurry.sdk.db.a((int) r15, (java.lang.String) r14, (java.lang.String) r6)     // Catch:{ all -> 0x0217 }
        L_0x00d4:
            r6 = 1
            goto L_0x00ec
        L_0x00d6:
            int r6 = r13.length     // Catch:{ all -> 0x0217 }
            if (r6 != 0) goto L_0x00e1
            java.lang.String r6 = e     // Catch:{ all -> 0x0217 }
            java.lang.String r14 = "Template does not contain trigger values. Firing."
            com.flurry.sdk.db.a((int) r15, (java.lang.String) r6, (java.lang.String) r14)     // Catch:{ all -> 0x0217 }
            goto L_0x00d4
        L_0x00e1:
            if (r2 != 0) goto L_0x00eb
            java.lang.String r6 = e     // Catch:{ all -> 0x0217 }
            java.lang.String r12 = "Publisher has not passed in params list. Not firing."
            com.flurry.sdk.db.a((int) r15, (java.lang.String) r6, (java.lang.String) r12)     // Catch:{ all -> 0x0217 }
            goto L_0x00ae
        L_0x00eb:
            r6 = 0
        L_0x00ec:
            r14 = r12
            com.flurry.sdk.aj r14 = (com.flurry.sdk.aj) r14     // Catch:{ all -> 0x0217 }
            java.lang.String r14 = r14.c     // Catch:{ all -> 0x0217 }
            java.lang.Object r14 = r2.get(r14)     // Catch:{ all -> 0x0217 }
            java.lang.String r14 = (java.lang.String) r14     // Catch:{ all -> 0x0217 }
            if (r14 != 0) goto L_0x0101
            java.lang.String r6 = e     // Catch:{ all -> 0x0217 }
            java.lang.String r12 = "Publisher params has no value associated with proton key. Not firing."
            com.flurry.sdk.db.a((int) r15, (java.lang.String) r6, (java.lang.String) r12)     // Catch:{ all -> 0x0217 }
            goto L_0x00ae
        L_0x0101:
            int r7 = r13.length     // Catch:{ all -> 0x0217 }
            r11 = 0
        L_0x0103:
            if (r11 >= r7) goto L_0x0113
            r15 = r13[r11]     // Catch:{ all -> 0x0217 }
            boolean r15 = r15.equals(r14)     // Catch:{ all -> 0x0217 }
            if (r15 == 0) goto L_0x010f
            r6 = 1
            goto L_0x0113
        L_0x010f:
            int r11 = r11 + 1
            r15 = 4
            goto L_0x0103
        L_0x0113:
            if (r6 != 0) goto L_0x011e
            java.lang.String r6 = e     // Catch:{ all -> 0x0217 }
            java.lang.String r7 = "Publisher params list does not match proton param values. Not firing."
            r11 = 4
            com.flurry.sdk.db.a((int) r11, (java.lang.String) r6, (java.lang.String) r7)     // Catch:{ all -> 0x0217 }
            goto L_0x0132
        L_0x011e:
            java.lang.String r6 = e     // Catch:{ all -> 0x0217 }
            java.lang.String r7 = "Publisher params match proton values. Firing."
            r11 = 4
            com.flurry.sdk.db.a((int) r11, (java.lang.String) r6, (java.lang.String) r7)     // Catch:{ all -> 0x0217 }
        L_0x0126:
            com.flurry.sdk.ac r6 = r12.b     // Catch:{ all -> 0x0217 }
            if (r6 != 0) goto L_0x0135
            java.lang.String r6 = e     // Catch:{ all -> 0x0217 }
            java.lang.String r7 = "Template is empty. Not firing current event."
            r11 = 3
            com.flurry.sdk.db.a((int) r11, (java.lang.String) r6, (java.lang.String) r7)     // Catch:{ all -> 0x0217 }
        L_0x0132:
            r11 = 3
            goto L_0x00ae
        L_0x0135:
            java.lang.String r7 = e     // Catch:{ all -> 0x0217 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0217 }
            java.lang.String r12 = "Creating callback report for partner: "
            r11.<init>(r12)     // Catch:{ all -> 0x0217 }
            java.lang.String r12 = r6.b     // Catch:{ all -> 0x0217 }
            r11.append(r12)     // Catch:{ all -> 0x0217 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0217 }
            r12 = 3
            com.flurry.sdk.db.a((int) r12, (java.lang.String) r7, (java.lang.String) r11)     // Catch:{ all -> 0x0217 }
            java.util.HashMap r7 = new java.util.HashMap     // Catch:{ all -> 0x0217 }
            r7.<init>()     // Catch:{ all -> 0x0217 }
            java.lang.String r11 = "event_name"
            r7.put(r11, r0)     // Catch:{ all -> 0x0217 }
            java.lang.String r11 = "event_time_millis"
            java.lang.String r12 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x0217 }
            r7.put(r11, r12)     // Catch:{ all -> 0x0217 }
            com.flurry.sdk.ar r11 = r1.h     // Catch:{ all -> 0x0217 }
            java.lang.String r12 = r6.e     // Catch:{ all -> 0x0217 }
            java.lang.String r20 = r11.a(r12, r7)     // Catch:{ all -> 0x0217 }
            r11 = 0
            java.lang.String r12 = r6.f     // Catch:{ all -> 0x0217 }
            if (r12 == 0) goto L_0x0176
            com.flurry.sdk.ar r11 = r1.h     // Catch:{ all -> 0x0217 }
            java.lang.String r12 = r6.f     // Catch:{ all -> 0x0217 }
            java.lang.String r7 = r11.a(r12, r7)     // Catch:{ all -> 0x0217 }
            r29 = r7
            goto L_0x0178
        L_0x0176:
            r29 = r11
        L_0x0178:
            long r11 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0217 }
            r13 = 259200000(0xf731400, double:1.280618154E-315)
            long r21 = r11 + r13
            com.flurry.sdk.av r7 = new com.flurry.sdk.av     // Catch:{ all -> 0x0217 }
            java.lang.String r11 = r6.b     // Catch:{ all -> 0x0217 }
            long r12 = r6.a     // Catch:{ all -> 0x0217 }
            com.flurry.sdk.af r14 = r1.t     // Catch:{ all -> 0x0217 }
            com.flurry.sdk.ad r14 = r14.e     // Catch:{ all -> 0x0217 }
            int r14 = r14.b     // Catch:{ all -> 0x0217 }
            int r15 = r6.g     // Catch:{ all -> 0x0217 }
            com.flurry.sdk.bc r2 = r6.d     // Catch:{ all -> 0x0217 }
            r30 = r3
            java.util.Map<java.lang.String, java.lang.String> r3 = r6.j     // Catch:{ all -> 0x0217 }
            r31 = r4
            int r4 = r6.i     // Catch:{ all -> 0x0217 }
            int r5 = r6.h     // Catch:{ all -> 0x0217 }
            r16 = r7
            r17 = r11
            r18 = r12
            r23 = r14
            r24 = r15
            r25 = r2
            r26 = r3
            r27 = r4
            r28 = r5
            r16.<init>(r17, r18, r20, r21, r23, r24, r25, r26, r27, r28, r29)     // Catch:{ all -> 0x0217 }
            long r2 = r6.a     // Catch:{ all -> 0x0217 }
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x0217 }
            r10.put(r2, r7)     // Catch:{ all -> 0x0217 }
            r3 = r30
            r4 = r31
            r2 = r35
            goto L_0x0132
        L_0x01c1:
            int r2 = r10.size()     // Catch:{ all -> 0x0217 }
            if (r2 == 0) goto L_0x0215
            com.flurry.sdk.az r11 = new com.flurry.sdk.az     // Catch:{ all -> 0x0217 }
            com.flurry.sdk.bq.a()     // Catch:{ all -> 0x0217 }
            long r5 = com.flurry.sdk.bq.d()     // Catch:{ all -> 0x0217 }
            com.flurry.sdk.bq.a()     // Catch:{ all -> 0x0217 }
            long r12 = com.flurry.sdk.bq.g()     // Catch:{ all -> 0x0217 }
            r2 = r11
            r3 = r34
            r4 = r8
            r7 = r12
            r2.<init>(r3, r4, r5, r7, r9, r10)     // Catch:{ all -> 0x0217 }
            java.lang.String r2 = "flurry.session_end"
            boolean r2 = r2.equals(r0)     // Catch:{ all -> 0x0217 }
            if (r2 == 0) goto L_0x01fe
            java.lang.String r2 = e     // Catch:{ all -> 0x0217 }
            java.lang.String r3 = "Storing Pulse callbacks for event: "
            java.lang.String r0 = java.lang.String.valueOf(r34)     // Catch:{ all -> 0x0217 }
            java.lang.String r0 = r3.concat(r0)     // Catch:{ all -> 0x0217 }
            r3 = 3
            com.flurry.sdk.db.a((int) r3, (java.lang.String) r2, (java.lang.String) r0)     // Catch:{ all -> 0x0217 }
            java.util.List<com.flurry.sdk.az> r0 = r1.j     // Catch:{ all -> 0x0217 }
            r0.add(r11)     // Catch:{ all -> 0x0217 }
            monitor-exit(r33)
            return
        L_0x01fe:
            java.lang.String r2 = e     // Catch:{ all -> 0x0217 }
            java.lang.String r3 = "Firing Pulse callbacks for event: "
            java.lang.String r0 = java.lang.String.valueOf(r34)     // Catch:{ all -> 0x0217 }
            java.lang.String r0 = r3.concat(r0)     // Catch:{ all -> 0x0217 }
            r3 = 3
            com.flurry.sdk.db.a((int) r3, (java.lang.String) r2, (java.lang.String) r0)     // Catch:{ all -> 0x0217 }
            com.flurry.sdk.ay r0 = com.flurry.sdk.ay.d()     // Catch:{ all -> 0x0217 }
            r0.a((com.flurry.sdk.az) r11)     // Catch:{ all -> 0x0217 }
        L_0x0215:
            monitor-exit(r33)
            return
        L_0x0217:
            r0 = move-exception
            monitor-exit(r33)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.as.b(java.lang.String, java.util.Map):void");
    }

    private byte[] f() {
        try {
            ae aeVar = new ae();
            aeVar.a = ck.a().b;
            aeVar.b = ej.a(ck.a().a);
            aeVar.c = ej.b(ck.a().a);
            aeVar.d = cl.b();
            aeVar.e = 3;
            cb.a();
            aeVar.f = cb.c();
            aeVar.g = !bs.a().f();
            aeVar.h = new ah();
            aeVar.h.a = new ab();
            aeVar.h.a.a = Build.MODEL;
            aeVar.h.a.b = Build.BRAND;
            aeVar.h.a.c = Build.ID;
            aeVar.h.a.d = Build.DEVICE;
            aeVar.h.a.e = Build.PRODUCT;
            aeVar.h.a.f = Build.VERSION.RELEASE;
            aeVar.i = new ArrayList();
            for (Map.Entry next : Collections.unmodifiableMap(bs.a().a).entrySet()) {
                ag agVar = new ag();
                agVar.a = ((ca) next.getKey()).d;
                if (((ca) next.getKey()).e) {
                    agVar.b = new String((byte[]) next.getValue());
                } else {
                    agVar.b = em.b((byte[]) next.getValue());
                }
                aeVar.i.add(agVar);
            }
            Location g2 = bw.a().g();
            if (g2 != null) {
                int d2 = bw.d();
                aeVar.j = new al();
                aeVar.j.a = new ak();
                aeVar.j.a.a = em.a(g2.getLatitude(), d2);
                aeVar.j.a.b = em.a(g2.getLongitude(), d2);
                aeVar.j.a.c = (float) em.a((double) g2.getAccuracy(), d2);
            }
            String str = (String) eg.a().a("UserId");
            if (!str.equals("")) {
                aeVar.k = new ao();
                aeVar.k.a = str;
            }
            dg<ae> dgVar = this.f;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            dgVar.c.a(byteArrayOutputStream, aeVar);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            String str2 = dg.a;
            db.a(3, str2, "Encoding " + dgVar.b + ": " + new String(byteArray));
            du duVar = new du(new ds());
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            duVar.a(byteArrayOutputStream2, byteArray);
            byte[] byteArray2 = byteArrayOutputStream2.toByteArray();
            dg.b(byteArray2);
            return byteArray2;
        } catch (Exception e2) {
            db.a(5, e, "Proton config request failed with exception: ".concat(String.valueOf(e2)));
            return null;
        }
    }

    /* access modifiers changed from: private */
    public synchronized void a(long j2, boolean z, byte[] bArr) {
        if (bArr != null) {
            db.a(4, e, "Saving proton config response");
            aq aqVar = new aq();
            aqVar.a = j2;
            aqVar.b = z;
            aqVar.c = bArr;
            this.k.a(aqVar);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00a5 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00a6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean b(com.flurry.sdk.af r11) {
        /*
            r0 = 0
            if (r11 != 0) goto L_0x0004
            return r0
        L_0x0004:
            com.flurry.sdk.ad r1 = r11.e
            r2 = 3
            r3 = 1
            if (r1 == 0) goto L_0x008b
            java.util.List<com.flurry.sdk.ac> r4 = r1.a
            if (r4 == 0) goto L_0x008b
            r4 = 0
        L_0x000f:
            java.util.List<com.flurry.sdk.ac> r5 = r1.a
            int r5 = r5.size()
            if (r4 >= r5) goto L_0x008b
            java.util.List<com.flurry.sdk.ac> r5 = r1.a
            java.lang.Object r5 = r5.get(r4)
            com.flurry.sdk.ac r5 = (com.flurry.sdk.ac) r5
            if (r5 == 0) goto L_0x0088
            java.lang.String r6 = r5.b
            java.lang.String r7 = ""
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L_0x007f
            long r6 = r5.a
            r8 = -1
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r10 == 0) goto L_0x007f
            java.lang.String r6 = r5.e
            java.lang.String r7 = ""
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L_0x007f
            java.util.List<com.flurry.sdk.ai> r5 = r5.c
            if (r5 == 0) goto L_0x007c
            java.util.Iterator r5 = r5.iterator()
        L_0x0045:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x007c
            java.lang.Object r6 = r5.next()
            com.flurry.sdk.ai r6 = (com.flurry.sdk.ai) r6
            java.lang.String r7 = r6.a
            java.lang.String r8 = ""
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x0064
            java.lang.String r5 = e
            java.lang.String r6 = "An event is missing a name"
            com.flurry.sdk.db.a((int) r2, (java.lang.String) r5, (java.lang.String) r6)
        L_0x0062:
            r5 = 0
            goto L_0x007d
        L_0x0064:
            boolean r7 = r6 instanceof com.flurry.sdk.aj
            if (r7 == 0) goto L_0x0045
            com.flurry.sdk.aj r6 = (com.flurry.sdk.aj) r6
            java.lang.String r6 = r6.c
            java.lang.String r7 = ""
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x0045
            java.lang.String r5 = e
            java.lang.String r6 = "An event trigger is missing a param name"
            com.flurry.sdk.db.a((int) r2, (java.lang.String) r5, (java.lang.String) r6)
            goto L_0x0062
        L_0x007c:
            r5 = 1
        L_0x007d:
            if (r5 != 0) goto L_0x0088
        L_0x007f:
            java.lang.String r1 = e
            java.lang.String r4 = "A callback template is missing required values"
            com.flurry.sdk.db.a((int) r2, (java.lang.String) r1, (java.lang.String) r4)
            r1 = 0
            goto L_0x008c
        L_0x0088:
            int r4 = r4 + 1
            goto L_0x000f
        L_0x008b:
            r1 = 1
        L_0x008c:
            if (r1 == 0) goto L_0x00a6
            com.flurry.sdk.ad r1 = r11.e
            if (r1 == 0) goto L_0x00a5
            com.flurry.sdk.ad r1 = r11.e
            java.lang.String r1 = r1.e
            if (r1 == 0) goto L_0x00a5
            com.flurry.sdk.ad r11 = r11.e
            java.lang.String r11 = r11.e
            java.lang.String r1 = ""
            boolean r11 = r11.equals(r1)
            if (r11 == 0) goto L_0x00a5
            goto L_0x00a6
        L_0x00a5:
            return r3
        L_0x00a6:
            java.lang.String r11 = e
            java.lang.String r1 = "Config response is missing required values."
            com.flurry.sdk.db.a((int) r2, (java.lang.String) r11, (java.lang.String) r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.as.b(com.flurry.sdk.af):boolean");
    }

    /* access modifiers changed from: private */
    public void g() {
        List<ac> list;
        List<ai> list2;
        if (this.t != null) {
            db.a(5, e, "Processing config response");
            ay.a(this.t.e.c);
            ay.b(this.t.e.d * 1000);
            ba a2 = ba.a();
            String str = this.t.e.e;
            if (str != null && !str.endsWith(".do")) {
                db.a(5, ba.a, "overriding analytics agent report URL without an endpoint, are you sure?");
            }
            a2.b = str;
            if (this.m) {
                eg.a().a("analyticsEnabled", (Object) Boolean.valueOf(this.t.f.b));
            }
            this.i.a();
            ad adVar = this.t.e;
            if (adVar != null && (list = adVar.a) != null) {
                for (ac next : list) {
                    if (!(next == null || (list2 = next.c) == null)) {
                        for (ai next2 : list2) {
                            if (next2 != null && !TextUtils.isEmpty(next2.a)) {
                                next2.b = next;
                                this.i.a(next2.a, next2);
                            }
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0033, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void h() {
        /*
            r4 = this;
            monitor-enter(r4)
            boolean r0 = r4.m     // Catch:{ all -> 0x0034 }
            if (r0 != 0) goto L_0x0007
            monitor-exit(r4)
            return
        L_0x0007:
            com.flurry.sdk.em.a()     // Catch:{ all -> 0x0034 }
            com.flurry.sdk.ck r0 = com.flurry.sdk.ck.a()     // Catch:{ all -> 0x0034 }
            android.content.Context r0 = r0.a     // Catch:{ all -> 0x0034 }
            java.lang.String r1 = "FLURRY_SHARED_PREFERENCES"
            r2 = 0
            android.content.SharedPreferences r0 = r0.getSharedPreferences(r1, r2)     // Catch:{ all -> 0x0034 }
            java.lang.String r1 = "com.flurry.android.flurryAppInstall"
            r3 = 1
            boolean r1 = r0.getBoolean(r1, r3)     // Catch:{ all -> 0x0034 }
            if (r1 == 0) goto L_0x0032
            java.lang.String r1 = "flurry.app_install"
            r3 = 0
            r4.b((java.lang.String) r1, (java.util.Map<java.lang.String, java.lang.String>) r3)     // Catch:{ all -> 0x0034 }
            android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch:{ all -> 0x0034 }
            java.lang.String r1 = "com.flurry.android.flurryAppInstall"
            r0.putBoolean(r1, r2)     // Catch:{ all -> 0x0034 }
            r0.apply()     // Catch:{ all -> 0x0034 }
        L_0x0032:
            monitor-exit(r4)
            return
        L_0x0034:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.as.h():void");
    }

    private synchronized void b(long j2) {
        Iterator<az> it = this.j.iterator();
        while (it.hasNext()) {
            if (j2 == it.next().a) {
                it.remove();
            }
        }
    }

    private synchronized void i() {
        if (!this.o) {
            db.e(e, "Analytics disabled, not sending pulse reports.");
            return;
        }
        String str = e;
        db.a(4, str, "Sending " + this.j.size() + " queued reports.");
        for (az next : this.j) {
            String str2 = e;
            db.a(3, str2, "Firing Pulse callbacks for event: " + next.d);
            ay.d().a(next);
        }
        j();
    }

    private synchronized void j() {
        this.j.clear();
        this.l.b();
    }

    /* access modifiers changed from: private */
    public synchronized void k() {
        db.a(4, e, "Saving queued report data.");
        this.l.a(this.j);
    }

    /* access modifiers changed from: private */
    public synchronized void l() {
        af afVar;
        aq a2 = this.k.a();
        if (a2 != null) {
            af afVar2 = null;
            try {
                afVar = this.g.c(a2.c);
            } catch (Exception e2) {
                db.a(5, e, "Failed to decode saved proton config response: ".concat(String.valueOf(e2)));
                this.k.b();
                afVar = null;
            }
            if (b(afVar)) {
                afVar2 = afVar;
            }
            if (afVar2 != null) {
                db.a(4, e, "Loaded saved proton config response");
                this.q = 10000;
                this.r = a2.a;
                this.s = a2.b;
                this.t = afVar2;
                g();
            }
        }
        this.p = true;
        ck.a().b(new eo() {
            public final void a() {
                as.this.e();
            }
        });
    }

    /* access modifiers changed from: private */
    public synchronized void m() {
        db.a(4, e, "Loading queued report data.");
        List a2 = this.l.a();
        if (a2 != null) {
            this.j.addAll(a2);
        }
    }
}
