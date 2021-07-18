package com.develeno.mylo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.develeno.mylo.R;

/**
 * Created by devel_000 on 13-Sep-15.
 */
public class FragmentCategories extends Fragment {

    private static RecyclerView gridView;
    private ProgressBar progressBar;
    private RelativeLayout errorLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.frag_home, container, false);

      /*  gridView = rootView.findViewById(R.id.gridView);
        errorLayout = rootView.findViewById(R.id.error_layout);
        progressBar = rootView.findViewById(R.id.progressBar);*/
/*
        if (Data.categories.size() == 0) {
            fetchCategories();
        } else {
            displayCategories();
        }


        errorLayout.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchCategories();
            }
        });*/
        return rootView;
    }
/*
    public static void scrollToTop() {
        gridView.scrollToPosition(0);
    }

    private void fetchCategories() {
       *//* final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading");
        dialog.setTitle("Please Wait");
        dialog.show();*//*

        progressBar.setVisibility(View.VISIBLE);
        errorLayout.setVisibility(View.GONE);
        if (Data.categories.size() == 0) {
            new FirebaseInteract(getActivity()).fetchCategories(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    //  dialog.dismiss();
                    //Toast.makeText(getContext(), task.getResult().size() + " categories fetched", Toast.LENGTH_SHORT).show();
                    ArrayList<Pair<String, Category>> categories = new ArrayList<>();
                    for (DocumentSnapshot snapshot : task.getResult().getDocuments()) {
                        Category category = snapshot.toObject(Category.class);
                        categories.add(new Pair<>(snapshot.getId(), category));
                    }
                    Data.categories = categories;
                    displayCategories();
                }
            });
        } else {
            //dialog.dismiss();
            displayCategories();
        }
    }

    private void displayCategories() {
        MainActivity activity = (MainActivity) getActivity();
        activity.getListener().getResponse(null);
        progressBar.setVisibility(View.GONE);
        if (Data.categories.size() > 0) {
            errorLayout.setVisibility(View.GONE);
            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
            gridView.setLayoutManager(layoutManager);
            CategoriesRecyclerAdapter adapter = new CategoriesRecyclerAdapter(getActivity(), Data.categories);
            gridView.setHasFixedSize(false);
            gridView.setAdapter(adapter);
            gridView.setVisibility(View.VISIBLE);
        } else {
            gridView.setVisibility(View.GONE);
            errorLayout.setVisibility(View.VISIBLE);
        }
    }*/

}
