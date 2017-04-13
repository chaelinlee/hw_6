package com.example.leechaelin.hw_5;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by leechaelin on 2017. 4. 7..
 */

public class Data implements Parcelable{
    String name,phonenum,homepage,enrolldate,category;
    String menu1,menu2,menu3;

    public Data(String name,String phonenum,String menu1,String menu2,String menu3,String homepage,String enrolldate,String category){
        this.name = name;
        this.phonenum = phonenum;
        this.menu1 = menu1;
        this.menu2= menu2;
        this.menu3 = menu3;
        this.homepage = homepage;
        this.enrolldate=enrolldate;
        this.category= category;

    }

    public String getName() {
        return name;
    }


    public static final Creator<Data>CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel source) {
            return new Data(source);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };
    public Data(Parcel in){
        name = in.readString();
        phonenum=in.readString();
        menu1 = in.readString();
        menu2 = in.readString();
        menu3 = in.readString();
        homepage = in.readString();
        enrolldate=in.readString();
        category=in.readString();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(phonenum);
        dest.writeString(menu1);
        dest.writeString(menu2);
        dest.writeString(menu3);
        dest.writeString(homepage);
        dest.writeString(enrolldate);
        dest.writeString(category);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
