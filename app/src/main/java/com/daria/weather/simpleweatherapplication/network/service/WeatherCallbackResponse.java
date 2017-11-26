package com.daria.weather.simpleweatherapplication.network.service;

import com.daria.weather.simpleweatherapplication.network.data.WeatherResponse;
import com.daria.weather.simpleweatherapplication.storage.WeatherStorageManager;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.CityWithWeather;
import com.daria.weather.simpleweatherapplication.utils.EntityUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Daria Popova on 19.11.17.
 */

public class WeatherCallbackResponse implements Callback<WeatherResponse> {

    private final EntityUtils entityUtils;
    private final WeatherStorageManager manager;

    public WeatherCallbackResponse(EntityUtils entityUtils, WeatherStorageManager manager) {
        this.entityUtils = entityUtils;
        this.manager = manager;
    }

    @Override
    public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
        WeatherResponse weatherResponse = response.body();
        CityWithWeather cityWithWeather = entityUtils.toEntity(weatherResponse.getCity(),
                weatherResponse.getWeatherList());
        manager.store(cityWithWeather);
    }

    @Override
    public void onFailure(Call<WeatherResponse> call, Throwable t) {

    }
}