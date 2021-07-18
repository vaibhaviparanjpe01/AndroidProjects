package com.develeno.mylo.others;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.develeno.mylo.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ChathuraHettiarachchi on 1/29/16.
 */
public class TimeAgo {

    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;
    private static final int WEEKS_MILLIS = 7 * DAY_MILLIS;
    private static final int MONTHS_MILLIS = 4 * WEEKS_MILLIS;
    SimpleDateFormat simpleDateFormat, dateFormat, dateFormat2;
    DateFormat timeFormat;
    Date dateTimeNow;
    String timeFromData;
    String pastDate;
    String sDateTimeNow;
    @Nullable
    Context context;
    private boolean future;
    //private static final int YEARS_MILLIS = 12 * MONTHS_MILLIS;

    public TimeAgo() {

        simpleDateFormat = new SimpleDateFormat("dd/M/yyyy HH:mm:ss");
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat2 = new SimpleDateFormat("dd MMM");
        timeFormat = new SimpleDateFormat("h:mm aa");

        Date now = new Date();
        sDateTimeNow = simpleDateFormat.format(now);

        try {
            dateTimeNow = simpleDateFormat.parse(sDateTimeNow);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public TimeAgo locale(@NonNull Context context) {
        this.context = context;
        return this;
    }

    public TimeAgo with(@NonNull SimpleDateFormat simpleDateFormat) {
        this.simpleDateFormat = simpleDateFormat;
        this.dateFormat = new SimpleDateFormat(simpleDateFormat.toPattern().split(" ")[0]);
        this.timeFormat = new SimpleDateFormat(simpleDateFormat.toPattern().split(" ")[1]);
        return this;
    }

    public String getTimeAgo(Date startDate) {

        //  date counting is done till todays date
        Date endDate = dateTimeNow;

        //  time difference in milli seconds
        long different = endDate.getTime() - startDate.getTime();

        if (different < 0) {
            different = different * -1;
            future = true;
        }

        if (context == null) {
            if (different < MINUTE_MILLIS) {
                return context.getResources().getString(R.string.just_now);
            } else if (different < 2 * MINUTE_MILLIS) {
                return context.getResources().getString(R.string.a_min_ago);
            } else if (different < 50 * MINUTE_MILLIS) {
                return different / MINUTE_MILLIS + context.getString(R.string.mins_ago);
            } else if (different < 90 * MINUTE_MILLIS) {
                return context.getString(R.string.a_hour_ago);
            } else if (different < 24 * HOUR_MILLIS) {
                timeFromData = timeFormat.format(startDate);
                return timeFromData;
            } else if (different < 48 * HOUR_MILLIS) {
                return context.getString(R.string.yesterday);
            } else if (different < 7 * DAY_MILLIS) {
                return different / DAY_MILLIS + context.getString(R.string.days_ago);
            } else if (different < 2 * WEEKS_MILLIS) {
                return different / WEEKS_MILLIS + context.getString(R.string.week_ago);
            } else if (different < 3.5 * WEEKS_MILLIS) {
                return different / WEEKS_MILLIS + context.getString(R.string.weeks_ago);
            } else {
                pastDate = dateFormat.format(startDate);
                return pastDate;
            }
        } else {
            if (different < MINUTE_MILLIS) {
                return future ? context.getResources().getString(R.string.today) : context.getResources().getString(R.string.today);
            } else if (different < 2 * MINUTE_MILLIS) {
                return future ? context.getResources().getString(R.string.today) : context.getResources().getString(R.string.today);
            } else if (different < 50 * MINUTE_MILLIS) {
                return future ? context.getResources().getString(R.string.today) : /*different / MINUTE_MILLIS +*/ context.getString(R.string.today);
            } else if (different < 90 * MINUTE_MILLIS) {
                return future ? context.getResources().getString(R.string.today) : context.getString(R.string.today);
            } else {
                int date = startDate.getDate();
                int date1 = Calendar.getInstance().getTime().getDate();
                if (different < 24 * HOUR_MILLIS && date == date1) {
                    timeFromData = timeFormat.format(startDate);
                    return context.getResources().getString(R.string.today);
                } else {
                    Calendar instance = Calendar.getInstance();
                    instance.add(Calendar.DATE, 1);
                    if (different < 48 * HOUR_MILLIS && instance.getTime().getDate() == startDate.getDate()) {
                        return future ? context.getResources().getString(R.string.tomorrow) : context.getString(R.string.yesterday);
                    } else if (different < 7 * DAY_MILLIS) {
                        timeFromData = dateFormat2.format(startDate);
                        return future ? timeFromData : different / DAY_MILLIS + context.getString(R.string.days_ago);
                    } else if (different < 2 * WEEKS_MILLIS) {
                        timeFromData = dateFormat2.format(startDate);
                        return future ? timeFromData : different / WEEKS_MILLIS + context.getString(R.string.week_ago);
                    } else if (different < 3.5 * WEEKS_MILLIS) {
                        timeFromData = dateFormat2.format(startDate);
                        return future ? timeFromData : different / WEEKS_MILLIS + context.getString(R.string.weeks_ago);
                    } else {
                        pastDate = dateFormat.format(startDate);
                        return pastDate;
                    }
                }
            }
        }
    }
}