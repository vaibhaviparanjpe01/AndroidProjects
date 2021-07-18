package com.newandromo.dev849565.app936843;

import android.graphics.drawable.Drawable;
import android.support.v7.graphics.Palette;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BaseTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.util.Util;

public class PaletteTarget extends BaseTarget<Palette> {
    private final int height;
    private PaletteListener listener;
    private final int width;

    public PaletteTarget(PaletteListener paletteListener) {
        this(paletteListener, Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public PaletteTarget(PaletteListener paletteListener, int i, int i2) {
        this.listener = paletteListener;
        this.width = i;
        this.height = i2;
    }

    public void setListener(PaletteListener paletteListener) {
        this.listener = paletteListener;
    }

    public void getSize(SizeReadyCallback sizeReadyCallback) {
        if (Util.isValidDimensions(this.width, this.height)) {
            sizeReadyCallback.onSizeReady(this.width, this.height);
            return;
        }
        throw new IllegalArgumentException("Width and height must both be > 0 or Target#SIZE_ORIGINAL, but given width: " + this.width + " and height: " + this.height + ", either provide dimensions in the constructor or call override()");
    }

    public void onResourceReady(Palette palette, GlideAnimation<? super Palette> glideAnimation) {
        if (this.listener != null) {
            this.listener.onPaletteReady(palette);
        }
    }

    public void onLoadCleared(Drawable drawable) {
        super.onLoadCleared(drawable);
    }

    public void onLoadFailed(Exception exc, Drawable drawable) {
        super.onLoadFailed(exc, drawable);
    }
}
