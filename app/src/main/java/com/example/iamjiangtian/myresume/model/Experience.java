package com.example.iamjiangtian.myresume.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by iamjiangtian on 9/26/16.
 */
public class Experience implements Parcelable{
    public String id;
    public String company;
    public String position;
    public Date startDate;
    public Date endDate;
    public String detail;

    @Override
    public int describeContents(){
        return 0;
    }
    @Override
    public void writeToParcel(Parcel out, int flags){
        out.writeString(id);
        out.writeString(company);
        out.writeString(position);
        out.writeLong(startDate.getTime());
        out.writeLong(endDate.getTime());
        out.writeString(detail);
    }
    public static final Parcelable.Creator<Experience> CREATOR = new Parcelable.Creator<Experience>(){
        public Experience createFromParcel(Parcel in){
            return new Experience(in);
        }
        public  Experience[] newArray(int size){
            return new Experience[size];
        }
    };
    public Experience(){
        id = UUID.randomUUID().toString();
    }
    protected Experience(Parcel in){
        id = in.readString();
        company = in.readString();
        position = in.readString();
        startDate = new Date(in.readLong());
        endDate = new Date(in.readLong());
        detail = in.readString();
    };
}
