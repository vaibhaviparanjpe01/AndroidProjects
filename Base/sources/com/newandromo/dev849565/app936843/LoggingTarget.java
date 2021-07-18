package com.newandromo.dev849565.app936843;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Log;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;

public class LoggingTarget<Z> extends WrappingTarget<Z> {
    protected boolean enableToString;
    private final int level;
    private final String tag;

    public LoggingTarget(@NonNull Target<? super Z> target) {
        this("LoggingTarget", 2, target);
    }

    public LoggingTarget(@NonNull String str, int i, @NonNull Target<? super Z> target) {
        super(target);
        this.enableToString = false;
        this.tag = str;
        this.level = i;
    }

    public LoggingTarget<Z> addToString() {
        this.enableToString = true;
        return this;
    }

    /* access modifiers changed from: private */
    public void log(@NonNull String str, Object... objArr) {
        log(str, (Throwable) null, objArr);
    }

    private void log(@NonNull String str, Throwable th, Object... objArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.target.getClass().getSimpleName());
        sb.append('@');
        sb.append(Integer.toHexString(this.target.hashCode()));
        sb.append('.');
        sb.append(str);
        sb.append('(');
        boolean z = true;
        for (Object obj : objArr) {
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append(obj);
        }
        sb.append(')');
        if (this.enableToString) {
            sb.append(10);
            sb.append(this.target);
        }
        if (th != null) {
            sb.append(10);
            sb.append(Log.getStackTraceString(th));
        }
        Log.println(this.level, this.tag, sb.toString());
    }

    public void getSize(final SizeReadyCallback sizeReadyCallback) {
        log("getSize", sizeReadyCallback);
        super.getSize(new SizeReadyCallback() {
            public void onSizeReady(int i, int i2) {
                LoggingTarget.this.log("onSizeReady", sizeReadyCallback, Integer.valueOf(i), Integer.valueOf(i2));
                sizeReadyCallback.onSizeReady(i, i2);
            }
        });
    }

    public void onLoadStarted(Drawable drawable) {
        log("onLoadStarted", drawable);
        super.onLoadStarted(drawable);
    }

    public void onLoadFailed(Exception exc, Drawable drawable) {
        log("onLoadFailed", exc, exc, drawable);
        super.onLoadFailed(exc, drawable);
    }

    public void onResourceReady(Z z, GlideAnimation<? super Z> glideAnimation) {
        log("onResourceReady", z, glideAnimation);
        super.onResourceReady(z, glideAnimation);
    }

    public void onLoadCleared(Drawable drawable) {
        log("onLoadCleared", drawable);
        super.onLoadCleared(drawable);
    }

    public Request getRequest() {
        log("getRequest", new Object[0]);
        return super.getRequest();
    }

    public void setRequest(Request request) {
        log("setRequest", request);
        super.setRequest(request);
    }

    public void onStart() {
        log("onStart", new Object[0]);
        super.onStart();
    }

    public void onStop() {
        log("onStop", new Object[0]);
        super.onStop();
    }

    public void onDestroy() {
        log("onDestroy", new Object[0]);
        super.onDestroy();
    }
}
