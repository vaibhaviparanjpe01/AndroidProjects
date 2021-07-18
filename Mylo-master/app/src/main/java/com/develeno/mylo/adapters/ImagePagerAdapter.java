package com.develeno.mylo.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.develeno.mylo.fragments.ImageFragment;

import java.util.ArrayList;

/**
 * Created by devel_000 on 17-Aug-17.
 */
public class ImagePagerAdapter extends FragmentPagerAdapter {
    public ArrayList<ImageFragment> frags = new ArrayList<>();
    private ArrayList<String> links = new ArrayList<>();

    public ImagePagerAdapter(FragmentManager supportFragmentManager, ArrayList<String> links, boolean centerInside) {
        super(supportFragmentManager);
        int index = 0;
        for (String link : links) {
            if (link.trim().length() > 0) {
                this.links.add(link);
                ImageFragment imageFragment = new ImageFragment();
                Bundle args = new Bundle();
                args.putInt("index", index);
                imageFragment.setArguments(args);
                imageFragment.setLinks(links, centerInside);
                this.frags.add(imageFragment);
            }
            index++;
        }
    }

    @Override
    public Fragment getItem(int position) {
        /*ImageFragment imageFragment = new ImageFragment();
        imageFragment.setLink(links.get(position));
        return imageFragment;*/
        return frags.get(position);
    }

    @Override
    public int getCount() {
        return links.size();
    }
}
