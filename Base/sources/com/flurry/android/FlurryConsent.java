package com.flurry.android;

import android.support.annotation.Nullable;
import java.util.Map;

public final class FlurryConsent extends Consent {
    public FlurryConsent(boolean z, @Nullable Map<String, String> map) throws IllegalArgumentException {
        if (!z || (map != null && !map.isEmpty())) {
            this.isGdprScope = z;
            this.consentStrings = map;
            return;
        }
        throw new IllegalArgumentException("Consent strings can not be null or empty when in scope of GDPR");
    }
}
