package com.newandromo.dev849565.app936843;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.io.IOException;
import java.io.InputStream;

public final class ImageUtils {
    private static final int DEFAULT_MAX_BITMAP_DIMENSION = 2048;
    private static final String TAG = "ImageUtils";
    private static int maxBitmapHeight;
    private static int maxBitmapWidth;

    public static int getMaxBitmapWidth() {
        if (maxBitmapWidth > 0) {
            return maxBitmapWidth;
        }
        return 2048;
    }

    public static int getMaxBitmapHeight() {
        if (maxBitmapHeight > 0) {
            return maxBitmapHeight;
        }
        return 2048;
    }

    public static void setMaxBitmapWidth(int i) {
        maxBitmapWidth = i;
    }

    public static void setMaxBitmapHeight(int i) {
        maxBitmapHeight = i;
    }

    public static boolean isMaxBitmapKnown() {
        return maxBitmapWidth > 0 && maxBitmapHeight > 0;
    }

    public static void determineMaxTextureSize(Activity activity) {
        int maxTextureSize = TextureSize.getMaxTextureSize();
        if (maxTextureSize > 0) {
            maxBitmapHeight = maxTextureSize;
            maxBitmapWidth = maxTextureSize;
            return;
        }
        getMaxTextureSizeNextOnDraw(activity);
    }

    public static void getMaxTextureSizeNextOnDraw(Activity activity) {
        ViewGroup viewGroup;
        if (Build.VERSION.SDK_INT < 14) {
            maxBitmapWidth = 2048;
            maxBitmapHeight = 2048;
        } else if (!isMaxBitmapKnown() && (viewGroup = (ViewGroup) activity.findViewById(16908290)) != null) {
            viewGroup.addView(new MaxTextureSizeView(activity));
        }
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int maxBitmapWidth2 = getMaxBitmapWidth();
        int maxBitmapHeight2 = getMaxBitmapHeight();
        if (i > maxBitmapWidth2) {
            i = maxBitmapWidth2;
        }
        int i5 = 1;
        if (i2 > maxBitmapHeight2) {
            i2 = maxBitmapHeight2;
        }
        while (true) {
            if (i3 / i5 <= i2 && i4 / i5 <= i) {
                return i5;
            }
            i5 *= 2;
        }
    }

    private static void getMaxTextureSizeUsingOpenGL() {
        int maxTextureSize = TextureSize.getMaxTextureSize();
        if (maxTextureSize > 0) {
            maxBitmapHeight = maxTextureSize;
            maxBitmapWidth = maxTextureSize;
        }
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources resources, int i) {
        if (!isMaxBitmapKnown()) {
            getMaxTextureSizeUsingOpenGL();
        }
        return decodeSampledBitmapFromResource(resources, i, getMaxBitmapWidth(), getMaxBitmapHeight());
    }

    public static Bitmap decodeSampledBitmapFromInputStream(InputStream inputStream) {
        if (!isMaxBitmapKnown()) {
            getMaxTextureSizeUsingOpenGL();
        }
        return decodeSampledBitmapFromInputStream(inputStream, getMaxBitmapWidth(), getMaxBitmapHeight());
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources resources, int i, int i2, int i3) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, i, options);
        options.inSampleSize = calculateInSampleSize(options, i2, i3);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, i, options);
    }

    public static Bitmap decodeSampledBitmapFromInputStream(InputStream inputStream, int i, int i2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, (Rect) null, options);
        options.inSampleSize = calculateInSampleSize(options, i, i2);
        options.inJustDecodeBounds = false;
        try {
            inputStream.reset();
        } catch (IOException unused) {
        }
        return BitmapFactory.decodeStream(inputStream, (Rect) null, options);
    }

    public static void setImageToSampledBitmapFromResource(ImageView imageView, Resources resources, int i, int i2, int i3) {
        Bitmap decodeSampledBitmapFromResource;
        if (imageView != null && (decodeSampledBitmapFromResource = decodeSampledBitmapFromResource(resources, i, i2, i3)) != null) {
            imageView.setImageBitmap(decodeSampledBitmapFromResource);
        }
    }

    public static Drawable getTintedDrawable(Resources resources, int i, int i2) {
        Drawable drawable = resources.getDrawable(i);
        drawable.setColorFilter(i2, PorterDuff.Mode.SRC_IN);
        return drawable;
    }

    public static Bitmap getSmallerBitmapIfNeeded(Bitmap bitmap, int i) {
        return getSmallerBitmapIfNeeded(bitmap, i, i);
    }

    public static Bitmap getSmallerBitmapIfNeeded(Bitmap bitmap, int i, int i2) {
        double d;
        int width = bitmap.getWidth() * bitmap.getHeight();
        if (width > i) {
            double d2 = (double) i2;
            double d3 = (double) width;
            Double.isNaN(d2);
            Double.isNaN(d3);
            d = d2 / d3;
        } else {
            d = -1.0d;
        }
        if (d <= 0.0d) {
            return bitmap;
        }
        double width2 = (double) bitmap.getWidth();
        Double.isNaN(width2);
        double height = (double) bitmap.getHeight();
        Double.isNaN(height);
        return Bitmap.createScaledBitmap(bitmap, (int) Math.ceil(width2 * d), (int) Math.ceil(height * d), false);
    }

    public static float getImageDarkness(Bitmap bitmap) {
        if (bitmap != null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int[] iArr = new int[(width * height)];
            bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
            int i = 0;
            int i2 = 0;
            for (int i3 : iArr) {
                int i4 = (i3 >> 16) & 255;
                int i5 = (i3 >> 8) & 255;
                int i6 = i3 & 255;
                double d = (double) (i4 * i4);
                Double.isNaN(d);
                double d2 = (double) (i5 * i5);
                Double.isNaN(d2);
                double d3 = (double) (i6 * i6);
                Double.isNaN(d3);
                if ((d * 0.241d) + (d2 * 0.691d) + (d3 * 0.068d) < 16900.0d) {
                    i++;
                } else {
                    i2++;
                }
            }
            if (bitmap != bitmap) {
                bitmap.recycle();
            }
            int i7 = i2 + i;
            if (i7 > 0) {
                return ((float) i) / ((float) i7);
            }
        }
        return 0.0f;
    }
}
