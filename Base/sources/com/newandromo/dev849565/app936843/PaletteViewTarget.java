package com.newandromo.dev849565.app936843;

import android.graphics.drawable.Drawable;
import android.support.v7.graphics.Palette;
import android.view.View;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BaseTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.util.Util;

public class PaletteViewTarget extends BaseTarget<Palette> {
    private final int height;
    private int index;
    private PaletteListener listener;
    private final View view;
    private final int width;

    public PaletteViewTarget(View view2, PaletteListener paletteListener, int i) {
        this(view2, paletteListener, i, Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public PaletteViewTarget(View view2, PaletteListener paletteListener, int i, int i2, int i3) {
        this.view = view2;
        this.listener = paletteListener;
        this.index = i;
        this.width = i2;
        this.height = i3;
    }

    public void setListener(PaletteListener paletteListener) {
        this.listener = paletteListener;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public void setRequest(Request request) {
        this.view.setTag(R.id.glide_palette_request, request);
    }

    public Request getRequest() {
        Object tag = this.view.getTag(R.id.glide_palette_request);
        if (tag == null) {
            return null;
        }
        if (tag instanceof Request) {
            return (Request) tag;
        }
        throw new IllegalArgumentException("Unable to get glide request from view tag.");
    }

    public static Request getRequest(View view2) {
        Object tag = view2.getTag(R.id.glide_palette_request);
        if (tag == null) {
            return null;
        }
        if (tag instanceof Request) {
            return (Request) tag;
        }
        throw new IllegalArgumentException("Unable to get glide request from view tag.");
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
            this.listener.onPaletteReady(palette, this.view, this.index);
        }
    }

    public void onLoadCleared(Drawable drawable) {
        super.onLoadCleared(drawable);
    }

    public void onLoadFailed(Exception exc, Drawable drawable) {
        super.onLoadFailed(exc, drawable);
    }
}
