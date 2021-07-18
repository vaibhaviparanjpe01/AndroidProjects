package com.newandromo.dev849565.app936843;

import android.content.Context;
import android.content.Intent;

public class ActivityLauncher implements Runnable {
    private final Context context;
    private final Intent intent;

    public ActivityLauncher(Context context2, Intent intent2) {
        this.context = context2;
        this.intent = intent2;
    }

    public void run() {
        this.context.startActivity(this.intent);
    }
}
