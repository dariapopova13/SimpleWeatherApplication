package com.daria.weather.simpleweatherapplication.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.daria.weather.simpleweatherapplication.di.activity.BaseActivityModule;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

//import com.daria.weather.simpleweatherapplication.di.BaseComponent;

/**
 * Created by Daria Popova on 17.11.17.
 */

public abstract class BaseActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;
    @Inject
    @Named(BaseActivityModule.ACTIVITY_FRAGMENT_MANAGER)
    protected FragmentManager fragmentManager;

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }


}
