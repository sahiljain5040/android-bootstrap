package com.project.castleglobal.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.io.Serializable;

/**308.61
 * Created by sahil on 10/14/17.
 */

public class Restaurant implements Parcelable, Serializable{

    private String id;
    private String name;
    private String url;
    private String cuisines;
    private int averageCostForTwo;
    private String thumb;
    private UserRating userRating;

    protected Restaurant(Parcel in) {
        id = in.readString();
        name = in.readString();
        url = in.readString();
        cuisines = in.readString();
        averageCostForTwo = in.readInt();
        thumb = in.readString();
        userRating = (UserRating) in.readSerializable();
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public int getAverageCostForTwo() {
        return averageCostForTwo;
    }

    public void setAverageCostForTwo(int averageCostForTwo) {
        this.averageCostForTwo = averageCostForTwo;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public UserRating getUserRating() {
        return userRating;
    }

    public void setUserRating(UserRating userRating) {
        this.userRating = userRating;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(url);
        parcel.writeString(cuisines);
        parcel.writeInt(averageCostForTwo);
        parcel.writeString(thumb);
        parcel.writeSerializable(userRating);
    }
}
