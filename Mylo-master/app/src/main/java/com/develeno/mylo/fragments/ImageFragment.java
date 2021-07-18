package com.develeno.mylo.fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.develeno.mylo.R;
import com.develeno.mylo.activities.ImageViewerActivity;
import com.develeno.mylo.pojo.MyHelper;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment {


    private ArrayList<String> links;
    private ImageView imageView;
    private AVLoadingIndicatorView indicatorView;
    private boolean centerInside;
    private PhotoView photo_view;
    private int index;

    public ImageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_image, container, false);

        imageView = inflate.findViewById(R.id.image);
        photo_view = inflate.findViewById(R.id.photo_view);
        indicatorView = inflate.findViewById(R.id.avi);

        Bundle arguments = getArguments();
        if (arguments != null) {
            index = arguments.getInt("index", 0);
        }

        if (!centerInside) {
            loadImage();
        } else {
            loadImagePhotoView();
        }
        return inflate;
    }

    private void loadImagePhotoView() {
        new MyHelper(getActivity()).loadImage(new Target() {
            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                photo_view.setImageBitmap(bitmap);
                photo_view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                //indicatorView.setRotation(90);
                if (errorDrawable != null) {
                    photo_view.setImageDrawable(errorDrawable);
                }
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        }, links.get(index));
    }

    public void loadImage() {
        new MyHelper(getActivity()).loadImage(new Target() {
            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                imageView.setOnClickListener(view -> {
                    ImageViewerActivity.setLinks(links);
                    Intent intent = new Intent(getActivity(), ImageViewerActivity.class);
                    intent.putExtra("centerInside", true);
                    intent.putExtra("index", index);
                    startActivity(intent);
                });
                imageView.setImageBitmap(bitmap);
                imageView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                //indicatorView.setRotation(90);
                if (errorDrawable != null) {
                    imageView.setImageDrawable(errorDrawable);
                }
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        }, links.get(index));
    }

    public void setLinks(ArrayList<String> links) {
        this.links = links;
    }

    public void setLinks(ArrayList<String> links, boolean centerInside) {
        this.links = links;
        this.centerInside = centerInside;
    }
}
