package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;

public class PaletteBitmapTranscoder implements ResourceTranscoder<Bitmap, PaletteBitmap> {
    private final BitmapPool bitmapPool;

    public PaletteBitmapTranscoder(Context context) {
        this.bitmapPool = Glide.get(context).getBitmapPool();
    }

    public PaletteBitmapTranscoder(Context context, boolean z) {
        this.bitmapPool = Glide.get(context).getBitmapPool();
    }

    public Resource<PaletteBitmap> transcode(Resource<Bitmap> resource) {
        Bitmap bitmap = resource.get();
        Palette generate = new Palette.Builder(bitmap).addTarget(PaletteUtils.DOMINANT).addTarget(PaletteUtils.DOMINANT_DARK).addTarget(PaletteUtils.DOMINANT_LIGHT).addTarget(PaletteUtils.DOMINANT_VIBRANT).addTarget(PaletteUtils.DOMINANT_MUTED).addTarget(PaletteUtils.DARK).addTarget(PaletteUtils.LIGHT).addTarget(PaletteUtils.NEUTRAL).generate();
        if (generate.getSwatches().isEmpty()) {
            generate = new Palette.Builder(bitmap).addTarget(PaletteUtils.DOMINANT).addTarget(PaletteUtils.DOMINANT_DARK).addTarget(PaletteUtils.DOMINANT_LIGHT).addTarget(PaletteUtils.DOMINANT_VIBRANT).addTarget(PaletteUtils.DOMINANT_MUTED).addTarget(PaletteUtils.DARK).addTarget(PaletteUtils.LIGHT).addTarget(PaletteUtils.NEUTRAL).clearFilters().generate();
        }
        return new PaletteBitmapResource(new PaletteBitmap(bitmap, generate), this.bitmapPool);
    }

    public String getId() {
        return PaletteBitmapTranscoder.class.getName();
    }
}
