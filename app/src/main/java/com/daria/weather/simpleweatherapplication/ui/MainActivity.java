package com.daria.weather.simpleweatherapplication.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daria.weather.simpleweatherapplication.R;
import com.daria.weather.simpleweatherapplication.location.WeatherLocationManager;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.CityWithWeather;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.WeatherListEntity;
import com.daria.weather.simpleweatherapplication.ui.fragment.CurrentWeatherFragment;
import com.daria.weather.simpleweatherapplication.ui.fragment.WeatherExtraInfoFragment;
import com.daria.weather.simpleweatherapplication.ui.fragment.WeatherListFragment;
import com.daria.weather.simpleweatherapplication.utils.DataUtils;
import com.daria.weather.simpleweatherapplication.utils.PreferencesUtils;
import com.daria.weather.simpleweatherapplication.utils.UrlUtils;
import com.daria.weather.simpleweatherapplication.viewmodel.CityWithWeatherViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity
        implements SwipeRefreshLayout.OnRefreshListener {

    public static final String UPDATED_KEY = "updated";
    private CityWithWeatherViewModel model;
    @BindView(R.id.main_background)
    ImageView background;
    @BindView(R.id.main_app_bar_location_text_view)
    TextView location;
    @BindView(R.id.location_detecting)
    TextView locationToast;
    private CurrentWeatherFragment currentWeatherFragment;
    private WeatherExtraInfoFragment weatherExtraInfoFragment;
    private WeatherListFragment weatherListFragment;
    @BindView(R.id.main_app_bar_toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_drawer)
    DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    @BindView(R.id.main_swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    private boolean isUpdated;
    private WeatherLocationManager weatherLocationManager;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.main_menu_location_detection: {
                cancelToast();
                showToast();
                return true;
            }
        }

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onRefresh() {
        weatherLocationManager.getAddress();
        model.loadDataFromNetwork();
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    private void showToast() {
//        locationToast.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weatherLocationManager = new WeatherLocationManager(this);
        if (savedInstanceState != null)
            isUpdated = savedInstanceState.getBoolean(UPDATED_KEY);
        setContentView(R.layout.activity_main);
        initUI();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(UPDATED_KEY, isUpdated);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        model = ViewModelProviders.of(this)
                .get(CityWithWeatherViewModel.class);
        model.setCurrentUrl(UrlUtils.getUrlFromPreferences(this));
        final Observer<List<CityWithWeather>> observer = new Observer<List<CityWithWeather>>() {
            @Override
            public void onChanged(@Nullable List<CityWithWeather> cityWithWeathers) {
                updateUI(cityWithWeathers);
            }
        };
        isUpdated = false;
        model.getCityWithWeather(isUpdated).observe(this, observer);
    }

    private void updateUI(List<CityWithWeather> cityWithWeathers) {
        swipeRefreshLayout.setRefreshing(false);
        if (cityWithWeathers.size() == 0) return;

        CityWithWeather cityWithWeather = cityWithWeathers.get(0);
        WeatherListEntity current = DataUtils.getCurrentWeather(cityWithWeather.getWeatherLists());
        // FIXME: 12.11.17 add pager with tabs
        if (current != null) {
            setBackground(current);
            currentWeatherFragment.updateUI(current);
            weatherListFragment.updateUI(cityWithWeather.getWeatherLists());
            weatherExtraInfoFragment.updateUI(current);
        }
    }

    private void cancelToast() {
//        locationToast.setVisibility(View.GONE);
    }

    private void setBackground(WeatherListEntity weather) {
        Glide.with(this)
                .load(DataUtils.getBackground(weather.getWeather().getId()))
                .into(background);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    private void initUI() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        updateLocationText();


        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.open_drawer,
                R.string.close_drawer) {


            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle("");
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("");
            }
        };

        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        swipeRefreshLayout.setOnRefreshListener(this);

        weatherExtraInfoFragment = WeatherExtraInfoFragment.newInstance();
        currentWeatherFragment = CurrentWeatherFragment.newInstance();
        weatherListFragment = WeatherListFragment.newInstance();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_current_weather_holder, currentWeatherFragment)
                .replace(R.id.weather_list_holder, weatherListFragment)
                .replace(R.id.main_weather_extra_info_holder, weatherExtraInfoFragment)
                .commit();
    }

    private void updateLocationText() {
        location.setText(PreferencesUtils.getCity(this));
    }

}
