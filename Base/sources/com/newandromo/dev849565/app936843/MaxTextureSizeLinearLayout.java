package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class MaxTextureSizeLinearLayout extends LinearLayout {
    private static final String TAG = "MaxTextureSizeLinearLayout";

    public MaxTextureSizeLinearLayout(Context context) {
        super(context);
        setWillNotDraw(false);
    }

    public MaxTextureSizeLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setWillNotDraw(false);
    }

    public MaxTextureSizeLinearLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setWillNotDraw(false);
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (Build.VERSION.SDK_INT >= 14 && canvas.isHardwareAccelerated()) {
            ImageUtils.setMaxBitmapWidth(canvas.getMaximumBitmapWidth());
            ImageUtils.setMaxBitmapHeight(canvas.getMaximumBitmapHeight());
        }
        setWillNotDraw(true);
    }
}
