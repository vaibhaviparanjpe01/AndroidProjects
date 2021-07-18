package com.develeno.mylo.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.develeno.mylo.listeners.OnGetViewListener;

import java.util.ArrayList;

/**
 * Created by devel_000 on 04-Dec-17.
 */
public class MyListAdapter extends BaseAdapter {
    private ArrayList<?> objects;
    private OnGetViewListener listener;

    public MyListAdapter(ArrayList<?> objects, OnGetViewListener listener) {
        this.objects = objects;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int i) {
        return objects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return listener.onGetView(i, view, viewGroup);
    }
}
