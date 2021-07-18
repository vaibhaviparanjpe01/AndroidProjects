package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;

public class PaletteBitmapToBitmapTranscoder implements ResourceTranscoder<PaletteBitmap, Bitmap> {
    private final BitmapPool bitmapPool;

    public PaletteBitmapToBitmapTranscoder(Context context) {
        this.bitmapPool = Glide.get(context).getBitmapPool();
    }

    public Resource<Bitmap> transcode(Resource<PaletteBitmap> resource) {
        return new BitmapResource(resource.get().bitmap, this.bitmapPool);
    }

    public String getId() {
        return PaletteBitmapToBitmapTranscoder.class.getName();
    }
}
