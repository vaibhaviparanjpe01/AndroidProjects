package com.develeno.mylo.adapters;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.develeno.mylo.R;


/**
 * Created by devel_000 on 30-Aug-15.
 */
public class IndicatorAdapter {
    private int selected;
    private LinearLayout indicator;
    private Context context;
    private ImageView[] dots;
    private int totalDots;
    private ViewPager viewPager;
    private int selectedDotID;
    private int unselectedDotID;

    public IndicatorAdapter(int totalDots, LinearLayout indicator, Context context, ViewPager viewPager) {
        this.totalDots = totalDots;
        this.context = context;
        this.indicator = indicator;
        this.viewPager = viewPager;
        dots = new ImageView[totalDots];
        this.unselectedDotID = R.drawable.dot_empty;
        this.selectedDotID = R.drawable.dot_filled;
        createDots();
        setSelected(0);
        setPageChangeListener(viewPager);
    }

    public IndicatorAdapter(int totalDots, LinearLayout indicator, Context context, ViewPager viewPager, int unselectedDotID, int selectedDotID) {
        this.totalDots = totalDots;
        this.context = context;
        this.indicator = indicator;
        this.viewPager = viewPager;
        dots = new ImageView[totalDots];
        this.unselectedDotID = unselectedDotID;
        this.selectedDotID = selectedDotID;
        createDots();
        setSelected(0);
        setPageChangeListener(viewPager);
    }


    private void setPageChangeListener(ViewPager viewPager) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setSelected(int i) {
        dots[selected].setImageResource(unselectedDotID);
        selected = i;
        dots[i].setImageResource(selectedDotID);
    }

    private void createDots() {
        for (int i = 0; i < totalDots; i++) {
            dots[i] = createDot(i);
        }
    }

    /**
     * Method to create a single Empty indicator dot.
     *
     * @param i
     */
    private ImageView createDot(final int i) {
        ImageView imageView = new ImageView(context);

        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, context.getResources().getDisplayMetrics());
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, context.getResources().getDisplayMetrics());
        int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, context.getResources().getDisplayMetrics());
        imageView.setImageResource(unselectedDotID);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        params.setMargins(margin, margin, margin, margin);
        imageView.setLayoutParams(params);

        indicator.addView(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(i);
            }
        });

        return imageView;
    }

    public void setSelectedDotID(int selectedDotID) {
        this.selectedDotID = selectedDotID;
    }

    public void setUnselectedDotID(int unselectedDotID) {
        this.unselectedDotID = unselectedDotID;
    }
}
