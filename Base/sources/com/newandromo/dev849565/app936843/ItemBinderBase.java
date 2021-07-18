package com.newandromo.dev849565.app936843;

public class ItemBinderBase<T> implements ItemBinder<T> {
    protected final int bindingVariableId;
    protected final int layoutId;

    public ItemBinderBase(int i, int i2) {
        this.bindingVariableId = i;
        this.layoutId = i2;
    }

    public int getLayoutResId(T t) {
        return this.layoutId;
    }

    public int getBindingVariableId(T t) {
        return this.bindingVariableId;
    }
}
