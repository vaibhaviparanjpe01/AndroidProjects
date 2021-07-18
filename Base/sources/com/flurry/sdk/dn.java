package com.flurry.sdk;

import com.flurry.sdk.dm;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class dn<ReportInfo extends dm> {
    private static final String a = "dn";
    protected static long b = 10000;
    public boolean c;
    protected long d;
    public final Runnable e = new eo() {
        public final void a() {
            dn.this.b();
        }
    };
    public final cv<bx> f = new cv<bx>() {
        public final /* bridge */ /* synthetic */ void a(cu cuVar) {
            if (((bx) cuVar).a) {
                dn.this.b();
            }
        }
    };
    private final int g = Integer.MAX_VALUE;
    private final ct<List<ReportInfo>> h;
    /* access modifiers changed from: private */
    public final List<ReportInfo> i = new ArrayList();
    private int j;

    /* access modifiers changed from: protected */
    public abstract ct<List<ReportInfo>> a();

    /* access modifiers changed from: protected */
    public abstract void a(ReportInfo reportinfo);

    public dn() {
        cw.a().a("com.flurry.android.sdk.NetworkStateEvent", this.f);
        this.h = a();
        this.d = b;
        this.j = -1;
        ck.a().b(new eo() {
            public final void a() {
                dn.this.a(dn.this.i);
                dn.this.b();
            }
        });
    }

    /* access modifiers changed from: protected */
    public synchronized void a(List<ReportInfo> list) {
        em.a();
        List a2 = this.h.a();
        if (a2 != null) {
            list.addAll(a2);
        }
    }

    /* access modifiers changed from: private */
    public synchronized void b() {
        if (!this.c) {
            if (this.j >= 0) {
                db.a(3, a, "Transmit is in progress");
                return;
            }
            c();
            if (this.i.isEmpty()) {
                this.d = b;
                this.j = -1;
                return;
            }
            this.j = 0;
            ck.a().b(new eo() {
                public final void a() {
                    dn.this.d();
                }
            });
        }
    }

    private synchronized void c() {
        Iterator<ReportInfo> it = this.i.iterator();
        while (it.hasNext()) {
            dm dmVar = (dm) it.next();
            if (dmVar.o) {
                String str = a;
                db.a(3, str, "Url transmitted - " + dmVar.q + " Attempts: " + dmVar.p);
                it.remove();
            } else if (dmVar.p > dmVar.s) {
                String str2 = a;
                db.a(3, str2, "Exceeded max no of attempts - " + dmVar.q + " Attempts: " + dmVar.p);
                it.remove();
            } else if (System.currentTimeMillis() > dmVar.n && dmVar.p > 0) {
                String str3 = a;
                db.a(3, str3, "Expired: Time expired - " + dmVar.q + " Attempts: " + dmVar.p);
                it.remove();
            }
        }
    }

    /* access modifiers changed from: private */
    public synchronized void d() {
        em.a();
        dm dmVar = null;
        if (by.a().c) {
            while (true) {
                if (this.j >= this.i.size()) {
                    break;
                }
                List<ReportInfo> list = this.i;
                int i2 = this.j;
                this.j = i2 + 1;
                dm dmVar2 = (dm) list.get(i2);
                if (!dmVar2.o) {
                    dmVar = dmVar2;
                    break;
                }
            }
        } else {
            db.a(3, a, "Network is not available, aborting transmission");
        }
        if (dmVar == null) {
            e();
        } else {
            a(dmVar);
        }
    }

    private synchronized void e() {
        c();
        b(this.i);
        if (this.c) {
            db.a(3, a, "Reporter paused");
            this.d = b;
        } else if (this.i.isEmpty()) {
            db.a(3, a, "All reports sent successfully");
            this.d = b;
        } else {
            this.d <<= 1;
            db.a(3, a, "One or more reports failed to send, backing off: " + this.d + "ms");
            ck.a().a(this.e, this.d);
        }
        this.j = -1;
    }

    /* access modifiers changed from: protected */
    public synchronized void b(List<ReportInfo> list) {
        em.a();
        this.h.a(new ArrayList(list));
    }

    public final synchronized void b(ReportInfo reportinfo) {
        if (reportinfo != null) {
            this.i.add(reportinfo);
            ck.a().b(new eo() {
                public final void a() {
                    dn.this.b();
                }
            });
        }
    }

    public final synchronized void d(ReportInfo reportinfo) {
        reportinfo.a();
        ck.a().b(new eo() {
            public final void a() {
                dn.this.d();
            }
        });
    }

    public final synchronized void c(ReportInfo reportinfo) {
        reportinfo.o = true;
        ck.a().b(new eo() {
            public final void a() {
                dn.this.d();
            }
        });
    }
}
