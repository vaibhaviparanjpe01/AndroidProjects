package com.newandromo.dev849565.app936843;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gifbitmap.GifBitmapWrapper;
import com.bumptech.glide.load.resource.transcode.GifBitmapWrapperDrawableTranscoder;
import com.bumptech.glide.load.resource.transcode.GlideBitmapDrawableTranscoder;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.bumptech.glide.load.resource.transcode.UnitTranscoder;

public class DelayTranscoder<Z, R> implements ResourceTranscoder<Z, R> {
    private final int delay;
    private final ResourceTranscoder<Z, R> transcoder;

    public DelayTranscoder(int i, ResourceTranscoder<Z, R> resourceTranscoder) {
        this.delay = i;
        this.transcoder = resourceTranscoder;
    }

    public Resource<R> transcode(Resource<Z> resource) {
        try {
            Thread.sleep((long) this.delay);
        } catch (InterruptedException unused) {
        }
        return this.transcoder.transcode(resource);
    }

    public String getId() {
        return this.transcoder.getId();
    }

    public static ResourceTranscoder<GifBitmapWrapper, GlideDrawable> drawable(int i, Context context) {
        return new DelayTranscoder(i, new GifBitmapWrapperDrawableTranscoder(new GlideBitmapDrawableTranscoder(context.getResources(), Glide.get(context).getBitmapPool())));
    }

    public static <T> ResourceTranscoder<T, T> unit(int i) {
        return new DelayTranscoder(i, UnitTranscoder.get());
    }
}
