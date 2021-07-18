package com.flurry.sdk;

import java.util.HashMap;
import java.util.Map;

public class cm {
    private static final String a = "cm";
    private static final HashMap<String, Map<String, String>> b = new HashMap<>();
    private static cm c;

    public static synchronized cm a() {
        cm cmVar;
        synchronized (cm.class) {
            if (c == null) {
                c = new cm();
            }
            cmVar = c;
        }
        return cmVar;
    }

    public static synchronized void b() {
        synchronized (cm.class) {
            c = null;
        }
    }

    public final synchronized void a(String str, String str2, Map<String, String> map) {
        if (map == null) {
            try {
                map = new HashMap<>();
            } catch (Throwable th) {
                throw th;
            }
        }
        if (map.size() >= 10) {
            String str3 = a;
            db.e(str3, "MaxOriginParams exceeded: " + map.size());
            return;
        }
        map.put("flurryOriginVersion", str2);
        synchronized (b) {
            if (b.size() < 10 || b.containsKey(str)) {
                b.put(str, map);
                return;
            }
            String str4 = a;
            db.e(str4, "MaxOrigins exceeded: " + b.size());
        }
    }

    public final synchronized HashMap<String, Map<String, String>> c() {
        HashMap<String, Map<String, String>> hashMap;
        synchronized (b) {
            hashMap = new HashMap<>(b);
        }
        return hashMap;
    }
}
