package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public final class dp {
    String a;
    byte[] b;

    /* synthetic */ dp(byte b2) {
        this();
    }

    private dp() {
        this.a = null;
        this.b = null;
    }

    public dp(byte[] bArr) {
        this.a = null;
        this.b = null;
        this.a = UUID.randomUUID().toString();
        this.b = bArr;
    }

    public static String a(String str) {
        return ".yflurrydatasenderblock.".concat(String.valueOf(str));
    }

    public static class a implements dw<dp> {
        private int a = 1;

        public final /* synthetic */ void a(OutputStream outputStream, Object obj) throws IOException {
            dp dpVar = (dp) obj;
            if (outputStream != null && dpVar != null) {
                AnonymousClass1 r0 = new DataOutputStream(outputStream) {
                    public final void close() {
                    }
                };
                int length = dpVar.b.length;
                if (this.a == 1) {
                    r0.writeShort(length);
                } else {
                    r0.writeInt(length);
                }
                r0.write(dpVar.b);
                r0.writeShort(0);
                r0.flush();
            }
        }

        public a(int i) {
            this.a = i;
        }

        public final /* synthetic */ Object a(InputStream inputStream) throws IOException {
            int i;
            if (inputStream == null) {
                return null;
            }
            AnonymousClass2 r1 = new DataInputStream(inputStream) {
                public final void close() {
                }
            };
            dp dpVar = new dp((byte) 0);
            if (this.a == 1) {
                i = r1.readShort();
            } else {
                i = r1.readInt();
            }
            if (i == 0) {
                return null;
            }
            dpVar.b = new byte[i];
            r1.readFully(dpVar.b);
            r1.readUnsignedShort();
            return dpVar;
        }
    }

    public static ct<dp> b(String str) {
        return new ct<>(ck.a().a.getFileStreamPath(a(str)), ".yflurrydatasenderblock.", 2, new dz<dp>() {
            public final dw<dp> a(int i) {
                return new a(i);
            }
        });
    }
}
