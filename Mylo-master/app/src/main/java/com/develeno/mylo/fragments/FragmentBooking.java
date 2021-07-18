package com.develeno.mylo.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.develeno.mylo.FireBaseInteract;
import com.develeno.mylo.R;
import com.develeno.mylo.activities.BookingActivity;
import com.develeno.mylo.adapters.DealRecyclerAdapter;
import com.develeno.mylo.adapters.MyListAdapter;
import com.develeno.mylo.listeners.OnGetViewListener;
import com.develeno.mylo.pojo.Deal;
import com.develeno.mylo.pojo.TimeSlot;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;

import static com.develeno.mylo.activities.BookingActivity.vendorId;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by devel_000 on 18-Sep-15.
 */
public class FragmentBooking extends Fragment {

    public static final int CALENDAR = 1;
    public static final int PERSON_COUNT = 2;
    public static final int TIME = 3;
    public static final int DEAL = 4;
    private ViewGroup rootView;
    private int mode;
    private RadioButton lastRadioButton;
    private ArrayList<Pair<String, TimeSlot>> timeSlots;
    private MyListAdapter timeSlotAdapter;
    private ArrayList<Pair<String, TimeSlot>> alltimeSlots = new ArrayList<>();
    private ListView listView;
    private ArrayList<Pair<String, Deal>> dealList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        mode = getArguments().getInt("mode");

        rootView = (ViewGroup) inflater.inflate(
                R.layout.frag_more, container, false);


        switch (mode) {
            case CALENDAR:
                rootView = (ViewGroup) inflater.inflate(
                        R.layout.frag_calendar, container, false);
                DatePicker datePicker = rootView.findViewById(R.id.calendar);
                long timeInMillis = Calendar.getInstance().getTimeInMillis();
                datePicker.setMinDate(timeInMillis - 1000);
                datePicker.setMaxDate(timeInMillis + (24 * 60 * 60 * 7 * 1000));
                Calendar instance = Calendar.getInstance();
                datePicker.init(instance.get(Calendar.YEAR), instance.get(Calendar.MONTH), instance.get(Calendar.DATE), (view, year, monthOfYear, dayOfMonth) -> {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year, monthOfYear, dayOfMonth);
                    BookingActivity.date = calendar.getTime();
                });
                BookingActivity.date = Calendar.getInstance().getTime();
                BookingActivity.timeSlot = null;
                BookingActivity.timeSlotString = null;
                lastRadioButton = null;
                break;
            case PERSON_COUNT:
                rootView = (ViewGroup) inflater.inflate(
                        R.layout.frag_person_count, container, false);
                SeekBar seek_bar = rootView.findViewById(R.id.seek_bar);
                TextView count = rootView.findViewById(R.id.count);
                seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        String persons = progress > 1 ? "persons" : " person";
                        String s = progress < 11 ? " " : "+ ";
                        int personCount = progress < 11 ? progress : 10;
                        count.setText(personCount + s + persons);
                        BookingActivity.personCount = progress;
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
                seek_bar.setProgress(2);
                break;
            case TIME:
                rootView = (ViewGroup) inflater.inflate(
                        R.layout.frag_time, container, false);
                fetchTimeSlots();
                break;
            case DEAL:
                rootView = (ViewGroup) inflater.inflate(
                        R.layout.frag_deals, container, false);
                fetchDealsForVendor(vendorId);
                break;
        }

        return rootView;
    }

    private void fetchTimeSlots() {
        new FireBaseInteract(getActivity()).fetchTimeSlotsForVendor(vendorId, new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                timeSlots = new ArrayList<>();
                for (DocumentSnapshot snapshot : task.getResult()) {
                    timeSlots.add(new Pair<>(snapshot.getId(), snapshot.toObject(TimeSlot.class)));
                }
                alltimeSlots.addAll(timeSlots);
                updateTimeSlotList(timeSlots);
            }
        });
    }

    private void updateTimeSlotList(ArrayList<Pair<String, TimeSlot>> list) {
        listView = rootView.findViewById(R.id.listView);
        if (list.size() > 0) {
            timeSlotAdapter = new MyListAdapter(list, getListener(list));
            listView.setAdapter(timeSlotAdapter);
            listView.setVisibility(View.VISIBLE);
        } else {
            listView.setVisibility(View.GONE);
//            rootView.findViewById(R.id.deals_label).setVisibility(View.GONE);
        }
    }

    @NonNull
    private OnGetViewListener getListener(ArrayList<Pair<String, TimeSlot>> list) {
        return new OnGetViewListener() {
            @Override
            public View onGetView(int i, View view, ViewGroup viewGroup) {
                LayoutInflater layoutInflater = getLayoutInflater();
                View v = layoutInflater.inflate(R.layout.time_slot_item, viewGroup, false);
                RadioButton radioButton = v.findViewById(R.id.text);
                TimeSlot timeSlot = list.get(i).second;
                String timeSlotString = "Between " + timeSlot.getStartTimeText() + " & " + timeSlot.getEndTimeText();
                radioButton.setText(timeSlotString);
                radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (lastRadioButton != null) {
                            lastRadioButton.setChecked(false);
                        }
                        if (isChecked) {
                            lastRadioButton = radioButton;
                            BookingActivity.timeSlot = timeSlot;
                            BookingActivity.timeSlotId = list.get(i).first;
                            BookingActivity.timeSlotString = timeSlotString;
                        }
                    }
                });

                if (BookingActivity.timeSlot != null && BookingActivity.timeSlot.equals(timeSlot)) {
                    radioButton.setChecked(true);
                    lastRadioButton = radioButton;
                }
                return v;
            }
        };
    }

    public void fetchDealsForVendor(String vendorId) {
        new FireBaseInteract(getActivity()).fetchDealsForVendor(vendorId, task -> {
            dealList = new ArrayList<>();
            for (DocumentSnapshot snapshot : task.getResult()) {
                dealList.add(new Pair<>(snapshot.getId(), snapshot.toObject(Deal.class)));
            }
            updateList(dealList);
        });
    }

    private void updateList(ArrayList<Pair<String, Deal>> list) {
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        if (list.size() > 0) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            DealRecyclerAdapter recyclerAdapter = new DealRecyclerAdapter(getActivity(), BookingActivity.vendor, list);
            recyclerAdapter.setBookingMode(true);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(recyclerAdapter);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.GONE);
            // rootView.findViewById(R.id.deals_label).setVisibility(View.GONE);
        }
    }

    public void updateTimeSlots() {
        if (timeSlots != null) {
            ArrayList<Pair<String, TimeSlot>> newTimeSlots = new ArrayList<>();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(BookingActivity.date);
            int day = calendar.get(Calendar.DAY_OF_WEEK);
            for (Pair<String, TimeSlot> timeSlot : timeSlots) {
                boolean isAvailableOnDay = timeSlot.second != null && timeSlot.second.isAvailableOnDay(day);
                if (isAvailableOnDay) {
                    newTimeSlots.add(timeSlot);
                }
            }
            timeSlotAdapter = new MyListAdapter(newTimeSlots, getListener(newTimeSlots));
            listView.setAdapter(timeSlotAdapter);
        }
    }

    public void updateAccToTimeSlots() {
        ArrayList<Pair<String, Deal>> pairs = new ArrayList<>();
        for (Pair<String, Deal> pair : dealList) {
            for (String id : pair.second.getTimeslotIds()) {
                if (id.equalsIgnoreCase(BookingActivity.timeSlotId)) {
                    pairs.add(pair);
                    break;
                }
            }
        }
        updateList(pairs);
    }
}

