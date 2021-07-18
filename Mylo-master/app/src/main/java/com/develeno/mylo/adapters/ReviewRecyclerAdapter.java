package com.develeno.mylo.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.develeno.mylo.R;
import com.develeno.mylo.activities.AllReviewsActivity;
import com.develeno.mylo.pojo.Review;
import com.develeno.mylo.pojo.Vendor;

import java.util.ArrayList;

public class ReviewRecyclerAdapter extends RecyclerView.Adapter<ReviewRecyclerAdapter.ViewHolder> {

    private final Activity activity;
    private final Pair<String, Vendor> vendor;
    private ArrayList<Pair<String, Review>> mData;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    public ReviewRecyclerAdapter(Activity activity, ArrayList<Pair<String, Review>> data, Pair<String, Vendor> vendor) {
        this.activity = activity;
        this.mInflater = LayoutInflater.from(activity);
        this.mData = data;
        this.vendor = vendor;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.review_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the textview in each row
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Pair<String, Review> stringDealPair = mData.get(position);
        final Review review = stringDealPair.second;
        holder.name.setText(review.getUsername());
        holder.review.setText(review.getReview());
        holder.ratingBar.setRating(review.getRating());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllReviewsActivity.setVendor(vendor);
                activity.startActivity(new Intent(activity, AllReviewsActivity.class));
            }
        });
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // convenience method for getting data at click position
    public Pair<String, Review> getItem(int id) {
        return mData.get(id);
    }

    // stores and recycles views as they are scrolled off screen
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView review;
        RatingBar ratingBar;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            review = itemView.findViewById(R.id.review);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }
    }
}