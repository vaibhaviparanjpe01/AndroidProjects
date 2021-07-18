package com.flurry.sdk;

import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class c {
    private static final String b = "c";
    private static String c;
    private static String d;
    a a;
    private Object e;

    public enum a {
        String("string"),
        Locale("localizedString"),
        Tombstone("tombstone");
        
        /* access modifiers changed from: private */
        public String d;

        private a(String str) {
            this.d = str;
        }

        public final String toString() {
            return this.d;
        }
    }

    public c(JSONObject jSONObject) {
        String optString = jSONObject.optString("type");
        if (a.String.d.equals(optString)) {
            this.a = a.String;
            this.e = jSONObject.optString("value");
        } else if (a.Locale.d.equals(optString)) {
            this.a = a.Locale;
            this.e = jSONObject.optJSONObject("value");
        } else if (a.Tombstone.d.equals(optString)) {
            this.a = a.Tombstone;
        } else {
            db.b(b, "Unknown ConfigItem type: ".concat(String.valueOf(optString)));
        }
    }

    public final String a() {
        if (this.e == null) {
            return null;
        }
        if (this.a != a.Locale) {
            return (String) this.e;
        }
        if (c == null) {
            c = Locale.getDefault().toString();
            d = Locale.getDefault().getLanguage();
        }
        JSONObject jSONObject = (JSONObject) this.e;
        String optString = jSONObject.optString(c, (String) null);
        if (optString == null) {
            optString = jSONObject.optString(d, (String) null);
        }
        return optString == null ? jSONObject.optString("default") : optString;
    }

    public final JSONObject a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", str);
            jSONObject.put("type", this.a.toString());
            jSONObject.put("value", this.e);
            return jSONObject;
        } catch (JSONException e2) {
            db.a(b, "Error to create JSON object.", (Throwable) e2);
            return null;
        }
    }
}
