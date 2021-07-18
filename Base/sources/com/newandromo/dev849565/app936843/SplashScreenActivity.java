package com.newandromo.dev849565.app936843;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import java.util.Random;

public class SplashScreenActivity extends Activity {
    /* access modifiers changed from: private */
    public static SplashScreenActivity instance;
    private final String TAG = "SplashScreenActivity";
    final String assetName = "";
    final int circleWidth = 0;
    final String color = "";
    /* access modifiers changed from: private */
    public Handler handler;
    final int iconWidth = 0;
    final long maxTime = 0;
    final long minTime = 0;
    private Runnable navRunnable = new Runnable() {
        public void run() {
            if (SplashScreenActivity.instance == null) {
                try {
                    SplashScreenActivity splashScreenActivity = SplashScreenActivity.this;
                    Intent intent = new Intent(splashScreenActivity, Class.forName(SplashScreenActivity.this.getPackageName() + "." + ""));
                    intent.addCategory("android.intent.category.LAUNCHER");
                    SplashScreenActivity.this.startActivity(intent);
                } catch (Exception unused) {
                }
            } else {
                SplashScreenActivity unused2 = SplashScreenActivity.instance = null;
                AdManager.showInterstitialAndRun(SplashScreenActivity.this, new Runnable() {
                    public void run() {
                        try {
                            SplashScreenActivity splashScreenActivity = SplashScreenActivity.this;
                            Intent intent = new Intent(splashScreenActivity, Class.forName(SplashScreenActivity.this.getPackageName() + "." + ""));
                            intent.addCategory("android.intent.category.LAUNCHER");
                            SplashScreenActivity.this.startActivity(intent);
                        } catch (Exception unused) {
                        }
                        SplashScreenActivity.this.handler.post(new Runnable() {
                            public void run() {
                                SplashScreenActivity.this.finish();
                            }
                        });
                    }
                });
            }
        }
    };
    private boolean navRunnableSet = false;
    final String returnActivity = "";
    private long startTime;
    final int xIconPercent = 0;
    final int xPercent = 0;
    final int yIconPercent = 0;
    final int yPercent = 0;

    public static void setNewTime(long j, long j2) {
        if (instance != null) {
            instance.setTime(j, j2);
        }
    }

    private void setTime(long j, long j2) {
        long max = Math.max(0, randLong(j, j2) - (System.currentTimeMillis() - this.startTime));
        this.handler.removeCallbacks(this.navRunnable);
        this.handler.postDelayed(this.navRunnable, max);
        this.navRunnableSet = true;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        instance = null;
        AdManager.onActivityDestroyed(this);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        AdManager.onActivityStarted(this);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        instance = this;
        this.handler = new Handler();
        AdManager.onActivityCreated(this);
        hideSystemUI();
        setContentView(R.layout.splash);
        final ImageView imageView = (ImageView) findViewById(R.id.splash_view);
        final ImageView imageView2 = (ImageView) findViewById(R.id.splash_icon_holder);
        try {
            imageView.setImageBitmap(BitmapFactory.decodeStream(getAssets().open("")));
            imageView2.setVisibility(8);
        } catch (Exception unused) {
        }
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.splash_loading_circle);
        progressBar.post(new Runnable() {
            public void run() {
                progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor(""), PorterDuff.Mode.MULTIPLY);
                Object[] bitmapPositionInsideImageView = SplashScreenActivity.this.getBitmapPositionInsideImageView(imageView);
                int convertPos = SplashScreenActivity.this.convertPos(((Float) bitmapPositionInsideImageView[6]).floatValue(), 0, ((Integer) bitmapPositionInsideImageView[0]).intValue(), ((Integer) bitmapPositionInsideImageView[8]).intValue());
                int convertPos2 = SplashScreenActivity.this.convertPos(((Float) bitmapPositionInsideImageView[7]).floatValue(), 0, ((Integer) bitmapPositionInsideImageView[1]).intValue(), ((Integer) bitmapPositionInsideImageView[9]).intValue());
                int round = Math.round(((Float) bitmapPositionInsideImageView[6]).floatValue() * 0.0f);
                progressBar.setX((float) convertPos);
                progressBar.setY((float) convertPos2);
                ViewGroup.LayoutParams layoutParams = progressBar.getLayoutParams();
                layoutParams.height = round;
                layoutParams.width = round;
                progressBar.setLayoutParams(layoutParams);
            }
        });
        this.startTime = System.currentTimeMillis();
        if (!this.navRunnableSet) {
            this.navRunnableSet = true;
            this.handler.postDelayed(this.navRunnable, randLong(0, 0));
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        instance = null;
        showSystemUI();
    }

    private void showSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(1792);
    }

    private void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(3846);
    }

    private long randLong(long j, long j2) {
        return (j == j2 || j2 == 0) ? j2 : (long) (new Random().nextInt(((int) (j2 - j)) + 1) + ((int) j));
    }

    public Object[] getBitmapPositionInsideImageView(ImageView imageView) {
        Object[] objArr = new Object[10];
        if (imageView == null || imageView.getDrawable() == null) {
            return null;
        }
        float[] fArr = new float[9];
        imageView.getImageMatrix().getValues(fArr);
        float f = fArr[0];
        float f2 = fArr[4];
        objArr[6] = Float.valueOf(f);
        objArr[7] = Float.valueOf(f2);
        Drawable drawable = imageView.getDrawable();
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        objArr[4] = Integer.valueOf(intrinsicWidth);
        objArr[5] = Integer.valueOf(intrinsicHeight);
        int round = Math.round(((float) intrinsicWidth) * f);
        int round2 = Math.round(((float) intrinsicHeight) * f2);
        objArr[2] = Integer.valueOf(round);
        objArr[3] = Integer.valueOf(round2);
        int width = imageView.getWidth();
        int height = imageView.getHeight();
        objArr[0] = Integer.valueOf((width - round) / 2);
        objArr[1] = Integer.valueOf((height - round2) / 2);
        objArr[8] = Integer.valueOf(width);
        objArr[9] = Integer.valueOf(height);
        return objArr;
    }

    public int convertPos(float f, int i, int i2, int i3) {
        int round = Math.round(f * ((float) i)) + i2;
        if (round < 0) {
            return 0;
        }
        return round > i3 ? i3 : round;
    }
}
