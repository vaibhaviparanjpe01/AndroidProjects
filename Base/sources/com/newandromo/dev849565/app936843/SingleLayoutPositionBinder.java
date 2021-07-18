package com.newandromo.dev849565.app936843;

public class SingleLayoutPositionBinder implements PositionBinder {
    protected final int layoutId;

    public int getBindingVariableId(int i) {
        return 9;
    }

    public SingleLayoutPositionBinder(int i) {
        this.layoutId = i;
    }

    public int getLayoutResId(int i) {
        return this.layoutId;
    }
}
