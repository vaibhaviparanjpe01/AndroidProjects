package com.newandromo.dev849565.app936843;

import android.support.v7.graphics.Palette;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.Resource;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class PaletteEncoder implements ResourceEncoder<Palette> {
    public boolean encode(Resource<Palette> resource, OutputStream outputStream) {
        PaletteSerializer paletteSerializer = new PaletteSerializer(resource.get());
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(paletteSerializer);
            objectOutputStream.flush();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public String getId() {
        return PaletteEncoder.class.getSimpleName();
    }
}
