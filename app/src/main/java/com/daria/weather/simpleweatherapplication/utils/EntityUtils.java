package com.daria.weather.simpleweatherapplication.utils;

import com.daria.weather.simpleweatherapplication.network.data.City;
import com.daria.weather.simpleweatherapplication.network.data.Temperature;
import com.daria.weather.simpleweatherapplication.network.data.Weather;
import com.daria.weather.simpleweatherapplication.network.data.WeatherList;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.CityEntity;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.CityWithWeather;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.TemperatureEntity;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.WeatherEntity;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.WeatherListEntity;

import java.util.ArrayList;
import java.util.List;

import dagger.Reusable;

/**
 * Created by Daria Popova on 09.11.17.
 */
@Reusable
public final class EntityUtils {


    public CityEntity toEntity(City city) {
        final CityEntity entity = new CityEntity();
        entity.setName(city.getName());
        entity.setId(city.getId());
        entity.setCountry(city.getCountry());

        return entity;
    }


    public WeatherListEntity toEntity(WeatherList weatherList) {
        final WeatherListEntity entity = new WeatherListEntity();
        entity.setHumidity(weatherList.getHumidity());
        entity.setPressure(weatherList.getPressure());
        entity.setDeg(weatherList.getWindDirection());
        entity.setSpeed(weatherList.getSpeed());
        entity.setClouds(weatherList.getClouds());
        entity.setDate(weatherList.getDate());
        entity.setTemperature(toEntity(weatherList.getTemperature()));
        entity.setWeather(toEntity(selectSingleWeather(weatherList.getWeathers())));
        entity.setRain(weatherList.getRain());
        entity.setSnow(weatherList.getSnow());
        return entity;
    }

    private WeatherEntity toEntity(Weather weather) {
        final WeatherEntity entity = new WeatherEntity();
        entity.setIcon(weather.getIconId());
        entity.setMain(weather.getMain());
        entity.setDescription(weather.getDescription());
        entity.setId(weather.getId());

        return entity;
    }

    private Weather selectSingleWeather(List<Weather> weathers) {
        if (weathers.isEmpty())
            return null;
        return weathers.get(0);
    }

    private TemperatureEntity toEntity(Temperature temperature) {
        final TemperatureEntity entity = new TemperatureEntity();
        entity.setMornTemp((int) temperature.getMorn());
        entity.setMaxTemp((int) temperature.getMax());
        entity.setMinTemp((int) temperature.getMin());
        entity.setEveTemp((int) temperature.getEve());
        entity.setNightTemp((int) temperature.getNight());
        entity.setDayTemp((int) temperature.getDay());

        return entity;
    }

    public CityWithWeather toEntity(City city, List<WeatherList> weatherLists) {
        final CityWithWeather entity = new CityWithWeather();
        entity.setCity(toEntity(city));
        List<WeatherListEntity> weatherListEntitySet = new ArrayList<>();
        entity.setWeatherLists(weatherListEntitySet);

        WeatherListEntity temp;
        for (WeatherList list : weatherLists) {
            temp = toEntity(list);
            temp.setCityId(city.getId());
            weatherListEntitySet.add(temp);
        }

        return entity;
    }
}
