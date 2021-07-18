package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import com.squareup.picasso.Picasso;

public class PicassoScrollListener extends RecyclerView.OnScrollListener {
    private final Context context;

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
    }

    public PicassoScrollListener(Context context2) {
        this.context = context2;
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        Picasso with = Picasso.with(this.context);
        if (i == 0 || i == 2) {
            with.resumeTag(this.context);
        } else {
            with.pauseTag(this.context);
        }
    }
}
