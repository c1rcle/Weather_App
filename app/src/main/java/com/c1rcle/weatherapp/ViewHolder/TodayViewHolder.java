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

    public final TextView refreshStamp;

    public TodayViewHolder(View view, WeatherInfoTodayItem item)
    {
        this.city = view.findViewById(R.id.todayCity);
        this.icon = view.findViewById(R.id.todayIcon);
        this.description = view.findViewById(R.id.todayDescription);
        this.temp = view.findViewById(R.id.todayTemp);
        this.refreshStamp = view.findViewById(R.id.todayRefresh);
        this.item = item;
    }

    public void setViews()
    {
        city.setText(item.getCity());
        icon.setImageResource(item.getIcon());
        description.setText(item.getDescription());
        temp.setText(item.getTemp());
        refreshStamp.setText(item.getRefreshStamp());
    }
}
