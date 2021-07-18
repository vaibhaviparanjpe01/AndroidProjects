package com.flurry.sdk;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class be implements dw<ae> {
    private static final String a = "be";

    public final /* synthetic */ void a(OutputStream outputStream, Object obj) throws IOException {
        JSONObject jSONObject;
        ae aeVar = (ae) obj;
        if (outputStream != null && aeVar != null) {
            AnonymousClass1 r0 = new DataOutputStream(outputStream) {
                public final void close() {
                }
            };
            JSONObject jSONObject2 = new JSONObject();
            try {
                a(jSONObject2, "project_key", aeVar.a);
                a(jSONObject2, "bundle_id", aeVar.b);
                a(jSONObject2, "app_version", aeVar.c);
                jSONObject2.put("sdk_version", aeVar.d);
                jSONObject2.put("platform", aeVar.e);
                a(jSONObject2, "platform_version", aeVar.f);
                jSONObject2.put("limit_ad_tracking", aeVar.g);
                JSONObject jSONObject3 = null;
                if (aeVar.h == null || aeVar.h.a == null) {
                    jSONObject = null;
                } else {
                    jSONObject = new JSONObject();
                    JSONObject jSONObject4 = new JSONObject();
                    a(jSONObject4, "model", aeVar.h.a.a);
                    a(jSONObject4, "brand", aeVar.h.a.b);
                    a(jSONObject4, "id", aeVar.h.a.c);
                    a(jSONObject4, "device", aeVar.h.a.d);
                    a(jSONObject4, "product", aeVar.h.a.e);
                    a(jSONObject4, "version_release", aeVar.h.a.f);
                    jSONObject.put("com.flurry.proton.generated.avro.v2.AndroidTags", jSONObject4);
                }
                if (jSONObject != null) {
                    jSONObject2.put("device_tags", jSONObject);
                } else {
                    jSONObject2.put("device_tags", JSONObject.NULL);
                }
                JSONArray jSONArray = new JSONArray();
                for (ag next : aeVar.i) {
                    JSONObject jSONObject5 = new JSONObject();
                    jSONObject5.put("type", next.a);
                    a(jSONObject5, "id", next.b);
                    jSONArray.put(jSONObject5);
                }
                jSONObject2.put("device_ids", jSONArray);
                if (!(aeVar.j == null || aeVar.j.a == null)) {
                    jSONObject3 = new JSONObject();
                    JSONObject jSONObject6 = new JSONObject();
                    jSONObject6.putOpt("latitude", Double.valueOf(aeVar.j.a.a));
                    jSONObject6.putOpt("longitude", Double.valueOf(aeVar.j.a.b));
                    jSONObject6.putOpt("accuracy", Float.valueOf(aeVar.j.a.c));
                    jSONObject3.put("com.flurry.proton.generated.avro.v2.Geolocation", jSONObject6);
                }
                if (jSONObject3 != null) {
                    jSONObject2.put("geo", jSONObject3);
                } else {
                    jSONObject2.put("geo", JSONObject.NULL);
                }
                JSONObject jSONObject7 = new JSONObject();
                if (aeVar.k != null) {
                    a(jSONObject7, "string", aeVar.k.a);
                    jSONObject2.put("publisher_user_id", jSONObject7);
                } else {
                    jSONObject2.put("publisher_user_id", JSONObject.NULL);
                }
                db.a(5, a, "Proton Request String: " + jSONObject2.toString());
                r0.write(jSONObject2.toString().getBytes());
                r0.flush();
                r0.close();
            } catch (JSONException e) {
                throw new IOException("Invalid Json", e);
            } catch (Throwable th) {
                r0.close();
                throw th;
            }
        }
    }

    private static void a(JSONObject jSONObject, String str, String str2) throws IOException, JSONException {
        if (str2 != null) {
            jSONObject.put(str, str2);
        } else {
            jSONObject.put(str, JSONObject.NULL);
        }
    }

    public final /* synthetic */ Object a(InputStream inputStream) throws IOException {
        throw new IOException("Deserialize not supported for request");
    }
}
