package com.daria.weather.simpleweatherapplication.di.app;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.daria.weather.simpleweatherapplication.network.WeatherSynchronizer;
import com.daria.weather.simpleweatherapplication.repository.WeatherDataRepository;
import com.daria.weather.simpleweatherapplication.repository.WeatherDataRepositoryImpl;
import com.daria.weather.simpleweatherapplication.storage.WeatherStorageManager;
import com.daria.weather.simpleweatherapplication.storage.database.WeatherDatabase;
import com.daria.weather.simpleweatherapplication.utils.UrlUtils;
import com.daria.weather.simpleweatherapplication.viewmodel.WeatherViewModelFactory;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Daria Popova on 16.11.17.
 */
@Module
public class ApplicationModule {

    public static final String APPLICATION_CONTEXT = "ApplicationModule.applicationContext";

    @Provides
    @Singleton
    @Named(APPLICATION_CONTEXT)
    public Context provideContext(Application application) {
        return application.getApplicationContext();
    }


    @Provides
    @Singleton
    public WeatherDatabase provideWeatherDatabase(@Named(APPLICATION_CONTEXT) Context context) {
        return Room.databaseBuilder(context,
                WeatherDatabase.class, WeatherDatabase.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences(@Named(APPLICATION_CONTEXT) Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }


    @Provides
    @Singleton
    public WeatherStorageManager provideWeatherStorageManager(WeatherDatabase database) {
        return new WeatherStorageManager(database);
    }


    @Singleton
    @Provides
    public WeatherDataRepository provideWeatherDataRepository(WeatherStorageManager manager,
                                                              WeatherSynchronizer synchronizer,
                                                              @Named(APPLICATION_CONTEXT) Context context,
                                                              UrlUtils urlUtils) {
        return new WeatherDataRepositoryImpl(manager, synchronizer, context, urlUtils);
    }


    @Singleton
    @Provides
    public WeatherViewModelFactory provideWeatherViewModelFactory(Application application,
                                                                  WeatherDataRepository repository) {
        return new WeatherViewModelFactory(application, repository);
    }
}
