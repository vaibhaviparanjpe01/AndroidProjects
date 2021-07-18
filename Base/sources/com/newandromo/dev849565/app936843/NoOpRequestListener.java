package com.newandromo.dev849565.app936843;

import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public final class NoOpRequestListener<A, B> implements RequestListener<A, B> {
    private static final RequestListener INSTANCE = new NoOpRequestListener();

    public boolean onException(Exception exc, A a, Target<B> target, boolean z) {
        return false;
    }

    public boolean onResourceReady(B b, A a, Target<B> target, boolean z, boolean z2) {
        return false;
    }

    public static <A, B> RequestListener<A, B> get() {
        return INSTANCE;
    }

    private NoOpRequestListener() {
    }
}
