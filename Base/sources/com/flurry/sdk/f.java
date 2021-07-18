package com.flurry.sdk;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class f {
    private static final String a = "f";

    public static List<l> a(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray("variants");
        if (optJSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(b(optJSONObject));
            }
        }
        return arrayList;
    }

    private static l b(JSONObject jSONObject) {
        String optString;
        l lVar = new l(j.a(jSONObject.optString("document", j.a.toString())));
        lVar.b = jSONObject.optInt("id");
        lVar.c = jSONObject.optInt("version");
        lVar.d = jSONObject;
        JSONArray optJSONArray = jSONObject.optJSONArray("items");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (!(optJSONObject == null || (optString = optJSONObject.optString("name", (String) null)) == null)) {
                    lVar.e.put(optString, new c(optJSONObject));
                }
            }
        }
        return lVar;
    }
}
