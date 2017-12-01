package com.daria.weather.simpleweatherapplication.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.daria.weather.simpleweatherapplication.repository.WeatherDataRepository;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.CityWithWeather;

import java.util.List;

/**
 * Created by Daria Popova on 09.11.17.
 */

public class WeatherViewModel extends AndroidViewModel {

    private WeatherDataRepository repository;


    public WeatherViewModel(@NonNull Application application, WeatherDataRepository repository) {
        super(application);
        this.repository = repository;
    }

    public LiveData<List<CityWithWeather>> getCityWithWeather(boolean update) {
        return repository.getWeatherLists(update);
    }


}
