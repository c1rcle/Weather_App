package com.c1rcle.weatherapp.WeatherInfo;

public class WeatherInfoForecastItem
{
    private final String day;

    private final String date;

    private final int icon;

    private final String minTemp;

    private final String maxTemp;

    private final String description;

    private final String pressure;

    private final String humidity;

    private final String wind;

    private final String precip;

    private boolean expanded;

    public WeatherInfoForecastItem(String day, String date, int icon, String minTemp, String maxTemp,
                                   String description, String pressure, String humidity, String wind,
                                   String precip)
    {
        this.day = day;
        this.date = date;
        this.icon = icon;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.description = description;
        this.pressure = pressure;
        this.humidity = humidity;
        this.wind = wind;
        this.precip = precip;
        expanded = false;
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

    public String getMinTemp()
    {
        return minTemp;
    }

    public String getMaxTemp()
    {
        return maxTemp;
    }

    public String getDescription()
    {
        return description;
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

    public boolean isExpanded()
    {
        return expanded;
    }

    public void setExpanded(boolean expanded)
    {
        this.expanded = expanded;
    }
}
