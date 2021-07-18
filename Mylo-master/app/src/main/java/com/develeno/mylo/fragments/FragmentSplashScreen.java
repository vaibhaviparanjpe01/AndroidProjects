package com.develeno.mylo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.develeno.mylo.R;


/**
 * Created by devel_000 on 18-Sep-15.
 */
public class FragmentSplashScreen extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        int selection = getArguments().getInt("selection");

        ViewGroup rootView;
        switch (selection) {
            case 1:
                rootView = (ViewGroup) inflater.inflate(
                        R.layout.splash_1, container, false);
                break;
            case 2:
                rootView = (ViewGroup) inflater.inflate(
                        R.layout.splash_2, container, false);
                break;
            case 3:
                rootView = (ViewGroup) inflater.inflate(
                        R.layout.splash_3, container, false);
                break;
            case 4:
                rootView = (ViewGroup) inflater.inflate(
                        R.layout.splash_4, container, false);
                break;
            default:
                rootView = (ViewGroup) inflater.inflate(
                        R.layout.splash_1, container, false);
                break;
        }
        return rootView;
    }
}

