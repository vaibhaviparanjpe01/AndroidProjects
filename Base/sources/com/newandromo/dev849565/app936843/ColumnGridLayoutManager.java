package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;

public class ColumnGridLayoutManager extends GridLayoutManager {
    private static final String TAG = "ColumnGridLayoutManager";
    @NonNull
    protected WeakReference<RecyclerView> parent = new WeakReference<>((Object) null);
    protected int requestedSpanCount;

    public ColumnGridLayoutManager(@NonNull Context context, int i) {
        super(context, i);
    }

    public void setSpanCount(int i) {
        super.setSpanCount(i);
        this.requestedSpanCount = i;
    }

    public void onAttachedToWindow(@NonNull RecyclerView recyclerView) {
        super.onAttachedToWindow(recyclerView);
        this.parent = new WeakReference<>(recyclerView);
        setSpanCount(determineSpanCount(this.requestedSpanCount));
    }

    public void onDetachedFromWindow(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        this.parent = new WeakReference<>((Object) null);
    }

    /* access modifiers changed from: protected */
    public int determineSpanCount(int i) {
        RecyclerView recyclerView = (RecyclerView) this.parent.get();
        if (recyclerView == null) {
            return i;
        }
        if (recyclerView.getWidth() == 0) {
            recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new LayoutListener(recyclerView));
            return i;
        }
        Context context = recyclerView.getContext();
        if (ThemeUtils.isLandscape(context)) {
            int width = recyclerView.getWidth();
            int screenHeight = ThemeUtils.getScreenHeight(context) / i;
            int i2 = 0;
            int i3 = Integer.MAX_VALUE;
            int i4 = i;
            int i5 = Integer.MAX_VALUE;
            while (i2 < 9) {
                int i6 = i + i2;
                int abs = Math.abs((width / i6) - screenHeight);
                if (abs < i3) {
                    i4 = i6;
                    i3 = abs;
                } else if (abs > i5) {
                    break;
                }
                i2++;
                i5 = abs;
            }
            return i4;
        }
        int width2 = recyclerView.getWidth() / i;
        return i;
    }

    protected class LayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        private RecyclerView recyclerView;

        public LayoutListener(@NonNull RecyclerView recyclerView2) {
            this.recyclerView = recyclerView2;
        }

        public void onGlobalLayout() {
            removeOnGlobalLayoutListener(this.recyclerView, this);
            ((GridLayoutManager) this.recyclerView.getLayoutManager()).setSpanCount(ColumnGridLayoutManager.this.determineSpanCount(ColumnGridLayoutManager.this.requestedSpanCount));
        }

        public void removeOnGlobalLayoutListener(@NonNull View view, @NonNull ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
            if (Build.VERSION.SDK_INT < 16) {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(onGlobalLayoutListener);
            } else {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
            }
        }
    }
}
