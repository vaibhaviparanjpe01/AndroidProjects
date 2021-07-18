package com.flurry.sdk;

import com.flurry.sdk.aw;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class av extends dm {
    private static final String t = "com.flurry.sdk.av";
    /* access modifiers changed from: package-private */
    public final long a;
    /* access modifiers changed from: package-private */
    public final int b;
    /* access modifiers changed from: package-private */
    public final int c;
    /* access modifiers changed from: package-private */
    public final bc d;
    /* access modifiers changed from: package-private */
    public final Map<String, String> e;
    public ArrayList<aw> f = new ArrayList<>();
    public az g;
    /* access modifiers changed from: package-private */
    public long h = 30000;
    /* access modifiers changed from: package-private */
    public int i;
    /* access modifiers changed from: package-private */
    public int j;
    /* access modifiers changed from: package-private */
    public String k;
    /* access modifiers changed from: package-private */
    public String l;
    /* access modifiers changed from: package-private */
    public boolean m;

    public av(String str, long j2, String str2, long j3, int i2, int i3, bc bcVar, Map<String, String> map, int i4, int i5, String str3) {
        this.q = str2;
        this.r = str2;
        this.n = j3;
        a();
        this.l = str;
        this.a = j2;
        this.s = i2;
        this.b = i2;
        this.c = i3;
        this.d = bcVar;
        this.e = map;
        this.i = i4;
        this.j = i5;
        this.k = str3;
    }

    public final void a() {
        super.a();
        if (this.p != 1) {
            this.h *= 3;
        }
    }

    public final synchronized void b() {
        this.g.c();
    }

    public final void c() {
        Iterator<aw> it = this.f.iterator();
        while (it.hasNext()) {
            it.next().l = this;
        }
    }

    public static class a implements dw<av> {
        dv<aw> a = new dv<>(new aw.a());

        public final /* synthetic */ void a(OutputStream outputStream, Object obj) throws IOException {
            av avVar = (av) obj;
            if (outputStream != null && avVar != null) {
                AnonymousClass1 r0 = new DataOutputStream(outputStream) {
                    public final void close() {
                    }
                };
                if (avVar.l != null) {
                    r0.writeUTF(avVar.l);
                } else {
                    r0.writeUTF("");
                }
                if (avVar.r != null) {
                    r0.writeUTF(avVar.r);
                } else {
                    r0.writeUTF("");
                }
                r0.writeLong(avVar.n);
                r0.writeInt(avVar.p);
                r0.writeLong(avVar.a);
                r0.writeInt(avVar.b);
                r0.writeInt(avVar.c);
                r0.writeInt(avVar.d.e);
                Map f = avVar.e;
                if (f != null) {
                    r0.writeInt(avVar.e.size());
                    for (String str : avVar.e.keySet()) {
                        r0.writeUTF(str);
                        r0.writeUTF((String) f.get(str));
                    }
                } else {
                    r0.writeInt(0);
                }
                r0.writeLong(avVar.h);
                r0.writeInt(avVar.i);
                r0.writeInt(avVar.j);
                if (avVar.k != null) {
                    r0.writeUTF(avVar.k);
                } else {
                    r0.writeUTF("");
                }
                r0.writeBoolean(avVar.m);
                r0.flush();
                this.a.a(outputStream, avVar.f);
            }
        }

        public final /* synthetic */ Object a(InputStream inputStream) throws IOException {
            HashMap hashMap;
            InputStream inputStream2 = inputStream;
            if (inputStream2 == null) {
                return null;
            }
            AnonymousClass2 r3 = new DataInputStream(inputStream2) {
                public final void close() {
                }
            };
            String readUTF = r3.readUTF();
            String str = readUTF.equals("") ? null : readUTF;
            String readUTF2 = r3.readUTF();
            long readLong = r3.readLong();
            int readInt = r3.readInt();
            long readLong2 = r3.readLong();
            int readInt2 = r3.readInt();
            int readInt3 = r3.readInt();
            bc a2 = bc.a(r3.readInt());
            int readInt4 = r3.readInt();
            if (readInt4 != 0) {
                HashMap hashMap2 = new HashMap();
                int i = 0;
                while (i < readInt4) {
                    hashMap2.put(r3.readUTF(), r3.readUTF());
                    i++;
                    readInt4 = readInt4;
                    InputStream inputStream3 = inputStream;
                }
                hashMap = hashMap2;
            } else {
                hashMap = null;
            }
            long readLong3 = r3.readLong();
            int readInt5 = r3.readInt();
            int readInt6 = r3.readInt();
            String readUTF3 = r3.readUTF();
            String str2 = readUTF3.equals("") ? null : readUTF3;
            boolean readBoolean = r3.readBoolean();
            av avVar = new av(str, readLong2, readUTF2, readLong, readInt2, readInt3, a2, hashMap, readInt5, readInt6, str2);
            long unused = avVar.h = readLong3;
            boolean unused2 = avVar.m = readBoolean;
            avVar.p = readInt;
            avVar.f = (ArrayList) this.a.a(inputStream);
            avVar.c();
            return avVar;
        }
    }
}
