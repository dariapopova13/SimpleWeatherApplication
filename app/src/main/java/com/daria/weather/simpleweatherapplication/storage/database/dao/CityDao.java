package com.daria.weather.simpleweatherapplication.storage.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.daria.weather.simpleweatherapplication.storage.database.entitiy.CityEntity;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.CityWithWeather;

import java.util.List;

/**
 * Created by Daria Popova on 07.11.17.
 */
@Dao
public interface CityDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CityEntity... cities);


    @Query("SELECT * FROM " + CityEntity.Entity.TABLE_NAME + " WHERE "
            + CityEntity.Entity.ID_COLUMN + " = :id")
    @Transaction
    LiveData<CityWithWeather> getById(Long id);


    @Delete
    @Transaction
    int delete(CityEntity... cities);


    @Query("SELECT * FROM " + CityEntity.Entity.TABLE_NAME)
    @Transaction
    LiveData<List<CityWithWeather>> getAll();
}
