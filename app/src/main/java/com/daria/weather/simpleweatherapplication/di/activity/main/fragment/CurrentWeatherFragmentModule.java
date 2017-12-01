package com.daria.weather.simpleweatherapplication.di.activity.main.fragment;

import android.support.v4.app.Fragment;

import com.daria.weather.simpleweatherapplication.di.scope.FragmentScope;
import com.daria.weather.simpleweatherapplication.ui.fragment.CurrentWeatherFragment;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Daria Popova on 24.11.17.
 */
@Module
public abstract class CurrentWeatherFragmentModule {

    @Binds
    @FragmentScope
    public abstract Fragment fragment(CurrentWeatherFragment fragment);

}
