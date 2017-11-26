package com.daria.weather.simpleweatherapplication.di.activity;


import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.daria.weather.simpleweatherapplication.di.scope.ActivityScope;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Daria Popova on 23.11.17.
 */
@Module
public abstract class BaseActivityModule {

    public static final String ACTIVITY_FRAGMENT_MANAGER = "BaseActivityModule.activityFragmentManager";
    public static final String ACTIVITY_CONTEXT = "BaseActivityModule.activityContext";

    @Provides
    @Named(ACTIVITY_FRAGMENT_MANAGER)
    @ActivityScope
    public static FragmentManager provideFragmentManager(AppCompatActivity activity) {
        return activity.getSupportFragmentManager();
    }

    @Binds
    @ActivityScope
    @Named(ACTIVITY_CONTEXT)
    public abstract Context context(AppCompatActivity activity);
}
