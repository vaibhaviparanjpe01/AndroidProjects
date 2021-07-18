package com.flurry.sdk;

import android.support.annotation.Nullable;
import com.flurry.android.Consent;
import java.util.Map;

public abstract class ez extends Consent {
    private boolean a;

    public ez(boolean z, boolean z2, @Nullable Map<String, String> map) throws IllegalArgumentException {
        if (z || !z2 || (map != null && !map.isEmpty())) {
            this.a = z;
            this.isGdprScope = z2;
            this.consentStrings = map;
            return;
        }
        throw new IllegalArgumentException("Consent strings can not be null or empty when in scope of GDPR");
    }

    public boolean isLICNEnabled() {
        return this.a;
    }

    public boolean equals(Object obj) {
        return super.equals(obj) && this.a == ((ez) obj).isLICNEnabled();
    }

    public int hashCode() {
        return (super.hashCode() * 31) + (this.a ? 1 : 0);
    }
}
