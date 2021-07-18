package com.flurry.sdk;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public final class bb {
    byte[] a;

    public bb() {
    }

    public bb(byte[] bArr) {
        this.a = bArr;
    }

    public static class a implements dw<bb> {
        public final /* synthetic */ void a(OutputStream outputStream, Object obj) throws IOException {
            bb bbVar = (bb) obj;
            if (outputStream != null && bbVar != null) {
                AnonymousClass1 r0 = new DataOutputStream(outputStream) {
                    public final void close() {
                    }
                };
                r0.writeShort(bbVar.a.length);
                r0.write(bbVar.a);
                r0.writeShort(0);
                r0.flush();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x0004, code lost:
            r1 = new com.flurry.sdk.bb.a.AnonymousClass2(r2, r3);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final /* synthetic */ java.lang.Object a(java.io.InputStream r3) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                if (r3 != 0) goto L_0x0004
                return r0
            L_0x0004:
                com.flurry.sdk.bb$a$2 r1 = new com.flurry.sdk.bb$a$2
                r1.<init>(r3)
                short r3 = r1.readShort()
                if (r3 != 0) goto L_0x0010
                return r0
            L_0x0010:
                com.flurry.sdk.bb r0 = new com.flurry.sdk.bb
                r0.<init>()
                byte[] r3 = new byte[r3]
                r0.a = r3
                byte[] r3 = r0.a
                r1.readFully(r3)
                r1.readUnsignedShort()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.bb.a.a(java.io.InputStream):java.lang.Object");
        }
    }
}
