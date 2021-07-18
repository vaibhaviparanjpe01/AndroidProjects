package com.newandromo.dev849565.app936843;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.SquaringDrawable;

public class ColorFilterTarget extends ImageViewTarget<GlideDrawable> {
    private static final float SQUARE_RATIO_MARGIN = 0.05f;
    private static final String TAG = "ColorFilterTarget";
    private ColorMatrix matrix;
    private int maxLoopCount;
    private GlideDrawable resource;

    public ColorFilterTarget(ImageView imageView, ColorMatrix colorMatrix) {
        this(imageView, colorMatrix, -1);
    }

    public ColorFilterTarget(ImageView imageView, ColorMatrix colorMatrix, int i) {
        super(imageView);
        this.maxLoopCount = i;
        this.matrix = colorMatrix;
    }

    public void setMatrix(ColorMatrix colorMatrix) {
        this.matrix = colorMatrix;
        if (this.resource != null && colorMatrix != null) {
            ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(colorMatrix);
            this.resource.mutate();
            this.resource.setColorFilter(colorMatrixColorFilter);
            this.resource.setLoopCount(this.maxLoopCount);
            setResource(this.resource);
        }
    }

    public void onLoadStarted(Drawable drawable) {
        super.onLoadStarted(drawable);
    }

    public void onLoadFailed(Exception exc, Drawable drawable) {
        super.onLoadFailed(exc, drawable);
    }

    public void onLoadCleared(Drawable drawable) {
        super.onLoadCleared(drawable);
    }

    public void onResourceReady(GlideDrawable glideDrawable, GlideAnimation<? super GlideDrawable> glideAnimation) {
        if (!glideDrawable.isAnimated()) {
            float width = ((float) ((ImageView) this.view).getWidth()) / ((float) ((ImageView) this.view).getHeight());
            float intrinsicWidth = ((float) glideDrawable.getIntrinsicWidth()) / ((float) glideDrawable.getIntrinsicHeight());
            if (Math.abs(width - 1.0f) <= SQUARE_RATIO_MARGIN && Math.abs(intrinsicWidth - 1.0f) <= SQUARE_RATIO_MARGIN) {
                glideDrawable = new SquaringDrawable(glideDrawable, ((ImageView) this.view).getWidth());
            }
        }
        if (!(glideDrawable == null || this.matrix == null)) {
            ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(this.matrix);
            glideDrawable.mutate();
            glideDrawable.setColorFilter(colorMatrixColorFilter);
        }
        super.onResourceReady(glideDrawable, glideAnimation);
        this.resource = glideDrawable;
        glideDrawable.setLoopCount(this.maxLoopCount);
        glideDrawable.start();
    }

    /* access modifiers changed from: protected */
    public void setResource(GlideDrawable glideDrawable) {
        ((ImageView) this.view).setImageDrawable(glideDrawable);
    }

    public void setDrawable(Drawable drawable) {
        if (!(drawable == null || this.matrix == null)) {
            ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(this.matrix);
            drawable.mutate();
            drawable.setColorFilter(colorMatrixColorFilter);
        }
        ((ImageView) this.view).setImageDrawable(drawable);
    }

    public void onStart() {
        if (this.resource != null) {
            this.resource.start();
        }
    }

    public void onStop() {
        if (this.resource != null) {
            this.resource.stop();
        }
    }
}
