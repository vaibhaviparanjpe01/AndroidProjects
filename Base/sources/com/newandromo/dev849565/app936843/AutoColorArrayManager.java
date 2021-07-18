package com.newandromo.dev849565.app936843;

import android.support.v7.widget.RecyclerView;

public class AutoColorArrayManager extends AbstractPartialArray<AutoColorArray> {
    private static final String TAG = "AutoColorArrayManager";
    private final int autoColorArraysPerItem;
    public RecyclerView.AdapterDataObserver changeObserver;

    public AutoColorArrayManager() {
        this(10);
    }

    public AutoColorArrayManager(int i) {
        this.changeObserver = new RecyclerView.AdapterDataObserver() {
            public void onChanged() {
                AutoColorArrayManager.this.resetAll();
            }

            public void onItemRangeChanged(int i, int i2) {
                AutoColorArrayManager.this.resetFrom(i, i2);
            }

            public void onItemRangeChanged(int i, int i2, Object obj) {
                AutoColorArrayManager.this.resetFrom(i, i2);
            }

            public void onItemRangeInserted(int i, int i2) {
                AutoColorArrayManager.this.resetFrom(i);
            }

            public void onItemRangeRemoved(int i, int i2) {
                AutoColorArrayManager.this.resetFrom(i);
            }

            public void onItemRangeMoved(int i, int i2, int i3) {
                AutoColorArrayManager.this.resetFrom(Math.min(i, i2));
            }
        };
        this.autoColorArraysPerItem = i;
    }

    public AutoColorArrayManager(int i, int i2, int i3) {
        super(i2, i3);
        this.changeObserver = new RecyclerView.AdapterDataObserver() {
            public void onChanged() {
                AutoColorArrayManager.this.resetAll();
            }

            public void onItemRangeChanged(int i, int i2) {
                AutoColorArrayManager.this.resetFrom(i, i2);
            }

            public void onItemRangeChanged(int i, int i2, Object obj) {
                AutoColorArrayManager.this.resetFrom(i, i2);
            }

            public void onItemRangeInserted(int i, int i2) {
                AutoColorArrayManager.this.resetFrom(i);
            }

            public void onItemRangeRemoved(int i, int i2) {
                AutoColorArrayManager.this.resetFrom(i);
            }

            public void onItemRangeMoved(int i, int i2, int i3) {
                AutoColorArrayManager.this.resetFrom(Math.min(i, i2));
            }
        };
        this.autoColorArraysPerItem = i;
    }

    /* access modifiers changed from: protected */
    public void onReset(AutoColorArray autoColorArray) {
        autoColorArray.removeAll();
    }

    /* access modifiers changed from: protected */
    public AutoColorArray createElement() {
        return new AutoColorArray(this.autoColorArraysPerItem);
    }

    /* access modifiers changed from: protected */
    public AutoColorArray[] createArray(int i) {
        return new AutoColorArray[i];
    }
}
