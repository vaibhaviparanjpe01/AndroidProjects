package com.flurry.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.common.GoogleApiAvailability;
import java.io.File;
import java.util.regex.Pattern;

public class ep {
    public static final String a = "ep";

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split("\\.");
        if (split.length != 5) {
            return null;
        }
        return split[3];
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split("\\.");
        if (split.length != 5) {
            return null;
        }
        return split[4];
    }

    public static String a(File file, String str) {
        String str2 = str + ".fcb";
        String[] a2 = el.a(file, Pattern.compile(".*" + Pattern.quote(str2) + ".*"));
        if (a2.length > 0) {
            return a2[0];
        }
        return null;
    }

    public enum a {
        UNKNOWN((byte) -1),
        ACTIVE((byte) 0),
        INACTIVE((byte) 1),
        BACKGROUND((byte) 2);
        
        public byte e;

        private a(byte b) {
            this.e = b;
        }
    }

    public enum b {
        UNAVAILABLE(-2),
        UNKNOWN(-1),
        SUCCESS(0),
        SERVICE_MISSING(1),
        SERVICE_UPDATING(2),
        SERVICE_VERSION_UPDATE_REQUIRED(3),
        SERVICE_DISABLED(4),
        SERVICE_INVALID(5);
        
        public int i;

        private b(int i2) {
            this.i = i2;
        }
    }

    public static b a(Context context) {
        try {
            int isGooglePlayServicesAvailable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context);
            if (isGooglePlayServicesAvailable == 9) {
                return b.SERVICE_INVALID;
            }
            if (isGooglePlayServicesAvailable == 18) {
                return b.SERVICE_UPDATING;
            }
            switch (isGooglePlayServicesAvailable) {
                case 0:
                    return b.SUCCESS;
                case 1:
                    return b.SERVICE_MISSING;
                case 2:
                    return b.SERVICE_VERSION_UPDATE_REQUIRED;
                case 3:
                    return b.SERVICE_DISABLED;
                default:
                    return b.UNAVAILABLE;
            }
        } catch (Exception | NoClassDefFoundError unused) {
            db.a(a, "Error retrieving Google Play Services Availability. This probably means google play services is unavailable.");
            return b.UNAVAILABLE;
        }
    }
}
