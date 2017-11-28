//package com.daria.weather.simpleweatherapplication.ui.fragment;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.support.v7.preference.PreferenceFragmentCompat;
//
//import com.daria.weather.simpleweatherapplication.R;
//import com.daria.weather.simpleweatherapplication.di.activity.BaseActivityModule;
//
//import javax.inject.Inject;
//import javax.inject.Named;
//
//import dagger.android.support.AndroidSupportInjection;
//
///**
// * Created by Daria Popova on 26.11.17.
// */
//
//public class AppPreferancesFragment extends PreferenceFragmentCompat
//        implements SharedPreferences.OnSharedPreferenceChangeListener {
//
//    public static AppPreferancesFragment newInstance() {
//
//        Bundle args = new Bundle();
//
//        AppPreferancesFragment fragment = new AppPreferancesFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//
//    @Inject
//    @Named(BaseActivityModule.ACTIVITY_CONTEXT)
//    protected Context context;
//    @Inject
//    SharedPreferences sharedPreferences;
//
//    @Override
//    public void onAttach(Context context) {
//        AndroidSupportInjection.inject(this);
//        super.onAttach(context);
//    }
//
//
//    @Override
//    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
//        setPreferencesFromResource(R.xml.weather_app_preferences, rootKey);
//        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
//    }
//
//    @Override
//    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
////       Prefe findPreference(key);
//    }
//}
