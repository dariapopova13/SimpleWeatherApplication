package com.daria.weather.simpleweatherapplication.storage.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import com.daria.weather.simpleweatherapplication.storage.database.entitiy.WeatherListEntity;

import java.util.Collection;

/**
 * Created by Daria Popova on 07.11.17.
 */
@Dao
public interface WeatherListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WeatherListEntity... weatherListEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Collection<WeatherListEntity> weatherLists);

}
