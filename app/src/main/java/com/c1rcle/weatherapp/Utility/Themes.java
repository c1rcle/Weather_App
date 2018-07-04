package com.c1rcle.weatherapp.Utility;

import com.c1rcle.weatherapp.R;

public class Themes
{
    public static int getTheme(String key)
    {
        switch (key)
        {
            case "t":
                return R.style.AppTheme_NoActionBarMain;
            case "i":
                return R.style.AppThemeI_NoActionBarMain;
            case "r":
                return R.style.AppThemeR_NoActionBarMain;
            default:
                return R.style.AppTheme_NoActionBarMain;
        }
    }
}
