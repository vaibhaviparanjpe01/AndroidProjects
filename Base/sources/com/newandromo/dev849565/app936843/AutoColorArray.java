package com.newandromo.dev849565.app936843;

import java.util.Arrays;

public class AutoColorArray implements AutoColorHolder {
    private static final String TAG = "AutoColorArray";
    private AutoColor[] autoColors;
    private int capacity;
    private int size;

    public AutoColorArray() {
        this(10);
    }

    public AutoColorArray(int i) {
        this.capacity = i;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !getClass().equals(obj.getClass())) {
            return false;
        }
        AutoColorArray autoColorArray = (AutoColorArray) obj;
        if (!EqualityUtils.areEqual((long) this.size, (long) autoColorArray.size) || !Arrays.equals(this.autoColors, autoColorArray.autoColors)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return HashCodeUtils.hash(HashCodeUtils.hash(23, this.size), (Object) this.autoColors);
    }

    public AutoColor get(int i) {
        if (i < 0 || i >= this.size || this.autoColors == null) {
            return null;
        }
        return this.autoColors[i];
    }

    public void set(int i, AutoColor autoColor) {
        if (i >= 0 && i < this.capacity) {
            if (this.autoColors == null) {
                this.autoColors = new AutoColor[this.capacity];
            }
            this.autoColors[i] = autoColor;
            if (autoColor != null && i >= this.size) {
                this.size = i + 1;
            }
        }
    }

    public boolean contains(int i) {
        if (i < 0 || i >= this.size || this.autoColors == null || this.autoColors[i] == null) {
            return false;
        }
        return true;
    }

    public int find(AutoColor autoColor) {
        return find(autoColor, 0);
    }

    public int find(AutoColor autoColor, int i) {
        if (this.autoColors == null) {
            return -1;
        }
        if (autoColor != null) {
            while (i < this.size) {
                if (autoColor.equals(this.autoColors[i])) {
                    return i;
                }
                i++;
            }
            return -1;
        }
        while (i < this.size) {
            if (this.autoColors[i] == null) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public int size() {
        return this.size;
    }

    private void updateSize() {
        if (this.autoColors != null) {
            while (this.size > 0 && this.autoColors[this.size - 1] == null) {
                this.size--;
            }
        }
    }

    public void removeAt(int i) {
        if (i < this.size) {
            this.autoColors[i] = null;
            if (i == this.size - 1) {
                this.size--;
            }
        }
    }

    public void removeAll() {
        if (this.autoColors != null) {
            while (this.size > 0) {
                AutoColor[] autoColorArr = this.autoColors;
                int i = this.size - 1;
                this.size = i;
                autoColorArr[i] = null;
            }
        }
    }

    public String toString() {
        String str = getClass().getName() + "{";
        for (int i = 0; i < this.size; i++) {
            if (i > 0) {
                str = str + ", ";
            }
            if (this.autoColors[i] == null) {
                str = str + i + ":null";
            } else {
                str = str + i + ":" + this.autoColors[i].toString();
            }
        }
        return str + "}";
    }
}
