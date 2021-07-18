package com.newandromo.dev849565.app936843;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class SingleItemAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {
    private static final String TAG = "SingleItemAdapter";

    public final int getItemCount() {
        return 1;
    }

    public abstract int getItemViewType();

    public abstract T onCreateViewHolder(ViewGroup viewGroup);

    public final int getItemViewType(int i) {
        return getItemViewType();
    }

    public final T onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == getItemViewType()) {
            return onCreateViewHolder(viewGroup);
        }
        throw new IllegalArgumentException("Unknown view type: " + i);
    }

    public static View inflateItemView(ViewGroup viewGroup, int i) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false);
    }
}
