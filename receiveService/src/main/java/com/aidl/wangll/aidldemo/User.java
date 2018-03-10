package com.aidl.wangll.aidldemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/10/27 0027.
 */

public class User implements Parcelable {
    private String name;
    private int age;

    public User(){}

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    protected User(Parcel in) {
        name = in.readString();
        age = in.readInt();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(age);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }


    public void readFromParcel(Parcel in){
        this.name = in.readString();
        this.age = in.readInt();
    }

}
