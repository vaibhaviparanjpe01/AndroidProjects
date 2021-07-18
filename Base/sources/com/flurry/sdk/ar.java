package com.flurry.sdk;

import android.location.Location;
import java.util.Map;

public class ar extends dh {
    private static final String a = "ar";

    public final String a(String str, Map<String, String> map) {
        String a2 = a(str);
        while (a2 != null) {
            if (a("timestamp_epoch_millis", a2)) {
                String valueOf = String.valueOf(System.currentTimeMillis());
                db.a(3, a, "Replacing param timestamp_epoch_millis with: ".concat(String.valueOf(valueOf)));
                str = str.replace(a2, em.c(valueOf));
            } else if (a("session_duration_millis", a2)) {
                bq.a();
                String l = Long.toString(bq.f());
                db.a(3, a, "Replacing param session_duration_millis with: ".concat(String.valueOf(l)));
                str = str.replace(a2, em.c(l));
            } else if (a("fg_timespent_millis", a2)) {
                bq.a();
                String l2 = Long.toString(bq.f());
                db.a(3, a, "Replacing param fg_timespent_millis with: ".concat(String.valueOf(l2)));
                str = str.replace(a2, em.c(l2));
            } else if (a("install_referrer", a2)) {
                String b = new y().b();
                if (b == null) {
                    b = "";
                }
                db.a(3, a, "Replacing param install_referrer with: ".concat(String.valueOf(b)));
                str = str.replace(a2, em.c(b));
            } else if (a("geo_latitude", a2)) {
                Location g = bw.a().g();
                String str2 = "";
                if (g != null) {
                    int d = bw.d();
                    str2 = str2 + em.a(g.getLatitude(), d);
                }
                db.a(3, a, "Replacing param geo_latitude with: ".concat(String.valueOf(str2)));
                str = str.replace(a2, em.c(str2));
            } else if (a("geo_longitude", a2)) {
                Location g2 = bw.a().g();
                String str3 = "";
                if (g2 != null) {
                    int d2 = bw.d();
                    str3 = str3 + em.a(g2.getLongitude(), d2);
                }
                db.a(3, a, "Replacing param geo_longitude with: ".concat(String.valueOf(str3)));
                str = str.replace(a2, em.c(str3));
            } else if (a("publisher_user_id", a2)) {
                String str4 = (String) eg.a().a("UserId");
                db.a(3, a, "Replacing param publisher_user_id with: ".concat(String.valueOf(str4)));
                str = str.replace(a2, em.c(str4));
            } else if (a("event_name", a2)) {
                if (map.containsKey("event_name")) {
                    db.a(3, a, "Replacing param event_name with: " + map.get("event_name"));
                    str = str.replace(a2, em.c(map.get("event_name")));
                } else {
                    db.a(3, a, "Replacing param event_name with empty string");
                    str = str.replace(a2, "");
                }
            } else if (!a("event_time_millis", a2)) {
                db.a(3, a, "Unknown param: ".concat(String.valueOf(a2)));
                str = str.replace(a2, "");
            } else if (map.containsKey("event_time_millis")) {
                db.a(3, a, "Replacing param event_time_millis with: " + map.get("event_time_millis"));
                str = str.replace(a2, em.c(map.get("event_time_millis")));
            } else {
                db.a(3, a, "Replacing param event_time_millis with empty string");
                str = str.replace(a2, "");
            }
            a2 = a(str);
        }
        return str;
    }
}
