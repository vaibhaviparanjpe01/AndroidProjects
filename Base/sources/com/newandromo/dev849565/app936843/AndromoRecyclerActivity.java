package com.newandromo.dev849565.app936843;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewTreeObserver;

public abstract class AndromoRecyclerActivity extends AndromoActivity {
    private static final String TAG = "AndromoRecyclerActivity";
    private RecyclerView.Adapter<ItemViewHolder> mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    protected RecyclerView mRecyclerView;

    /* access modifiers changed from: protected */
    public abstract RecyclerView.Adapter<ItemViewHolder> createAdapter();

    /* access modifiers changed from: protected */
    public abstract float getDecorationSize();

    /* access modifiers changed from: protected */
    public abstract int getDecorationType();

    /* access modifiers changed from: protected */
    public abstract int getLayoutColumns();

    /* access modifiers changed from: protected */
    public abstract boolean getLayoutMatchNaturalSpanSize();

    /* access modifiers changed from: protected */
    public abstract float getLayoutMaxSpanSize();

    /* access modifiers changed from: protected */
    public abstract int getLayoutMaxSpansLandscape();

    /* access modifiers changed from: protected */
    public abstract int getLayoutMaxSpansPortrait();

    /* access modifiers changed from: protected */
    public abstract float getLayoutMinSpanSize();

    /* access modifiers changed from: protected */
    public abstract int getLayoutMinSpans();

    /* access modifiers changed from: protected */
    public abstract int getLayoutStyle();

    /* access modifiers changed from: protected */
    public int getRecyclerViewId() {
        return R.id.recycler;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mRecyclerView = (RecyclerView) findViewById(getRecyclerViewId());
        this.mRecyclerView.setHasFixedSize(true);
        if (getLayoutStyle() == 1) {
            this.mLayoutManager = new ColumnGridLayoutManager(this, getLayoutColumns());
            this.mRecyclerView.setLayoutManager(this.mLayoutManager);
            addGridDecoration(this.mRecyclerView, getDecorationType(), getDecorationSize());
        } else if (getLayoutStyle() == 2) {
            this.mLayoutManager = new AdaptiveGridLayoutManager(this, getLayoutMinSpans(), getLayoutMaxSpansPortrait(), getLayoutMaxSpansLandscape(), getLayoutMinSpanSize(), getLayoutMaxSpanSize(), getLayoutMatchNaturalSpanSize());
            this.mRecyclerView.setLayoutManager(this.mLayoutManager);
            addGridDecoration(this.mRecyclerView, getDecorationType(), getDecorationSize());
        } else if (getLayoutStyle() == 0) {
            this.mLayoutManager = new LinearLayoutManager(this);
            this.mRecyclerView.setLayoutManager(this.mLayoutManager);
            addLinearDecoration(this.mRecyclerView, (LinearLayoutManager) this.mLayoutManager, getDecorationType());
        }
        this.mAdapter = createAdapter();
        this.mRecyclerView.setAdapter(this.mAdapter);
        if (!isAutomaticBackgroundColorEnabled(this) || usingToolbarColorsFromTheme()) {
            onToolbarColorsFromTheme();
        }
    }

    private static void addGridDecoration(RecyclerView recyclerView, int i, float f) {
        if (i == 1) {
            recyclerView.addItemDecoration(new OffsetItemDecoration(recyclerView.getContext(), f));
        } else if (i == 2) {
            recyclerView.addItemDecoration(new GridDividerItemDecoration(recyclerView.getContext()));
        }
    }

    private static void addLinearDecoration(RecyclerView recyclerView, LinearLayoutManager linearLayoutManager, int i) {
        if (i == 2) {
            recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation()));
        }
    }

    /* access modifiers changed from: protected */
    public void onToolbarColorsReady(int i, int i2) {
        if (this.mAdapter != null && (this.mAdapter instanceof OnToolbarColorsReady)) {
            ((OnToolbarColorsReady) this.mAdapter).onToolbarColorsReady(i, i2);
        }
    }

    public void onBackPressed() {
        if (this.mRecyclerView != null) {
            this.mRecyclerView.stopScroll();
        }
        super.onBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.mAdapter instanceof Recycleable) {
            ((Recycleable) this.mAdapter).recycle();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void swapCursor(Cursor cursor) {
        if (this.mAdapter instanceof CursorItemAdapter) {
            ((CursorItemAdapter) this.mAdapter).swapCursor(cursor);
        }
    }

    public void onActivityReenter(int i, Intent intent) {
        super.onActivityReenter(i, intent);
        if (this.bSharedElementTransitionEnabled) {
            supportPostponeEnterTransition();
            this.mRecyclerView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                public boolean onPreDraw() {
                    AndromoRecyclerActivity.this.mRecyclerView.getViewTreeObserver().removeOnPreDrawListener(this);
                    AndromoRecyclerActivity.this.supportStartPostponedEnterTransition();
                    return true;
                }
            });
        }
    }
}
