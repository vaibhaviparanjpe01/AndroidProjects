package com.flurry.sdk;

public enum bc {
    GET("GET", 0),
    PUT("PUT", 1),
    POST("POST", 2);
    
    String d;
    int e;

    private bc(String str, int i) {
        this.d = str;
        this.e = i;
    }

    public static bc a(int i) {
        switch (i) {
            case 0:
                return GET;
            case 1:
                return PUT;
            case 2:
                return POST;
            default:
                return null;
        }
    }
}
