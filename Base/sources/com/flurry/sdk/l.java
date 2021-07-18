package com.flurry.sdk;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

public final class l implements Comparable<l> {
    j a;
    public int b;
    public int c;
    JSONObject d;
    Map<String, c> e = new HashMap();

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        l lVar = (l) obj;
        if (this.a != lVar.a) {
            return this.a == j.a ? -1 : 1;
        }
        return this.b - lVar.b;
    }

    public l(l lVar) {
        this.a = lVar.a;
        this.b = lVar.b;
        this.c = lVar.c;
        this.d = lVar.d;
        this.e = new HashMap(lVar.e);
    }

    public l(j jVar) {
        this.a = jVar;
    }

    public final c a(String str) {
        return this.e.get(str);
    }

    public final Set<Map.Entry<String, c>> a() {
        return this.e.entrySet();
    }

    public final void a(l lVar) {
        for (Map.Entry next : lVar.a()) {
            String str = (String) next.getKey();
            if (!this.e.containsKey(str)) {
                this.e.put(str, next.getValue());
            }
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof l)) {
            return false;
        }
        l lVar = (l) obj;
        return this.a == lVar.a && this.b == lVar.b;
    }

    public final int hashCode() {
        return (this.a.hashCode() * 31) + this.b;
    }

    public final String toString() {
        return this.a + ":" + this.b + ":" + this.c;
    }
}
