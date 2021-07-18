package com.newandromo.dev849565.app936843;

import android.text.format.DateUtils;
import java.lang.ref.SoftReference;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class DateHelper {
    static final long MILLISECONDS_IN_ONE_DAY = 86400000;
    static final long MILLISECONDS_IN_ONE_HOUR = 3600000;
    static final long MILLISECONDS_IN_ONE_MINUTE = 60000;
    static final long MILLISECONDS_IN_THREE_DAYS = 259200000;
    public static final String OUTPUT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss Z";
    private static final String TAG = "DateHelper";
    private static String[] localeDefaultformatDateStrings = {"yyyy-MM-dd'T'HH:mm:ss Z", "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy-MM-dd HH:mm:ss.SSSZ", "yyyy-MM-dd HH:mm:ss.SSSZ", "yyyy-MM-dd'T'HH:mm:ssZ", "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
    private static String[] localeUSFormatDateStrings = {"yyyy-mm-dd'T'HH:mm:sszzzz", "yyyy-MM-dd'T'HH:mm:ss.SSSzzzz", "yyyy-MM-dd'T'HH:mm:sszzzz", "yyyy-MM-dd'T'HHmmss.SSSz", "yyyy-MM-dd'T'HH:mm:ss z", "yyyy-MM-dd'T'HH:mm:ssz", "yyyy-mm-dd'T'HH:mm:ssz", "EEE MMM dd HH:mm:ss z yyyy", "EEE, dd MMM yyyy HH:mm:ss Z", "EEE, dd MMM yyyy HH:mm:ss z", "EEE, dd MMM yyyy HH:mm zzzz", "EEE MMM dd HH:mm:ss z yyyy", "MMM dd, yyyy HH:mm:ss a", "M d, yyyy HH:mm:ss a z", "dd MMM yyyy HH:mm:ss z", "dd MMM yyyy HH:mm:ss Z", "d MMM yyyy HH:mm:ss Z", "d MMM yyyy HH:mm:ss z", "yy/MM/dd HH:mm a", "EEE, dd MMM yyyy HH:mm:ss"};

    public static Date parseDate(String str) {
        Date parseDateUsingOutputFormat = parseDateUsingOutputFormat(str);
        if (parseDateUsingOutputFormat != null) {
            return parseDateUsingOutputFormat;
        }
        String[] strArr = localeDefaultformatDateStrings;
        int i = 0;
        while (true) {
            if (i >= strArr.length) {
                break;
            }
            try {
                parseDateUsingOutputFormat = DateFormatHolder.formatFor(strArr[i], true).parse(str);
                break;
            } catch (ParseException unused) {
                i++;
            }
        }
        if (parseDateUsingOutputFormat != null) {
            return parseDateUsingOutputFormat;
        }
        String[] strArr2 = localeUSFormatDateStrings;
        int i2 = 0;
        while (i2 < strArr2.length) {
            try {
                return DateFormatHolder.formatFor(strArr2[i2], false).parse(str);
            } catch (ParseException unused2) {
                i2++;
            }
        }
        return parseDateUsingOutputFormat;
    }

    public static SimpleDateFormat getOutputSimpleDateFormat() {
        return DateFormatHolder.formatFor(OUTPUT_DATE_FORMAT, true);
    }

    public static Date parseDateUsingOutputFormat(String str) {
        try {
            SimpleDateFormat outputSimpleDateFormat = getOutputSimpleDateFormat();
            if (outputSimpleDateFormat != null) {
                return outputSimpleDateFormat.parse(str);
            }
            return null;
        } catch (ParseException unused) {
            return null;
        }
    }

    public static String getConvertedDate(String str) {
        SimpleDateFormat outputSimpleDateFormat = getOutputSimpleDateFormat();
        Date parseDate = parseDate(str);
        if (parseDate == null || outputSimpleDateFormat == null) {
            return new Date().toString();
        }
        return outputSimpleDateFormat.format(parseDate);
    }

    public static String getCurrentFormattedDate() {
        Date date = new Date();
        DateFormat dateTimeInstance = DateFormat.getDateTimeInstance(2, 2);
        if (dateTimeInstance == null) {
            return "";
        }
        dateTimeInstance.setTimeZone(TimeZone.getTimeZone("UTC"));
        return getConvertedDate(date.toString());
    }

    static final class DateFormatHolder {
        private static final ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>> THREADLOCAL_FORMATS = new ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>>() {
            /* access modifiers changed from: protected */
            public SoftReference<Map<String, SimpleDateFormat>> initialValue() {
                return new SoftReference<>(new HashMap());
            }
        };

        DateFormatHolder() {
        }

        public static SimpleDateFormat formatFor(String str, boolean z) {
            SimpleDateFormat simpleDateFormat;
            Map map = (Map) THREADLOCAL_FORMATS.get().get();
            if (map == null) {
                map = new HashMap();
                THREADLOCAL_FORMATS.set(new SoftReference(map));
            }
            SimpleDateFormat simpleDateFormat2 = (SimpleDateFormat) map.get(str);
            if (simpleDateFormat2 == null) {
                if (z) {
                    simpleDateFormat = new SimpleDateFormat(str, Locale.getDefault());
                } else {
                    simpleDateFormat = new SimpleDateFormat(str, Locale.US);
                }
                simpleDateFormat2 = simpleDateFormat;
                simpleDateFormat2.setTimeZone(TimeZone.getTimeZone("UTC"));
                map.put(str, simpleDateFormat2);
            }
            return simpleDateFormat2;
        }
    }

    public static boolean isYesterday(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.add(6, -1);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(date);
        if (instance.get(1) == instance2.get(1) && instance.get(6) == instance2.get(6)) {
            return true;
        }
        return false;
    }

    public static String getPrettyTime(Date date, Date date2) {
        return getPrettyTime(date, date2, MILLISECONDS_IN_ONE_MINUTE, MILLISECONDS_IN_THREE_DAYS, MILLISECONDS_IN_ONE_DAY, -1, 327680);
    }

    public static String getPrettyTime(Date date, Date date2, int i) {
        return getPrettyTime(date, date2, MILLISECONDS_IN_ONE_MINUTE, MILLISECONDS_IN_THREE_DAYS, MILLISECONDS_IN_ONE_DAY, -1, i);
    }

    public static String getPrettyTime(Date date, Date date2, long j, long j2, long j3, long j4, int i) {
        String str;
        Date date3 = date;
        long time = date2.getTime() - date.getTime();
        if (time < 0 || time >= j2) {
            str = DateFormat.getDateInstance(2).format(date3);
        } else {
            str = DateUtils.getRelativeTimeSpanString(date.getTime(), date2.getTime(), j, i).toString();
        }
        if (j3 < 0 || time <= j3) {
            return str;
        }
        if (j4 >= 0 && time >= j4) {
            return str;
        }
        return str + " - " + DateFormat.getTimeInstance(3).format(date3);
    }

    public static String getPrettyTime(Date date, Date date2, long j, long j2, long j3, long j4, int i, String str) {
        String str2;
        Date date3 = date;
        long time = date2.getTime() - date.getTime();
        if (time < 0 || time >= j2) {
            str2 = DateFormat.getDateInstance(2).format(date3);
        } else {
            str2 = DateUtils.getRelativeTimeSpanString(date.getTime(), date2.getTime(), j, i).toString();
        }
        if (j3 < 0 || time <= j3 || (j4 >= 0 && time >= j4)) {
            return str2;
        }
        return String.format(str, new Object[]{DateFormat.getTimeInstance(3).format(date3), str2});
    }

    public static String getPrettyDate(Date date, Date date2, long j, long j2, int i) {
        long time = date2.getTime() - date.getTime();
        if (time >= 0 && time < j2) {
            return DateUtils.getRelativeTimeSpanString(date.getTime(), date2.getTime(), j, i).toString();
        }
        Date date3 = date;
        return DateFormat.getDateInstance(2).format(date);
    }

    public static String getTimeAgo(Date date, Date date2) {
        long time = date2.getTime() - date.getTime();
        if (time < 0 || time > MILLISECONDS_IN_ONE_MINUTE) {
            return DateUtils.getRelativeTimeSpanString(date.getTime(), date2.getTime(), MILLISECONDS_IN_ONE_MINUTE, 81920).toString();
        }
        return AndromoApplication.getAppResources().getString(R.string.just_now);
    }

    public static String getCompactTimeAgo(Date date, Date date2) {
        long time = date2.getTime() - date.getTime();
        if (time < 0 || time > MILLISECONDS_IN_ONE_MINUTE) {
            return DateUtils.getRelativeTimeSpanString(date.getTime(), date2.getTime(), MILLISECONDS_IN_ONE_MINUTE, 786432).toString();
        }
        return AndromoApplication.getAppResources().getString(R.string.just_now);
    }

    public static double getAgeInHours(Date date, Date date2) {
        double time = (double) (date2.getTime() - date.getTime());
        Double.isNaN(time);
        return time / 3600000.0d;
    }

    public static double getAgeInDays(Date date, Date date2) {
        double time = (double) (date2.getTime() - date.getTime());
        Double.isNaN(time);
        return time / 8.64E7d;
    }
}
