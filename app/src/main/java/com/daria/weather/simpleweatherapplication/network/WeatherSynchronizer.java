package com.daria.weather.simpleweatherapplication.network;

import com.daria.weather.simpleweatherapplication.network.service.WeatherApiService;
import com.daria.weather.simpleweatherapplication.network.service.WeatherCallbackResponse;

/**
 * Created by Daria Popova on 09.11.17.
 */
public class WeatherSynchronizer {

    private final WeatherCallbackResponse callbackResponse;
    private final WeatherApiService service;

    public WeatherSynchronizer(WeatherCallbackResponse callbackResponse,
                               WeatherApiService service) {
        this.callbackResponse = callbackResponse;
        this.service = service;
    }

    public void synchronize(String url) {
        service.getWeather(url).enqueue(callbackResponse);
    }


}
