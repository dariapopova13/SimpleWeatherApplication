package com.daria.weather.simpleweatherapplication.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.daria.weather.simpleweatherapplication.network.WeatherSynchronizer;
import com.daria.weather.simpleweatherapplication.storage.WeatherStorageManager;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.CityWithWeather;
import com.daria.weather.simpleweatherapplication.utils.UrlUtils;

import java.util.List;

/**
 * Created by Daria Popova on 20.11.17.
 */

public class WeatherDataRepositoryImpl implements WeatherDataRepository{

    private final WeatherStorageManager manager;
    private final WeatherSynchronizer synchronizer;
    private final Context context;
    private final UrlUtils urlUtils;

    public WeatherDataRepositoryImpl(WeatherStorageManager manager,
                                     WeatherSynchronizer synchronizer,
                                     Context context,
                                     UrlUtils urlUtils) {
        this.manager = manager;
        this.context = context;
        this.synchronizer = synchronizer;
        this.urlUtils = urlUtils;
    }


    @Override
    public LiveData<List<CityWithWeather>> getWeatherLists(boolean update) {
        if (update)
            updateWeatherDataFromNet();
        return manager.getAll();
    }

    private void updateWeatherDataFromNet() {
        String url = urlUtils.getUrlFromPreferences();
        synchronizer.synchronize(url);
    }

}
