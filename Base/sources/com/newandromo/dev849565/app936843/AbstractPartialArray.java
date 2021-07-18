package com.newandromo.dev849565.app936843;

import java.util.Arrays;

public abstract class AbstractPartialArray<E> {
    private static final int DEFAULT_MAX_ELEMENTS = 32;
    private static final int DEFAULT_MIN_ELEMENTS = 8;
    private static final String TAG = "AbstractPartialArray";
    private E[] elements;
    private int itemCount;
    private final int maxElements;
    private final int minElements;
    private int[] positions;

    /* access modifiers changed from: protected */
    public abstract E[] createArray(int i);

    /* access modifiers changed from: protected */
    public abstract E createElement();

    /* access modifiers changed from: protected */
    public abstract void onReset(E e);

    public AbstractPartialArray() {
        this(8, 32);
    }

    public AbstractPartialArray(int i, int i2) {
        this.minElements = i;
        this.maxElements = i2;
    }

    private void expand(int i) {
        if (this.positions != null) {
            if (this.elements == null || this.elements.length < this.positions.length) {
                throw new IllegalStateException("positions.length > elements.length");
            }
            E[] createArray = createArray(i);
            int[] iArr = new int[i];
            Arrays.fill(iArr, -1);
            for (int i2 = 0; i2 < this.positions.length; i2++) {
                int i3 = this.positions[i2];
                if (i3 != -1) {
                    int i4 = i3 % i;
                    createArray[i4] = this.elements[i2];
                    iArr[i4] = i3;
                } else {
                    E e = this.elements[i2];
                    if (e != null) {
                        onReset(e);
                    }
                }
            }
            this.elements = createArray;
            this.positions = iArr;
        } else if (this.elements != null) {
            E[] createArray2 = createArray(i);
            System.arraycopy(this.elements, 0, createArray2, 0, this.elements.length);
            this.elements = createArray2;
        }
    }

    public void setItemCount(int i) {
        this.itemCount = i;
    }

    public void ensureCapacity(int i) {
        if (this.elements != null && i > this.elements.length) {
            expand(this.minElements * ((i / this.minElements) + 1));
        }
        if (this.elements != null) {
            return;
        }
        if (this.itemCount <= Math.max(i, this.maxElements)) {
            this.elements = createArray(Math.max(this.itemCount, i));
            this.positions = null;
            return;
        }
        int max = Math.max(i, this.minElements);
        this.elements = createArray(max);
        this.positions = new int[max];
        Arrays.fill(this.positions, -1);
    }

    public void ensureCapacity(int i, int i2) {
        if (this.elements != null && i > this.elements.length) {
            expand(this.minElements * ((i / this.minElements) + 1));
        }
        if (this.elements == null) {
            if (i2 <= Math.max(i, this.maxElements)) {
                this.elements = createArray(Math.max(i2, i));
                this.positions = null;
            } else {
                int max = Math.max(i, this.minElements);
                this.elements = createArray(max);
                this.positions = new int[max];
                Arrays.fill(this.positions, -1);
            }
        }
        this.itemCount = i2;
    }

    private int currentCapacity() {
        if (this.elements != null) {
            return this.elements.length;
        }
        return this.minElements;
    }

    public E get(int i) {
        ensureCapacity(currentCapacity(), this.itemCount);
        if (this.elements == null) {
            return null;
        }
        if (this.elements.length < this.itemCount) {
            int length = i % this.elements.length;
            if (this.elements[length] == null) {
                this.elements[length] = createElement();
            } else if (this.positions == null || this.positions[length] != i) {
                onReset(this.elements[length]);
            }
            if (this.positions == null) {
                return null;
            }
            this.positions[length] = i;
            return null;
        } else if (this.elements[i] != null) {
            return null;
        } else {
            this.elements[i] = createElement();
            return null;
        }
    }

    public void resetAll() {
        for (E e : this.elements) {
            if (e != null) {
                onReset(e);
            }
        }
        if (this.positions != null) {
            Arrays.fill(this.positions, -1);
        }
    }

    public void resetFrom(int i) {
        if (this.elements != null) {
            resetFrom(i, this.elements.length - i);
        }
    }

    public void resetFrom(int i, int i2) {
        if (this.positions != null) {
            int i3 = i2 + i;
            for (int i4 = 0; i4 < this.elements.length; i4++) {
                int i5 = this.positions[i4];
                if (i5 > i && i5 < i3) {
                    if (this.elements[i4] != null) {
                        onReset(this.elements[i4]);
                    }
                    this.positions[i4] = -1;
                }
            }
        } else if (this.elements.length >= i2) {
            int min = Math.min(i2 + i, this.elements.length);
            while (i < min) {
                if (this.elements[i] != null) {
                    onReset(this.elements[i]);
                }
                i++;
            }
        } else {
            resetAll();
        }
    }
}
