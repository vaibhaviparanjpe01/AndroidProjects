package com.flurry.sdk;

import com.flurry.sdk.bp;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

public final class aa {
    private static final String a = "aa";

    public static bp a(File file) {
        DataInputStream dataInputStream;
        FileInputStream fileInputStream;
        if (file == null || !file.exists()) {
            return null;
        }
        bp.a aVar = new bp.a();
        try {
            fileInputStream = new FileInputStream(file);
            try {
                dataInputStream = new DataInputStream(fileInputStream);
            } catch (Exception e) {
                e = e;
                dataInputStream = null;
                try {
                    db.a(3, a, "Error loading legacy agent data.", e);
                    em.a((Closeable) dataInputStream);
                    em.a((Closeable) fileInputStream);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    em.a((Closeable) dataInputStream);
                    em.a((Closeable) fileInputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                dataInputStream = null;
                em.a((Closeable) dataInputStream);
                em.a((Closeable) fileInputStream);
                throw th;
            }
            try {
                if (dataInputStream.readUnsignedShort() != 46586) {
                    db.a(3, a, "Unexpected file type");
                } else {
                    int readUnsignedShort = dataInputStream.readUnsignedShort();
                    if (readUnsignedShort != 2) {
                        db.a(6, a, "Unknown agent file version: ".concat(String.valueOf(readUnsignedShort)));
                    } else {
                        bp bpVar = (bp) aVar.a(dataInputStream);
                        em.a((Closeable) dataInputStream);
                        em.a((Closeable) fileInputStream);
                        return bpVar;
                    }
                }
                em.a((Closeable) dataInputStream);
                em.a((Closeable) fileInputStream);
                return null;
            } catch (Exception e2) {
                e = e2;
                db.a(3, a, "Error loading legacy agent data.", e);
                em.a((Closeable) dataInputStream);
                em.a((Closeable) fileInputStream);
                return null;
            }
        } catch (Exception e3) {
            e = e3;
            dataInputStream = null;
            fileInputStream = null;
            db.a(3, a, "Error loading legacy agent data.", e);
            em.a((Closeable) dataInputStream);
            em.a((Closeable) fileInputStream);
            return null;
        } catch (Throwable th3) {
            fileInputStream = null;
            th = th3;
            dataInputStream = null;
            em.a((Closeable) dataInputStream);
            em.a((Closeable) fileInputStream);
            throw th;
        }
    }
}
