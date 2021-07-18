package com.develeno.mylo.adapters;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.develeno.mylo.FireBaseInteract;
import com.develeno.mylo.R;
import com.develeno.mylo.activities.BookingActivity;
import com.develeno.mylo.activities.DealsActivity;
import com.develeno.mylo.activities.MainActivity;
import com.develeno.mylo.activities.MyBookingsActivity;
import com.develeno.mylo.others.Data;
import com.develeno.mylo.pojo.Deal;
import com.develeno.mylo.pojo.Reservation;
import com.develeno.mylo.pojo.UserObject;
import com.develeno.mylo.pojo.Vendor;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import at.blogc.android.views.ExpandableTextView;

public class DealRecyclerAdapter extends RecyclerView.Adapter<DealRecyclerAdapter.ViewHolder> {

    private final Activity activity;
    private final Vendor vendor;
    private ArrayList<Pair<String, Deal>> mData;
    private LayoutInflater mInflater;
    private boolean bookingMode;
    public int selectedPos = -1;
    private CheckBox lastCheckBox;

    // data is passed into the constructor
    public DealRecyclerAdapter(Activity activity, Vendor vendor, ArrayList<Pair<String, Deal>> data) {
        this.activity = activity;
        this.mInflater = LayoutInflater.from(activity);
        this.mData = data;
        this.vendor = vendor;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.deal_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the textview in each row
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Pair<String, Deal> stringDealPair = mData.get(position);
        final Deal deal = stringDealPair.second;

        holder.title.setText(deal.getDeal());
        holder.price.setText("Rs." + deal.getPrice());
        String secondTnc = deal.getTnc();
        String[] tc = secondTnc.split(";");
        holder.tnc.setText("");
        for (int i = 0; i < tc.length; i++) {
            holder.tnc.setText(holder.tnc.getText() + "\n" + tc[i]);
        }

        if (!bookingMode) {
            holder.buy.setVisibility(View.GONE);
            holder.checkBox.setVisibility(View.GONE);
        }

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (lastCheckBox != null) {
                        lastCheckBox.setChecked(false);
                    }
                    selectedPos = position;
                    lastCheckBox = holder.checkBox;
                }
                //notifyDataSetChanged();
            }
        });

        if (position == selectedPos) {
            holder.checkBox.setChecked(true);
        } else {
            holder.checkBox.setChecked(false);
        }

        final boolean purchased = Data.myDeals.contains(stringDealPair.first);
        if (!purchased) {
            holder.buy.setOnClickListener(v -> {
                //Toast.makeText(activity, "coming soon", Toast.LENGTH_SHORT).show();
                //showDialog(stringDealPair);
                if (!bookingMode) {
                    openBookingActivity(deal);
                } else {
                    // buyDirectly(stringDealPair);
                    if (BookingActivity.timeSlot != null) {
                        Reservation reservation = new Reservation(stringDealPair.first, deal, deal.getTnc(), vendor,
                                FireBaseInteract.userObject.getNumber(), BookingActivity.date, BookingActivity.personCount, BookingActivity.timeSlotString);
                        reservation.setUserName(FireBaseInteract.userObject.getName());
                        showConfirmDialog(reservation, deal);
                    } else {
                        BookingActivity.pager.setCurrentItem(2);
                        Toast.makeText(activity, "Select Time slot", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            holder.buy.setText("Purchased");
            holder.buy.setTextColor(Color.GRAY);
            holder.buy.setBackgroundResource(0);
        }


        holder.expand.setOnClickListener(v -> {
            if (holder.tnc.isExpanded()) {
                holder.tnc.collapse();
                holder.expand.setText("View T&C");
            } else {
                holder.tnc.expand();
                holder.expand.setText("Hide Terms & Conditions");
            }
        });

        holder.itemView.setOnClickListener(v -> {
            if (!purchased) {
                if (!bookingMode) {
//                        showDialog(stringDealPair);
                    openBookingActivity(deal);
                } else {
                    // buyDirectly(stringDealPair);
                }
            } else {
                activity.startActivity(new Intent(activity, MyBookingsActivity.class));
            }
        });
    }

    private void showConfirmDialog(Reservation reservation, Deal deal) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(R.layout.confirm_dialog);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        View decorView = alertDialog.getWindow().getDecorView();
        View button = decorView.findViewById(R.id.button);
        ExpandableTextView tnc = decorView.findViewById(R.id.tnc);
        TextView button_toggle = decorView.findViewById(R.id.button_toggle);
        button_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tnc.toggle();
            }
        });
        TextView text = decorView.findViewById(R.id.title);
        TextView details = decorView.findViewById(R.id.details);
        tnc.setText(reservation.getTnc());
        text.setText(deal.getDeal());
        String text1 = "<b>Place:</b> " + reservation.getVendorName() + "\n<b><br>Time:</b> " + reservation.getTimeSlot() +
                "\n<br><b>Expected People:</b> " + reservation.getGuestCount();
        details.setText(Html.fromHtml(text1));
        details.setLineSpacing(10, 1.5f);
        button.setOnClickListener(v -> {
            makeAReservation(reservation);
            alertDialog.dismiss();
        });
    }

    private void makeAReservation(Reservation reservation) {
        final ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle("Making your reservation");
        progressDialog.setMessage("Please wait");
        progressDialog.show();

        FireBaseInteract fireBaseInteract = new FireBaseInteract(activity);
        fireBaseInteract.makeAReservation(reservation, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                progressDialog.dismiss();
                Toast.makeText(activity, "Successfully booked", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
                activity.startActivity(new Intent(activity, MyBookingsActivity.class));
                activity.finish();

                fireBaseInteract.fetchVendorUserById(reservation.getVendorId(), new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (queryDocumentSnapshots.getDocuments().size() > 0) {
                            UserObject vendor = queryDocumentSnapshots.getDocuments().get(0).toObject(UserObject.class);
                            new FireBaseInteract(activity).sendNotif("New Booking", "Tap to open", vendor.getFcm());

                            //Sending SMS to Vendor and Admin
                            final String customerName = reservation.getUserName() + "";
                            final String cafeName = reservation.getVendorName() + "";
                            final String customerCount = reservation.getGuestCount() <= 10 ? reservation.getGuestCount() + "" : "10+";
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM");
                            final Date reservationDate = reservation.getReservationDate();
                            final String dateTime = dateFormat.format(reservationDate) + " " + reservation.getTimeSlot().replace("&", " - ");
                            final String msg = customerName + " booked "
                                    + cafeName + " for " + customerCount + " people on " + dateTime + " ";
                            final String num = vendor.getNumber().replace("+91", "");
                            String msg2 = "New Booking\n" +
                                    " Name - " + customerName + "\n" +
                                    " Place -" + cafeName + "\n" +
                                    " Date - " + dateFormat.format(reservationDate) + "\n" +
                                    " Time - " + reservation.getTimeSlot().replace("&", " - ") + "\n" +
                                    " No. Of people - " + customerCount;
                            // FireBaseInteract.sendSMS(msg, num, activity);
                            FireBaseInteract.sendSMS(msg, "7000605754", activity);
                        }
                    }
                });
            }
        });
    }


    private void openBookingActivity(Deal deal) {
        BookingActivity.vendor = vendor;
        BookingActivity.vendorId = deal.getVendorId();
        activity.startActivity(new Intent(activity, BookingActivity.class));
    }

    private void buyDirectly(Pair<String, Deal> dealPair) {
        final ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle("Buying Deal");
        progressDialog.setMessage("Please wait");
        progressDialog.show();

        new FireBaseInteract(activity).buyDeal(dealPair, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                progressDialog.dismiss();
                Toast.makeText(activity, "Successfully purchased deal", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
                activity.startActivity(new Intent(activity, DealsActivity.class));
                activity.finish();
            }
        });
    }

    private void showDialog(final Pair<String, Deal> dealPair) {
        Deal deal = dealPair.second;
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(R.layout.deal_dialog);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        View view = alertDialog.getWindow().getDecorView();
        TextView title = view.findViewById(R.id.title);
        TextView tnc = view.findViewById(R.id.tnc);
        title.setText(vendor.getName() + " - " + deal.getDeal());
        String secondTnc = deal.getTnc();
        String[] tc = secondTnc.split(";");
        tnc.setText("");
        for (int i = 0; i < tc.length; i++) {
            tnc.setText(tnc.getText() + "\n" + tc[i]);
        }
        view.findViewById(R.id.buy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                final ProgressDialog progressDialog = new ProgressDialog(activity);
                progressDialog.setTitle("Buying Deal");
                progressDialog.setMessage("Please wait");
                progressDialog.show();

                new FireBaseInteract(activity).buyDeal(dealPair, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        progressDialog.dismiss();
                        Toast.makeText(activity, "Successfully purchased deal", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                        activity.startActivity(new Intent(activity, DealsActivity.class));
                        activity.finish();
                    }
                });

//                activity.startActivity(new Intent(activity, null));
            }
        });
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // convenience method for getting data at click position
    public Pair<String, Deal> getItem(int id) {
        return mData.get(id);
    }

    public void setBookingMode(boolean bookingMode) {
        this.bookingMode = bookingMode;
    }

    public void open() {
        openBookingActivity(mData.get(0).second);
    }

    public void makeBooking() {

    }

    // stores and recycles views as they are scrolled off screen
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView price;
        TextView buy;
        private TextView expand;
        private ExpandableTextView tnc;
        CheckBox checkBox;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            buy = itemView.findViewById(R.id.buy);
            expand = itemView.findViewById(R.id.button_toggle);
            tnc = itemView.findViewById(R.id.tnc);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }
}