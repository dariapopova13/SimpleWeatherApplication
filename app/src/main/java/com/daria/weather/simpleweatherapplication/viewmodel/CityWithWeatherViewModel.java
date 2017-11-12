package com.daria.weather.simpleweatherapplication.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import com.daria.weather.simpleweatherapplication.network.WeatherSynchronizer;
import com.daria.weather.simpleweatherapplication.storage.WeatherStorageManager;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.CityWithWeather;
import com.daria.weather.simpleweatherapplication.utils.UrlUtils;

import java.util.List;

/**
 * Created by Daria Popova on 09.11.17.
 */

public class CityWithWeatherViewModel extends AndroidViewModel
        implements WeatherSynchronizer.NetworkDataUpdaterListener {

    private LiveData<List<CityWithWeather>> cityWithWeather;
    private WeatherStorageManager storageManager;
    private WeatherSynchronizer weatherSynchronizer;
    private Context context;

    @Override
    public void update(CityWithWeather cityWithWeather) {
        store(cityWithWeather);
    }

    public CityWithWeatherViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        storageManager = new WeatherStorageManager(context);
        weatherSynchronizer = WeatherSynchronizer.getInstance(this);
    }


    public LiveData<List<CityWithWeather>> getCityWithWeather(boolean isUpdated) {
        if (cityWithWeather == null) {
            cityWithWeather = new MutableLiveData<>();
            loadWeather();
        }
        if (!isUpdated)
            loadDataFromNetwork();
        return cityWithWeather;
    }

    public void loadWeather() {
        cityWithWeather = storageManager.getAll();
    }

    public void loadDataFromNetwork() {
        weatherSynchronizer.synchronize(UrlUtils.getUrlFromPreferences(context));
    }

    public void store(CityWithWeather cityWithWeather) {
        storageManager.store(cityWithWeather);
        loadWeather();
    }

}
