package com.develeno.mylo.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.develeno.mylo.R;
import com.develeno.mylo.others.MoreListItem;

import java.util.ArrayList;

/**
 * Created by devel_000 on 18-Sep-15.
 */
public class MoreListAdapter extends BaseAdapter {
    private ArrayList<MoreListItem> items;
    private Context context;

    public MoreListAdapter(Context context, ArrayList<MoreListItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.more_list_item, viewGroup, false);
        TextView textView = layout.findViewById(R.id.textView);
        TextView desctv = layout.findViewById(R.id.desc);
        ImageView icon = layout.findViewById(R.id.imageView);
        MoreListItem moreListItem = items.get(i);
        textView.setText(moreListItem.getTitle());
        desctv.setText(moreListItem.getDesc());
        if (moreListItem.getImageId() != 0) {
            icon.setImageResource(moreListItem.getImageId());
        } else {
            icon.setVisibility(View.GONE);
        }

        final Intent intent = moreListItem.getIntent();
        View.OnClickListener listener = moreListItem.getOnClickListener() == null ? new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (intent != null) {
                    context.startActivity(intent);
                } else {
                }
            }
        } : moreListItem.getOnClickListener();
        layout.setOnClickListener(listener);

        return layout;
    }
}
