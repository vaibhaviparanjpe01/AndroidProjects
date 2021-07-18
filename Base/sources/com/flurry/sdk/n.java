package com.flurry.sdk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class n {
    private static final String a = "n";

    public static void a(m mVar) {
        if (mVar.e() != 0) {
            ArrayList<l> arrayList = new ArrayList<>(mVar.b());
            Collections.sort(arrayList);
            ArrayList arrayList2 = new ArrayList();
            for (l lVar : arrayList) {
                arrayList2.add(Integer.toString(lVar.b));
            }
        }
    }

    public static void a(int i, long j, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("exp_code", String.valueOf(i));
        hashMap.put("exp_ms", String.valueOf(j));
        if (str != null) {
            hashMap.put("exp_det", str);
        }
        if (db.c() <= 2) {
            db.d(a, String.format("YWA event: %1$s {%2$s}", new Object[]{"expsdk_data", hashMap.toString()}));
        }
    }
}
