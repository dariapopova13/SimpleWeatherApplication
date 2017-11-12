package com.daria.weather.simpleweatherapplication.network;

import com.daria.weather.simpleweatherapplication.utils.UrlUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Daria Popova on 25.09.17.
 */

public class WeatherApiClient {

    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(UrlUtils.WEATHER_BASE_URL)
                    .addConverterFactory(create())
                    .build();
        }

        return retrofit;
    }

    private static GsonConverterFactory create() {

        JsonDeserializer<Date> deserializer = new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return json == null ? null : new Date( json.getAsLong() * 1000);
            }
        };

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, deserializer).create();
        return GsonConverterFactory.create(gson);
    }
}
