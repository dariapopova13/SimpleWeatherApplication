package com.daria.weather.simpleweatherapplication.di.app;

import android.content.Context;
import android.content.SharedPreferences;

import com.daria.weather.simpleweatherapplication.utils.DataUtils;
import com.daria.weather.simpleweatherapplication.utils.EntityUtils;
import com.daria.weather.simpleweatherapplication.utils.PreferencesUtils;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Daria Popova on 19.11.17.
 */
@Module
public class UtilsModule {


    @Provides
    public DataUtils provideDataUtils(@Named(ApplicationModule.APPLICATION_CONTEXT) Context context) {
        return new DataUtils(context);
    }


    @Provides
    public EntityUtils provideEntityUtils() {
        return new EntityUtils();
    }


    @Provides
    public PreferencesUtils providePreferencesUtils(SharedPreferences sharedPreferences) {
        return new PreferencesUtils(sharedPreferences);
    }


}
