package com.newandromo.dev849565.app936843;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.graphics.Palette;
import android.util.Log;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SizeReadyCallback;

public class LoggingPaletteTarget extends PaletteTarget {
    protected boolean enableToString;
    private final int level;
    private final String tag;

    public LoggingPaletteTarget(PaletteListener paletteListener) {
        this(paletteListener, "LoggingPaletteTarget", 2);
    }

    public LoggingPaletteTarget(PaletteListener paletteListener, int i, int i2) {
        this(paletteListener, i, i2, "LoggingPaletteTarget", 2);
    }

    public LoggingPaletteTarget(PaletteListener paletteListener, @NonNull String str, int i) {
        super(paletteListener);
        this.enableToString = false;
        this.tag = str;
        this.level = i;
        log("PaletteTarget", paletteListener);
    }

    public LoggingPaletteTarget(PaletteListener paletteListener, int i, int i2, @NonNull String str, int i3) {
        super(paletteListener, i, i2);
        this.enableToString = false;
        this.tag = str;
        this.level = i3;
        log("PaletteTarget", paletteListener, Integer.valueOf(i), Integer.valueOf(i2));
    }

    public LoggingPaletteTarget addToString() {
        this.enableToString = true;
        return this;
    }

    /* access modifiers changed from: private */
    public void log(@NonNull String str, Object... objArr) {
        log(str, (Throwable) null, objArr);
    }

    private void log(@NonNull String str, Throwable th, Object... objArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(super.getClass().getSimpleName());
        sb.append('@');
        sb.append(Integer.toHexString(super.hashCode()));
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
            sb.append(super.toString());
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
                LoggingPaletteTarget.this.log("onSizeReady", sizeReadyCallback, Integer.valueOf(i), Integer.valueOf(i2));
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

    public void onResourceReady(Palette palette, GlideAnimation<? super Palette> glideAnimation) {
        log("onResourceReady", palette, glideAnimation);
        super.onResourceReady(palette, glideAnimation);
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

    public void setListener(PaletteListener paletteListener) {
        log("setListener", paletteListener);
        super.setListener(paletteListener);
    }
}
