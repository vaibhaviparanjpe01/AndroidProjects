package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.model.ImageVideoWrapper;
import java.io.InputStream;

public class ImageVideoBitmapDecoder implements ResourceDecoder<ImageVideoWrapper, Bitmap> {
    private static final String TAG = "ImageVideoDecoder";
    private final ResourceDecoder<ParcelFileDescriptor, Bitmap> fileDescriptorDecoder;
    private final ResourceDecoder<InputStream, Bitmap> streamDecoder;

    public String getId() {
        return "ImageVideoBitmapDecoder.com.bumptech.glide.load.resource.bitmap";
    }

    public ImageVideoBitmapDecoder(ResourceDecoder<InputStream, Bitmap> resourceDecoder, ResourceDecoder<ParcelFileDescriptor, Bitmap> resourceDecoder2) {
        this.streamDecoder = resourceDecoder;
        this.fileDescriptorDecoder = resourceDecoder2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0021, code lost:
        r4 = r4.getFileDescriptor();
     */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0027  */
    /* JADX WARNING: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.bumptech.glide.load.engine.Resource<android.graphics.Bitmap> decode(com.bumptech.glide.load.model.ImageVideoWrapper r4, int r5, int r6) throws java.io.IOException {
        /*
            r3 = this;
            java.io.InputStream r0 = r4.getStream()
            if (r0 == 0) goto L_0x001e
            com.bumptech.glide.load.ResourceDecoder<java.io.InputStream, android.graphics.Bitmap> r1 = r3.streamDecoder     // Catch:{ IOException -> 0x000d }
            com.bumptech.glide.load.engine.Resource r0 = r1.decode(r0, r5, r6)     // Catch:{ IOException -> 0x000d }
            goto L_0x001f
        L_0x000d:
            r0 = move-exception
            java.lang.String r1 = "ImageVideoDecoder"
            r2 = 2
            boolean r1 = android.util.Log.isLoggable(r1, r2)
            if (r1 == 0) goto L_0x001e
            java.lang.String r1 = "ImageVideoDecoder"
            java.lang.String r2 = "Failed to load image from stream, trying FileDescriptor"
            android.util.Log.v(r1, r2, r0)
        L_0x001e:
            r0 = 0
        L_0x001f:
            if (r0 != 0) goto L_0x002d
            android.os.ParcelFileDescriptor r4 = r4.getFileDescriptor()
            if (r4 == 0) goto L_0x002d
            com.bumptech.glide.load.ResourceDecoder<android.os.ParcelFileDescriptor, android.graphics.Bitmap> r0 = r3.fileDescriptorDecoder
            com.bumptech.glide.load.engine.Resource r0 = r0.decode(r4, r5, r6)
        L_0x002d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.ImageVideoBitmapDecoder.decode(com.bumptech.glide.load.model.ImageVideoWrapper, int, int):com.bumptech.glide.load.engine.Resource");
    }
}
