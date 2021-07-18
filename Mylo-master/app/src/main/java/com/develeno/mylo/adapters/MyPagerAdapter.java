package com.develeno.mylo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.util.Pair;

import java.util.ArrayList;

/**
 * Created by devel_000 on 03-Dec-17.
 */
public class MyPagerAdapter extends FragmentStatePagerAdapter {
    private final ArrayList<Pair<String, Fragment>> fragments;

    public MyPagerAdapter(ArrayList<Pair<String, Fragment>> fragments, FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position).second;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).first;
    }

}
