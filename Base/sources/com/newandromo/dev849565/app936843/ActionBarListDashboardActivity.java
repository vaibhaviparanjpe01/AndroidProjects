package com.newandromo.dev849565.app936843;

import android.view.View;
import android.widget.AdapterView;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public abstract class ActionBarListDashboardActivity extends DashboardActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "ActionBarListActivity";
    private ListView mListView;

    /* access modifiers changed from: protected */
    public void onListItemClick(ListView listView, View view, int i, long j) {
    }

    /* access modifiers changed from: protected */
    public ListView getListView() {
        if (this.mListView == null) {
            this.mListView = (ListView) findViewById(16908298);
            this.mListView.setOnItemClickListener(this);
        }
        return this.mListView;
    }

    /* access modifiers changed from: protected */
    public void setListAdapter(ListAdapter listAdapter) {
        getListView().setAdapter(listAdapter);
    }

    /* access modifiers changed from: protected */
    public ListAdapter getListAdapter() {
        ListAdapter adapter = getListView().getAdapter();
        return adapter instanceof HeaderViewListAdapter ? ((HeaderViewListAdapter) adapter).getWrappedAdapter() : adapter;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        onListItemClick((ListView) adapterView, view, i, j);
    }
}
