package com.daria.weather.simpleweatherapplication.di.activity.settings;

import com.daria.weather.simpleweatherapplication.di.scope.FragmentScope;
import com.daria.weather.simpleweatherapplication.ui.fragment.PreferencesFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Daria Popova on 01.12.17.
 */
@Module
public abstract class SettingsBindingModule {


    @FragmentScope
    @ContributesAndroidInjector
    abstract PreferencesFragment preferencesFragment();
}
