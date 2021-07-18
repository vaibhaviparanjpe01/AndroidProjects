package com.newandromo.dev849565.app936843;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;

public interface ViewHolderCreator<T extends RecyclerView.ViewHolder> {
    T onCreateViewHolder(ViewGroup viewGroup, int i);
}
