package com.daria.weather.simpleweatherapplication.network;

import com.daria.weather.simpleweatherapplication.network.data.WeatherResponse;
import com.daria.weather.simpleweatherapplication.storage.WeatherStorageManager;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.CityWithWeather;
import com.daria.weather.simpleweatherapplication.utils.EntityUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Daria Popova on 09.11.17.
 */

public class WeatherSynchronizer {


    private CityWithWeather cityWithWeather;
    private WeatherStorageManager storageManager;
    private static WeatherSynchronizer synchronizer;
    private static List<NetworkDataUpdaterListener> listeners;

    public static WeatherSynchronizer getInstance(NetworkDataUpdaterListener listener) {
        if (synchronizer == null)
            synchronizer = new WeatherSynchronizer();
        if (listeners == null)
            listeners = new ArrayList<>();
        listeners.add(listener);

        return synchronizer;
    }

    public void synchronize(String url) {

        Retrofit retrofit = WeatherApiClient.getClient();
        WeatherApiInterface service = retrofit.create(WeatherApiInterface.class);

        service.getWeather(url).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                WeatherResponse weatherResponse = response.body();
                cityWithWeather = EntityUtils.toEntity(weatherResponse.getCity(),
                        weatherResponse.getWeatherList());
                sendCallbackToListeners(cityWithWeather);
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {

            }
        });

    }

    private void sendCallbackToListeners(CityWithWeather cityWithWeather) {
        for (NetworkDataUpdaterListener listener : listeners) {
            listener.update(cityWithWeather);
        }
    }


    public interface NetworkDataUpdaterListener {
        void update(CityWithWeather cityWithWeather);
    }

}
