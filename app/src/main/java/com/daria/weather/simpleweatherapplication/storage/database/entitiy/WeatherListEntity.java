package com.daria.weather.simpleweatherapplication.storage.database.entitiy;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.daria.weather.simpleweatherapplication.storage.database.DateConverterUtils;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.common.BaseEntity;

import java.util.Date;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Daria Popova on 06.11.17.
 */
@Entity(tableName = WeatherListEntity.Entity.TABLE_NAME,
        primaryKeys = {WeatherListEntity.Entity.CITY_ID_COLUMN, WeatherListEntity.Entity.DATE_COLUMN},
        foreignKeys = {@ForeignKey(entity = CityEntity.class,
                parentColumns = CityEntity.Entity.ID_COLUMN,
                childColumns = WeatherListEntity.Entity.CITY_ID_COLUMN,
                onDelete = CASCADE,
                onUpdate = CASCADE)})
public class WeatherListEntity {

    @ColumnInfo(name = Entity.DATE_COLUMN)
    @TypeConverters(DateConverterUtils.class)
    @NonNull
    private Date date;
    @ColumnInfo(name = Entity.PRESSURE_COLUMN)
    private double pressure;
    @ColumnInfo(name = Entity.HUMIDITY_COLUMN)
    private double humidity;
    @ColumnInfo(name = Entity.SPEED_COLUMN)
    private double speed;
    @ColumnInfo(name = Entity.DEG_COLUMN)
    //wind direction, degrees
    private int deg;
    @ColumnInfo(name = Entity.CLOUDS_COLUMN)
    private int clouds;
    @ColumnInfo(name = Entity.RAIN_COLUMN)
    private double rain;
    @ColumnInfo(name = Entity.CITY_ID_COLUMN)
    @NonNull
    private Long cityId;
    @Embedded(prefix = "_temp")
    private TemperatureEntity temperature;
    @Embedded(prefix = "_weather")
    private WeatherEntity weather;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherListEntity entity = (WeatherListEntity) o;

        if (Double.compare(entity.pressure, pressure) != 0) return false;
        if (Double.compare(entity.humidity, humidity) != 0) return false;
        if (Double.compare(entity.speed, speed) != 0) return false;
        if (deg != entity.deg) return false;
        if (clouds != entity.clouds) return false;
        if (Double.compare(entity.rain, rain) != 0) return false;
        if (!date.equals(entity.date)) return false;
        if (!cityId.equals(entity.cityId)) return false;
        if (temperature != null ? !temperature.equals(entity.temperature) : entity.temperature != null)
            return false;
        return weather != null ? weather.equals(entity.weather) : entity.weather == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = date.hashCode();
        temp = Double.doubleToLongBits(pressure);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(humidity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(speed);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + deg;
        result = 31 * result + clouds;
        temp = Double.doubleToLongBits(rain);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + cityId.hashCode();
        result = 31 * result + (temperature != null ? temperature.hashCode() : 0);
        result = 31 * result + (weather != null ? weather.hashCode() : 0);
        return result;
    }

    public TemperatureEntity getTemperature() {
        return temperature;
    }

    public void setTemperature(TemperatureEntity temperature) {
        this.temperature = temperature;
    }

    public WeatherEntity getWeather() {
        return weather;
    }

    public void setWeather(WeatherEntity weather) {
        this.weather = weather;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }


    public static final class Entity extends BaseEntity {

        public static final String TABLE_NAME = "list";

        public static final String PRESSURE_COLUMN = "pressure";
        public static final String HUMIDITY_COLUMN = "humidity";
        public static final String SPEED_COLUMN = "speed";
        public static final String DEG_COLUMN = "deg";
        public static final String CLOUDS_COLUMN = "cloud_day";
        public static final String DATE_COLUMN = "date";
        public static final String RAIN_COLUMN = "rain_night";
        public static final String CITY_ID_COLUMN = "city_id";
    }
}
