package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public final class dv<T> implements dw<List<T>> {
    dw<T> a;

    public dv(dw<T> dwVar) {
        this.a = dwVar;
    }

    public final void a(OutputStream outputStream, List<T> list) throws IOException {
        if (outputStream != null) {
            AnonymousClass1 r0 = new DataOutputStream(outputStream) {
                public final void close() {
                }
            };
            int size = list != null ? list.size() : 0;
            r0.writeInt(size);
            for (int i = 0; i < size; i++) {
                this.a.a(outputStream, list.get(i));
            }
            r0.flush();
        }
    }

    /* renamed from: b */
    public final List<T> a(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        int readInt = new DataInputStream(inputStream) {
            public final void close() {
            }
        }.readInt();
        ArrayList arrayList = new ArrayList(readInt);
        int i = 0;
        while (i < readInt) {
            T a2 = this.a.a(inputStream);
            if (a2 != null) {
                arrayList.add(a2);
                i++;
            } else {
                throw new IOException("Missing record.");
            }
        }
        return arrayList;
    }
}
