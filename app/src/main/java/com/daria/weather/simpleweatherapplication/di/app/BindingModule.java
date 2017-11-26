package com.daria.weather.simpleweatherapplication.di.app;

import com.daria.weather.simpleweatherapplication.di.activity.main.MainActivityModule;
import com.daria.weather.simpleweatherapplication.di.scope.ActivityScope;
import com.daria.weather.simpleweatherapplication.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Daria Popova on 23.11.17.
 */
@Module
public interface BindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    MainActivity mainActivity();

}
