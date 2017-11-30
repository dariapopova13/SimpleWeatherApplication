package com.daria.weather.simpleweatherapplication.di.activity.settings;

import android.support.v7.app.AppCompatActivity;

import com.daria.weather.simpleweatherapplication.di.activity.BaseActivityModule;
import com.daria.weather.simpleweatherapplication.di.scope.ActivityScope;
import com.daria.weather.simpleweatherapplication.ui.SettingsActivity;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Daria Popova on 01.12.17.
 */
@Module(includes = {
        BaseActivityModule.class,
        SettingsBindingModule.class
})
public abstract class SettingsActivityModule {

    @Binds
    @ActivityScope
    public abstract AppCompatActivity activity(SettingsActivity settingsActivity);
}
