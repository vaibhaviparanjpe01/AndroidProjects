package com.newandromo.dev849565.app936843;

import android.databinding.BindingConversion;
import android.graphics.drawable.ColorDrawable;

public final class BindingConversions {
    private static final String TAG = "BindingConversions";

    @BindingConversion
    public static ColorDrawable convertColorToDrawable(int i) {
        if (i != 0) {
            return new ColorDrawable(i);
        }
        return null;
    }
}
