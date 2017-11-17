package com.daria.weather.simpleweatherapplication.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daria.weather.simpleweatherapplication.R;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.WeatherListEntity;
import com.daria.weather.simpleweatherapplication.ui.view.DateView;
import com.daria.weather.simpleweatherapplication.ui.view.TemperatureView;
import com.daria.weather.simpleweatherapplication.utils.DataUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Daria Popova on 12.11.17.
 */

public class CurrentWeatherFragment extends Fragment {

    @BindView(R.id.main_date)
    DateView dateView;
    @BindView(R.id.main_weather_current_temp)
    TextView currentWeatherTemp;
    @BindView(R.id.main_weather_temp_units)
    TextView weatherTempUnits;
    @BindView(R.id.main_weather_current_temp_icon)
    ImageView currentWeatherIcon;
    @BindView(R.id.main_weather_current_temp_description)
    TextView currentWeatherDescription;
    @BindView(R.id.main_weather_max_temp)
    TemperatureView weatherMaxTemp;
    @BindView(R.id.main_weather_min_temp)
    TemperatureView weatherMinTemp;
    @BindView(R.id.main_wind_direction)
    TextView windDirection;
    @BindView(R.id.main_weather_wind_speed)
    TextView windSpeed;

    public static CurrentWeatherFragment newInstance() {
        Bundle args = new Bundle();
        CurrentWeatherFragment fragment = new CurrentWeatherFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void updateUI(WeatherListEntity weather) {
        dateView.setDate(weather.getDate());
        currentWeatherTemp.setText(String.valueOf(weather.getTemperature().getDayTemp()));
        weatherMaxTemp.setTemp(weather.getTemperature().getMaxTemp());
        weatherMinTemp.setTemp(weather.getTemperature().getMinTemp());
        windSpeed.setText(String.valueOf((int) weather.getSpeed()));
        windDirection.setText(DataUtils.windDirection(weather.getDeg()));
        currentWeatherDescription.setText(weather.getWeather().getDescription());


            Glide.with(this)
                    .load(DataUtils.getIcon(weather.getWeather().getId()))
                    .into(currentWeatherIcon);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.current_weather_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
