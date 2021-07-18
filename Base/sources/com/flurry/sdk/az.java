package com.flurry.sdk;

import android.annotation.SuppressLint;
import com.flurry.sdk.av;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public final class az {
    private static final String f = ba.class.getName();
    public long a;
    /* access modifiers changed from: package-private */
    public bd b;
    /* access modifiers changed from: package-private */
    public int c;
    public String d;
    /* access modifiers changed from: package-private */
    public Map<Long, av> e;
    /* access modifiers changed from: private */
    public long g = System.currentTimeMillis();
    /* access modifiers changed from: private */
    public long h;
    /* access modifiers changed from: private */
    public boolean i;
    /* access modifiers changed from: private */
    public int j;
    /* access modifiers changed from: private */
    public AtomicInteger k;

    public az(String str, boolean z, long j2, long j3, bd bdVar, Map<Long, av> map) {
        this.d = str;
        this.i = z;
        this.a = j2;
        this.h = j3;
        this.b = bdVar;
        this.e = map;
        if (map != null) {
            for (Long l : map.keySet()) {
                map.get(l).g = this;
            }
            this.j = map.size();
        } else {
            this.j = 0;
        }
        this.k = new AtomicInteger(0);
    }

    public final List<av> a() {
        if (this.e != null) {
            return new ArrayList(this.e.values());
        }
        return Collections.emptyList();
    }

    public final synchronized boolean b() {
        return this.k.intValue() >= this.j;
    }

    public final synchronized void c() {
        this.k.incrementAndGet();
    }

    public final byte[] d() throws IOException {
        DataOutputStream dataOutputStream;
        IOException e2;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            try {
                dataOutputStream.writeShort(this.b.e);
                dataOutputStream.writeLong(this.a);
                dataOutputStream.writeLong(this.h);
                dataOutputStream.writeBoolean(this.i);
                if (this.i) {
                    dataOutputStream.writeShort(this.c);
                    dataOutputStream.writeUTF(this.d);
                }
                dataOutputStream.writeShort(this.e.size());
                if (this.e != null) {
                    for (Map.Entry next : this.e.entrySet()) {
                        av avVar = (av) next.getValue();
                        dataOutputStream.writeLong(((Long) next.getKey()).longValue());
                        dataOutputStream.writeUTF(avVar.r);
                        dataOutputStream.writeShort(avVar.f.size());
                        Iterator<aw> it = avVar.f.iterator();
                        while (it.hasNext()) {
                            aw next2 = it.next();
                            dataOutputStream.writeShort(next2.a);
                            dataOutputStream.writeLong(next2.b);
                            dataOutputStream.writeLong(next2.c);
                            dataOutputStream.writeBoolean(next2.d);
                            dataOutputStream.writeShort(next2.e);
                            dataOutputStream.writeShort(next2.f.e);
                            if ((next2.e < 200 || next2.e >= 400) && next2.g != null) {
                                byte[] bytes = next2.g.getBytes();
                                dataOutputStream.writeShort(bytes.length);
                                dataOutputStream.write(bytes);
                            }
                            dataOutputStream.writeShort(next2.h);
                            dataOutputStream.writeInt((int) next2.k);
                        }
                    }
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                em.a((Closeable) dataOutputStream);
                return byteArray;
            } catch (IOException e3) {
                e2 = e3;
                try {
                    db.a(6, f, "Error when generating report", e2);
                    throw e2;
                } catch (Throwable th) {
                    th = th;
                    em.a((Closeable) dataOutputStream);
                    throw th;
                }
            }
        } catch (IOException e4) {
            dataOutputStream = null;
            e2 = e4;
            db.a(6, f, "Error when generating report", e2);
            throw e2;
        } catch (Throwable th2) {
            dataOutputStream = null;
            th = th2;
            em.a((Closeable) dataOutputStream);
            throw th;
        }
    }

    public static class a implements dw<az> {
        dv<av> a = new dv<>(new av.a());

        public final /* synthetic */ void a(OutputStream outputStream, Object obj) throws IOException {
            az azVar = (az) obj;
            if (outputStream != null && azVar != null) {
                AnonymousClass1 r0 = new DataOutputStream(outputStream) {
                    public final void close() {
                    }
                };
                r0.writeLong(azVar.a);
                r0.writeLong(azVar.g);
                r0.writeLong(azVar.h);
                r0.writeInt(azVar.b.e);
                r0.writeBoolean(azVar.i);
                r0.writeInt(azVar.c);
                if (azVar.d != null) {
                    r0.writeUTF(azVar.d);
                } else {
                    r0.writeUTF("");
                }
                r0.writeInt(azVar.j);
                r0.writeInt(azVar.k.intValue());
                r0.flush();
                this.a.a(outputStream, azVar.a());
            }
        }

        @SuppressLint({"UseSparseArrays"})
        public final /* synthetic */ Object a(InputStream inputStream) throws IOException {
            InputStream inputStream2 = inputStream;
            if (inputStream2 == null) {
                return null;
            }
            AnonymousClass2 r2 = new DataInputStream(inputStream2) {
                public final void close() {
                }
            };
            long readLong = r2.readLong();
            long readLong2 = r2.readLong();
            long readLong3 = r2.readLong();
            bd a2 = bd.a(r2.readInt());
            boolean readBoolean = r2.readBoolean();
            int readInt = r2.readInt();
            String readUTF = r2.readUTF();
            int readInt2 = r2.readInt();
            int readInt3 = r2.readInt();
            az azVar = r3;
            az azVar2 = new az(readUTF, readBoolean, readLong, readLong3, a2, (Map<Long, av>) null);
            long unused = azVar.g = readLong2;
            int unused2 = azVar.c = readInt;
            int unused3 = azVar.j = readInt2;
            AtomicInteger unused4 = azVar.k = new AtomicInteger(readInt3);
            az azVar3 = azVar;
            List<av> b = this.a.a(inputStream);
            if (b != null) {
                Map unused5 = azVar3.e = new HashMap();
                for (av next : b) {
                    next.g = azVar3;
                    azVar3.e.put(Long.valueOf(next.a), next);
                }
            }
            return azVar3;
        }
    }
}
