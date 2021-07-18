package com.flurry.sdk;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class dx implements dw<String> {
    public final /* synthetic */ void a(OutputStream outputStream, Object obj) throws IOException {
        String str = (String) obj;
        if (outputStream != null && str != null) {
            byte[] bytes = str.getBytes("utf-8");
            outputStream.write(bytes, 0, bytes.length);
        }
    }

    public final /* synthetic */ Object a(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        em.a(inputStream, (OutputStream) byteArrayOutputStream);
        return byteArrayOutputStream.toString();
    }
}
