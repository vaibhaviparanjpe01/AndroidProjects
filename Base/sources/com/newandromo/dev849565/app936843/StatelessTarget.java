package com.newandromo.dev849565.app936843;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.Target;

public abstract class StatelessTarget<Z> implements Target<Z> {
    private RequestHolder requestHolder;

    public void onDestroy() {
    }

    public void onLoadCleared(Drawable drawable) {
    }

    public void onLoadFailed(Exception exc, Drawable drawable) {
    }

    public void onLoadStarted(Drawable drawable) {
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public StatelessTarget(@NonNull RequestHolder requestHolder2) {
        this.requestHolder = requestHolder2;
    }

    public void setRequest(Request request) {
        this.requestHolder.setRequest(request);
    }

    public Request getRequest() {
        return this.requestHolder.getRequest();
    }
}
