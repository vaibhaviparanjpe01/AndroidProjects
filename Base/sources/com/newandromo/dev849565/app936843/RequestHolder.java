package com.newandromo.dev849565.app936843;

import android.support.annotation.NonNull;
import com.bumptech.glide.request.Request;

public interface RequestHolder {
    Request getRequest();

    void setRequest(@NonNull Request request);
}
