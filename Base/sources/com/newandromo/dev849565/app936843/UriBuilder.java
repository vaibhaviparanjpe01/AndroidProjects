package com.newandromo.dev849565.app936843;

import android.net.Uri;

public final class UriBuilder {
    public static final String TAG = "UriBuilder";
    private Uri.Builder builder;
    private boolean hasEmptyFragment;

    public UriBuilder() {
        this.builder = new Uri.Builder();
    }

    public UriBuilder(Uri uri) {
        if (uri != null) {
            String encodedFragment = uri.getEncodedFragment();
            this.hasEmptyFragment = encodedFragment != null && encodedFragment.isEmpty();
            this.builder = uri.buildUpon();
            return;
        }
        this.builder = new Uri.Builder();
    }

    public UriBuilder(String str) {
        if (str != null) {
            Uri parse = Uri.parse(str);
            if (parse != null) {
                String encodedFragment = parse.getEncodedFragment();
                this.hasEmptyFragment = encodedFragment != null && encodedFragment.isEmpty();
                this.builder = parse.buildUpon();
                return;
            }
            this.builder = new Uri.Builder();
            return;
        }
        this.builder = new Uri.Builder();
    }

    public static UriBuilder fromUri(Uri uri) {
        return new UriBuilder(uri);
    }

    public static UriBuilder fromString(String str) {
        return new UriBuilder(str);
    }

    public UriBuilder scheme(String str) {
        this.builder.scheme(str);
        return this;
    }

    public UriBuilder opaquePart(String str) {
        this.builder.opaquePart(str);
        return this;
    }

    public UriBuilder encodedOpaquePart(String str) {
        this.builder.encodedOpaquePart(str);
        return this;
    }

    public UriBuilder authority(String str) {
        this.builder.authority(str);
        return this;
    }

    public UriBuilder encodedAuthority(String str) {
        this.builder.encodedAuthority(str);
        return this;
    }

    public UriBuilder path(String str) {
        this.builder.path(str);
        return this;
    }

    public UriBuilder encodedPath(String str) {
        this.builder.encodedPath(str);
        return this;
    }

    public UriBuilder appendPath(String str) {
        this.builder.appendPath(str);
        return this;
    }

    public UriBuilder appendEncodedPath(String str) {
        this.builder.appendEncodedPath(str);
        return this;
    }

    public UriBuilder query(String str) {
        this.builder.query(str);
        return this;
    }

    public UriBuilder encodedQuery(String str) {
        this.builder.encodedQuery(str);
        return this;
    }

    public UriBuilder fragment(String str) {
        this.hasEmptyFragment = str != null && str.isEmpty();
        this.builder.fragment(str);
        return this;
    }

    public UriBuilder encodedFragment(String str) {
        this.hasEmptyFragment = str != null && str.isEmpty();
        this.builder.encodedFragment(str);
        return this;
    }

    public UriBuilder appendQueryParameter(String str, String str2) {
        this.builder.appendQueryParameter(str, str2);
        return this;
    }

    public UriBuilder clearQuery() {
        this.builder.clearQuery();
        return this;
    }

    public Uri build() {
        if (this.hasEmptyFragment) {
            return Uri.parse(toString());
        }
        return this.builder.build();
    }

    public String toString() {
        if (!this.hasEmptyFragment) {
            return this.builder.toString();
        }
        return this.builder.toString() + "#";
    }
}
