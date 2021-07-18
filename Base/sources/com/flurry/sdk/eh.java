package com.flurry.sdk;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class eh {
    private Map<String, Object> a = new HashMap();
    private Map<String, List<a>> b = new HashMap();

    public interface a {
        void a(String str, Object obj);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0051, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x001f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void a(java.lang.String r3, java.lang.Object r4) {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x0052 }
            if (r0 == 0) goto L_0x0009
            monitor-exit(r2)
            return
        L_0x0009:
            java.util.Map<java.lang.String, java.lang.Object> r0 = r2.a     // Catch:{ all -> 0x0052 }
            java.lang.Object r0 = r0.get(r3)     // Catch:{ all -> 0x0052 }
            if (r4 == r0) goto L_0x001c
            if (r4 == 0) goto L_0x001a
            boolean r0 = r4.equals(r0)     // Catch:{ all -> 0x0052 }
            if (r0 == 0) goto L_0x001a
            goto L_0x001c
        L_0x001a:
            r0 = 0
            goto L_0x001d
        L_0x001c:
            r0 = 1
        L_0x001d:
            if (r0 != 0) goto L_0x0050
            if (r4 != 0) goto L_0x0027
            java.util.Map<java.lang.String, java.lang.Object> r0 = r2.a     // Catch:{ all -> 0x0052 }
            r0.remove(r3)     // Catch:{ all -> 0x0052 }
            goto L_0x002c
        L_0x0027:
            java.util.Map<java.lang.String, java.lang.Object> r0 = r2.a     // Catch:{ all -> 0x0052 }
            r0.put(r3, r4)     // Catch:{ all -> 0x0052 }
        L_0x002c:
            java.util.Map<java.lang.String, java.util.List<com.flurry.sdk.eh$a>> r0 = r2.b     // Catch:{ all -> 0x0052 }
            java.lang.Object r0 = r0.get(r3)     // Catch:{ all -> 0x0052 }
            if (r0 == 0) goto L_0x0050
            java.util.Map<java.lang.String, java.util.List<com.flurry.sdk.eh$a>> r0 = r2.b     // Catch:{ all -> 0x0052 }
            java.lang.Object r0 = r0.get(r3)     // Catch:{ all -> 0x0052 }
            java.util.List r0 = (java.util.List) r0     // Catch:{ all -> 0x0052 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0052 }
        L_0x0040:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x0052 }
            if (r1 == 0) goto L_0x0050
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x0052 }
            com.flurry.sdk.eh$a r1 = (com.flurry.sdk.eh.a) r1     // Catch:{ all -> 0x0052 }
            r1.a(r3, r4)     // Catch:{ all -> 0x0052 }
            goto L_0x0040
        L_0x0050:
            monitor-exit(r2)
            return
        L_0x0052:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.eh.a(java.lang.String, java.lang.Object):void");
    }

    public final synchronized Object a(String str) {
        return this.a.get(str);
    }

    public final synchronized void a(String str, a aVar) {
        if (!TextUtils.isEmpty(str)) {
            List list = this.b.get(str);
            if (list == null) {
                list = new LinkedList();
            }
            list.add(aVar);
            this.b.put(str, list);
        }
    }

    public final synchronized boolean b(String str, a aVar) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (aVar == null) {
            return false;
        }
        List list = this.b.get(str);
        if (list == null) {
            return false;
        }
        return list.remove(aVar);
    }

    public final synchronized void c() {
        this.b.clear();
    }
}
