package com.daria.weather.simpleweatherapplication.di;

import android.app.Application;

import com.daria.weather.simpleweatherapplication.WeatherApplication;
import com.daria.weather.simpleweatherapplication.di.app.ApplicationModule;
import com.daria.weather.simpleweatherapplication.di.app.BindingModule;
import com.daria.weather.simpleweatherapplication.di.app.NetWorkModule;
import com.daria.weather.simpleweatherapplication.di.app.UtilsModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Daria Popova on 17.11.17.
 */
@Singleton
@Component(modules = {
        ApplicationModule.class,
        UtilsModule.class,
        AndroidSupportInjectionModule.class,
        BindingModule.class,
        NetWorkModule.class
})
public interface AppComponent extends AndroidInjector<WeatherApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        @BindsInstance
        Builder baseUrl(String baseUrl);

        AppComponent build();
    }

    void inject(WeatherApplication application);
}
