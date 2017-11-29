package com.example.iamjiangtian.myresume.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

/**
 * Created by iamjiangtian on 9/26/16.
 */
public class Project implements Parcelable{
    public String id;
    public String projectName;
    public String detail;

    public Project(){
        id = UUID.randomUUID().toString();
    }
    protected Project(Parcel in) {
        id = in.readString();
        projectName = in.readString();
        detail = in.readString();
    }

    public static final Creator<Project> CREATOR = new Creator<Project>() {
        @Override
        public Project createFromParcel(Parcel in) {
            return new Project(in);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(projectName);
        parcel.writeString(detail);
    }
}
