package com.develeno.mylo.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.develeno.mylo.FireBaseInteract;
import com.develeno.mylo.R;
import com.develeno.mylo.adapters.CommonRecyclerAdapter;
import com.develeno.mylo.adapters.DealRecyclerAdapter;
import com.develeno.mylo.adapters.MyPagerAdapter;
import com.develeno.mylo.fragments.FragmentBooking;
import com.develeno.mylo.listeners.OnBindViewListener;
import com.develeno.mylo.pojo.Deal;
import com.develeno.mylo.pojo.TimeSlot;
import com.develeno.mylo.pojo.Vendor;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BookingActivity extends AppCompatActivity {
    public static String vendorId = "";
    public static Vendor vendor;
    public static int personCount = 1;
    public static Date date;
    public static TimeSlot timeSlot;
    public static String timeSlotString;
    public static ViewPager pager;
    public static String timeSlotId;
    private FragmentBooking fragmentBooking;
    private FragmentBooking fragmentBooking1;
    private FragmentBooking fragmentBooking2;
    private FragmentBooking fragmentBooking3;
    private TextView steps;
    private ImageView icon;
    private ArrayList<Pair<String, TimeSlot>> timeSlots;
    private CommonRecyclerAdapter timeSlotAdapter;
    private ArrayList<Pair<String, TimeSlot>> alltimeSlots = new ArrayList<>();
    private RecyclerView recyclerView_time;
    private TextView lastTextViewSelected;
    private CommonRecyclerAdapter recyclerAdapter;
    private CommonRecyclerAdapter recyclerAdapter1;
    private DealRecyclerAdapter dealRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_single_page);

//        steps = findViewById(R.id.steps);
//        icon = findViewById(R.id.icon);
//        setupViewPager();
//        pager.setCurrentItem(0);
//        steps.setText("1/4");
//        icon.setImageResource(R.drawable.cal);

        fetchTimeSlots();
        fetchDealsForVendor(vendorId);
        setupDateRecyclerView();
        setupPeopleRecyclerView();
        date = Calendar.getInstance().getTime();
        findViewById(R.id.recyclerView_deals).setVisibility(View.GONE);
        findViewById(R.id.deals_label).setVisibility(View.GONE);
        findViewById(R.id.button).setVisibility(View.GONE);
    }

    private void setupPeopleRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView_people);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        ArrayList<Integer> peoples = new ArrayList<>();
        for (int i = 1; i <= 11; i++) {
            peoples.add(i);
        }
        recyclerAdapter = new CommonRecyclerAdapter(peoples, R.layout.people_item, this, (holder, position) -> {
            int num = peoples.get(position);
            TextView text = holder.itemView.findViewById(R.id.num);
            if (num < 10) {
                text.setText("0" + num + "");
            } else if (num == 10) {
                text.setText(num + "");
            } else {
                text.setText("10+");
            }
            holder.itemView.setOnClickListener(v -> {
                personCount = num;
                recyclerAdapter.notifyDataSetChanged();
            });

            if (num == personCount) {
                text.setTextColor(Color.WHITE);
                text.setBackgroundResource(R.drawable.circle_selected);
            } else {
                text.setTextColor(Color.BLACK);
                text.setBackgroundResource(R.drawable.circle);
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void setupDateRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView_date);
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        ArrayList<Date> dates = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, i);
            Date date = calendar.getTime();
            dates.add(date);
        }
        recyclerAdapter1 = new CommonRecyclerAdapter(dates, R.layout.date_item, this, (holder, position) -> {
            TextView date = holder.itemView.findViewById(R.id.date);
            TextView day = holder.itemView.findViewById(R.id.day);
            TextView month = holder.itemView.findViewById(R.id.month);
            Date date1 = dates.get(position);
            date.setText(date1.getDate() + "");
            day.setText(getDayInText(date1) + "");
            month.setText(getMonthInText(date1) + "");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BookingActivity.date = date1;
                    timeSlotId = null;
                    lastTextViewSelected = null;
                    setupDateRecyclerView();
                    hidePassedTimeSlots();
                    findViewById(R.id.deals_label).setVisibility(View.GONE);
                    findViewById(R.id.recyclerView_deals).setVisibility(View.GONE);
                    findViewById(R.id.button).setVisibility(View.GONE);
                }
            });

            if (BookingActivity.date.getYear() == date1.getYear() &&
                    BookingActivity.date.getMonth() == date1.getMonth() &&
                    BookingActivity.date.getDate() == date1.getDate()) {
                holder.itemView.setBackgroundResource(R.color.colorPrimaryDark);
            } else {
                holder.itemView.setBackgroundResource(R.color.colorPrimary);
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter1);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private String getDayInText(Date date1) {

        switch (date1.getDay()) {
            case 0:
                return "SUN";
            case 1:
                return "MON";
            case 2:
                return "TUE";
            case 3:
                return "WED";
            case 4:
                return "THU";
            case 5:
                return "FRI";
            case 6:
                return "SAT";
            default:
                return "???";
        }
    }

    private String getMonthInText(Date date1) {

        switch (date1.getMonth()) {
            case 0:
                return "JAN";
            case 1:
                return "FEB";
            case 2:
                return "MAR";
            case 3:
                return "APR";
            case 4:
                return "MAY";
            case 5:
                return "JUN";
            case 6:
                return "JUL";
            case 7:
                return "AUG";
            case 8:
                return "SEP";
            case 9:
                return "OCT";
            case 10:
                return "NOV";
            case 11:
                return "DEC";
            default:
                return "???";
        }
    }

    private void fetchTimeSlots() {
        findViewById(R.id.progress_time).setVisibility(View.VISIBLE);
        new FireBaseInteract(this).fetchTimeSlotsForVendor(vendorId, task -> {
            timeSlots = new ArrayList<>();
            for (DocumentSnapshot snapshot : task.getResult()) {
                timeSlots.add(new Pair<>(snapshot.getId(), snapshot.toObject(TimeSlot.class)));
            }
            alltimeSlots.addAll(timeSlots);
            updateTimeSlotList(timeSlots);
            findViewById(R.id.progress_time).setVisibility(View.GONE);
            hidePassedTimeSlots();
        });
    }

    private ArrayList<Pair<String, Deal>> dealList;

    public void fetchDealsForVendor(String vendorId) {
        findViewById(R.id.progress_deals).setVisibility(View.VISIBLE);
        new FireBaseInteract(this).fetchDealsForVendor(vendorId, task -> {
            dealList = new ArrayList<>();
            for (DocumentSnapshot snapshot : task.getResult()) {
                dealList.add(new Pair<>(snapshot.getId(), snapshot.toObject(Deal.class)));
            }
            //updateList(dealList);
            findViewById(R.id.progress_deals).setVisibility(View.GONE);
        });
    }

    private void updateList(ArrayList<Pair<String, Deal>> list) {
        RecyclerView recyclerView = findViewById(R.id.recyclerView_deals);
        TextView text = findViewById(R.id.deals_label);
        if (timeSlotId != null) {
            text.setVisibility(View.VISIBLE);
            if (list.size() > 0) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                dealRecyclerAdapter = new DealRecyclerAdapter(this, BookingActivity.vendor, list);
                dealRecyclerAdapter.setBookingMode(true);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(dealRecyclerAdapter);
                recyclerView.setVisibility(View.VISIBLE);
                text.setText(list.size() + " Deals Available");
                //findViewById(R.id.button).setVisibility(View.VISIBLE);
            } else {
                recyclerView.setVisibility(View.GONE);
                text.setText("No Deals Available at this TimeSlot");
                //            text.setVisibility(View.GONE);
            }
        } else {
            text.setVisibility(View.GONE);
        }
    }

    private void updateTimeSlotList(ArrayList<Pair<String, TimeSlot>> list) {
        recyclerView_time = findViewById(R.id.recyclerView_time);
        if (list.size() > 0) {
            timeSlotAdapter = new CommonRecyclerAdapter(list, R.layout.time_slot_item_2, this, getListener(list));
            recyclerView_time.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            recyclerView_time.setAdapter(timeSlotAdapter);
            recyclerView_time.setVisibility(View.VISIBLE);
            recyclerView_time.setNestedScrollingEnabled(true);
        } else {
            recyclerView_time.setVisibility(View.GONE);
//            rootView.findViewById(R.id.deals_label).setVisibility(View.GONE);
        }
    }


    public void hidePassedTimeSlots() {
        timeSlots.clear();
        if (BookingActivity.date.getDate() == Calendar.getInstance().getTime().getDate()) {
            for (Pair<String, TimeSlot> pair : alltimeSlots) {
                if (pair.second.getStartTime().getHours() >= Calendar.getInstance().getTime().getHours()) {
                    timeSlots.add(pair);
                }
            }
        } else {
            timeSlots.addAll(alltimeSlots);
        }
        updateTimeSlotList(timeSlots);
    }

    @NonNull
    private OnBindViewListener getListener(ArrayList<Pair<String, TimeSlot>> list) {
        return (holder, position) -> {
            holder.setIsRecyclable(false);
            TextView textView = holder.itemView.findViewById(R.id.text);
            TimeSlot timeSlot = list.get(position).second;
            String timeSlotString = "" + timeSlot.getStartTimeText() + " - " + timeSlot.getEndTimeText();
            textView.setText(timeSlotString);
            /*textView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (lastTextViewSelected != null) {
                        lastTextViewSelected.setChecked(false);
                    }
                    if (isChecked) {
                        lastTextViewSelected = textView;
                        BookingActivity.timeSlot = timeSlot;
                        BookingActivity.timeSlotId = list.get(position).first;
                        BookingActivity.timeSlotString = timeSlotString;
                        updateAccToTimeSlots();
                    }
                }
            });*/

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (lastTextViewSelected != null) {
                        lastTextViewSelected.setBackgroundResource(R.drawable.chip);
                        lastTextViewSelected.setTextColor(Color.BLACK);
                    }
                    textView.setBackgroundResource(R.drawable.chip_selected);
                    textView.setTextColor(Color.WHITE);
                    // if (isChecked) {
                    lastTextViewSelected = textView;
                    BookingActivity.timeSlot = timeSlot;
                    BookingActivity.timeSlotId = list.get(position).first;
                    BookingActivity.timeSlotString = timeSlotString;
                    updateAccToTimeSlots();
                    //}
                }
            });

                if (BookingActivity.timeSlot != null && BookingActivity.timeSlot.equals(timeSlot)) {
                    textView.setBackgroundResource(R.drawable.chip_selected);
                    textView.setTextColor(Color.WHITE);
                    lastTextViewSelected = textView;
                }
        };
    }

    private void setupViewPager() {
        pager = findViewById(R.id.pager);
        ArrayList<Pair<String, Fragment>> fragments = new ArrayList<>();

        fragmentBooking = new FragmentBooking();
        Bundle args = new Bundle();
        args.putInt("mode", 1);
        fragmentBooking.setArguments(args);
        fragments.add(new Pair<>("Select Date", fragmentBooking));

        fragmentBooking1 = new FragmentBooking();
        Bundle args1 = new Bundle();
        args1.putInt("mode", 2);
        fragmentBooking1.setArguments(args1);
        fragments.add(new Pair<>("No. of Guests", fragmentBooking1));

        fragmentBooking2 = new FragmentBooking();
        Bundle args2 = new Bundle();
        args2.putInt("mode", 3);
        fragmentBooking2.setArguments(args2);
        fragments.add(new Pair<>("Select Time", fragmentBooking2));

        fragmentBooking3 = new FragmentBooking();
        Bundle args3 = new Bundle();
        args3.putInt("mode", 4);
        fragmentBooking3.setArguments(args3);
        fragments.add(new Pair<>("Select Deal", fragmentBooking3));

        MyPagerAdapter adapter = new MyPagerAdapter(fragments, getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(5);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        icon.setImageResource(R.drawable.cal);
                        break;
                    case 1:
                        icon.setImageResource(R.drawable.guest);
                        break;
                    case 2:
                        icon.setImageResource(R.drawable.clock);
                        fragmentBooking2.updateTimeSlots();
                        break;
                    case 3:
                        icon.setImageResource(R.drawable.deal_icon);
                        fragmentBooking3.updateAccToTimeSlots();
                        if (timeSlotId == null) {
                            pager.setCurrentItem(2);
                            Toast.makeText(BookingActivity.this, "Select Time please", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                int pos = i + 1;
                steps.setText(pos + "/" + adapter.getCount());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
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

    public void next(View view) {
        pager.setCurrentItem(pager.getCurrentItem() + 1);
    }

    public void prev(View view) {
        if (pager.getCurrentItem() != 0) {
            pager.setCurrentItem(pager.getCurrentItem() - 1);
        } else {
            onBackPressed();
        }
    }

    public void close(View view) {
        finish();
    }

    public void book(View view) {
        if (dealRecyclerAdapter.selectedPos >= 0) {
            dealRecyclerAdapter.makeBooking();
        }
    }
}
