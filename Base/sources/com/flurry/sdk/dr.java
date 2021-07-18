package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class dr {
    String a;

    /* synthetic */ dr(byte b) {
        this();
    }

    private dr() {
    }

    public dr(String str) {
        this.a = str;
    }

    public static class a implements dw<dr> {
        public final /* synthetic */ void a(OutputStream outputStream, Object obj) throws IOException {
            dr drVar = (dr) obj;
            if (outputStream != null && drVar != null) {
                AnonymousClass1 r0 = new DataOutputStream(outputStream) {
                    public final void close() {
                    }
                };
                r0.writeUTF(drVar.a);
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
            dr drVar = new dr((byte) 0);
            drVar.a = r0.readUTF();
            return drVar;
        }
    }
}
