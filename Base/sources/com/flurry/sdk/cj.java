package com.flurry.sdk;

import android.support.annotation.NonNull;
import com.flurry.android.Consent;
import java.util.Map;

public final class cj {
    public static boolean a(@NonNull Consent consent) {
        boolean isGdprScope = consent.isGdprScope();
        Map<String, String> consentStrings = consent.getConsentStrings();
        return consent instanceof ez ? ((ez) consent).isLICNEnabled() || !isGdprScope || (consentStrings != null && !consentStrings.isEmpty()) : !isGdprScope || (consentStrings != null && !consentStrings.isEmpty());
    }
}
