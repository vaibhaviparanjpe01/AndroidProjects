package com.flurry.android;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.flurry.sdk.a;
import com.flurry.sdk.c;
import com.flurry.sdk.ck;
import com.flurry.sdk.d;
import com.flurry.sdk.j;

public final class FlurryConfig {
    private static FlurryConfig a;
    private d b = d.a();

    public static synchronized FlurryConfig getInstance() {
        FlurryConfig flurryConfig;
        synchronized (FlurryConfig.class) {
            if (a == null) {
                if (ck.a() != null) {
                    a = new FlurryConfig();
                } else {
                    throw new IllegalStateException("Flurry SDK must be initialized before starting config");
                }
            }
            flurryConfig = a;
        }
        return flurryConfig;
    }

    private FlurryConfig() {
    }

    public final void fetchConfig() {
        this.b.d();
    }

    public final boolean activateConfig() {
        return this.b.a((j) null);
    }

    public final void resetState() {
        this.b.e();
    }

    public final void registerListener(@NonNull FlurryConfigListener flurryConfigListener) {
        this.b.a(flurryConfigListener, j.a, (Handler) null);
    }

    public final void registerListener(@NonNull FlurryConfigListener flurryConfigListener, @NonNull Handler handler) {
        this.b.a(flurryConfigListener, j.a, handler);
    }

    public final void unregisterListener(@NonNull FlurryConfigListener flurryConfigListener) {
        this.b.a(flurryConfigListener);
    }

    public final String getString(@NonNull String str, @Nullable String str2) {
        return this.b.c().a(str, str2, j.a);
    }

    public final boolean getBoolean(@NonNull String str, boolean z) {
        a c = this.b.c();
        c a2 = c.b.a(str, j.a);
        if (a2 == null) {
            a2 = c.a.a(str);
        }
        return a2 != null ? Boolean.parseBoolean(a2.a()) : z;
    }

    public final int getInt(@NonNull String str, int i) {
        return this.b.c().a(str, i, j.a);
    }

    public final long getLong(@NonNull String str, long j) {
        return this.b.c().a(str, j, j.a);
    }

    public final double getDouble(@NonNull String str, double d) {
        return this.b.c().a(str, d, j.a);
    }

    public final float getFloat(@NonNull String str, float f) {
        return this.b.c().a(str, f, j.a);
    }

    public final String toString() {
        return this.b.toString();
    }
}
