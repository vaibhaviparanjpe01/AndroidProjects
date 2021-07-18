package com.newandromo.dev849565.app936843;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class AndromoAboutActivity extends AppCompatActivity {
    private static final String TAG = "AndromoAboutActivity";
    protected Toolbar toolbar;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Log.v(TAG, "onCreate");
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(ThemeUtils.getColor((Context) this, (int) R.attr.colorPrimaryDark));
        }
        setContentView((int) R.layout.about_layout);
        setupToolbar();
        setupViews();
    }

    /* access modifiers changed from: package-private */
    public void setupToolbar() {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (this.toolbar != null) {
            setSupportActionBar(this.toolbar);
        } else {
            Log.e(TAG, "Could not find toolbar in layout.");
        }
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayShowHomeEnabled(true);
            supportActionBar.setHomeButtonEnabled(false);
            supportActionBar.setDisplayShowCustomEnabled(false);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowTitleEnabled(true);
            supportActionBar.setTitle((CharSequence) getString(R.string.about_dialog_title, new Object[]{getString(R.string.app_name)}));
        }
    }

    /* access modifiers changed from: package-private */
    public void setupViews() {
        TextView textView = (TextView) findViewById(R.id.app_version);
        if (textView != null) {
            textView.setText(Html.fromHtml(getString(R.string.about_dialog_app_version, new Object[]{AndromoUtils.getVersionNameFromPackage(this)})));
        }
        TextView textView2 = (TextView) findViewById(R.id.app_developer);
        if (textView2 != null) {
            textView2.setMovementMethod(LinkMovementMethod.getInstance());
            textView2.setText(Html.fromHtml(getString(R.string.about_dialog_developed_by, new Object[]{"<b><a href=\"https://www.andromo.com\">Coding with Vp</a></b>"})));
            textView2.setVisibility(0);
        }
        TextView textView3 = (TextView) findViewById(R.id.build_version);
        if (textView3 != null) {
            textView3.setText(getString(R.string.about_dialog_build_version, new Object[]{"5.3.14/2.0"}));
        }
        TextView textView4 = (TextView) findViewById(R.id.copyright);
        if (textView4 != null) {
            textView4.setText(getString(R.string.about_dialog_copyright, new Object[]{"2020"}));
        }
        View findViewById = findViewById(R.id.analytics_notice);
        if (findViewById != null) {
            findViewById.setVisibility((AnalyticsUtils.isAnalyticsEnabledInPrefs(this) || AndromoFirebaseAnalytics.isFirebaseAnalyticsEnabledInPrefs(this) || AndromoFlurryAnalytics.isFlurryAnalyticsEnabled()) ? 0 : 8);
        }
        AnonymousClass1 r0 = new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    IntentUtils.openUrlInDefaultBrowserWithFallback(view.getContext(), "http://www.andromo.com/?utm_source=about&utm_medium=app&utm_campaign=andromo_app");
                } catch (ActivityNotFoundException unused) {
                    Toast.makeText(view.getContext(), R.string.error_loading_url, 0).show();
                }
            }
        };
        View findViewById2 = findViewById(R.id.about_separator);
        if (findViewById2 != null) {
            findViewById2.setVisibility(0);
        }
        TextView textView5 = (TextView) findViewById(R.id.about_andromo);
        if (textView5 != null) {
            textView5.setMovementMethod(LinkMovementMethod.getInstance());
            textView5.setText(Html.fromHtml(getString(R.string.about_dialog_andromo_blurb, new Object[]{"<b><a href=\"https://www.andromo.com\">Andromo</a></b>"})));
        }
        View findViewById3 = findViewById(R.id.andromo_logo);
        if (findViewById3 != null) {
            findViewById3.setOnClickListener(r0);
        }
        View findViewById4 = findViewById(R.id.andromo_banner);
        if (findViewById4 != null) {
            findViewById4.setOnClickListener(r0);
        }
        TextView textView6 = (TextView) findViewById(R.id.custom_text);
        if (textView6 != null) {
            textView6.setVisibility(8);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        AnalyticsUtils.activityStart(this, "About App");
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        AnalyticsUtils.activityStop(this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        AndromoFirebaseAnalytics.recordScreenView(this, "About " + getString(R.string.app_name), "About App");
    }
}
