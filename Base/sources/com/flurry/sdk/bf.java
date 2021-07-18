package com.flurry.sdk;

import android.text.TextUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class bf implements dw<af> {
    private static final String a = "bf";

    public final /* synthetic */ Object a(InputStream inputStream) throws IOException {
        return b(inputStream);
    }

    private static af b(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        String str = new String(em.a(inputStream));
        db.a(5, a, "Proton response string: ".concat(String.valueOf(str)));
        af afVar = new af();
        try {
            JSONObject jSONObject = new JSONObject(str);
            afVar.a = jSONObject.optLong("issued_at", -1);
            afVar.b = jSONObject.optLong("refresh_ttl", 3600);
            afVar.c = jSONObject.optLong("expiration_ttl", 86400);
            JSONObject optJSONObject = jSONObject.optJSONObject("global_settings");
            afVar.d = new am();
            if (optJSONObject != null) {
                afVar.d.a = a(optJSONObject.optString("log_level"));
            }
            JSONObject optJSONObject2 = jSONObject.optJSONObject("pulse");
            ad adVar = new ad();
            if (optJSONObject2 != null) {
                a(adVar, optJSONObject2.optJSONArray("callbacks"));
                adVar.b = optJSONObject2.optInt("max_callback_retries", 3);
                adVar.c = optJSONObject2.optInt("max_callback_attempts_per_report", 15);
                adVar.d = optJSONObject2.optInt("max_report_delay_seconds", 600);
                adVar.e = optJSONObject2.optString("agent_report_url", "");
            }
            afVar.e = adVar;
            JSONObject optJSONObject3 = jSONObject.optJSONObject("analytics");
            afVar.f = new ap();
            if (optJSONObject3 != null) {
                afVar.f.b = optJSONObject3.optBoolean("analytics_enabled", true);
                afVar.f.a = optJSONObject3.optInt("max_session_properties", 10);
            }
            return afVar;
        } catch (JSONException e) {
            throw new IOException("Exception while deserialize: ", e);
        }
    }

    private static an a(String str) {
        an anVar = an.OFF;
        try {
            return !TextUtils.isEmpty(str) ? (an) Enum.valueOf(an.class, str) : anVar;
        } catch (Exception unused) {
            return anVar;
        }
    }

    private static void a(ad adVar, JSONArray jSONArray) throws JSONException {
        JSONObject optJSONObject;
        if (jSONArray != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject2 = jSONArray.optJSONObject(i);
                if (optJSONObject2 != null) {
                    ac acVar = new ac();
                    acVar.b = optJSONObject2.optString("partner", "");
                    a(acVar, optJSONObject2.optJSONArray("events"));
                    acVar.d = b(optJSONObject2.optString("method"));
                    acVar.e = optJSONObject2.optString("uri_template", "");
                    JSONObject optJSONObject3 = optJSONObject2.optJSONObject("body_template");
                    if (optJSONObject3 != null) {
                        String optString = optJSONObject3.optString("string", "null");
                        if (!optString.equals("null")) {
                            acVar.f = optString;
                        }
                    }
                    acVar.g = optJSONObject2.optInt("max_redirects", 5);
                    acVar.h = optJSONObject2.optInt("connect_timeout", 20);
                    acVar.i = optJSONObject2.optInt("request_timeout", 20);
                    acVar.a = optJSONObject2.optLong("callback_id", -1);
                    JSONObject optJSONObject4 = optJSONObject2.optJSONObject("headers");
                    if (!(optJSONObject4 == null || (optJSONObject = optJSONObject4.optJSONObject("map")) == null)) {
                        acVar.j = en.a(optJSONObject);
                    }
                    arrayList.add(acVar);
                }
            }
            adVar.a = arrayList;
        }
    }

    private static void a(ac acVar, JSONArray jSONArray) {
        String[] strArr;
        if (jSONArray != null) {
            ArrayList arrayList = null;
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    if (optJSONObject.has("string")) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        ai aiVar = new ai();
                        aiVar.a = optJSONObject.optString("string", "");
                        arrayList.add(aiVar);
                    } else if (optJSONObject.has("com.flurry.proton.generated.avro.v2.EventParameterCallbackTrigger")) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        JSONObject optJSONObject2 = optJSONObject.optJSONObject("com.flurry.proton.generated.avro.v2.EventParameterCallbackTrigger");
                        if (optJSONObject2 != null) {
                            aj ajVar = new aj();
                            ajVar.a = optJSONObject2.optString("event_name", "");
                            ajVar.c = optJSONObject2.optString("event_parameter_name", "");
                            JSONArray optJSONArray = optJSONObject2.optJSONArray("event_parameter_values");
                            if (optJSONArray != null) {
                                strArr = new String[optJSONArray.length()];
                                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                                    strArr[i2] = optJSONArray.optString(i2, "");
                                }
                            } else {
                                strArr = new String[0];
                            }
                            ajVar.d = strArr;
                            arrayList.add(ajVar);
                        }
                    }
                }
            }
            acVar.c = arrayList;
        }
    }

    private static bc b(String str) {
        bc bcVar = bc.GET;
        try {
            return !TextUtils.isEmpty(str) ? (bc) Enum.valueOf(bc.class, str) : bcVar;
        } catch (Exception unused) {
            return bcVar;
        }
    }

    public final /* synthetic */ void a(OutputStream outputStream, Object obj) throws IOException {
        throw new IOException("Serialize not supported for response");
    }
}
