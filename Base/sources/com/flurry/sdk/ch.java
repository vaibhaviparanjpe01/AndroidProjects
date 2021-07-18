package com.flurry.sdk;

import android.os.SystemClock;
import com.flurry.sdk.ec;
import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;
import java.util.Map;

public class ch {
    private static final String f = "ch";
    /* access modifiers changed from: package-private */
    public WeakReference<eb> a;
    public volatile long b = 0;
    public volatile long c = 0;
    public volatile long d = -1;
    public volatile long e = 0;
    /* access modifiers changed from: private */
    public final cv<ec> g = new cv<ec>() {
        public final /* synthetic */ void a(cu cuVar) {
            ec ecVar = (ec) cuVar;
            if (ch.this.a == null || ecVar.b == ch.this.a.get()) {
                switch (AnonymousClass4.a[ecVar.d - 1]) {
                    case 1:
                        ch chVar = ch.this;
                        eb ebVar = ecVar.b;
                        ecVar.a.get();
                        chVar.a = new WeakReference<>(ebVar);
                        chVar.b = System.currentTimeMillis();
                        chVar.c = SystemClock.elapsedRealtime();
                        ck.a().b(new eo() {
                            public final void a() {
                                bw.a().e();
                            }
                        });
                        return;
                    case 2:
                        ch chVar2 = ch.this;
                        ecVar.a.get();
                        chVar2.a();
                        return;
                    case 3:
                        ch chVar3 = ch.this;
                        ecVar.a.get();
                        chVar3.d = SystemClock.elapsedRealtime() - chVar3.c;
                        return;
                    case 4:
                        cw.a().b("com.flurry.android.sdk.FlurrySessionEvent", ch.this.g);
                        return;
                    default:
                        return;
                }
            }
        }
    };
    private volatile long h = 0;
    private String i;
    private String j;
    private Map<String, String> k;

    /* renamed from: com.flurry.sdk.ch$4  reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] a = new int[ec.a.a().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0011 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0021 */
        static {
            /*
                int[] r0 = com.flurry.sdk.ec.a.a()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                r0 = 1
                int[] r1 = a     // Catch:{ NoSuchFieldError -> 0x0011 }
                int r2 = com.flurry.sdk.ec.a.a     // Catch:{ NoSuchFieldError -> 0x0011 }
                int r2 = r2 - r0
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0011 }
            L_0x0011:
                int[] r1 = a     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r2 = com.flurry.sdk.ec.a.b     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r2 = r2 - r0
                r3 = 2
                r1[r2] = r3     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                int[] r1 = a     // Catch:{ NoSuchFieldError -> 0x0021 }
                int r2 = com.flurry.sdk.ec.a.c     // Catch:{ NoSuchFieldError -> 0x0021 }
                int r2 = r2 - r0
                r3 = 3
                r1[r2] = r3     // Catch:{ NoSuchFieldError -> 0x0021 }
            L_0x0021:
                int[] r1 = a     // Catch:{ NoSuchFieldError -> 0x0029 }
                int r2 = com.flurry.sdk.ec.a.d     // Catch:{ NoSuchFieldError -> 0x0029 }
                int r2 = r2 - r0
                r0 = 4
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0029 }
            L_0x0029:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.ch.AnonymousClass4.<clinit>():void");
        }
    }

    public ch() {
        cw.a().a("com.flurry.android.sdk.FlurrySessionEvent", this.g);
        this.k = new LinkedHashMap<String, String>() {
            /* access modifiers changed from: protected */
            public final boolean removeEldestEntry(Map.Entry<String, String> entry) {
                return size() > 10;
            }
        };
    }

    public final synchronized void a() {
        long j2 = ed.a().a;
        if (j2 > 0) {
            this.e += System.currentTimeMillis() - j2;
        }
    }

    public final synchronized long b() {
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.c;
        if (elapsedRealtime <= this.h) {
            elapsedRealtime = this.h + 1;
            this.h = elapsedRealtime;
        }
        this.h = elapsedRealtime;
        return this.h;
    }

    public final synchronized String c() {
        return this.i;
    }

    public final synchronized void a(String str) {
        this.i = str;
    }

    public final synchronized String d() {
        return this.j;
    }

    public final synchronized void b(String str) {
        this.j = str;
    }

    public final synchronized void a(String str, String str2) {
        this.k.put(str, str2);
    }

    public final synchronized Map<String, String> e() {
        return this.k;
    }
}
