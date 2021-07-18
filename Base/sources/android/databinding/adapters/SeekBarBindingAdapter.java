package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.support.annotation.RestrictTo;
import android.widget.SeekBar;

@InverseBindingMethods({@InverseBindingMethod(attribute = "android:progress", type = SeekBar.class)})
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class SeekBarBindingAdapter {

    public interface OnProgressChanged {
        void onProgressChanged(SeekBar seekBar, int i, boolean z);
    }

    public interface OnStartTrackingTouch {
        void onStartTrackingTouch(SeekBar seekBar);
    }

    public interface OnStopTrackingTouch {
        void onStopTrackingTouch(SeekBar seekBar);
    }

    @BindingAdapter({"android:progress"})
    public static void setProgress(SeekBar seekBar, int i) {
        if (i != seekBar.getProgress()) {
            seekBar.setProgress(i);
        }
    }

    @BindingAdapter(requireAll = false, value = {"android:onStartTrackingTouch", "android:onStopTrackingTouch", "android:onProgressChanged", "android:progressAttrChanged"})
    public static void setOnSeekBarChangeListener(SeekBar seekBar, final OnStartTrackingTouch onStartTrackingTouch, final OnStopTrackingTouch onStopTrackingTouch, final OnProgressChanged onProgressChanged, final InverseBindingListener inverseBindingListener) {
        if (onStartTrackingTouch == null && onStopTrackingTouch == null && onProgressChanged == null && inverseBindingListener == null) {
            seekBar.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) null);
        } else {
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                    if (onProgressChanged != null) {
                        onProgressChanged.onProgressChanged(seekBar, i, z);
                    }
                    if (inverseBindingListener != null) {
                        inverseBindingListener.onChange();
                    }
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                    if (onStartTrackingTouch != null) {
                        onStartTrackingTouch.onStartTrackingTouch(seekBar);
                    }
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                    if (onStopTrackingTouch != null) {
                        onStopTrackingTouch.onStopTrackingTouch(seekBar);
                    }
                }
            });
        }
    }
}
