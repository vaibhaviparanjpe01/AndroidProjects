package com.newandromo.dev849565.app936843;

public class PositionBinderBase implements PositionBinder {
    protected final int bindingVariableId;
    protected final int layoutId;

    public PositionBinderBase(int i, int i2) {
        this.bindingVariableId = i;
        this.layoutId = i2;
    }

    public int getLayoutResId(int i) {
        return this.layoutId;
    }

    public int getBindingVariableId(int i) {
        return this.bindingVariableId;
    }
}
