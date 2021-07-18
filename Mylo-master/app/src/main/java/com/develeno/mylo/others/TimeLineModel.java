package com.develeno.mylo.others;

import android.os.Parcel;
import android.os.Parcelable;

public class TimeLineModel implements Parcelable {

    public static final Parcelable.Creator<TimeLineModel> CREATOR = new Parcelable.Creator<TimeLineModel>() {
        @Override
        public TimeLineModel createFromParcel(Parcel source) {
            return new TimeLineModel(source);
        }

        @Override
        public TimeLineModel[] newArray(int size) {
            return new TimeLineModel[size];
        }
    };
    private String message;
    private String date;
    private OrderStatus status;

    public TimeLineModel() {
    }

    public TimeLineModel(String message, String date, OrderStatus status) {
        this.message = message;
        this.date = date;
        this.status = status;
    }

    protected TimeLineModel(Parcel in) {
        this.message = in.readString();
        this.date = in.readString();
        int tmpMStatus = in.readInt();
        this.status = tmpMStatus == -1 ? null : OrderStatus.values()[tmpMStatus];
    }

    public String getMessage() {
        return message;
    }

    public void semMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus mStatus) {
        this.status = mStatus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.message);
        dest.writeString(this.date);
        dest.writeInt(this.status == null ? -1 : this.status.ordinal());
    }
}