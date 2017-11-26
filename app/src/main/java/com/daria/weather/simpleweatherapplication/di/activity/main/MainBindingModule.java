package com.daria.weather.simpleweatherapplication.di.activity.main;

import com.daria.weather.simpleweatherapplication.di.scope.FragmentScope;
import com.daria.weather.simpleweatherapplication.ui.fragment.CurrentWeatherFragment;
import com.daria.weather.simpleweatherapplication.ui.fragment.WeatherExtraInfoFragment;
import com.daria.weather.simpleweatherapplication.ui.fragment.WeatherListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Daria Popova on 24.11.17.
 */
@Module
public abstract class MainBindingModule {

    @FragmentScope
    @ContributesAndroidInjector
    public abstract CurrentWeatherFragment currentWeatherFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = WeatherListFragmentModule.class)
    public abstract WeatherListFragment weatherListFragment();

    @FragmentScope
    @ContributesAndroidInjector
    public abstract WeatherExtraInfoFragment weatherExtraInfoFragment();
}
