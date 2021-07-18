package com.newandromo.dev849565.app936843;

import android.databinding.ViewDataBinding;
import android.view.View;

public interface ItemBindingInfo {
    View getItemDateViewFromBinding(ViewDataBinding viewDataBinding);

    View getItemDescriptionViewFromBinding(ViewDataBinding viewDataBinding);

    View getItemImageViewFromBinding(ViewDataBinding viewDataBinding);

    View getItemRootViewFromBinding(ViewDataBinding viewDataBinding);

    View getItemScrimViewFromBinding(ViewDataBinding viewDataBinding);

    View getItemSubtitleViewFromBinding(ViewDataBinding viewDataBinding);

    View getItemTitleViewFromBinding(ViewDataBinding viewDataBinding);

    View getViewFromBinding(ViewDataBinding viewDataBinding, int i);
}
