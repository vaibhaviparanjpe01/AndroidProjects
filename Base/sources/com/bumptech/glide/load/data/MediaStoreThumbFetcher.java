package com.bumptech.glide.load.data;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import com.bumptech.glide.Priority;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MediaStoreThumbFetcher implements DataFetcher<InputStream> {
    private static final ThumbnailStreamOpenerFactory DEFAULT_FACTORY = new ThumbnailStreamOpenerFactory();
    private static final int MINI_HEIGHT = 384;
    private static final int MINI_WIDTH = 512;
    private static final String TAG = "MediaStoreThumbFetcher";
    private final Context context;
    private final DataFetcher<InputStream> defaultFetcher;
    private final ThumbnailStreamOpenerFactory factory;
    private final int height;
    private InputStream inputStream;
    private final Uri mediaStoreUri;
    private final int width;

    interface ThumbnailQuery {
        Cursor queryPath(Context context, Uri uri);
    }

    public void cancel() {
    }

    public MediaStoreThumbFetcher(Context context2, Uri uri, DataFetcher<InputStream> dataFetcher, int i, int i2) {
        this(context2, uri, dataFetcher, i, i2, DEFAULT_FACTORY);
    }

    MediaStoreThumbFetcher(Context context2, Uri uri, DataFetcher<InputStream> dataFetcher, int i, int i2, ThumbnailStreamOpenerFactory thumbnailStreamOpenerFactory) {
        this.context = context2;
        this.mediaStoreUri = uri;
        this.defaultFetcher = dataFetcher;
        this.width = i;
        this.height = i2;
        this.factory = thumbnailStreamOpenerFactory;
    }

    public InputStream loadData(Priority priority) throws Exception {
        ThumbnailStreamOpener build = this.factory.build(this.mediaStoreUri, this.width, this.height);
        if (build != null) {
            this.inputStream = openThumbInputStream(build);
        }
        if (this.inputStream == null) {
            this.inputStream = this.defaultFetcher.loadData(priority);
        }
        return this.inputStream;
    }

    private InputStream openThumbInputStream(ThumbnailStreamOpener thumbnailStreamOpener) {
        InputStream inputStream2;
        try {
            inputStream2 = thumbnailStreamOpener.open(this.context, this.mediaStoreUri);
        } catch (FileNotFoundException e) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Failed to find thumbnail file", e);
            }
            inputStream2 = null;
        }
        int orientation = inputStream2 != null ? thumbnailStreamOpener.getOrientation(this.context, this.mediaStoreUri) : -1;
        return orientation != -1 ? new ExifOrientationStream(inputStream2, orientation) : inputStream2;
    }

    public void cleanup() {
        if (this.inputStream != null) {
            try {
                this.inputStream.close();
            } catch (IOException unused) {
            }
        }
        this.defaultFetcher.cleanup();
    }

    public String getId() {
        return this.mediaStoreUri.toString();
    }

    /* access modifiers changed from: private */
    public static boolean isMediaStoreUri(Uri uri) {
        return uri != null && "content".equals(uri.getScheme()) && "media".equals(uri.getAuthority());
    }

    /* access modifiers changed from: private */
    public static boolean isMediaStoreVideo(Uri uri) {
        return isMediaStoreUri(uri) && uri.getPathSegments().contains("video");
    }

    static class FileService {
        FileService() {
        }

        public boolean exists(File file) {
            return file.exists();
        }

        public long length(File file) {
            return file.length();
        }

        public File get(String str) {
            return new File(str);
        }
    }

    static class ThumbnailStreamOpener {
        private static final FileService DEFAULT_SERVICE = new FileService();
        private ThumbnailQuery query;
        private final FileService service;

        public ThumbnailStreamOpener(ThumbnailQuery thumbnailQuery) {
            this(DEFAULT_SERVICE, thumbnailQuery);
        }

        public ThumbnailStreamOpener(FileService fileService, ThumbnailQuery thumbnailQuery) {
            this.service = fileService;
            this.query = thumbnailQuery;
        }

        /* JADX WARNING: Removed duplicated region for block: B:17:0x002a A[Catch:{ all -> 0x0047 }] */
        /* JADX WARNING: Removed duplicated region for block: B:19:0x0042 A[SYNTHETIC, Splitter:B:19:0x0042] */
        /* JADX WARNING: Removed duplicated region for block: B:26:0x004a A[SYNTHETIC, Splitter:B:26:0x004a] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int getOrientation(android.content.Context r6, android.net.Uri r7) {
            /*
                r5 = this;
                r0 = 0
                android.content.ContentResolver r6 = r6.getContentResolver()     // Catch:{ IOException -> 0x001d, all -> 0x001a }
                java.io.InputStream r6 = r6.openInputStream(r7)     // Catch:{ IOException -> 0x001d, all -> 0x001a }
                com.bumptech.glide.load.resource.bitmap.ImageHeaderParser r0 = new com.bumptech.glide.load.resource.bitmap.ImageHeaderParser     // Catch:{ IOException -> 0x0018 }
                r0.<init>(r6)     // Catch:{ IOException -> 0x0018 }
                int r0 = r0.getOrientation()     // Catch:{ IOException -> 0x0018 }
                if (r6 == 0) goto L_0x0046
                r6.close()     // Catch:{ IOException -> 0x0046 }
                goto L_0x0046
            L_0x0018:
                r0 = move-exception
                goto L_0x0021
            L_0x001a:
                r7 = move-exception
                r6 = r0
                goto L_0x0048
            L_0x001d:
                r6 = move-exception
                r4 = r0
                r0 = r6
                r6 = r4
            L_0x0021:
                java.lang.String r1 = "MediaStoreThumbFetcher"
                r2 = 3
                boolean r1 = android.util.Log.isLoggable(r1, r2)     // Catch:{ all -> 0x0047 }
                if (r1 == 0) goto L_0x0040
                java.lang.String r1 = "MediaStoreThumbFetcher"
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0047 }
                r2.<init>()     // Catch:{ all -> 0x0047 }
                java.lang.String r3 = "Failed to open uri: "
                r2.append(r3)     // Catch:{ all -> 0x0047 }
                r2.append(r7)     // Catch:{ all -> 0x0047 }
                java.lang.String r7 = r2.toString()     // Catch:{ all -> 0x0047 }
                android.util.Log.d(r1, r7, r0)     // Catch:{ all -> 0x0047 }
            L_0x0040:
                if (r6 == 0) goto L_0x0045
                r6.close()     // Catch:{ IOException -> 0x0045 }
            L_0x0045:
                r0 = -1
            L_0x0046:
                return r0
            L_0x0047:
                r7 = move-exception
            L_0x0048:
                if (r6 == 0) goto L_0x004d
                r6.close()     // Catch:{ IOException -> 0x004d }
            L_0x004d:
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.data.MediaStoreThumbFetcher.ThumbnailStreamOpener.getOrientation(android.content.Context, android.net.Uri):int");
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x001e  */
        /* JADX WARNING: Removed duplicated region for block: B:14:0x0023  */
        /* JADX WARNING: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.io.InputStream open(android.content.Context r3, android.net.Uri r4) throws java.io.FileNotFoundException {
            /*
                r2 = this;
                com.bumptech.glide.load.data.MediaStoreThumbFetcher$ThumbnailQuery r0 = r2.query
                android.database.Cursor r4 = r0.queryPath(r3, r4)
                r0 = 0
                if (r4 == 0) goto L_0x001b
                boolean r1 = r4.moveToFirst()     // Catch:{ all -> 0x0014 }
                if (r1 == 0) goto L_0x001b
                android.net.Uri r1 = r2.parseThumbUri(r4)     // Catch:{ all -> 0x0014 }
                goto L_0x001c
            L_0x0014:
                r3 = move-exception
                if (r4 == 0) goto L_0x001a
                r4.close()
            L_0x001a:
                throw r3
            L_0x001b:
                r1 = r0
            L_0x001c:
                if (r4 == 0) goto L_0x0021
                r4.close()
            L_0x0021:
                if (r1 == 0) goto L_0x002b
                android.content.ContentResolver r3 = r3.getContentResolver()
                java.io.InputStream r0 = r3.openInputStream(r1)
            L_0x002b:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.data.MediaStoreThumbFetcher.ThumbnailStreamOpener.open(android.content.Context, android.net.Uri):java.io.InputStream");
        }

        private Uri parseThumbUri(Cursor cursor) {
            String string = cursor.getString(0);
            if (!TextUtils.isEmpty(string)) {
                File file = this.service.get(string);
                if (this.service.exists(file) && this.service.length(file) > 0) {
                    return Uri.fromFile(file);
                }
            }
            return null;
        }
    }

    static class ImageThumbnailQuery implements ThumbnailQuery {
        private static final String[] PATH_PROJECTION = {"_data"};
        private static final String PATH_SELECTION = "kind = 1 AND image_id = ?";

        ImageThumbnailQuery() {
        }

        public Cursor queryPath(Context context, Uri uri) {
            String lastPathSegment = uri.getLastPathSegment();
            return context.getContentResolver().query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, PATH_PROJECTION, PATH_SELECTION, new String[]{lastPathSegment}, (String) null);
        }
    }

    static class VideoThumbnailQuery implements ThumbnailQuery {
        private static final String[] PATH_PROJECTION = {"_data"};
        private static final String PATH_SELECTION = "kind = 1 AND video_id = ?";

        VideoThumbnailQuery() {
        }

        public Cursor queryPath(Context context, Uri uri) {
            String lastPathSegment = uri.getLastPathSegment();
            return context.getContentResolver().query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, PATH_PROJECTION, PATH_SELECTION, new String[]{lastPathSegment}, (String) null);
        }
    }

    static class ThumbnailStreamOpenerFactory {
        ThumbnailStreamOpenerFactory() {
        }

        public ThumbnailStreamOpener build(Uri uri, int i, int i2) {
            if (!MediaStoreThumbFetcher.isMediaStoreUri(uri) || i > 512 || i2 > MediaStoreThumbFetcher.MINI_HEIGHT) {
                return null;
            }
            if (MediaStoreThumbFetcher.isMediaStoreVideo(uri)) {
                return new ThumbnailStreamOpener(new VideoThumbnailQuery());
            }
            return new ThumbnailStreamOpener(new ImageThumbnailQuery());
        }
    }
}
