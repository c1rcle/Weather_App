package com.c1rcle.weatherapp.WeatherInfo;

public class WeatherInfoForecastItem
{
    private final String day;

    private final String date;

    private final int icon;

    private final String temp;

    private final String description;

    public WeatherInfoForecastItem(String day, String date, int icon, String temp, String description)
    {
        this.day = day;
        this.date = date;
        this.icon = icon;
        this.temp = temp;
        this.description = description;
    }

    public String getDay()
    {
        return day;
    }

    public String getDate()
    {
        return date;
    }

    public int getIcon()
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
}
