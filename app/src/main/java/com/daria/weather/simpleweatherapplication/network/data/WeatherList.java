package com.daria.weather.simpleweatherapplication.network.data;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by Daria Popova on 25.09.17.
 */

public class WeatherList {


    @SerializedName("dt")
    private Date date;
    @SerializedName("temp")
    private Temperature temperature;
    private double pressure;
    private double humidity;
    @SerializedName("weather")
    private List<Weather> weathers;
    @SerializedName("speed")
    private double windSpeed;
    @SerializedName("deg")
    private int windDirection;
    private int clouds;
    private double rain;


    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public List<Weather> getWeathers() {
        return weathers;
    }

    public void setWeathers(List<Weather> weathers) {
        this.weathers = weathers;
    }

    public double getSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(int windDirection) {
        this.windDirection = windDirection;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }
}
