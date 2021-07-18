package com.newandromo.dev849565.app936843;

import android.databinding.ViewDataBinding;
import android.support.v4.util.ArraySet;
import android.support.v7.widget.RecyclerView;
import com.bumptech.glide.request.Request;
import java.util.Set;

public class ItemViewHolder extends RecyclerView.ViewHolder implements RequestHolder {
    private static final String TAG = "ItemViewHolder";
    public final ViewDataBinding binding;
    public Set<PaletteViewTarget> paletteTargets = new ArraySet();
    private Request request;

    public ItemViewHolder(ViewDataBinding viewDataBinding) {
        super(viewDataBinding.getRoot());
        this.binding = viewDataBinding;
        viewDataBinding.setVariable(49, this);
    }

    public void setRequest(Request request2) {
        this.request = request2;
    }

    public Request getRequest() {
        return this.request;
    }
}
