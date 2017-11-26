package com.daria.weather.simpleweatherapplication.storage;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.daria.weather.simpleweatherapplication.storage.database.WeatherDatabase;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.CityWithWeather;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Daria Popova on 09.11.17.
 */

public class WeatherStorageManager {

    private WeatherDatabase database;

    public WeatherStorageManager(WeatherDatabase database) {
        this.database = database;
    }

    public void store(CityWithWeather cityWithWeathers) {
        new InsertAsyncTask(database).execute(cityWithWeathers);
    }

    public LiveData<List<CityWithWeather>> getAll() {
        try {
            return new GetAsyncTask(database).execute().get();
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
        }
        return null;
    }

    private static class GetAsyncTask extends AsyncTask<Void, Void, LiveData<List<CityWithWeather>>> {

        private WeatherDatabase database;

        public GetAsyncTask(WeatherDatabase database) {
            this.database = database;
        }

        @Override
        protected LiveData<List<CityWithWeather>> doInBackground(Void... voids) {
            return database.cityDao().getAll();
        }
    }


    private static class InsertAsyncTask extends AsyncTask<CityWithWeather, Void, Void> {

        private WeatherDatabase database;

        public InsertAsyncTask(WeatherDatabase database) {
            this.database = database;
        }

        @Override
        protected Void doInBackground(CityWithWeather... cityWithWeathers) {
            database.insertCityWithWeather(cityWithWeathers[0]);
            return null;
        }
    }

}
