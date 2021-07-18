package com.develeno.mylo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.develeno.mylo.FireBaseInteract;
import com.develeno.mylo.R;
import com.develeno.mylo.adapters.MyListAdapter;
import com.develeno.mylo.adapters.VendorRecyclerAdapter;
import com.develeno.mylo.pojo.Vendor;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    private View view;
    private MyListAdapter adapter;
    private ArrayList<Pair<String, Vendor>> results = new ArrayList<>();
    private View empty;
    private SwipeRefreshLayout refreshLayout;
    private String query;
    private VendorRecyclerAdapter recyclerAdapter;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search, container, false);
        empty = view.findViewById(R.id.empty_layout);
        refreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                search(query);
            }
        });
        setupListView();
        return view;
    }

    public void search(String query) {
        this.query = query;
        refreshLayout.setRefreshing(true);
        results.clear();
        new FireBaseInteract(getActivity()).searchVendors(query, new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot querySnapshot) {

                for (DocumentSnapshot snapshot : querySnapshot.getDocuments()) {
                    Vendor listing = snapshot.toObject(Vendor.class);
                    results.add(new Pair<>(snapshot.getId(), listing));
                }

                refreshLayout.setRefreshing(false);
                recyclerAdapter.notifyDataSetChanged();

                if (results.size() == 0) {
                    empty.setVisibility(View.VISIBLE);
                } else {
                    empty.setVisibility(View.GONE);
                }
            }
        });
    }

    private void setupListView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerAdapter = new VendorRecyclerAdapter(getActivity(), results);
        RecyclerView recyclerView = view.findViewById(R.id.listView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setVisibility(View.VISIBLE);
        view.findViewById(R.id.empty_layout).setVisibility(View.GONE);
    }
}
