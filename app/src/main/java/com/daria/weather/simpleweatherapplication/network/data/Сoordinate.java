package com.daria.weather.simpleweatherapplication.network.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Daria Popova on 25.09.17.
 */

public class Сoordinate {

    @SerializedName("lat")
    private double latitude;
    @SerializedName("lon")
    private double longtitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }
}
