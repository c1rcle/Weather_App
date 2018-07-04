package com.c1rcle.weatherapp.Utility;

import android.content.Context;

import com.c1rcle.weatherapp.R;

public class WeekDays
{
    public static String translateDay(String day, Context context)
    {
        switch (day) {
            case "Mon":
                return context.getResources().getString(R.string.day_mon);
            case "Tue":
                return context.getResources().getString(R.string.day_tue);
            case "Wed":
                return context.getResources().getString(R.string.day_wed);
            case "Thu":
                return context.getResources().getString(R.string.day_thu);
            case "Fri":
                return context.getResources().getString(R.string.day_fri);
            case "Sat":
                return context.getResources().getString(R.string.day_sat);
            default:
                return context.getResources().getString(R.string.day_sun);
        }
    }
}
