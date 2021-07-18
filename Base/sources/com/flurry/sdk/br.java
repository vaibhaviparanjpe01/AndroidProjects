package com.flurry.sdk;

public final class br {
    private static br a;

    private br() {
    }

    public static synchronized br a() {
        br brVar;
        synchronized (br.class) {
            if (a == null) {
                a = new br();
            }
            brVar = a;
        }
        return brVar;
    }
}
