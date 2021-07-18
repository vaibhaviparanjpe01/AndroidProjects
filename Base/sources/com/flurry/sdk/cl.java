package com.flurry.sdk;

import java.util.Locale;

public class cl {
    private static final String a = "cl";

    public static String a() {
        return String.format(Locale.getDefault(), "Flurry_Android_%d_%d.%d.%d%s%s", new Object[]{Integer.valueOf(b()), Integer.valueOf(((Integer) eg.a().a("ReleaseMajorVersion")).intValue()), Integer.valueOf(((Integer) eg.a().a("ReleaseMinorVersion")).intValue()), Integer.valueOf(((Integer) eg.a().a("ReleasePatchVersion")).intValue()), c().length() > 0 ? "." : "", c()});
    }

    private static String c() {
        return (String) eg.a().a("ReleaseBetaVersion");
    }

    public static int b() {
        int intValue = ((Integer) eg.a().a("AgentVersion")).intValue();
        db.a(4, a, "getAgentVersion() = ".concat(String.valueOf(intValue)));
        return intValue;
    }
}
