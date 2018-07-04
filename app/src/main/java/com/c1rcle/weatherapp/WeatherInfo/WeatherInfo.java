package com.c1rcle.weatherapp.WeatherInfo;

import java.util.ArrayList;
import java.util.List;

public class WeatherInfo
{
    public static WeatherInfoTodayItem TODAY_ITEM;

    public static List<WeatherInfoForecastItem> FORECAST_ITEMS = new ArrayList<>();

    public static void addItem(WeatherInfoForecastItem item)
    {
        FORECAST_ITEMS.add(item);
    }

    public static void clearList()
    {
        FORECAST_ITEMS.clear();
    }
}
