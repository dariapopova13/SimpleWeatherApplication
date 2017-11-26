package com.daria.weather.simpleweatherapplication.repository;

import android.arch.lifecycle.LiveData;

import com.daria.weather.simpleweatherapplication.storage.database.entitiy.CityWithWeather;

import java.util.List;

/**
 * Created by Daria Popova on 20.11.17.
 */

public interface WeatherDataRepository {

    LiveData<List<CityWithWeather>> getWeatherLists();

}
