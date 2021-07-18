package com.newandromo.dev849565.app936843;

public abstract class ConditionalDataBinder<T> extends ItemBinderBase<T> {
    public abstract boolean canHandle(T t);

    public ConditionalDataBinder(int i, int i2) {
        super(i, i2);
    }

    public boolean canHandle(int i) {
        return this.layoutId == i;
    }
}
