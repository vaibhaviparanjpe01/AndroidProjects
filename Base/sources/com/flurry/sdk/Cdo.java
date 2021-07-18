package com.flurry.sdk;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* renamed from: com.flurry.sdk.do  reason: invalid class name */
public abstract class Cdo {
    protected final String a;
    protected String b = "defaultDataKey_";
    Set<String> c = new HashSet();
    dq d;
    private cv<bx> e = new cv<bx>() {
        public final /* synthetic */ void a(cu cuVar) {
            bx bxVar = (bx) cuVar;
            String str = Cdo.this.a;
            db.a(4, str, "onNetworkStateChanged : isNetworkEnable = " + bxVar.a);
            if (bxVar.a) {
                Cdo.this.d();
            }
        }
    };

    /* renamed from: com.flurry.sdk.do$a */
    public interface a {
    }

    /* access modifiers changed from: protected */
    public abstract void a(byte[] bArr, String str, String str2);

    public Cdo(final String str, String str2) {
        this.a = str2;
        cw.a().a("com.flurry.android.sdk.NetworkStateEvent", this.e);
        ck.a().b(new eo() {
            public final void a() {
                Cdo.this.d = new dq(str);
            }
        });
    }

    public final void b(final byte[] bArr, final String str, final String str2) {
        if (bArr == null || bArr.length == 0) {
            db.a(6, this.a, "Report that has to be sent is EMPTY or NULL");
            return;
        }
        ck.a().b(new eo() {
            public final void a() {
                Cdo.this.c(bArr, str, str2);
            }
        });
        b();
    }

    /* access modifiers changed from: protected */
    public final void b() {
        ck.a().b(new eo() {
            final /* synthetic */ a a = null;

            public final void a() {
                Cdo.this.c();
            }
        });
    }

    /* access modifiers changed from: protected */
    public final void c() {
        if (!by.a().c) {
            db.a(5, this.a, "Reports were not sent! No Internet connection!");
            return;
        }
        ArrayList<String> arrayList = new ArrayList<>(this.d.c.keySet());
        if (arrayList.isEmpty()) {
            db.a(4, this.a, "No more reports to send.");
            return;
        }
        for (String str : arrayList) {
            if (a()) {
                List<String> a2 = this.d.a(str);
                String str2 = this.a;
                db.a(4, str2, "Number of not sent blocks = " + a2.size());
                for (String next : a2) {
                    if (!this.c.contains(next)) {
                        if (!a()) {
                            break;
                        }
                        dp a3 = dp.b(next).a();
                        if (a3 == null) {
                            db.a(6, this.a, "Internal ERROR! Cannot read!");
                            this.d.a(next, str);
                        } else {
                            byte[] bArr = a3.b;
                            if (bArr == null || bArr.length == 0) {
                                db.a(6, this.a, "Internal ERROR! Report is empty!");
                                this.d.a(next, str);
                            } else {
                                db.a(5, this.a, "Reading block info ".concat(String.valueOf(next)));
                                this.c.add(next);
                                a(bArr, next, str);
                            }
                        }
                    }
                }
            } else {
                return;
            }
        }
    }

    private boolean a() {
        return e() <= 5;
    }

    private int e() {
        return this.c.size();
    }

    public final void d() {
        b();
    }

    /* access modifiers changed from: protected */
    public void a(final String str, final String str2, int i) {
        ck.a().b(new eo() {
            public final void a() {
                if (!Cdo.this.d.a(str, str2)) {
                    String str = Cdo.this.a;
                    db.a(6, str, "Internal error. Block wasn't deleted with id = " + str);
                }
                if (!Cdo.this.c.remove(str)) {
                    String str2 = Cdo.this.a;
                    db.a(6, str2, "Internal error. Block with id = " + str + " was not in progress state");
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public final void c(byte[] bArr, String str, String str2) {
        String str3 = this.b + str + "_" + str2;
        dp dpVar = new dp(bArr);
        String str4 = dpVar.a;
        dp.b(str4).a(dpVar);
        db.a(5, this.a, "Saving Block File " + str4 + " at " + ck.a().a.getFileStreamPath(dp.a(str4)));
        this.d.a(dpVar, str3);
    }
}
