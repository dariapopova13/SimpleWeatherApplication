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
import com.daria.weather.simpleweatherapplication.utils.DataUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Daria Popova on 12.11.17.
 */

public class WeatherExtraInfoFragment extends Fragment {

    @BindView(R.id.detailed_info_table_humidity)
    TextView humidity;
    @BindView(R.id.detailed_info_table_precipitation)
    TextView precipitation;
    @BindView(R.id.detailed_info_table_cloud_cover)
    TextView cloudCover;
    @BindView(R.id.detailed_info_table_pressure)
    TextView pressure;
    @BindView(R.id.detailed_info_current_weather_icon)
    ImageView currentWeatherIcon;


    public void updateUI(WeatherListEntity weather) {
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
        ButterKnife.bind(this, view);
        return view;
    }


}
