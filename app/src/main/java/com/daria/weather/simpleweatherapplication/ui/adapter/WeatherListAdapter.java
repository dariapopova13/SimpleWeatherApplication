package com.daria.weather.simpleweatherapplication.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.daria.weather.simpleweatherapplication.R;
import com.daria.weather.simpleweatherapplication.storage.database.entitiy.WeatherListEntity;
import com.daria.weather.simpleweatherapplication.ui.view.TemperatureView;
import com.daria.weather.simpleweatherapplication.ui.view.WeekdayView;

import java.util.List;

/**
 * Created by Daria Popova on 11.11.17.
 */

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.WeatherViewHolder> {

    private List<WeatherListEntity> weatherList;
    private Context context;

    public WeatherListAdapter(Context context) {
        this.context = context;
    }

    public void setWeatherList(List<WeatherListEntity> weatherList) {
        this.weatherList =  weatherList;
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
        holder.minTemp.setTemp(entity.getTemperature().getMinTemp());
        holder.maxTemp.setTemp(entity.getTemperature().getMaxTemp());
        holder.weekday.setWeekday(entity.getDate());
    }

    @Override
    public int getItemCount() {
        return weatherList == null ? 0 : weatherList.size();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {

        WeekdayView weekday;
        ImageView weatherIcon;
        TemperatureView maxTemp;
        TemperatureView minTemp;

        public WeatherViewHolder(View view) {
            super(view);
            weekday = (WeekdayView) view.findViewById(R.id.weather_recycler_view_weekday);
            weatherIcon = (ImageView) view.findViewById(R.id.weather_recycler_view_icon);
            maxTemp = (TemperatureView) view.findViewById(R.id.weather_recycler_view_max);
            minTemp = (TemperatureView) view.findViewById(R.id.weather_recycler_view_min);
        }
    }
}
