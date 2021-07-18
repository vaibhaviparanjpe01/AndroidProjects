package com.newandromo.dev849565.app936843;

public class CompositeItemBinder<T> implements ItemBinder<T> {
    private final ConditionalDataBinder<T>[] conditionalDataBinders;

    public CompositeItemBinder(ConditionalDataBinder<T>... conditionalDataBinderArr) {
        this.conditionalDataBinders = conditionalDataBinderArr;
    }

    public int getLayoutResId(T t) {
        for (ConditionalDataBinder<T> conditionalDataBinder : this.conditionalDataBinders) {
            if (conditionalDataBinder.canHandle(t)) {
                return conditionalDataBinder.getLayoutResId(t);
            }
        }
        throw new IllegalStateException();
    }

    public int getBindingVariableId(T t) {
        for (ConditionalDataBinder<T> conditionalDataBinder : this.conditionalDataBinders) {
            if (conditionalDataBinder.canHandle(t)) {
                return conditionalDataBinder.getBindingVariableId(t);
            }
        }
        throw new IllegalStateException();
    }
}
