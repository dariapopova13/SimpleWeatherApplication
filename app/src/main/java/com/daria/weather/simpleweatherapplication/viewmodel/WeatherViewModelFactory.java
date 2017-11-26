package com.daria.weather.simpleweatherapplication.viewmodel;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.daria.weather.simpleweatherapplication.repository.WeatherDataRepository;

/**
 * Created by Daria Popova on 19.11.17.
 */

public class WeatherViewModelFactory implements ViewModelProvider.Factory {


    private final WeatherDataRepository repository;
    private final Application application;

    public WeatherViewModelFactory(Application application, WeatherDataRepository repository) {
        this.application = application;
        this.repository = repository;
    }

    @NonNull
    @Override
    public WeatherViewModel create(@NonNull Class modelClass) {
        return new WeatherViewModel(application, repository);
    }
}
