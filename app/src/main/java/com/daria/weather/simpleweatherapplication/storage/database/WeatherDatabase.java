package com.daria.weather.simpleweatherapplication.storage.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.daria.weather.simpleweatherapplication.storage.database.dao.CityDao;
import com.daria.weather.simpleweatherapplication.storage.database.dao.WeatherListDao;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.CityEntity;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.CityWithWeather;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.WeatherListEntity;


/**
 * Created by Daria Popova on 06.11.17.
 */
@Database(entities = {CityEntity.class, WeatherListEntity.class},
        version = WeatherDatabase.DATABASE_VERSION)
public abstract class WeatherDatabase extends RoomDatabase {

    public final static String DATABASE_NAME = "weather.db";
    public final static int DATABASE_VERSION = 3;

    public abstract CityDao cityDao();

    public abstract WeatherListDao weatherListRepository();

    public void insertCityWithWeather(CityWithWeather cityWithWeather) {

        try {
            beginTransaction();
            cityDao().delete(cityWithWeather.getCity());
            cityDao().insert(cityWithWeather.getCity());
            weatherListRepository().insert(cityWithWeather.getWeatherLists());
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }
}
