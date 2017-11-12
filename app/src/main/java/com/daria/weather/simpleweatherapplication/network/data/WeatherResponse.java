package com.daria.weather.simpleweatherapplication.network.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Daria Popova on 25.09.17.
 */

public class WeatherResponse {

    private City city;
    private int code;
    private String message;
    @SerializedName("cnt")
    private int linesCount;
    @SerializedName("list")
    private List<WeatherList> weatherList;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getLinesCount() {
        return linesCount;
    }

    public void setLinesCount(int linesCount) {
        this.linesCount = linesCount;
    }

    public List<WeatherList> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(List<WeatherList> weatherList) {
        this.weatherList = weatherList;
    }
}
