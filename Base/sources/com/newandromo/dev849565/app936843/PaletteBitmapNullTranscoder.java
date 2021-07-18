package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;

public class PaletteBitmapNullTranscoder implements ResourceTranscoder<Bitmap, PaletteBitmap> {
    private final BitmapPool bitmapPool;

    public PaletteBitmapNullTranscoder(Context context) {
        this.bitmapPool = Glide.get(context).getBitmapPool();
    }

    public Resource<PaletteBitmap> transcode(Resource<Bitmap> resource) {
        return new PaletteBitmapResource(new PaletteBitmap(resource.get(), (Palette) null), this.bitmapPool);
    }

    public String getId() {
        return PaletteBitmapNullTranscoder.class.getName();
    }
}
