package com.develeno.mylo.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.develeno.mylo.R;
import com.develeno.mylo.activities.VendorProfileActivity;
import com.develeno.mylo.others.MyHelper;
import com.develeno.mylo.pojo.Vendor;

import java.util.ArrayList;

public class VendorRecyclerAdapter extends RecyclerView.Adapter<VendorRecyclerAdapter.ViewHolder> {

    private final Activity activity;
    private ArrayList<Pair<String, Vendor>> mData;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    public VendorRecyclerAdapter(Activity activity, ArrayList<Pair<String, Vendor>> data) {
        this.activity = activity;
        this.mInflater = LayoutInflater.from(activity);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.vendor_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the textview in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Pair<String, Vendor> stringVendorPair = mData.get(position);
        final Vendor vendor = stringVendorPair.second;
        String name = vendor.getName();
        holder.name.setText(name);
        holder.sublabel.setText(vendor.getSublabel());
        float rating = 0;
        if (vendor.getRatingCount() > 0) {
            rating = vendor.getRatingTotal() / vendor.getRatingCount();
        }
        holder.ratingBar.setRating(rating);
        holder.reviewCount.setText("(" + vendor.getRatingCount() + " Reviews)");
        holder.text.setText(vendor.getText() + "");
        assert vendor != null;
        new MyHelper(activity).loadImage(holder.icon, vendor.getImages().get(0));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VendorProfileActivity.setVendor(mData.get(position));
                activity.startActivity(new Intent(activity, VendorProfileActivity.class));
            }
        });

        if (vendor.getDealsEnabled()) {
            holder.buy.setText("Book Now");
        } else {
            holder.buy.setText("Open");
        }

        if (vendor.getSublabel2() != null && !vendor.getSublabel2().isEmpty()) {
            holder.text2.setVisibility(View.VISIBLE);
            holder.text2.setText(vendor.getSublabel2() + "");
        } else {
            holder.text2.setVisibility(View.GONE);
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // convenience method for getting data at click position
    public Pair<String, Vendor> getItem(int id) {
        return mData.get(id);
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon;
        TextView name;
        TextView buy;
        RatingBar ratingBar;
        TextView reviewCount;
        TextView text;
        TextView text2;
        private TextView sublabel;

        ViewHolder(View itemView) {
            super(itemView);
            buy = itemView.findViewById(R.id.buy);
            name = itemView.findViewById(R.id.name);
            sublabel = itemView.findViewById(R.id.sublabel);
            icon = itemView.findViewById(R.id.image);
            reviewCount = itemView.findViewById(R.id.reviewCount);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            text = itemView.findViewById(R.id.text);
            text2 = itemView.findViewById(R.id.text2);
        }
    }
}