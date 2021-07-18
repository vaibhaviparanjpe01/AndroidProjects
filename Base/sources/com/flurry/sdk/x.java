package com.flurry.sdk;

import android.content.Context;
import com.flurry.android.FlurryEventRecordStatus;
import com.flurry.sdk.eb;
import com.flurry.sdk.ec;
import com.google.android.instantapps.InstantApps;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class x implements df {
    private static final String f = "x";
    public as a;
    public bk b;
    public au c;
    public et d;
    public boolean e = false;
    private final Object g = new Object();
    private Queue<w> h = new LinkedList();
    private Queue<w> i = new LinkedList();
    private Queue<v> j = new LinkedList();
    private final cv<ec> k = new cv<ec>() {
        public final /* bridge */ /* synthetic */ void a(cu cuVar) {
            if (AnonymousClass2.a[((ec) cuVar).d - 1] == 1) {
                x.a(x.this);
            }
        }
    };

    public static synchronized x a() {
        x xVar;
        synchronized (x.class) {
            xVar = (x) ck.a().a((Class<? extends df>) x.class);
        }
        return xVar;
    }

    public void init(Context context) {
        eb.a((Class<?>) bo.class);
        this.b = new bk();
        this.a = new as();
        this.c = new au();
        this.d = new et();
        cw.a().a("com.flurry.android.sdk.FlurrySessionEvent", this.k);
        if (!em.a(context, "android.permission.INTERNET")) {
            db.b(f, "Application must declare permission: android.permission.INTERNET");
        }
        if (!em.a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            db.e(f, "It is highly recommended that the application declare permission: android.permission.ACCESS_NETWORK_STATE");
        }
        int identifier = context.getResources().getIdentifier("FLURRY_IS_YAHOO_APP", "bool", context.getPackageName());
        if (identifier != 0) {
            this.e = context.getResources().getBoolean(identifier);
            String str = f;
            db.c(str, "Found FLURRY_IS_YAHOO_APP resource id. Value: " + this.e);
        }
        da a2 = da.a();
        try {
            Class.forName("com.google.android.instantapps.InstantApps");
            a2.b = InstantApps.isInstantApp(context);
            String str2 = da.a;
            db.a(str2, "isInstantApp: " + String.valueOf(a2.b));
        } catch (ClassNotFoundException unused) {
            db.a(da.a, "isInstantApps dependency is not added");
        }
    }

    public void destroy() {
        if (this.c != null) {
            au auVar = this.c;
            ck.a().c(auVar.e);
            cw.a().b("com.flurry.android.sdk.NetworkStateEvent", auVar.f);
            this.c = null;
        }
        if (this.b != null) {
            bk bkVar = this.b;
            eg.a().b("UseHttps", bkVar);
            eg.a().b("ReportUrl", bkVar);
            this.b = null;
        }
        if (this.a != null) {
            as asVar = this.a;
            ck.a().c(asVar.a);
            cw.a().b("com.flurry.android.sdk.NetworkStateEvent", asVar.d);
            cw.a().b("com.flurry.android.sdk.IdProviderUpdatedAdvertisingId", asVar.c);
            cw.a().b("com.flurry.android.sdk.IdProviderFinishedEvent", asVar.b);
            ay.a();
            eg.a().b("ProtonEnabled", asVar);
            this.a = null;
        }
        if (this.d != null) {
            this.d.a = null;
            this.d = null;
        }
        cw.a().b("com.flurry.android.sdk.FlurrySessionEvent", this.k);
        eb.b(bo.class);
    }

    public static bo b() {
        eb f2 = ed.a().f();
        if (f2 == null) {
            return null;
        }
        return (bo) f2.c(bo.class);
    }

    public final FlurryEventRecordStatus a(String str, Map<String, String> map, int i2) {
        return a(str, map, false, i2);
    }

    public final FlurryEventRecordStatus a(String str, Map<String, String> map, boolean z) {
        return a(str, map, z, 0);
    }

    public final FlurryEventRecordStatus a(String str, Map<String, String> map, boolean z, int i2) {
        w wVar = new w(str, map, z, i2);
        synchronized (this.g) {
            switch (AnonymousClass2.b[c() - 1]) {
                case 1:
                    String str2 = f;
                    db.a(str2, "There is no active Flurry session. Adding this event to deferred queue and flush them when the session initializes. Event: " + wVar.a);
                    this.h.add(wVar);
                    FlurryEventRecordStatus flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventLoggingDelayed;
                    return flurryEventRecordStatus;
                case 2:
                    String str3 = f;
                    db.a(str3, "Waiting for Flurry session to initialize before logging event: " + wVar.a);
                    this.h.add(wVar);
                    FlurryEventRecordStatus flurryEventRecordStatus2 = FlurryEventRecordStatus.kFlurryEventLoggingDelayed;
                    return flurryEventRecordStatus2;
                case 3:
                    FlurryEventRecordStatus b2 = b(wVar);
                    return b2;
                default:
                    FlurryEventRecordStatus flurryEventRecordStatus3 = FlurryEventRecordStatus.kFlurryEventFailed;
                    return flurryEventRecordStatus3;
            }
        }
    }

    /* renamed from: com.flurry.sdk.x$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[ec.a.a().length];
        static final /* synthetic */ int[] b = new int[eb.a.a().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|6|7|9|10|12) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0011 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                int[] r0 = com.flurry.sdk.eb.a.a()
                int r0 = r0.length
                int[] r0 = new int[r0]
                b = r0
                r0 = 1
                int[] r1 = b     // Catch:{ NoSuchFieldError -> 0x0011 }
                int r2 = com.flurry.sdk.eb.a.a     // Catch:{ NoSuchFieldError -> 0x0011 }
                int r2 = r2 - r0
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0011 }
            L_0x0011:
                int[] r1 = b     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r2 = com.flurry.sdk.eb.a.b     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r2 = r2 - r0
                r3 = 2
                r1[r2] = r3     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                int[] r1 = b     // Catch:{ NoSuchFieldError -> 0x0021 }
                int r2 = com.flurry.sdk.eb.a.c     // Catch:{ NoSuchFieldError -> 0x0021 }
                int r2 = r2 - r0
                r3 = 3
                r1[r2] = r3     // Catch:{ NoSuchFieldError -> 0x0021 }
            L_0x0021:
                int[] r1 = com.flurry.sdk.ec.a.a()
                int r1 = r1.length
                int[] r1 = new int[r1]
                a = r1
                int[] r1 = a     // Catch:{ NoSuchFieldError -> 0x0031 }
                int r2 = com.flurry.sdk.ec.a.e     // Catch:{ NoSuchFieldError -> 0x0031 }
                int r2 = r2 - r0
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0031 }
            L_0x0031:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.x.AnonymousClass2.<clinit>():void");
        }
    }

    private static FlurryEventRecordStatus b(w wVar) {
        bo b2 = b();
        return b2 != null ? b2.a(wVar.a, wVar.b, wVar.c, wVar.d) : FlurryEventRecordStatus.kFlurryEventFailed;
    }

    private synchronized int c() {
        return ed.a().e();
    }

    public final void a(w wVar) {
        synchronized (this.g) {
            switch (AnonymousClass2.b[c() - 1]) {
                case 1:
                    String str = f;
                    db.a(str, "There is no active Flurry session. Adding this timed event to deferred queue and flush them when the session initializes. Timed event: " + wVar.a);
                    this.i.add(wVar);
                    return;
                case 2:
                    String str2 = f;
                    db.a(str2, "Waiting for Flurry session to initialize before ending timed event: " + wVar.a);
                    this.i.add(wVar);
                    return;
                case 3:
                    c(wVar);
                    return;
                default:
                    return;
            }
        }
    }

    private static void c(w wVar) {
        bo b2 = b();
        if (b2 != null) {
            b2.a(wVar.a, wVar.b);
        }
    }

    public final void a(String str, String str2, Throwable th, Map<String, String> map) {
        boolean z = str != null && "uncaught".equals(str);
        v vVar = new v(str, str2, th.getClass().getName(), th, eu.a(z), map);
        if (z && this.d != null) {
            List<es> a2 = this.d.a();
            vVar.g = a2;
            String str3 = f;
            db.a(4, str3, "Total breadcrumbs - " + a2.size());
        }
        a(vVar);
    }

    public final void a(v vVar) {
        synchronized (this.g) {
            switch (AnonymousClass2.b[c() - 1]) {
                case 1:
                    String str = f;
                    db.a(str, "There is no active Flurry session. Adding this logging error to deferred queue and flush them when the session initializes. Error: " + vVar.a);
                    this.j.add(vVar);
                    return;
                case 2:
                    String str2 = f;
                    db.a(str2, "Waiting for Flurry session to initialize before logging error: " + vVar.a);
                    this.j.add(vVar);
                    return;
                case 3:
                    b(vVar);
                    return;
                default:
                    return;
            }
        }
    }

    private static void b(v vVar) {
        bo b2 = b();
        if (b2 != null) {
            b2.a(vVar);
        }
    }

    static /* synthetic */ void a(x xVar) {
        db.a(f, "Flushing deferred events queues.");
        synchronized (xVar.g) {
            while (xVar.h.peek() != null) {
                b(xVar.h.poll());
            }
            while (xVar.j.peek() != null) {
                b(xVar.j.poll());
            }
            while (xVar.i.peek() != null) {
                c(xVar.i.poll());
            }
        }
    }
}
