package com.daria.weather.simpleweatherapplication.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daria.weather.simpleweatherapplication.R;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.WeatherListEntity;
import com.daria.weather.simpleweatherapplication.utils.DataUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Daria Popova on 11.11.17.
 */

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.WeatherViewHolder> {

    private List<WeatherListEntity> weatherList;
    private Context context;
    private final DataUtils dataUtils;

    public WeatherListAdapter(Context context, DataUtils dataUtils) {
        this.context = context;
        this.dataUtils = dataUtils;
    }

    public void setWeatherList(List<WeatherListEntity> weatherList) {
        this.weatherList = weatherList;
        notifyDataSetChanged();
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_recycler_view_instance, parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        WeatherListEntity entity = weatherList.get(position);
        holder.minTemp.setText(dataUtils.getTemp(entity.getTemperature().getMinTemp()));
        holder.maxTemp.setText(dataUtils.getTemp(entity.getTemperature().getMaxTemp()));
        holder.weekday.setText(dataUtils.getWeekday(entity.getDate()));

        Glide.with(context)
                .load(dataUtils.getIcon(entity.getWeather().getId()))
                .into(holder.weatherIcon);
    }

    @Override
    public int getItemCount() {
        return weatherList == null ? 0 : weatherList.size();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.weather_recycler_view_weekday)
        TextView weekday;
        @BindView(R.id.weather_recycler_view_icon)
        ImageView weatherIcon;
        @BindView(R.id.weather_recycler_view_max)
        TextView maxTemp;
        @BindView(R.id.weather_recycler_view_min)
        TextView minTemp;

        public WeatherViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
