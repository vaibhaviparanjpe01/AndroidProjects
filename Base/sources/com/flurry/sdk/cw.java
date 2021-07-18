package com.flurry.sdk;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class cw {
    private static cw a;
    private final cq<String, dl<cv<?>>> b = new cq<>();
    private final cq<dl<cv<?>>, String> c = new cq<>();

    private cw() {
    }

    public static synchronized cw a() {
        cw cwVar;
        synchronized (cw.class) {
            if (a == null) {
                a = new cw();
            }
            cwVar = a;
        }
        return cwVar;
    }

    public static synchronized void b() {
        synchronized (cw.class) {
            if (a != null) {
                a.c();
                a = null;
            }
        }
    }

    private synchronized void c() {
        this.b.a();
        this.c.a();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void a(java.lang.String r3, com.flurry.sdk.cv<?> r4) {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x002e }
            if (r0 != 0) goto L_0x002c
            if (r4 != 0) goto L_0x000a
            goto L_0x002c
        L_0x000a:
            com.flurry.sdk.dl r0 = new com.flurry.sdk.dl     // Catch:{ all -> 0x002e }
            r0.<init>(r4)     // Catch:{ all -> 0x002e }
            com.flurry.sdk.cq<java.lang.String, com.flurry.sdk.dl<com.flurry.sdk.cv<?>>> r4 = r2.b     // Catch:{ all -> 0x002e }
            r1 = 0
            java.util.List r4 = r4.a(r3, (boolean) r1)     // Catch:{ all -> 0x002e }
            if (r4 == 0) goto L_0x001c
            boolean r1 = r4.contains(r0)     // Catch:{ all -> 0x002e }
        L_0x001c:
            if (r1 == 0) goto L_0x0020
            monitor-exit(r2)
            return
        L_0x0020:
            com.flurry.sdk.cq<java.lang.String, com.flurry.sdk.dl<com.flurry.sdk.cv<?>>> r4 = r2.b     // Catch:{ all -> 0x002e }
            r4.a(r3, r0)     // Catch:{ all -> 0x002e }
            com.flurry.sdk.cq<com.flurry.sdk.dl<com.flurry.sdk.cv<?>>, java.lang.String> r4 = r2.c     // Catch:{ all -> 0x002e }
            r4.a(r0, r3)     // Catch:{ all -> 0x002e }
            monitor-exit(r2)
            return
        L_0x002c:
            monitor-exit(r2)
            return
        L_0x002e:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.cw.a(java.lang.String, com.flurry.sdk.cv):void");
    }

    public final synchronized void b(String str, cv<?> cvVar) {
        if (!TextUtils.isEmpty(str)) {
            dl dlVar = new dl(cvVar);
            this.b.b(str, dlVar);
            this.c.b(dlVar, str);
        }
    }

    public final synchronized void a(cv<?> cvVar) {
        if (cvVar != null) {
            dl dlVar = new dl(cvVar);
            for (String b2 : this.c.a(dlVar)) {
                this.b.b(b2, dlVar);
            }
            this.c.b(dlVar);
        }
    }

    public final void a(final cu cuVar) {
        for (final cv next : a(cuVar.a())) {
            ck.a().b(new eo() {
                public final void a() {
                    next.a(cuVar);
                }
            });
        }
    }

    private synchronized List<cv<?>> a(String str) {
        if (TextUtils.isEmpty(str)) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Iterator<dl<cv<?>>> it = this.b.a(str).iterator();
        while (it.hasNext()) {
            cv cvVar = (cv) it.next().get();
            if (cvVar == null) {
                it.remove();
            } else {
                arrayList.add(cvVar);
            }
        }
        return arrayList;
    }
}
