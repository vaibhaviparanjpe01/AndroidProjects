package com.flurry.android;

import java.util.Map;

public abstract class Consent {
    protected Map<String, String> consentStrings;
    protected boolean isGdprScope;

    public boolean isGdprScope() {
        return this.isGdprScope;
    }

    public Map<String, String> getConsentStrings() {
        return this.consentStrings;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Consent consent = (Consent) obj;
        return this.isGdprScope == consent.isGdprScope() && (this.consentStrings == null ? consent.getConsentStrings() == null : this.consentStrings.equals(consent.getConsentStrings()));
    }

    public int hashCode() {
        return ((this.isGdprScope ? 1 : 0) * true) + (this.consentStrings != null ? this.consentStrings.hashCode() : 0);
    }
}
