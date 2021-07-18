package com.newandromo.dev849565.app936843;

import android.support.v7.graphics.Palette;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.SimpleResource;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class PaletteDecoder implements ResourceDecoder<InputStream, Palette> {
    public Resource<Palette> decode(InputStream inputStream, int i, int i2) throws IOException {
        try {
            return new SimpleResource(((PaletteSerializer) new ObjectInputStream(inputStream).readObject()).getPalette());
        } catch (Exception e) {
            throw new IOException("Cannot read palette", e);
        }
    }

    public String getId() {
        return PaletteDecoder.class.getSimpleName();
    }
}
