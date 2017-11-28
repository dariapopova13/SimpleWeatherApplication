package com.daria.weather.simpleweatherapplication.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daria.weather.simpleweatherapplication.R;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.WeatherListEntity;
import com.daria.weather.simpleweatherapplication.ui.base.BaseDaggerFragment;
import com.daria.weather.simpleweatherapplication.utils.DataUtils;
import com.daria.weather.simpleweatherapplication.viewmodel.ViewModelChangeListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Daria Popova on 12.11.17.
 */

public class CurrentWeatherFragment extends BaseDaggerFragment
        implements ViewModelChangeListener<WeatherListEntity> {

    @BindView(R.id.main_date)
    TextView dateView;
    @BindView(R.id.main_weather_current_temp)
    TextView currentWeatherTemp;
    @BindView(R.id.main_weather_temp_units)
    TextView weatherTempUnits;
    @BindView(R.id.main_weather_current_temp_icon)
    ImageView currentWeatherIcon;
    @BindView(R.id.main_weather_current_temp_description)
    TextView currentWeatherDescription;
    @BindView(R.id.main_weather_max_temp)
    TextView weatherMaxTemp;
    @BindView(R.id.main_weather_min_temp)
    TextView weatherMinTemp;
    @BindView(R.id.main_wind_direction)
    TextView windDirection;
    @BindView(R.id.main_weather_wind_speed)
    TextView windSpeed;
    @Inject
    DataUtils dataUtils;
    private Unbinder unbinder;


    @Inject
    public CurrentWeatherFragment() {
    }

    public static CurrentWeatherFragment newInstance() {
        Bundle args = new Bundle();
        CurrentWeatherFragment fragment = new CurrentWeatherFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void updateUI(WeatherListEntity weather) {
        assert weather != null;
        dateView.setText(dataUtils.getDate(weather.getDate()));
        currentWeatherTemp.setText(dataUtils.getCurrentTemp(weather));
        weatherMaxTemp.setText(dataUtils.getTemp(weather.getTemperature().getMaxTemp()));
        weatherMinTemp.setText(dataUtils.getTemp(weather.getTemperature().getMinTemp()));
        windSpeed.setText(String.valueOf((int) weather.getSpeed()));
        windDirection.setText(dataUtils.windDirection(weather.getDeg()));
        currentWeatherDescription.setText(weather.getWeather().getDescription());


        Glide.with(this)
                .load(dataUtils.getIcon(weather.getWeather().getId()))
                .into(currentWeatherIcon);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.current_weather_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
