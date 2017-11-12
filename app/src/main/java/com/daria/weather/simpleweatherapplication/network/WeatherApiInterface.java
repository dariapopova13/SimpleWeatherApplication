package com.daria.weather.simpleweatherapplication.network;


import com.daria.weather.simpleweatherapplication.network.data.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Daria Popova on 25.09.17.
 */

public interface WeatherApiInterface {

    @GET
    Call<WeatherResponse> getWeather(@Url String url);

}
