package com.daria.weather.simpleweatherapplication.di.activity.main;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.daria.weather.simpleweatherapplication.di.activity.BaseActivityModule;
import com.daria.weather.simpleweatherapplication.di.scope.ActivityScope;
import com.daria.weather.simpleweatherapplication.location.WeatherLocationManager;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.CityWithWeather;
import com.daria.weather.simpleweatherapplication.ui.MainActivity;
import com.daria.weather.simpleweatherapplication.ui.base.BaseViewModelActivity;
import com.daria.weather.simpleweatherapplication.utils.PreferencesUtils;
import com.daria.weather.simpleweatherapplication.viewmodel.WeatherViewModel;
import com.daria.weather.simpleweatherapplication.viewmodel.WeatherViewModelFactory;
import com.google.android.gms.location.FusedLocationProviderClient;

import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;


/**
 * Created by Daria Popova on 23.11.17.
 */
@Module(includes = {
        BaseActivityModule.class,
        MainBindingModule.class
})
public abstract class MainActivityModule {


    @ActivityScope
    @Provides
    public static WeatherViewModel provideViewModel(WeatherViewModelFactory factory,
                                                    Observer<List<CityWithWeather>> observer,
                                                    BaseViewModelActivity activity) {
        WeatherViewModel model = factory.create(WeatherViewModel.class);
        model.getCityWithWeather(true).observe(activity, observer);
        return model;
    }

    @ActivityScope
    @Provides
    public static WeatherLocationManager locationManager(Activity activity,
                                                         FusedLocationProviderClient client,
                                                         PreferencesUtils preferencesUtils) {
        return new WeatherLocationManager(activity, client, preferencesUtils);
    }

    @ActivityScope
    @Provides
    public static FusedLocationProviderClient fusedLocationProviderClient(Activity activity) {
        return new FusedLocationProviderClient(activity);
    }

    @Provides
    @ActivityScope
    public static Observer<List<CityWithWeather>> provideObserver(final BaseViewModelActivity activity) {
        Observer<List<CityWithWeather>> observer = new Observer<List<CityWithWeather>>() {
            @Override
            public void onChanged(@Nullable List<CityWithWeather> cityWithWeathers) {
                activity.updateUI(cityWithWeathers);
            }
        };
        return observer;
    }

    @Binds
    @ActivityScope
    public abstract Activity activity(MainActivity mainActivity);

    @Binds
    @ActivityScope
    public abstract AppCompatActivity appCompatActivity(MainActivity mainActivity);

    @Binds
    @ActivityScope
    public abstract BaseViewModelActivity viewModelActivity(MainActivity mainActivity);
}
