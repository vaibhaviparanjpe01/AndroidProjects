package com.newandromo.dev849565.app936843;

import android.support.v7.widget.RecyclerView;

public class PaletteArrayManager extends AbstractPartialArray<PaletteArray> {
    private static final String TAG = "PaletteArrayManager";
    public RecyclerView.AdapterDataObserver changeObserver;
    private final int paletteArraysPerItem;

    public PaletteArrayManager() {
        this(10);
    }

    public PaletteArrayManager(int i) {
        this.changeObserver = new RecyclerView.AdapterDataObserver() {
            public void onChanged() {
                PaletteArrayManager.this.resetAll();
            }

            public void onItemRangeChanged(int i, int i2) {
                PaletteArrayManager.this.resetFrom(i, i2);
            }

            public void onItemRangeChanged(int i, int i2, Object obj) {
                PaletteArrayManager.this.resetFrom(i, i2);
            }

            public void onItemRangeInserted(int i, int i2) {
                PaletteArrayManager.this.resetFrom(i);
            }

            public void onItemRangeRemoved(int i, int i2) {
                PaletteArrayManager.this.resetFrom(i);
            }

            public void onItemRangeMoved(int i, int i2, int i3) {
                PaletteArrayManager.this.resetFrom(Math.min(i, i2));
            }
        };
        this.paletteArraysPerItem = i;
    }

    public PaletteArrayManager(int i, int i2, int i3) {
        super(i2, i3);
        this.changeObserver = new RecyclerView.AdapterDataObserver() {
            public void onChanged() {
                PaletteArrayManager.this.resetAll();
            }

            public void onItemRangeChanged(int i, int i2) {
                PaletteArrayManager.this.resetFrom(i, i2);
            }

            public void onItemRangeChanged(int i, int i2, Object obj) {
                PaletteArrayManager.this.resetFrom(i, i2);
            }

            public void onItemRangeInserted(int i, int i2) {
                PaletteArrayManager.this.resetFrom(i);
            }

            public void onItemRangeRemoved(int i, int i2) {
                PaletteArrayManager.this.resetFrom(i);
            }

            public void onItemRangeMoved(int i, int i2, int i3) {
                PaletteArrayManager.this.resetFrom(Math.min(i, i2));
            }
        };
        this.paletteArraysPerItem = i;
    }

    /* access modifiers changed from: protected */
    public void onReset(PaletteArray paletteArray) {
        paletteArray.removeAll();
    }

    /* access modifiers changed from: protected */
    public PaletteArray createElement() {
        return new PaletteArray(this.paletteArraysPerItem);
    }

    /* access modifiers changed from: protected */
    public PaletteArray[] createArray(int i) {
        return new PaletteArray[i];
    }
}
