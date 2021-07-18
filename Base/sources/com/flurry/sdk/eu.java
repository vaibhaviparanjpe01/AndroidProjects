package com.flurry.sdk;

import android.os.Build;
import android.os.Debug;
import android.os.Environment;
import android.os.StatFs;
import com.flurry.sdk.ep;
import java.util.HashMap;
import java.util.Map;

public class eu {
    public static final String a = "eu";

    public static Map<String, String> a(boolean z) {
        final HashMap hashMap = new HashMap();
        try {
            hashMap.put("mem.java.max", Long.toString(Runtime.getRuntime().maxMemory()));
        } catch (RuntimeException e) {
            db.a(a, "Error retrieving max memory", (Throwable) e);
        }
        try {
            Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
            Debug.getMemoryInfo(memoryInfo);
            hashMap.put("mem.pss", Long.toString((long) (memoryInfo.getTotalPss() * 1024)));
        } catch (RuntimeException e2) {
            db.a(a, "Error retrieving pss memory", (Throwable) e2);
        }
        if (ck.c()) {
            hashMap.put("application.state", Integer.toString(ep.a.ACTIVE.e));
        } else {
            hashMap.put("application.state", Integer.toString(ep.a.BACKGROUND.e));
        }
        int i = -1;
        try {
            bq.a();
            i = bq.l() - 1;
        } catch (Exception unused) {
        }
        hashMap.put("net.status", Integer.toString(i));
        int i2 = 0;
        try {
            i2 = ek.a();
        } catch (RuntimeException unused2) {
        }
        hashMap.put("orientation", Integer.toString(i2));
        if (!z) {
            ck.a().b(new Runnable() {
                public final void run() {
                    eu.d(hashMap);
                    eu.a((Map<String, String>) hashMap);
                    eu.b(hashMap);
                }
            });
        } else {
            d(hashMap);
            a((Map<String, String>) hashMap);
            b(hashMap);
        }
        return hashMap;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0098 A[Catch:{ FileNotFoundException -> 0x00ba, IOException -> 0x00b8, all -> 0x00b6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0099 A[Catch:{ FileNotFoundException -> 0x00ba, IOException -> 0x00b8, all -> 0x00b6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x009f A[Catch:{ FileNotFoundException -> 0x00ba, IOException -> 0x00b8, all -> 0x00b6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00a5 A[Catch:{ FileNotFoundException -> 0x00ba, IOException -> 0x00b8, all -> 0x00b6 }] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:63:0x00d6=Splitter:B:63:0x00d6, B:58:0x00cd=Splitter:B:58:0x00cd} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void d(java.util.Map<java.lang.String, java.lang.String> r10) {
        /*
            if (r10 != 0) goto L_0x0003
            return
        L_0x0003:
            java.lang.String r0 = "^Vm(RSS|Size|Peak):\\s+(\\d+)\\s+kB$"
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)
            java.io.File r1 = new java.io.File
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "/proc/"
            r2.<init>(r3)
            int r3 = android.os.Process.myPid()
            java.lang.String r3 = java.lang.Integer.toString(r3)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            java.io.File r2 = new java.io.File
            java.lang.String r3 = "status"
            r2.<init>(r1, r3)
            r1 = 0
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x00d4, IOException -> 0x00cb, all -> 0x00c7 }
            r3.<init>(r2)     // Catch:{ FileNotFoundException -> 0x00d4, IOException -> 0x00cb, all -> 0x00c7 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ FileNotFoundException -> 0x00c3, IOException -> 0x00bf, all -> 0x00bc }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ FileNotFoundException -> 0x00c3, IOException -> 0x00bf, all -> 0x00bc }
            r4.<init>(r3)     // Catch:{ FileNotFoundException -> 0x00c3, IOException -> 0x00bf, all -> 0x00bc }
            r2.<init>(r4)     // Catch:{ FileNotFoundException -> 0x00c3, IOException -> 0x00bf, all -> 0x00bc }
            java.lang.String r1 = r2.readLine()     // Catch:{ FileNotFoundException -> 0x00ba, IOException -> 0x00b8, all -> 0x00b6 }
        L_0x003f:
            if (r1 == 0) goto L_0x00af
            java.util.regex.Matcher r1 = r0.matcher(r1)     // Catch:{ FileNotFoundException -> 0x00ba, IOException -> 0x00b8, all -> 0x00b6 }
            boolean r4 = r1.find()     // Catch:{ FileNotFoundException -> 0x00ba, IOException -> 0x00b8, all -> 0x00b6 }
            if (r4 == 0) goto L_0x00aa
            r4 = 1
            java.lang.String r5 = r1.group(r4)     // Catch:{ FileNotFoundException -> 0x00ba, IOException -> 0x00b8, all -> 0x00b6 }
            r6 = 2
            java.lang.String r1 = r1.group(r6)     // Catch:{ FileNotFoundException -> 0x00ba, IOException -> 0x00b8, all -> 0x00b6 }
            boolean r7 = android.text.TextUtils.isEmpty(r5)     // Catch:{ FileNotFoundException -> 0x00ba, IOException -> 0x00b8, all -> 0x00b6 }
            if (r7 != 0) goto L_0x00aa
            boolean r7 = android.text.TextUtils.isEmpty(r1)     // Catch:{ FileNotFoundException -> 0x00ba, IOException -> 0x00b8, all -> 0x00b6 }
            if (r7 == 0) goto L_0x0062
            goto L_0x00aa
        L_0x0062:
            r7 = -1
            int r8 = r5.hashCode()     // Catch:{ FileNotFoundException -> 0x00ba, IOException -> 0x00b8, all -> 0x00b6 }
            r9 = 81458(0x13e32, float:1.14147E-40)
            if (r8 == r9) goto L_0x008a
            r9 = 2483455(0x25e4ff, float:3.480062E-39)
            if (r8 == r9) goto L_0x0080
            r6 = 2577441(0x275421, float:3.611764E-39)
            if (r8 == r6) goto L_0x0077
            goto L_0x0094
        L_0x0077:
            java.lang.String r6 = "Size"
            boolean r5 = r5.equals(r6)     // Catch:{ FileNotFoundException -> 0x00ba, IOException -> 0x00b8, all -> 0x00b6 }
            if (r5 == 0) goto L_0x0094
            goto L_0x0095
        L_0x0080:
            java.lang.String r4 = "Peak"
            boolean r4 = r5.equals(r4)     // Catch:{ FileNotFoundException -> 0x00ba, IOException -> 0x00b8, all -> 0x00b6 }
            if (r4 == 0) goto L_0x0094
            r4 = 2
            goto L_0x0095
        L_0x008a:
            java.lang.String r4 = "RSS"
            boolean r4 = r5.equals(r4)     // Catch:{ FileNotFoundException -> 0x00ba, IOException -> 0x00b8, all -> 0x00b6 }
            if (r4 == 0) goto L_0x0094
            r4 = 0
            goto L_0x0095
        L_0x0094:
            r4 = -1
        L_0x0095:
            switch(r4) {
                case 0: goto L_0x00a5;
                case 1: goto L_0x009f;
                case 2: goto L_0x0099;
                default: goto L_0x0098;
            }     // Catch:{ FileNotFoundException -> 0x00ba, IOException -> 0x00b8, all -> 0x00b6 }
        L_0x0098:
            goto L_0x00aa
        L_0x0099:
            java.lang.String r4 = "mem.virt.max"
            r10.put(r4, r1)     // Catch:{ FileNotFoundException -> 0x00ba, IOException -> 0x00b8, all -> 0x00b6 }
            goto L_0x00aa
        L_0x009f:
            java.lang.String r4 = "mem.virt"
            r10.put(r4, r1)     // Catch:{ FileNotFoundException -> 0x00ba, IOException -> 0x00b8, all -> 0x00b6 }
            goto L_0x00aa
        L_0x00a5:
            java.lang.String r4 = "mem.rss"
            r10.put(r4, r1)     // Catch:{ FileNotFoundException -> 0x00ba, IOException -> 0x00b8, all -> 0x00b6 }
        L_0x00aa:
            java.lang.String r1 = r2.readLine()     // Catch:{ FileNotFoundException -> 0x00ba, IOException -> 0x00b8, all -> 0x00b6 }
            goto L_0x003f
        L_0x00af:
            com.flurry.sdk.em.a((java.io.Closeable) r3)
        L_0x00b2:
            com.flurry.sdk.em.a((java.io.Closeable) r2)
            return
        L_0x00b6:
            r10 = move-exception
            goto L_0x00dc
        L_0x00b8:
            r10 = move-exception
            goto L_0x00c1
        L_0x00ba:
            r10 = move-exception
            goto L_0x00c5
        L_0x00bc:
            r10 = move-exception
            r2 = r1
            goto L_0x00dc
        L_0x00bf:
            r10 = move-exception
            r2 = r1
        L_0x00c1:
            r1 = r3
            goto L_0x00cd
        L_0x00c3:
            r10 = move-exception
            r2 = r1
        L_0x00c5:
            r1 = r3
            goto L_0x00d6
        L_0x00c7:
            r10 = move-exception
            r2 = r1
            r3 = r2
            goto L_0x00dc
        L_0x00cb:
            r10 = move-exception
            r2 = r1
        L_0x00cd:
            r10.printStackTrace()     // Catch:{ all -> 0x00da }
        L_0x00d0:
            com.flurry.sdk.em.a((java.io.Closeable) r1)
            goto L_0x00b2
        L_0x00d4:
            r10 = move-exception
            r2 = r1
        L_0x00d6:
            r10.printStackTrace()     // Catch:{ all -> 0x00da }
            goto L_0x00d0
        L_0x00da:
            r10 = move-exception
            r3 = r1
        L_0x00dc:
            com.flurry.sdk.em.a((java.io.Closeable) r3)
            com.flurry.sdk.em.a((java.io.Closeable) r2)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.eu.d(java.util.Map):void");
    }

    static void a(Map<String, String> map) {
        long j;
        long j2;
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        if (Build.VERSION.SDK_INT >= 18) {
            j = statFs.getBlockSizeLong();
        } else {
            j = (long) statFs.getBlockSize();
        }
        if (Build.VERSION.SDK_INT >= 18) {
            j2 = statFs.getAvailableBlocksLong();
        } else {
            j2 = (long) statFs.getAvailableBlocks();
        }
        map.put("disk.size.free", Long.toString(j2 * j));
    }

    static void b(Map<String, String> map) {
        long j;
        long j2;
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        if (Build.VERSION.SDK_INT >= 18) {
            j = statFs.getBlockSizeLong();
        } else {
            j = (long) statFs.getBlockSize();
        }
        if (Build.VERSION.SDK_INT >= 18) {
            j2 = statFs.getBlockCountLong();
        } else {
            j2 = (long) statFs.getBlockCount();
        }
        map.put("disk.size.total", Long.toString(j2 * j));
    }
}
