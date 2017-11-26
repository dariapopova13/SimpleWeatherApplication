package com.daria.weather.simpleweatherapplication.network.service;


import com.daria.weather.simpleweatherapplication.network.data.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Daria Popova on 25.09.17.
 */

public interface WeatherApiService {

    @GET
    Call<WeatherResponse> getWeather(@Url String url);
//
//    @GET
//    Call<WeatherResponse> getWeatherResponse(@Query("q") String location);
//
//    @GET
//    Call<WeatherResponse> getWeatherResponse(@Query("q") String location,
//                                             @Query("units") String units);
//
//
//    @GET
//    Call<WeatherResponse> getWeatherResponse(@Query("q") String location,
//                                             @Query("units") String units,
//                                             @Query("cnt") int cnt);
//
//
}
