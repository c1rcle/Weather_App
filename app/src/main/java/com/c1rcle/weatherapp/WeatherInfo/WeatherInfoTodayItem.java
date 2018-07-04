package com.c1rcle.weatherapp.WeatherInfo;

public class WeatherInfoTodayItem
{
    private final String icon;

    private final String temp;

    private final String description;

    private final String city;

    private final String refreshStamp;

    public WeatherInfoTodayItem(String city, String icon, String temp, String description, String refreshStamp)
    {
        this.city = city;
        this.icon = icon;
        this.temp = temp;
        this.description = description;
        this.refreshStamp = refreshStamp;
    }

    public String getIcon()
    {
        return icon;
    }

    public String getTemp()
    {
        return temp;
    }

    public String getDescription()
    {
        return description;
    }

    public String getCity()
    {
        return city;
    }

    public String getRefreshStamp()
    {
        return refreshStamp;
    }
}
