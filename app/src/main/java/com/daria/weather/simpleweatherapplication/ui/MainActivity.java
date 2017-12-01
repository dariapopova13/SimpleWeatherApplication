package com.daria.weather.simpleweatherapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daria.weather.simpleweatherapplication.R;
import com.daria.weather.simpleweatherapplication.location.WeatherLocationManager;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.CityWithWeather;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.WeatherListEntity;
import com.daria.weather.simpleweatherapplication.ui.base.BaseViewModelActivity;
import com.daria.weather.simpleweatherapplication.ui.fragment.CurrentWeatherFragment;
import com.daria.weather.simpleweatherapplication.ui.fragment.WeatherExtraInfoFragment;
import com.daria.weather.simpleweatherapplication.ui.fragment.WeatherListFragment;
import com.daria.weather.simpleweatherapplication.utils.DataUtils;
import com.daria.weather.simpleweatherapplication.utils.PreferencesUtils;
import com.daria.weather.simpleweatherapplication.viewmodel.WeatherViewModel;
import com.daria.weather.simpleweatherapplication.viewmodel.WeatherViewModelFactory;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseViewModelActivity<List<CityWithWeather>>
        implements WeatherLocationManager.Callback, SwipeRefreshLayout.OnRefreshListener{


    private static final String UPDATE_KEY = "update";
    @BindView(R.id.main_background)
    ImageView background;
    @BindView(R.id.main_progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.main_app_bar_location_text_view)
    TextView locationTextView;
    @BindView(R.id.main_app_bar_location_icon)
    ImageView locationIcon;
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
    @Inject
    WeatherLocationManager locationManager;
    @Inject
    PreferencesUtils preferencesUtils;
    private CurrentWeatherFragment currentWeatherFragment;
    private WeatherExtraInfoFragment weatherExtraInfoFragment;
    private WeatherListFragment weatherListFragment;
    private boolean update;
    private Toast toast;

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

    @Override
    public void updateUI(List<CityWithWeather> cityWithWeathers) {
        if (cityWithWeathers != null && cityWithWeathers.size() != 0) {
            CityWithWeather cityWithWeather = cityWithWeathers.get(0);
            WeatherListEntity current = dataUtils.getCurrentWeather(cityWithWeather.getWeatherLists());
            weatherListFragment.updateUI(cityWithWeather.getWeatherLists());
            weatherExtraInfoFragment.updateUI(current);
            currentWeatherFragment.updateUI(current);
            setBackground(current);
            update = false;
        }
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onSuccess() {
        updateFromNet();
        setLocationName();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFuilure() {
        progressBar.setVisibility(View.GONE);
        makeToast(R.string.location_failure_message);
    }

    @Override
    public void onRefresh() {
        updateFromNet();
    }

    private void makeToast(int message) {
        cancelToast();
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    private void cancelToast() {
        if (toast != null && toast.getView().isShown())
            toast.cancel();
    }

    private void startSettingsActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getLocation();
        initUI();
        if (savedInstanceState != null) {
            update = savedInstanceState.getBoolean(UPDATE_KEY);
        }
        if (update) updateFromNet();
    }


    private void setBackground(WeatherListEntity weather) {
        Glide.with(this)
                .load(dataUtils.getBackground(weather.getWeather().getId()))
                .into(background);
    }

    private void initUI() {
        swipeRefreshLayout.setOnRefreshListener(this);
        setSupportActionBar(toolbar);
        locationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLocation();
            }
        });

        setFragments();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(UPDATE_KEY, update);
    }


    private void updateFromNet() {
        update = true;
        model.getCityWithWeather(update);
    }

    private void getLocation() {
        progressBar.setVisibility(View.VISIBLE);
        locationManager.getAddress();
    }

    private void setLocationName() {
        locationTextView.setText(
                preferencesUtils.getFullNameLocation());
        locationTextView.setSelected(true);
    }

    private void setFragments() {
        currentWeatherFragment = CurrentWeatherFragment.newInstance();
        weatherExtraInfoFragment = WeatherExtraInfoFragment.newInstance();
        weatherListFragment = WeatherListFragment.newInstance();

        setLocationName();

        fragmentManager.beginTransaction()
                .replace(R.id.main_current_weather_holder, currentWeatherFragment)
                .replace(R.id.weather_list_holder, weatherListFragment)
                .replace(R.id.main_weather_extra_info_holder, weatherExtraInfoFragment)
                .commit();
    }

}
