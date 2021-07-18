package com.newandromo.dev849565.app936843;

import android.view.View;
import android.view.animation.Animation;
import java.lang.ref.WeakReference;

final class SetVisibilityAnimationListener implements Animation.AnimationListener {
    final WeakReference<View> mView;
    final int mVisibility;

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
    }

    public SetVisibilityAnimationListener(View view, int i) {
        this.mView = new WeakReference<>(view);
        this.mVisibility = i;
    }

    public void onAnimationEnd(Animation animation) {
        View view = (View) this.mView.get();
        if (view != null) {
            view.setVisibility(this.mVisibility);
        }
    }
}
