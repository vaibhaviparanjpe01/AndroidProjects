package com.develeno.mylo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.develeno.mylo.R;
import com.develeno.mylo.activities.EnterNumberActivity;


/**
 * Created by devel_000 on 18-Sep-15.
 */
public class FragmentGetStarted extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.activity_splash, container, false);
        rootView.findViewById(R.id.splash).setVisibility(View.GONE);
        Button btn = rootView.findViewById(R.id.getstarted);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), EnterNumberActivity.class));
            }
        });
        return rootView;
    }


}

