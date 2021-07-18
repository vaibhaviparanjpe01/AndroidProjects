package com.flurry.sdk;

import android.content.SharedPreferences;
import com.flurry.sdk.ec;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class bs {
    private static final String c = "bs";
    private static bs d;
    public final Map<ca, byte[]> a = new HashMap();
    public String b;
    private final Set<String> e;
    private a f = a.NONE;
    private cc g;
    private ce h;
    private List<b> i = new ArrayList();
    private final cv<ec> j = new cv<ec>() {
        public final /* synthetic */ void a(cu cuVar) {
            if (AnonymousClass6.a[((ec) cuVar).d - 1] == 1 && bs.this.c()) {
                ck.a().b(new eo() {
                    public final void a() {
                        bs.this.h();
                    }
                });
            }
        }
    };
    private SharedPreferences k = ck.a().a.getSharedPreferences("FLURRY_SHARED_PREFERENCES", 0);

    enum a {
        NONE,
        ADVERTISING,
        DEVICE,
        INSTALL_ID,
        REPORTED_IDS,
        FINISHED
    }

    public interface b {
        void a();
    }

    private bs() {
        HashSet hashSet = new HashSet();
        hashSet.add("null");
        hashSet.add("9774d56d682e549c");
        hashSet.add("dead00beef");
        this.e = Collections.unmodifiableSet(hashSet);
        cw.a().a("com.flurry.android.sdk.FlurrySessionEvent", this.j);
        ck.a().b(new eo() {
            public final void a() {
                bs.b(bs.this);
            }
        });
    }

    public static synchronized bs a() {
        bs bsVar;
        synchronized (bs.class) {
            if (d == null) {
                d = new bs();
            }
            bsVar = d;
        }
        return bsVar;
    }

    public static void b() {
        d = null;
    }

    public final boolean c() {
        return a.FINISHED.equals(this.f);
    }

    public final void a(b bVar) {
        if (c()) {
            bVar.a();
        } else {
            this.i.add(bVar);
        }
    }

    public final String d() {
        if (this.g == null) {
            return null;
        }
        return this.g.a;
    }

    public final byte[] e() {
        try {
            if (this.h == null) {
                this.h = new ce();
            }
            return this.h.a();
        } catch (Exception e2) {
            String str = c;
            db.a(5, str, "Error while generating Install ID" + e2.getMessage(), e2);
            return null;
        }
    }

    public final boolean f() {
        if (this.g != null && this.g.b) {
            return false;
        }
        return true;
    }

    /* renamed from: com.flurry.sdk.bs$6  reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] a = new int[ec.a.a().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(13:0|1|2|3|4|5|6|7|8|9|10|11|(3:13|14|16)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.flurry.sdk.bs$a[] r0 = com.flurry.sdk.bs.a.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                b = r0
                r0 = 1
                int[] r1 = b     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.flurry.sdk.bs$a r2 = com.flurry.sdk.bs.a.NONE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r1 = b     // Catch:{ NoSuchFieldError -> 0x001f }
                com.flurry.sdk.bs$a r2 = com.flurry.sdk.bs.a.ADVERTISING     // Catch:{ NoSuchFieldError -> 0x001f }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r3 = 2
                r1[r2] = r3     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r1 = b     // Catch:{ NoSuchFieldError -> 0x002a }
                com.flurry.sdk.bs$a r2 = com.flurry.sdk.bs.a.INSTALL_ID     // Catch:{ NoSuchFieldError -> 0x002a }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r3 = 3
                r1[r2] = r3     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r1 = b     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.flurry.sdk.bs$a r2 = com.flurry.sdk.bs.a.DEVICE     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r3 = 4
                r1[r2] = r3     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r1 = b     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.flurry.sdk.bs$a r2 = com.flurry.sdk.bs.a.REPORTED_IDS     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r3 = 5
                r1[r2] = r3     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r1 = com.flurry.sdk.ec.a.a()
                int r1 = r1.length
                int[] r1 = new int[r1]
                a = r1
                int[] r1 = a     // Catch:{ NoSuchFieldError -> 0x0050 }
                int r2 = com.flurry.sdk.ec.a.a     // Catch:{ NoSuchFieldError -> 0x0050 }
                int r2 = r2 - r0
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0050 }
            L_0x0050:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.bs.AnonymousClass6.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    public void h() {
        final cc ccVar;
        em.a();
        String string = this.k.getString("advertising_id", (String) null);
        boolean z = this.k.getBoolean("ad_tracking_enabled", false);
        if (string != null) {
            ck.a().a((Runnable) new eo() {
                public final void a() {
                    bs.a(bs.this, bs.i());
                }
            }, 5000);
            ccVar = new cc(string, z);
        } else {
            ccVar = i();
            ck.a().a((Runnable) new eo() {
                public final void a() {
                    bs.a(bs.this, ccVar);
                }
            }, 5000);
        }
        this.g = ccVar;
        if (c()) {
            l();
            cw.a().a((cu) new bu());
        }
    }

    /* access modifiers changed from: private */
    public static cc i() {
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(ck.a().a);
            return new cc(advertisingIdInfo.getId(), advertisingIdInfo.isLimitAdTrackingEnabled());
        } catch (NoClassDefFoundError unused) {
            db.b(c, "There is a problem with the Google Play Services library, which is required for Android Advertising ID support. The Google Play Services library should be integrated in any app shipping in the Play Store that uses analytics or advertising.");
            return null;
        } catch (Exception e2) {
            String str = c;
            db.b(str, "GOOGLE PLAY SERVICES ERROR: " + e2.getMessage());
            db.b(c, "There is a problem with the Google Play Services library, which is required for Android Advertising ID support. The Google Play Services library should be integrated in any app shipping in the Play Store that uses analytics or advertising.");
            return null;
        }
    }

    private static void a(String str, File file) {
        DataOutputStream dataOutputStream = null;
        try {
            DataOutputStream dataOutputStream2 = new DataOutputStream(new FileOutputStream(file));
            try {
                dataOutputStream2.writeInt(1);
                dataOutputStream2.writeUTF(str);
                em.a((Closeable) dataOutputStream2);
            } catch (Throwable th) {
                th = th;
                dataOutputStream = dataOutputStream2;
                em.a((Closeable) dataOutputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            db.a(6, c, "Error when saving deviceId", th);
            em.a((Closeable) dataOutputStream);
        }
    }

    private static String j() {
        DataInputStream dataInputStream;
        File fileStreamPath = ck.a().a.getFileStreamPath(".flurryb.");
        String str = null;
        if (fileStreamPath == null || !fileStreamPath.exists()) {
            return null;
        }
        try {
            dataInputStream = new DataInputStream(new FileInputStream(fileStreamPath));
            try {
                if (1 == dataInputStream.readInt()) {
                    str = dataInputStream.readUTF();
                }
            } catch (Throwable th) {
                th = th;
                try {
                    db.a(6, c, "Error when loading deviceId", th);
                    em.a((Closeable) dataInputStream);
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    em.a((Closeable) dataInputStream);
                    throw th;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            dataInputStream = null;
            em.a((Closeable) dataInputStream);
            throw th;
        }
        em.a((Closeable) dataInputStream);
        return str;
    }

    private String k() {
        String[] list;
        File fileStreamPath;
        DataInputStream dataInputStream;
        File filesDir = ck.a().a.getFilesDir();
        String str = null;
        if (filesDir == null || (list = filesDir.list(new FilenameFilter() {
            public final boolean accept(File file, String str) {
                return str.startsWith(".flurryagent.");
            }
        })) == null || list.length == 0 || (fileStreamPath = ck.a().a.getFileStreamPath(list[0])) == null || !fileStreamPath.exists()) {
            return null;
        }
        try {
            dataInputStream = new DataInputStream(new FileInputStream(fileStreamPath));
            try {
                if (46586 == dataInputStream.readUnsignedShort()) {
                    if (2 == dataInputStream.readUnsignedShort()) {
                        dataInputStream.readUTF();
                        str = dataInputStream.readUTF();
                    }
                }
            } catch (Throwable th) {
                th = th;
                try {
                    db.a(6, c, "Error when loading deviceId", th);
                    em.a((Closeable) dataInputStream);
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    em.a((Closeable) dataInputStream);
                    throw th;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            dataInputStream = null;
            em.a((Closeable) dataInputStream);
            throw th;
        }
        em.a((Closeable) dataInputStream);
        return str;
    }

    private void l() {
        String d2 = d();
        if (d2 != null) {
            db.a(3, c, "Fetched advertising id");
            this.a.put(ca.AndroidAdvertisingId, em.d(d2));
        }
        byte[] e2 = e();
        if (e2 != null) {
            db.a(3, c, "Fetched install id");
            this.a.put(ca.AndroidInstallationId, e2);
        }
        String str = this.b;
        if (str != null) {
            db.a(3, c, "Fetched device id");
            this.a.put(ca.DeviceId, em.d(str));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x006e A[Catch:{ Exception -> 0x00ff }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0070 A[Catch:{ Exception -> 0x00ff }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0080 A[Catch:{ Exception -> 0x00ff }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0081 A[Catch:{ Exception -> 0x00ff }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void b(com.flurry.sdk.bs r8) {
        /*
        L_0x0000:
            com.flurry.sdk.bs$a r0 = com.flurry.sdk.bs.a.FINISHED
            com.flurry.sdk.bs$a r1 = r8.f
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0120
            int[] r0 = com.flurry.sdk.bs.AnonymousClass6.b
            com.flurry.sdk.bs$a r1 = r8.f
            int r1 = r1.ordinal()
            r0 = r0[r1]
            switch(r0) {
                case 1: goto L_0x002c;
                case 2: goto L_0x0027;
                case 3: goto L_0x0022;
                case 4: goto L_0x001d;
                case 5: goto L_0x0018;
                default: goto L_0x0017;
            }
        L_0x0017:
            goto L_0x0030
        L_0x0018:
            com.flurry.sdk.bs$a r0 = com.flurry.sdk.bs.a.FINISHED
            r8.f = r0
            goto L_0x0030
        L_0x001d:
            com.flurry.sdk.bs$a r0 = com.flurry.sdk.bs.a.REPORTED_IDS
            r8.f = r0
            goto L_0x0030
        L_0x0022:
            com.flurry.sdk.bs$a r0 = com.flurry.sdk.bs.a.DEVICE
            r8.f = r0
            goto L_0x0030
        L_0x0027:
            com.flurry.sdk.bs$a r0 = com.flurry.sdk.bs.a.INSTALL_ID
            r8.f = r0
            goto L_0x0030
        L_0x002c:
            com.flurry.sdk.bs$a r0 = com.flurry.sdk.bs.a.ADVERTISING
            r8.f = r0
        L_0x0030:
            int[] r0 = com.flurry.sdk.bs.AnonymousClass6.b     // Catch:{ Exception -> 0x00ff }
            com.flurry.sdk.bs$a r1 = r8.f     // Catch:{ Exception -> 0x00ff }
            int r1 = r1.ordinal()     // Catch:{ Exception -> 0x00ff }
            r0 = r0[r1]     // Catch:{ Exception -> 0x00ff }
            switch(r0) {
                case 2: goto L_0x00fa;
                case 3: goto L_0x00ea;
                case 4: goto L_0x0042;
                case 5: goto L_0x003e;
                default: goto L_0x003d;
            }     // Catch:{ Exception -> 0x00ff }
        L_0x003d:
            goto L_0x0000
        L_0x003e:
            r8.l()     // Catch:{ Exception -> 0x00ff }
            goto L_0x0000
        L_0x0042:
            com.flurry.sdk.em.a()     // Catch:{ Exception -> 0x00ff }
            com.flurry.sdk.ck r0 = com.flurry.sdk.ck.a()     // Catch:{ Exception -> 0x00ff }
            android.content.Context r0 = r0.a     // Catch:{ Exception -> 0x00ff }
            android.content.ContentResolver r0 = r0.getContentResolver()     // Catch:{ Exception -> 0x00ff }
            java.lang.String r1 = "android_id"
            java.lang.String r0 = android.provider.Settings.Secure.getString(r0, r1)     // Catch:{ Exception -> 0x00ff }
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x00ff }
            if (r1 != 0) goto L_0x006b
            java.util.Locale r1 = java.util.Locale.US     // Catch:{ Exception -> 0x00ff }
            java.lang.String r1 = r0.toLowerCase(r1)     // Catch:{ Exception -> 0x00ff }
            java.util.Set<java.lang.String> r2 = r8.e     // Catch:{ Exception -> 0x00ff }
            boolean r1 = r2.contains(r1)     // Catch:{ Exception -> 0x00ff }
            if (r1 != 0) goto L_0x006b
            r1 = 1
            goto L_0x006c
        L_0x006b:
            r1 = 0
        L_0x006c:
            if (r1 != 0) goto L_0x0070
            r0 = 0
            goto L_0x007a
        L_0x0070:
            java.lang.String r1 = "AND"
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Exception -> 0x00ff }
            java.lang.String r0 = r1.concat(r0)     // Catch:{ Exception -> 0x00ff }
        L_0x007a:
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x00ff }
            if (r1 != 0) goto L_0x0081
            goto L_0x00e6
        L_0x0081:
            java.lang.String r0 = j()     // Catch:{ Exception -> 0x00ff }
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x00ff }
            if (r1 == 0) goto L_0x00e6
            java.lang.String r0 = r8.k()     // Catch:{ Exception -> 0x00ff }
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x00ff }
            if (r1 == 0) goto L_0x00cb
            double r0 = java.lang.Math.random()     // Catch:{ Exception -> 0x00ff }
            long r0 = java.lang.Double.doubleToLongBits(r0)     // Catch:{ Exception -> 0x00ff }
            long r2 = java.lang.System.nanoTime()     // Catch:{ Exception -> 0x00ff }
            com.flurry.sdk.ck r4 = com.flurry.sdk.ck.a()     // Catch:{ Exception -> 0x00ff }
            android.content.Context r4 = r4.a     // Catch:{ Exception -> 0x00ff }
            java.lang.String r4 = com.flurry.sdk.ej.a(r4)     // Catch:{ Exception -> 0x00ff }
            long r4 = com.flurry.sdk.em.g(r4)     // Catch:{ Exception -> 0x00ff }
            r6 = 37
            long r4 = r4 * r6
            long r2 = r2 + r4
            long r2 = r2 * r6
            long r0 = r0 + r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ff }
            java.lang.String r3 = "ID"
            r2.<init>(r3)     // Catch:{ Exception -> 0x00ff }
            r3 = 16
            java.lang.String r0 = java.lang.Long.toString(r0, r3)     // Catch:{ Exception -> 0x00ff }
            r2.append(r0)     // Catch:{ Exception -> 0x00ff }
            java.lang.String r0 = r2.toString()     // Catch:{ Exception -> 0x00ff }
        L_0x00cb:
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x00ff }
            if (r1 != 0) goto L_0x00e6
            com.flurry.sdk.ck r1 = com.flurry.sdk.ck.a()     // Catch:{ Exception -> 0x00ff }
            android.content.Context r1 = r1.a     // Catch:{ Exception -> 0x00ff }
            java.lang.String r2 = ".flurryb."
            java.io.File r1 = r1.getFileStreamPath(r2)     // Catch:{ Exception -> 0x00ff }
            boolean r2 = com.flurry.sdk.el.a(r1)     // Catch:{ Exception -> 0x00ff }
            if (r2 == 0) goto L_0x00e6
            a((java.lang.String) r0, (java.io.File) r1)     // Catch:{ Exception -> 0x00ff }
        L_0x00e6:
            r8.b = r0     // Catch:{ Exception -> 0x00ff }
            goto L_0x0000
        L_0x00ea:
            com.flurry.sdk.em.a()     // Catch:{ Exception -> 0x00ff }
            com.flurry.sdk.ce r0 = r8.h     // Catch:{ Exception -> 0x00ff }
            if (r0 != 0) goto L_0x0000
            com.flurry.sdk.ce r0 = new com.flurry.sdk.ce     // Catch:{ Exception -> 0x00ff }
            r0.<init>()     // Catch:{ Exception -> 0x00ff }
            r8.h = r0     // Catch:{ Exception -> 0x00ff }
            goto L_0x0000
        L_0x00fa:
            r8.h()     // Catch:{ Exception -> 0x00ff }
            goto L_0x0000
        L_0x00ff:
            r0 = move-exception
            r1 = 4
            java.lang.String r2 = c
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Exception during id fetch:"
            r3.<init>(r4)
            com.flurry.sdk.bs$a r4 = r8.f
            r3.append(r4)
            java.lang.String r4 = ", "
            r3.append(r4)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            com.flurry.sdk.db.a((int) r1, (java.lang.String) r2, (java.lang.String) r0)
            goto L_0x0000
        L_0x0120:
            com.flurry.sdk.bt r0 = new com.flurry.sdk.bt
            r0.<init>()
            com.flurry.sdk.cw r1 = com.flurry.sdk.cw.a()
            r1.a((com.flurry.sdk.cu) r0)
            java.util.List<com.flurry.sdk.bs$b> r0 = r8.i
            int r0 = r0.size()
            if (r0 <= 0) goto L_0x014d
            java.util.List<com.flurry.sdk.bs$b> r8 = r8.i
            java.util.Iterator r8 = r8.iterator()
        L_0x013a:
            boolean r0 = r8.hasNext()
            if (r0 == 0) goto L_0x014d
            java.lang.Object r0 = r8.next()
            com.flurry.sdk.bs$b r0 = (com.flurry.sdk.bs.b) r0
            r0.a()
            r8.remove()
            goto L_0x013a
        L_0x014d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.bs.b(com.flurry.sdk.bs):void");
    }

    static /* synthetic */ void a(bs bsVar, cc ccVar) {
        if (ccVar != null) {
            bsVar.k.edit().putString("advertising_id", ccVar.a).putBoolean("ad_tracking_enabled", ccVar.b).apply();
        }
    }
}
