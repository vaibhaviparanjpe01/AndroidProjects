package com.newandromo.dev849565.app936843;

import android.os.Parcel;
import android.os.Parcelable;

public final class AudioItem implements Parcelable {
    public static final Parcelable.Creator<AudioItem> CREATOR = new Parcelable.Creator<AudioItem>() {
        public AudioItem createFromParcel(Parcel parcel) {
            return new AudioItem(parcel);
        }

        public AudioItem[] newArray(int i) {
            return new AudioItem[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
    }

    private AudioItem(Parcel parcel) {
    }
}
