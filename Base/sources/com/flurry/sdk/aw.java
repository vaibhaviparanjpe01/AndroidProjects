package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class aw {
    private static final String m = "com.flurry.sdk.aw";
    public int a;
    public long b;
    public long c;
    public boolean d;
    public int e;
    public ax f;
    public String g;
    public int h;
    public long i;
    public boolean j;
    public long k = 0;
    public av l;

    public aw(av avVar, long j2, long j3, int i2) {
        this.l = avVar;
        this.b = j2;
        this.c = j3;
        this.a = i2;
        this.e = 0;
        this.f = ax.PENDING_COMPLETION;
    }

    public final void a() {
        this.l.f.add(this);
        if (this.d) {
            this.l.m = true;
        }
    }

    public static class a implements dw<aw> {
        public final /* synthetic */ void a(OutputStream outputStream, Object obj) throws IOException {
            aw awVar = (aw) obj;
            if (outputStream != null && awVar != null) {
                AnonymousClass1 r0 = new DataOutputStream(outputStream) {
                    public final void close() {
                    }
                };
                r0.writeInt(awVar.a);
                r0.writeLong(awVar.b);
                r0.writeLong(awVar.c);
                r0.writeBoolean(awVar.d);
                r0.writeInt(awVar.e);
                r0.writeInt(awVar.f.e);
                if (awVar.g != null) {
                    r0.writeUTF(awVar.g);
                } else {
                    r0.writeUTF("");
                }
                r0.writeInt(awVar.h);
                r0.writeLong(awVar.i);
                r0.writeBoolean(awVar.j);
                r0.writeLong(awVar.k);
                r0.flush();
            }
        }

        public final /* synthetic */ Object a(InputStream inputStream) throws IOException {
            InputStream inputStream2 = inputStream;
            if (inputStream2 == null) {
                return null;
            }
            AnonymousClass2 r1 = new DataInputStream(inputStream2) {
                public final void close() {
                }
            };
            int readInt = r1.readInt();
            long readLong = r1.readLong();
            long readLong2 = r1.readLong();
            boolean readBoolean = r1.readBoolean();
            int readInt2 = r1.readInt();
            ax a = ax.a(r1.readInt());
            String readUTF = r1.readUTF();
            int readInt3 = r1.readInt();
            long readLong3 = r1.readLong();
            aw awVar = new aw((av) null, readLong, readLong2, readInt);
            awVar.d = readBoolean;
            awVar.e = readInt2;
            awVar.f = a;
            awVar.g = readUTF;
            awVar.h = readInt3;
            awVar.i = readLong3;
            awVar.j = r1.readBoolean();
            awVar.k = r1.readLong();
            return awVar;
        }
    }
}
