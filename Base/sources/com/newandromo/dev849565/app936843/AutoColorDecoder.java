package com.newandromo.dev849565.app936843;

import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.SimpleResource;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class AutoColorDecoder implements ResourceDecoder<InputStream, AutoColor> {
    public Resource<AutoColor> decode(InputStream inputStream, int i, int i2) throws IOException {
        try {
            return new SimpleResource(((AutoColorSerializer) new ObjectInputStream(inputStream).readObject()).getAutoColor());
        } catch (Exception e) {
            throw new IOException("Cannot read AutoColor", e);
        }
    }

    public String getId() {
        return AutoColorDecoder.class.getSimpleName();
    }
}
