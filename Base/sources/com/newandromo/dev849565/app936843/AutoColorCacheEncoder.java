package com.newandromo.dev849565.app936843;

import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.Resource;
import java.io.IOException;
import java.io.OutputStream;

public class AutoColorCacheEncoder implements ResourceEncoder<AutoColor> {
    static final int AUTO_COLOR_MAGIC_BYTE = 1;
    private final ResourceEncoder<AutoColor> autoColorEncoder;

    public AutoColorCacheEncoder(ResourceEncoder<AutoColor> resourceEncoder) {
        this.autoColorEncoder = resourceEncoder;
    }

    public boolean encode(Resource<AutoColor> resource, OutputStream outputStream) {
        try {
            outputStream.write(1);
            return this.autoColorEncoder.encode(resource, outputStream);
        } catch (IOException unused) {
            return false;
        }
    }

    public String getId() {
        return AutoColorCacheEncoder.class.getSimpleName();
    }
}
