package com.example.iamjiangtian.myresume.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by iamjiangtian on 9/26/16.
 */
public class Education implements Parcelable{
    public String id;
    public String school;
    public Date startDate;
    public Date endDate;
    public String courses;

    public Education(){
        id = UUID.randomUUID().toString();
    }
    protected Education(Parcel in) {
        id = in.readString();
        school = in.readString();
        startDate = new Date(in.readLong());
        endDate = new Date(in.readLong());
        courses = in.readString();
    }

    public static final Creator<Education> CREATOR = new Creator<Education>() {
        @Override
        public Education createFromParcel(Parcel in) {
            return new Education(in);
        }

        @Override
        public Education[] newArray(int size) {
            return new Education[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(school);
        parcel.writeLong(startDate.getTime());
        parcel.writeLong(endDate.getTime());
        parcel.writeString(courses);
    }
}
