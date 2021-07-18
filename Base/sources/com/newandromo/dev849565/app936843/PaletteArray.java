package com.newandromo.dev849565.app936843;

import android.support.v7.graphics.Palette;
import java.util.Arrays;

public class PaletteArray implements PaletteHolder {
    private static final String TAG = "PaletteArray";
    private int capacity;
    private Palette[] palettes;
    private int size;

    public PaletteArray() {
        this(10);
    }

    public PaletteArray(int i) {
        this.capacity = i;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !getClass().equals(obj.getClass())) {
            return false;
        }
        PaletteArray paletteArray = (PaletteArray) obj;
        if (!EqualityUtils.areEqual((long) this.size, (long) paletteArray.size) || !Arrays.equals(this.palettes, paletteArray.palettes)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return HashCodeUtils.hash(HashCodeUtils.hash(23, this.size), (Object) this.palettes);
    }

    public Palette get(int i) {
        if (i < 0 || i >= this.size || this.palettes == null) {
            return null;
        }
        return this.palettes[i];
    }

    public void set(int i, Palette palette) {
        if (i >= 0 && i < this.capacity) {
            if (this.palettes == null) {
                this.palettes = new Palette[this.capacity];
            }
            this.palettes[i] = palette;
            if (palette != null && i >= this.size) {
                this.size = i + 1;
            }
        }
    }

    public boolean contains(int i) {
        if (i < 0 || i >= this.size || this.palettes == null || this.palettes[i] == null) {
            return false;
        }
        return true;
    }

    public int size() {
        return this.size;
    }

    private void updateSize() {
        if (this.palettes != null) {
            while (this.size > 0 && this.palettes[this.size - 1] == null) {
                this.size--;
            }
        }
    }

    public void removeAt(int i) {
        if (i < this.size) {
            this.palettes[i] = null;
            if (i == this.size - 1) {
                this.size--;
            }
        }
    }

    public void removeAll() {
        if (this.palettes != null) {
            while (this.size > 0) {
                Palette[] paletteArr = this.palettes;
                int i = this.size - 1;
                this.size = i;
                paletteArr[i] = null;
            }
        }
    }

    public String toString() {
        String str = getClass().getName() + "{";
        for (int i = 0; i < this.size; i++) {
            if (i > 0) {
                str = str + ", ";
            }
            if (this.palettes[i] == null) {
                str = str + i + ":null";
            } else {
                str = str + i + ":" + this.palettes[i].toString();
            }
        }
        return str + "}";
    }
}
