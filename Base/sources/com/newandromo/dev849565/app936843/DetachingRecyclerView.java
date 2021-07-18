package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class DetachingRecyclerView extends RecyclerView {
    public DetachingRecyclerView(Context context) {
        super(context);
    }

    public DetachingRecyclerView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DetachingRecyclerView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        swapAdapter((RecyclerView.Adapter) null, true);
    }
}
