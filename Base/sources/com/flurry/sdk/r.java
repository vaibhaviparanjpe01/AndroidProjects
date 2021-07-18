package com.flurry.sdk;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

public abstract class r implements Runnable {
    private static final String i = "r";
    private static ExecutorService j;
    protected String a;
    protected g b;
    protected String c;
    protected String d;
    protected String e;
    protected String f;
    protected String g;
    public String h;

    /* access modifiers changed from: protected */
    public abstract InputStream a() throws IOException;

    /* access modifiers changed from: protected */
    public boolean a(String str) {
        return true;
    }

    /* access modifiers changed from: protected */
    public abstract void b();

    public boolean c() {
        return true;
    }

    public static Future<?> a(Runnable runnable) {
        db.a(i, "Submit transport task.");
        if (j == null) {
            j = Executors.newFixedThreadPool(2, new ThreadFactory() {
                int a = 0;

                public final Thread newThread(Runnable runnable) {
                    StringBuilder sb = new StringBuilder("Flurry-Config-Background-");
                    int i = this.a + 1;
                    this.a = i;
                    sb.append(i);
                    return new Thread(runnable, sb.toString());
                }
            });
        }
        return j.submit(runnable);
    }

    /* JADX WARNING: Removed duplicated region for block: B:103:0x017d A[SYNTHETIC, Splitter:B:103:0x017d] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00e8 A[SYNTHETIC, Splitter:B:63:0x00e8] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0116 A[SYNTHETIC, Splitter:B:74:0x0116] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0144 A[SYNTHETIC, Splitter:B:85:0x0144] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:60:0x00d9=Splitter:B:60:0x00d9, B:93:0x015a=Splitter:B:93:0x015a, B:82:0x012c=Splitter:B:82:0x012c, B:71:0x00fe=Splitter:B:71:0x00fe} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r7 = this;
            com.flurry.sdk.g r0 = com.flurry.sdk.g.a
            r7.b = r0
            r0 = 0
            java.io.InputStream r1 = r7.a()     // Catch:{ MalformedURLException -> 0x0156, SSLException -> 0x0128, IOException -> 0x00fa, Exception -> 0x00d5, all -> 0x00cf }
            com.flurry.sdk.g r0 = r7.b     // Catch:{ MalformedURLException -> 0x00cc, SSLException -> 0x00c9, IOException -> 0x00c7, Exception -> 0x00c5 }
            com.flurry.sdk.g r2 = com.flurry.sdk.g.a     // Catch:{ MalformedURLException -> 0x00cc, SSLException -> 0x00c9, IOException -> 0x00c7, Exception -> 0x00c5 }
            if (r0 == r2) goto L_0x0023
            if (r1 == 0) goto L_0x001f
            r1.close()     // Catch:{ IOException -> 0x0015 }
            goto L_0x001f
        L_0x0015:
            r0 = move-exception
            java.lang.String r1 = i
            java.lang.String r2 = r0.getMessage()
            com.flurry.sdk.db.b((java.lang.String) r1, (java.lang.String) r2, (java.lang.Throwable) r0)
        L_0x001f:
            r7.b()
            return
        L_0x0023:
            if (r1 != 0) goto L_0x004b
            java.lang.String r0 = i     // Catch:{ MalformedURLException -> 0x00cc, SSLException -> 0x00c9, IOException -> 0x00c7, Exception -> 0x00c5 }
            java.lang.String r2 = "Null InputStream"
            com.flurry.sdk.db.b(r0, r2)     // Catch:{ MalformedURLException -> 0x00cc, SSLException -> 0x00c9, IOException -> 0x00c7, Exception -> 0x00c5 }
            com.flurry.sdk.g r0 = new com.flurry.sdk.g     // Catch:{ MalformedURLException -> 0x00cc, SSLException -> 0x00c9, IOException -> 0x00c7, Exception -> 0x00c5 }
            com.flurry.sdk.g$a r2 = com.flurry.sdk.g.a.IO     // Catch:{ MalformedURLException -> 0x00cc, SSLException -> 0x00c9, IOException -> 0x00c7, Exception -> 0x00c5 }
            java.lang.String r3 = "Null InputStream"
            r0.<init>(r2, r3)     // Catch:{ MalformedURLException -> 0x00cc, SSLException -> 0x00c9, IOException -> 0x00c7, Exception -> 0x00c5 }
            r7.b = r0     // Catch:{ MalformedURLException -> 0x00cc, SSLException -> 0x00c9, IOException -> 0x00c7, Exception -> 0x00c5 }
            if (r1 == 0) goto L_0x0047
            r1.close()     // Catch:{ IOException -> 0x003d }
            goto L_0x0047
        L_0x003d:
            r0 = move-exception
            java.lang.String r1 = i
            java.lang.String r2 = r0.getMessage()
            com.flurry.sdk.db.b((java.lang.String) r1, (java.lang.String) r2, (java.lang.Throwable) r0)
        L_0x0047:
            r7.b()
            return
        L_0x004b:
            java.nio.channels.ReadableByteChannel r0 = java.nio.channels.Channels.newChannel(r1)     // Catch:{ MalformedURLException -> 0x00cc, SSLException -> 0x00c9, IOException -> 0x00c7, Exception -> 0x00c5 }
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ MalformedURLException -> 0x00cc, SSLException -> 0x00c9, IOException -> 0x00c7, Exception -> 0x00c5 }
            r2.<init>()     // Catch:{ MalformedURLException -> 0x00cc, SSLException -> 0x00c9, IOException -> 0x00c7, Exception -> 0x00c5 }
            java.nio.channels.WritableByteChannel r3 = java.nio.channels.Channels.newChannel(r2)     // Catch:{ MalformedURLException -> 0x00cc, SSLException -> 0x00c9, IOException -> 0x00c7, Exception -> 0x00c5 }
            r4 = 16384(0x4000, float:2.2959E-41)
            java.nio.ByteBuffer r4 = java.nio.ByteBuffer.allocateDirect(r4)     // Catch:{ MalformedURLException -> 0x00cc, SSLException -> 0x00c9, IOException -> 0x00c7, Exception -> 0x00c5 }
        L_0x005e:
            int r5 = r0.read(r4)     // Catch:{ MalformedURLException -> 0x00cc, SSLException -> 0x00c9, IOException -> 0x00c7, Exception -> 0x00c5 }
            if (r5 >= 0) goto L_0x00b8
            int r5 = r4.position()     // Catch:{ MalformedURLException -> 0x00cc, SSLException -> 0x00c9, IOException -> 0x00c7, Exception -> 0x00c5 }
            if (r5 <= 0) goto L_0x006b
            goto L_0x00b8
        L_0x006b:
            r2.flush()     // Catch:{ MalformedURLException -> 0x00cc, SSLException -> 0x00c9, IOException -> 0x00c7, Exception -> 0x00c5 }
            java.lang.String r0 = r2.toString()     // Catch:{ MalformedURLException -> 0x00cc, SSLException -> 0x00c9, IOException -> 0x00c7, Exception -> 0x00c5 }
            boolean r0 = r7.a((java.lang.String) r0)     // Catch:{ MalformedURLException -> 0x00cc, SSLException -> 0x00c9, IOException -> 0x00c7, Exception -> 0x00c5 }
            if (r0 != 0) goto L_0x0097
            com.flurry.sdk.g r0 = new com.flurry.sdk.g     // Catch:{ MalformedURLException -> 0x00cc, SSLException -> 0x00c9, IOException -> 0x00c7, Exception -> 0x00c5 }
            com.flurry.sdk.g$a r2 = com.flurry.sdk.g.a.AUTHENTICATE     // Catch:{ MalformedURLException -> 0x00cc, SSLException -> 0x00c9, IOException -> 0x00c7, Exception -> 0x00c5 }
            java.lang.String r3 = "Signature Error."
            r0.<init>(r2, r3)     // Catch:{ MalformedURLException -> 0x00cc, SSLException -> 0x00c9, IOException -> 0x00c7, Exception -> 0x00c5 }
            r7.b = r0     // Catch:{ MalformedURLException -> 0x00cc, SSLException -> 0x00c9, IOException -> 0x00c7, Exception -> 0x00c5 }
            if (r1 == 0) goto L_0x0093
            r1.close()     // Catch:{ IOException -> 0x0089 }
            goto L_0x0093
        L_0x0089:
            r0 = move-exception
            java.lang.String r1 = i
            java.lang.String r2 = r0.getMessage()
            com.flurry.sdk.db.b((java.lang.String) r1, (java.lang.String) r2, (java.lang.Throwable) r0)
        L_0x0093:
            r7.b()
            return
        L_0x0097:
            java.lang.String r0 = new java.lang.String     // Catch:{ MalformedURLException -> 0x00cc, SSLException -> 0x00c9, IOException -> 0x00c7, Exception -> 0x00c5 }
            byte[] r2 = r2.toByteArray()     // Catch:{ MalformedURLException -> 0x00cc, SSLException -> 0x00c9, IOException -> 0x00c7, Exception -> 0x00c5 }
            java.lang.String r3 = "utf-8"
            r0.<init>(r2, r3)     // Catch:{ MalformedURLException -> 0x00cc, SSLException -> 0x00c9, IOException -> 0x00c7, Exception -> 0x00c5 }
            r7.h = r0     // Catch:{ MalformedURLException -> 0x00cc, SSLException -> 0x00c9, IOException -> 0x00c7, Exception -> 0x00c5 }
            if (r1 == 0) goto L_0x00b4
            r1.close()     // Catch:{ IOException -> 0x00aa }
            goto L_0x00b4
        L_0x00aa:
            r0 = move-exception
            java.lang.String r1 = i
            java.lang.String r2 = r0.getMessage()
            com.flurry.sdk.db.b((java.lang.String) r1, (java.lang.String) r2, (java.lang.Throwable) r0)
        L_0x00b4:
            r7.b()
            return
        L_0x00b8:
            r4.flip()     // Catch:{ MalformedURLException -> 0x00cc, SSLException -> 0x00c9, IOException -> 0x00c7, Exception -> 0x00c5 }
            r3.write(r4)     // Catch:{ MalformedURLException -> 0x00cc, SSLException -> 0x00c9, IOException -> 0x00c7, Exception -> 0x00c5 }
            r4.compact()     // Catch:{ MalformedURLException -> 0x00cc, SSLException -> 0x00c9, IOException -> 0x00c7, Exception -> 0x00c5 }
            goto L_0x005e
        L_0x00c2:
            r0 = move-exception
            goto L_0x017b
        L_0x00c5:
            r0 = move-exception
            goto L_0x00d9
        L_0x00c7:
            r0 = move-exception
            goto L_0x00fe
        L_0x00c9:
            r0 = move-exception
            goto L_0x012c
        L_0x00cc:
            r0 = move-exception
            goto L_0x015a
        L_0x00cf:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L_0x017b
        L_0x00d5:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
        L_0x00d9:
            com.flurry.sdk.g r2 = new com.flurry.sdk.g     // Catch:{ all -> 0x00c2 }
            com.flurry.sdk.g$a r3 = com.flurry.sdk.g.a.OTHER     // Catch:{ all -> 0x00c2 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00c2 }
            r2.<init>(r3, r0)     // Catch:{ all -> 0x00c2 }
            r7.b = r2     // Catch:{ all -> 0x00c2 }
            if (r1 == 0) goto L_0x00f6
            r1.close()     // Catch:{ IOException -> 0x00ec }
            goto L_0x00f6
        L_0x00ec:
            r0 = move-exception
            java.lang.String r1 = i
            java.lang.String r2 = r0.getMessage()
            com.flurry.sdk.db.b((java.lang.String) r1, (java.lang.String) r2, (java.lang.Throwable) r0)
        L_0x00f6:
            r7.b()
            return
        L_0x00fa:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
        L_0x00fe:
            java.lang.String r2 = i     // Catch:{ all -> 0x00c2 }
            java.lang.String r3 = r0.getMessage()     // Catch:{ all -> 0x00c2 }
            com.flurry.sdk.db.a((java.lang.String) r2, (java.lang.String) r3, (java.lang.Throwable) r0)     // Catch:{ all -> 0x00c2 }
            com.flurry.sdk.g r2 = new com.flurry.sdk.g     // Catch:{ all -> 0x00c2 }
            com.flurry.sdk.g$a r3 = com.flurry.sdk.g.a.IO     // Catch:{ all -> 0x00c2 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00c2 }
            r2.<init>(r3, r0)     // Catch:{ all -> 0x00c2 }
            r7.b = r2     // Catch:{ all -> 0x00c2 }
            if (r1 == 0) goto L_0x0124
            r1.close()     // Catch:{ IOException -> 0x011a }
            goto L_0x0124
        L_0x011a:
            r0 = move-exception
            java.lang.String r1 = i
            java.lang.String r2 = r0.getMessage()
            com.flurry.sdk.db.b((java.lang.String) r1, (java.lang.String) r2, (java.lang.Throwable) r0)
        L_0x0124:
            r7.b()
            return
        L_0x0128:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
        L_0x012c:
            java.lang.String r2 = i     // Catch:{ all -> 0x00c2 }
            java.lang.String r3 = r0.getMessage()     // Catch:{ all -> 0x00c2 }
            com.flurry.sdk.db.a((java.lang.String) r2, (java.lang.String) r3, (java.lang.Throwable) r0)     // Catch:{ all -> 0x00c2 }
            com.flurry.sdk.g r2 = new com.flurry.sdk.g     // Catch:{ all -> 0x00c2 }
            com.flurry.sdk.g$a r3 = com.flurry.sdk.g.a.UNKNOWN_CERTIFICATE     // Catch:{ all -> 0x00c2 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00c2 }
            r2.<init>(r3, r0)     // Catch:{ all -> 0x00c2 }
            r7.b = r2     // Catch:{ all -> 0x00c2 }
            if (r1 == 0) goto L_0x0152
            r1.close()     // Catch:{ IOException -> 0x0148 }
            goto L_0x0152
        L_0x0148:
            r0 = move-exception
            java.lang.String r1 = i
            java.lang.String r2 = r0.getMessage()
            com.flurry.sdk.db.b((java.lang.String) r1, (java.lang.String) r2, (java.lang.Throwable) r0)
        L_0x0152:
            r7.b()
            return
        L_0x0156:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
        L_0x015a:
            com.flurry.sdk.g r2 = new com.flurry.sdk.g     // Catch:{ all -> 0x00c2 }
            com.flurry.sdk.g$a r3 = com.flurry.sdk.g.a.OTHER     // Catch:{ all -> 0x00c2 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00c2 }
            r2.<init>(r3, r0)     // Catch:{ all -> 0x00c2 }
            r7.b = r2     // Catch:{ all -> 0x00c2 }
            if (r1 == 0) goto L_0x0177
            r1.close()     // Catch:{ IOException -> 0x016d }
            goto L_0x0177
        L_0x016d:
            r0 = move-exception
            java.lang.String r1 = i
            java.lang.String r2 = r0.getMessage()
            com.flurry.sdk.db.b((java.lang.String) r1, (java.lang.String) r2, (java.lang.Throwable) r0)
        L_0x0177:
            r7.b()
            return
        L_0x017b:
            if (r1 == 0) goto L_0x018b
            r1.close()     // Catch:{ IOException -> 0x0181 }
            goto L_0x018b
        L_0x0181:
            r1 = move-exception
            java.lang.String r2 = i
            java.lang.String r3 = r1.getMessage()
            com.flurry.sdk.db.b((java.lang.String) r2, (java.lang.String) r3, (java.lang.Throwable) r1)
        L_0x018b:
            r7.b()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.r.run():void");
    }

    public final String d() {
        return this.c;
    }

    public final String e() {
        return this.d;
    }

    public final String f() {
        return this.f;
    }

    public final String g() {
        return this.g;
    }

    public final g h() {
        return this.b;
    }
}
