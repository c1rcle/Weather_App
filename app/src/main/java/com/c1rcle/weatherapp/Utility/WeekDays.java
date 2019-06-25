package com.c1rcle.weatherapp.Utility;

import android.content.Context;

import com.c1rcle.weatherapp.R;

public class WeekDays
{
    public static String translateDay(int day, Context context)
    {
        switch (day) {
            case 1:
                return context.getResources().getString(R.string.day_mon);
            case 2:
                return context.getResources().getString(R.string.day_tue);
            case 3:
                return context.getResources().getString(R.string.day_wed);
            case 4:
                return context.getResources().getString(R.string.day_thu);
            case 5:
                return context.getResources().getString(R.string.day_fri);
            case 6:
                return context.getResources().getString(R.string.day_sat);
            default:
                return context.getResources().getString(R.string.day_sun);
        }
    }
}
