package com.daria.weather.simpleweatherapplication.di.app;

import com.daria.weather.simpleweatherapplication.network.WeatherSynchronizer;
import com.daria.weather.simpleweatherapplication.network.service.WeatherApiService;
import com.daria.weather.simpleweatherapplication.network.service.WeatherCallbackResponse;
import com.daria.weather.simpleweatherapplication.storage.WeatherStorageManager;
import com.daria.weather.simpleweatherapplication.utils.EntityUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Daria Popova on 19.11.17.
 */
@Module
public class NetWorkModule {


    @Singleton
    @Provides
    public WeatherSynchronizer provideWeatherSynchronizer(WeatherCallbackResponse response,
                                                          WeatherApiService service) {
        return new WeatherSynchronizer(response, service);
    }

    @Singleton
    @Provides
    public WeatherCallbackResponse provideWeatherCallbackResponse(EntityUtils entityUtils,
                                                                  WeatherStorageManager manager) {
        return new WeatherCallbackResponse(entityUtils, manager);
    }

    @Singleton
    @Provides
    public WeatherApiService provideRetrofit(GsonConverterFactory factory,
                                             String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(factory)
                .build()
                .create(WeatherApiService.class);
    }

    @Singleton
    @Provides
    public GsonConverterFactory provideGsonConverterFactory() {
        JsonDeserializer<Date> deserializer = new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return json == null ? null : new Date(json.getAsLong() * 1000);
            }
        };
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, deserializer).create();

        return GsonConverterFactory.create(gson);
    }

}
