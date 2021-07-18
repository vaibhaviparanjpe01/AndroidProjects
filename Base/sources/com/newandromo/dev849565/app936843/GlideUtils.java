package com.newandromo.dev849565.app936843;

import android.content.Context;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.signature.ApplicationVersionSignature;
import com.bumptech.glide.signature.EmptySignature;

public final class GlideUtils {
    public static Key getSignatureForImageLoadFromUrl() {
        return EmptySignature.obtain();
    }

    public static Key getSignatureForImageLoadFromResource(Context context) {
        return ApplicationVersionSignature.obtain(context);
    }
}
