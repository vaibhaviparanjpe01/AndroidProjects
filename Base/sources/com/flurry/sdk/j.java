package com.flurry.sdk;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class j {
    public static final j a = new j("APP");
    public static final j b = new j("KILLSWITCH");
    private static final Map<String, j> c = new HashMap();
    private String d;

    private j(String str) {
        this.d = str;
        c.put(str, this);
    }

    public static j a(String str) {
        if (c.containsKey(str)) {
            return c.get(str);
        }
        return new j(str);
    }

    public static Collection<j> a() {
        return c.values();
    }

    public final String toString() {
        return this.d;
    }
}
