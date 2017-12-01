package com.daria.weather.simpleweatherapplication.di.activity.main.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;

import com.daria.weather.simpleweatherapplication.di.activity.BaseActivityModule;
import com.daria.weather.simpleweatherapplication.di.scope.FragmentScope;
import com.daria.weather.simpleweatherapplication.ui.adapter.WeatherListAdapter;
import com.daria.weather.simpleweatherapplication.ui.fragment.WeatherListFragment;
import com.daria.weather.simpleweatherapplication.utils.DataUtils;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Daria Popova on 24.11.17.
 */
@Module()
public abstract class WeatherListFragmentModule {

    @FragmentScope
    @Provides
    public static WeatherListAdapter provideWeatherListAdapter(
            @Named(BaseActivityModule.ACTIVITY_CONTEXT) Context context,
            DataUtils dataUtils) {
        return new WeatherListAdapter(context, dataUtils);
    }

    @FragmentScope
    @Provides
    public static LinearLayoutManager provideLinearLayoutManager(
            @Named(BaseActivityModule.ACTIVITY_CONTEXT) Context context) {
        return new LinearLayoutManager(context);
    }

    @Binds
    @FragmentScope
    public abstract Fragment fragment(WeatherListFragment fragment);
}
