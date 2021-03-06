package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.support.annotation.RestrictTo;
import android.widget.CalendarView;

@InverseBindingMethods({@InverseBindingMethod(attribute = "android:date", type = CalendarView.class)})
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class CalendarViewBindingAdapter {
    @BindingAdapter({"android:date"})
    public static void setDate(CalendarView calendarView, long j) {
        if (calendarView.getDate() != j) {
            calendarView.setDate(j);
        }
    }

    @BindingAdapter(requireAll = false, value = {"android:onSelectedDayChange", "android:dateAttrChanged"})
    public static void setListeners(CalendarView calendarView, final CalendarView.OnDateChangeListener onDateChangeListener, final InverseBindingListener inverseBindingListener) {
        if (inverseBindingListener == null) {
            calendarView.setOnDateChangeListener(onDateChangeListener);
        } else {
            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                public void onSelectedDayChange(CalendarView calendarView, int i, int i2, int i3) {
                    if (onDateChangeListener != null) {
                        onDateChangeListener.onSelectedDayChange(calendarView, i, i2, i3);
                    }
                    inverseBindingListener.onChange();
                }
            });
        }
    }
}
