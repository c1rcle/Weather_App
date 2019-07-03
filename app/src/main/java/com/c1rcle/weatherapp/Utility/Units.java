package com.c1rcle.weatherapp.Utility;

public class Units
{
    public static String getTempUnit(String unit)
    {
        if (unit.equals("si")) return " °C";
        else return " °F";
    }

    public static String getSpeedUnit(String unit)
    {
        if (unit.equals("si")) return " km/h ";
        else return "mph";
    }

    public static String getPressureUnit(String unit)
    {
        if (unit.equals("si")) return " hp";
        else return "inHg";
    }
}
