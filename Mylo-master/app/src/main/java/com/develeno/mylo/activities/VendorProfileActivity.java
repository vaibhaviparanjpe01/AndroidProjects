package com.develeno.mylo.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.develeno.mylo.FireBaseInteract;
import com.develeno.mylo.R;
import com.develeno.mylo.adapters.DealRecyclerAdapter;
import com.develeno.mylo.adapters.ImagePagerAdapter;
import com.develeno.mylo.adapters.IndicatorAdapter;
import com.develeno.mylo.adapters.ReviewRecyclerAdapter;
import com.develeno.mylo.others.MyHelper;
import com.develeno.mylo.pojo.Deal;
import com.develeno.mylo.pojo.Review;
import com.develeno.mylo.pojo.UserObject;
import com.develeno.mylo.pojo.Vendor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class VendorProfileActivity extends AppCompatActivity {

    private static Pair<String, Vendor> vendor;
    private Menu menu;
    private RatingBar ratingBar;
    private TextView review;
    private AppBarLayout appBarLayout;
    private ImagePagerAdapter adapter;
    private DealRecyclerAdapter recyclerAdapter;

    public static void setVendor(Pair<String, Vendor> vendor) {
        VendorProfileActivity.vendor = vendor;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_profile);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        Toolbar toolbar = findViewById(R.id.MyToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Vendor second = vendor.second;
        getSupportActionBar().setTitle(second.getName());
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        appBarLayout = findViewById(R.id.app_bar_layout);
        fetchDealsForVendor(vendor.first);
        fetchReviewsForVendor(vendor.first);
        checkForMyReview();

//        GoogleMapView googleMapView = findViewById(R.id.googleMapView);
//        googleMapView.setZoomable(this);
//        float latitude = (float) second.getCoordinates().getLatitude();
//        googleMapView.setLatitude(latitude);
//        float longitude = (float) second.getCoordinates().getLongitude();
//        googleMapView.setLongitude(longitude);
//        googleMapView.setVisibility(View.VISIBLE);

        TextView title = findViewById(R.id.name);
        TextView buy1 = findViewById(R.id.buy1);
        TextView subTitle = findViewById(R.id.sublabel);
        TextView desc = findViewById(R.id.desc);
        TextView address = findViewById(R.id.address);
        TextView timming = findViewById(R.id.timming);
        TextView avg_rating = findViewById(R.id.avg_rating);
        TextView num_reviews = findViewById(R.id.num_reviews);
        RatingBar ratingBar = findViewById(R.id.ratingBar);

        title.setText(second.getName());
        subTitle.setText(second.getSublabel());
        desc.setText(second.getDesc());

        if (second.getServices() != null && second.getServices().size() > 0) {
            String description = "";
            for (String s : second.getServices()) {
                description = description + "· " + s + "\n";
            }

            TextView text = findViewById(R.id.services_text);
            text.setText(description.trim());
        } else {
            findViewById(R.id.services_label).setVisibility(View.GONE);
            findViewById(R.id.services_card).setVisibility(View.GONE);
        }

        address.setText(second.getAddress());
        if (second.getTimming() != null) {
            timming.setText("Time : " + second.getTimming());
        } else {
            timming.setVisibility(View.GONE);
        }

        float rate = 0.0f;
        if (second.getRatingCount() > 0) {
            DecimalFormat df = new DecimalFormat("#.##");
            rate = second.getRatingTotal() / second.getRatingCount();
            String text = rate + "";
            String substring = text.substring(0, 3);
            avg_rating.setText(substring + "");
            ratingBar.setRating(rate);
            num_reviews.setText("(" + second.getRatingCount() + " Reviews)");
        } else {
//            findViewById(R.id.rating_layout).setVisibility(View.GONE);
//            findViewById(R.id.review_header).setVisibility(View.GONE);
            findViewById(R.id.review_body).setVisibility(View.GONE);
            findViewById(R.id.review_text).setVisibility(View.GONE);
        }

        if (!vendor.second.getDealsEnabled()) {
            buy1.setVisibility(View.GONE);
        }


        ViewPager pager = findViewById(R.id.pager);
        List<String> images = second.getImages();
        ArrayList<String> links = new ArrayList<>();
        links.addAll(images);
        adapter = new ImagePagerAdapter(getSupportFragmentManager(), links, false);
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(5);
        pager.addOnPageChangeListener(getPageChangeListener());
        LinearLayout indicatorLayout = findViewById(R.id.indicator);
        new IndicatorAdapter(adapter.getCount(), indicatorLayout, this, pager, R.drawable.dot_empty, R.drawable.dot_filled);


        LinearLayout menu = findViewById(R.id.menu);
        LinearLayout veg = findViewById(R.id.veg);
        LinearLayout cost = findViewById(R.id.cost);

        String category = vendor.second.getCategory();
        boolean isFoodCategory = category.equalsIgnoreCase("restaurants") || category.equalsIgnoreCase("cafe");
        if (!isFoodCategory) {
            veg.setVisibility(View.GONE);
            cost.setVisibility(View.GONE);
            findViewById(R.id.cuisines).setVisibility(View.GONE);
        } else {
            TextView cuisines = findViewById(R.id.cuisines);
            TextView costForTwo = findViewById(R.id.costForTwo);
            TextView vegText = findViewById(R.id.vegText);
            ImageView veg_icon = findViewById(R.id.veg_icon);
            costForTwo.setText("Rs." + vendor.second.getCostForTwo() + " for one");
            if (vendor.second.getCuisines() == null || vendor.second.getCuisines().trim().isEmpty()) {
                cuisines.setVisibility(View.GONE);
            } else {
                cuisines.setText("Cuisines: " + vendor.second.getCuisines());
            }
            vegText.setText(getVegStatus());
            veg_icon.setImageResource(getVegIcon());
            if (vendor.second.getVegStatus() == 2) {
                int width = MyHelper.dpToPx(54, getApplicationContext());
                int height = MyHelper.dpToPx(28, getApplicationContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
                veg_icon.setLayoutParams(params);
            }
        }

        if (second.getMenu() != null) {
            menu.setOnClickListener(v -> {
                ArrayList<String> menu1 = new ArrayList<>(second.getMenu());
                ImageViewerActivity.setLinks(menu1);
                Intent intent = new Intent(VendorProfileActivity.this, ImageViewerActivity.class);
                intent.putExtra("centerInside", true);
                startActivity(intent);
            });
        } else {
            menu.setVisibility(View.GONE);
        }

    }

    private int getVegIcon() {
        switch (vendor.second.getVegStatus()) {
            case 0:
                return R.drawable.veg;
            case 1:
                return R.drawable.non_veg;
            case 2:
                return R.drawable.veg_non_veg;
        }
        return R.drawable.veg;
    }

    private String getVegStatus() {
        switch (vendor.second.getVegStatus()) {
            case 0:
                return "Veg";
            case 1:
                return "Non-Veg";
            case 2:
                return "Veg & Non-Veg";
        }
        return "Unknown";
    }

    private ViewPager.OnPageChangeListener getPageChangeListener() {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                adapter.frags.get(i).loadImage();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        };
    }

    private void checkForMyReview() {
        new FireBaseInteract(this).checkForMyReview(vendor.first, new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.getResult().size() > 0) {
                    final DocumentSnapshot snapshot = task.getResult().getDocuments().get(0);
                    TextView addReview = findViewById(R.id.addReview);
                    addReview.setText("Edit your Review");
                    addReview.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showDialog(snapshot.toObject(Review.class), snapshot.getId());
                        }
                    });
                }
            }
        });
    }

    private void showDialog(final Review reviewObj, final String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(VendorProfileActivity.this);
        builder.setView(R.layout.add_review_dialog);
        builder.setPositiveButton("Post", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                postReview(reviewObj, id);
            }
        });
        builder.setNegativeButton("Discard", null);
        AlertDialog dialog = builder.create();
        dialog.show();
        View v = dialog.getWindow().getDecorView();
        ratingBar = v.findViewById(R.id.ratingBar);
        review = v.findViewById(R.id.review);
        ratingBar.setRating(reviewObj.getRating());
        review.setText(reviewObj.getReview());
    }

    private void postReview(Review r, String id) {
        float oldrating = r.getRating();
        float rating = ratingBar.getRating();
        String stringReview = review.getText().toString().trim();

        r.setRating(rating);
        r.setReview(stringReview);

        if (checkFields()) {
            final ProgressDialog dialog = new ProgressDialog(this);
            dialog.setTitle("Modifying Review");
            dialog.setMessage("Please wait");
            // dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            //findViewById(R.id.addReview).setVisibility(View.GONE);
            new FireBaseInteract(this).modifyReview(id, r, oldrating, vendor, new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    dialog.dismiss();
                    Toast.makeText(VendorProfileActivity.this, "Successfully edited review", Toast.LENGTH_SHORT).show();
                    fetchReviewsForVendor(vendor.first);
                }
            });
        }
    }

    private void fetchReviewsForVendor(String vendorId) {
        new FireBaseInteract(this).fetchReviewsForVendor(vendorId, new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                refreshLayout.setRefreshing(false);
                ArrayList<Pair<String, Review>> list = new ArrayList<>();
                for (DocumentSnapshot snapshot : task.getResult()) {
                    list.add(new Pair<>(snapshot.getId(), snapshot.toObject(Review.class)));
                    if (list.size() >= 3) {
                        break;
                    }
                }
                updateReviewList(list);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void updateReviewList(ArrayList<Pair<String, Review>> list) {
        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        if (list.size() > 0) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            ReviewRecyclerAdapter recyclerAdapter = new ReviewRecyclerAdapter(this, list, vendor);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(recyclerAdapter);
            recyclerView.setVisibility(View.VISIBLE);
            findViewById(R.id.rating_layout).setVisibility(View.VISIBLE);
            findViewById(R.id.review_header).setVisibility(View.VISIBLE);
            findViewById(R.id.review_body).setVisibility(View.VISIBLE);
            findViewById(R.id.review_text).setVisibility(View.VISIBLE);
//            findViewById(R.id.empty).setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.GONE);
            findViewById(R.id.review_text).setVisibility(View.GONE);
            // findViewById(R.id.addReview).setVisibility(View.GONE);
            findViewById(R.id.review_body).setVisibility(View.GONE);
        }

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllReviewsActivity.setVendor(vendor);
                startActivity(new Intent(VendorProfileActivity.this, AllReviewsActivity.class));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.bookmark:
                if (!FireBaseInteract.userObject.getFav().contains(vendor.first)) {
                    FireBaseInteract.userObject.addToFav(vendor.first);
                    Toast.makeText(VendorProfileActivity.this, "Added to Favorites", Toast.LENGTH_SHORT).show();
                } else {
                    FireBaseInteract.userObject.removeFromFav(vendor.first);
                    Toast.makeText(VendorProfileActivity.this, "Removed", Toast.LENGTH_SHORT).show();
                }
                new FireBaseInteract(this).saveUser(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
                updateActionBarButtons();
                break;
        }
        return true;
    }

    private void updateActionBarButtons() {
        if (FireBaseInteract.userObject.getFav().contains(vendor.first)) {
            menu.getItem(0).setIcon(R.drawable.heart_filled);
        } else {
            menu.getItem(0).setIcon(R.drawable.heart);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_vendor_page, menu);
        this.menu = menu;
        updateActionBarButtons();
        return true;
    }

    public void fetchDealsForVendor(String vendorId) {
        findViewById(R.id.rating_layout).setVisibility(View.GONE);
//        refreshLayout.setRefreshing(true);
        new FireBaseInteract(this).fetchDealsForVendor(vendorId, new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                refreshLayout.setRefreshing(false);
                ArrayList<Pair<String, Deal>> list = new ArrayList<>();
                for (DocumentSnapshot snapshot : task.getResult()) {
                    list.add(new Pair<>(snapshot.getId(), snapshot.toObject(Deal.class)));
//                    list.add(new Pair<>(snapshot.getId(), snapshot.toObject(Deal.class)));
//                    list.add(new Pair<>(snapshot.getId(), snapshot.toObject(Deal.class)));
                }
                updateList(list);
            }
        });
    }

    private void updateList(ArrayList<Pair<String, Deal>> list) {
        LinearLayout ratingLayout = findViewById(R.id.rating_layout);
        TextView avgRating = findViewById(R.id.avg_rating);
        TextView numReviews = findViewById(R.id.num_reviews);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        float avgRatingInt = 0;
        try {
            avgRatingInt = vendor.second.getRatingTotal() / vendor.second.getRatingCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String text = avgRatingInt + "";
        avgRating.setText(text.substring(0, 3));
        ratingBar.setRating(avgRatingInt);
        numReviews.setText("(" + vendor.second.getRatingCount() + " Reviews)");

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        if (list.size() > 0) {
//            ratingLayout.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerAdapter = new DealRecyclerAdapter(this, vendor.second, list);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(recyclerAdapter);
            recyclerView.setVisibility(View.VISIBLE);
            findViewById(R.id.deals_label).setVisibility(View.VISIBLE);
            findViewById(R.id.openDeal).setVisibility(View.VISIBLE);
        } else {
            ratingLayout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
            findViewById(R.id.deals_label).setVisibility(View.GONE);
            findViewById(R.id.openDeal).setVisibility(View.GONE);
        }
    }

    public void directions(View view) {
        final GeoPoint coordinates = vendor.second.getCoordinates();
        final double lat = coordinates.getLatitude();
        final double longi = coordinates.getLongitude();
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?daddr=" + lat + "," + longi));
        startActivity(intent);
    }

    public void addReview(View view) {
        showDialog();
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(VendorProfileActivity.this);
        builder.setView(R.layout.add_review_dialog);
        builder.setPositiveButton("Post", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                postReview();
            }
        });
        builder.setNegativeButton("Discard", null);
        AlertDialog dialog = builder.create();
        dialog.show();
        View v = dialog.getWindow().getDecorView();
        ratingBar = v.findViewById(R.id.ratingBar);
        review = v.findViewById(R.id.review);
    }

    private void postReview() {
        float rating = ratingBar.getRating();
        String stringReview = review.getText().toString().trim();

        Review r = new Review();
        r.setRating(rating);
        r.setReview(stringReview);
        r.setCreatedOn(Calendar.getInstance().getTime());
        UserObject userObject = FireBaseInteract.userObject;
        r.setUserId(userObject.getNumber());
        r.setUsername(userObject.getName());
        r.setVendorId(vendor.first);

        if (checkFields()) {
            final ProgressDialog dialog = new ProgressDialog(this);
            dialog.setTitle("Adding Review");
            dialog.setMessage("Please wait");
            // dialog.setCanceledOnTouchOutside(false);
            dialog.show();

            new FireBaseInteract(this).postReview(r, vendor, new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    dialog.dismiss();
                    Toast.makeText(VendorProfileActivity.this, "Successfully posted review", Toast.LENGTH_SHORT).show();
                    fetchReviewsForVendor(vendor.first);
                    checkForMyReview();
                }
            });
        }
    }

    private boolean checkFields() {
        float numStars = ratingBar.getRating();
        if (numStars == 0) {
            showDialog();
            Toast.makeText(this, "Rating not given", Toast.LENGTH_SHORT).show();
            return false;
        }

        String text = review.getText().toString();
        if (text.length() < 6) {
            showDialog();
            ratingBar.setRating(numStars);
            review.setText(text);
            review.setError("too short");
            return false;
        }
        return true;
    }

    public void collapse(View view) {
        appBarLayout.setExpanded(false);
    }

    public void showAllReviews(View view) {
        AllReviewsActivity.setVendor(vendor);
        startActivity(new Intent(this, AllReviewsActivity.class));
    }

    public void openDeal(View view) {
        recyclerAdapter.open();
    }
}
