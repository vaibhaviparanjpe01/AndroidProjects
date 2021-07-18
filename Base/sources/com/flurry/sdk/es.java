package com.flurry.sdk;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public final class es {
    private static SimpleDateFormat c = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US);
    String a;
    long b;

    public es(String str, long j) {
        this.a = str;
        this.b = j;
    }

    public final byte[] a() {
        Throwable th;
        DataOutputStream dataOutputStream = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream2 = new DataOutputStream(byteArrayOutputStream);
            try {
                dataOutputStream2.writeLong(this.b);
                dataOutputStream2.writeUTF(this.a);
                dataOutputStream2.flush();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                em.a((Closeable) dataOutputStream2);
                return byteArray;
            } catch (IOException unused) {
                dataOutputStream = dataOutputStream2;
                try {
                    byte[] bArr = new byte[0];
                    em.a((Closeable) dataOutputStream);
                    return bArr;
                } catch (Throwable th2) {
                    dataOutputStream2 = dataOutputStream;
                    th = th2;
                    em.a((Closeable) dataOutputStream2);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                em.a((Closeable) dataOutputStream2);
                throw th;
            }
        } catch (IOException unused2) {
            byte[] bArr2 = new byte[0];
            em.a((Closeable) dataOutputStream);
            return bArr2;
        }
    }

    public final String toString() {
        return c.format(Long.valueOf(this.b)) + ": " + this.a + "\n";
    }
}
