package com.newandromo.dev849565.app936843;

import android.view.View;
import android.view.animation.Animation;
import java.lang.ref.WeakReference;

final class AnimRunnable implements Runnable {
    private final Animation mAnim;
    private final WeakReference<View> mView;

    public AnimRunnable(View view, Animation animation) {
        this.mView = new WeakReference<>(view);
        this.mAnim = animation;
    }

    public void run() {
        View view = (View) this.mView.get();
        Animation animation = this.mAnim;
        if (view != null && animation != null && view.getVisibility() != 8) {
            view.startAnimation(animation);
        }
    }
}
