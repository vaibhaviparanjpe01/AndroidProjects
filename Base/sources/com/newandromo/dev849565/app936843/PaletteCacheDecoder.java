package com.newandromo.dev849565.app936843;

import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.SimpleResource;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class PaletteCacheDecoder implements ResourceDecoder<InputStream, Palette> {
    private final ResourceDecoder<InputStream, Bitmap> bitmapDecoder;
    private final ResourceDecoder<InputStream, Palette> paletteDecoder;

    public PaletteCacheDecoder(ResourceDecoder<InputStream, Palette> resourceDecoder, ResourceDecoder<InputStream, Bitmap> resourceDecoder2) {
        this.paletteDecoder = resourceDecoder;
        this.bitmapDecoder = resourceDecoder2;
    }

    public Resource<Palette> decode(InputStream inputStream, int i, int i2) throws IOException {
        if (!inputStream.markSupported()) {
            inputStream = new BufferedInputStream(inputStream);
        }
        if (isBitmap(inputStream)) {
            Resource<Bitmap> decode = this.bitmapDecoder.decode(inputStream, i, i2);
            try {
                Palette generate = new Palette.Builder(decode.get()).resizeBitmapArea(-1).addTarget(PaletteUtils.DOMINANT).addTarget(PaletteUtils.DOMINANT_DARK).addTarget(PaletteUtils.DOMINANT_LIGHT).addTarget(PaletteUtils.DOMINANT_VIBRANT).addTarget(PaletteUtils.DOMINANT_MUTED).addTarget(PaletteUtils.DARK).addTarget(PaletteUtils.LIGHT).addTarget(PaletteUtils.NEUTRAL).generate();
                if (generate.getSwatches().isEmpty()) {
                    generate = new Palette.Builder(decode.get()).resizeBitmapArea(-1).addTarget(PaletteUtils.DOMINANT).addTarget(PaletteUtils.DOMINANT_DARK).addTarget(PaletteUtils.DOMINANT_LIGHT).addTarget(PaletteUtils.DOMINANT_VIBRANT).addTarget(PaletteUtils.DOMINANT_MUTED).addTarget(PaletteUtils.DARK).addTarget(PaletteUtils.LIGHT).addTarget(PaletteUtils.NEUTRAL).clearFilters().generate();
                }
                return new SimpleResource(generate);
            } finally {
                decode.recycle();
            }
        } else if (inputStream.read() == 0) {
            return this.paletteDecoder.decode(inputStream, i, i2);
        } else {
            throw new IOException("Cannot read palette magic.");
        }
    }

    private boolean isBitmap(InputStream inputStream) throws IOException {
        if (inputStream.markSupported()) {
            boolean z = true;
            inputStream.mark(1);
            if (inputStream.read() == 0) {
                z = false;
            }
            inputStream.reset();
            return z;
        }
        throw new IllegalArgumentException("Cannot peek");
    }

    public String getId() {
        return PaletteCacheDecoder.class.getSimpleName();
    }
}
