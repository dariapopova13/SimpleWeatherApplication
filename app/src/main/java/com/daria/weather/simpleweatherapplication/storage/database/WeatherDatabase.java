package com.daria.weather.simpleweatherapplication.storage.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.Transaction;
import android.content.Context;

import com.daria.weather.simpleweatherapplication.storage.database.dao.CityDao;
import com.daria.weather.simpleweatherapplication.storage.database.dao.WeatherListDao;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.CityEntity;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.CityWithWeather;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.WeatherListEntity;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by Daria Popova on 06.11.17.
 */
@Database(entities = {CityEntity.class, WeatherListEntity.class},
        version = WeatherDatabase.DATABASE_VERSION)
public abstract class WeatherDatabase extends RoomDatabase {

    public final static String DATABASE_NAME = "weather.db";
    public final static int DATABASE_VERSION = 1;
    private static WeatherDatabase instance;

    public static synchronized WeatherDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room
                    .databaseBuilder(context.getApplicationContext(), WeatherDatabase.class, DATABASE_NAME)
                    .build();
        }
        return instance;
    }

    public abstract CityDao cityDao();

    public abstract WeatherListDao weatherListRepository();

    @Transaction
    public void insertCityWithWeather(CityWithWeather... cityWithWeathers) {

        Set<CityEntity> cityEntities = new HashSet<>();
        Set<WeatherListEntity> weatherListEntities = new HashSet<>();
        for (CityWithWeather cityWithWeather : cityWithWeathers) {
            if (cityWithWeather.getCity() == null)
                continue;
            cityEntities.add(cityWithWeather.getCity());
            weatherListEntities.addAll(cityWithWeather.getWeatherLists());
        }

        cityDao().delete(cityEntities);
        cityDao().insert(cityEntities);
        weatherListRepository().insert(weatherListEntities);
    }
}
