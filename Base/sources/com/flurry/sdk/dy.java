package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class dy<T> implements dw<T> {
    private final String a;
    private final int b;
    private final dz<T> c;

    public dy(String str, int i, dz<T> dzVar) {
        this.a = str;
        this.b = i;
        this.c = dzVar;
    }

    public final void a(OutputStream outputStream, T t) throws IOException {
        if (outputStream != null && this.c != null) {
            AnonymousClass1 r0 = new DataOutputStream(outputStream) {
                public final void close() {
                }
            };
            r0.writeUTF(this.a);
            r0.writeInt(this.b);
            this.c.a(this.b).a(r0, t);
            r0.flush();
        }
    }

    public final T a(InputStream inputStream) throws IOException {
        if (inputStream == null || this.c == null) {
            return null;
        }
        AnonymousClass2 r0 = new DataInputStream(inputStream) {
            public final void close() {
            }
        };
        String readUTF = r0.readUTF();
        if (this.a.equals(readUTF)) {
            return this.c.a(r0.readInt()).a(r0);
        }
        throw new IOException("Signature: " + readUTF + " is invalid");
    }
}
