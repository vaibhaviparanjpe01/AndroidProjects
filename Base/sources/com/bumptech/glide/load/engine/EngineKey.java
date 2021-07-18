package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

class EngineKey implements Key {
    private static final String EMPTY_LOG_STRING = "";
    private final ResourceDecoder cacheDecoder;
    private final ResourceDecoder decoder;
    private final ResourceEncoder encoder;
    private int hashCode;
    private final int height;
    private final String id;
    private Key originalKey;
    private final Key signature;
    private final Encoder sourceEncoder;
    private String stringKey;
    private final ResourceTranscoder transcoder;
    private final Transformation transformation;
    private final int width;

    public EngineKey(String str, Key key, int i, int i2, ResourceDecoder resourceDecoder, ResourceDecoder resourceDecoder2, Transformation transformation2, ResourceEncoder resourceEncoder, ResourceTranscoder resourceTranscoder, Encoder encoder2) {
        this.id = str;
        this.signature = key;
        this.width = i;
        this.height = i2;
        this.cacheDecoder = resourceDecoder;
        this.decoder = resourceDecoder2;
        this.transformation = transformation2;
        this.encoder = resourceEncoder;
        this.transcoder = resourceTranscoder;
        this.sourceEncoder = encoder2;
    }

    public Key getOriginalKey() {
        if (this.originalKey == null) {
            this.originalKey = new OriginalKey(this.id, this.signature);
        }
        return this.originalKey;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EngineKey engineKey = (EngineKey) obj;
        if (!this.id.equals(engineKey.id) || !this.signature.equals(engineKey.signature) || this.height != engineKey.height || this.width != engineKey.width) {
            return false;
        }
        if ((this.transformation == null) ^ (engineKey.transformation == null)) {
            return false;
        }
        if (this.transformation != null && !this.transformation.getId().equals(engineKey.transformation.getId())) {
            return false;
        }
        if ((this.decoder == null) ^ (engineKey.decoder == null)) {
            return false;
        }
        if (this.decoder != null && !this.decoder.getId().equals(engineKey.decoder.getId())) {
            return false;
        }
        if ((this.cacheDecoder == null) ^ (engineKey.cacheDecoder == null)) {
            return false;
        }
        if (this.cacheDecoder != null && !this.cacheDecoder.getId().equals(engineKey.cacheDecoder.getId())) {
            return false;
        }
        if ((this.encoder == null) ^ (engineKey.encoder == null)) {
            return false;
        }
        if (this.encoder != null && !this.encoder.getId().equals(engineKey.encoder.getId())) {
            return false;
        }
        if ((this.transcoder == null) ^ (engineKey.transcoder == null)) {
            return false;
        }
        if (this.transcoder != null && !this.transcoder.getId().equals(engineKey.transcoder.getId())) {
            return false;
        }
        if ((this.sourceEncoder == null) ^ (engineKey.sourceEncoder == null)) {
            return false;
        }
        return this.sourceEncoder == null || this.sourceEncoder.getId().equals(engineKey.sourceEncoder.getId());
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = this.id.hashCode();
            this.hashCode = (this.hashCode * 31) + this.signature.hashCode();
            this.hashCode = (this.hashCode * 31) + this.width;
            this.hashCode = (this.hashCode * 31) + this.height;
            int i = 0;
            this.hashCode = (this.hashCode * 31) + (this.cacheDecoder != null ? this.cacheDecoder.getId().hashCode() : 0);
            this.hashCode = (this.hashCode * 31) + (this.decoder != null ? this.decoder.getId().hashCode() : 0);
            this.hashCode = (this.hashCode * 31) + (this.transformation != null ? this.transformation.getId().hashCode() : 0);
            this.hashCode = (this.hashCode * 31) + (this.encoder != null ? this.encoder.getId().hashCode() : 0);
            this.hashCode = (this.hashCode * 31) + (this.transcoder != null ? this.transcoder.getId().hashCode() : 0);
            int i2 = this.hashCode * 31;
            if (this.sourceEncoder != null) {
                i = this.sourceEncoder.getId().hashCode();
            }
            this.hashCode = i2 + i;
        }
        return this.hashCode;
    }

    public String toString() {
        if (this.stringKey == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("EngineKey{");
            sb.append(this.id);
            sb.append('+');
            sb.append(this.signature);
            sb.append("+[");
            sb.append(this.width);
            sb.append('x');
            sb.append(this.height);
            sb.append("]+");
            sb.append('\'');
            sb.append(this.cacheDecoder != null ? this.cacheDecoder.getId() : "");
            sb.append('\'');
            sb.append('+');
            sb.append('\'');
            sb.append(this.decoder != null ? this.decoder.getId() : "");
            sb.append('\'');
            sb.append('+');
            sb.append('\'');
            sb.append(this.transformation != null ? this.transformation.getId() : "");
            sb.append('\'');
            sb.append('+');
            sb.append('\'');
            sb.append(this.encoder != null ? this.encoder.getId() : "");
            sb.append('\'');
            sb.append('+');
            sb.append('\'');
            sb.append(this.transcoder != null ? this.transcoder.getId() : "");
            sb.append('\'');
            sb.append('+');
            sb.append('\'');
            sb.append(this.sourceEncoder != null ? this.sourceEncoder.getId() : "");
            sb.append('\'');
            sb.append('}');
            this.stringKey = sb.toString();
        }
        return this.stringKey;
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) throws UnsupportedEncodingException {
        byte[] array = ByteBuffer.allocate(8).putInt(this.width).putInt(this.height).array();
        this.signature.updateDiskCacheKey(messageDigest);
        messageDigest.update(this.id.getBytes(Key.STRING_CHARSET_NAME));
        messageDigest.update(array);
        messageDigest.update((this.cacheDecoder != null ? this.cacheDecoder.getId() : "").getBytes(Key.STRING_CHARSET_NAME));
        messageDigest.update((this.decoder != null ? this.decoder.getId() : "").getBytes(Key.STRING_CHARSET_NAME));
        messageDigest.update((this.transformation != null ? this.transformation.getId() : "").getBytes(Key.STRING_CHARSET_NAME));
        messageDigest.update((this.encoder != null ? this.encoder.getId() : "").getBytes(Key.STRING_CHARSET_NAME));
        messageDigest.update((this.sourceEncoder != null ? this.sourceEncoder.getId() : "").getBytes(Key.STRING_CHARSET_NAME));
    }
}
