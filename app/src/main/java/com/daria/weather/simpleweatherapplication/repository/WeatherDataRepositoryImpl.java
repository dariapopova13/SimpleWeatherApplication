package com.daria.weather.simpleweatherapplication.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.daria.weather.simpleweatherapplication.network.WeatherSynchronizer;
import com.daria.weather.simpleweatherapplication.storage.WeatherStorageManager;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.CityWithWeather;

import java.util.List;

/**
 * Created by Daria Popova on 20.11.17.
 */

public class WeatherDataRepositoryImpl implements WeatherDataRepository {

    private final WeatherStorageManager manager;
    private final WeatherSynchronizer synchronizer;
    private final Context context;

    public WeatherDataRepositoryImpl(WeatherStorageManager manager,
                                     WeatherSynchronizer synchronizer,
                                     Context context) {
        this.manager = manager;
        this.context = context;
        this.synchronizer = synchronizer;
    }


    @Override
    public LiveData<List<CityWithWeather>> getWeatherLists() {
        updateWeatherDataFromNet();
        return manager.getAll();
    }

    private void updateWeatherDataFromNet() {
        // FIXME: 26.11.17 remove
        String url = "http://api.openweathermap.org/data/2.5" +
                "/forecast/daily?q=Kazan,RU&units=metric&cnt=7&APPID=9b8203c6b9b90ba0d77e5b07f943d216";

        synchronizer.synchronize(url);
    }


}
