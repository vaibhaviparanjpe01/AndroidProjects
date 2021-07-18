package com.develeno.mylo.others;

import android.content.Intent;
import android.view.View;

/**
 * Created by devel_000 on 22-Sep-15.
 */
public class MoreListItem {

    private View.OnClickListener onClickListener;
    private int imageId;
    private String title;
    private String desc;
    private Intent intent;

    public MoreListItem(String title, String desc, int imageId, Intent intent) {
        this.desc = desc;
        this.title = title;
        this.intent = intent;
        this.imageId = imageId;
    }

    public MoreListItem(String title, String desc, int imageId, View.OnClickListener onClickListener) {
        this.desc = desc;
        this.title = title;
        this.onClickListener = onClickListener;
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public Intent getIntent() {
        return intent;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }
}
