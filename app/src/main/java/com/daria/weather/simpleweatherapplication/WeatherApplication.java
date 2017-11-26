package com.daria.weather.simpleweatherapplication;

import android.app.Activity;
import android.app.Application;

import com.daria.weather.simpleweatherapplication.di.DaggerAppComponent;
import com.daria.weather.simpleweatherapplication.utils.UrlUtils;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by Daria Popova on 17.11.17.
 */

public class WeatherApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent.builder()
                .application(this)
                .baseUrl(UrlUtils.WEATHER_BASE_URL)
                .build()
                .inject(this);
    }


    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
