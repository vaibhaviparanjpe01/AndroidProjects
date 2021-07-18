package com.flurry.sdk;

public enum ca {
    DeviceId(0, true),
    AndroidAdvertisingId(13, true),
    AndroidInstallationId(14, false);
    
    public final int d;
    public final boolean e;

    private ca(int i, boolean z) {
        this.d = i;
        this.e = z;
    }
}
