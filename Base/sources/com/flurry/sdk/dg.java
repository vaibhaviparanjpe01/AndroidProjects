package com.flurry.sdk;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class dg<ObjectType> {
    public static final String a = "dg";
    private static final byte[] d = {113, -92, -8, 125, 121, 107, -65, -61, -74, -114, -32, 0, -57, -87, -35, -56, -6, -52, 51, 126, -104, 49, 79, -52, 118, -84, 99, -52, -14, -126, -27, -64};
    public String b;
    public dw<ObjectType> c;

    public dg(String str, dw<ObjectType> dwVar) {
        this.b = str;
        this.c = dwVar;
    }

    public static int a(byte[] bArr) {
        if (bArr == null) {
            return 0;
        }
        cr crVar = new cr();
        crVar.update(bArr);
        return crVar.b();
    }

    public static void b(byte[] bArr) {
        if (bArr != null) {
            int length = bArr.length;
            int length2 = d.length;
            for (int i = 0; i < length; i++) {
                bArr[i] = (byte) ((bArr[i] ^ d[i % length2]) ^ ((i * 31) % 251));
            }
        }
    }

    public final ObjectType c(byte[] bArr) throws IOException {
        if (bArr != null) {
            d(bArr);
            byte[] bArr2 = (byte[]) new du(new ds()).a(new ByteArrayInputStream(bArr));
            String str = a;
            db.a(3, str, "Decoding: " + this.b + ": " + new String(bArr2));
            return this.c.a(new ByteArrayInputStream(bArr2));
        }
        throw new IOException("Decoding: " + this.b + ": Nothing to decode");
    }

    private static void d(byte[] bArr) {
        b(bArr);
    }
}
