package com.newandromo.dev849565.app936843;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;

public final class ClearingTarget<Z> implements Target<Z> {
    private RequestHolder requestHolder;

    public void onDestroy() {
    }

    public void onLoadCleared(Drawable drawable) {
    }

    public void onLoadFailed(Exception exc, Drawable drawable) {
    }

    public void onLoadStarted(Drawable drawable) {
    }

    public void onResourceReady(Z z, GlideAnimation<? super Z> glideAnimation) {
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public ClearingTarget(@NonNull RequestHolder requestHolder2) {
        this.requestHolder = requestHolder2;
    }

    public void setRequest(Request request) {
        this.requestHolder.setRequest(request);
    }

    public Request getRequest() {
        return this.requestHolder.getRequest();
    }

    public final void getSize(SizeReadyCallback sizeReadyCallback) {
        sizeReadyCallback.onSizeReady(Integer.MIN_VALUE, Integer.MIN_VALUE);
        throw new IllegalArgumentException("May need to properly implement getSize() in ClearingTarget. (Didn't expect this to get called!)");
    }
}
