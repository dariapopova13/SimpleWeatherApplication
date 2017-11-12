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
    private Double pressure;
    @ColumnInfo(name = Entity.HUMIDITY_COLUMN)
    private Double humidity;
    @ColumnInfo(name = Entity.SPEED_COLUMN)
    private Double speed;
    @ColumnInfo(name = Entity.DEG_COLUMN)
    //wind direction, degrees
    private Integer deg;
    @ColumnInfo(name = Entity.CLOUDS_COLUMN)
    private Integer clouds;
    @ColumnInfo(name = Entity.RAIN_COLUMN)
    private Double rain;
    @ColumnInfo(name = Entity.CITY_ID_COLUMN)
    @NonNull
    private Long cityId;
    @Embedded(prefix = "_temp")
    private TemperatureEntity temperature;
    @Embedded(prefix = "_weather")
    private WeatherEntity weather;


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherListEntity that = (WeatherListEntity) o;

        if (!date.equals(that.date)) return false;
        if (!pressure.equals(that.pressure)) return false;
        if (!humidity.equals(that.humidity)) return false;
        if (!speed.equals(that.speed)) return false;
        if (deg != null ? !deg.equals(that.deg) : that.deg != null) return false;
        if (clouds != null ? !clouds.equals(that.clouds) : that.clouds != null) return false;
        if (rain != null ? !rain.equals(that.rain) : that.rain != null) return false;
        if (!cityId.equals(that.cityId)) return false;
        if (!temperature.equals(that.temperature)) return false;
        return weather.equals(that.weather);
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + pressure.hashCode();
        result = 31 * result + humidity.hashCode();
        result = 31 * result + speed.hashCode();
        result = 31 * result + (deg != null ? deg.hashCode() : 0);
        result = 31 * result + (clouds != null ? clouds.hashCode() : 0);
        result = 31 * result + (rain != null ? rain.hashCode() : 0);
        result = 31 * result + cityId.hashCode();
        result = 31 * result + temperature.hashCode();
        result = 31 * result + weather.hashCode();
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

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getDeg() {
        return deg;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    public Integer getClouds() {
        return clouds;
    }

    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }

    public Double getRain() {
        return rain;
    }

    public void setRain(Double rain) {
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
        public static final String CLOUDS_COLUMN = "clouds";
        public static final String DATE_COLUMN = "date";
        public static final String RAIN_COLUMN = "rain";
        public static final String CITY_ID_COLUMN = "city_id";
    }
}
