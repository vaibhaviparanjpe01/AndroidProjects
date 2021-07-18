package com.newandromo.dev849565.app936843;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;

public class StatelessWrappingTarget<Z> implements Target<Z> {
    private final RequestHolder requestHolder;
    @NonNull
    protected final Target<? super Z> target;

    public StatelessWrappingTarget(@NonNull RequestHolder requestHolder2, @NonNull Target<? super Z> target2) {
        this.requestHolder = requestHolder2;
        this.target = target2;
    }

    @NonNull
    public Target<? super Z> getWrappedTarget() {
        return this.target;
    }

    public void getSize(SizeReadyCallback sizeReadyCallback) {
        this.target.getSize(sizeReadyCallback);
    }

    public void onLoadStarted(Drawable drawable) {
        this.target.onLoadStarted(drawable);
    }

    public void onLoadFailed(Exception exc, Drawable drawable) {
        this.target.onLoadFailed(exc, drawable);
    }

    public void onResourceReady(Z z, GlideAnimation<? super Z> glideAnimation) {
        this.target.onResourceReady(z, glideAnimation);
    }

    public void onLoadCleared(Drawable drawable) {
        this.target.onLoadCleared(drawable);
    }

    public Request getRequest() {
        return this.requestHolder.getRequest();
    }

    public void setRequest(Request request) {
        this.requestHolder.setRequest(request);
    }

    public void onStart() {
        this.target.onStart();
    }

    public void onStop() {
        this.target.onStop();
    }

    public void onDestroy() {
        this.target.onDestroy();
    }
}
