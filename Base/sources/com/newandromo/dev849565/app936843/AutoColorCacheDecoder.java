package com.newandromo.dev849565.app936843;

import android.support.v4.internal.view.SupportMenu;
import android.support.v7.graphics.Palette;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.SimpleResource;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class AutoColorCacheDecoder implements ResourceDecoder<InputStream, AutoColor> {
    private final ResourceDecoder<InputStream, AutoColor> autoColorDecoder;
    private final ResourceDecoder<InputStream, Palette> paletteDecoder;

    public AutoColorCacheDecoder(ResourceDecoder<InputStream, AutoColor> resourceDecoder, ResourceDecoder<InputStream, Palette> resourceDecoder2) {
        this.autoColorDecoder = resourceDecoder;
        this.paletteDecoder = resourceDecoder2;
    }

    public Resource<AutoColor> decode(InputStream inputStream, int i, int i2) throws IOException {
        if (!inputStream.markSupported()) {
            inputStream = new BufferedInputStream(inputStream);
        }
        if (isPalette(inputStream)) {
            this.paletteDecoder.decode(inputStream, i, i2);
            return new SimpleResource(new AutoColor(SupportMenu.CATEGORY_MASK));
        } else if (1 == inputStream.read()) {
            return this.autoColorDecoder.decode(inputStream, i, i2);
        } else {
            throw new IOException("Cannot read AutoColor magic byte.");
        }
    }

    private boolean isPalette(InputStream inputStream) throws IOException {
        if (inputStream.markSupported()) {
            boolean z = true;
            inputStream.mark(1);
            if (inputStream.read() != 0) {
                z = false;
            }
            inputStream.reset();
            return z;
        }
        throw new IllegalArgumentException("Cannot peek");
    }

    private boolean isAutoColor(InputStream inputStream) throws IOException {
        if (inputStream.markSupported()) {
            boolean z = true;
            inputStream.mark(1);
            if (inputStream.read() != 1) {
                z = false;
            }
            inputStream.reset();
            return z;
        }
        throw new IllegalArgumentException("Cannot peek");
    }

    public String getId() {
        return AutoColorCacheDecoder.class.getSimpleName();
    }
}
