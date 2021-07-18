package com.flurry.sdk;

import android.content.Context;
import android.os.Build;
import com.flurry.sdk.bb;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.zip.CRC32;

public class ba {
    public static final String a = "com.flurry.sdk.ba";
    private static ba c;
    public String b;
    /* access modifiers changed from: private */
    public ct<List<bb>> d;
    /* access modifiers changed from: private */
    public List<bb> e;
    private boolean f;

    private ba() {
    }

    public static synchronized ba a() {
        ba baVar;
        synchronized (ba.class) {
            if (c == null) {
                ba baVar2 = new ba();
                c = baVar2;
                Context context = ck.a().a;
                baVar2.d = new ct<>(context.getFileStreamPath(".yflurrypulselogging." + Long.toString(em.g(ck.a().b), 16)), ".yflurrypulselogging.", 1, new dz<List<bb>>() {
                    public final dw<List<bb>> a(int i) {
                        return new dv(new bb.a());
                    }
                });
                baVar2.f = ((Boolean) eg.a().a("UseHttps")).booleanValue();
                String str = a;
                db.a(4, str, "initSettings, UseHttps = " + baVar2.f);
                baVar2.e = baVar2.d.a();
                if (baVar2.e == null) {
                    baVar2.e = new ArrayList();
                }
            }
            baVar = c;
        }
        return baVar;
    }

    public final synchronized void a(az azVar) {
        try {
            this.e.add(new bb(azVar.d()));
            db.a(4, a, "Saving persistent Pulse logging data.");
            this.d.a(this.e);
        } catch (IOException unused) {
            db.a(6, a, "Error when generating pulse log report in addReport part");
        }
    }

    public final synchronized void b() {
        try {
            a(d());
        } catch (IOException unused) {
            db.a(6, a, "Report not send due to exception in generate data");
        }
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [byte[], RequestObjectType] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void a(byte[] r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            com.flurry.sdk.by r0 = com.flurry.sdk.by.a()     // Catch:{ all -> 0x007f }
            boolean r0 = r0.c     // Catch:{ all -> 0x007f }
            if (r0 != 0) goto L_0x0013
            r6 = 5
            java.lang.String r0 = a     // Catch:{ all -> 0x007f }
            java.lang.String r1 = "Reports were not sent! No Internet connection!"
            com.flurry.sdk.db.a((int) r6, (java.lang.String) r0, (java.lang.String) r1)     // Catch:{ all -> 0x007f }
            monitor-exit(r5)
            return
        L_0x0013:
            if (r6 == 0) goto L_0x0075
            int r0 = r6.length     // Catch:{ all -> 0x007f }
            if (r0 != 0) goto L_0x0019
            goto L_0x0075
        L_0x0019:
            java.lang.String r0 = r5.b     // Catch:{ all -> 0x007f }
            if (r0 == 0) goto L_0x0020
            java.lang.String r0 = r5.b     // Catch:{ all -> 0x007f }
            goto L_0x0022
        L_0x0020:
            java.lang.String r0 = "https://data.flurry.com/pcr.do"
        L_0x0022:
            r1 = 4
            java.lang.String r2 = a     // Catch:{ all -> 0x007f }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x007f }
            java.lang.String r4 = "PulseLoggingManager: start upload data "
            r3.<init>(r4)     // Catch:{ all -> 0x007f }
            java.lang.String r4 = java.util.Arrays.toString(r6)     // Catch:{ all -> 0x007f }
            r3.append(r4)     // Catch:{ all -> 0x007f }
            java.lang.String r4 = " to "
            r3.append(r4)     // Catch:{ all -> 0x007f }
            r3.append(r0)     // Catch:{ all -> 0x007f }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x007f }
            com.flurry.sdk.db.a((int) r1, (java.lang.String) r2, (java.lang.String) r3)     // Catch:{ all -> 0x007f }
            com.flurry.sdk.di r1 = new com.flurry.sdk.di     // Catch:{ all -> 0x007f }
            r1.<init>()     // Catch:{ all -> 0x007f }
            r1.g = r0     // Catch:{ all -> 0x007f }
            r0 = 100000(0x186a0, float:1.4013E-40)
            r1.u = r0     // Catch:{ all -> 0x007f }
            com.flurry.sdk.dk$a r0 = com.flurry.sdk.dk.a.kPost     // Catch:{ all -> 0x007f }
            r1.h = r0     // Catch:{ all -> 0x007f }
            r0 = 1
            r1.k = r0     // Catch:{ all -> 0x007f }
            java.lang.String r0 = "Content-Type"
            java.lang.String r2 = "application/octet-stream"
            r1.a(r0, r2)     // Catch:{ all -> 0x007f }
            com.flurry.sdk.ds r0 = new com.flurry.sdk.ds     // Catch:{ all -> 0x007f }
            r0.<init>()     // Catch:{ all -> 0x007f }
            r1.c = r0     // Catch:{ all -> 0x007f }
            r1.b = r6     // Catch:{ all -> 0x007f }
            com.flurry.sdk.ba$2 r6 = new com.flurry.sdk.ba$2     // Catch:{ all -> 0x007f }
            r6.<init>()     // Catch:{ all -> 0x007f }
            r1.a = r6     // Catch:{ all -> 0x007f }
            com.flurry.sdk.cg r6 = com.flurry.sdk.cg.a()     // Catch:{ all -> 0x007f }
            r6.a((java.lang.Object) r5, r1)     // Catch:{ all -> 0x007f }
            monitor-exit(r5)
            return
        L_0x0075:
            r6 = 3
            java.lang.String r0 = a     // Catch:{ all -> 0x007f }
            java.lang.String r1 = "No report need be sent"
            com.flurry.sdk.db.a((int) r6, (java.lang.String) r0, (java.lang.String) r1)     // Catch:{ all -> 0x007f }
            monitor-exit(r5)
            return
        L_0x007f:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.ba.a(byte[]):void");
    }

    private byte[] d() throws IOException {
        DataOutputStream dataOutputStream;
        IOException e2;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            try {
                if (this.e != null) {
                    if (!this.e.isEmpty()) {
                        dataOutputStream.writeShort(1);
                        dataOutputStream.writeShort(1);
                        dataOutputStream.writeLong(System.currentTimeMillis());
                        dataOutputStream.writeUTF(ck.a().b);
                        dataOutputStream.writeUTF(cb.a().e());
                        dataOutputStream.writeShort(cl.b());
                        dataOutputStream.writeShort(3);
                        cb.a();
                        dataOutputStream.writeUTF(cb.d());
                        dataOutputStream.writeBoolean(bs.a().f());
                        ArrayList<ag> arrayList = new ArrayList<>();
                        for (Map.Entry next : Collections.unmodifiableMap(bs.a().a).entrySet()) {
                            ag agVar = new ag();
                            agVar.a = ((ca) next.getKey()).d;
                            if (((ca) next.getKey()).e) {
                                agVar.b = new String((byte[]) next.getValue());
                            } else {
                                agVar.b = em.b((byte[]) next.getValue());
                            }
                            arrayList.add(agVar);
                        }
                        dataOutputStream.writeShort(arrayList.size());
                        for (ag agVar2 : arrayList) {
                            dataOutputStream.writeShort(agVar2.a);
                            byte[] bytes = agVar2.b.getBytes();
                            dataOutputStream.writeShort(bytes.length);
                            dataOutputStream.write(bytes);
                        }
                        dataOutputStream.writeShort(6);
                        dataOutputStream.writeShort(at.b - 1);
                        dataOutputStream.writeUTF(Build.MODEL);
                        dataOutputStream.writeShort(at.c - 1);
                        dataOutputStream.writeUTF(Build.BOARD);
                        dataOutputStream.writeShort(at.d - 1);
                        dataOutputStream.writeUTF(Build.ID);
                        dataOutputStream.writeShort(at.e - 1);
                        dataOutputStream.writeUTF(Build.DEVICE);
                        dataOutputStream.writeShort(at.f - 1);
                        dataOutputStream.writeUTF(Build.PRODUCT);
                        dataOutputStream.writeShort(at.g - 1);
                        dataOutputStream.writeUTF(Build.VERSION.RELEASE);
                        dataOutputStream.writeShort(this.e.size());
                        for (bb bbVar : this.e) {
                            dataOutputStream.write(bbVar.a);
                        }
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        CRC32 crc32 = new CRC32();
                        crc32.update(byteArray);
                        dataOutputStream.writeInt((int) crc32.getValue());
                        byte[] byteArray2 = byteArrayOutputStream.toByteArray();
                        em.a((Closeable) dataOutputStream);
                        return byteArray2;
                    }
                }
                byte[] byteArray3 = byteArrayOutputStream.toByteArray();
                em.a((Closeable) dataOutputStream);
                return byteArray3;
            } catch (IOException e3) {
                e2 = e3;
                try {
                    db.a(6, a, "Error when generating report", e2);
                    throw e2;
                } catch (Throwable th) {
                    th = th;
                    em.a((Closeable) dataOutputStream);
                    throw th;
                }
            }
        } catch (IOException e4) {
            dataOutputStream = null;
            e2 = e4;
            db.a(6, a, "Error when generating report", e2);
            throw e2;
        } catch (Throwable th2) {
            th = th2;
            dataOutputStream = null;
            em.a((Closeable) dataOutputStream);
            throw th;
        }
    }
}
