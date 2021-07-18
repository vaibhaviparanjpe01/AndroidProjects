package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class bl {
    private static final String b = "bl";
    byte[] a;

    /* synthetic */ bl(byte b2) {
        this();
    }

    private bl() {
    }

    public bl(byte[] bArr) {
        this.a = bArr;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:90|91|127) */
    /* JADX WARNING: Code restructure failed: missing block: B:91:?, code lost:
        com.flurry.sdk.db.a(6, b, "Error encoding purchase receipt.");
        r6 = r8;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:90:0x028c */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0197 A[Catch:{ IOException -> 0x02f3, all -> 0x02f1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x019b A[Catch:{ IOException -> 0x02f3, all -> 0x02f1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x01ab A[Catch:{ IOException -> 0x02f3, all -> 0x02f1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x01af A[Catch:{ IOException -> 0x02f3, all -> 0x02f1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x01e3 A[Catch:{ IOException -> 0x02f3, all -> 0x02f1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x01e7 A[Catch:{ IOException -> 0x02f3, all -> 0x02f1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x020f A[Catch:{ IOException -> 0x02f3, all -> 0x02f1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0241 A[Catch:{ IOException -> 0x02f3, all -> 0x02f1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x024d A[Catch:{ IOException -> 0x02f3, all -> 0x02f1 }, LOOP:5: B:81:0x024b->B:82:0x024d, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x027d A[Catch:{ IOException -> 0x02f3, all -> 0x02f1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x02a7 A[Catch:{ IOException -> 0x02f3, all -> 0x02f1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x02ab A[Catch:{ IOException -> 0x02f3, all -> 0x02f1 }] */
    @android.annotation.SuppressLint({"NewApi"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public bl(com.flurry.sdk.bm r12) throws java.io.IOException {
        /*
            r11 = this;
            r11.<init>()
            r0 = 6
            r1 = 0
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x02f9 }
            r2.<init>()     // Catch:{ IOException -> 0x02f9 }
            java.io.DataOutputStream r3 = new java.io.DataOutputStream     // Catch:{ IOException -> 0x02f9 }
            r3.<init>(r2)     // Catch:{ IOException -> 0x02f9 }
            r1 = 14
            r3.writeShort(r1)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.lang.String r1 = r12.a     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeUTF(r1)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.lang.String r1 = ""
            r3.writeUTF(r1)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.lang.String r1 = "11060000"
            r3.writeUTF(r1)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            long r4 = r12.b     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeLong(r4)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            long r4 = r12.c     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeLong(r4)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            long r4 = r12.d     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeLong(r4)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r1 = 1
            r3.writeBoolean(r1)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            byte r4 = r12.r     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeByte(r4)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            boolean r4 = r12.s     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeBoolean(r4)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.lang.String r4 = r12.f     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r5 = 0
            if (r4 != 0) goto L_0x0052
            r3.writeBoolean(r1)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.lang.String r4 = r12.f     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeUTF(r4)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            goto L_0x0055
        L_0x0052:
            r3.writeBoolean(r5)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
        L_0x0055:
            java.lang.String r4 = r12.g     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            if (r4 != 0) goto L_0x0066
            r3.writeBoolean(r1)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.lang.String r4 = r12.g     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeUTF(r4)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            goto L_0x0069
        L_0x0066:
            r3.writeBoolean(r5)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
        L_0x0069:
            java.util.Map<java.lang.String, java.lang.String> r4 = r12.h     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            if (r4 != 0) goto L_0x0071
            r3.writeShort(r5)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            goto L_0x009f
        L_0x0071:
            int r6 = r4.size()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeShort(r6)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.util.Set r4 = r4.entrySet()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
        L_0x0080:
            boolean r6 = r4.hasNext()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            if (r6 == 0) goto L_0x009f
            java.lang.Object r6 = r4.next()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.lang.Object r7 = r6.getKey()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeUTF(r7)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.lang.Object r6 = r6.getValue()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeUTF(r6)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            goto L_0x0080
        L_0x009f:
            java.util.Map<java.lang.String, java.lang.String> r4 = r12.e     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            if (r4 != 0) goto L_0x00a7
            r3.writeShort(r5)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            goto L_0x00d8
        L_0x00a7:
            int r6 = r4.size()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeShort(r6)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.util.Set r4 = r4.entrySet()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
        L_0x00b6:
            boolean r6 = r4.hasNext()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            if (r6 == 0) goto L_0x00d8
            java.lang.Object r6 = r4.next()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.lang.Object r7 = r6.getKey()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeUTF(r7)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.lang.Object r6 = r6.getValue()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeUTF(r6)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeByte(r5)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            goto L_0x00b6
        L_0x00d8:
            java.lang.String r4 = r12.i     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeUTF(r4)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.lang.String r4 = r12.j     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeUTF(r4)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            int r4 = r12.k     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeByte(r4)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            int r4 = r12.l     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeByte(r4)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.lang.String r4 = r12.m     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeUTF(r4)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            android.location.Location r4 = r12.n     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r6 = -1
            if (r4 != 0) goto L_0x00f8
            goto L_0x017a
        L_0x00f8:
            int r4 = com.flurry.sdk.bw.c()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeBoolean(r1)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            android.location.Location r7 = r12.n     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            double r8 = r7.getLatitude()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            double r8 = com.flurry.sdk.em.a((double) r8, (int) r4)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeDouble(r8)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            double r8 = r7.getLongitude()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            double r8 = com.flurry.sdk.em.a((double) r8, (int) r4)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeDouble(r8)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            float r8 = r7.getAccuracy()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeFloat(r8)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            long r8 = r7.getTime()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeLong(r8)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            double r8 = r7.getAltitude()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeDouble(r8)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            int r8 = android.os.Build.VERSION.SDK_INT     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r9 = 26
            if (r8 < r9) goto L_0x013a
            float r8 = r7.getVerticalAccuracyMeters()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeFloat(r8)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            goto L_0x013e
        L_0x013a:
            r8 = 0
            r3.writeFloat(r8)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
        L_0x013e:
            float r8 = r7.getBearing()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeFloat(r8)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            float r8 = r7.getSpeed()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeFloat(r8)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            int r8 = android.os.Build.VERSION.SDK_INT     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            if (r8 < r9) goto L_0x0173
            boolean r8 = r7.hasBearingAccuracy()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            if (r8 == 0) goto L_0x015e
            boolean r8 = r7.hasSpeedAccuracy()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            if (r8 == 0) goto L_0x015e
            r8 = 1
            goto L_0x015f
        L_0x015e:
            r8 = 0
        L_0x015f:
            r3.writeBoolean(r8)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            if (r8 == 0) goto L_0x0176
            float r8 = r7.getBearingAccuracyDegrees()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeFloat(r8)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            float r7 = r7.getSpeedAccuracyMetersPerSecond()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeFloat(r7)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            goto L_0x0176
        L_0x0173:
            r3.writeBoolean(r5)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
        L_0x0176:
            if (r4 == r6) goto L_0x017a
            r4 = 1
            goto L_0x017b
        L_0x017a:
            r4 = 0
        L_0x017b:
            r3.writeBoolean(r4)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            int r4 = r12.o     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeInt(r4)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            int r4 = r12.x     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeByte(r4)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeByte(r6)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeByte(r6)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            byte r4 = r12.p     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeByte(r4)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.lang.Long r4 = r12.q     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            if (r4 != 0) goto L_0x019b
            r3.writeBoolean(r5)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            goto L_0x01a7
        L_0x019b:
            r3.writeBoolean(r1)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.lang.Long r4 = r12.q     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            long r6 = r4.longValue()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeLong(r6)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
        L_0x01a7:
            java.util.Map<java.lang.String, com.flurry.sdk.bh> r4 = r12.t     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            if (r4 != 0) goto L_0x01af
            r3.writeShort(r5)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            goto L_0x01df
        L_0x01af:
            int r6 = r4.size()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeShort(r6)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.util.Set r4 = r4.entrySet()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
        L_0x01be:
            boolean r6 = r4.hasNext()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            if (r6 == 0) goto L_0x01df
            java.lang.Object r6 = r4.next()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.lang.Object r7 = r6.getKey()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeUTF(r7)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.lang.Object r6 = r6.getValue()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            com.flurry.sdk.bh r6 = (com.flurry.sdk.bh) r6     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            int r6 = r6.a     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeInt(r6)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            goto L_0x01be
        L_0x01df:
            java.util.List<com.flurry.sdk.bi> r4 = r12.u     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            if (r4 != 0) goto L_0x01e7
            r3.writeShort(r5)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            goto L_0x0206
        L_0x01e7:
            int r6 = r4.size()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeShort(r6)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
        L_0x01f2:
            boolean r6 = r4.hasNext()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            if (r6 == 0) goto L_0x0206
            java.lang.Object r6 = r4.next()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            com.flurry.sdk.bi r6 = (com.flurry.sdk.bi) r6     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            byte[] r6 = r6.b()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.write(r6)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            goto L_0x01f2
        L_0x0206:
            boolean r4 = r12.w     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeBoolean(r4)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.util.List<com.flurry.sdk.bg> r4 = r12.z     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            if (r4 == 0) goto L_0x0241
            java.util.Iterator r6 = r4.iterator()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r7 = 0
            r8 = 0
        L_0x0215:
            boolean r9 = r6.hasNext()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            if (r9 == 0) goto L_0x0242
            java.lang.Object r9 = r6.next()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            com.flurry.sdk.bg r9 = (com.flurry.sdk.bg) r9     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            boolean r10 = r9.b()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            if (r10 == 0) goto L_0x022a
            int r7 = r7 + 1
            goto L_0x0215
        L_0x022a:
            byte[] r9 = r9.a()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            int r9 = r9.length     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            int r8 = r8 + r9
            r9 = 160000(0x27100, float:2.24208E-40)
            if (r8 <= r9) goto L_0x023e
            r6 = 5
            java.lang.String r8 = b     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.lang.String r9 = "Error Log size exceeded. No more event details logged."
            com.flurry.sdk.db.a((int) r6, (java.lang.String) r8, (java.lang.String) r9)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            goto L_0x0242
        L_0x023e:
            int r7 = r7 + 1
            goto L_0x0215
        L_0x0241:
            r7 = 0
        L_0x0242:
            int r6 = r12.y     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeInt(r6)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeShort(r7)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r6 = 0
        L_0x024b:
            if (r6 >= r7) goto L_0x025d
            java.lang.Object r8 = r4.get(r6)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            com.flurry.sdk.bg r8 = (com.flurry.sdk.bg) r8     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            byte[] r8 = r8.a()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.write(r8)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            int r6 = r6 + 1
            goto L_0x024b
        L_0x025d:
            r3.writeInt(r5)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeShort(r5)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeShort(r5)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeShort(r5)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.util.List<java.lang.String> r4 = r12.v     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            int r6 = r4.size()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeShort(r6)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
        L_0x0276:
            boolean r6 = r4.hasNext()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r7 = 2
            if (r6 == 0) goto L_0x029f
            java.lang.Object r6 = r4.next()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            byte[] r8 = new byte[r5]     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.lang.String r9 = "UTF8"
            byte[] r6 = r6.getBytes(r9)     // Catch:{ UnsupportedEncodingException -> 0x028c }
            goto L_0x0294
        L_0x028c:
            java.lang.String r6 = b     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.lang.String r9 = "Error encoding purchase receipt."
            com.flurry.sdk.db.a((int) r0, (java.lang.String) r6, (java.lang.String) r9)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r6 = r8
        L_0x0294:
            r3.writeByte(r7)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            int r7 = r6.length     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeInt(r7)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.write(r6)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            goto L_0x0276
        L_0x029f:
            java.lang.String r12 = r12.A     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            boolean r4 = android.text.TextUtils.isEmpty(r12)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            if (r4 == 0) goto L_0x02ab
            r3.writeShort(r5)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            goto L_0x02e7
        L_0x02ab:
            java.lang.String r4 = ","
            java.lang.String[] r12 = r12.split(r4)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            int r4 = r12.length     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            int r4 = r4 % r7
            if (r4 != r1) goto L_0x02dd
            r4 = r12[r5]     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            short r4 = java.lang.Short.parseShort(r4)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeShort(r4)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            int r4 = r12.length     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            int r4 = r4 - r1
            r5 = 1
        L_0x02c1:
            if (r5 >= r4) goto L_0x02e7
            r3.writeShort(r1)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r6 = r12[r5]     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            long r6 = java.lang.Long.parseLong(r6)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeLong(r6)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            int r5 = r5 + 1
            r6 = r12[r5]     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeInt(r6)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            int r5 = r5 + 1
            goto L_0x02c1
        L_0x02dd:
            java.lang.String r12 = b     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            java.lang.String r1 = "Error variant IDs."
            com.flurry.sdk.db.a((int) r0, (java.lang.String) r12, (java.lang.String) r1)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r3.writeShort(r5)     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
        L_0x02e7:
            byte[] r12 = r2.toByteArray()     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            r11.a = r12     // Catch:{ IOException -> 0x02f3, all -> 0x02f1 }
            com.flurry.sdk.em.a((java.io.Closeable) r3)
            return
        L_0x02f1:
            r12 = move-exception
            goto L_0x0302
        L_0x02f3:
            r12 = move-exception
            r1 = r3
            goto L_0x02fa
        L_0x02f6:
            r12 = move-exception
            r3 = r1
            goto L_0x0302
        L_0x02f9:
            r12 = move-exception
        L_0x02fa:
            java.lang.String r2 = b     // Catch:{ all -> 0x02f6 }
            java.lang.String r3 = ""
            com.flurry.sdk.db.a(r0, r2, r3, r12)     // Catch:{ all -> 0x02f6 }
            throw r12     // Catch:{ all -> 0x02f6 }
        L_0x0302:
            com.flurry.sdk.em.a((java.io.Closeable) r3)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.bl.<init>(com.flurry.sdk.bm):void");
    }

    public static class a implements dw<bl> {
        public final /* synthetic */ void a(OutputStream outputStream, Object obj) throws IOException {
            bl blVar = (bl) obj;
            if (outputStream != null && blVar != null) {
                AnonymousClass1 r0 = new DataOutputStream(outputStream) {
                    public final void close() {
                    }
                };
                int i = 0;
                if (blVar.a != null) {
                    i = blVar.a.length;
                }
                r0.writeShort(i);
                if (i > 0) {
                    r0.write(blVar.a);
                }
                r0.flush();
            }
        }

        public final /* synthetic */ Object a(InputStream inputStream) throws IOException {
            if (inputStream == null) {
                return null;
            }
            AnonymousClass2 r0 = new DataInputStream(inputStream) {
                public final void close() {
                }
            };
            bl blVar = new bl((byte) 0);
            int readUnsignedShort = r0.readUnsignedShort();
            if (readUnsignedShort > 0) {
                byte[] bArr = new byte[readUnsignedShort];
                r0.readFully(bArr);
                blVar.a = bArr;
            }
            return blVar;
        }
    }
}
