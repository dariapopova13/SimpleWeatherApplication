package com.daria.weather.simpleweatherapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

//import com.daria.weather.simpleweatherapplication.ui.fragment.PreferencesFragment;


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
    private CurrentWeatherFragment currentWeatherFragment;
    private WeatherExtraInfoFragment weatherExtraInfoFragment;
    private WeatherListFragment weatherListFragment;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.main_menu_settings: {
                startSettingsActivity();
            }
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void startSettingsActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
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


    private void initUI() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.drawable.ic_location_on_white_24dp);


        currentWeatherFragment = CurrentWeatherFragment.newInstance();
        weatherExtraInfoFragment = WeatherExtraInfoFragment.newInstance();
        weatherListFragment = WeatherListFragment.newInstance();

        fragmentManager.beginTransaction()
                .replace(R.id.main_current_weather_holder, currentWeatherFragment)
                .replace(R.id.weather_list_holder, weatherListFragment)
                .replace(R.id.main_weather_extra_info_holder, weatherExtraInfoFragment)
                .commit();
    }


}
