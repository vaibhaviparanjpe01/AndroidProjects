package com.newandromo.dev849565.app936843;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.SquaringDrawable;

public class TintTarget extends ImageViewTarget<GlideDrawable> {
    public static final int CLEAR_ALL = 131204;
    public static final int CLEAR_NONE = 0;
    public static final int CLEAR_NULL_ERROR = 131072;
    public static final int CLEAR_NULL_PLACEHOLDER = 128;
    public static final int CLEAR_NULL_RESULT = 4;
    private static final float SQUARE_RATIO_MARGIN = 0.05f;
    private static final String TAG = "TintTarget";
    private final ColorStateList errorColor;
    private final int flags;
    private int maxLoopCount;
    private final ColorStateList placeholderColor;
    private GlideDrawable resource;
    private final ColorStateList resultColor;

    public TintTarget(ImageView imageView, ColorStateList colorStateList, ColorStateList colorStateList2, ColorStateList colorStateList3) {
        this(imageView, colorStateList, colorStateList2, colorStateList3, CLEAR_ALL, -1);
    }

    public TintTarget(ImageView imageView, ColorStateList colorStateList, ColorStateList colorStateList2, ColorStateList colorStateList3, int i) {
        this(imageView, colorStateList, colorStateList2, colorStateList3, i, -1);
    }

    public TintTarget(ImageView imageView, ColorStateList colorStateList, ColorStateList colorStateList2, ColorStateList colorStateList3, int i, int i2) {
        super(imageView);
        this.maxLoopCount = i2;
        this.resultColor = colorStateList;
        this.placeholderColor = colorStateList2;
        this.errorColor = colorStateList3;
        this.flags = i;
    }

    public void onLoadStarted(Drawable drawable) {
        if (drawable != null) {
            if (this.view instanceof TintableImageView) {
                if (this.placeholderColor != null || isSet(128)) {
                    ((TintableImageView) this.view).setColorFilter(this.placeholderColor, PorterDuff.Mode.SRC_IN);
                }
            } else if (this.placeholderColor != null) {
                drawable.mutate();
                drawable.setColorFilter(this.placeholderColor.getDefaultColor(), PorterDuff.Mode.SRC_IN);
            } else {
                drawable.clearColorFilter();
            }
        }
        super.onLoadStarted(drawable);
    }

    public void onLoadFailed(Exception exc, Drawable drawable) {
        if (drawable != null) {
            if (this.view instanceof TintableImageView) {
                if (this.placeholderColor != null || isSet(131072)) {
                    ((TintableImageView) this.view).setColorFilter(this.errorColor, PorterDuff.Mode.SRC_IN);
                }
            } else if (this.errorColor != null) {
                drawable.mutate();
                drawable.setColorFilter(this.errorColor.getDefaultColor(), PorterDuff.Mode.SRC_IN);
            } else {
                drawable.clearColorFilter();
            }
        }
        super.onLoadFailed(exc, drawable);
    }

    public void onLoadCleared(Drawable drawable) {
        if (drawable != null) {
            if (this.view instanceof TintableImageView) {
                if (this.placeholderColor != null || isSet(128)) {
                    ((TintableImageView) this.view).setColorFilter(this.placeholderColor, PorterDuff.Mode.SRC_IN);
                }
            } else if (this.placeholderColor != null) {
                drawable.mutate();
                drawable.setColorFilter(this.placeholderColor.getDefaultColor(), PorterDuff.Mode.SRC_IN);
            } else {
                drawable.clearColorFilter();
            }
        }
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
        if (glideDrawable != null) {
            if (this.view instanceof TintableImageView) {
                if (this.resultColor != null || isSet(4)) {
                    ((TintableImageView) this.view).setColorFilter(this.resultColor, PorterDuff.Mode.SRC_IN);
                }
            } else if (this.resultColor != null) {
                glideDrawable.mutate();
                glideDrawable.setColorFilter(this.resultColor.getDefaultColor(), PorterDuff.Mode.SRC_IN);
            } else {
                glideDrawable.clearColorFilter();
            }
        }
        super.onResourceReady(glideDrawable, glideAnimation);
        this.resource = glideDrawable;
        glideDrawable.setLoopCount(this.maxLoopCount);
        glideDrawable.start();
    }

    private boolean isSet(int i) {
        return (i & this.flags) != 0;
    }

    /* access modifiers changed from: protected */
    public void setResource(GlideDrawable glideDrawable) {
        ((ImageView) this.view).setImageDrawable(glideDrawable);
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
