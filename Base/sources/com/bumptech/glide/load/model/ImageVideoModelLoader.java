package com.bumptech.glide.load.model;

import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.data.DataFetcher;
import java.io.InputStream;

public class ImageVideoModelLoader<A> implements ModelLoader<A, ImageVideoWrapper> {
    private static final String TAG = "IVML";
    private final ModelLoader<A, ParcelFileDescriptor> fileDescriptorLoader;
    private final ModelLoader<A, InputStream> streamLoader;

    public ImageVideoModelLoader(ModelLoader<A, InputStream> modelLoader, ModelLoader<A, ParcelFileDescriptor> modelLoader2) {
        if (modelLoader == null && modelLoader2 == null) {
            throw new NullPointerException("At least one of streamLoader and fileDescriptorLoader must be non null");
        }
        this.streamLoader = modelLoader;
        this.fileDescriptorLoader = modelLoader2;
    }

    public DataFetcher<ImageVideoWrapper> getResourceFetcher(A a, int i, int i2) {
        DataFetcher<InputStream> resourceFetcher = this.streamLoader != null ? this.streamLoader.getResourceFetcher(a, i, i2) : null;
        DataFetcher<ParcelFileDescriptor> resourceFetcher2 = this.fileDescriptorLoader != null ? this.fileDescriptorLoader.getResourceFetcher(a, i, i2) : null;
        if (resourceFetcher == null && resourceFetcher2 == null) {
            return null;
        }
        return new ImageVideoFetcher(resourceFetcher, resourceFetcher2);
    }

    static class ImageVideoFetcher implements DataFetcher<ImageVideoWrapper> {
        private final DataFetcher<ParcelFileDescriptor> fileDescriptorFetcher;
        private final DataFetcher<InputStream> streamFetcher;

        public ImageVideoFetcher(DataFetcher<InputStream> dataFetcher, DataFetcher<ParcelFileDescriptor> dataFetcher2) {
            this.streamFetcher = dataFetcher;
            this.fileDescriptorFetcher = dataFetcher2;
        }

        /* JADX WARNING: Removed duplicated region for block: B:14:0x002a A[SYNTHETIC, Splitter:B:14:0x002a] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.bumptech.glide.load.model.ImageVideoWrapper loadData(com.bumptech.glide.Priority r6) throws java.lang.Exception {
            /*
                r5 = this;
                com.bumptech.glide.load.data.DataFetcher<java.io.InputStream> r0 = r5.streamFetcher
                r1 = 2
                r2 = 0
                if (r0 == 0) goto L_0x0025
                com.bumptech.glide.load.data.DataFetcher<java.io.InputStream> r0 = r5.streamFetcher     // Catch:{ Exception -> 0x000f }
                java.lang.Object r0 = r0.loadData(r6)     // Catch:{ Exception -> 0x000f }
                java.io.InputStream r0 = (java.io.InputStream) r0     // Catch:{ Exception -> 0x000f }
                goto L_0x0026
            L_0x000f:
                r0 = move-exception
                java.lang.String r3 = "IVML"
                boolean r3 = android.util.Log.isLoggable(r3, r1)
                if (r3 == 0) goto L_0x001f
                java.lang.String r3 = "IVML"
                java.lang.String r4 = "Exception fetching input stream, trying ParcelFileDescriptor"
                android.util.Log.v(r3, r4, r0)
            L_0x001f:
                com.bumptech.glide.load.data.DataFetcher<android.os.ParcelFileDescriptor> r3 = r5.fileDescriptorFetcher
                if (r3 == 0) goto L_0x0024
                goto L_0x0025
            L_0x0024:
                throw r0
            L_0x0025:
                r0 = r2
            L_0x0026:
                com.bumptech.glide.load.data.DataFetcher<android.os.ParcelFileDescriptor> r3 = r5.fileDescriptorFetcher
                if (r3 == 0) goto L_0x0047
                com.bumptech.glide.load.data.DataFetcher<android.os.ParcelFileDescriptor> r3 = r5.fileDescriptorFetcher     // Catch:{ Exception -> 0x0033 }
                java.lang.Object r6 = r3.loadData(r6)     // Catch:{ Exception -> 0x0033 }
                android.os.ParcelFileDescriptor r6 = (android.os.ParcelFileDescriptor) r6     // Catch:{ Exception -> 0x0033 }
                goto L_0x0048
            L_0x0033:
                r6 = move-exception
                java.lang.String r3 = "IVML"
                boolean r1 = android.util.Log.isLoggable(r3, r1)
                if (r1 == 0) goto L_0x0043
                java.lang.String r1 = "IVML"
                java.lang.String r3 = "Exception fetching ParcelFileDescriptor"
                android.util.Log.v(r1, r3, r6)
            L_0x0043:
                if (r0 == 0) goto L_0x0046
                goto L_0x0047
            L_0x0046:
                throw r6
            L_0x0047:
                r6 = r2
            L_0x0048:
                com.bumptech.glide.load.model.ImageVideoWrapper r1 = new com.bumptech.glide.load.model.ImageVideoWrapper
                r1.<init>(r0, r6)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.model.ImageVideoModelLoader.ImageVideoFetcher.loadData(com.bumptech.glide.Priority):com.bumptech.glide.load.model.ImageVideoWrapper");
        }

        public void cleanup() {
            if (this.streamFetcher != null) {
                this.streamFetcher.cleanup();
            }
            if (this.fileDescriptorFetcher != null) {
                this.fileDescriptorFetcher.cleanup();
            }
        }

        public String getId() {
            if (this.streamFetcher != null) {
                return this.streamFetcher.getId();
            }
            return this.fileDescriptorFetcher.getId();
        }

        public void cancel() {
            if (this.streamFetcher != null) {
                this.streamFetcher.cancel();
            }
            if (this.fileDescriptorFetcher != null) {
                this.fileDescriptorFetcher.cancel();
            }
        }
    }
}
