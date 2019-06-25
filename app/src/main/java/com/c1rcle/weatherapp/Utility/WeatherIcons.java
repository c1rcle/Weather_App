package com.c1rcle.weatherapp.Utility;

import android.content.Context;

import com.c1rcle.weatherapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeatherIcons
{
    public static int translateCode(String code)
    {
        switch (code)
        {
            case "clear-day": return R.drawable.icon_wi_day_sunny;
            case "clear-night": return R.drawable.icon_wi_night_clear;
            case "rain": return R.drawable.icon_wi_showers;
            case "snow": return R.drawable.icon_wi_snow;
            case "sleet": return R.drawable.icon_wi_rain_mix;
            case "wind": return R.drawable.icon_wi_cloudy_windy;
            case "fog": return R.drawable.icon_wi_fog;
            case "cloudy": return R.drawable.icon_wi_cloud;
            case "partly-cloudy-day": return R.drawable.icon_wi_day_sunny_overcast;
            case "partly-cloudy-night": return R.drawable.icon_wi_night_cloudy;
            default: return -1;
        }
    }

    public static String translateDescription(String code, Context context)
    {
        switch (code)
        {
            case "clear-day": return context.getString(R.string.condition_31);
            case "clear-night": return context.getString(R.string.condition_31);
            case "rain": return context.getString(R.string.condition_rain);
            case "snow": return context.getString(R.string.condition_16);
            case "sleet": return context.getString(R.string.condition_18);
            case "wind": return context.getString(R.string.condition_24);
            case "fog": return context.getString(R.string.condition_20);
            case "cloudy": return context.getString(R.string.condition_26);
            case "partly-cloudy-day": return context.getString(R.string.condition_29_30_44);
            case "partly-cloudy-night": return context.getString(R.string.condition_29_30_44);
            default: return context.getString(R.string.condition_3200);
        }
    }
}
