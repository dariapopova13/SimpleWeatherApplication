package com.daria.weather.simpleweatherapplication.network.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Daria Popova on 25.09.17.
 */

public class City {

    private long id;
    private String name;
    @SerializedName("coord")
    private Сoordinate сoordinate;
    private String country;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Сoordinate getСoordinate() {
        return сoordinate;
    }

    public void setСoordinate(Сoordinate сoordinate) {
        this.сoordinate = сoordinate;
    }

}
