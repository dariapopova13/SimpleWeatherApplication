package com.daria.weather.simpleweatherapplication.storage.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.daria.weather.simpleweatherapplication.storage.database.entitiy.WeatherListEntity;

import java.util.Collection;
import java.util.List;

/**
 * Created by Daria Popova on 07.11.17.
 */
@Dao
public interface WeatherListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WeatherListEntity... weatherListEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Collection<WeatherListEntity> weatherLists);

    @Query("SELECT * FROM " + WeatherListEntity.Entity.TABLE_NAME)
    List<WeatherListEntity> selectAll();

    @Query("SELECT * FROM " + WeatherListEntity.Entity.TABLE_NAME
            + " WHERE " + WeatherListEntity.Entity.CITY_ID_COLUMN + " = :cityId")
    List<WeatherListEntity> selectAllByCityId(Long cityId);
}
