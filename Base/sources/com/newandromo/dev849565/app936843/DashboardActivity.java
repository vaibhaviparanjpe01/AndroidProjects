package com.newandromo.dev849565.app936843;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public abstract class DashboardActivity extends AndromoRecyclerActivity {
    private static final String TAG = "DashboardActivity";
    int mActivityIndex = -1;
    private ImageView mBackgroundImageView;

    /* access modifiers changed from: protected */
    public int getBackgroundImageDrawableId() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public abstract boolean getBackgroundImageEnabled();

    /* access modifiers changed from: protected */
    public abstract boolean getShowAdsOnDashboard();

    /* access modifiers changed from: protected */
    public boolean isDetailActivity() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean isHiddenActivity() {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract boolean isRootDashboard();

    /* access modifiers changed from: protected */
    public boolean isShareActionEnabled() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean isToolbarEnabled() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean isHamburgerEnabled() {
        if (isRootDashboard()) {
            return true;
        }
        return super.isHamburgerEnabled();
    }

    /* access modifiers changed from: package-private */
    public void setToolbarTitle(int i) {
        if (isRootDashboard()) {
            super.setToolbarTitle((int) R.string.app_name);
        } else {
            super.setToolbarTitle(i);
        }
    }

    /* access modifiers changed from: package-private */
    public void setToolbarTitle(CharSequence charSequence) {
        if (isRootDashboard()) {
            super.setToolbarTitle((int) R.string.app_name);
        } else {
            super.setToolbarTitle(charSequence);
        }
    }

    public void onCreate(Bundle bundle) {
        Bitmap decodeSampledBitmapFromResource;
        super.onCreate(bundle);
        if (isRootDashboard()) {
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setHomeButtonEnabled(false);
            }
        } else if (this.mActivityIndex == -1 && !isHiddenActivity()) {
            this.mActivityIndex = ActionBarUtils.getActivityIndexFromClassNamesArray((Context) this, getParentClassNamesArray());
        }
        if (getBackgroundImageEnabled() && getBackgroundImageDrawableId() != 0) {
            this.mBackgroundImageView = (ImageView) findViewById(R.id.background_image);
            if (!(this.mBackgroundImageView == null || (decodeSampledBitmapFromResource = ImageUtils.decodeSampledBitmapFromResource(getResources(), getBackgroundImageDrawableId(), 1024, 1024)) == null)) {
                this.mBackgroundImageView.setImageBitmap(decodeSampledBitmapFromResource);
            }
        }
        if (getShowAdsOnDashboard()) {
            AdManager.insertBannerAd((Activity) this, (ViewGroup) (LinearLayout) findViewById(R.id.contentAdLayout), false);
        }
        AdManager.hideInterstitialAd();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.mBackgroundImageView != null) {
            this.mBackgroundImageView.setImageDrawable((Drawable) null);
            this.mBackgroundImageView.setBackgroundDrawable((Drawable) null);
            this.mBackgroundImageView = null;
        }
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
        if (IntercomHelper.showInDashboard()) {
            IntercomHelper.showHelper(false);
        }
        super.onPause();
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        if (IntercomHelper.showInDashboard()) {
            IntercomHelper.showHelper(true);
        }
        super.onResume();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem add;
        MenuInflater menuInflater = getMenuInflater();
        if (menuInflater != null) {
            menuInflater.inflate(R.menu.dashboard_options_menu, menu);
        }
        if (isShareActionEnabled() && (add = menu.add(0, R.id.share, 0, R.string.options_menu_share)) != null) {
            add.setShowAsAction(2);
            if (add != null) {
                add.setShowAsAction(2);
                add.setIcon(R.drawable.ic_share_black_24dp);
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.share) {
            startActivity(IntentUtils.createAppShareIntent(this));
            return true;
        } else if (ActionBarUtils.handleDefaultMenuItems(this, menuItem) || super.onOptionsItemSelected(menuItem)) {
            return true;
        } else {
            return false;
        }
    }

    public void onDelayedAdLoad() {
        super.onDelayedAdLoad();
        if (getShowAdsOnDashboard()) {
            AdManager.insertBannerAd((Activity) this, (ViewGroup) (LinearLayout) findViewById(R.id.contentAdLayout), false);
        }
    }
}
