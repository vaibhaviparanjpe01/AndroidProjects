package com.newandromo.dev849565.app936843;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.databinding.ViewDataBinding;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.Pair;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ImageView;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.model.stream.StreamStringLoader;
import com.bumptech.glide.load.resource.bitmap.Downsampler;
import com.bumptech.glide.load.resource.bitmap.StreamBitmapDecoder;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.ApplicationVersionSignature;
import com.bumptech.glide.signature.EmptySignature;
import java.io.InputStream;

public abstract class AndromoActivity extends AppCompatActivity implements ItemBindingInfo, PaletteListener, NavigationView.OnNavigationItemSelectedListener {
    public static final String CLOSE_NAV_DRAWER = "CLOSE_NAV_DRAWER";
    public static final int LAYOUT_MANAGER_ADAPTIVE_GRID = 2;
    public static final int LAYOUT_MANAGER_COLUMN_GRID = 1;
    public static final int LAYOUT_MANAGER_GRID = 1;
    public static final int LAYOUT_MANAGER_LINEAR = 0;
    static final int MAX_FEATURE_WIDTH = 1440;
    private static final String TAG = "AndromoActivity";
    public static final int TITLE_MODE_APP_NAME = 1;
    public static final int TITLE_MODE_NONE = 0;
    public static final int TITLE_MODE_TEXT = 2;
    private static long buildTime = 1601128658049L;
    private static AlertDialog dialog = null;
    private static boolean isTrial = true;
    public static boolean showConsent = false;
    private static long trialPeriod = 604800000;
    protected boolean bSharedElementTransitionEnabled = false;
    private int backgroundImageTintColor = 0;
    protected CollapsingToolbarLayout collapsingToolbar;
    /* access modifiers changed from: private */
    public ColorFilterTarget colorFilterTarget;
    private boolean colorizeToolbar;
    private int customBackgroundImageTintColor = 0;
    protected boolean isAutoRotateEnabled;
    protected boolean isLandscape;
    private boolean mCloseNavDrawer;
    private boolean mDelayFlurryEvents = false;
    private DrawerLayout mDrawerLayout;
    private int mDrawerSelectedItemID = -1;
    /* access modifiers changed from: private */
    public ActionBarDrawerToggle mDrawerToggle;
    private boolean mNavDrawerEnabled = true;
    private boolean mNavDrawerShowRootItem = true;
    private boolean mNavDrawerStartOpen = false;
    protected Palette palette;
    private GenericRequestBuilder<String, InputStream, Palette, Palette> paletteRequest;
    private PaletteTarget paletteTarget;
    protected Toolbar toolbar;
    private int toolbarBackgroundColor = -536870913;
    private int toolbarItemsColor = -536870913;
    private int toolbarTextColor = -536870913;
    private boolean usingToolbarColorsFromTheme;

    private static String getSwatchRules(int i) {
        return i == 0 ? PaletteUtils.SWATCH_RULES_ANY_COLOR : i == 4 ? PaletteUtils.SWATCH_RULES_DOMINANT : PaletteUtils.SWATCH_RULES_AUTOMATIC;
    }

    /* access modifiers changed from: private */
    public void onBackgroundImageReady(GlideDrawable glideDrawable) {
    }

    /* access modifiers changed from: protected */
    public abstract String getActivityTitleForAnalytics();

    /* access modifiers changed from: protected */
    public abstract String getActivityTypeForAnalytics();

    /* access modifiers changed from: protected */
    public boolean getHandleCustomWindowColor() {
        return false;
    }

    public ItemBindingUtils getItemBindingUtils() {
        return null;
    }

    public abstract String[] getParentClassNamesArray();

    /* access modifiers changed from: protected */
    public int getTitleMode() {
        return 2;
    }

    /* access modifiers changed from: protected */
    public abstract boolean isDetailActivity();

    /* access modifiers changed from: protected */
    public abstract boolean isParentReachable();

    /* access modifiers changed from: protected */
    public abstract boolean isToolbarEnabled();

    /* access modifiers changed from: protected */
    public void onToolbarColorsReady(int i, int i2) {
    }

    /* access modifiers changed from: protected */
    public abstract void setContentView();

    /* access modifiers changed from: protected */
    public boolean isHamburgerEnabled() {
        return !isParentReachable();
    }

    /* access modifiers changed from: protected */
    public int getDetailOverridesResId() {
        return ThemeUtils.getResourceId(this, R.attr.detail_theme_override);
    }

    public void onCreate(Bundle bundle) {
        int color;
        int color2;
        int detailOverridesResId;
        int i;
        if (showConsent) {
            showConsent = false;
            ((AndromoApplication) getApplicationContext()).launchConsentFrom(this);
        }
        Bundle extras = getIntent().getExtras();
        if (!(extras == null || (i = extras.getInt("ThemeResourceId", 0)) == 0)) {
            setTheme(i);
        }
        int resourceId = ThemeUtils.getResourceId(this, R.attr.toolbar_settings);
        if (resourceId != 0) {
            getTheme().applyStyle(resourceId, true);
        }
        this.isLandscape = getResources().getBoolean(R.bool.is_landscape);
        this.isAutoRotateEnabled = Settings.System.getInt(getContentResolver(), "accelerometer_rotation", 0) == 1;
        if ((isDetailActivity() || this.isLandscape) && (detailOverridesResId = getDetailOverridesResId()) != 0) {
            getTheme().applyStyle(detailOverridesResId, true);
        }
        super.onCreate(bundle);
        setContentView();
        if (isLauncher()) {
            EulaHelper.initialize(this);
        }
        RequestCode.loadFromPrefsIfNeeded(this);
        ImageUtils.determineMaxTextureSize(this);
        setupToolbar();
        setupBackgroundImage();
        this.toolbarItemsColor = ThemeUtils.getTextColorPrimary(this.toolbar);
        this.toolbarTextColor = ThemeUtils.getTextColorPrimary(this.toolbar);
        if (getHandleCustomWindowColor()) {
            if (ThemeUtils.getInt(this, R.attr.body_style) == 2 && (color2 = ThemeUtils.getColor((Context) this, (int) R.attr.custom_window_color)) != 0) {
                getWindow().getDecorView().setBackgroundColor(color2);
            }
        } else if (Build.VERSION.SDK_INT <= 19 && ThemeUtils.getInt(this, R.attr.body_style) == 2 && (color = ThemeUtils.getColor((Context) this, 16842801)) != 0) {
            getWindow().getDecorView().setBackgroundColor(color);
        }
        if (bundle != null) {
            this.mCloseNavDrawer = bundle.getBoolean(CLOSE_NAV_DRAWER);
        }
        setupNavigationDrawer();
        if (this.mDrawerLayout == null && Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(ThemeUtils.getColor((Context) this, (int) R.attr.colorPrimaryDark));
        }
        AnalyticsUtils.setContext(this);
        if (AndromoFlurryAnalytics.isFlurryAnalyticsEnabled()) {
            if (AndromoFlurryAnalytics.isFlurryAnalyticsSessionActive()) {
                AndromoFlurryAnalytics.trackPageViewCount();
                AndromoFlurryAnalytics.trackPageViewEvent(getActivityTypeForAnalytics());
                this.mDelayFlurryEvents = false;
            } else {
                this.mDelayFlurryEvents = true;
            }
        }
        AdManager.onActivityCreated(this);
    }

    private void setupNavigationDrawer() {
        Drawable drawable;
        View headerView;
        View findViewById;
        Drawable drawable2;
        Drawable drawable3;
        this.mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (this.mDrawerLayout != null) {
            NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
            navigationView.setNavigationItemSelectedListener(this);
            String[] stringArray = getResources().getStringArray(R.array.activity_000_titles);
            String[] stringArray2 = getResources().getStringArray(R.array.activity_000_classes);
            TypedArray obtainTypedArray = getResources().obtainTypedArray(R.array.activity_000_icons_24dp);
            boolean z = false;
            boolean z2 = false;
            for (int i = 0; i < stringArray.length; i++) {
                if (obtainTypedArray.getResourceId(i, 0) != 0) {
                    z2 = true;
                }
            }
            Menu menu = navigationView.getMenu();
            if (this.mNavDrawerShowRootItem) {
                MenuItem add = menu.add(R.id.nav_drawer_main_items, 1, 0, getString(R.string.drawer_root_item_title));
                add.setCheckable(true);
                if (getClass().getSimpleName().equals("Dashboard_000")) {
                    int itemId = add.getItemId();
                    this.mDrawerSelectedItemID = itemId;
                    setDrawerItemChecked(itemId);
                }
                Drawable drawable4 = getResources().getDrawable(R.drawable.ic_home_black_24dp);
                if (drawable4 != null) {
                    add.setIcon(drawable4);
                }
            }
            for (int i2 = 0; i2 < stringArray.length; i2++) {
                int i3 = i2 + 2;
                MenuItem add2 = menu.add(R.id.nav_drawer_main_items, i3, 0, stringArray[i2]);
                add2.setCheckable(true);
                if (getClass().getSimpleName().equals(stringArray2[i2])) {
                    this.mDrawerSelectedItemID = i3;
                    setDrawerItemChecked(i3);
                }
                int resourceId = obtainTypedArray.getResourceId(i2, 0);
                if (!(resourceId == 0 || (drawable3 = getResources().getDrawable(resourceId)) == null)) {
                    add2.setIcon(drawable3);
                }
            }
            MenuItem findItem = menu.findItem(R.id.intercom_drawer);
            if (!IntercomHelper.showInDrawer()) {
                if (findItem != null) {
                    findItem.setVisible(false);
                }
            } else if (findItem != null) {
                findItem.setTitle("Intercom");
                Drawable drawable5 = getResources().getDrawable(R.drawable.ic_help_black_24dp);
                if (drawable5 != null) {
                    findItem.setIcon(drawable5);
                }
            }
            MenuItem findItem2 = menu.findItem(R.id.nav_drawer_menu_settings);
            if (!AndromoUtils.isPreferencesEnabled()) {
                if (findItem2 != null) {
                    findItem2.setVisible(false);
                }
            } else if (z2 && (drawable2 = getResources().getDrawable(R.drawable.ic_settings_black_24dp)) != null) {
                findItem2.setIcon(drawable2);
            }
            String trim = getString(R.string.drawer_header_title).trim();
            String trim2 = getString(R.string.drawer_header_subtitle).trim();
            if (trim.isEmpty() && trim2.isEmpty() && (headerView = navigationView.getHeaderView(0)) != null && (findViewById = headerView.findViewById(R.id.backgroundScrim)) != null) {
                findViewById.setVisibility(8);
            }
            MenuItem findItem3 = menu.findItem(R.id.nav_drawer_menu_about);
            if (findItem3 != null) {
                findItem3.setTitle(getString(R.string.about_dialog_title, new Object[]{getString(R.string.app_name)}));
                if (z2 && (drawable = getResources().getDrawable(R.drawable.ic_help_black_24dp)) != null) {
                    findItem3.setIcon(drawable);
                }
            }
            if (this.mNavDrawerEnabled) {
                this.mDrawerLayout.setDrawerLockMode(0, (int) GravityCompat.START);
                if (isDrawerFirstTime() && this.mNavDrawerStartOpen) {
                    this.mDrawerLayout.openDrawer((int) GravityCompat.START);
                    this.mCloseNavDrawer = false;
                }
            } else {
                this.mDrawerLayout.setDrawerLockMode(1, (int) GravityCompat.START);
            }
            this.mDrawerToggle = new ActionBarDrawerToggle(this, this.mDrawerLayout, this.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
                public void onDrawerClosed(View view) {
                    super.onDrawerClosed(view);
                    AndromoActivity.this.invalidateOptionsMenu();
                }

                public void onDrawerOpened(View view) {
                    super.onDrawerOpened(view);
                    AndromoActivity.this.invalidateOptionsMenu();
                }

                public void onDrawerStateChanged(int i) {
                    super.onDrawerStateChanged(i);
                }
            };
            this.mDrawerLayout.setDrawerListener(this.mDrawerToggle);
            this.mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!AndromoActivity.this.mDrawerToggle.isDrawerIndicatorEnabled() && !AndromoUtils.navigateUpFrom(AndromoActivity.this)) {
                        AndromoActivity.this.onBackPressed();
                    }
                }
            });
            ActionBarDrawerToggle actionBarDrawerToggle = this.mDrawerToggle;
            if (isHamburgerEnabled() && this.mNavDrawerEnabled) {
                z = true;
            }
            actionBarDrawerToggle.setDrawerIndicatorEnabled(z);
            this.mDrawerToggle.getDrawerArrowDrawable().setColor(ThemeUtils.getTextColorPrimary(this.toolbar));
        }
    }

    private boolean isDrawerFirstTime() {
        SharedPreferences defaultSharedPreferences;
        if (!this.mNavDrawerEnabled || !this.mNavDrawerStartOpen || (defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)) == null) {
            return false;
        }
        boolean z = defaultSharedPreferences.getBoolean("drawer_first_time", true);
        if (z) {
            SharedPreferences.Editor edit = defaultSharedPreferences.edit();
            edit.putBoolean("drawer_first_time", false);
            edit.apply();
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public void setColorizeToolbarEnabled(boolean z) {
        this.colorizeToolbar = z;
    }

    /* access modifiers changed from: package-private */
    public void setToolbarBackgroundColor(int i) {
        this.toolbarBackgroundColor = i;
    }

    /* access modifiers changed from: package-private */
    public void setToolbarItemsColor(int i) {
        this.toolbarItemsColor = i;
    }

    /* access modifiers changed from: package-private */
    public void setToolbarTextColor(int i) {
        this.toolbarTextColor = i;
    }

    /* access modifiers changed from: package-private */
    public int getToolbarBackgroundColor() {
        return this.toolbarBackgroundColor;
    }

    /* access modifiers changed from: package-private */
    public int getToolbarItemsColor() {
        return this.toolbarItemsColor;
    }

    /* access modifiers changed from: package-private */
    public int getToolbarTextColor() {
        return this.toolbarTextColor;
    }

    /* access modifiers changed from: package-private */
    public void setToolbarTitle(CharSequence charSequence) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            switch (getTitleMode()) {
                case 0:
                    supportActionBar.setDisplayShowTitleEnabled(false);
                    return;
                case 1:
                    supportActionBar.setDisplayShowTitleEnabled(true);
                    supportActionBar.setTitle((int) R.string.app_name);
                    return;
                default:
                    supportActionBar.setDisplayShowTitleEnabled(true);
                    supportActionBar.setTitle(charSequence);
                    return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setToolbarTitle(int i) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            switch (getTitleMode()) {
                case 0:
                    supportActionBar.setDisplayShowTitleEnabled(false);
                    return;
                case 1:
                    supportActionBar.setDisplayShowTitleEnabled(true);
                    supportActionBar.setTitle((int) R.string.app_name);
                    return;
                default:
                    supportActionBar.setDisplayShowTitleEnabled(true);
                    supportActionBar.setTitle(i);
                    return;
            }
        }
    }

    private GenericRequestBuilder<String, InputStream, Palette, Palette> getPaletteRequestBuilder() {
        if (this.paletteRequest != null) {
            return this.paletteRequest.clone();
        }
        this.paletteRequest = Glide.with((FragmentActivity) this).using(new StreamStringLoader((Context) this), InputStream.class).from(String.class).as(Palette.class).diskCacheStrategy(DiskCacheStrategy.ALL).encoder(new PaletteCacheEncoder(new PaletteEncoder())).sourceEncoder(new StreamEncoder()).cacheDecoder(new FileToStreamDecoder(new PaletteCacheDecoder(new PaletteDecoder(), new StreamBitmapDecoder(Downsampler.AT_MOST, Glide.get(this).getBitmapPool(), DecodeFormat.DEFAULT)))).override(256, 256).dontTransform().dontAnimate();
        return this.paletteRequest;
    }

    private Key getSignatureForImageLoadFromUrl() {
        return EmptySignature.obtain();
    }

    private Key getSignatureForImageLoadFromResource() {
        return ApplicationVersionSignature.obtain(this);
    }

    private void getPaletteForActivityImage(String str, Key key) {
        if (this.paletteTarget != null) {
            Glide.clear((Target<?>) this.paletteTarget);
        }
        this.paletteTarget = (PaletteTarget) getPaletteRequestBuilder().signature(key).load(str).into(new PaletteTarget(this));
    }

    private void getPaletteForBackgroundImage(String str, Key key) {
        getPaletteForActivityImage(str, key);
    }

    private static String resourceIdToUrlString(Context context, int i) {
        Resources resources = context.getResources();
        try {
            return "android.resource://" + resources.getResourcePackageName(i) + '/' + resources.getResourceTypeName(i) + '/' + resources.getResourceEntryName(i);
        } catch (Resources.NotFoundException unused) {
            Log.isLoggable(TAG, 5);
            return null;
        }
    }

    private int getWidthForFeatureImage() {
        int i;
        if (this.isLandscape) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            i = displayMetrics.heightPixels;
        } else {
            i = Resources.getSystem().getDisplayMetrics().widthPixels;
        }
        return i > MAX_FEATURE_WIDTH ? MAX_FEATURE_WIDTH : i;
    }

    private int getHeightForFeatureImage(Context context, int i) {
        return (int) (((float) i) / ThemeUtils.getFraction(context, R.attr.feature_aspect_ratio, 1.0f));
    }

    private DrawableRequestBuilder<String> getActivityImageRequestBuilder(Context context) {
        int widthForFeatureImage = getWidthForFeatureImage();
        int heightForFeatureImage = getHeightForFeatureImage(context, widthForFeatureImage);
        DrawableRequestBuilder<String> diskCacheStrategy = Glide.with(context).from(String.class).diskCacheStrategy(DiskCacheStrategy.ALL);
        if (this.bSharedElementTransitionEnabled) {
            return diskCacheStrategy.dontAnimate();
        }
        return diskCacheStrategy.crossFade(150).thumbnail(0.25f).fitCenter().override(widthForFeatureImage, heightForFeatureImage);
    }

    private DrawableRequestBuilder<String> getBackgroundImageRequestBuilder(Context context) {
        DrawableRequestBuilder<String> centerCrop = Glide.with(context).from(String.class).diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop();
        if (this.bSharedElementTransitionEnabled) {
            return centerCrop.dontAnimate();
        }
        return centerCrop.thumbnail(0.25f);
    }

    public static boolean isAutomaticBackgroundColorEnabled(Context context) {
        return ThemeUtils.getInt(context, R.attr.toolbar_background_color_mode) != 0;
    }

    public static class ActivityImageInfo {
        public int errorResId;
        public int fallbackColor;
        public int fallbackResId;
        public int placeholderResId;
        public final Key signature;
        public final String url;

        public ActivityImageInfo(String str) {
            this(str, EmptySignature.obtain(), 0);
        }

        public ActivityImageInfo(String str, Key key) {
            this.url = str;
            this.signature = key == null ? EmptySignature.obtain() : key;
        }

        public ActivityImageInfo(String str, Key key, int i) {
            this.url = str;
            this.signature = key == null ? EmptySignature.obtain() : key;
            this.fallbackResId = i;
        }

        public ActivityImageInfo fallbackColor(int i) {
            this.fallbackColor = i;
            return this;
        }

        public ActivityImageInfo fallbackResource(int i) {
            this.fallbackResId = i;
            return this;
        }

        public ActivityImageInfo fallback(int i) {
            this.fallbackResId = i;
            return this;
        }

        public ActivityImageInfo error(int i) {
            this.errorResId = i;
            return this;
        }

        public ActivityImageInfo placeholder(int i) {
            this.placeholderResId = i;
            return this;
        }

        public ActivityImageInfo plain() {
            this.fallbackResId = 0;
            this.placeholderResId = 0;
            this.errorResId = 0;
            return this;
        }

        public String toString() {
            return getClass().getName() + "[ url: '" + this.url + "', signature: '" + this.signature + "]";
        }

        public boolean hasUrl() {
            return this.url != null && !this.url.isEmpty();
        }

        public static String whyInvalid(ActivityImageInfo activityImageInfo) {
            if (activityImageInfo == null) {
                return "imageInfo is null";
            }
            if (activityImageInfo.url == null) {
                return "imageInfo.url is null";
            }
            if (activityImageInfo.url.isEmpty()) {
                return "imageInfo.url is empty";
            }
            return "";
        }
    }

    private void loadActivityImageFromUrl(ImageView imageView, String str, int i) {
        loadActivityImage(imageView, new ActivityImageInfo(str, getSignatureForImageLoadFromUrl(), i));
    }

    private void loadActivityImageFromResource(ImageView imageView, int i) {
        loadActivityImage(imageView, new ActivityImageInfo(resourceIdToUrlString(this, i), getSignatureForImageLoadFromResource(), 0));
    }

    private void loadActivityImage(final ImageView imageView, final ActivityImageInfo activityImageInfo) {
        if (activityImageInfo != null) {
            String str = activityImageInfo.url;
            Key key = activityImageInfo.signature;
            if (str != null) {
                Context context = imageView.getContext();
                if (isAutomaticBackgroundColorEnabled(context)) {
                    getPaletteForActivityImage(activityImageInfo.url, activityImageInfo.signature);
                } else {
                    onToolbarColorsFromTheme();
                }
                getActivityImageRequestBuilder(context).signature(key).load(str).into(new GlideDrawableImageViewTarget(imageView) {
                    public void onResourceReady(GlideDrawable glideDrawable, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        super.onResourceReady(glideDrawable, glideAnimation);
                        AndromoActivity.this.onActivityImageReady(glideDrawable);
                    }

                    public void onLoadFailed(Exception exc, Drawable drawable) {
                        super.onLoadFailed(exc, drawable);
                        AndromoActivity.this.onActivityImageLoadFailed(imageView, activityImageInfo);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public void onActivityImageReady(GlideDrawable glideDrawable) {
        if (this.bSharedElementTransitionEnabled) {
            supportStartPostponedEnterTransition();
        }
    }

    /* access modifiers changed from: private */
    public void onActivityImageLoadFailed(ImageView imageView, ActivityImageInfo activityImageInfo) {
        handleImageFallback(imageView, activityImageInfo);
        ToolbarColorizer.tintAllMenuItems(this.toolbar, this.toolbarItemsColor);
    }

    private void handleImageFallback(ImageView imageView, ActivityImageInfo activityImageInfo) {
        if (imageView != null) {
            if (activityImageInfo == null) {
                imageView.setImageDrawable((Drawable) null);
            } else if (activityImageInfo.fallbackResId != 0) {
                ActivityImageInfo activityImageInfo2 = new ActivityImageInfo(ThemeUtils.resourceIdToUrlString(imageView.getContext(), activityImageInfo.fallbackResId), getSignatureForImageLoadFromResource(), 0);
                activityImageInfo2.fallbackColor = activityImageInfo.fallbackColor;
                activityImageInfo2.errorResId = activityImageInfo.errorResId;
                activityImageInfo2.placeholderResId = activityImageInfo.placeholderResId;
                loadActivityImage(imageView, activityImageInfo2);
            } else if (activityImageInfo.fallbackColor != 0) {
                imageView.setImageDrawable(new ColorDrawable(activityImageInfo.fallbackColor));
            } else if (activityImageInfo.errorResId == 0 || !activityImageInfo.hasUrl()) {
                imageView.setImageDrawable((Drawable) null);
            } else {
                ActivityImageInfo activityImageInfo3 = new ActivityImageInfo(ThemeUtils.resourceIdToUrlString(imageView.getContext(), activityImageInfo.errorResId), getSignatureForImageLoadFromResource(), 0);
                activityImageInfo3.placeholderResId = activityImageInfo.placeholderResId;
                loadActivityImage(imageView, activityImageInfo3);
            }
        }
    }

    private void setupActivityImage() {
        ImageView imageView = (ImageView) findViewById(R.id.feature_image);
        if (this.bSharedElementTransitionEnabled) {
            supportPostponeEnterTransition();
        }
        String resolveString = ThemeUtils.resolveString(this, R.attr.activity_image_url);
        int resourceId = ThemeUtils.getResourceId(this, R.attr.activity_image_drawable, 0);
        if (imageView == null) {
            ActivityImageInfo activityImageInfo = null;
            if (resolveString != null && !"".equals(resolveString)) {
                activityImageInfo = new ActivityImageInfo(resolveString, getSignatureForImageLoadFromUrl(), resourceId);
            } else if (resourceId != 0) {
                activityImageInfo = new ActivityImageInfo(resourceIdToUrlString(this, resourceId), getSignatureForImageLoadFromResource(), 0);
            }
            if (activityImageInfo == null) {
                onToolbarColorsFromTheme();
            } else if (activityImageInfo.url == null) {
                onToolbarColorsFromTheme();
            } else if (isAutomaticBackgroundColorEnabled(this)) {
                getPaletteForActivityImage(activityImageInfo.url, activityImageInfo.signature);
            } else {
                onToolbarColorsFromTheme();
            }
            if (this.bSharedElementTransitionEnabled) {
                startPostponedEnterTransitionOnDecorView();
            }
        } else if (resolveString != null && !"".equals(resolveString)) {
            loadActivityImageFromUrl(imageView, resolveString, resourceId);
        } else if (resourceId != 0) {
            loadActivityImageFromResource(imageView, resourceId);
        } else {
            onToolbarColorsFromTheme();
            if (this.bSharedElementTransitionEnabled) {
                startPostponedEnterTransitionOnDecorView();
            }
        }
    }

    private void loadBackgroundImageFromUrl(ImageView imageView, String str, int i) {
        loadBackgroundImage(imageView, new ActivityImageInfo(str, getSignatureForImageLoadFromUrl(), i));
    }

    private void loadBackgroundImageFromResource(ImageView imageView, int i) {
        loadBackgroundImage(imageView, new ActivityImageInfo(resourceIdToUrlString(this, i), getSignatureForImageLoadFromResource(), 0));
    }

    private void loadBackgroundImage(ImageView imageView, ActivityImageInfo activityImageInfo) {
        ActivityImageInfo activityImageInfo2 = activityImageInfo;
        if (activityImageInfo2 != null) {
            String str = activityImageInfo2.url;
            Key key = activityImageInfo2.signature;
            if (str != null) {
                Context context = imageView.getContext();
                if (!isFeatureImageEnabled()) {
                    if (isAutomaticBackgroundColorEnabled(context)) {
                        Log.v(TAG, "Generating palette from background image; activity feature image is disabled.");
                        getPaletteForBackgroundImage(activityImageInfo2.url, activityImageInfo2.signature);
                    } else {
                        onToolbarColorsFromTheme();
                    }
                }
                final DrawableRequestBuilder<String> centerCrop = this.isAutoRotateEnabled ? Glide.with(context.getApplicationContext()).load(str).diskCacheStrategy(DiskCacheStrategy.ALL).signature(key).priority(Priority.LOW).centerCrop() : null;
                Point screenResolution = ThemeUtils.getScreenResolution(context);
                int i = screenResolution.x / 2;
                int i2 = screenResolution.y / 2;
                final int i3 = i;
                final int i4 = i2;
                final int i5 = i;
                String str2 = str;
                AnonymousClass4 r11 = r0;
                final ImageView imageView2 = imageView;
                final ActivityImageInfo activityImageInfo3 = activityImageInfo;
                AnonymousClass4 r0 = new ColorFilterTarget(imageView, getBackgroundEffect()) {
                    public void onResourceReady(GlideDrawable glideDrawable, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        super.onResourceReady(glideDrawable, glideAnimation);
                        AndromoActivity.this.onBackgroundImageReady(glideDrawable);
                        if (centerCrop != null && glideDrawable.getIntrinsicWidth() == i3) {
                            centerCrop.preload(i4, i5);
                        }
                    }

                    public void onLoadFailed(Exception exc, Drawable drawable) {
                        super.onLoadFailed(exc, drawable);
                        AndromoActivity.this.colorFilterTarget.setMatrix((ColorMatrix) null);
                        ColorFilterTarget unused = AndromoActivity.this.colorFilterTarget = null;
                        AndromoActivity.this.onBackgroundImageLoadFailed(imageView2, activityImageInfo3);
                    }

                    public void onLoadCleared(Drawable drawable) {
                        super.onLoadCleared(drawable);
                        AndromoActivity.this.colorFilterTarget.setMatrix((ColorMatrix) null);
                        ColorFilterTarget unused = AndromoActivity.this.colorFilterTarget = null;
                    }
                };
                this.colorFilterTarget = r11;
                getBackgroundImageRequestBuilder(context).signature(key).override(i, i2).load(str2).priority(Priority.HIGH).into(this.colorFilterTarget);
            }
        }
    }

    /* access modifiers changed from: protected */
    public ColorMatrix getBackgroundEffect() {
        int i;
        int i2 = ThemeUtils.getInt(this, R.attr.activity_background_image_effect);
        if (BackgroundEffect.isTintEnabled(i2)) {
            this.customBackgroundImageTintColor = ThemeUtils.getColor((Context) this, (int) R.attr.activity_background_image_tint_color);
            i = this.customBackgroundImageTintColor == 0 ? (!isAutomaticBackgroundColorEnabled(this) || usingToolbarColorsFromTheme()) ? ThemeUtils.getColor((Context) this, (int) R.attr.colorPrimary) : getToolbarBackgroundColor() : this.customBackgroundImageTintColor;
        } else {
            i = 0;
        }
        this.backgroundImageTintColor = i;
        return BackgroundEffect.getMatrix(i2, i);
    }

    /* access modifiers changed from: private */
    public void onBackgroundImageLoadFailed(ImageView imageView, ActivityImageInfo activityImageInfo) {
        handleImageFallback(imageView, activityImageInfo);
    }

    private boolean isFeatureImageEnabled() {
        String resolveString = ThemeUtils.resolveString(this, R.attr.activity_image_url);
        if ((resolveString == null || "".equals(resolveString)) && ThemeUtils.getResourceId(this, R.attr.activity_image_drawable, 0) == 0) {
            return false;
        }
        return true;
    }

    private void setupBackgroundImage() {
        ImageView imageView;
        String resolveString = ThemeUtils.resolveString(this, R.attr.activity_background_image_url);
        boolean z = false;
        int resourceId = ThemeUtils.getResourceId(this, R.attr.activity_background_image_drawable, 0);
        if ((resolveString != null && !resolveString.isEmpty()) || resourceId != 0) {
            z = true;
        }
        if (z && (imageView = (ImageView) findViewById(R.id.background_image)) != null) {
            if (resolveString != null && !"".equals(resolveString)) {
                loadBackgroundImageFromUrl(imageView, resolveString, resourceId);
            } else if (resourceId != 0) {
                loadBackgroundImageFromResource(imageView, resourceId);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setupToolbar() {
        inflateToolbar();
        if (this.toolbar != null) {
            setSupportActionBar(this.toolbar);
            setupActivityImage();
        }
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayShowHomeEnabled(true);
            supportActionBar.setHomeButtonEnabled(true);
            supportActionBar.setDisplayShowCustomEnabled(false);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayHomeAsUpEnabled(false);
            supportActionBar.setDisplayHomeAsUpEnabled(isParentReachable());
        }
    }

    private void startPostponedEnterTransitionOnDecorView() {
        final View decorView = getWindow().getDecorView();
        decorView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                decorView.getViewTreeObserver().removeOnPreDrawListener(this);
                AndromoActivity.this.supportStartPostponedEnterTransition();
                return true;
            }
        });
    }

    private void inflateToolbar() {
        if (isToolbarEnabled()) {
            ViewStub viewStub = (ViewStub) findViewById(R.id.app_bar);
            if (viewStub != null) {
                viewStub.setInflatedId(-1);
                viewStub.inflate();
            }
            this.toolbar = (Toolbar) findViewById(R.id.toolbar);
            this.collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
            CollapsingToolbarLayout collapsingToolbarLayout = this.collapsingToolbar;
            return;
        }
        this.toolbar = null;
    }

    public void onPaletteReady(Palette palette2, View view, int i) {
        onPaletteReady(palette2);
    }

    public void onPaletteReady(Palette palette2) {
        Palette.Swatch swatch;
        if (palette2 != null) {
            this.palette = palette2;
            if (this.paletteTarget != null) {
                Glide.clear((Target<?>) this.paletteTarget);
                this.paletteTarget.setListener((PaletteListener) null);
                this.paletteTarget = null;
            }
            PaletteUtils.logSwatches(palette2);
            int i = ThemeUtils.getInt(this, R.attr.toolbar_background_color_mode);
            if (i != 0) {
                int i2 = ThemeUtils.getInt(this, R.attr.toolbar_background_color_preference);
                float darkness = getDarkness(palette2, i2);
                String swatchRules = getSwatchRules(i2);
                switch (i) {
                    case 1:
                        swatch = PaletteUtils.getSwatch(palette2, swatchRules, darkness);
                        break;
                    case 2:
                        swatch = PaletteUtils.getNearestMaterialSwatch(PaletteUtils.getSwatch(palette2, swatchRules, darkness), false);
                        break;
                    case 3:
                        swatch = PaletteUtils.getNearestMaterialSwatch(PaletteUtils.getSwatch(palette2, swatchRules, darkness), true);
                        break;
                    default:
                        throw new RuntimeException("unexpected toolbarBackgroundColorMode: " + i);
                }
                setToolbarColorsFromSwatch(swatch);
                return;
            }
            onToolbarColorsFromTheme();
        }
    }

    /* access modifiers changed from: protected */
    public boolean usingToolbarColorsFromTheme() {
        return this.usingToolbarColorsFromTheme;
    }

    /* access modifiers changed from: protected */
    public final void onToolbarColorsFromTheme() {
        int color = ThemeUtils.getColor(this.toolbar, 16842806);
        int color2 = ThemeUtils.getColor((Context) this, (int) R.attr.colorPrimary);
        this.usingToolbarColorsFromTheme = true;
        onToolbarColorsReady(color2, color);
    }

    private void updateBackgroundEffect(int i) {
        Drawable drawable;
        if (BackgroundEffect.isTintEnabled(ThemeUtils.getInt(this, R.attr.activity_background_image_effect)) && this.customBackgroundImageTintColor == 0 && this.backgroundImageTintColor != i) {
            if (this.colorFilterTarget != null) {
                this.colorFilterTarget.setMatrix(getBackgroundEffect());
                return;
            }
            ImageView imageView = (ImageView) findViewById(R.id.background_image);
            if (imageView != null && (drawable = imageView.getDrawable()) != null) {
                ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(getBackgroundEffect());
                drawable.mutate();
                drawable.setColorFilter(colorMatrixColorFilter);
                imageView.setImageDrawable(drawable);
                imageView.invalidateDrawable(drawable);
                imageView.invalidate();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onToolbarSwatchReady(int i, int i2) {
        Drawable drawable;
        if (!BackgroundEffect.isTintEnabled(ThemeUtils.getInt(this, R.attr.activity_background_image_effect))) {
            return;
        }
        if (this.colorFilterTarget != null) {
            this.colorFilterTarget.setMatrix(getBackgroundEffect());
            return;
        }
        ImageView imageView = (ImageView) findViewById(R.id.background_image);
        if (imageView != null && (drawable = imageView.getDrawable()) != null) {
            ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(getBackgroundEffect());
            drawable.mutate();
            drawable.setColorFilter(colorMatrixColorFilter);
            imageView.setImageDrawable(drawable);
        }
    }

    private static float getDarkness(Palette palette2, int i) {
        if (i == 2) {
            return 1.0f;
        }
        if (i == 3) {
            return 0.0f;
        }
        return PaletteUtils.getPaletteDarknessByPopulation(palette2);
    }

    /* access modifiers changed from: package-private */
    public void setToolbarColorsFromSwatch(Palette.Swatch swatch) {
        if (swatch != null) {
            this.usingToolbarColorsFromTheme = false;
            ColorUtils.isDark(this.toolbarBackgroundColor);
            this.toolbarBackgroundColor = swatch.getRgb();
            ColorUtils.isDark(this.toolbarBackgroundColor);
            int bodyTextColor = swatch.getBodyTextColor();
            this.toolbarTextColor = bodyTextColor;
            this.toolbarItemsColor = bodyTextColor;
            applyToolbarColors();
            updateBackgroundEffect(this.toolbarBackgroundColor);
            onToolbarSwatchReady(this.toolbarBackgroundColor, this.toolbarTextColor);
            onToolbarColorsReady(this.toolbarBackgroundColor, this.toolbarTextColor);
        }
    }

    private void applyToolbarColors() {
        if (this.toolbar != null) {
            if (this.collapsingToolbar != null) {
                this.collapsingToolbar.setCollapsedTitleTextColor(this.toolbarTextColor);
                this.collapsingToolbar.setExpandedTitleColor(this.toolbarTextColor);
                if (ThemeUtils.getColor((Context) this, (int) R.attr.collapsing_toolbar_content_scrim) != 0) {
                    this.collapsingToolbar.setContentScrimColor(this.toolbarBackgroundColor);
                }
            }
            if (ThemeUtils.getColor((Context) this, (int) R.attr.toolbar_background) != 0 || isDetailActivity() || this.isLandscape) {
                this.toolbar.setBackgroundColor(this.toolbarBackgroundColor);
            }
            this.toolbar.setTitleTextColor(this.toolbarTextColor);
            if (Build.VERSION.SDK_INT >= 21) {
                Window window = getWindow();
                if (this.mDrawerLayout == null || !this.mDrawerLayout.getFitsSystemWindows()) {
                    window.addFlags(Integer.MIN_VALUE);
                    window.clearFlags(67108864);
                    window.setStatusBarColor(ColorUtils.darker(this.toolbarBackgroundColor, 0.8f));
                } else {
                    this.mDrawerLayout.setStatusBarBackgroundColor(ColorUtils.darker(this.toolbarBackgroundColor, 0.8f));
                    window.setStatusBarColor(0);
                }
            }
            ToolbarColorizer.tintAllMenuItems(this.toolbar, this.toolbarItemsColor);
            ToolbarColorizer.setNavigationIconColor(this.toolbar, this.toolbarItemsColor);
            ToolbarColorizer.setOverflowButtonColor(this.toolbar, this.toolbarItemsColor);
            if (this.mDrawerToggle != null) {
                this.mDrawerToggle.getDrawerArrowDrawable().setColor(this.toolbarItemsColor);
            }
        }
    }

    private void setDrawerItemChecked(int i) {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        if (navigationView != null) {
            navigationView.setCheckedItem(i);
        }
    }

    public boolean onNavigationItemSelected(MenuItem menuItem) {
        setDrawerItemChecked(menuItem.getItemId());
        int itemId = menuItem.getItemId();
        if (itemId == 1) {
            AndromoUtils.navigateWithInterstitial(this, "Dashboard_000", menuItem.getTitle().toString(), new Pair[0]);
            this.mCloseNavDrawer = true;
        } else if (itemId != R.id.intercom_drawer) {
            switch (itemId) {
                case R.id.nav_drawer_menu_about /*2131296437*/:
                    AndromoUtils.showAboutActivity(this);
                    this.mCloseNavDrawer = true;
                    break;
                case R.id.nav_drawer_menu_settings /*2131296438*/:
                    AndromoUtils.handleDrawerSettingsItem(this);
                    this.mCloseNavDrawer = true;
                    break;
                default:
                    int itemId2 = menuItem.getItemId() - 2;
                    String[] stringArray = getResources().getStringArray(R.array.activity_000_classes);
                    if (itemId2 >= 0 && itemId2 < stringArray.length) {
                        AndromoUtils.navigateWithInterstitial(this, stringArray[itemId2], menuItem.getTitle().toString(), new Pair[0]);
                        this.mCloseNavDrawer = true;
                        break;
                    }
            }
        } else {
            IntercomHelper.launch();
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        if (this.mDrawerToggle != null) {
            this.mDrawerToggle.syncState();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.mDrawerToggle != null) {
            this.mDrawerToggle.onConfigurationChanged(configuration);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        AndromoUtils.inflateDefaultMenuItems(getMenuInflater(), menu);
        ToolbarColorizer.tintAllMenuItems(this.toolbar, this.toolbarItemsColor);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onCreateOptionsMenu(MenuInflater menuInflater, Menu menu, boolean z) {
        AndromoUtils.inflateDefaultMenuItems(menuInflater, menu, z);
        ToolbarColorizer.tintAllMenuItems(this.toolbar, this.toolbarItemsColor);
        return super.onCreateOptionsMenu(menu);
    }

    /* access modifiers changed from: package-private */
    public void tintMenuItem(int i, int i2) {
        MenuColorizer.tintMenuItem(this.toolbar.getMenu(), i, i2);
    }

    /* access modifiers changed from: package-private */
    public int getThemeColor(int i) {
        return ThemeUtils.getColor((Context) this, i);
    }

    /* access modifiers changed from: package-private */
    public int getToolbarThemeColor(int i) {
        return ThemeUtils.getColor(this.toolbar, i);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int drawerLockMode = this.mDrawerLayout.getDrawerLockMode((int) GravityCompat.START);
        if (this.mDrawerToggle != null && drawerLockMode == 0 && this.mDrawerToggle.onOptionsItemSelected(menuItem)) {
            return true;
        }
        menuItem.getItemId();
        if (ActionBarUtils.handleDefaultMenuItems(this, menuItem) || super.onOptionsItemSelected(menuItem)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        AdManager.onActivityStarted(this);
        AnalyticsUtils.activityStart(this, getActivityTypeForAnalytics());
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        try {
            super.onStop();
            AdManager.onActivityStopped(this);
            AnalyticsUtils.activityStop(this);
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        if (this.mDelayFlurryEvents && AndromoFlurryAnalytics.isFlurryAnalyticsEnabled()) {
            if (AndromoFlurryAnalytics.isFlurryAnalyticsSessionActive()) {
                AndromoFlurryAnalytics.trackPageViewCount();
                AndromoFlurryAnalytics.trackPageViewEvent(getActivityTypeForAnalytics());
            }
            this.mDelayFlurryEvents = false;
        }
        AdManager.onActivityPaused(this);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (isTrial && buildTime + trialPeriod < System.currentTimeMillis() && dialog == null) {
            dialog = new AlertDialog.Builder(this).setCancelable(false).setMessage("Trial build expired\n\nThis app has been created using Andromo App Maker in trial mode. This build has been expired. The developer of this app needs to upgrade their subscription or produce a new build.").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Process.killProcess(Process.myPid());
                }
            }).show();
        }
        setDrawerItemChecked(this.mDrawerSelectedItemID);
        if (this.mCloseNavDrawer && this.mDrawerLayout != null && this.mDrawerLayout.isDrawerOpen((int) GravityCompat.START)) {
            this.mDrawerLayout.closeDrawer((int) GravityCompat.START);
            this.mCloseNavDrawer = false;
        }
        AndromoFirebaseAnalytics.recordScreenView(this, getActivityTitleForAnalytics(), getActivityTypeForAnalytics());
        AdManager.onActivityResumed(this);
        RatePopupHelper.check(this);
    }

    public void onBackPressed() {
        if (this.mDrawerLayout == null || !this.mDrawerLayout.isDrawerOpen((int) GravityCompat.START)) {
            AdManager.onActivityBackPressed(this);
            super.onBackPressed();
            return;
        }
        this.mDrawerLayout.closeDrawer((int) GravityCompat.START);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        try {
            AdManager.onActivityDestroyed(this);
            RequestCode.saveToPrefsIfNeeded(this);
            super.onDestroy();
            if (this.paletteTarget != null) {
                this.paletteTarget.setListener((PaletteListener) null);
            }
            if (this.colorFilterTarget != null) {
                this.colorFilterTarget.setMatrix((ColorMatrix) null);
                this.colorFilterTarget = null;
            }
        } catch (Throwable unused) {
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (bundle != null) {
            bundle.putBoolean(CLOSE_NAV_DRAWER, this.mCloseNavDrawer);
        }
        super.onSaveInstanceState(bundle);
    }

    private boolean isLauncher() {
        Intent intent = getIntent();
        return intent != null && (intent.hasCategory("android.intent.category.LAUNCHER") || "android.intent.action.MAIN".equals(intent.getAction()));
    }

    public View getViewFromBinding(ViewDataBinding viewDataBinding, int i) {
        ItemBindingUtils itemBindingUtils = getItemBindingUtils();
        if (itemBindingUtils != null) {
            switch (i) {
                case R.id.itemDate /*2131296405*/:
                    return itemBindingUtils.getDateView(viewDataBinding);
                case R.id.itemDescription /*2131296406*/:
                    return itemBindingUtils.getDescriptionView(viewDataBinding);
                case R.id.itemImage /*2131296407*/:
                    return itemBindingUtils.getImageView(viewDataBinding);
                case R.id.itemRoot /*2131296408*/:
                    return itemBindingUtils.getRootView(viewDataBinding);
                case R.id.itemSubtitle /*2131296410*/:
                    return itemBindingUtils.getSubtitleView(viewDataBinding);
                case R.id.itemTitle /*2131296411*/:
                    return itemBindingUtils.getTitleView(viewDataBinding);
            }
        }
        return null;
    }

    public View getItemRootViewFromBinding(ViewDataBinding viewDataBinding) {
        ItemBindingUtils itemBindingUtils = getItemBindingUtils();
        if (itemBindingUtils != null) {
            return itemBindingUtils.getRootView(viewDataBinding);
        }
        return null;
    }

    public View getItemImageViewFromBinding(ViewDataBinding viewDataBinding) {
        ItemBindingUtils itemBindingUtils = getItemBindingUtils();
        if (itemBindingUtils != null) {
            return itemBindingUtils.getImageView(viewDataBinding);
        }
        return null;
    }

    public View getItemScrimViewFromBinding(ViewDataBinding viewDataBinding) {
        ItemBindingUtils itemBindingUtils = getItemBindingUtils();
        if (itemBindingUtils != null) {
            return itemBindingUtils.getScrimView(viewDataBinding);
        }
        return null;
    }

    public View getItemTitleViewFromBinding(ViewDataBinding viewDataBinding) {
        ItemBindingUtils itemBindingUtils = getItemBindingUtils();
        if (itemBindingUtils != null) {
            return itemBindingUtils.getTitleView(viewDataBinding);
        }
        return null;
    }

    public View getItemSubtitleViewFromBinding(ViewDataBinding viewDataBinding) {
        ItemBindingUtils itemBindingUtils = getItemBindingUtils();
        if (itemBindingUtils != null) {
            return itemBindingUtils.getSubtitleView(viewDataBinding);
        }
        return null;
    }

    public View getItemDescriptionViewFromBinding(ViewDataBinding viewDataBinding) {
        ItemBindingUtils itemBindingUtils = getItemBindingUtils();
        if (itemBindingUtils != null) {
            return itemBindingUtils.getDescriptionView(viewDataBinding);
        }
        return null;
    }

    public View getItemDateViewFromBinding(ViewDataBinding viewDataBinding) {
        ItemBindingUtils itemBindingUtils = getItemBindingUtils();
        if (itemBindingUtils != null) {
            return itemBindingUtils.getDateView(viewDataBinding);
        }
        return null;
    }

    public static class ParentCheck {
        private boolean known;
        private boolean reachable;

        public boolean isParentReachable(AppCompatActivity appCompatActivity) {
            if (!this.known) {
                this.reachable = AndromoUtils.isParentReachable(appCompatActivity);
                this.known = true;
            }
            return this.reachable;
        }

        public boolean isParentReachable(AppCompatActivity appCompatActivity, String str) {
            if (!this.known) {
                this.reachable = AndromoUtils.isParentReachable(appCompatActivity, str);
                this.known = true;
            }
            return this.reachable;
        }
    }

    public void onDelayedAdLoad() {
        AdManager.setDelayedAdLoading(false);
        AdManager.onActivityCreated(this);
        AdManager.onActivityStarted(this);
    }
}
