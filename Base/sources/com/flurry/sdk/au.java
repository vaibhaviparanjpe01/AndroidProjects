package com.flurry.sdk;

import com.flurry.sdk.av;
import com.flurry.sdk.di;
import com.flurry.sdk.dk;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class au extends dn<av> {
    public static long a = 0;
    /* access modifiers changed from: private */
    public static final String g = "au";

    public final /* synthetic */ void a(dm dmVar) {
        final av avVar = (av) dmVar;
        String str = g;
        db.a(3, str, "Sending next pulse report to " + avVar.l + " at: " + avVar.r);
        bq.a();
        long d = bq.d();
        if (d == 0) {
            d = a;
        }
        long j = d;
        bq.a();
        long g2 = bq.g();
        if (g2 == 0) {
            g2 = System.currentTimeMillis() - j;
        }
        final aw awVar = new aw(avVar, j, g2, avVar.p);
        di diVar = new di();
        diVar.g = avVar.r;
        diVar.u = 100000;
        if (avVar.d.equals(bc.POST)) {
            diVar.c = new ds();
            if (avVar.k != null) {
                diVar.b = avVar.k.getBytes();
            }
            diVar.h = dk.a.kPost;
        } else {
            diVar.h = dk.a.kGet;
        }
        diVar.i = avVar.i * 1000;
        diVar.j = avVar.j * 1000;
        diVar.m = true;
        diVar.r = true;
        diVar.s = (avVar.i + avVar.j) * 1000;
        Map<String, String> map = avVar.e;
        if (map != null) {
            for (String next : avVar.e.keySet()) {
                diVar.a(next, map.get(next));
            }
        }
        diVar.k = false;
        diVar.a = new di.a<byte[], String>() {
            public final /* synthetic */ void a(di diVar, Object obj) {
                String str = (String) obj;
                String str2 = avVar.l;
                String str3 = avVar.g.d;
                bd bdVar = avVar.g.b;
                db.a(3, au.g, "Pulse report to " + str2 + " for " + str3 + ", HTTP status code is: " + diVar.q);
                int i = diVar.q;
                aw awVar = awVar;
                int i2 = (int) diVar.o;
                if (i2 >= 0) {
                    awVar.k += (long) i2;
                } else if (awVar.k <= 0) {
                    awVar.k = 0;
                }
                awVar.e = i;
                if (!diVar.b()) {
                    Exception exc = diVar.p;
                    boolean z = true;
                    boolean z2 = diVar.p != null && (diVar.p instanceof SocketTimeoutException);
                    if (!diVar.t && !z2) {
                        z = false;
                    }
                    if (z) {
                        if (diVar.c()) {
                            db.a(3, au.g, "Timeout occurred when trying to connect to: " + str2 + ". Exception: " + diVar.p.getMessage());
                        } else {
                            db.a(3, au.g, "Manually managed http request timeout occurred for: ".concat(String.valueOf(str2)));
                        }
                        au.a(au.this, awVar, avVar);
                    } else {
                        db.a(3, au.g, "Error occurred when trying to connect to: " + str2 + ". Exception: " + exc.getMessage());
                        au.a(au.this, awVar, avVar, str);
                    }
                    a.b(str2, str3, bdVar);
                } else if (i >= 200 && i < 300) {
                    au.b(au.this, awVar, avVar);
                    a.a(str2, str3, bdVar);
                } else if (i < 300 || i >= 400) {
                    db.a(3, au.g, str3 + " report failed sending to : " + str2);
                    au.a(au.this, awVar, avVar, str);
                    a.b(str2, str3, bdVar);
                } else {
                    au.a(au.this, awVar, avVar, diVar);
                }
            }
        };
        cg.a().a((Object) this, diVar);
    }

    public au() {
        dn.b = 30000;
        this.d = dn.b;
    }

    public final ct<List<av>> a() {
        return new ct<>(ck.a().a.getFileStreamPath(".yflurryanpulsecallbackreporter"), ".yflurryanpulsecallbackreporter", 2, new dz<List<av>>() {
            public final dw<List<av>> a(int i) {
                return new dv(new av.a());
            }
        });
    }

    public final synchronized void a(List<av> list) {
        ay.d();
        List<az> e = ay.e();
        if (e != null) {
            if (e.size() != 0) {
                String str = g;
                db.a(3, str, "Restoring " + e.size() + " from report queue.");
                for (az b : e) {
                    ay.d().b(b);
                }
                ay.d();
                for (az a2 : ay.c()) {
                    for (av next : a2.a()) {
                        if (!next.m) {
                            String str2 = g;
                            db.a(3, str2, "Callback for " + next.g.d + " to " + next.l + " not completed.  Adding to reporter queue.");
                            list.add(next);
                        }
                    }
                }
            }
        }
    }

    public final synchronized void b(List<av> list) {
        ay.d().b();
    }

    static class a {
        private static HashMap<bd, String> a;

        static {
            HashMap<bd, String> hashMap = new HashMap<>();
            a = hashMap;
            hashMap.put(bd.INSTALL, "Install");
            a.put(bd.SESSION_START, "Session Start");
            a.put(bd.SESSION_END, "Session End");
            a.put(bd.APPLICATION_EVENT, "App Event");
        }

        static void a(String str, String str2, bd bdVar) {
            if (!x.a().e) {
                db.a(4, au.g, "Not yahoo app. Don't log event Flurry.PulseSuccess");
                return;
            }
            HashMap hashMap = new HashMap(3);
            hashMap.put("fl.Partner", str);
            hashMap.put("fl.Event", str2);
            hashMap.put("fl.Trigger", a(bdVar));
            try {
                x.a().a("Flurry.PulseSuccess", (Map<String, String>) hashMap, false);
            } catch (Throwable th) {
                db.a(au.g, "Failed to log event: Flurry.PulseSuccess", th);
            }
        }

        static void b(String str, String str2, bd bdVar) {
            if (!x.a().e) {
                db.a(4, au.g, "Not yahoo app. Don't log event Flurry.PulseFail");
                return;
            }
            HashMap hashMap = new HashMap(3);
            hashMap.put("fl.Partner", str);
            hashMap.put("fl.Event", str2);
            hashMap.put("fl.Trigger", a(bdVar));
            try {
                x.a().a("Flurry.PulseFail", (Map<String, String>) hashMap, false);
            } catch (Throwable th) {
                db.a(au.g, "Failed to log event: Flurry.PulseFail", th);
            }
        }

        private static String a(bd bdVar) {
            String str = a.get(bdVar);
            return str == null ? "Unknown" : str;
        }
    }

    static /* synthetic */ void a(au auVar, aw awVar, av avVar) {
        ay.d().b(awVar);
        auVar.c(avVar);
    }

    static /* synthetic */ void a(au auVar, aw awVar, av avVar, String str) {
        boolean b = ay.d().b(awVar, str);
        db.a(3, g, "Failed report retrying: ".concat(String.valueOf(b)));
        if (b) {
            auVar.d(avVar);
        } else {
            auVar.c(avVar);
        }
    }

    static /* synthetic */ void b(au auVar, aw awVar, av avVar) {
        String str = g;
        db.a(3, str, avVar.g.d + " report sent successfully to : " + avVar.l);
        ay.d().a(awVar);
        auVar.c(avVar);
    }

    static /* synthetic */ void a(au auVar, aw awVar, av avVar, di diVar) {
        List<String> a2 = diVar.a("Location");
        String a3 = (a2 == null || a2.size() <= 0) ? null : er.a(a2.get(0), avVar.q);
        boolean a4 = ay.d().a(awVar, a3);
        if (a4) {
            db.a(3, g, "Received redirect url. Retrying: ".concat(String.valueOf(a3)));
        } else {
            db.a(3, g, "Received redirect url. Retrying: false");
        }
        if (a4) {
            avVar.r = a3;
            diVar.g = a3;
            if (diVar.f != null && diVar.f.a.containsKey("Location")) {
                diVar.f.b("Location");
            }
            cg.a().a((Object) auVar, diVar);
            return;
        }
        auVar.c(avVar);
    }
}
