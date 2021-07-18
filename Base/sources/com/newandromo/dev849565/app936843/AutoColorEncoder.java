package com.newandromo.dev849565.app936843;

import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.Resource;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class AutoColorEncoder implements ResourceEncoder<AutoColor> {
    public boolean encode(Resource<AutoColor> resource, OutputStream outputStream) {
        AutoColorSerializer autoColorSerializer = new AutoColorSerializer(resource.get());
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(autoColorSerializer);
            objectOutputStream.flush();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public String getId() {
        return AutoColorEncoder.class.getSimpleName();
    }
}
