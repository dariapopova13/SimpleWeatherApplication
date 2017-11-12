package com.daria.weather.simpleweatherapplication.storage.database.entitiy;


import android.arch.persistence.room.ColumnInfo;

import com.daria.weather.simpleweatherapplication.storage.database.entitiy.common.BaseEntity;

/**
 * Created by Daria Popova on 06.11.17.
 */
public class TemperatureEntity {

    @ColumnInfo(name = Entity.MIN_COLUMN)
    private int minTemp;
    @ColumnInfo(name = Entity.MAX_COLUMN)
    private int maxTemp;
    @ColumnInfo(name = Entity.DAY_COLUMN)
    private int dayTemp;
    @ColumnInfo(name = Entity.NIGHT_COLUMN)
    private int nightTemp;
    @ColumnInfo(name = Entity.EVE_COLUMN)
    private int eveTemp;
    @ColumnInfo(name = Entity.MORN_COLUMN)
    private int mornTemp;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TemperatureEntity that = (TemperatureEntity) o;

        if (minTemp != that.minTemp) return false;
        if (maxTemp != that.maxTemp) return false;
        if (dayTemp != that.dayTemp) return false;
        if (nightTemp != that.nightTemp) return false;
        if (eveTemp != that.eveTemp) return false;
        return mornTemp == that.mornTemp;
    }

    @Override
    public int hashCode() {
        int result = minTemp;
        result = 31 * result + maxTemp;
        result = 31 * result + dayTemp;
        result = 31 * result + nightTemp;
        result = 31 * result + eveTemp;
        result = 31 * result + mornTemp;
        return result;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public int getDayTemp() {
        return dayTemp;
    }

    public void setDayTemp(int dayTemp) {
        this.dayTemp = dayTemp;
    }

    public int getNightTemp() {
        return nightTemp;
    }

    public void setNightTemp(int nightTemp) {
        this.nightTemp = nightTemp;
    }

    public int getEveTemp() {
        return eveTemp;
    }

    public void setEveTemp(int eveTemp) {
        this.eveTemp = eveTemp;
    }

    public int getMornTemp() {
        return mornTemp;
    }

    public void setMornTemp(int mornTemp) {
        this.mornTemp = mornTemp;
    }

    public static final class Entity extends BaseEntity {

        public static final String MIN_COLUMN = "min";
        public static final String MAX_COLUMN = "max";
        public static final String DAY_COLUMN = "day";
        public static final String NIGHT_COLUMN = "night";
        public static final String EVE_COLUMN = "eve";
        public static final String MORN_COLUMN = "morn";
    }
}
