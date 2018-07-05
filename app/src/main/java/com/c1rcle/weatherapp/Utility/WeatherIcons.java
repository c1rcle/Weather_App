package com.c1rcle.weatherapp.Utility;

import android.content.Context;

import com.c1rcle.weatherapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeatherIcons
{
    public static int translateCode(int code)
    {
        switch (code)
        {
            case 0: return R.drawable.icon_wi_tornado;
            case 1: return R.drawable.icon_wi_tornado;
            case 2: return R.drawable.icon_wi_tornado;
            case 3: return R.drawable.icon_wi_thunderstorm;
            case 4: return R.drawable.icon_wi_thunderstorm;
            case 5: return R.drawable.icon_wi_rain_mix;
            case 6: return R.drawable.icon_wi_rain_mix;
            case 7: return R.drawable.icon_wi_rain_mix;
            case 8: return R.drawable.icon_wi_hail;
            case 9: return R.drawable.icon_wi_sprinkle;
            case 10: return R.drawable.icon_wi_hail;
            case 11: return R.drawable.icon_wi_showers;
            case 12: return R.drawable.icon_wi_showers;
            case 13: return R.drawable.icon_wi_snow;
            case 14: return R.drawable.icon_wi_snow;
            case 15: return R.drawable.icon_wi_snow;
            case 16: return R.drawable.icon_wi_snow;
            case 17: return R.drawable.icon_wi_hail;
            case 18: return R.drawable.icon_wi_hail;
            case 19: return R.drawable.icon_wi_fog;
            case 20: return R.drawable.icon_wi_fog;
            case 21: return R.drawable.icon_wi_fog;
            case 22: return R.drawable.icon_wi_fog;
            case 23: return R.drawable.icon_wi_cloudy_gusts;
            case 24: return R.drawable.icon_wi_cloudy_windy;
            case 25: return R.drawable.icon_wi_thermometer_exterior;
            case 26: return R.drawable.icon_wi_cloudy;
            case 27: return R.drawable.icon_wi_night_cloudy;
            case 28: return R.drawable.icon_wi_day_cloudy;
            case 29: return R.drawable.icon_wi_night_cloudy;
            case 30: return R.drawable.icon_wi_day_cloudy;
            case 31: return R.drawable.icon_wi_night_clear;
            case 32: return R.drawable.icon_wi_day_sunny;
            case 33: return R.drawable.icon_wi_night_clear;
            case 34: return R.drawable.icon_wi_day_sunny_overcast;
            case 35: return R.drawable.icon_wi_rain_mix;
            case 36: return R.drawable.icon_wi_day_sunny;
            case 37: return R.drawable.icon_wi_thunderstorm;
            case 38: return R.drawable.icon_wi_thunderstorm;
            case 39: return R.drawable.icon_wi_thunderstorm;
            case 40: return R.drawable.icon_wi_thunderstorm;
            case 41: return R.drawable.icon_wi_snow;
            case 42: return R.drawable.icon_wi_snow;
            case 43: return R.drawable.icon_wi_snow;
            case 44: return R.drawable.icon_wi_day_cloudy;
            case 45: return R.drawable.icon_wi_storm_showers;
            case 46: return R.drawable.icon_wi_snow;
            case 47: return R.drawable.icon_wi_thunderstorm;
            case 3200: return R.drawable.icon_wi_cloud;
            default: return -1;
        }
    }

    public static String translateDescription(int code, Context context)
    {
        switch (code)
        {
            case 0: return context.getString(R.string.condition_0);
            case 1: return context.getString(R.string.condition_1);
            case 2: return context.getString(R.string.condition_2);
            case 3: return context.getString(R.string.condition_3);
            case 4: return context.getString(R.string.condition_4);
            case 5: return context.getString(R.string.condition_5);
            case 6: return context.getString(R.string.condition_6);
            case 7: return context.getString(R.string.condition_7);
            case 8: return context.getString(R.string.condition_8);
            case 9: return context.getString(R.string.condition_9);
            case 10: return context.getString(R.string.condition_10);
            case 11: return context.getString(R.string.condition_11_12);
            case 12: return context.getString(R.string.condition_11_12);
            case 13: return context.getString(R.string.condition_13);
            case 14: return context.getString(R.string.condition_14);
            case 15: return context.getString(R.string.condition_15);
            case 16: return context.getString(R.string.condition_16);
            case 17: return context.getString(R.string.condition_17);
            case 18: return context.getString(R.string.condition_18);
            case 19: return context.getString(R.string.condition_19);
            case 20: return context.getString(R.string.condition_20);
            case 21: return context.getString(R.string.condition_21);
            case 22: return context.getString(R.string.condition_22);
            case 23: return context.getString(R.string.condition_23);
            case 24: return context.getString(R.string.condition_24);
            case 25: return context.getString(R.string.condition_25);
            case 26: return context.getString(R.string.condition_26);
            case 27: return context.getString(R.string.condition_27_28);
            case 28: return context.getString(R.string.condition_27_28);
            case 29: return context.getString(R.string.condition_29_30_44);
            case 30: return context.getString(R.string.condition_29_30_44);
            case 31: return context.getString(R.string.condition_31);
            case 32: return context.getString(R.string.condition_32);
            case 33: return context.getString(R.string.condition_33_34);
            case 34: return context.getString(R.string.condition_33_34);
            case 35: return context.getString(R.string.condition_35);
            case 36: return context.getString(R.string.condition_36);
            case 37: return context.getString(R.string.condition_37);
            case 38: return context.getString(R.string.condition_38_39);
            case 39: return context.getString(R.string.condition_38_39);
            case 40: return context.getString(R.string.condition_40);
            case 41: return context.getString(R.string.condition_41_43);
            case 42: return context.getString(R.string.condition_42);
            case 43: return context.getString(R.string.condition_41_43);
            case 44: return context.getString(R.string.condition_29_30_44);
            case 45: return context.getString(R.string.condition_45);
            case 46: return context.getString(R.string.condition_46);
            case 47: return context.getString(R.string.condition_47);
            case 3200: return context.getString(R.string.condition_3200);
            default: return "";
        }
    }
}
