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

import com.daria.weather.simpleweatherapplication.R;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.CityWithWeather;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.WeatherListEntity;
import com.daria.weather.simpleweatherapplication.ui.view.DateView;
import com.daria.weather.simpleweatherapplication.ui.view.TemperatureView;

/**
 * Created by Daria Popova on 12.11.17.
 */

public class CurrentWeatherFragment extends Fragment {

    private DateView dateView;
    private TextView currentWeatherTemp;
    private TextView weatherTempUnits;
    private ImageView currentWeatherIcon;
    private TextView currentWeatherDescription;
    private TemperatureView weatherMaxTemp;
    private TemperatureView weatherMinTemp;
    private TextView windDirection;
    private TextView windSpeed;

    public void updateUI(CityWithWeather cityWithWeather) {
        WeatherListEntity weather = cityWithWeather.getWeatherLists().get(0);
        dateView.setDate(weather.getDate());
        currentWeatherTemp.setText(String.valueOf(weather.getTemperature().getDayTemp()));
        weatherMaxTemp.setTemp(weather.getTemperature().getMaxTemp());
        weatherMinTemp.setTemp(weather.getTemperature().getMinTemp());
        windSpeed.setText(String.valueOf(weather.getSpeed()));
        windDirection.setText(String.valueOf(weather.getDeg()));
        currentWeatherDescription.setText(weather.getWeather().getDescription());
    }

    public static CurrentWeatherFragment newInstance() {

        Bundle args = new Bundle();

        CurrentWeatherFragment fragment = new CurrentWeatherFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.current_weather_fragment, container, false);

        initUI(view);
        return view;
    }


    private void initUI(View view) {
        dateView = (DateView) view.findViewById(R.id.main_date);
        currentWeatherTemp = (TextView) view.findViewById(R.id.main_weather_current_temp);
        weatherTempUnits = (TextView) view.findViewById(R.id.main_weather_temp_units);
        currentWeatherIcon = (ImageView) view.findViewById(R.id.main_weather_current_temp_icon);
        currentWeatherDescription = (TextView) view.findViewById(R.id.main_weather_current_temp_description);
        weatherMaxTemp = (TemperatureView) view.findViewById(R.id.main_weather_max_temp);
        weatherMinTemp = (TemperatureView) view.findViewById(R.id.main_weather_min_temp);
        windDirection = (TextView) view.findViewById(R.id.main_wind_direction);
        windSpeed = (TextView) view.findViewById(R.id.main_weather_wind_speed);
    }
}
