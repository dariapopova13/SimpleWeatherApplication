package com.daria.weather.simpleweatherapplication.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daria.weather.simpleweatherapplication.R;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.CityWithWeather;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.WeatherListEntity;
import com.daria.weather.simpleweatherapplication.ui.base.BaseViewModelActivity;
import com.daria.weather.simpleweatherapplication.ui.fragment.CurrentWeatherFragment;
import com.daria.weather.simpleweatherapplication.ui.fragment.WeatherExtraInfoFragment;
import com.daria.weather.simpleweatherapplication.ui.fragment.WeatherListFragment;
import com.daria.weather.simpleweatherapplication.utils.DataUtils;
import com.daria.weather.simpleweatherapplication.viewmodel.WeatherViewModel;
import com.daria.weather.simpleweatherapplication.viewmodel.WeatherViewModelFactory;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseViewModelActivity<List<CityWithWeather>> {


    @BindView(R.id.main_background)
    ImageView background;
    @BindView(R.id.main_app_bar_location_text_view)
    TextView location;
    @BindView(R.id.location_detecting)
    TextView locationToast;
    @BindView(R.id.main_app_bar_toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_drawer)
    DrawerLayout drawerLayout;
    @BindView(R.id.main_swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Inject
    WeatherViewModelFactory weatherViewModelFactory;
    @Inject
    WeatherViewModel model;
    @Inject
    DataUtils dataUtils;
    //    private ActionBarDrawerToggle drawerToggle;
//    private WeatherLocationManager weatherLocationManager;
    private CurrentWeatherFragment currentWeatherFragment;
    private WeatherExtraInfoFragment weatherExtraInfoFragment;
    private WeatherListFragment weatherListFragment;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
//        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void updateUI(List<CityWithWeather> cityWithWeathers) {
        if (cityWithWeathers != null && cityWithWeathers.size() != 0) {
            CityWithWeather cityWithWeather = cityWithWeathers.get(0);
            WeatherListEntity current = dataUtils.getCurrentWeather(cityWithWeather.getWeatherLists());
            weatherListFragment.updateUI(cityWithWeather.getWeatherLists());
            weatherExtraInfoFragment.updateUI(current);
            currentWeatherFragment.updateUI(current);
            setBackground(current);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }


    private void setBackground(WeatherListEntity weather) {
        Glide.with(this)
                .load(dataUtils.getBackground(weather.getWeather().getId()))
                .into(background);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
//        drawerToggle.syncState();
    }

    private void initUI() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

//        updateLocationText();


//        drawerToggle = new ActionBarDrawerToggle(
//                this,
//                drawerLayout,
//                R.string.open_drawer,
//                R.string.close_drawer) {


//            public void onDrawerClosed(View view) {
//                super.onDrawerClosed(view);
//                getSupportActionBar().setTitle("");
//            }
//
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//                getSupportActionBar().setTitle("");
//            }
//        };

//        drawerLayout.addDrawerListener(drawerToggle);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);

//        swipeRefreshLayout.setOnRefreshListener(this);
        currentWeatherFragment = CurrentWeatherFragment.newInstance();
        weatherExtraInfoFragment = WeatherExtraInfoFragment.newInstance();
        weatherListFragment = WeatherListFragment.newInstance();

        fragmentManager.beginTransaction()
                .replace(R.id.main_current_weather_holder, currentWeatherFragment)
                .replace(R.id.weather_list_holder, weatherListFragment)
                .replace(R.id.main_weather_extra_info_holder, weatherExtraInfoFragment)
                .commit();
    }

//    private void updateLocationText() {
//        location.setText(PreferencesUtils.getCity(this));
//    }

}
