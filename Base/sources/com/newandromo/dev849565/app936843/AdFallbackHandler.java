package com.newandromo.dev849565.app936843;

import android.app.Activity;
import android.view.View;

public interface AdFallbackHandler {
    void replaceAdWithFallback(Activity activity, View view);

    void resetAdFallbacks();
}
