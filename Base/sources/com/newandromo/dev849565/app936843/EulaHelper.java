package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.widget.TextView;
import com.newandromo.dev849565.app936843.CountryTask;
import java.util.Locale;

public class EulaHelper {
    private static final String TAG = "EulaHelper";

    public static boolean hasAcceptedEula(Context context) {
        return true;
    }

    public static boolean hasShownLaunchNotice(Context context) {
        return true;
    }

    public static void initialize(final AndromoActivity andromoActivity) {
        if (!hasAcceptedEula(andromoActivity) || !hasShownLaunchNotice(andromoActivity)) {
            AdManager.setDelayedAdLoading(true);
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(andromoActivity);
        if (defaultSharedPreferences.contains("eu_country")) {
            boolean z = defaultSharedPreferences.getBoolean("eu_country", false);
            AndromoApplication.onEUCountryKnown(andromoActivity, z);
            AdManager.setDisabledNetworksForGDPR(z);
            AdManager.setPersonalizedAdsConsent(andromoActivity, !z);
            if (!hasAcceptedEula(andromoActivity)) {
                showEula(false, andromoActivity);
            } else {
                handleLaunchNotice(andromoActivity);
            }
        } else {
            AdManager.setDelayedAdLoading(true);
            new CountryTask(andromoActivity, new CountryTask.OnCompletedListener() {
                public void onCompleted(String str) {
                    boolean isEuropeanUnion = EulaHelper.isEuropeanUnion(str);
                    EulaHelper.setEuropeanUnion(andromoActivity, isEuropeanUnion);
                    AndromoApplication.onEUCountryKnown(andromoActivity, isEuropeanUnion);
                    AdManager.setDisabledNetworksForGDPR(isEuropeanUnion);
                    AdManager.setPersonalizedAdsConsent(andromoActivity, !isEuropeanUnion);
                    if (!EulaHelper.hasAcceptedEula(andromoActivity)) {
                        EulaHelper.showEula(false, andromoActivity);
                    } else {
                        EulaHelper.handleLaunchNotice(andromoActivity);
                    }
                }
            }).execute(new Void[0]);
        }
    }

    /* access modifiers changed from: private */
    public static void setAcceptedEula(Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("accepted_eula", true).apply();
    }

    public static void showEula(boolean z, final AndromoActivity andromoActivity) {
        TextView textView;
        AlertDialog.Builder cancelable = new AlertDialog.Builder(andromoActivity).setTitle((int) R.string.eula_title).setMessage((CharSequence) Html.fromHtml(andromoActivity.getString(R.string.eula_text))).setCancelable(z);
        switch (2) {
            case 1:
                cancelable.setIconAttribute(16843605);
                break;
            case 2:
                cancelable.setIcon((int) R.drawable.ic_launcher_icon);
                break;
        }
        if (z) {
            cancelable.setPositiveButton(17039370, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    EulaHelper.handleLaunchNotice(andromoActivity);
                }
            });
        } else {
            cancelable.setPositiveButton((int) R.string.accept, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    EulaHelper.setAcceptedEula(andromoActivity);
                    dialogInterface.dismiss();
                    EulaHelper.handleLaunchNotice(andromoActivity);
                }
            }).setNegativeButton((int) R.string.decline, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                    andromoActivity.finish();
                }
            });
        }
        AlertDialog show = cancelable.show();
        if (show != null && (textView = (TextView) show.findViewById(16908299)) != null) {
            String string = andromoActivity.getString(R.string.failed_to_open_url);
            ErrorHandlingLinkMovementMethod.getInstance();
            textView.setMovementMethod(ErrorHandlingLinkMovementMethod.withErrorMessage(string));
        }
    }

    public static void handleLaunchNotice(AndromoActivity andromoActivity) {
        boolean isEuropeanUnion = isEuropeanUnion((Context) andromoActivity);
        if (!hasShownLaunchNotice(andromoActivity)) {
            if (isEuropeanUnion) {
                showLaunchNotice(andromoActivity);
            } else if (AdManager.isDelayedAdLoadingEnabled()) {
                andromoActivity.onDelayedAdLoad();
            }
        } else if (AdManager.isDelayedAdLoadingEnabled()) {
            andromoActivity.onDelayedAdLoad();
        }
    }

    /* access modifiers changed from: private */
    public static void setLaunchNoticeShown(Context context, boolean z) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("launch_notice_shown", z).apply();
    }

    private static boolean isEuropeanUnionKnown(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).contains("eu_country");
    }

    public static boolean isEuropeanUnion(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("eu_country", false);
    }

    public static void setEuropeanUnion(Context context, boolean z) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("eu_country", z).apply();
    }

    public static boolean isEuropeanUnion(String str) {
        if (str == null || str.length() != 2) {
            return false;
        }
        String upperCase = str.toUpperCase(Locale.US);
        if ("BE".equals(upperCase) || "BG".equals(upperCase) || "CZ".equals(upperCase) || "DK".equals(upperCase) || "DE".equals(upperCase) || "EE".equals(upperCase) || "IE".equals(upperCase) || "GR".equals(upperCase) || "ES".equals(upperCase) || "FR".equals(upperCase) || "HR".equals(upperCase) || "IT".equals(upperCase) || "CY".equals(upperCase) || "LV".equals(upperCase) || "LT".equals(upperCase) || "LU".equals(upperCase) || "HU".equals(upperCase) || "MT".equals(upperCase) || "NL".equals(upperCase) || "AT".equals(upperCase) || "PL".equals(upperCase) || "PT".equals(upperCase) || "RO".equals(upperCase) || "SI".equals(upperCase) || "SK".equals(upperCase) || "FI".equals(upperCase) || "SE".equals(upperCase) || "UK".equals(upperCase) || "GB".equals(upperCase) || "EU".equals(upperCase)) {
            return true;
        }
        return false;
    }

    public static void showLaunchNotice(final AndromoActivity andromoActivity) {
        TextView textView;
        AlertDialog.Builder cancelable = new AlertDialog.Builder(andromoActivity).setTitle((int) R.string.launch_notice_title).setMessage((CharSequence) Html.fromHtml(andromoActivity.getString(R.string.launch_notice_text))).setCancelable(false);
        switch (2) {
            case 1:
                cancelable.setIconAttribute(16843605);
                break;
            case 2:
                cancelable.setIcon((int) R.drawable.ic_launcher_icon);
                break;
        }
        cancelable.setPositiveButton(17039370, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                EulaHelper.setLaunchNoticeShown(andromoActivity, true);
                andromoActivity.onDelayedAdLoad();
                dialogInterface.dismiss();
            }
        });
        AlertDialog show = cancelable.show();
        if (show != null && (textView = (TextView) show.findViewById(16908299)) != null) {
            String string = andromoActivity.getString(R.string.failed_to_open_url);
            ErrorHandlingLinkMovementMethod.getInstance();
            textView.setMovementMethod(ErrorHandlingLinkMovementMethod.withErrorMessage(string));
        }
    }
}
