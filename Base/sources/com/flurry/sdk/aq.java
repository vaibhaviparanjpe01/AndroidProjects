package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class aq {
    long a;
    boolean b;
    byte[] c;

    public static class a implements dw<aq> {
        public final /* synthetic */ void a(OutputStream outputStream, Object obj) throws IOException {
            aq aqVar = (aq) obj;
            if (outputStream != null && aqVar != null) {
                AnonymousClass1 r0 = new DataOutputStream(outputStream) {
                    public final void close() {
                    }
                };
                r0.writeLong(aqVar.a);
                r0.writeBoolean(aqVar.b);
                r0.writeInt(aqVar.c.length);
                r0.write(aqVar.c);
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
            aq aqVar = new aq();
            aqVar.a = r0.readLong();
            aqVar.b = r0.readBoolean();
            aqVar.c = new byte[r0.readInt()];
            r0.readFully(aqVar.c);
            return aqVar;
        }
    }
}
