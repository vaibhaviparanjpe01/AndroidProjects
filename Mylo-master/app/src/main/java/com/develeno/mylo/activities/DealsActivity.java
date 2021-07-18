package com.develeno.mylo.activities;

import android.os.Bundle;
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
import com.develeno.mylo.pojo.Deal;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import at.blogc.android.views.ExpandableTextView;

public class DealsActivity extends AppCompatActivity {

    private SwipeRefreshLayout refreshLayout;
    private ArrayList<Pair<String, Deal>> list = new ArrayList<>();
    private MyListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deals);

        Toolbar toolbar = findViewById(R.id.MyToolbar);
        refreshLayout = findViewById(R.id.swipe_refresh_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Active Deals");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Setup ListView
        ListView listView = findViewById(R.id.listView);
        adapter = new MyListAdapter(list, new OnGetViewListener() {
            @Override
            public View onGetView(final int i, View view, ViewGroup viewGroup) {
                LayoutInflater layoutInflater = getLayoutInflater();
                View v = layoutInflater.inflate(R.layout.deal_item, viewGroup, false);

                TextView title = v.findViewById(R.id.title);
                TextView price = v.findViewById(R.id.price);
                ExpandableTextView tnc = v.findViewById(R.id.tnc);
                TextView buy = v.findViewById(R.id.buy);

                final Deal deal = list.get(i).second;

                title.setText(deal.getDeal());
                price.setText("Rs." + deal.getPrice());
                tnc.setText(deal.getTnc());
                buy.setText("Redeem");

                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        QRActivity.setDeal(list.get(i));
//                        DealsActivity.this.startActivity(new Intent(DealsActivity.this, QRActivity.class));
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


    public void fetchMyActiveDeals() {
        list.clear();
        refreshLayout.setRefreshing(true);
        final FireBaseInteract fireBaseInteract = new FireBaseInteract(this);
        fireBaseInteract.fetchMyDeals(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                refreshLayout.setRefreshing(false);

                if (queryDocumentSnapshots.getDocuments().size() == 0) {
                    Toast.makeText(DealsActivity.this, "You don't have any active deals", Toast.LENGTH_SHORT).show();
                    finish();
                }

                for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                    refreshLayout.setRefreshing(true);
                    fireBaseInteract.fetchDealById(snapshot.getId(), new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            final Deal deal = documentSnapshot.toObject(Deal.class);
                            list.add(new Pair<>(documentSnapshot.getId(), deal));
                            adapter.notifyDataSetChanged();
                            refreshLayout.setRefreshing(false);
                        }
                    });
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
}
