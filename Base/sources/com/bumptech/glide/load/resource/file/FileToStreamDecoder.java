package com.bumptech.glide.load.resource.file;

import com.bumptech.glide.load.ResourceDecoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileToStreamDecoder<T> implements ResourceDecoder<File, T> {
    private static final FileOpener DEFAULT_FILE_OPENER = new FileOpener();
    private final FileOpener fileOpener;
    private ResourceDecoder<InputStream, T> streamDecoder;

    public String getId() {
        return "";
    }

    public FileToStreamDecoder(ResourceDecoder<InputStream, T> resourceDecoder) {
        this(resourceDecoder, DEFAULT_FILE_OPENER);
    }

    FileToStreamDecoder(ResourceDecoder<InputStream, T> resourceDecoder, FileOpener fileOpener2) {
        this.streamDecoder = resourceDecoder;
        this.fileOpener = fileOpener2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0019 A[SYNTHETIC, Splitter:B:14:0x0019] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.bumptech.glide.load.engine.Resource<T> decode(java.io.File r3, int r4, int r5) throws java.io.IOException {
        /*
            r2 = this;
            r0 = 0
            com.bumptech.glide.load.resource.file.FileToStreamDecoder$FileOpener r1 = r2.fileOpener     // Catch:{ all -> 0x0015 }
            java.io.InputStream r3 = r1.open(r3)     // Catch:{ all -> 0x0015 }
            com.bumptech.glide.load.ResourceDecoder<java.io.InputStream, T> r0 = r2.streamDecoder     // Catch:{ all -> 0x0013 }
            com.bumptech.glide.load.engine.Resource r4 = r0.decode(r3, r4, r5)     // Catch:{ all -> 0x0013 }
            if (r3 == 0) goto L_0x0012
            r3.close()     // Catch:{ IOException -> 0x0012 }
        L_0x0012:
            return r4
        L_0x0013:
            r4 = move-exception
            goto L_0x0017
        L_0x0015:
            r4 = move-exception
            r3 = r0
        L_0x0017:
            if (r3 == 0) goto L_0x001c
            r3.close()     // Catch:{ IOException -> 0x001c }
        L_0x001c:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.file.FileToStreamDecoder.decode(java.io.File, int, int):com.bumptech.glide.load.engine.Resource");
    }

    static class FileOpener {
        FileOpener() {
        }

        public InputStream open(File file) throws FileNotFoundException {
            return new FileInputStream(file);
        }
    }
}
