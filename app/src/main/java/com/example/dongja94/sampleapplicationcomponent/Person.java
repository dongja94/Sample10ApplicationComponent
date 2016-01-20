package com.example.dongja94.sampleapplicationcomponent;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dongja94 on 2016-01-20.
 */
public class Person implements Parcelable {
    String message;
    String name;
    int age;
//    boolean isMan;

    public Person() {
    }

    public Person(Parcel source) {
        message = source.readString();
        name = source.readString();
        age = source.readInt();
//        byte b = source.readByte();
//        isMan = (b==1)?true:false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(message);
        dest.writeString(name);
        dest.writeInt(age);
//        dest.writeByte(isMan?(byte)1:(byte)0);
    }

    public static Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
