package com.newandromo.dev849565.app936843;

import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Util;

public class PaletteBitmapResource implements Resource<PaletteBitmap> {
    private BitmapPool bitmapPool;
    private PaletteBitmap paletteBitmap;

    public PaletteBitmapResource(PaletteBitmap paletteBitmap2, BitmapPool bitmapPool2) {
        this.paletteBitmap = paletteBitmap2;
        this.bitmapPool = bitmapPool2;
    }

    public PaletteBitmap get() {
        return this.paletteBitmap;
    }

    public int getSize() {
        return Util.getBitmapByteSize(this.paletteBitmap.bitmap);
    }

    public void recycle() {
        if (!this.bitmapPool.put(this.paletteBitmap.bitmap)) {
            this.paletteBitmap.bitmap.recycle();
        }
    }
}
