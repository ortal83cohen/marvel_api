package com.marvel.api.ortal.data;


import android.os.Parcel;
import android.os.Parcelable;

public class Character implements Parcelable {

    public static final Creator<Character> CREATOR = new Creator<Character>() {
        @Override
        public Character createFromParcel(Parcel in) {
            return new Character(in);
        }

        @Override
        public Character[] newArray(int size) {
            return new Character[size];
        }
    };
    public String name;
    public String thumbnail;
    public String description;

    public Character() {
    }

    public Character(String name, String thumbnail, String description) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.description = description;
    }

    protected Character(Parcel in) {
        name = in.readString();
        thumbnail = in.readString();
        description = in.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(thumbnail);
        dest.writeString(description);
    }
}