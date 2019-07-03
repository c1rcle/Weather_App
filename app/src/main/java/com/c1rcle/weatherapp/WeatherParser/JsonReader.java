package com.c1rcle.weatherapp.WeatherParser;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import com.c1rcle.weatherapp.R;
import com.c1rcle.weatherapp.Utility.City;
import com.c1rcle.weatherapp.Utility.ObjectSerialization;
import com.c1rcle.weatherapp.Utility.Units;
import com.c1rcle.weatherapp.Utility.WeatherIcons;
import com.c1rcle.weatherapp.Utility.WeekDays;
import com.c1rcle.weatherapp.WeatherInfo.WeatherInfo;
import com.c1rcle.weatherapp.WeatherInfo.WeatherInfoForecastItem;
import com.c1rcle.weatherapp.WeatherInfo.WeatherInfoTodayItem;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class JsonReader
{
    private JsonObject jsonWeather;

    private Context context;

    public JsonReader(JsonElement data, Context context)
    {
        this.context = context;
        try
        {
            this.jsonWeather = data.getAsJsonObject();
        }
        catch (JsonSyntaxException e)
        {
            e.printStackTrace();
            Toast.makeText(context, context.getString(R.string.error_parsing), Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("DefaultLocale")
    public void populateList(String unit) throws JsonSyntaxException
    {
        WeatherInfo.clearList();
        JsonArray forecast = jsonWeather.getAsJsonObject("daily").getAsJsonArray("data");
        for (int i = 1; i < forecast.size(); i++)
        {
            JsonObject object = forecast.get(i).getAsJsonObject();

            Date date = new Date(object.get("time").getAsLong() * 1000);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            String day = WeekDays.translateDay(calendar.get(Calendar.DAY_OF_WEEK), context);
            int icon = WeatherIcons.translateCode(object.get("icon").getAsString());
            String tempMin = String.format("%.0f", object.get("temperatureMin").getAsDouble()) + Units.getTempUnit(unit);
            String tempMax = String.format("%.0f", object.get("temperatureMax").getAsDouble()) + Units.getTempUnit(unit);
            String description = WeatherIcons.translateDescription(object.get("icon").getAsString(), context);
            String pressure = String.format("%.0f", object.get("pressure").getAsDouble()) + Units.getPressureUnit(unit);
            String humidity = String.format("%.0f", object.get("humidity").getAsDouble() * 100) + "%";
            String wind = object.get("windSpeed").getAsString() + Units.getSpeedUnit(unit) + "(" +
                    context.getString(R.string.forecast_direction) + " " + object.get("windBearing").getAsString() + "°)";
            String precip = String.format("%.0f", object.get("precipProbability").getAsDouble() * 100) + "%";

            WeatherInfoForecastItem item = new WeatherInfoForecastItem(day,
                    new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(date),
                    icon, tempMin, tempMax, description, pressure, humidity, wind, precip);
            WeatherInfo.addItem(item);
        }
    }

    @SuppressLint("DefaultLocale")
    public void populateToday(String unit) throws JsonSyntaxException
    {
        JsonObject forecast = jsonWeather.getAsJsonObject("currently");

        City city = ObjectSerialization.getObject("default_city", context);
        int icon = WeatherIcons.translateCode(forecast.get("icon").getAsString());
        String temp = String.format("%.0f", forecast.get("temperature").getAsDouble()) + Units.getTempUnit(unit);
        String description = WeatherIcons.translateDescription(forecast.get("icon").getAsString(), context);
        String ref = new SimpleDateFormat(" dd.MM.yyyy HH:mm", Locale.getDefault()).format(new Date());
        ref = context.getString(R.string.last_refreshed) + ref;
        WeatherInfo.TODAY_ITEM = new WeatherInfoTodayItem(Objects.requireNonNull(city).getName(),
                icon, temp, description, ref);
    }
}
