package com.daria.weather.simpleweatherapplication.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.daria.weather.simpleweatherapplication.network.WeatherSynchronizer;
import com.daria.weather.simpleweatherapplication.storage.WeatherStorageManager;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.CityWithWeather;

import java.util.List;

/**
 * Created by Daria Popova on 09.11.17.
 */

public class CityWithWeatherViewModel extends AndroidViewModel
        implements WeatherSynchronizer.NetworkDataUpdaterListener {

    private LiveData<List<CityWithWeather>> cityWithWeather;
    private WeatherStorageManager storageManager;
    private WeatherSynchronizer weatherSynchronizer;
    // FIXME: 14.11.17 replace this fragment
    private String currentUrl;

    @Override
    public void update(CityWithWeather cityWithWeather) {
        store(cityWithWeather);
    }

    public CityWithWeatherViewModel(@NonNull Application application) {
        super(application);
        storageManager = WeatherStorageManager.getInstance(application);
        weatherSynchronizer = WeatherSynchronizer.getInstance(application, this);
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

    public void setCurrentUrl(String urlFromPreferences) {
        this.currentUrl = urlFromPreferences;
    }

    private void loadWeather() {
        cityWithWeather = storageManager.getAll();
    }

    public void loadDataFromNetwork() {
        weatherSynchronizer.synchronize(currentUrl);
    }

    public void store(CityWithWeather cityWithWeather) {
        storageManager.store(cityWithWeather);
        loadWeather();
    }

}
