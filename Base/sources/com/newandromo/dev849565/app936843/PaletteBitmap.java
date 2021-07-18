package com.newandromo.dev849565.app936843;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.graphics.Palette;

public class PaletteBitmap {
    public final Bitmap bitmap;
    public final Palette palette;

    public PaletteBitmap(@NonNull Bitmap bitmap2, Palette palette2) {
        this.bitmap = bitmap2;
        this.palette = palette2;
    }
}
