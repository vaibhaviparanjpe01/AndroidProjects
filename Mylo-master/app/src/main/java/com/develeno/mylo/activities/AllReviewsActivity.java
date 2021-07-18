package com.develeno.mylo.activities;

import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.develeno.mylo.FireBaseInteract;
import com.develeno.mylo.R;
import com.develeno.mylo.adapters.MyListAdapter;
import com.develeno.mylo.pojo.Review;
import com.develeno.mylo.pojo.Vendor;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

public class AllReviewsActivity extends AppCompatActivity {

    private static Pair<String, Vendor> vendor;
    private SwipeRefreshLayout refreshLayout;
    private MyListAdapter adapter;
    private ArrayList<Pair<String, Review>> list = new ArrayList<>();

    public static void setVendor(Pair<String, Vendor> vendor) {
        AllReviewsActivity.vendor = vendor;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_reviews);

        Toolbar toolbar = findViewById(R.id.MyToolbar);
        refreshLayout = findViewById(R.id.swipe_refresh_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("All Reviews");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView listView = findViewById(R.id.listView);
        adapter = new MyListAdapter(list, (i, view, viewGroup) -> {
            LayoutInflater layoutInflater = getLayoutInflater();
            View v = layoutInflater.inflate(R.layout.review_item, viewGroup, false);

            TextView name = v.findViewById(R.id.name);
            RatingBar ratingBar = v.findViewById(R.id.ratingBar);
            TextView reviewText = v.findViewById(R.id.review);

            final Review review = list.get(i).second;

            name.setText(review.getUsername());
            reviewText.setText(review.getReview());
            ratingBar.setRating(review.getRating());

            return v;
        });
        listView.setAdapter(adapter);
        listView.setVisibility(View.VISIBLE);
        findViewById(R.id.empty_layout).setVisibility(View.GONE);

        fetchAllReviews();

        refreshLayout.setOnRefreshListener(this::fetchAllReviews);
    }

    private void fetchAllReviews() {
        refreshLayout.setRefreshing(true);
        final FireBaseInteract fireBaseInteract = new FireBaseInteract(this);
        fireBaseInteract.fetchReviewsForVendor(vendor.first, task -> {
            list.clear();
            for (DocumentSnapshot snapshot : task.getResult()) {
                list.add(new Pair<>(snapshot.getId(), snapshot.toObject(Review.class)));
            }
            adapter.notifyDataSetChanged();
            refreshLayout.setRefreshing(false);
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
