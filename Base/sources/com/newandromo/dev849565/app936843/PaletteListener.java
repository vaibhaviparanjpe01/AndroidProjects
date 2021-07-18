package com.newandromo.dev849565.app936843;

import android.support.v7.graphics.Palette;
import android.view.View;

public interface PaletteListener {
    void onPaletteReady(Palette palette);

    void onPaletteReady(Palette palette, View view, int i);
}
