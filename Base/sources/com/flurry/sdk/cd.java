package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class cd {
    boolean a;
    byte[] b;
    byte[] c;
    int d;

    /* synthetic */ cd(byte b2) {
        this();
    }

    public cd(byte[] bArr, byte[] bArr2, boolean z, int i) {
        this.b = bArr2;
        this.c = bArr;
        this.a = z;
        this.d = i;
    }

    private cd() {
    }

    static class a implements dw<cd> {
        public final /* synthetic */ void a(OutputStream outputStream, Object obj) throws IOException {
            cd cdVar = (cd) obj;
            if (outputStream != null && cdVar != null) {
                AnonymousClass1 r0 = new DataOutputStream(outputStream) {
                    public final void close() {
                    }
                };
                r0.writeBoolean(cdVar.a);
                if (cdVar.b == null) {
                    r0.writeInt(0);
                } else {
                    r0.writeInt(cdVar.b.length);
                    r0.write(cdVar.b);
                }
                if (cdVar.c == null) {
                    r0.writeInt(0);
                } else {
                    r0.writeInt(cdVar.c.length);
                    r0.write(cdVar.c);
                }
                r0.writeInt(cdVar.d);
                r0.flush();
            }
        }

        a() {
        }

        public final /* synthetic */ Object a(InputStream inputStream) throws IOException {
            if (inputStream == null) {
                return null;
            }
            AnonymousClass2 r1 = new DataInputStream(inputStream) {
                public final void close() {
                }
            };
            cd cdVar = new cd((byte) 0);
            cdVar.a = r1.readBoolean();
            int readInt = r1.readInt();
            if (readInt > 0) {
                cdVar.b = new byte[readInt];
                r1.read(cdVar.b, 0, readInt);
            } else {
                cdVar.b = null;
            }
            int readInt2 = r1.readInt();
            if (readInt2 > 0) {
                cdVar.c = new byte[readInt2];
                r1.read(cdVar.c, 0, readInt2);
            } else {
                cdVar.c = null;
            }
            cdVar.d = r1.readInt();
            return cdVar;
        }
    }
}
