package com.flurry.sdk;

import android.util.SparseArray;
import com.flurry.sdk.c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class m {
    static final String a = "m";
    Map<j, SparseArray<l>> b;
    Map<j, Map<String, c>> c;
    long d;
    private Map<j, Map<String, c>> e;

    public m() {
        a();
    }

    public final synchronized void a(List<l> list) {
        if (list != null) {
            if (!list.isEmpty()) {
                a(list, this.b);
                c(list);
            }
        }
    }

    public final synchronized boolean b(List<l> list) {
        if (list == null) {
            return false;
        }
        if (list.size() != e()) {
            return true;
        }
        for (l next : list) {
            SparseArray sparseArray = this.b.get(next.a);
            if (sparseArray == null) {
                return true;
            }
            l lVar = (l) sparseArray.get(next.b);
            if (lVar == null) {
                return true;
            }
            if (next.c != lVar.c) {
                return true;
            }
        }
        return false;
    }

    public final synchronized boolean a(List<l> list, boolean z) {
        if (list == null) {
            return true;
        }
        if (list.isEmpty()) {
            f();
            return true;
        } else if (z) {
            f();
            a(list, this.b);
            b(list, this.c);
            return true;
        } else {
            HashMap hashMap = new HashMap();
            a(this.b, (Map<j, SparseArray<l>>) hashMap, true, true);
            HashMap hashMap2 = new HashMap();
            a(this.c, (Map<j, Map<String, c>>) hashMap2, (j) null, true);
            a(list, (Map<j, SparseArray<l>>) hashMap, (Map<j, Map<String, c>>) hashMap2);
            a(list, (Map<j, SparseArray<l>>) hashMap);
            b(list, hashMap2);
            db.a(a, "Verify ETag merged JSON: ".concat(String.valueOf(a((Map<j, SparseArray<l>>) hashMap, (Map<j, Map<String, c>>) hashMap2, true))));
            a((Map<j, SparseArray<l>>) hashMap, this.b, false, false);
            a((Map<j, Map<String, c>>) hashMap2, this.c, (j) null, false);
            return true;
        }
    }

    private static void a(Map<j, SparseArray<l>> map, Map<j, SparseArray<l>> map2, boolean z, boolean z2) {
        SparseArray sparseArray;
        for (Map.Entry next : map.entrySet()) {
            j jVar = (j) next.getKey();
            if (z) {
                SparseArray sparseArray2 = (SparseArray) next.getValue();
                sparseArray = new SparseArray(sparseArray2.size());
                for (int i = 0; i < sparseArray2.size(); i++) {
                    l lVar = (l) sparseArray2.valueAt(i);
                    int i2 = lVar.b;
                    if (z2) {
                        lVar = new l(lVar);
                    }
                    sparseArray.put(i2, lVar);
                }
            } else {
                sparseArray = (SparseArray) next.getValue();
            }
            map2.put(jVar, sparseArray);
        }
    }

    private synchronized void a(List<l> list, Map<j, SparseArray<l>> map, Map<j, Map<String, c>> map2) {
        HashMap hashMap = new HashMap();
        a(map, (Map<j, SparseArray<l>>) hashMap, true, false);
        for (l next : list) {
            SparseArray sparseArray = (SparseArray) hashMap.get(next.a);
            if (sparseArray != null) {
                sparseArray.remove(next.b);
            }
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            j jVar = (j) entry.getKey();
            SparseArray sparseArray2 = (SparseArray) entry.getValue();
            SparseArray sparseArray3 = map.get(jVar);
            Map map3 = map2.get(jVar);
            for (int i = 0; i < sparseArray2.size(); i++) {
                l lVar = (l) sparseArray2.valueAt(i);
                sparseArray3.remove(lVar.b);
                for (String remove : lVar.e.keySet()) {
                    map3.remove(remove);
                }
            }
        }
    }

    private synchronized void a(List<l> list, Map<j, SparseArray<l>> map) {
        for (l next : list) {
            int i = next.b;
            j jVar = next.a;
            SparseArray sparseArray = map.get(jVar);
            if (sparseArray == null) {
                sparseArray = new SparseArray();
                map.put(jVar, sparseArray);
            } else {
                l lVar = (l) sparseArray.get(i);
                if (lVar != null) {
                    next.a(lVar);
                }
            }
            sparseArray.put(i, next);
        }
    }

    private static void a(Map<j, Map<String, c>> map, Map<j, Map<String, c>> map2, j jVar, boolean z) {
        for (Map.Entry next : map.entrySet()) {
            j jVar2 = (j) next.getKey();
            if (jVar == null || jVar == jVar2) {
                Map map3 = (Map) next.getValue();
                if (z) {
                    map3 = new HashMap(map3);
                }
                map2.put(jVar2, map3);
            }
        }
    }

    private synchronized void c(List<l> list) {
        for (l next : list) {
            j jVar = next.a;
            Map map = this.e.get(jVar);
            if (map == null) {
                map = new HashMap();
                this.e.put(jVar, map);
            }
            Map map2 = this.c.get(jVar);
            if (map2 == null) {
                map2 = new HashMap();
                this.c.put(jVar, map2);
            }
            for (Map.Entry next2 : next.a()) {
                String str = (String) next2.getKey();
                c cVar = (c) next2.getValue();
                map.put(str, cVar);
                map2.put(str, cVar);
            }
        }
    }

    private synchronized void b(List<l> list, Map<j, Map<String, c>> map) {
        for (l next : list) {
            j jVar = next.a;
            Map map2 = map.get(jVar);
            if (map2 == null) {
                map2 = new HashMap();
                map.put(jVar, map2);
            }
            for (Map.Entry next2 : next.a()) {
                String str = (String) next2.getKey();
                c cVar = (c) next2.getValue();
                if (cVar.a == c.a.Tombstone) {
                    map2.remove(str);
                } else {
                    map2.put(str, cVar);
                }
            }
        }
    }

    public final synchronized void a(j jVar) {
        String str = a;
        db.a(3, str, "original Variants properties:" + this.e.keySet().toString() + " with: " + this.b.values().toString());
        a(this.c, this.e, jVar, true);
        String str2 = a;
        db.a(3, str2, "new Variants properties:" + this.e.keySet().toString());
    }

    public final c a(String str, j jVar) {
        if (jVar == null) {
            for (Map<String, c> map : this.e.values()) {
                c cVar = (c) map.get(str);
                if (cVar != null) {
                    return cVar;
                }
            }
            return null;
        }
        Map map2 = this.e.get(jVar);
        if (map2 != null) {
            return (c) map2.get(str);
        }
        return null;
    }

    public final synchronized void a() {
        f();
        this.e = new HashMap();
        for (j put : j.a()) {
            this.e.put(put, new HashMap());
        }
    }

    private synchronized void f() {
        this.b = new HashMap();
        this.c = new HashMap();
        for (j next : j.a()) {
            this.b.put(next, new SparseArray());
            this.c.put(next, new HashMap());
        }
    }

    public final synchronized List<l> b() {
        return a(this.b);
    }

    private synchronized List<l> a(Map<j, SparseArray<l>> map) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (SparseArray next : map.values()) {
            for (int i = 0; i < next.size(); i++) {
                arrayList.add(next.valueAt(i));
            }
        }
        return arrayList;
    }

    public final synchronized String c() {
        StringBuilder sb;
        sb = new StringBuilder();
        int i = 0;
        for (SparseArray next : this.b.values()) {
            i += next.size();
            for (int i2 = 0; i2 < next.size(); i2++) {
                l lVar = (l) next.valueAt(i2);
                sb.append("," + lVar.b);
                sb.append("," + lVar.c);
            }
        }
        sb.insert(0, i);
        return sb.toString();
    }

    public final synchronized List<j> d() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (Map.Entry next : this.b.entrySet()) {
            if (((SparseArray) next.getValue()).size() > 0) {
                arrayList.add(next.getKey());
            }
        }
        return arrayList;
    }

    public final synchronized int e() {
        int i;
        i = 0;
        for (SparseArray<l> size : this.b.values()) {
            i += size.size();
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public final JSONObject a(Map<j, SparseArray<l>> map, Map<j, Map<String, c>> map2, boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            List<l> a2 = a(map);
            if (z) {
                Collections.sort(a2);
            }
            for (l next : a2) {
                Map map3 = map2.get(next.a);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("id", next.b);
                jSONObject2.put("version", next.c);
                jSONObject2.put("document", next.a.toString());
                JSONArray jSONArray2 = new JSONArray();
                for (Map.Entry<String, c> key : z ? new TreeMap(next.e).entrySet() : next.a()) {
                    String str = (String) key.getKey();
                    c cVar = (c) map3.get(str);
                    if (cVar != null) {
                        jSONArray2.put(cVar.a(str));
                    }
                }
                jSONObject2.put("items", jSONArray2);
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("variants", jSONArray);
            jSONObject.put("refreshInSeconds", this.d);
            return jSONObject;
        } catch (JSONException e2) {
            db.a(a, "Error to create JSON object.", (Throwable) e2);
            return null;
        }
    }
}
