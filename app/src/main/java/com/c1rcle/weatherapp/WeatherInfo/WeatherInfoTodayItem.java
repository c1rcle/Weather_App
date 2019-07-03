package com.c1rcle.weatherapp.WeatherInfo;

public class WeatherInfoTodayItem
{
    private final int icon;

    private final String temp;

    private final String description;

    private final String city;

    private final String pressure;

    private final String humidity;

    private final String wind;

    private final String precip;

    private final String refreshStamp;

    public WeatherInfoTodayItem(String city, int icon, String temp, String description, String pressure,
                                String humidity, String wind, String precip, String refreshStamp)
    {
        this.city = city;
        this.icon = icon;
        this.temp = temp;
        this.description = description;
        this.pressure = pressure;
        this.humidity = humidity;
        this.wind = wind;
        this.precip = precip;
        this.refreshStamp = refreshStamp;
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

    public String getCity()
    {
        return city;
    }

    public String getPressure()
    {
        return pressure;
    }

    public String getHumidity()
    {
        return humidity;
    }

    public String getWind()
    {
        return wind;
    }

    public String getPrecip()
    {
        return precip;
    }

    public String getRefreshStamp()
    {
        return refreshStamp;
    }
}
