package com.flurry.sdk;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class cq<K, V> {
    public final Map<K, List<V>> a = new HashMap();
    private int b;

    public final void a() {
        this.a.clear();
    }

    public final List<V> a(K k) {
        if (k == null) {
            return Collections.emptyList();
        }
        List<V> a2 = a(k, false);
        return a2 == null ? Collections.emptyList() : a2;
    }

    public final List<V> a(K k, boolean z) {
        ArrayList arrayList;
        List<V> list = this.a.get(k);
        if (z && list == null) {
            if (this.b > 0) {
                arrayList = new ArrayList(this.b);
            } else {
                arrayList = new ArrayList();
            }
            list = arrayList;
            this.a.put(k, list);
        }
        return list;
    }

    public final void a(K k, V v) {
        if (k != null) {
            a(k, true).add(v);
        }
    }

    public final boolean b(K k, V v) {
        List a2;
        if (k == null || (a2 = a(k, false)) == null) {
            return false;
        }
        boolean remove = a2.remove(v);
        if (a2.size() == 0) {
            this.a.remove(k);
        }
        return remove;
    }

    public final boolean b(K k) {
        return this.a.remove(k) != null;
    }

    public final Collection<Map.Entry<K, V>> b() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry next : this.a.entrySet()) {
            for (Object simpleImmutableEntry : (List) next.getValue()) {
                arrayList.add(new AbstractMap.SimpleImmutableEntry(next.getKey(), simpleImmutableEntry));
            }
        }
        return arrayList;
    }
}
