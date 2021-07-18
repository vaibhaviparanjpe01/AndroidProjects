package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class OffsetItemDecoration extends RecyclerView.ItemDecoration {
    private int offsetPx;

    public OffsetItemDecoration(int i) {
        this.offsetPx = i / 2;
    }

    public OffsetItemDecoration(Context context, float f) {
        this.offsetPx = DimenUtils.dp(context, f / 2.0f);
    }

    public OffsetItemDecoration(Context context, @DimenRes int i) {
        this.offsetPx = context.getResources().getDimensionPixelSize(i) / 2;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        if (recyclerView.getPaddingLeft() != this.offsetPx) {
            recyclerView.setPadding(this.offsetPx, this.offsetPx, this.offsetPx, this.offsetPx);
            recyclerView.setClipToPadding(false);
        }
        rect.top = this.offsetPx;
        rect.bottom = this.offsetPx;
        rect.left = this.offsetPx;
        rect.right = this.offsetPx;
    }
}
