package com.flurry.sdk;

import java.io.File;

public class ct<T> {
    private static final String a = "ct";
    private final File b;
    private final dw<T> c;

    public ct(File file, String str, int i, dz<T> dzVar) {
        this.b = file;
        this.c = new du(new dy(str, i, dzVar));
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0063  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final T a() {
        /*
            r7 = this;
            java.io.File r0 = r7.b
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            java.io.File r0 = r7.b
            boolean r0 = r0.exists()
            if (r0 != 0) goto L_0x0029
            r0 = 5
            java.lang.String r2 = a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "No data to read for file:"
            r3.<init>(r4)
            java.io.File r4 = r7.b
            java.lang.String r4 = r4.getName()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.flurry.sdk.db.a((int) r0, (java.lang.String) r2, (java.lang.String) r3)
            return r1
        L_0x0029:
            r0 = 0
            r2 = 3
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0042, all -> 0x003f }
            java.io.File r4 = r7.b     // Catch:{ Exception -> 0x0042, all -> 0x003f }
            r3.<init>(r4)     // Catch:{ Exception -> 0x0042, all -> 0x003f }
            com.flurry.sdk.dw<T> r4 = r7.c     // Catch:{ Exception -> 0x003d }
            java.lang.Object r4 = r4.a(r3)     // Catch:{ Exception -> 0x003d }
            com.flurry.sdk.em.a((java.io.Closeable) r3)
            r1 = r4
            goto L_0x0061
        L_0x003d:
            r0 = move-exception
            goto L_0x0044
        L_0x003f:
            r0 = move-exception
            r3 = r1
            goto L_0x0083
        L_0x0042:
            r0 = move-exception
            r3 = r1
        L_0x0044:
            java.lang.String r4 = a     // Catch:{ all -> 0x0082 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0082 }
            java.lang.String r6 = "Error reading data file:"
            r5.<init>(r6)     // Catch:{ all -> 0x0082 }
            java.io.File r6 = r7.b     // Catch:{ all -> 0x0082 }
            java.lang.String r6 = r6.getName()     // Catch:{ all -> 0x0082 }
            r5.append(r6)     // Catch:{ all -> 0x0082 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0082 }
            com.flurry.sdk.db.a(r2, r4, r5, r0)     // Catch:{ all -> 0x0082 }
            r0 = 1
            com.flurry.sdk.em.a((java.io.Closeable) r3)
        L_0x0061:
            if (r0 == 0) goto L_0x0081
            java.lang.String r0 = a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Deleting data file:"
            r3.<init>(r4)
            java.io.File r4 = r7.b
            java.lang.String r4 = r4.getName()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.flurry.sdk.db.a((int) r2, (java.lang.String) r0, (java.lang.String) r3)
            java.io.File r0 = r7.b
            r0.delete()
        L_0x0081:
            return r1
        L_0x0082:
            r0 = move-exception
        L_0x0083:
            com.flurry.sdk.em.a((java.io.Closeable) r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.ct.a():java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(T r7) {
        /*
            r6 = this;
            r0 = 1
            r1 = 3
            if (r7 != 0) goto L_0x001e
            java.lang.String r7 = a
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "No data to write for file:"
            r2.<init>(r3)
            java.io.File r3 = r6.b
            java.lang.String r3 = r3.getName()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.flurry.sdk.db.a((int) r1, (java.lang.String) r7, (java.lang.String) r2)
            goto L_0x0065
        L_0x001e:
            r2 = 0
            java.io.File r3 = r6.b     // Catch:{ Exception -> 0x0048 }
            boolean r3 = com.flurry.sdk.el.a(r3)     // Catch:{ Exception -> 0x0048 }
            if (r3 == 0) goto L_0x003e
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0048 }
            java.io.File r4 = r6.b     // Catch:{ Exception -> 0x0048 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x0048 }
            com.flurry.sdk.dw<T> r2 = r6.c     // Catch:{ Exception -> 0x003b, all -> 0x0038 }
            r2.a(r3, r7)     // Catch:{ Exception -> 0x003b, all -> 0x0038 }
            com.flurry.sdk.em.a((java.io.Closeable) r3)
            r0 = 0
            goto L_0x0065
        L_0x0038:
            r7 = move-exception
            r2 = r3
            goto L_0x0086
        L_0x003b:
            r7 = move-exception
            r2 = r3
            goto L_0x0049
        L_0x003e:
            java.io.IOException r7 = new java.io.IOException     // Catch:{ Exception -> 0x0048 }
            java.lang.String r3 = "Cannot create parent directory!"
            r7.<init>(r3)     // Catch:{ Exception -> 0x0048 }
            throw r7     // Catch:{ Exception -> 0x0048 }
        L_0x0046:
            r7 = move-exception
            goto L_0x0086
        L_0x0048:
            r7 = move-exception
        L_0x0049:
            java.lang.String r3 = a     // Catch:{ all -> 0x0046 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0046 }
            java.lang.String r5 = "Error writing data file:"
            r4.<init>(r5)     // Catch:{ all -> 0x0046 }
            java.io.File r5 = r6.b     // Catch:{ all -> 0x0046 }
            java.lang.String r5 = r5.getName()     // Catch:{ all -> 0x0046 }
            r4.append(r5)     // Catch:{ all -> 0x0046 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0046 }
            com.flurry.sdk.db.a(r1, r3, r4, r7)     // Catch:{ all -> 0x0046 }
            com.flurry.sdk.em.a((java.io.Closeable) r2)
        L_0x0065:
            if (r0 == 0) goto L_0x0085
            java.lang.String r7 = a
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "Deleting data file:"
            r0.<init>(r2)
            java.io.File r2 = r6.b
            java.lang.String r2 = r2.getName()
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            com.flurry.sdk.db.a((int) r1, (java.lang.String) r7, (java.lang.String) r0)
            java.io.File r7 = r6.b
            r7.delete()
        L_0x0085:
            return
        L_0x0086:
            com.flurry.sdk.em.a((java.io.Closeable) r2)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.ct.a(java.lang.Object):void");
    }

    public final boolean b() {
        if (this.b == null) {
            return false;
        }
        return this.b.delete();
    }
}
