package com.flurry.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import com.flurry.sdk.bl;
import com.flurry.sdk.by;
import com.flurry.sdk.ec;
import com.flurry.sdk.eh;
import com.flurry.sdk.ep;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;

public class bo implements eh.a {
    static final String a = "bo";
    static int b = 500;
    static int c = 10;
    static int d = 1000;
    static int e = 160000;
    static int f = 50;
    private final y A = new y();
    private long B;
    private String C;
    private String D;
    private int E = -1;
    private boolean F = true;
    private int G = 0;
    private int H = 0;
    private int I = 0;
    /* access modifiers changed from: private */
    public final cv<ec> J = new cv<ec>() {
        public final /* synthetic */ void a(cu cuVar) {
            ec ecVar = (ec) cuVar;
            if (bo.this.g == null || ecVar.b == bo.this.g.get()) {
                switch (AnonymousClass9.a[ecVar.d - 1]) {
                    case 1:
                        bo boVar = bo.this;
                        eb ebVar = ecVar.b;
                        Context context = (Context) ecVar.a.get();
                        boVar.g = new WeakReference<>(ebVar);
                        eg a2 = eg.a();
                        boVar.k = ((Boolean) a2.a("LogEvents")).booleanValue();
                        a2.a("LogEvents", (eh.a) boVar);
                        String str = bo.a;
                        db.a(4, str, "initSettings, LogEvents = " + boVar.k);
                        boVar.l = (String) a2.a("UserId");
                        a2.a("UserId", (eh.a) boVar);
                        String str2 = bo.a;
                        db.a(4, str2, "initSettings, UserId = " + boVar.l);
                        boVar.m = ((Byte) a2.a("Gender")).byteValue();
                        a2.a("Gender", (eh.a) boVar);
                        String str3 = bo.a;
                        db.a(4, str3, "initSettings, Gender = " + boVar.m);
                        boVar.n = (Long) a2.a("Age");
                        a2.a("Age", (eh.a) boVar);
                        String str4 = bo.a;
                        db.a(4, str4, "initSettings, BirthDate = " + boVar.n);
                        boVar.p = ((Boolean) a2.a("analyticsEnabled")).booleanValue();
                        a2.a("analyticsEnabled", (eh.a) boVar);
                        String str5 = bo.a;
                        db.a(4, str5, "initSettings, AnalyticsEnabled = " + boVar.p);
                        boVar.h = context.getFileStreamPath(".flurryagent." + Integer.toString(ck.a().b.hashCode(), 16));
                        boVar.i = new ct<>(context.getFileStreamPath(".yflurryreport." + Long.toString(em.g(ck.a().b), 16)), ".yflurryreport.", 1, new dz<List<bl>>() {
                            public final dw<List<bl>> a(int i) {
                                return new dv(new bl.a());
                            }
                        });
                        boVar.o = ebVar.a();
                        boVar.a(context);
                        boVar.a(true);
                        if (x.a().a != null) {
                            ck.a().b(new eo() {
                                public final void a() {
                                    x.a().a.a();
                                }
                            });
                        }
                        ck.a().b(new eo() {
                            public final void a() {
                                bo.this.e();
                            }
                        });
                        ck.a().b(new eo() {
                            public final void a() {
                                bo.d(bo.this);
                            }
                        });
                        ck.a().b(new eo() {
                            public final void a() {
                                bo.e(bo.this);
                            }
                        });
                        if (bs.a().c()) {
                            ck.a().b(new eo() {
                                public final void a() {
                                    bo boVar = bo.this;
                                    bq.a();
                                    boVar.a(true, bq.d());
                                }
                            });
                            return;
                        } else {
                            cw.a().a("com.flurry.android.sdk.IdProviderFinishedEvent", boVar.q);
                            return;
                        }
                    case 2:
                        bo boVar2 = bo.this;
                        ecVar.a.get();
                        boVar2.a();
                        return;
                    case 3:
                        bo boVar3 = bo.this;
                        ecVar.a.get();
                        boVar3.b();
                        return;
                    case 4:
                        cw.a().b("com.flurry.android.sdk.FlurrySessionEvent", bo.this.J);
                        bo.this.a(ecVar.e);
                        return;
                    default:
                        return;
                }
            }
        }
    };
    /* access modifiers changed from: package-private */
    public WeakReference<eb> g;
    File h;
    ct<List<bl>> i;
    public boolean j;
    boolean k;
    String l;
    byte m;
    Long n;
    boolean o;
    /* access modifiers changed from: package-private */
    public boolean p = true;
    final cv<bt> q = new cv<bt>() {
        public final /* synthetic */ void a(cu cuVar) {
            ck.a().b(new eo() {
                public final void a() {
                    bo boVar = bo.this;
                    bq.a();
                    boVar.a(true, bq.d());
                }
            });
        }
    };
    private final AtomicInteger r = new AtomicInteger(0);
    private final AtomicInteger s = new AtomicInteger(0);
    private final List<bl> t = new ArrayList();
    private final Map<String, List<String>> u = new HashMap();
    private final Map<String, String> v = new HashMap();
    private final Map<String, bh> w = new HashMap();
    private final List<bi> x = new ArrayList();
    private final List<bg> y = new ArrayList();
    private final List<String> z = new ArrayList();

    /* renamed from: com.flurry.sdk.bo$9  reason: invalid class name */
    static /* synthetic */ class AnonymousClass9 {
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
            throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.bo.AnonymousClass9.<clinit>():void");
        }
    }

    public bo() {
        cw.a().a("com.flurry.android.sdk.FlurrySessionEvent", this.J);
    }

    private static String d() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("logcat -d").getInputStream()));
            StringBuilder sb = new StringBuilder();
            int i2 = 0;
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null && i2 < 1000) {
                    sb.append(readLine);
                    sb.append("\n");
                    i2++;
                }
            }
            return sb.toString();
        } catch (IOException e2) {
            String str = a;
            db.a(6, str, "There was an issue grabbing logcat. " + e2.getMessage());
            return "";
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(Context context) {
        Bundle extras;
        if ((context instanceof Activity) && (extras = ((Activity) context).getIntent().getExtras()) != null) {
            String str = a;
            db.a(3, str, "Launch Options Bundle is present " + extras.toString());
            for (String str2 : extras.keySet()) {
                if (str2 != null) {
                    Object obj = extras.get(str2);
                    String obj2 = obj != null ? obj.toString() : "null";
                    this.u.put(str2, Collections.singletonList(obj2));
                    String str3 = a;
                    db.a(3, str3, "Launch options Key: " + str2 + ". Its value: " + obj2);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x017f  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0182  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x019d  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x01a0  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x01b8  */
    /* JADX WARNING: Removed duplicated region for block: B:51:? A[RETURN, SYNTHETIC] */
    @android.annotation.TargetApi(18)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(boolean r8) {
        /*
            r7 = this;
            if (r8 == 0) goto L_0x00cf
            java.util.Map<java.lang.String, java.lang.String> r0 = r7.v
            java.lang.String r1 = "boot.time"
            long r2 = java.lang.System.currentTimeMillis()
            long r4 = android.os.SystemClock.elapsedRealtime()
            long r2 = r2 - r4
            java.lang.String r2 = java.lang.Long.toString(r2)
            r0.put(r1, r2)
            android.os.StatFs r0 = new android.os.StatFs
            java.io.File r1 = android.os.Environment.getRootDirectory()
            java.lang.String r1 = r1.getAbsolutePath()
            r0.<init>(r1)
            android.os.StatFs r1 = new android.os.StatFs
            java.io.File r2 = android.os.Environment.getExternalStorageDirectory()
            java.lang.String r2 = r2.getAbsolutePath()
            r1.<init>(r2)
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 18
            if (r2 < r3) goto L_0x0073
            java.util.Map<java.lang.String, java.lang.String> r2 = r7.v
            java.lang.String r3 = "disk.size.total.internal"
            long r4 = r0.getAvailableBlocksLong()
            java.lang.String r4 = java.lang.Long.toString(r4)
            r2.put(r3, r4)
            java.util.Map<java.lang.String, java.lang.String> r2 = r7.v
            java.lang.String r3 = "disk.size.available.internal"
            long r4 = r0.getAvailableBlocksLong()
            java.lang.String r0 = java.lang.Long.toString(r4)
            r2.put(r3, r0)
            java.util.Map<java.lang.String, java.lang.String> r0 = r7.v
            java.lang.String r2 = "disk.size.total.external"
            long r3 = r1.getAvailableBlocksLong()
            java.lang.String r3 = java.lang.Long.toString(r3)
            r0.put(r2, r3)
            java.util.Map<java.lang.String, java.lang.String> r0 = r7.v
            java.lang.String r2 = "disk.size.available.external"
            long r3 = r1.getAvailableBlocksLong()
            java.lang.String r1 = java.lang.Long.toString(r3)
            r0.put(r2, r1)
            goto L_0x00b3
        L_0x0073:
            java.util.Map<java.lang.String, java.lang.String> r2 = r7.v
            java.lang.String r3 = "disk.size.total.internal"
            int r4 = r0.getAvailableBlocks()
            long r4 = (long) r4
            java.lang.String r4 = java.lang.Long.toString(r4)
            r2.put(r3, r4)
            java.util.Map<java.lang.String, java.lang.String> r2 = r7.v
            java.lang.String r3 = "disk.size.available.internal"
            int r0 = r0.getAvailableBlocks()
            long r4 = (long) r0
            java.lang.String r0 = java.lang.Long.toString(r4)
            r2.put(r3, r0)
            java.util.Map<java.lang.String, java.lang.String> r0 = r7.v
            java.lang.String r2 = "disk.size.total.external"
            int r3 = r1.getAvailableBlocks()
            long r3 = (long) r3
            java.lang.String r3 = java.lang.Long.toString(r3)
            r0.put(r2, r3)
            java.util.Map<java.lang.String, java.lang.String> r0 = r7.v
            java.lang.String r2 = "disk.size.available.external"
            int r1 = r1.getAvailableBlocks()
            long r3 = (long) r1
            java.lang.String r1 = java.lang.Long.toString(r3)
            r0.put(r2, r1)
        L_0x00b3:
            java.util.Map<java.lang.String, java.lang.String> r0 = r7.v
            java.lang.String r1 = "carrier.name"
            com.flurry.sdk.bz.a()
            java.lang.String r2 = com.flurry.sdk.bz.c()
            r0.put(r1, r2)
            java.util.Map<java.lang.String, java.lang.String> r0 = r7.v
            java.lang.String r1 = "carrier.details"
            com.flurry.sdk.bz.a()
            java.lang.String r2 = com.flurry.sdk.bz.d()
            r0.put(r1, r2)
        L_0x00cf:
            com.flurry.sdk.ck r0 = com.flurry.sdk.ck.a()
            android.content.Context r0 = r0.a
            java.lang.String r1 = "activity"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.app.ActivityManager r0 = (android.app.ActivityManager) r0
            android.app.ActivityManager$MemoryInfo r1 = new android.app.ActivityManager$MemoryInfo
            r1.<init>()
            r0.getMemoryInfo(r1)
            java.util.Map<java.lang.String, java.lang.String> r0 = r7.v
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "memory.available"
            r2.<init>(r3)
            if (r8 == 0) goto L_0x00f3
            java.lang.String r3 = ".start"
            goto L_0x00f5
        L_0x00f3:
            java.lang.String r3 = ".end"
        L_0x00f5:
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            long r3 = r1.availMem
            java.lang.String r3 = java.lang.Long.toString(r3)
            r0.put(r2, r3)
            int r0 = android.os.Build.VERSION.SDK_INT
            r2 = 16
            if (r0 < r2) goto L_0x012b
            java.util.Map<java.lang.String, java.lang.String> r0 = r7.v
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "memory.total"
            r2.<init>(r3)
            if (r8 == 0) goto L_0x0119
            java.lang.String r3 = ".start"
            goto L_0x011b
        L_0x0119:
            java.lang.String r3 = ".end"
        L_0x011b:
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            long r3 = r1.availMem
            java.lang.String r1 = java.lang.Long.toString(r3)
            r0.put(r2, r1)
        L_0x012b:
            r0 = 5
            r1 = 0
            r2 = -1
            android.content.IntentFilter r3 = new android.content.IntentFilter     // Catch:{ Exception -> 0x015e }
            java.lang.String r4 = "android.intent.action.BATTERY_CHANGED"
            r3.<init>(r4)     // Catch:{ Exception -> 0x015e }
            com.flurry.sdk.ck r4 = com.flurry.sdk.ck.a()     // Catch:{ Exception -> 0x015e }
            android.content.Context r4 = r4.a     // Catch:{ Exception -> 0x015e }
            r5 = 0
            android.content.Intent r3 = r4.registerReceiver(r5, r3)     // Catch:{ Exception -> 0x015e }
            if (r3 == 0) goto L_0x0170
            java.lang.String r4 = "status"
            int r4 = r3.getIntExtra(r4, r2)     // Catch:{ Exception -> 0x015e }
            r5 = 2
            if (r4 == r5) goto L_0x014d
            if (r4 != r0) goto L_0x014e
        L_0x014d:
            r1 = 1
        L_0x014e:
            java.lang.String r4 = "level"
            int r4 = r3.getIntExtra(r4, r2)     // Catch:{ Exception -> 0x015e }
            java.lang.String r5 = "scale"
            int r3 = r3.getIntExtra(r5, r2)     // Catch:{ Exception -> 0x015c }
            r2 = r4
            goto L_0x0171
        L_0x015c:
            r3 = move-exception
            goto L_0x0160
        L_0x015e:
            r3 = move-exception
            r4 = -1
        L_0x0160:
            java.lang.String r5 = a
            java.lang.String r6 = "Error getting battery status: "
            java.lang.String r3 = java.lang.String.valueOf(r3)
            java.lang.String r3 = r6.concat(r3)
            com.flurry.sdk.db.a((int) r0, (java.lang.String) r5, (java.lang.String) r3)
            r2 = r4
        L_0x0170:
            r3 = -1
        L_0x0171:
            float r0 = (float) r2
            float r2 = (float) r3
            float r0 = r0 / r2
            java.util.Map<java.lang.String, java.lang.String> r2 = r7.v
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "battery.charging"
            r3.<init>(r4)
            if (r8 == 0) goto L_0x0182
            java.lang.String r4 = ".start"
            goto L_0x0184
        L_0x0182:
            java.lang.String r4 = ".end"
        L_0x0184:
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.String r1 = java.lang.Boolean.toString(r1)
            r2.put(r3, r1)
            java.util.Map<java.lang.String, java.lang.String> r1 = r7.v
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "battery.remaining"
            r2.<init>(r3)
            if (r8 == 0) goto L_0x01a0
            java.lang.String r8 = ".start"
            goto L_0x01a2
        L_0x01a0:
            java.lang.String r8 = ".end"
        L_0x01a2:
            r2.append(r8)
            java.lang.String r8 = r2.toString()
            java.lang.String r0 = java.lang.Float.toString(r0)
            r1.put(r8, r0)
            com.flurry.sdk.da r8 = com.flurry.sdk.da.a()
            boolean r8 = r8.b
            if (r8 == 0) goto L_0x01dc
            com.flurry.sdk.da r8 = com.flurry.sdk.da.a()
            java.lang.String r8 = r8.b()
            java.util.Map<java.lang.String, java.lang.String> r0 = r7.v
            java.lang.String r1 = "instantapp.name"
            if (r8 == 0) goto L_0x01c8
            r2 = r8
            goto L_0x01ca
        L_0x01c8:
            java.lang.String r2 = "instant app"
        L_0x01ca:
            r0.put(r1, r2)
            java.lang.String r0 = a
            java.lang.String r1 = "instantAppName: "
            java.lang.String r8 = java.lang.String.valueOf(r8)
            java.lang.String r8 = r1.concat(r8)
            com.flurry.sdk.db.c(r0, r8)
        L_0x01dc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.bo.a(boolean):void");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0062, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void e() {
        /*
            r7 = this;
            monitor-enter(r7)
            com.flurry.sdk.em.a()     // Catch:{ all -> 0x0063 }
            java.lang.String r0 = a     // Catch:{ all -> 0x0063 }
            java.lang.String r1 = "Loading persistent session report data."
            r2 = 4
            com.flurry.sdk.db.a((int) r2, (java.lang.String) r0, (java.lang.String) r1)     // Catch:{ all -> 0x0063 }
            com.flurry.sdk.ct<java.util.List<com.flurry.sdk.bl>> r0 = r7.i     // Catch:{ all -> 0x0063 }
            java.lang.Object r0 = r0.a()     // Catch:{ all -> 0x0063 }
            java.util.List r0 = (java.util.List) r0     // Catch:{ all -> 0x0063 }
            if (r0 == 0) goto L_0x001d
            java.util.List<com.flurry.sdk.bl> r1 = r7.t     // Catch:{ all -> 0x0063 }
            r1.addAll(r0)     // Catch:{ all -> 0x0063 }
            monitor-exit(r7)
            return
        L_0x001d:
            java.io.File r0 = r7.h     // Catch:{ all -> 0x0063 }
            boolean r0 = r0.exists()     // Catch:{ all -> 0x0063 }
            if (r0 == 0) goto L_0x0061
            java.lang.String r0 = a     // Catch:{ all -> 0x0063 }
            java.lang.String r1 = "Legacy persistent agent data found, converting."
            com.flurry.sdk.db.a((int) r2, (java.lang.String) r0, (java.lang.String) r1)     // Catch:{ all -> 0x0063 }
            java.io.File r0 = r7.h     // Catch:{ all -> 0x0063 }
            com.flurry.sdk.bp r0 = com.flurry.sdk.aa.a(r0)     // Catch:{ all -> 0x0063 }
            if (r0 == 0) goto L_0x0059
            boolean r1 = r0.b     // Catch:{ all -> 0x0063 }
            long r2 = r0.c     // Catch:{ all -> 0x0063 }
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 > 0) goto L_0x0045
            com.flurry.sdk.bq.a()     // Catch:{ all -> 0x0063 }
            long r2 = com.flurry.sdk.bq.d()     // Catch:{ all -> 0x0063 }
        L_0x0045:
            r7.j = r1     // Catch:{ all -> 0x0063 }
            r7.B = r2     // Catch:{ all -> 0x0063 }
            r7.f()     // Catch:{ all -> 0x0063 }
            java.util.List<com.flurry.sdk.bl> r0 = r0.a     // Catch:{ all -> 0x0063 }
            java.util.List r0 = java.util.Collections.unmodifiableList(r0)     // Catch:{ all -> 0x0063 }
            if (r0 == 0) goto L_0x0059
            java.util.List<com.flurry.sdk.bl> r1 = r7.t     // Catch:{ all -> 0x0063 }
            r1.addAll(r0)     // Catch:{ all -> 0x0063 }
        L_0x0059:
            java.io.File r0 = r7.h     // Catch:{ all -> 0x0063 }
            r0.delete()     // Catch:{ all -> 0x0063 }
            r7.g()     // Catch:{ all -> 0x0063 }
        L_0x0061:
            monitor-exit(r7)
            return
        L_0x0063:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.bo.e():void");
    }

    /* access modifiers changed from: private */
    public synchronized void a(boolean z2, long j2) {
        byte[] bArr;
        synchronized (this) {
            if (!this.p) {
                db.a(3, a, "Analytics disabled, not sending agent report.");
                return;
            }
            if (!z2) {
                if (this.t.isEmpty()) {
                    return;
                }
            }
            db.a(3, a, "generating agent report with " + this.t.size() + " session reports.");
            try {
                bArr = new bj(ck.a().b, cb.a().e(), this.j, bs.a().f(), this.B, j2, this.t, Collections.unmodifiableMap(bs.a().a), this.A.a(), this.u, cm.a().c(), System.currentTimeMillis(), eg.a(), this.o).a;
            } catch (Exception e2) {
                db.e(a, "Exception while generating report: ".concat(String.valueOf(e2)));
                bArr = null;
            }
            if (bArr == null) {
                db.e(a, "Error generating report");
            } else {
                db.a(3, a, "generated report of size " + bArr.length + " with " + this.t.size() + " reports.");
                bk bkVar = x.a().b;
                StringBuilder sb = new StringBuilder();
                sb.append(cl.b());
                bkVar.b(bArr, ck.a().b, sb.toString());
            }
            this.t.clear();
            this.i.b();
        }
    }

    /* access modifiers changed from: private */
    public void f() {
        SharedPreferences.Editor edit = ck.a().a.getSharedPreferences("FLURRY_SHARED_PREFERENCES", 0).edit();
        edit.putBoolean("com.flurry.sdk.previous_successful_report", this.j);
        edit.putLong("com.flurry.sdk.initial_run_time", this.B);
        edit.putString("com.flurry.sdk.api_key", ck.a().b);
        edit.apply();
    }

    private synchronized void g() {
        db.a(4, a, "Saving persistent agent data.");
        this.i.a(this.t);
    }

    public final synchronized void a() {
        this.E = ek.a();
        if (x.a().c != null) {
            ck.a().b(new eo() {
                public final void a() {
                    au auVar = x.a().c;
                    auVar.c = false;
                    ck.a().b(new eo() {
                        public final void a() {
                            dn.this.b();
                        }
                    });
                }
            });
        }
        if (this.p && x.a().a != null) {
            ck.a().b(new eo() {
                public final void a() {
                    x.a().a.b();
                }
            });
        }
    }

    public final synchronized void b() {
        h();
    }

    private synchronized void h() {
        a(false);
        bq.a();
        final long d2 = bq.d();
        final long f2 = bq.f();
        final long h2 = bq.h();
        final int l2 = bq.l() - 1;
        final String i2 = bq.i();
        final String j2 = bq.j();
        final Map<String, String> k2 = bq.k();
        if (this.p && x.a().a != null) {
            ck.a().b(new eo() {
                public final void a() {
                    x.a().a.a(d2);
                }
            });
        }
        ck.a().b(new eo() {
            public final void a() {
                bo.this.f();
            }
        });
        if (bs.a().c()) {
            ck.a().b(new eo() {
                public final void a() {
                    bo.a(bo.this, d2, f2, h2, l2, i2, j2, k2);
                }
            });
        }
    }

    private synchronized bl a(long j2, long j3, long j4, int i2, String str, String str2, Map<String, String> map) {
        bl blVar;
        bm bmVar = new bm();
        bmVar.s = ((Boolean) eg.a().a("IncludeBackgroundSessionsInMetrics")).booleanValue();
        if (this.o) {
            bmVar.r = ep.a.BACKGROUND.e;
        } else {
            bmVar.r = ep.a.ACTIVE.e;
        }
        bmVar.a = cb.a().e();
        bmVar.b = j2;
        bmVar.c = j3;
        bmVar.d = j4;
        bmVar.e = this.v;
        bmVar.f = str;
        bmVar.g = str2;
        bmVar.h = map;
        bv.a();
        bmVar.i = bv.b();
        bv.a();
        bmVar.j = TimeZone.getDefault().getID();
        bmVar.k = i2;
        bmVar.l = this.E != -1 ? this.E : ek.a();
        bmVar.m = this.l == null ? "" : this.l;
        bmVar.n = bw.a().g();
        bmVar.o = this.I;
        bmVar.x = ep.a(ck.a().a).i;
        bmVar.p = this.m;
        bmVar.q = this.n;
        bmVar.t = this.w;
        List<bi> list = this.x;
        String str3 = a;
        db.a(3, str3, "Total events in session report: " + list.size());
        bmVar.u = list;
        bmVar.w = this.F;
        bmVar.z = this.y;
        bmVar.y = this.H;
        String str4 = a;
        db.a(3, str4, "Total errors in session report: " + this.H);
        bmVar.v = this.z;
        bmVar.A = this.D;
        blVar = null;
        try {
            blVar = new bl(bmVar);
        } catch (IOException e2) {
            db.a(5, a, "Error creating analytics session report: ".concat(String.valueOf(e2)));
        }
        if (blVar == null) {
            db.e(a, "New session report wasn't created");
        }
        return blVar;
    }

    private synchronized bl a(long j2, long j3, bg bgVar) {
        bl blVar;
        bm bmVar = new bm();
        bmVar.s = false;
        bmVar.r = ep.a.UNKNOWN.e;
        bmVar.a = cb.a().e();
        bmVar.b = j2;
        bmVar.c = j3;
        bmVar.d = 0;
        blVar = null;
        bmVar.e = null;
        bmVar.f = null;
        bmVar.g = null;
        bmVar.h = null;
        bmVar.i = "";
        bmVar.j = "";
        bmVar.k = by.a.a - 1;
        bmVar.l = 0;
        bmVar.m = "";
        bmVar.n = null;
        bmVar.o = 0;
        bmVar.x = ep.a(ck.a().a).i;
        bmVar.p = -1;
        bmVar.q = null;
        bmVar.t = null;
        bmVar.u = null;
        bmVar.w = true;
        ArrayList arrayList = new ArrayList();
        arrayList.add(bgVar);
        bmVar.z = arrayList;
        bmVar.y = arrayList.size();
        String str = a;
        db.a(3, str, "Total errors in native crash session report: " + arrayList.size());
        bmVar.v = new ArrayList();
        bmVar.A = null;
        try {
            blVar = new bl(bmVar);
        } catch (IOException e2) {
            db.a(5, a, "Error creating analytics native crash session report: ".concat(String.valueOf(e2)));
        }
        if (blVar == null) {
            db.e(a, "New native crash session report wasn't created");
        }
        return blVar;
    }

    private synchronized void b(long j2) {
        for (bi next : this.x) {
            if (next.b && !next.c) {
                next.a(j2);
            }
        }
    }

    public final synchronized void a(long j2) {
        synchronized (this) {
            cw.a().a((cv<?>) this.q);
            bq.a();
            b(bq.f());
            ck.a().b(new eo() {
                public final void a() {
                    if (bo.this.p && x.a().a != null) {
                        x.a().a.c();
                    }
                    if (x.a().c != null) {
                        ck.a().b(new eo() {
                            public final void a() {
                                x.a().c.c = true;
                            }
                        });
                    }
                }
            });
            if (bs.a().c()) {
                bq.a();
                final long f2 = bq.f();
                final long h2 = bq.h();
                final int l2 = bq.l() - 1;
                final String i2 = bq.i();
                final String j3 = bq.j();
                final Map<String, String> k2 = bq.k();
                final long j4 = j2;
                ck.a().b(new eo() {
                    public final void a() {
                        bo.a(bo.this, j4, f2, h2, l2, i2, j3, k2);
                        bo.this.a(false, j4);
                    }
                });
            }
            eg.a().b("Gender", this);
            eg.a().b("UserId", this);
            eg.a().b("Age", this);
            eg.a().b("LogEvents", this);
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.lang.String r3, java.lang.Object r4) {
        /*
            r2 = this;
            int r0 = r3.hashCode()
            r1 = 4
            switch(r0) {
                case -1752163738: goto L_0x0031;
                case -1720015653: goto L_0x0027;
                case -738063011: goto L_0x001d;
                case 65759: goto L_0x0013;
                case 2129321697: goto L_0x0009;
                default: goto L_0x0008;
            }
        L_0x0008:
            goto L_0x003b
        L_0x0009:
            java.lang.String r0 = "Gender"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x003b
            r3 = 2
            goto L_0x003c
        L_0x0013:
            java.lang.String r0 = "Age"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x003b
            r3 = 3
            goto L_0x003c
        L_0x001d:
            java.lang.String r0 = "LogEvents"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x003b
            r3 = 0
            goto L_0x003c
        L_0x0027:
            java.lang.String r0 = "analyticsEnabled"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x003b
            r3 = 4
            goto L_0x003c
        L_0x0031:
            java.lang.String r0 = "UserId"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x003b
            r3 = 1
            goto L_0x003c
        L_0x003b:
            r3 = -1
        L_0x003c:
            switch(r3) {
                case 0: goto L_0x00b8;
                case 1: goto L_0x009e;
                case 2: goto L_0x0080;
                case 3: goto L_0x0066;
                case 4: goto L_0x0048;
                default: goto L_0x003f;
            }
        L_0x003f:
            r3 = 6
            java.lang.String r4 = a
            java.lang.String r0 = "onSettingUpdate internal error!"
            com.flurry.sdk.db.a((int) r3, (java.lang.String) r4, (java.lang.String) r0)
            return
        L_0x0048:
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r3 = r4.booleanValue()
            r2.p = r3
            java.lang.String r3 = a
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r0 = "onSettingUpdate, AnalyticsEnabled = "
            r4.<init>(r0)
            boolean r0 = r2.p
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            com.flurry.sdk.db.a((int) r1, (java.lang.String) r3, (java.lang.String) r4)
            return
        L_0x0066:
            java.lang.Long r4 = (java.lang.Long) r4
            r2.n = r4
            java.lang.String r3 = a
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r0 = "onSettingUpdate, Birthdate = "
            r4.<init>(r0)
            java.lang.Long r0 = r2.n
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            com.flurry.sdk.db.a((int) r1, (java.lang.String) r3, (java.lang.String) r4)
            return
        L_0x0080:
            java.lang.Byte r4 = (java.lang.Byte) r4
            byte r3 = r4.byteValue()
            r2.m = r3
            java.lang.String r3 = a
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r0 = "onSettingUpdate, Gender = "
            r4.<init>(r0)
            byte r0 = r2.m
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            com.flurry.sdk.db.a((int) r1, (java.lang.String) r3, (java.lang.String) r4)
            return
        L_0x009e:
            java.lang.String r4 = (java.lang.String) r4
            r2.l = r4
            java.lang.String r3 = a
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r0 = "onSettingUpdate, UserId = "
            r4.<init>(r0)
            java.lang.String r0 = r2.l
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            com.flurry.sdk.db.a((int) r1, (java.lang.String) r3, (java.lang.String) r4)
            return
        L_0x00b8:
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r3 = r4.booleanValue()
            r2.k = r3
            java.lang.String r3 = a
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r0 = "onSettingUpdate, LogEvents = "
            r4.<init>(r0)
            boolean r0 = r2.k
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            com.flurry.sdk.db.a((int) r1, (java.lang.String) r3, (java.lang.String) r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.bo.a(java.lang.String, java.lang.Object):void");
    }

    public final synchronized void c() {
        this.I++;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:51:0x016d, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized com.flurry.android.FlurryEventRecordStatus a(java.lang.String r16, java.util.Map<java.lang.String, java.lang.String> r17, boolean r18, int r19) {
        /*
            r15 = this;
            r1 = r15
            monitor-enter(r15)
            com.flurry.android.FlurryEventRecordStatus r2 = com.flurry.android.FlurryEventRecordStatus.kFlurryEventRecorded     // Catch:{ all -> 0x016e }
            boolean r3 = r1.p     // Catch:{ all -> 0x016e }
            if (r3 != 0) goto L_0x0013
            com.flurry.android.FlurryEventRecordStatus r0 = com.flurry.android.FlurryEventRecordStatus.kFlurryEventAnalyticsDisabled     // Catch:{ all -> 0x016e }
            java.lang.String r2 = a     // Catch:{ all -> 0x016e }
            java.lang.String r3 = "Analytics has been disabled, not logging event."
            com.flurry.sdk.db.e(r2, r3)     // Catch:{ all -> 0x016e }
            monitor-exit(r15)
            return r0
        L_0x0013:
            long r3 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x016e }
            com.flurry.sdk.bq.a()     // Catch:{ all -> 0x016e }
            long r5 = com.flurry.sdk.bq.e()     // Catch:{ all -> 0x016e }
            r7 = 0
            long r12 = r3 - r5
            java.lang.String r3 = com.flurry.sdk.em.b((java.lang.String) r16)     // Catch:{ all -> 0x016e }
            int r4 = r3.length()     // Catch:{ all -> 0x016e }
            if (r4 != 0) goto L_0x002f
            com.flurry.android.FlurryEventRecordStatus r0 = com.flurry.android.FlurryEventRecordStatus.kFlurryEventFailed     // Catch:{ all -> 0x016e }
            monitor-exit(r15)
            return r0
        L_0x002f:
            java.util.Map<java.lang.String, com.flurry.sdk.bh> r4 = r1.w     // Catch:{ all -> 0x016e }
            java.lang.Object r4 = r4.get(r3)     // Catch:{ all -> 0x016e }
            com.flurry.sdk.bh r4 = (com.flurry.sdk.bh) r4     // Catch:{ all -> 0x016e }
            r5 = 1
            if (r4 != 0) goto L_0x0072
            java.util.Map<java.lang.String, com.flurry.sdk.bh> r4 = r1.w     // Catch:{ all -> 0x016e }
            int r4 = r4.size()     // Catch:{ all -> 0x016e }
            int r6 = b     // Catch:{ all -> 0x016e }
            if (r4 >= r6) goto L_0x0060
            com.flurry.sdk.bh r4 = new com.flurry.sdk.bh     // Catch:{ all -> 0x016e }
            r4.<init>()     // Catch:{ all -> 0x016e }
            r4.a = r5     // Catch:{ all -> 0x016e }
            java.util.Map<java.lang.String, com.flurry.sdk.bh> r5 = r1.w     // Catch:{ all -> 0x016e }
            r5.put(r3, r4)     // Catch:{ all -> 0x016e }
            java.lang.String r4 = a     // Catch:{ all -> 0x016e }
            java.lang.String r5 = "Event count started: "
            java.lang.String r6 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x016e }
            java.lang.String r5 = r5.concat(r6)     // Catch:{ all -> 0x016e }
            com.flurry.sdk.db.e(r4, r5)     // Catch:{ all -> 0x016e }
            goto L_0x0088
        L_0x0060:
            java.lang.String r2 = a     // Catch:{ all -> 0x016e }
            java.lang.String r4 = "Too many different events. Event not counted: "
            java.lang.String r5 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x016e }
            java.lang.String r4 = r4.concat(r5)     // Catch:{ all -> 0x016e }
            com.flurry.sdk.db.e(r2, r4)     // Catch:{ all -> 0x016e }
            com.flurry.android.FlurryEventRecordStatus r2 = com.flurry.android.FlurryEventRecordStatus.kFlurryEventUniqueCountExceeded     // Catch:{ all -> 0x016e }
            goto L_0x0088
        L_0x0072:
            int r2 = r4.a     // Catch:{ all -> 0x016e }
            int r2 = r2 + r5
            r4.a = r2     // Catch:{ all -> 0x016e }
            java.lang.String r2 = a     // Catch:{ all -> 0x016e }
            java.lang.String r4 = "Event count incremented: "
            java.lang.String r5 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x016e }
            java.lang.String r4 = r4.concat(r5)     // Catch:{ all -> 0x016e }
            com.flurry.sdk.db.e(r2, r4)     // Catch:{ all -> 0x016e }
            com.flurry.android.FlurryEventRecordStatus r2 = com.flurry.android.FlurryEventRecordStatus.kFlurryEventRecorded     // Catch:{ all -> 0x016e }
        L_0x0088:
            boolean r4 = r1.k     // Catch:{ all -> 0x016e }
            r5 = 0
            if (r4 == 0) goto L_0x016a
            java.util.List<com.flurry.sdk.bi> r4 = r1.x     // Catch:{ all -> 0x016e }
            int r4 = r4.size()     // Catch:{ all -> 0x016e }
            int r6 = d     // Catch:{ all -> 0x016e }
            if (r4 >= r6) goto L_0x016a
            int r4 = r1.G     // Catch:{ all -> 0x016e }
            int r6 = e     // Catch:{ all -> 0x016e }
            if (r4 >= r6) goto L_0x016a
            if (r17 != 0) goto L_0x00a4
            java.util.Map r2 = java.util.Collections.emptyMap()     // Catch:{ all -> 0x016e }
            goto L_0x00a6
        L_0x00a4:
            r2 = r17
        L_0x00a6:
            int r4 = r2.size()     // Catch:{ all -> 0x016e }
            int r4 = r4 - r19
            int r6 = c     // Catch:{ all -> 0x016e }
            if (r4 <= r6) goto L_0x00cd
            java.lang.String r3 = a     // Catch:{ all -> 0x016e }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x016e }
            java.lang.String r5 = "MaxEventParams exceeded: "
            r4.<init>(r5)     // Catch:{ all -> 0x016e }
            int r2 = r2.size()     // Catch:{ all -> 0x016e }
            int r2 = r2 - r19
            r4.append(r2)     // Catch:{ all -> 0x016e }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x016e }
            com.flurry.sdk.db.e(r3, r0)     // Catch:{ all -> 0x016e }
            com.flurry.android.FlurryEventRecordStatus r2 = com.flurry.android.FlurryEventRecordStatus.kFlurryEventParamsCountExceeded     // Catch:{ all -> 0x016e }
            goto L_0x016c
        L_0x00cd:
            com.flurry.sdk.bi r0 = new com.flurry.sdk.bi     // Catch:{ all -> 0x016e }
            java.util.concurrent.atomic.AtomicInteger r4 = r1.r     // Catch:{ all -> 0x016e }
            int r9 = r4.incrementAndGet()     // Catch:{ all -> 0x016e }
            r8 = r0
            r10 = r3
            r11 = r2
            r14 = r18
            r8.<init>(r9, r10, r11, r12, r14)     // Catch:{ all -> 0x016e }
            byte[] r4 = r0.b()     // Catch:{ all -> 0x016e }
            int r4 = r4.length     // Catch:{ all -> 0x016e }
            int r6 = r1.G     // Catch:{ all -> 0x016e }
            int r4 = r4 + r6
            int r6 = e     // Catch:{ all -> 0x016e }
            if (r4 > r6) goto L_0x015a
            java.util.List<com.flurry.sdk.bi> r4 = r1.x     // Catch:{ all -> 0x016e }
            r4.add(r0)     // Catch:{ all -> 0x016e }
            int r4 = r1.G     // Catch:{ all -> 0x016e }
            byte[] r5 = r0.b()     // Catch:{ all -> 0x016e }
            int r5 = r5.length     // Catch:{ all -> 0x016e }
            int r4 = r4 + r5
            r1.G = r4     // Catch:{ all -> 0x016e }
            com.flurry.android.FlurryEventRecordStatus r4 = com.flurry.android.FlurryEventRecordStatus.kFlurryEventRecorded     // Catch:{ all -> 0x016e }
            java.lang.String r5 = "Flurry.purchase"
            boolean r5 = r5.equals(r3)     // Catch:{ all -> 0x016e }
            if (r5 == 0) goto L_0x0140
            java.util.Map r5 = r0.a()     // Catch:{ all -> 0x016e }
            java.lang.String r6 = "fl.OrderJSON"
            java.lang.Object r6 = r5.get(r6)     // Catch:{ all -> 0x016e }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x016e }
            java.lang.String r7 = "fl.OrderJSONSignature"
            java.lang.Object r7 = r5.get(r7)     // Catch:{ all -> 0x016e }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x016e }
            if (r6 == 0) goto L_0x0140
            if (r7 == 0) goto L_0x0140
            java.lang.String r8 = "fl.OrderJSON"
            r5.remove(r8)     // Catch:{ all -> 0x016e }
            java.lang.String r8 = "fl.OrderJSONSignature"
            r5.remove(r8)     // Catch:{ all -> 0x016e }
            r0.b(r5)     // Catch:{ all -> 0x016e }
            java.util.List<java.lang.String> r0 = r1.z     // Catch:{ all -> 0x016e }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x016e }
            r5.<init>()     // Catch:{ all -> 0x016e }
            r5.append(r7)     // Catch:{ all -> 0x016e }
            r7 = 10
            r5.append(r7)     // Catch:{ all -> 0x016e }
            r5.append(r6)     // Catch:{ all -> 0x016e }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x016e }
            r0.add(r5)     // Catch:{ all -> 0x016e }
        L_0x0140:
            boolean r0 = r1.p     // Catch:{ all -> 0x016e }
            if (r0 == 0) goto L_0x0158
            com.flurry.sdk.x r0 = com.flurry.sdk.x.a()     // Catch:{ all -> 0x016e }
            com.flurry.sdk.as r0 = r0.a     // Catch:{ all -> 0x016e }
            if (r0 == 0) goto L_0x0158
            com.flurry.sdk.ck r0 = com.flurry.sdk.ck.a()     // Catch:{ all -> 0x016e }
            com.flurry.sdk.bo$8 r5 = new com.flurry.sdk.bo$8     // Catch:{ all -> 0x016e }
            r5.<init>(r3, r2)     // Catch:{ all -> 0x016e }
            r0.b(r5)     // Catch:{ all -> 0x016e }
        L_0x0158:
            r2 = r4
            goto L_0x016c
        L_0x015a:
            int r0 = e     // Catch:{ all -> 0x016e }
            r1.G = r0     // Catch:{ all -> 0x016e }
            r1.F = r5     // Catch:{ all -> 0x016e }
            java.lang.String r0 = a     // Catch:{ all -> 0x016e }
            java.lang.String r2 = "Event Log size exceeded. No more event details logged."
            com.flurry.sdk.db.e(r0, r2)     // Catch:{ all -> 0x016e }
            com.flurry.android.FlurryEventRecordStatus r2 = com.flurry.android.FlurryEventRecordStatus.kFlurryEventLogCountExceeded     // Catch:{ all -> 0x016e }
            goto L_0x016c
        L_0x016a:
            r1.F = r5     // Catch:{ all -> 0x016e }
        L_0x016c:
            monitor-exit(r15)
            return r2
        L_0x016e:
            r0 = move-exception
            monitor-exit(r15)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.bo.a(java.lang.String, java.util.Map, boolean, int):com.flurry.android.FlurryEventRecordStatus");
    }

    public final synchronized void a(String str, Map<String, String> map) {
        boolean z2;
        for (bi next : this.x) {
            if (!next.b || next.d != 0 || !next.a.equals(str)) {
                z2 = false;
                continue;
            } else {
                z2 = true;
                continue;
            }
            if (z2) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                bq.a();
                long e2 = elapsedRealtime - bq.e();
                if (map != null && map.size() > 0 && this.G < e) {
                    int length = this.G - next.b().length;
                    HashMap hashMap = new HashMap(next.a());
                    next.a(map);
                    if (next.b().length + length > e) {
                        next.b(hashMap);
                        this.F = false;
                        this.G = e;
                        db.e(a, "Event Log size exceeded. No more event details logged.");
                    } else if (next.a().size() > c) {
                        String str2 = a;
                        db.e(str2, "MaxEventParams exceeded on endEvent: " + next.a().size());
                        next.b(hashMap);
                    } else {
                        this.G = length + next.b().length;
                    }
                }
                next.a(e2);
                return;
            }
        }
    }

    public final synchronized void a(v vVar) {
        int i2 = 0;
        boolean z2 = vVar.a != null && "uncaught".equals(vVar.a);
        this.H++;
        if (this.y.size() < f) {
            bg bgVar = new bg(this.s.incrementAndGet(), Long.valueOf(System.currentTimeMillis()).longValue(), vVar.a, vVar.b, vVar.c, vVar.d, vVar.e, vVar.f);
            bgVar.a(vVar.g);
            this.y.add(bgVar);
            db.e(a, "Error logged: " + bgVar.a);
        } else if (z2) {
            while (i2 < this.y.size()) {
                bg bgVar2 = this.y.get(i2);
                if (bgVar2.a == null || "uncaught".equals(bgVar2.a)) {
                    i2++;
                } else {
                    bg bgVar3 = new bg(this.s.incrementAndGet(), Long.valueOf(System.currentTimeMillis()).longValue(), vVar.a, vVar.b, vVar.c, vVar.d, vVar.e, vVar.f);
                    bgVar3.a(vVar.g);
                    this.y.set(i2, bgVar3);
                    return;
                }
            }
        } else {
            db.e(a, "Max errors logged. No more errors logged.");
        }
    }

    static /* synthetic */ void d(bo boVar) {
        SharedPreferences sharedPreferences = ck.a().a.getSharedPreferences("FLURRY_SHARED_PREFERENCES", 0);
        boVar.j = sharedPreferences.getBoolean("com.flurry.sdk.previous_successful_report", false);
        bq.a();
        boVar.B = sharedPreferences.getLong("com.flurry.sdk.initial_run_time", bq.d());
        boVar.C = sharedPreferences.getString("com.flurry.sdk.api_key", "");
        boVar.D = sharedPreferences.getString("com.flurry.sdk.variant_ids", (String) null);
        if (TextUtils.isEmpty(boVar.C) && boVar.B > 0) {
            boVar.C = ck.a().b;
        } else if (!boVar.C.equals(ck.a().b)) {
            bq.a();
            boVar.B = bq.d();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x00d8  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0101  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0114  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0143  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void e(com.flurry.sdk.bo r29) {
        /*
            com.flurry.sdk.em.a()
            com.flurry.sdk.ck r0 = com.flurry.sdk.ck.a()
            android.content.Context r0 = r0.a
            java.lang.String r1 = ".yflurrynativecrash"
            java.io.File r0 = r0.getFileStreamPath(r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = ".*"
            r1.<init>(r2)
            java.lang.String r2 = ".dmp"
            java.lang.String r2 = java.util.regex.Pattern.quote(r2)
            r1.append(r2)
            java.lang.String r2 = "$"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.util.regex.Pattern r1 = java.util.regex.Pattern.compile(r1)
            java.lang.String[] r1 = com.flurry.sdk.el.a((java.io.File) r0, (java.util.regex.Pattern) r1)
            int r2 = r1.length
            r4 = 0
        L_0x0032:
            if (r4 >= r2) goto L_0x0151
            r5 = r1[r4]
            r6 = 3
            java.lang.String r7 = a
            java.lang.String r8 = "Native crash occurred in previous session! Found minidump file - "
            java.lang.String r9 = java.lang.String.valueOf(r5)
            java.lang.String r8 = r8.concat(r9)
            com.flurry.sdk.db.a((int) r6, (java.lang.String) r7, (java.lang.String) r8)
            java.lang.String r6 = com.flurry.sdk.ep.a(r0, r5)
            boolean r7 = android.text.TextUtils.isEmpty(r6)
            r8 = 1
            r9 = 6
            if (r7 == 0) goto L_0x005b
            java.lang.String r7 = a
            java.lang.String r10 = "There was no breadcrumbs file associated with the minidump file."
            com.flurry.sdk.db.a((int) r9, (java.lang.String) r7, (java.lang.String) r10)
            r7 = 1
            goto L_0x005c
        L_0x005b:
            r7 = 0
        L_0x005c:
            java.lang.String r10 = a
            java.lang.String r11 = "Breadcrumbs file associated with minidump file - "
            java.lang.String r12 = java.lang.String.valueOf(r6)
            java.lang.String r11 = r11.concat(r12)
            r12 = 2
            com.flurry.sdk.db.a((int) r12, (java.lang.String) r10, (java.lang.String) r11)
            java.lang.String r10 = com.flurry.sdk.ep.a((java.lang.String) r6)
            java.lang.String r11 = com.flurry.sdk.ep.b(r6)
            boolean r13 = android.text.TextUtils.isEmpty(r10)
            if (r13 == 0) goto L_0x008a
            java.lang.String r7 = a
            java.lang.String r13 = "There is no session id specified with crash breadcrumbs file: "
            java.lang.String r14 = java.lang.String.valueOf(r6)
            java.lang.String r13 = r13.concat(r14)
            com.flurry.sdk.db.a((int) r9, (java.lang.String) r7, (java.lang.String) r13)
            r7 = 1
        L_0x008a:
            r13 = 0
            long r15 = java.lang.System.currentTimeMillis()
            long r17 = java.lang.Long.parseLong(r10)     // Catch:{ NumberFormatException -> 0x009c }
            long r13 = java.lang.Long.parseLong(r11)     // Catch:{ NumberFormatException -> 0x009e }
            r15 = r13
            r10 = r17
            goto L_0x00b0
        L_0x009c:
            r17 = r13
        L_0x009e:
            java.lang.String r7 = a
            java.lang.String r11 = "Issue parsing session id into start time: "
            java.lang.String r10 = java.lang.String.valueOf(r10)
            java.lang.String r10 = r11.concat(r10)
            com.flurry.sdk.db.a((int) r9, (java.lang.String) r7, (java.lang.String) r10)
            r10 = r17
            r7 = 1
        L_0x00b0:
            com.flurry.sdk.bg r13 = new com.flurry.sdk.bg
            r20 = 1
            java.lang.String r23 = "native"
            java.lang.String r24 = ""
            java.lang.String r25 = ""
            r26 = 0
            r27 = 0
            r28 = 0
            r19 = r13
            r21 = r15
            r19.<init>(r20, r21, r23, r24, r25, r26, r27, r28)
            java.lang.String r14 = d()
            r13.b = r14
            java.io.File r14 = new java.io.File
            r14.<init>(r0, r6)
            boolean r6 = r14.exists()
            if (r6 == 0) goto L_0x0101
            com.flurry.sdk.et r6 = new com.flurry.sdk.et
            r6.<init>(r14)
            java.util.List r6 = r6.a()
            java.lang.String r8 = a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r9 = "Number of crash breadcrumbs - "
            r3.<init>(r9)
            int r9 = r6.size()
            r3.append(r9)
            java.lang.String r3 = r3.toString()
            com.flurry.sdk.db.a((int) r12, (java.lang.String) r8, (java.lang.String) r3)
            r13.a(r6)
            r14.delete()
            r8 = r7
            r7 = 6
            goto L_0x0109
        L_0x0101:
            java.lang.String r3 = a
            java.lang.String r6 = "Breadcrumbs file does not exist."
            r7 = 6
            com.flurry.sdk.db.a((int) r7, (java.lang.String) r3, (java.lang.String) r6)
        L_0x0109:
            java.io.File r3 = new java.io.File
            r3.<init>(r0, r5)
            boolean r5 = r3.exists()
            if (r5 == 0) goto L_0x0143
            if (r8 == 0) goto L_0x0123
            java.lang.String r5 = a
            java.lang.String r6 = "Some error occurred with minidump file. Deleting it."
            com.flurry.sdk.db.a((int) r7, (java.lang.String) r5, (java.lang.String) r6)
            r3.delete()
        L_0x0120:
            r5 = r29
            goto L_0x014d
        L_0x0123:
            java.lang.String r5 = com.flurry.sdk.el.c(r3)
            r13.c = r5
            r3.delete()
            r3 = 0
            long r20 = r15 - r10
            r17 = r29
            r18 = r10
            r22 = r13
            com.flurry.sdk.bl r3 = r17.a((long) r18, (long) r20, (com.flurry.sdk.bg) r22)
            if (r3 == 0) goto L_0x0120
            r5 = r29
            java.util.List<com.flurry.sdk.bl> r6 = r5.t
            r6.add(r3)
            goto L_0x014d
        L_0x0143:
            r5 = r29
            java.lang.String r3 = a
            java.lang.String r6 = "Minidump file doesn't exist."
            r7 = 6
            com.flurry.sdk.db.a((int) r7, (java.lang.String) r3, (java.lang.String) r6)
        L_0x014d:
            int r4 = r4 + 1
            goto L_0x0032
        L_0x0151:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.bo.e(com.flurry.sdk.bo):void");
    }

    static /* synthetic */ void a(bo boVar, long j2, long j3, long j4, int i2, String str, String str2, Map map) {
        bl a2 = boVar.a(j2, j3, j4, i2, str, str2, map);
        boVar.t.clear();
        boVar.t.add(a2);
        boVar.g();
    }
}
