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
import com.daria.weather.simpleweatherapplication.ui.base.BaseFragment;
import com.daria.weather.simpleweatherapplication.utils.DataUtils;
import com.daria.weather.simpleweatherapplication.viewmodel.ViewModelChangeListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Daria Popova on 12.11.17.
 */

public class WeatherExtraInfoFragment extends BaseFragment
        implements ViewModelChangeListener<WeatherListEntity> {

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
    @Inject
    DataUtils dataUtils;
    private Unbinder unbinder;

    @Inject
    public WeatherExtraInfoFragment() {
    }

    public static WeatherExtraInfoFragment newInstance() {

        Bundle args = new Bundle();

        WeatherExtraInfoFragment fragment = new WeatherExtraInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public void updateUI(WeatherListEntity weather) {
        assert weather != null;
        humidity.setText(String.valueOf(weather.getHumidity()));
        precipitation.setText(String.valueOf(weather.getRain()));
        cloudCover.setText(String.valueOf(weather.getClouds()));
        pressure.setText(String.valueOf(weather.getPressure()));
        Glide.with(this)
                .load(dataUtils.getIcon(weather.getWeather().getId()))
                .into(currentWeatherIcon);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_extra_info_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


}
