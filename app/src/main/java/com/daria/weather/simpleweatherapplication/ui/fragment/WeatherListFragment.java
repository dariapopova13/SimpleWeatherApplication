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
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.WeatherListEntity;
import com.daria.weather.simpleweatherapplication.ui.adapter.WeatherListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Daria Popova on 12.11.17.
 */

public class WeatherListFragment extends Fragment {

    @BindView(R.id.main_week_weather_recycler_view)
    RecyclerView weatherRecycleView;
    WeatherListAdapter adapter;


    public void updateUI(List<WeatherListEntity> weatherListEntities) {
        adapter.setWeatherList(weatherListEntities);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_list_fragment, container, false);
        ButterKnife.bind(this, view);
        initRecyclerView(view);
        return view;
    }

    public static WeatherListFragment newInstance() {

        Bundle args = new Bundle();

        WeatherListFragment fragment = new WeatherListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initRecyclerView(View view) {
        adapter = new WeatherListAdapter(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());

        weatherRecycleView.setAdapter(adapter);
        weatherRecycleView.setLayoutManager(manager);
    }


}
