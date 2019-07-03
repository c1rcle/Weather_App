package com.c1rcle.weatherapp.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.c1rcle.weatherapp.R;
import com.c1rcle.weatherapp.WeatherInfo.WeatherInfoTodayItem;

public class TodayViewHolder
{
    public final WeatherInfoTodayItem item;

    public final TextView city;

    public final ImageView icon;

    public final TextView description;

    public final TextView temp;

    public final TextView pressure;

    public final TextView humidity;

    public final TextView wind;

    public final TextView precip;

    public final TextView refreshStamp;

    public TodayViewHolder(View view, WeatherInfoTodayItem item)
    {
        this.city = view.findViewById(R.id.todayCity);
        this.icon = view.findViewById(R.id.todayIcon);
        this.description = view.findViewById(R.id.todayDescription);
        this.temp = view.findViewById(R.id.todayTemp);
        this.pressure = view.findViewById(R.id.todayPressureValue);
        this.humidity = view.findViewById(R.id.todayHumidityValue);
        this.wind = view.findViewById(R.id.todayWindValue);
        this.precip = view.findViewById(R.id.todayPrecipValue);
        this.refreshStamp = view.findViewById(R.id.todayRefresh);
        this.item = item;
    }

    public void setViews()
    {
        city.setText(item.getCity());
        icon.setImageResource(item.getIcon());
        description.setText(item.getDescription());
        temp.setText(item.getTemp());
        pressure.setText(item.getPressure());
        humidity.setText(item.getHumidity());
        wind.setText(item.getWind());
        precip.setText(item.getPrecip());
        refreshStamp.setText(item.getRefreshStamp());
    }
}
