package com.daria.weather.simpleweatherapplication.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daria.weather.simpleweatherapplication.R;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.CityWithWeather;
import com.daria.weather.simpleweatherapplication.ui.adapter.WeatherListAdapter;

/**
 * Created by Daria Popova on 12.11.17.
 */

public class WeatherListFragment extends Fragment {

    private RecyclerView weatherRecycleView;
    private WeatherListAdapter adapter;


    public void updateUI(CityWithWeather cityWithWeather) {
        adapter.setWeatherList(cityWithWeather.getWeatherLists());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_list_fragment, container, false);
        initUI(view);
        return view;
    }

    public static WeatherListFragment newInstance() {

        Bundle args = new Bundle();

        WeatherListFragment fragment = new WeatherListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initUI(View view) {
        weatherRecycleView = (RecyclerView) view.findViewById(R.id.main_week_weather_recycler_view);
        adapter = new WeatherListAdapter(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());

        weatherRecycleView.setAdapter(adapter);
        weatherRecycleView.setLayoutManager(manager);
    }


}
