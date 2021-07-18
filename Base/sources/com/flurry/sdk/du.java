package com.flurry.sdk;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public final class du<ObjectType> extends dt<ObjectType> {
    public du(dw<ObjectType> dwVar) {
        super(dwVar);
    }

    public final void a(OutputStream outputStream, ObjectType objecttype) throws IOException {
        if (outputStream != null) {
            GZIPOutputStream gZIPOutputStream = null;
            try {
                GZIPOutputStream gZIPOutputStream2 = new GZIPOutputStream(outputStream);
                try {
                    super.a(gZIPOutputStream2, objecttype);
                    em.a((Closeable) gZIPOutputStream2);
                } catch (Throwable th) {
                    th = th;
                    gZIPOutputStream = gZIPOutputStream2;
                    em.a((Closeable) gZIPOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                em.a((Closeable) gZIPOutputStream);
                throw th;
            }
        }
    }

    public final ObjectType a(InputStream inputStream) throws IOException {
        GZIPInputStream gZIPInputStream = null;
        if (inputStream == null) {
            return null;
        }
        try {
            GZIPInputStream gZIPInputStream2 = new GZIPInputStream(inputStream);
            try {
                ObjectType a = super.a(gZIPInputStream2);
                em.a((Closeable) gZIPInputStream2);
                return a;
            } catch (Throwable th) {
                th = th;
                gZIPInputStream = gZIPInputStream2;
                em.a((Closeable) gZIPInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            em.a((Closeable) gZIPInputStream);
            throw th;
        }
    }
}
