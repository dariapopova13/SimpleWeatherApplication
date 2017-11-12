package com.daria.weather.simpleweatherapplication.storage.database.entitiy;


import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Daria Popova on 06.11.17.
 */
public class CityWithWeather {

    @Embedded
    private CityEntity city;
    @Relation(parentColumn = CityEntity.Entity.ID_COLUMN,
            entityColumn = WeatherListEntity.Entity.CITY_ID_COLUMN)
    private List<WeatherListEntity> weatherLists = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CityWithWeather that = (CityWithWeather) o;

        if (!city.equals(that.city)) return false;

        return isWeatherListEquals(weatherLists, that.weatherLists);
    }

    @Override
    public int hashCode() {
        int result = city.hashCode();
        result = 31 * result + weatherLists.hashCode();
        return result;
    }

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    public List<WeatherListEntity> getWeatherLists() {
        return weatherLists;
    }

    public void setWeatherLists(List<WeatherListEntity> weatherLists) {
        this.weatherLists = weatherLists;
    }

    private boolean isWeatherListEquals(Collection<WeatherListEntity> a,
                                        Collection<WeatherListEntity> b) {

        if (a.size() != b.size()) {
            return false;
        }
        int aFrequency, bFrequenct;
        Set<WeatherListEntity> temp = new HashSet<>(a);
        temp.addAll(b);
        for (WeatherListEntity weatherListEntity : temp) {
            aFrequency = Collections.frequency(a, weatherListEntity);
            bFrequenct = Collections.frequency(b, weatherListEntity);
            if (aFrequency != bFrequenct)
                return false;
        }

        return true;
    }
}
