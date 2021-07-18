package com.newandromo.dev849565.app936843;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.newandromo.dev849565.app936843.AndromoActivity;

public class About_1601128430_b794eb9e4a extends AndromoActivity {
    private static final String TAG = "AboutUs";
    private static AndromoActivity.ParentCheck mParentCheck = new AndromoActivity.ParentCheck();
    int mActivityIndex = -1;
    private long mChannelID;
    private int position = -1;

    /* access modifiers changed from: protected */
    public String getActivityTypeForAnalytics() {
        return "About";
    }

    /* access modifiers changed from: protected */
    public boolean getHandleCustomWindowColor() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean isDetailActivity() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean isToolbarEnabled() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean isParentReachable() {
        return mParentCheck.isParentReachable(this, "material");
    }

    public String[] getParentClassNamesArray() {
        return getResources().getStringArray(R.array.activity_000_classes);
    }

    /* access modifiers changed from: protected */
    public String getActivityTitleForAnalytics() {
        return getString(R.string.About_1601128430_b794eb9e4a_activity_title);
    }

    /* access modifiers changed from: protected */
    public void setContentView() {
        setContentView((int) R.layout.about_us_main);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ImageUtils.getMaxTextureSizeNextOnDraw(this);
        if (this.mActivityIndex == -1) {
            this.mActivityIndex = ActionBarUtils.getActivityIndexFromClassNamesArray((Context) this, (int) R.array.activity_000_classes);
        }
        setToolbarTitle((int) R.string.About_1601128430_b794eb9e4a_activity_title);
        TextView textView = (TextView) findViewById(R.id.name);
        if (textView != null) {
            String string = getString(R.string.About_1601128430_b794eb9e4a_name);
            if (!string.equals("")) {
                textView.setText(string);
                textView.setVisibility(0);
            }
        }
        TextView textView2 = (TextView) findViewById(R.id.title);
        if (textView2 != null) {
            String string2 = getString(R.string.About_1601128430_b794eb9e4a_title);
            if (!string2.equals("")) {
                textView2.setText(string2);
                textView2.setVisibility(0);
            }
        }
        TextView textView3 = (TextView) findViewById(R.id.description);
        if (textView3 != null) {
            String string3 = getString(R.string.About_1601128430_b794eb9e4a_description);
            if (!string3.equals("")) {
                textView3.setText(Html.fromHtml(string3));
                textView3.setVisibility(0);
            }
        }
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.buttonLayout);
        if (relativeLayout != null) {
            ImageView imageView = (ImageView) findViewById(R.id.aboutUsFacebook);
            if (imageView != null) {
                final String string4 = getString(R.string.About_1601128430_b794eb9e4a_facebook);
                if (!string4.equals("")) {
                    relativeLayout.setVisibility(0);
                    imageView.setVisibility(0);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            About_1601128430_b794eb9e4a.this.onButtonClick(string4);
                        }
                    });
                }
            }
            ImageView imageView2 = (ImageView) findViewById(R.id.aboutUsTwitter);
            if (imageView2 != null) {
                final String string5 = getString(R.string.About_1601128430_b794eb9e4a_twitter);
                if (!string5.equals("")) {
                    relativeLayout.setVisibility(0);
                    imageView2.setVisibility(0);
                    imageView2.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            About_1601128430_b794eb9e4a.this.onButtonClick(string5);
                        }
                    });
                }
            }
            ImageView imageView3 = (ImageView) findViewById(R.id.aboutUsLinkedIn);
            if (imageView3 != null) {
                final String string6 = getString(R.string.About_1601128430_b794eb9e4a_linkedin);
                if (!string6.equals("")) {
                    relativeLayout.setVisibility(0);
                    imageView3.setVisibility(0);
                    imageView3.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            About_1601128430_b794eb9e4a.this.onButtonClick(string6);
                        }
                    });
                }
            }
            ImageView imageView4 = (ImageView) findViewById(R.id.aboutUsGooglePlus);
            if (imageView4 != null) {
                final String string7 = getString(R.string.About_1601128430_b794eb9e4a_googleplus);
                if (!string7.equals("")) {
                    relativeLayout.setVisibility(0);
                    imageView4.setVisibility(0);
                    imageView4.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            About_1601128430_b794eb9e4a.this.onButtonClick(string7);
                        }
                    });
                }
            }
            ImageView imageView5 = (ImageView) findViewById(R.id.aboutUsWebsite);
            if (imageView5 != null) {
                final String string8 = getString(R.string.About_1601128430_b794eb9e4a_website);
                if (!string8.equals("")) {
                    relativeLayout.setVisibility(0);
                    imageView5.setVisibility(0);
                    imageView5.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            About_1601128430_b794eb9e4a.this.onButtonClick(string8);
                        }
                    });
                }
            }
        }
        AdManager.insertBannerAd((Activity) this, (ViewGroup) (LinearLayout) findViewById(R.id.contentAdLayout), false);
    }

    public void onButtonClick(String str) {
        if (!IntentUtils.isAndromoProtocol(this, str)) {
            try {
                IntentUtils.openUrlInDefaultBrowserWithFallback(this, str);
            } catch (ActivityNotFoundException unused) {
                Toast.makeText(this, R.string.no_app_found_to_open_url, 0).show();
            }
        } else if (!IntentUtils.invokeActivityAndromoProtocol((Context) this, str)) {
            Toast.makeText(this, getString(R.string.failed_to_open_url, new Object[]{str}), 0).show();
        }
    }

    private Bitmap scaleBitmap(Bitmap bitmap, float f) {
        return Bitmap.createScaledBitmap(bitmap, (int) (((float) bitmap.getWidth()) * f), (int) (((float) bitmap.getHeight()) * f), true);
    }

    private float getBitmapScalingFactor(Bitmap bitmap, int i) {
        return ((float) i) / ((float) bitmap.getWidth());
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return super.onOptionsItemSelected(menuItem);
    }

    public void onDelayedAdLoad() {
        super.onDelayedAdLoad();
        AdManager.insertBannerAd((Activity) this, (ViewGroup) (LinearLayout) findViewById(R.id.contentAdLayout), false);
    }
}
