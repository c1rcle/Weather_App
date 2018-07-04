package com.c1rcle.weatherapp.Utility;

public class Units
{
    public static String getUnit(String unit)
    {
        if (unit.equals("c")) return " °C";
        else return " °F";
    }
}
