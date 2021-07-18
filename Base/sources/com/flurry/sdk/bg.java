package com.flurry.sdk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class bg {
    public String a;
    public String b;
    public String c;
    private int d;
    private long e;
    private String f;
    private String g;
    private Throwable h;
    private Map<String, String> i = new HashMap();
    private Map<String, String> j = new HashMap();
    private List<es> k = new ArrayList();

    public bg(int i2, long j2, String str, String str2, String str3, Throwable th, Map<String, String> map, Map<String, String> map2) {
        this.d = i2;
        this.e = j2;
        this.a = str;
        this.f = str2;
        this.g = str3;
        this.h = th;
        if (map != null) {
            this.i = map;
        }
        if (map2 != null) {
            this.j = map2;
        }
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:52:0x018b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] a() {
        /*
            r10 = this;
            r0 = 0
            r1 = 0
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x018b }
            r2.<init>()     // Catch:{ IOException -> 0x018b }
            java.io.DataOutputStream r3 = new java.io.DataOutputStream     // Catch:{ IOException -> 0x018b }
            r3.<init>(r2)     // Catch:{ IOException -> 0x018b }
            int r1 = r10.d     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            r3.writeShort(r1)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            long r4 = r10.e     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            r3.writeLong(r4)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.lang.String r1 = r10.a     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            r3.writeUTF(r1)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.lang.String r1 = r10.f     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            r3.writeUTF(r1)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.lang.String r1 = r10.g     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            r3.writeUTF(r1)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.util.Map<java.lang.String, java.lang.String> r1 = r10.i     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            int r1 = r1.size()     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            r3.writeShort(r1)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.util.Map<java.lang.String, java.lang.String> r1 = r10.i     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.util.Set r1 = r1.entrySet()     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
        L_0x0038:
            boolean r4 = r1.hasNext()     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            if (r4 == 0) goto L_0x0057
            java.lang.Object r4 = r1.next()     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.lang.Object r5 = r4.getKey()     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            r3.writeUTF(r5)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.lang.Object r4 = r4.getValue()     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            r3.writeUTF(r4)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            goto L_0x0038
        L_0x0057:
            java.util.Map<java.lang.String, java.lang.String> r1 = r10.j     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            int r1 = r1.size()     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            r3.writeShort(r1)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.util.Map<java.lang.String, java.lang.String> r1 = r10.j     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.util.Set r1 = r1.entrySet()     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
        L_0x006a:
            boolean r4 = r1.hasNext()     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            if (r4 == 0) goto L_0x0089
            java.lang.Object r4 = r1.next()     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.lang.Object r5 = r4.getKey()     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            r3.writeUTF(r5)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.lang.Object r4 = r4.getValue()     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            r3.writeUTF(r4)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            goto L_0x006a
        L_0x0089:
            java.lang.Throwable r1 = r10.h     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            r4 = 3
            r5 = 1
            if (r1 == 0) goto L_0x00ff
            java.lang.String r1 = "uncaught"
            java.lang.String r6 = r10.a     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            boolean r1 = r1.equals(r6)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            r6 = 2
            if (r1 == 0) goto L_0x009e
            r3.writeByte(r4)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            goto L_0x00a1
        L_0x009e:
            r3.writeByte(r6)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
        L_0x00a1:
            r3.writeByte(r6)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.lang.String r4 = ""
            r1.<init>(r4)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.lang.String r4 = "line.separator"
            java.lang.String r4 = java.lang.System.getProperty(r4)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.lang.Throwable r6 = r10.h     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.lang.StackTraceElement[] r6 = r6.getStackTrace()     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            int r7 = r6.length     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            r8 = 0
        L_0x00b9:
            if (r8 >= r7) goto L_0x00c6
            r9 = r6[r8]     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            r1.append(r9)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            r1.append(r4)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            int r8 = r8 + 1
            goto L_0x00b9
        L_0x00c6:
            java.lang.Throwable r6 = r10.h     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.lang.Throwable r6 = r6.getCause()     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            if (r6 == 0) goto L_0x00ef
            r1.append(r4)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.lang.String r6 = "Caused by: "
            r1.append(r6)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.lang.Throwable r6 = r10.h     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.lang.Throwable r6 = r6.getCause()     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.lang.StackTraceElement[] r6 = r6.getStackTrace()     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            int r7 = r6.length     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            r8 = 0
        L_0x00e2:
            if (r8 >= r7) goto L_0x00ef
            r9 = r6[r8]     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            r1.append(r9)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            r1.append(r4)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            int r8 = r8 + 1
            goto L_0x00e2
        L_0x00ef:
            java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            byte[] r1 = r1.getBytes()     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            int r4 = r1.length     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            r3.writeInt(r4)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            r3.write(r1)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            goto L_0x012b
        L_0x00ff:
            boolean r1 = r10.b()     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            if (r1 == 0) goto L_0x0125
            r3.writeByte(r4)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.lang.String r1 = r10.c     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            if (r1 == 0) goto L_0x0114
            r3.writeByte(r0)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            goto L_0x012b
        L_0x0114:
            r3.writeByte(r4)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.lang.String r1 = r10.c     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            byte[] r1 = r1.getBytes()     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            int r4 = r1.length     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            r3.writeInt(r4)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            r3.write(r1)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            goto L_0x012b
        L_0x0125:
            r3.writeByte(r5)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            r3.writeByte(r0)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
        L_0x012b:
            java.lang.String r1 = r10.b     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            if (r1 == 0) goto L_0x0137
            r3.writeByte(r0)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            goto L_0x0147
        L_0x0137:
            r3.writeByte(r5)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.lang.String r1 = r10.b     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            byte[] r1 = r1.getBytes()     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            int r4 = r1.length     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            r3.writeInt(r4)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            r3.write(r1)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
        L_0x0147:
            int r1 = com.flurry.sdk.et.b()     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            r3.writeShort(r1)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.util.List<com.flurry.sdk.es> r1 = r10.k     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            if (r1 == 0) goto L_0x0175
            java.util.List<com.flurry.sdk.es> r1 = r10.k     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            int r1 = r1.size()     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            r3.writeShort(r1)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.util.List<com.flurry.sdk.es> r1 = r10.k     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
        L_0x0161:
            boolean r4 = r1.hasNext()     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            if (r4 == 0) goto L_0x0178
            java.lang.Object r4 = r1.next()     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            com.flurry.sdk.es r4 = (com.flurry.sdk.es) r4     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            byte[] r4 = r4.a()     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            r3.write(r4)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            goto L_0x0161
        L_0x0175:
            r3.writeShort(r0)     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
        L_0x0178:
            r3.flush()     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            byte[] r1 = r2.toByteArray()     // Catch:{ IOException -> 0x0186, all -> 0x0184 }
            com.flurry.sdk.em.a((java.io.Closeable) r3)
            r0 = r1
            goto L_0x0190
        L_0x0184:
            r0 = move-exception
            goto L_0x0191
        L_0x0186:
            r1 = r3
            goto L_0x018b
        L_0x0188:
            r0 = move-exception
            r3 = r1
            goto L_0x0191
        L_0x018b:
            byte[] r0 = new byte[r0]     // Catch:{ all -> 0x0188 }
            com.flurry.sdk.em.a((java.io.Closeable) r1)
        L_0x0190:
            return r0
        L_0x0191:
            com.flurry.sdk.em.a((java.io.Closeable) r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.bg.a():byte[]");
    }

    public final void a(List<es> list) {
        if (list != null) {
            this.k = list;
        }
    }

    public final boolean b() {
        return "native".equals(this.a);
    }
}
