package com.flurry.sdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class dt<ObjectType> implements dw<ObjectType> {
    protected final dw<ObjectType> a;

    public dt(dw<ObjectType> dwVar) {
        this.a = dwVar;
    }

    public void a(OutputStream outputStream, ObjectType objecttype) throws IOException {
        if (this.a != null && outputStream != null && objecttype != null) {
            this.a.a(outputStream, objecttype);
        }
    }

    public ObjectType a(InputStream inputStream) throws IOException {
        if (this.a == null || inputStream == null) {
            return null;
        }
        return this.a.a(inputStream);
    }
}
