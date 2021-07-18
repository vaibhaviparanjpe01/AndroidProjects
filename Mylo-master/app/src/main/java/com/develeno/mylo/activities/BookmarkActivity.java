package com.develeno.mylo.activities;

import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.develeno.mylo.FireBaseInteract;
import com.develeno.mylo.R;
import com.develeno.mylo.adapters.VendorRecyclerAdapter;
import com.develeno.mylo.pojo.Vendor;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

public class BookmarkActivity extends AppCompatActivity {


    private SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<Pair<String, Vendor>> list = new ArrayList<>();
    private VendorRecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        Toolbar toolbar = findViewById(R.id.MyToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Favourites");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchWishList();
            }
        });

        //Setup ListView
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerAdapter = new VendorRecyclerAdapter(this, list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setVisibility(View.VISIBLE);
        findViewById(R.id.empty_layout).setVisibility(View.GONE);

        //fetchWishList();
    }

    private void fetchWishList() {
        list.clear();
        findViewById(R.id.empty_layout).setVisibility(View.GONE);
        final FireBaseInteract interact = new FireBaseInteract(this);
        for (String id : FireBaseInteract.userObject.getFav()) {
            swipeRefreshLayout.setRefreshing(true);
            interact.fetchVendorById(id, new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    list.add(new Pair<>(documentSnapshot.getId(), documentSnapshot.toObject(Vendor.class)));
                    recyclerAdapter.notifyDataSetChanged();
                    recyclerView.setVisibility(View.VISIBLE);
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
        }
        if (FireBaseInteract.userObject.getFav().size() == 0) {
            findViewById(R.id.empty_layout).setVisibility(View.VISIBLE);
            recyclerAdapter.notifyDataSetChanged();
            recyclerView.setVisibility(View.GONE);
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchWishList();
    }

    /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_account_details, menu);
        return true;
    }*/

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
