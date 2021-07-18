package com.develeno.mylo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.develeno.mylo.R;
import com.develeno.mylo.adapters.MyPagerAdapter;
import com.develeno.mylo.fragments.SearchFragment;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private MyPagerAdapter adapter;
    private EditText searchbar;
    private ArrayList<Pair<String, Fragment>> fragments;

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setupViewPager();
        setupSearchBar();

        Intent intent = getIntent();
        if (intent != null) {
            boolean mode = intent.getBooleanExtra("mode", false);
            if (mode) {
                viewPager.setCurrentItem(1);
            }

            //  searchbar.setText(intent.getExtras().getString("q", ""));
        }
    }

    private void setupViewPager() {
        fragments = new ArrayList<>();
        Pair<String, Fragment> productSearchFragment = new Pair<String, Fragment>("Products", new SearchFragment());
        fragments.add(productSearchFragment);

        viewPager = findViewById(R.id.pager);
        tabLayout = findViewById(R.id.tabs);
        adapter = new MyPagerAdapter(fragments, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(5);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        searchbar.setHint("Search Products");
                        break;
                    case 1:
                        searchbar.setHint("Search Service Providers");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setupSearchBar() {
        searchbar = findViewById(R.id.search_bar_edittext);
        searchbar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Fragment fragment = fragments.get(viewPager.getCurrentItem()).second;
                if (charSequence.length() > 0) {
                    // ((SearchFragment) fragment).search(charSequence.toString().trim());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ImageView up = findViewById(R.id.up);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        searchbar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    search(v);
                    return true;
                }
                return false;
            }
        });
    }

    private void search(CharSequence charSequence) {

    }

    public void search(View view) {
        String charSequence = searchbar.getText().toString().trim();
//        search(charSequence);
        Fragment fragment = fragments.get(viewPager.getCurrentItem()).second;
        if (charSequence.length() > 0) {
            if (fragment != null) {
                ((SearchFragment) fragment).search(charSequence.trim());
            }
        }
        hideKeyboard(this);
    }
}
