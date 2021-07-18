package com.flurry.sdk;

import com.flurry.sdk.dr;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class dq {
    public static final Integer a = 50;
    private static final String d = dq.class.getSimpleName();
    String b;
    LinkedHashMap<String, List<String>> c;

    public dq(String str) {
        this.b = str + "Main";
        b(this.b);
    }

    private void b(String str) {
        this.c = new LinkedHashMap<>();
        ArrayList<String> arrayList = new ArrayList<>();
        if (c(str)) {
            List<String> d2 = d(str);
            if (d2 != null && d2.size() > 0) {
                arrayList.addAll(d2);
                for (String e : arrayList) {
                    e(e);
                }
            }
            f(str);
        } else {
            List<dr> list = (List) new ct(ck.a().a.getFileStreamPath(g(this.b)), str, 1, new dz<List<dr>>() {
                public final dw<List<dr>> a(int i) {
                    return new dv(new dr.a());
                }
            }).a();
            if (list == null) {
                db.c(d, "New main file also not found. returning..");
                return;
            }
            for (dr drVar : list) {
                arrayList.add(drVar.a);
            }
        }
        for (String str2 : arrayList) {
            this.c.put(str2, h(str2));
        }
    }

    private synchronized boolean c(String str) {
        File fileStreamPath;
        fileStreamPath = ck.a().a.getFileStreamPath(".FlurrySenderIndex.info.".concat(String.valueOf(str)));
        String str2 = d;
        db.a(5, str2, "isOldIndexFilePresent: for " + str + fileStreamPath.exists());
        return fileStreamPath.exists();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00bd, code lost:
        r9 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00bf, code lost:
        r9 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00c0, code lost:
        r1 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00bd A[ExcHandler: all (th java.lang.Throwable), Splitter:B:7:0x0071] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized java.util.List<java.lang.String> d(java.lang.String r9) {
        /*
            r8 = this;
            monitor-enter(r8)
            com.flurry.sdk.em.a()     // Catch:{ all -> 0x00e2 }
            java.lang.String r0 = d     // Catch:{ all -> 0x00e2 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e2 }
            java.lang.String r2 = "Reading Index File for "
            r1.<init>(r2)     // Catch:{ all -> 0x00e2 }
            r1.append(r9)     // Catch:{ all -> 0x00e2 }
            java.lang.String r2 = " file name:"
            r1.append(r2)     // Catch:{ all -> 0x00e2 }
            com.flurry.sdk.ck r2 = com.flurry.sdk.ck.a()     // Catch:{ all -> 0x00e2 }
            android.content.Context r2 = r2.a     // Catch:{ all -> 0x00e2 }
            java.lang.String r3 = ".FlurrySenderIndex.info."
            java.lang.String r4 = java.lang.String.valueOf(r9)     // Catch:{ all -> 0x00e2 }
            java.lang.String r3 = r3.concat(r4)     // Catch:{ all -> 0x00e2 }
            java.io.File r2 = r2.getFileStreamPath(r3)     // Catch:{ all -> 0x00e2 }
            r1.append(r2)     // Catch:{ all -> 0x00e2 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00e2 }
            r2 = 5
            com.flurry.sdk.db.a((int) r2, (java.lang.String) r0, (java.lang.String) r1)     // Catch:{ all -> 0x00e2 }
            com.flurry.sdk.ck r0 = com.flurry.sdk.ck.a()     // Catch:{ all -> 0x00e2 }
            android.content.Context r0 = r0.a     // Catch:{ all -> 0x00e2 }
            java.lang.String r1 = ".FlurrySenderIndex.info."
            java.lang.String r3 = java.lang.String.valueOf(r9)     // Catch:{ all -> 0x00e2 }
            java.lang.String r1 = r1.concat(r3)     // Catch:{ all -> 0x00e2 }
            java.io.File r0 = r0.getFileStreamPath(r1)     // Catch:{ all -> 0x00e2 }
            boolean r1 = r0.exists()     // Catch:{ all -> 0x00e2 }
            r3 = 0
            if (r1 == 0) goto L_0x00d8
            java.lang.String r1 = d     // Catch:{ all -> 0x00e2 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e2 }
            java.lang.String r5 = "Reading Index File for "
            r4.<init>(r5)     // Catch:{ all -> 0x00e2 }
            r4.append(r9)     // Catch:{ all -> 0x00e2 }
            java.lang.String r9 = " Found file."
            r4.append(r9)     // Catch:{ all -> 0x00e2 }
            java.lang.String r9 = r4.toString()     // Catch:{ all -> 0x00e2 }
            com.flurry.sdk.db.a((int) r2, (java.lang.String) r1, (java.lang.String) r9)     // Catch:{ all -> 0x00e2 }
            java.io.FileInputStream r9 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x00c6 }
            r9.<init>(r0)     // Catch:{ Throwable -> 0x00c6 }
            java.io.DataInputStream r0 = new java.io.DataInputStream     // Catch:{ Throwable -> 0x00c6 }
            r0.<init>(r9)     // Catch:{ Throwable -> 0x00c6 }
            int r9 = r0.readUnsignedShort()     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            if (r9 != 0) goto L_0x007c
            com.flurry.sdk.em.a((java.io.Closeable) r0)     // Catch:{ all -> 0x00e2 }
            monitor-exit(r8)
            return r3
        L_0x007c:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            r1.<init>(r9)     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            r2 = 0
        L_0x0082:
            if (r2 >= r9) goto L_0x00b6
            int r3 = r0.readUnsignedShort()     // Catch:{ Throwable -> 0x00b4, all -> 0x00bd }
            r4 = 4
            java.lang.String r5 = d     // Catch:{ Throwable -> 0x00b4, all -> 0x00bd }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00b4, all -> 0x00bd }
            java.lang.String r7 = "read iter "
            r6.<init>(r7)     // Catch:{ Throwable -> 0x00b4, all -> 0x00bd }
            r6.append(r2)     // Catch:{ Throwable -> 0x00b4, all -> 0x00bd }
            java.lang.String r7 = " dataLength = "
            r6.append(r7)     // Catch:{ Throwable -> 0x00b4, all -> 0x00bd }
            r6.append(r3)     // Catch:{ Throwable -> 0x00b4, all -> 0x00bd }
            java.lang.String r6 = r6.toString()     // Catch:{ Throwable -> 0x00b4, all -> 0x00bd }
            com.flurry.sdk.db.a((int) r4, (java.lang.String) r5, (java.lang.String) r6)     // Catch:{ Throwable -> 0x00b4, all -> 0x00bd }
            byte[] r3 = new byte[r3]     // Catch:{ Throwable -> 0x00b4, all -> 0x00bd }
            r0.readFully(r3)     // Catch:{ Throwable -> 0x00b4, all -> 0x00bd }
            java.lang.String r4 = new java.lang.String     // Catch:{ Throwable -> 0x00b4, all -> 0x00bd }
            r4.<init>(r3)     // Catch:{ Throwable -> 0x00b4, all -> 0x00bd }
            r1.add(r4)     // Catch:{ Throwable -> 0x00b4, all -> 0x00bd }
            int r2 = r2 + 1
            goto L_0x0082
        L_0x00b4:
            r9 = move-exception
            goto L_0x00c1
        L_0x00b6:
            r0.readUnsignedShort()     // Catch:{ Throwable -> 0x00b4, all -> 0x00bd }
            com.flurry.sdk.em.a((java.io.Closeable) r0)     // Catch:{ all -> 0x00e2 }
            goto L_0x00e0
        L_0x00bd:
            r9 = move-exception
            goto L_0x00d4
        L_0x00bf:
            r9 = move-exception
            r1 = r3
        L_0x00c1:
            r3 = r0
            goto L_0x00c8
        L_0x00c3:
            r9 = move-exception
            r0 = r3
            goto L_0x00d4
        L_0x00c6:
            r9 = move-exception
            r1 = r3
        L_0x00c8:
            r0 = 6
            java.lang.String r2 = d     // Catch:{ all -> 0x00c3 }
            java.lang.String r4 = "Error when loading persistent file"
            com.flurry.sdk.db.a(r0, r2, r4, r9)     // Catch:{ all -> 0x00c3 }
            com.flurry.sdk.em.a((java.io.Closeable) r3)     // Catch:{ all -> 0x00e2 }
            goto L_0x00e0
        L_0x00d4:
            com.flurry.sdk.em.a((java.io.Closeable) r0)     // Catch:{ all -> 0x00e2 }
            throw r9     // Catch:{ all -> 0x00e2 }
        L_0x00d8:
            java.lang.String r9 = d     // Catch:{ all -> 0x00e2 }
            java.lang.String r0 = "Agent cache file doesn't exist."
            com.flurry.sdk.db.a((int) r2, (java.lang.String) r9, (java.lang.String) r0)     // Catch:{ all -> 0x00e2 }
            r1 = r3
        L_0x00e0:
            monitor-exit(r8)
            return r1
        L_0x00e2:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.dq.d(java.lang.String):java.util.List");
    }

    private void e(String str) {
        List<String> d2 = d(str);
        if (d2 == null) {
            db.c(d, "No old file to replace");
            return;
        }
        for (String next : d2) {
            byte[] i = i(next);
            if (i == null) {
                db.a(6, d, "File does not exist");
            } else {
                a(next, i);
                em.a();
                String str2 = d;
                db.a(5, str2, "Deleting  block File for " + next + " file name:" + ck.a().a.getFileStreamPath(".flurrydatasenderblock.".concat(String.valueOf(next))));
                File fileStreamPath = ck.a().a.getFileStreamPath(".flurrydatasenderblock.".concat(String.valueOf(next)));
                if (fileStreamPath.exists()) {
                    boolean delete = fileStreamPath.delete();
                    String str3 = d;
                    db.a(5, str3, "Found file for " + next + ". Deleted - " + delete);
                }
            }
        }
        a(str, d2, ".YFlurrySenderIndex.info.");
        f(str);
    }

    private static void f(String str) {
        em.a();
        String str2 = d;
        db.a(5, str2, "Deleting Index File for " + str + " file name:" + ck.a().a.getFileStreamPath(".FlurrySenderIndex.info.".concat(String.valueOf(str))));
        File fileStreamPath = ck.a().a.getFileStreamPath(".FlurrySenderIndex.info.".concat(String.valueOf(str)));
        if (fileStreamPath.exists()) {
            boolean delete = fileStreamPath.delete();
            String str3 = d;
            db.a(5, str3, "Found file for " + str + ". Deleted - " + delete);
        }
    }

    private static String g(String str) {
        return ".YFlurrySenderIndex.info.".concat(String.valueOf(str));
    }

    private synchronized List<String> h(String str) {
        ArrayList arrayList;
        em.a();
        String str2 = d;
        db.a(5, str2, "Reading Index File for " + str + " file name:" + ck.a().a.getFileStreamPath(g(str)));
        arrayList = new ArrayList();
        for (dr drVar : (List) new ct(ck.a().a.getFileStreamPath(g(str)), ".YFlurrySenderIndex.info.", 1, new dz<List<dr>>() {
            public final dw<List<dr>> a(int i) {
                return new dv(new dr.a());
            }
        }).a()) {
            arrayList.add(drVar.a);
        }
        return arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0088, code lost:
        r6 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0089, code lost:
        r3 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x008b, code lost:
        r1 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x008c, code lost:
        r6 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0088 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:5:0x0070] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] i(java.lang.String r6) {
        /*
            com.flurry.sdk.em.a()
            java.lang.String r0 = d
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Reading block File for "
            r1.<init>(r2)
            r1.append(r6)
            java.lang.String r2 = " file name:"
            r1.append(r2)
            com.flurry.sdk.ck r2 = com.flurry.sdk.ck.a()
            android.content.Context r2 = r2.a
            java.lang.String r3 = ".flurrydatasenderblock."
            java.lang.String r4 = java.lang.String.valueOf(r6)
            java.lang.String r3 = r3.concat(r4)
            java.io.File r2 = r2.getFileStreamPath(r3)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r2 = 5
            com.flurry.sdk.db.a((int) r2, (java.lang.String) r0, (java.lang.String) r1)
            com.flurry.sdk.ck r0 = com.flurry.sdk.ck.a()
            android.content.Context r0 = r0.a
            java.lang.String r1 = ".flurrydatasenderblock."
            java.lang.String r3 = java.lang.String.valueOf(r6)
            java.lang.String r1 = r1.concat(r3)
            java.io.File r0 = r0.getFileStreamPath(r1)
            boolean r1 = r0.exists()
            r3 = 0
            if (r1 == 0) goto L_0x00a3
            java.lang.String r1 = d
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Reading Index File for "
            r4.<init>(r5)
            r4.append(r6)
            java.lang.String r6 = " Found file."
            r4.append(r6)
            java.lang.String r6 = r4.toString()
            com.flurry.sdk.db.a((int) r2, (java.lang.String) r1, (java.lang.String) r6)
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0091 }
            r6.<init>(r0)     // Catch:{ Throwable -> 0x0091 }
            java.io.DataInputStream r0 = new java.io.DataInputStream     // Catch:{ Throwable -> 0x0091 }
            r0.<init>(r6)     // Catch:{ Throwable -> 0x0091 }
            int r6 = r0.readUnsignedShort()     // Catch:{ Throwable -> 0x008b, all -> 0x0088 }
            if (r6 != 0) goto L_0x007a
            com.flurry.sdk.em.a((java.io.Closeable) r0)
            return r3
        L_0x007a:
            byte[] r6 = new byte[r6]     // Catch:{ Throwable -> 0x008b, all -> 0x0088 }
            r0.readFully(r6)     // Catch:{ Throwable -> 0x0086, all -> 0x0088 }
            r0.readUnsignedShort()     // Catch:{ Throwable -> 0x0086, all -> 0x0088 }
            com.flurry.sdk.em.a((java.io.Closeable) r0)
            goto L_0x00ac
        L_0x0086:
            r1 = move-exception
            goto L_0x008d
        L_0x0088:
            r6 = move-exception
            r3 = r0
            goto L_0x009f
        L_0x008b:
            r1 = move-exception
            r6 = r3
        L_0x008d:
            r3 = r0
            goto L_0x0093
        L_0x008f:
            r6 = move-exception
            goto L_0x009f
        L_0x0091:
            r1 = move-exception
            r6 = r3
        L_0x0093:
            r0 = 6
            java.lang.String r2 = d     // Catch:{ all -> 0x008f }
            java.lang.String r4 = "Error when loading persistent file"
            com.flurry.sdk.db.a(r0, r2, r4, r1)     // Catch:{ all -> 0x008f }
            com.flurry.sdk.em.a((java.io.Closeable) r3)
            goto L_0x00ac
        L_0x009f:
            com.flurry.sdk.em.a((java.io.Closeable) r3)
            throw r6
        L_0x00a3:
            r6 = 4
            java.lang.String r0 = d
            java.lang.String r1 = "Agent cache file doesn't exist."
            com.flurry.sdk.db.a((int) r6, (java.lang.String) r0, (java.lang.String) r1)
            r6 = r3
        L_0x00ac:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.dq.i(java.lang.String):byte[]");
    }

    private synchronized void a(String str, byte[] bArr) {
        em.a();
        String str2 = d;
        db.a(5, str2, "Saving Block File for " + str + " file name:" + ck.a().a.getFileStreamPath(dp.a(str)));
        dp.b(str).a(new dp(bArr));
    }

    private synchronized void a(String str, List<String> list, String str2) {
        em.a();
        String str3 = d;
        db.a(5, str3, "Saving Index File for " + str + " file name:" + ck.a().a.getFileStreamPath(g(str)));
        ct ctVar = new ct(ck.a().a.getFileStreamPath(g(str)), str2, 1, new dz<List<dr>>() {
            public final dw<List<dr>> a(int i) {
                return new dv(new dr.a());
            }
        });
        ArrayList arrayList = new ArrayList();
        for (String drVar : list) {
            arrayList.add(new dr(drVar));
        }
        ctVar.a(arrayList);
    }

    public final synchronized void a(dp dpVar, String str) {
        boolean z;
        db.a(4, d, "addBlockInfo".concat(String.valueOf(str)));
        String str2 = dpVar.a;
        List list = this.c.get(str);
        if (list == null) {
            db.a(4, d, "New Data Key");
            list = new LinkedList();
            z = true;
        } else {
            z = false;
        }
        list.add(str2);
        if (list.size() > a.intValue()) {
            dp.b((String) list.get(0)).b();
            list.remove(0);
        }
        this.c.put(str, list);
        a(str, list, ".YFlurrySenderIndex.info.");
        if (z) {
            a();
        }
    }

    private synchronized void a() {
        LinkedList linkedList = new LinkedList(this.c.keySet());
        new ct(ck.a().a.getFileStreamPath(g(this.b)), ".YFlurrySenderIndex.info.", 1, new dz<List<dr>>() {
            public final dw<List<dr>> a(int i) {
                return new dv(new dr.a());
            }
        }).b();
        if (!linkedList.isEmpty()) {
            a(this.b, linkedList, this.b);
        }
    }

    public final boolean a(String str, String str2) {
        boolean z;
        List list = this.c.get(str2);
        if (list != null) {
            dp.b(str).b();
            z = list.remove(str);
        } else {
            z = false;
        }
        if (list == null || list.isEmpty()) {
            j(str2);
        } else {
            this.c.put(str2, list);
            a(str2, list, ".YFlurrySenderIndex.info.");
        }
        return z;
    }

    private synchronized boolean j(String str) {
        boolean b2;
        em.a();
        ct ctVar = new ct(ck.a().a.getFileStreamPath(g(str)), ".YFlurrySenderIndex.info.", 1, new dz<List<dr>>() {
            public final dw<List<dr>> a(int i) {
                return new dv(new dr.a());
            }
        });
        List<String> a2 = a(str);
        if (a2 != null) {
            String str2 = d;
            db.a(4, str2, "discardOutdatedBlocksForDataKey: notSentBlocks = " + a2.size());
            for (String next : a2) {
                dp.b(next).b();
                db.a(4, d, "discardOutdatedBlocksForDataKey: removed block = ".concat(String.valueOf(next)));
            }
        }
        this.c.remove(str);
        b2 = ctVar.b();
        a();
        return b2;
    }

    public final List<String> a(String str) {
        return this.c.get(str);
    }
}
