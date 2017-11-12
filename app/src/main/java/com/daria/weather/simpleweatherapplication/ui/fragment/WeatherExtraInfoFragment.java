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
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.CityWithWeather;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.WeatherListEntity;
import com.daria.weather.simpleweatherapplication.utils.DataUtils;

/**
 * Created by Daria Popova on 12.11.17.
 */

public class WeatherExtraInfoFragment extends Fragment {

    private TextView humidity;
    private TextView precipitation;
    //    private TextView windChill;
//    private TextView sunrise;
//    private TextView dewPoint;
    private TextView cloudCover;
    private TextView pressure;
    private ImageView currentWeatherIcon;

    //    private TextView sunset;
    public void updateUI(CityWithWeather cityWithWeather) {
        WeatherListEntity weather = cityWithWeather.getWeatherLists().get(0);
        humidity.setText(String.valueOf(weather.getHumidity()));
        precipitation.setText(String.valueOf(weather.getRain()));
        cloudCover.setText(String.valueOf(weather.getClouds()));
        pressure.setText(String.valueOf(weather.getPressure()));
        if (getContext() != null)
            Glide.with(getContext())
                    .load(DataUtils.getIcon(weather.getWeather().getId()))
                    .into(currentWeatherIcon);
    }

    public static WeatherExtraInfoFragment newInstance() {

        Bundle args = new Bundle();

        WeatherExtraInfoFragment fragment = new WeatherExtraInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_extra_info_fragment, container, false);
        initUI(view);
        return view;
    }

    private void initUI(View view) {
        humidity = (TextView) view.findViewById(R.id.detailed_info_table_humidity);
        precipitation = (TextView) view.findViewById(R.id.detailed_info_table_precipitation);
//        windChill = (TextView) view.findViewById(R.id.detailed_info_table_wind_chill);
//        sunrise = (TextView) view.findViewById(R.id.detailed_info_table_sunrise);
//        dewPoint = (TextView) view.findViewById(R.id.detailed_info_table_dew_point);
        cloudCover = (TextView) view.findViewById(R.id.detailed_info_table_cloud_cover);
        pressure = (TextView) view.findViewById(R.id.detailed_info_table_pressure);
//        sunset = (TextView) view.findViewById(R.id.detailed_info_table_sunset);
        currentWeatherIcon = (ImageView) view.findViewById(R.id.detailed_info_current_weather_icon);
    }


}
