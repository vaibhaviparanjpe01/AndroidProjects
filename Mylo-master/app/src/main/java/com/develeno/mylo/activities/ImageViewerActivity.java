package com.develeno.mylo.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.develeno.mylo.R;
import com.develeno.mylo.adapters.ImagePagerAdapter;

import java.util.ArrayList;

public class ImageViewerActivity extends AppCompatActivity {

    private static ArrayList<String> links;
    private static Bitmap bm;
    private boolean centerInside;
    private int index;

    public static void setBitmap(Bitmap bitmap) {
        ImageViewerActivity.bm = bitmap;
    }

    public static void setLinks(ArrayList<String> links) {
        ImageViewerActivity.links = links;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_activity);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            centerInside = extras.getBoolean("centerInside", false);
            index = extras.getInt("index", 0);
        }

        ImageView close = findViewById(R.id.close);
        ImageView image = findViewById(R.id.image);
        /*if (bm != null) {
            image.setImageBitmap(bm);
        } else if (links != null) {
            if (centerInside) {
                image.setVisibility(View.VISIBLE);
                new MyHelper(this).loadImage(image, links.get(0));
                image.setOnClickListener(null);
            } else {
                image.setVisibility(View.VISIBLE);
                new MyHelper(this).loadImage(image, links.get(0));
            }
        } else {
            Toast.makeText(ImageViewerActivity.this, "No Image set", Toast.LENGTH_SHORT).show();
            finish();
        }*/
        close.setVisibility(View.VISIBLE);
        close.setOnClickListener(view -> finish());

        ViewPager pager = findViewById(R.id.pager);
        ImagePagerAdapter adapter = new ImagePagerAdapter(getSupportFragmentManager(), links, true);
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(5);
        pager.setVisibility(View.VISIBLE);
        pager.setCurrentItem(index);
        //pager.addOnPageChangeListener(getPageChangeListener());
    }
}
