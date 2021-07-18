package com.newandromo.dev849565.app936843;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.target.ViewTarget;
import java.util.Locale;

public class LoggingListener<A, B> implements RequestListener<A, B> {
    private final RequestListener<A, B> delegate;
    private final int level;
    private final String name;

    private String isFirst(boolean z) {
        return z ? "first" : "not first";
    }

    private String isMem(boolean z) {
        return z ? "sync" : "async";
    }

    public LoggingListener() {
        this("");
    }

    public LoggingListener(@NonNull String str) {
        this(2, str);
    }

    public LoggingListener(int i, @NonNull String str) {
        this(i, str, (RequestListener) null);
    }

    public LoggingListener(RequestListener<A, B> requestListener) {
        this(2, "", requestListener);
    }

    public LoggingListener(int i, @NonNull String str, RequestListener<A, B> requestListener) {
        this.level = i;
        this.name = str;
        this.delegate = requestListener == null ? NoOpRequestListener.get() : requestListener;
    }

    public boolean onException(Exception exc, A a, Target<B> target, boolean z) {
        Log.println(this.level, "GLIDE", String.format(Locale.ROOT, "%s.onException(%s, %s, %s, %s)\n%s", new Object[]{this.name, exc, a, strip(target), isFirst(z), Log.getStackTraceString(exc)}));
        return this.delegate.onException(exc, a, target, z);
    }

    public boolean onResourceReady(B b, A a, Target<B> target, boolean z, boolean z2) {
        String strip = strip(getResourceDescription(b));
        String strip2 = strip(getTargetDescription(target));
        Log.println(this.level, "GLIDE", String.format(Locale.ROOT, "%s.onResourceReady(%s, %s, %s, %s, %s)", new Object[]{this.name, strip, a, strip2, isMem(z), isFirst(z2)}));
        return this.delegate.onResourceReady(b, a, target, z, z2);
    }

    private String getTargetDescription(Target<?> target) {
        if (target instanceof WrappingTarget) {
            Target wrappedTarget = ((WrappingTarget) target).getWrappedTarget();
            return String.format(Locale.ROOT, "%s in %s", new Object[]{getTargetDescription(wrappedTarget), target});
        } else if (!(target instanceof ViewTarget)) {
            return String.valueOf(target);
        } else {
            View view = ((ViewTarget) target).getView();
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            return String.format(Locale.ROOT, "%s(params=%dx%d->size=%dx%d)", new Object[]{target, Integer.valueOf(layoutParams.width), Integer.valueOf(layoutParams.height), Integer.valueOf(view.getWidth()), Integer.valueOf(view.getHeight())});
        }
    }

    private String getResourceDescription(B b) {
        if (b instanceof Bitmap) {
            Bitmap bitmap = (Bitmap) b;
            return String.format(Locale.ROOT, "%s(%dx%d@%s)", new Object[]{b, Integer.valueOf(bitmap.getWidth()), Integer.valueOf(bitmap.getHeight()), bitmap.getConfig()});
        } else if (b instanceof BitmapDrawable) {
            Bitmap bitmap2 = ((BitmapDrawable) b).getBitmap();
            return String.format(Locale.ROOT, "%s(%dx%d@%s)", new Object[]{b, Integer.valueOf(bitmap2.getWidth()), Integer.valueOf(bitmap2.getHeight()), bitmap2.getConfig()});
        } else if (b instanceof GlideBitmapDrawable) {
            Bitmap bitmap3 = ((GlideBitmapDrawable) b).getBitmap();
            return String.format(Locale.ROOT, "%s(%dx%d@%s)", new Object[]{b, Integer.valueOf(bitmap3.getWidth()), Integer.valueOf(bitmap3.getHeight()), bitmap3.getConfig()});
        } else if (!(b instanceof Drawable)) {
            return String.valueOf(b);
        } else {
            Drawable drawable = (Drawable) b;
            return String.format(Locale.ROOT, "%s(%dx%d)", new Object[]{b, Integer.valueOf(drawable.getIntrinsicWidth()), Integer.valueOf(drawable.getIntrinsicHeight())});
        }
    }

    private static String strip(Object obj) {
        return String.valueOf(obj).replaceAll("(com|android|net|org)(\\.[a-z]+)+\\.", "");
    }
}
