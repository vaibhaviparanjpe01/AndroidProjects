package com.develeno.mylo.listeners;


import com.develeno.mylo.adapters.CommonRecyclerAdapter;

/**
 * Created by devel_000 on 04-Dec-17.
 */
public interface OnBindViewListener {
     void onGetView(CommonRecyclerAdapter.ViewHolder holder, int position);
}
