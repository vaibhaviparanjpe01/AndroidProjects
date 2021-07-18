package com.flurry.sdk;

import android.content.Context;
import com.flurry.android.FlurryAgent;
import com.flurry.sdk.bs;
import com.flurry.sdk.g;
import com.flurry.sdk.k;
import java.util.List;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;

public class b {
    private static final String a = "b";
    private static boolean b = false;
    /* access modifiers changed from: private */
    public g c;
    private r d;
    /* access modifiers changed from: private */
    public a e;
    /* access modifiers changed from: private */
    public e f;
    private m g;
    /* access modifiers changed from: private */
    public long h;
    /* access modifiers changed from: private */
    public k i;

    public interface a {
        void a(g gVar, boolean z);
    }

    public b(r rVar, a aVar, e eVar, m mVar) {
        this.d = rVar;
        this.e = aVar;
        this.f = eVar;
        this.g = mVar;
    }

    public final synchronized void a() {
        db.a(a, "Starting Config fetch.");
        r.a((Runnable) new Runnable() {
            public final void run() {
                g unused = b.this.c = g.b;
                long unused2 = b.this.h = System.currentTimeMillis();
                k unused3 = b.this.i = null;
                b.this.f.b();
                if (!b.c(b.this)) {
                    b.this.e.a(b.this.c, false);
                } else {
                    b.this.b();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public synchronized void b() {
        if (bs.a().c()) {
            c();
            return;
        }
        db.a(a, "Waiting for ID provider.");
        bs.a().a((bs.b) new bs.b() {
            public final void a() {
                b.this.c();
            }
        });
    }

    /* access modifiers changed from: private */
    public synchronized void c() {
        JSONObject a2;
        db.a(a, "Fetching Config data.");
        this.d.run();
        this.c = this.d.h();
        if (this.c == g.a) {
            db.a(a, "Processing Config fetched data.");
            try {
                String str = this.d.h;
                db.a(a, "JSON body: ".concat(String.valueOf(str)));
                JSONObject jSONObject = new JSONObject(str);
                String d2 = this.d.d();
                String str2 = ck.a().b;
                String optString = jSONObject.optString("requestGuid");
                String optString2 = jSONObject.optString("apiKey");
                if (d2.equals(optString)) {
                    if (str2.equals(optString2)) {
                        List<l> a3 = f.a(jSONObject);
                        long optLong = jSONObject.optLong("refreshInSeconds");
                        this.g.d = optLong;
                        if (!t.a(this.f.d()) || !this.d.c() || this.g.b(a3)) {
                            this.g.a(a3, this.d.c());
                            this.c = g.a;
                            m mVar = this.g;
                            Context context = ck.a().a;
                            if (!this.d.c()) {
                                str = null;
                            }
                            if (str == null && (a2 = mVar.a(mVar.b, mVar.c, false)) != null) {
                                str = a2.toString();
                            }
                            if (str != null) {
                                t.a(context, str);
                            }
                            e eVar = this.f;
                            String g2 = this.d.g();
                            if (eVar.b != null) {
                                eVar.b.edit().putString("lastETag", g2).apply();
                            }
                            e eVar2 = this.f;
                            String e2 = this.d.e();
                            if (eVar2.b != null) {
                                eVar2.b.edit().putString("lastKeyId", e2).apply();
                            }
                            e eVar3 = this.f;
                            String f2 = this.d.f();
                            if (eVar3.b != null) {
                                eVar3.b.edit().putString("lastRSA", f2).apply();
                            }
                        } else {
                            this.c = g.b;
                        }
                        b = true;
                        e eVar4 = this.f;
                        String c2 = this.g.c();
                        if (eVar4.b != null) {
                            db.a(e.a, "Save serized variant IDs: ".concat(String.valueOf(c2)));
                            eVar4.b.edit().putString("com.flurry.sdk.variant_ids", c2).apply();
                        }
                        e eVar5 = this.f;
                        if (eVar5.b != null) {
                            eVar5.b.edit().putInt("appVersion", eVar5.c).apply();
                        }
                        this.f.a(System.currentTimeMillis());
                        e eVar6 = this.f;
                        long j = optLong * 1000;
                        if (j == 0) {
                            eVar6.d = 0;
                        } else if (j > 604800000) {
                            eVar6.d = 604800000;
                        } else if (j < 60000) {
                            eVar6.d = 60000;
                        } else {
                            eVar6.d = j;
                        }
                        if (eVar6.b != null) {
                            eVar6.b.edit().putLong("refreshFetch", eVar6.d).apply();
                        }
                        if (d.b() != null) {
                            d.b();
                            n.a(this.g);
                        }
                        this.f.b();
                        if (d.b() != null) {
                            d.b();
                            n.a(this.c.d.h, System.currentTimeMillis() - this.h, this.c.toString());
                        }
                        this.e.a(this.c, false);
                        return;
                    }
                }
                g.a aVar = g.a.AUTHENTICATE;
                this.c = new g(aVar, "Guid: " + d2 + ", payload: " + optString + " APIKey: " + str2 + ", payload: " + optString2);
                String str3 = a;
                StringBuilder sb = new StringBuilder("Authentication error: ");
                sb.append(this.c);
                db.b(str3, sb.toString());
                d();
            } catch (JSONException e3) {
                db.a(a, "Json parse error", (Throwable) e3);
                this.c = new g(g.a.NOT_VALID_JSON, e3.toString());
            } catch (Exception e4) {
                db.a(a, "Fetch result error", (Throwable) e4);
                this.c = new g(g.a.OTHER, e4.toString());
            }
        } else if (this.c == g.b) {
            this.f.a(System.currentTimeMillis());
            this.f.b();
            this.e.a(this.c, false);
        } else {
            String str4 = a;
            db.e(str4, "fetch error:" + this.c.toString());
            if (this.i == null && this.c.d == g.a.UNKNOWN_CERTIFICATE) {
                FlurryAgent.onError("FlurryUnknownCertificate", this.c.c, a);
            }
            if (d.b() != null) {
                d.b();
                n.a(this.c.d.h, System.currentTimeMillis() - this.h, this.c.toString());
            }
            d();
        }
    }

    private void d() {
        db.a(a, "Retry fetching Config data.");
        if (this.i == null) {
            this.i = new k(k.a.values()[0]);
        } else {
            this.i = new k(this.i.a.a());
        }
        if (this.i.a == k.a.ABANDON) {
            this.e.a(this.c, false);
            return;
        }
        this.e.a(this.c, true);
        this.f.a(new TimerTask() {
            public final void run() {
                b.this.c();
            }
        }, ((long) this.i.a()) * 1000);
    }

    static /* synthetic */ boolean c(b bVar) {
        if (!t.a(ck.a().a)) {
            return true;
        }
        String str = a;
        db.a(str, "Compare version: current=" + bVar.f.c + ", recorded=" + bVar.f.a());
        if (bVar.f.a() < bVar.f.c) {
            return true;
        }
        long j = bVar.f.d;
        long j2 = 0;
        if (j != 0) {
            e eVar = bVar.f;
            if (eVar.b != null) {
                j2 = eVar.b.getLong("lastFetch", 0);
            }
            if (System.currentTimeMillis() - j2 > j) {
                return true;
            }
        } else if (!b) {
            return true;
        }
        db.a(a, "It does not meet any criterias for data fetch.");
        return false;
    }
}
