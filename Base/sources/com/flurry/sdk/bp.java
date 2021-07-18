package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class bp {
    private static final String d = "bp";
    /* access modifiers changed from: package-private */
    public final List<bl> a = new ArrayList();
    /* access modifiers changed from: package-private */
    public boolean b;
    /* access modifiers changed from: package-private */
    public long c;

    public static class a implements dw<bp> {
        public final /* synthetic */ Object a(InputStream inputStream) throws IOException {
            if (inputStream == null) {
                return null;
            }
            AnonymousClass1 r0 = new DataInputStream(inputStream) {
                public final void close() {
                }
            };
            bp bpVar = new bp();
            r0.readUTF();
            r0.readUTF();
            boolean unused = bpVar.b = r0.readBoolean();
            long unused2 = bpVar.c = r0.readLong();
            while (true) {
                int readUnsignedShort = r0.readUnsignedShort();
                if (readUnsignedShort == 0) {
                    return bpVar;
                }
                byte[] bArr = new byte[readUnsignedShort];
                r0.readFully(bArr);
                bpVar.a.add(0, new bl(bArr));
            }
        }

        public final /* synthetic */ void a(OutputStream outputStream, Object obj) throws IOException {
            throw new UnsupportedOperationException("Serialization not supported");
        }
    }
}
