package com.develeno.mylo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.develeno.mylo.FireBaseInteract;
import com.develeno.mylo.R;
import com.develeno.mylo.adapters.MyListAdapter;
import com.develeno.mylo.listeners.OnGetViewListener;
import com.develeno.mylo.others.TimeAgo;
import com.develeno.mylo.pojo.Reservation;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MyBookingsActivity extends AppCompatActivity {

    private static final int REFRESH = 45;
    private SwipeRefreshLayout refreshLayout;
    private ArrayList<Pair<String, Reservation>> list = new ArrayList<>();
    private MyListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);

        Toolbar toolbar = findViewById(R.id.MyToolbar);
        refreshLayout = findViewById(R.id.swipe_refresh_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Bookings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Setup ListView
        ListView listView = findViewById(R.id.listView);
        adapter = new MyListAdapter(list, new OnGetViewListener() {
            @Override
            public View onGetView(final int i, View view, ViewGroup viewGroup) {
                LayoutInflater layoutInflater = getLayoutInflater();
                View v = layoutInflater.inflate(R.layout.booking_item, viewGroup, false);

                TextView title = v.findViewById(R.id.title);
                TextView dealName = v.findViewById(R.id.price);
                TextView time = v.findViewById(R.id.tnc);
                TextView buy = v.findViewById(R.id.buy);

                final Reservation reservation = list.get(i).second;

                title.setText(reservation.getVendorName() + "");
                TimeAgo timeAgo = new TimeAgo();
                String timeAgoString = timeAgo.locale(getApplicationContext()).getTimeAgo(reservation.getReservationDate());
                time.setText(timeAgoString + ", " + reservation.getTimeSlot().replace("Between ", "").replace("&", "-"));
                dealName.setText(reservation.getDeal());
                String text = getButtonText(reservation);
                buy.setText(text);
                int backgroundResource = getBackgroundResource(reservation);
                buy.setBackgroundResource(backgroundResource);

                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (reservation.getStatus() == 1) {
                            QRActivity.setReservation(list.get(i));
                            Intent intent = new Intent(MyBookingsActivity.this, QRActivity.class);
                            MyBookingsActivity.this.startActivityForResult(intent, REFRESH);
                        }
                    }
                });

                return v;
            }
        });
        listView.setAdapter(adapter);
        listView.setVisibility(View.VISIBLE);
        findViewById(R.id.empty_layout).setVisibility(View.GONE);

        fetchMyActiveDeals();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchMyActiveDeals();
            }
        });
    }

    private int getBackgroundResource(Reservation reservation) {
        switch (reservation.getStatus()) {
            case 0:
                return R.drawable.layout_bg;
            case 1:
                return R.drawable.layout_bg2;
            case 2:
                return R.drawable.layout_bg_red;
            case 3:
                return R.drawable.layout_bg_grey;
            case 4:
                return R.drawable.layout_bg_grey;
        }
        return R.drawable.layout_bg2;
    }

    @NonNull
    private String getButtonText(Reservation reservation) {
        switch (reservation.getStatus()) {
            case 0:
                return "Pending";
            case 1:
                return "Confirmed";
            case 2:
                return "Rejected";
            case 3:
                return "Canceled";
            case 4:
                return "Scanned";
            case 5:
                return "Expired";
        }
        return "Unknown";
    }

    public void fetchMyActiveDeals() {
        list.clear();
        refreshLayout.setRefreshing(true);
        final FireBaseInteract fireBaseInteract = new FireBaseInteract(this);
        fireBaseInteract.fetchMyBookings(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                refreshLayout.setRefreshing(false);

                if (queryDocumentSnapshots.getDocuments().size() == 0) {
                    Toast.makeText(MyBookingsActivity.this, "You don't have any bookings", Toast.LENGTH_SHORT).show();
                    finish();
                }

                for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
//                    refreshLayout.setRefreshing(true);
//                    fireBaseInteract.fetchDealById(snapshot.getId(), new OnSuccessListener<DocumentSnapshot>() {
//                        @Override
//                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                    final Reservation reservation = snapshot.toObject(Reservation.class);
                    list.add(new Pair<>(snapshot.getId(), reservation));
                    adapter.notifyDataSetChanged();
                    refreshLayout.setRefreshing(false);
//                        }
//                    });
                }
            }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REFRESH && resultCode == RESULT_OK) {
            fetchMyActiveDeals();
        }
    }
}
