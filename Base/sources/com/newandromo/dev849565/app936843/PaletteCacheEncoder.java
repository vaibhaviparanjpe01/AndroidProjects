package com.newandromo.dev849565.app936843;

import android.support.v7.graphics.Palette;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.Resource;
import java.io.IOException;
import java.io.OutputStream;

public class PaletteCacheEncoder implements ResourceEncoder<Palette> {
    static final int PALETTE_MAGIC_BYTE = 0;
    private final ResourceEncoder<Palette> paletteEncoder;

    public PaletteCacheEncoder(ResourceEncoder<Palette> resourceEncoder) {
        this.paletteEncoder = resourceEncoder;
    }

    public boolean encode(Resource<Palette> resource, OutputStream outputStream) {
        try {
            outputStream.write(0);
            return this.paletteEncoder.encode(resource, outputStream);
        } catch (IOException unused) {
            return false;
        }
    }

    public String getId() {
        return PaletteCacheEncoder.class.getSimpleName();
    }
}
