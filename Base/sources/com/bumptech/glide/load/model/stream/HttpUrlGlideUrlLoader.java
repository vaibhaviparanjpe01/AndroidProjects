package com.bumptech.glide.load.model.stream;

import android.content.Context;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.data.HttpUrlFetcher;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelCache;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.newandromo.dev849565.app936843.AdHelper;
import java.io.InputStream;

public class HttpUrlGlideUrlLoader implements StreamModelLoader<GlideUrl> {
    private final ModelCache<GlideUrl, GlideUrl> modelCache;

    public static class Factory implements ModelLoaderFactory<GlideUrl, InputStream> {
        private final ModelCache<GlideUrl, GlideUrl> modelCache = new ModelCache<>(AdHelper.MIN_SCREEN_HEIGHT);

        public void teardown() {
        }

        public ModelLoader<GlideUrl, InputStream> build(Context context, GenericLoaderFactory genericLoaderFactory) {
            return new HttpUrlGlideUrlLoader(this.modelCache);
        }
    }

    public HttpUrlGlideUrlLoader() {
        this((ModelCache<GlideUrl, GlideUrl>) null);
    }

    public HttpUrlGlideUrlLoader(ModelCache<GlideUrl, GlideUrl> modelCache2) {
        this.modelCache = modelCache2;
    }

    public DataFetcher<InputStream> getResourceFetcher(GlideUrl glideUrl, int i, int i2) {
        if (this.modelCache != null) {
            GlideUrl glideUrl2 = this.modelCache.get(glideUrl, 0, 0);
            if (glideUrl2 == null) {
                this.modelCache.put(glideUrl, 0, 0, glideUrl);
            } else {
                glideUrl = glideUrl2;
            }
        }
        return new HttpUrlFetcher(glideUrl);
    }
}
