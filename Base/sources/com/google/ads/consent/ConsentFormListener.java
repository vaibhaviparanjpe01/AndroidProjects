package com.google.ads.consent;

public abstract class ConsentFormListener {
    public void onConsentFormClosed(ConsentStatus consentStatus, Boolean bool) {
    }

    public void onConsentFormError(String str) {
    }

    public void onConsentFormLoaded() {
    }

    public void onConsentFormOpened() {
    }
}
