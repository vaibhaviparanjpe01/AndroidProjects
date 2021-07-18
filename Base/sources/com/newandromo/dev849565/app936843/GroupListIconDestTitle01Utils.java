package com.newandromo.dev849565.app936843;

import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.TextView;
import com.newandromo.dev849565.app936843.databinding.GroupListIconDestTitle01Binding;

public final class GroupListIconDestTitle01Utils implements ItemBindingUtils {
    public static final String TAG = "GroupListIconDestTitle01Utils";

    public static TextView getDateViewFromBinding(GroupListIconDestTitle01Binding groupListIconDestTitle01Binding) {
        return null;
    }

    public static TextView getDescriptionViewFromBinding(GroupListIconDestTitle01Binding groupListIconDestTitle01Binding) {
        return null;
    }

    public static View getScrimViewFromBinding(GroupListIconDestTitle01Binding groupListIconDestTitle01Binding) {
        return null;
    }

    public static TextView getSubtitleViewFromBinding(GroupListIconDestTitle01Binding groupListIconDestTitle01Binding) {
        return null;
    }

    public ConstraintLayout getRootView(ViewDataBinding viewDataBinding) {
        if (viewDataBinding instanceof GroupListIconDestTitle01Binding) {
            return ((GroupListIconDestTitle01Binding) viewDataBinding).itemRoot;
        }
        return null;
    }

    public TintableImageView getImageView(ViewDataBinding viewDataBinding) {
        if (viewDataBinding instanceof GroupListIconDestTitle01Binding) {
            return ((GroupListIconDestTitle01Binding) viewDataBinding).itemImage;
        }
        return null;
    }

    public View getScrimView(ViewDataBinding viewDataBinding) {
        if (!(viewDataBinding instanceof GroupListIconDestTitle01Binding)) {
            return null;
        }
        GroupListIconDestTitle01Binding groupListIconDestTitle01Binding = (GroupListIconDestTitle01Binding) viewDataBinding;
        return null;
    }

    public TextView getTitleView(ViewDataBinding viewDataBinding) {
        if (viewDataBinding instanceof GroupListIconDestTitle01Binding) {
            return ((GroupListIconDestTitle01Binding) viewDataBinding).itemTitle;
        }
        return null;
    }

    public TextView getSubtitleView(ViewDataBinding viewDataBinding) {
        if (!(viewDataBinding instanceof GroupListIconDestTitle01Binding)) {
            return null;
        }
        GroupListIconDestTitle01Binding groupListIconDestTitle01Binding = (GroupListIconDestTitle01Binding) viewDataBinding;
        return null;
    }

    public TextView getDescriptionView(ViewDataBinding viewDataBinding) {
        if (!(viewDataBinding instanceof GroupListIconDestTitle01Binding)) {
            return null;
        }
        GroupListIconDestTitle01Binding groupListIconDestTitle01Binding = (GroupListIconDestTitle01Binding) viewDataBinding;
        return null;
    }

    public TextView getDateView(ViewDataBinding viewDataBinding) {
        if (!(viewDataBinding instanceof GroupListIconDestTitle01Binding)) {
            return null;
        }
        GroupListIconDestTitle01Binding groupListIconDestTitle01Binding = (GroupListIconDestTitle01Binding) viewDataBinding;
        return null;
    }

    public static ConstraintLayout getRootViewFromBinding(GroupListIconDestTitle01Binding groupListIconDestTitle01Binding) {
        return groupListIconDestTitle01Binding.itemRoot;
    }

    public static TintableImageView getImageViewFromBinding(GroupListIconDestTitle01Binding groupListIconDestTitle01Binding) {
        return groupListIconDestTitle01Binding.itemImage;
    }

    public static TextView getTitleViewFromBinding(GroupListIconDestTitle01Binding groupListIconDestTitle01Binding) {
        return groupListIconDestTitle01Binding.itemTitle;
    }
}
