package com.develeno.mylo.fragments;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.develeno.mylo.FireBaseInteract;
import com.develeno.mylo.R;
import com.develeno.mylo.activities.ChatActivity;
import com.develeno.mylo.activities.MainActivity;
import com.develeno.mylo.adapters.VendorRecyclerAdapter;
import com.develeno.mylo.pojo.Vendor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;
import com.wooplr.spotlight.SpotlightView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;

/**
 * Created by devel_000 on 13-Sep-15.
 */
public class FragmentHome extends Fragment {


    public static FloatingActionButton fab;
    public Switch locationSwitch;
    private ViewGroup rootView;
    private SwipeRefreshLayout refreshLayout;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(
                R.layout.frag_home, container, false);

        fab = rootView.findViewById(R.id.fab);
        //  fab.setVisibility(View.VISIBLE);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ChatActivity.class));
            }
        });


        refreshLayout = rootView.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (MainActivity.mainActivity.lastTv != null) {
                    MainActivity.mainActivity.loadCategory(MainActivity.mainActivity.lastTv);
                } else {
                    refreshLayout.setRefreshing(false);
                }
            }
        });
        fetchVendorsAround(null);

        locationSwitch = rootView.findViewById(R.id.switch1);
        locationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    askLocationPermission();
                    Toast.makeText(getContext(), "Showing nearby deals", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Showing all deals in city", Toast.LENGTH_SHORT).show();
                    String category = null;
                    if (MainActivity.mainActivity.lastTv != null) {
                        category = MainActivity.mainActivity.lastTv.getText().toString();
                    }
                    if (category != null && !category.contains("All")) {
                        fetchVendorsAround(category);
                    } else {
                        fetchVendorsAround(null);
                    }
                }
            }
        });

    /*    if(MainActivity.loggedIn){
            initiatePopupWindow(rootView);
        }else{
            Toast.makeText(getContext(), MainActivity.loggedIn+"", Toast.LENGTH_SHORT).show();
        }*/

        //initiatePopupWindow(rootView.findViewById(R.id.switch1));
        View all = rootView.findViewById(R.id.all);
        MainActivity.mainActivity.loadCategory(all);
        return rootView;
    }

    private void showSpotLight() {
        new SpotlightView.Builder(getActivity())
                .introAnimationDuration(400)
                .enableRevealAnimation(true)
                .performClick(true)
                .fadeinTextDuration(400)
                .headingTvColor(Color.parseColor("#eb273f"))
                .headingTvSize(32)
                .headingTvText("Show Nearby")
                .subHeadingTvColor(Color.parseColor("#ffffff"))
                .subHeadingTvSize(16)
                .subHeadingTvText("Toggle this switch\nto show nearby restaurants, cafes, and more.")
                .maskColor(Color.parseColor("#dc000000"))
                .target(locationSwitch)
                .lineAnimDuration(400)
                .lineAndArcColor(Color.parseColor("#eb273f"))
                .dismissOnTouch(true)
                .dismissOnBackPress(true)
                .enableDismissAfterShown(true)
                .usageId("0") //UNIQUE ID
                .show();
    }

    @Override
    public void onResume() {
        super.onResume();
        showSpotLight();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // initiatePopupWindow(rootView.findViewById(R.id.switch1));
    }

    private void initiatePopupWindow(View v) {

        try {
            PopupMenu popup = new PopupMenu(getContext(), v);
            //Inflating the Popup using xml file
            popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

            //registering popup with OnMenuItemClickListener
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {
                    // Toast.makeText(getContext(), "You Clicked : " + item.getTitle(),  Toast.LENGTH_SHORT).show();
                    return true;
                }
            });

            popup.show(); //showing popup menu
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fetchVendorsAround(String category) {
        refreshLayout.setRefreshing(true);
        new FireBaseInteract(getActivity()).fetchVendorsAround(category, new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                refreshLayout.setRefreshing(false);
                ArrayList<Pair<String, Vendor>> list = new ArrayList<>();
                for (DocumentSnapshot snapshot : task.getResult()) {
                    try {
                        list.add(new Pair<>(snapshot.getId(), snapshot.toObject(Vendor.class)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                updateList(list);
            }
        });
    }

    private void updateList(ArrayList<Pair<String, Vendor>> list) {
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        if (list.size() > 0) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            VendorRecyclerAdapter recyclerAdapter = new VendorRecyclerAdapter(getActivity(), list);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(recyclerAdapter);
            recyclerView.setVisibility(View.VISIBLE);
            rootView.findViewById(R.id.empty).setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.GONE);
            rootView.findViewById(R.id.empty).setVisibility(View.VISIBLE);
        }
    }

    private void askLocationPermission() {
        String[] permissions = {android.Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
        Permissions.check(getActivity(), permissions, null, null, new PermissionHandler() {
            @Override
            public void onGranted() {
                // do your task.
                if (MainActivity.location == null) {
                    fetchLocation();
                } else {
                    performNearbySearch(MainActivity.location);
                }
            }

            @Override
            public void onDenied(Context context, ArrayList<String> deniedPermissions) {
                super.onDenied(context, deniedPermissions);
                locationSwitch.setChecked(false);
            }
        });
    }

    private void performNearbySearch(Location location) {
        String category = null;
        if (MainActivity.mainActivity.lastTv != null) {
            category = MainActivity.mainActivity.lastTv.getText().toString();
            category = category.replaceAll("All", "");
        }
        fetchVendorsNearBy(location, category);
    }

    private void fetchLocation() {

        // Check if the location services are enabled
        boolean areLocationServicesEnabled = SmartLocation.with(getActivity()).location().state().locationServicesEnabled();

        // Check if any provider (network or gps) is enabled
        boolean isAnyProviderAvailable = SmartLocation.with(getActivity()).location().state().isAnyProviderAvailable();

        // Check if GPS is available
        boolean isGpsAvailable = SmartLocation.with(getActivity()).location().state().isGpsAvailable();

        // Check if Network is available
        boolean isNetworkAvailable = SmartLocation.with(getActivity()).location().state().isNetworkAvailable();

        // Check if the passive provider is available
        boolean isPassiveAvailable = SmartLocation.with(getActivity()).location().state().isPassiveAvailable();

        // Check if the location is mocked
        boolean isMockSettingEnabled = SmartLocation.with(getActivity()).location().state().isMockSettingEnabled();


        String msg = "areLocationServicesEnabled: " + areLocationServicesEnabled +
                "\nisAnyProviderAvailable: " + isAnyProviderAvailable +
                "\nisGpsAvailable: " + isGpsAvailable +
                "\nisNetworkAvailable: " + isNetworkAvailable +
                "\nisPassiveAvailable: " + isPassiveAvailable +
                "\nisMockSettingEnabled: " + isMockSettingEnabled;
        //MyHelper.showErrorDialog("Status", msg, getActivity());

        //aQuery = new AQuery(getActivity());
//        aQuery.showPopupLoading();
        final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading");
        dialog.show();
        //  Toast.makeText(MainActivity.this, "fetching location...", Toast.LENGTH_SHORT).show();
        SmartLocation.with(getActivity()).location()
                .oneFix()
                .start(new OnLocationUpdatedListener() {
                    @Override
                    public void onLocationUpdated(Location location) {
                        MainActivity.location = location;
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        //BrandActivity.brandActivity.booking.setGeoPoint(new GeoPoint(latitude, longitude));
                        // Toast.makeText(getActivity(), "latitude: " + latitude + "\nlongitude: " + longitude, Toast.LENGTH_SHORT).show();
                        try {
                            dialog.dismiss();
                            //aQuery.hidePopupLoadin();
                            getAddress(latitude, longitude);
                            performNearbySearch(location);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void fetchVendorsNearBy(Location location, String category) {
        refreshLayout.setRefreshing(true);
        new FireBaseInteract(getActivity()).fetchVendorsNearby(category, location, new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                refreshLayout.setRefreshing(false);
                ArrayList<Pair<String, Vendor>> list = new ArrayList<>();
                for (DocumentSnapshot snapshot : task.getResult()) {
                    try {
                        Vendor vendor = snapshot.toObject(Vendor.class);
                        Pair<String, Vendor> vendorPair = new Pair<>(snapshot.getId(), vendor);

                        Location loc1 = new Location("");
                        loc1.setLatitude(MainActivity.location.getLatitude());
                        loc1.setLongitude(MainActivity.location.getLongitude());

                        Location loc2 = new Location("");
                        loc2.setLatitude(vendor.getCoordinates().getLatitude());
                        loc2.setLongitude(vendor.getCoordinates().getLongitude());

                        float distanceInMeters = loc1.distanceTo(loc2);
                        vendorPair.second.setDistanceInMetres(distanceInMeters);

                        if (distanceInMeters < 1500) {
                            list.add(vendorPair);
                        }

//                        Toast.makeText(getActivity(), ""+distanceInMeters, Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                updateList(arrangeByDistance(list));
            }
        });
    }

    private ArrayList<Pair<String, Vendor>> arrangeByDistance(ArrayList<Pair<String, Vendor>> list) {
        Collections.sort(list, new Comparator<Pair<String, Vendor>>() {
            @Override
            public int compare(Pair<String, Vendor> o1, Pair<String, Vendor> o2) {
                return Float.compare(o1.second.getDistanceInMetres(), o2.second.getDistanceInMetres());
            }
        });
        return list;
    }

    private void getAddress(double latitude, double longitude) throws IOException {
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
        String cityName = addresses.get(0).getAddressLine(0);
        String stateName = addresses.get(0).getAddressLine(1);
        String countryName = addresses.get(0).getAddressLine(2);
        EditText address = rootView.findViewById(R.id.address);
        //address.setText(cityName + "");
//        View btn = rootView.findViewById(R.id.get_location);
//        TextView lat_long = rootView.findViewById(R.id.lat_long);
//        btn.setVisibility(View.GONE);
//        lat_long.setVisibility(View.VISIBLE);
//        lat_long.setText("(" + latitude + ", " + longitude + ")\n\n"+cityName);
//        BrandActivity.brandActivity.booking.setAddress(cityName);
    }

}
