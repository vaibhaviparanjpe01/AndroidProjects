package com.flurry.sdk;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class bi {
    public String a;
    public boolean b;
    public boolean c;
    public long d;
    private final Map<String, String> e = new HashMap();
    private int f;
    private long g;

    public bi(int i, String str, Map<String, String> map, long j, boolean z) {
        this.f = i;
        this.a = str;
        if (map != null) {
            this.e.putAll(map);
        }
        this.g = j;
        this.b = z;
        this.c = !this.b;
    }

    public final void a(long j) {
        this.c = true;
        this.d = j - this.g;
        db.a(3, "FlurryAgent", "Ended event '" + this.a + "' (" + this.g + ") after " + this.d + "ms");
    }

    public final synchronized void a(Map<String, String> map) {
        if (map != null) {
            this.e.putAll(map);
        }
    }

    public final synchronized Map<String, String> a() {
        return new HashMap(this.e);
    }

    public final synchronized void b(Map<String, String> map) {
        this.e.clear();
        if (map != null) {
            this.e.putAll(map);
        }
    }

    public final synchronized byte[] b() {
        Throwable th;
        byte[] bArr;
        DataOutputStream dataOutputStream = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream2 = new DataOutputStream(byteArrayOutputStream);
            try {
                dataOutputStream2.writeShort(this.f);
                dataOutputStream2.writeUTF(this.a);
                dataOutputStream2.writeShort(this.e.size());
                for (Map.Entry next : this.e.entrySet()) {
                    dataOutputStream2.writeUTF(em.b((String) next.getKey()));
                    dataOutputStream2.writeUTF(em.b((String) next.getValue()));
                }
                dataOutputStream2.writeLong(this.g);
                dataOutputStream2.writeLong(this.d);
                dataOutputStream2.flush();
                bArr = byteArrayOutputStream.toByteArray();
                em.a((Closeable) dataOutputStream2);
            } catch (IOException unused) {
                dataOutputStream = dataOutputStream2;
                try {
                    byte[] bArr2 = new byte[0];
                    em.a((Closeable) dataOutputStream);
                    bArr = bArr2;
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
            byte[] bArr22 = new byte[0];
            em.a((Closeable) dataOutputStream);
            bArr = bArr22;
            return bArr;
        }
        return bArr;
    }
}
