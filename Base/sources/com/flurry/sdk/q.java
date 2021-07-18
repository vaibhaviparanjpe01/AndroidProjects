package com.flurry.sdk;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class q {
    private static final String a = "q";

    public static String a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("timestamp", System.currentTimeMillis() / 1000);
            jSONObject.put("guid", str);
            JSONArray jSONArray = new JSONArray();
            jSONArray.put("APP");
            try {
                Class.forName("com.flurry.android.config.killswitch.KillSwitch");
                jSONArray.put("KILLSWITCH");
            } catch (ClassNotFoundException unused) {
            }
            jSONObject.put("documents", jSONArray);
            JSONArray jSONArray2 = new JSONArray();
            jSONArray2.put(t.a() ? "com.flurry.configkey.prod.ec.1" : "com.flurry.configkey.prod.rot.6");
            jSONArray2.put("com.flurry.configkey.prod.fs.0");
            jSONObject.put("signatureKeys", jSONArray2);
            cb a2 = cb.a();
            Context context = ck.a().a;
            d a3 = d.a();
            e eVar = a3.a;
            if (t.a(eVar.d())) {
                String str2 = null;
                if (eVar.b != null) {
                    str2 = eVar.b.getString("lastETag", (String) null);
                }
                if (str2 != null) {
                    jSONObject.put("etag", str2);
                }
            }
            jSONObject.put("apiKey", ck.a().b);
            jSONObject.put("appVersion", a2.e());
            jSONObject.put("appBuild", Integer.toString(cb.b(context)));
            jSONObject.put("sdkVersion", cl.b());
            jSONObject.put("platform", 3);
            jSONObject.put("platformVersion", cb.c());
            JSONArray jSONArray3 = new JSONArray();
            for (Map.Entry next : Collections.unmodifiableMap(bs.a().a).entrySet()) {
                JSONObject jSONObject2 = new JSONObject();
                byte[] bArr = (byte[]) next.getValue();
                jSONObject2.put("id", ((ca) next.getKey()).e ? new String(bArr) : Base64.encodeToString(bArr, 2));
                jSONObject2.put("type", ((ca) next.getKey()).d);
                jSONArray3.put(jSONObject2);
            }
            jSONObject.put("deviceIds", jSONArray3);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("brand", Build.BRAND);
            jSONObject3.put("device", Build.DEVICE);
            jSONObject3.put("id", Build.ID);
            jSONObject3.put("model", Build.MODEL);
            jSONObject3.put("product", Build.PRODUCT);
            jSONObject3.put("version_release", Build.VERSION.RELEASE);
            jSONObject.put("deviceTags", jSONObject3);
            jSONObject.put("bundleId", ej.a(context));
            bv.a();
            jSONObject.put("locale", bv.b());
            String str3 = (String) eg.a().a("UserId");
            if (!TextUtils.isEmpty(str3)) {
                jSONObject.put("publisherUserId", str3);
            }
            List<l> f = a3.f();
            if (f != null && f.size() > 0) {
                JSONArray jSONArray4 = new JSONArray();
                for (l next2 : f) {
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put("id", next2.b);
                    jSONObject4.put("version", next2.c);
                    jSONArray4.put(jSONObject4);
                }
                jSONObject.put("currentVariants", jSONArray4);
            }
        } catch (JSONException e) {
            db.a(a, "ParameterProvider error", (Throwable) e);
        }
        String jSONObject5 = jSONObject.toString();
        db.a(a, "Request Parameters: ".concat(String.valueOf(jSONObject5)));
        return jSONObject5;
    }
}
