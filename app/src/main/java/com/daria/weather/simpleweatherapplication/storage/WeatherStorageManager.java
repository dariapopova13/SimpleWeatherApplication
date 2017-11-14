package com.daria.weather.simpleweatherapplication.storage;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import com.daria.weather.simpleweatherapplication.storage.database.WeatherDatabase;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.CityEntity;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.CityWithWeather;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Daria Popova on 09.11.17.
 */

public class WeatherStorageManager {

    private static WeatherDatabase database;
    private static WeatherStorageManager manager;

    private WeatherStorageManager() {
    }

    public static WeatherStorageManager getInstance(Context context) {
        if (database == null) {
            database = WeatherDatabase.getInstance(context);
        }
        if (manager == null)
            manager = new WeatherStorageManager();
        return manager;
    }

    public void store(CityWithWeather... cityWithWeathers) {
        new InsertAsyncTask().execute(cityWithWeathers);
    }

    public void delete(CityWithWeather cityWithWeather) {
        new DeleteAsyncTask().execute(cityWithWeather);
    }

    public LiveData<CityWithWeather> getById(long id) {
        try {
            return new GetAsyncTask().execute(id).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public LiveData<List<CityWithWeather>> getAll() {
        try {
            return new GetAllAsyncTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class GetAsyncTask extends AsyncTask<Long, Void, LiveData<CityWithWeather>> {

        @Override
        protected LiveData<CityWithWeather> doInBackground(Long... longs) {
            return database.cityDao().getById(longs[0]);
        }
    }

    private static class GetAllAsyncTask extends AsyncTask<Void, Void, LiveData<List<CityWithWeather>>> {

        @Override
        protected LiveData<List<CityWithWeather>> doInBackground(Void... voids) {
            return database.cityDao().getAll();
        }
    }

    private static class InsertAsyncTask extends AsyncTask<CityWithWeather, Void, Void> {

        @Override
        protected Void doInBackground(CityWithWeather... cityWithWeathers) {
            database.insertCityWithWeather(cityWithWeathers);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<CityWithWeather, Void, Void> {


        protected Void doInBackground(CityWithWeather... cityWithWeathers) {
            List<CityEntity> cityEntities = new ArrayList<>();
            for (CityWithWeather cityWithWeather : cityWithWeathers) {
                cityEntities.add(cityWithWeather.getCity());
            }
            database.cityDao().delete(cityEntities);
            return null;
        }
    }
}
