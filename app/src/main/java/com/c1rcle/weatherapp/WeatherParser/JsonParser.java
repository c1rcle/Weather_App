package com.c1rcle.weatherapp.WeatherParser;

import android.content.Context;
import android.widget.Toast;

import com.c1rcle.weatherapp.R;
import com.c1rcle.weatherapp.Utility.TinyDB;
import com.c1rcle.weatherapp.Utility.WeatherIcons;
import com.c1rcle.weatherapp.Utility.WeekDays;
import com.c1rcle.weatherapp.WeatherInfo.WeatherInfo;
import com.c1rcle.weatherapp.WeatherInfo.WeatherInfoForecastItem;
import com.c1rcle.weatherapp.WeatherInfo.WeatherInfoTodayItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonParser
{
    private JSONObject jsonWeather;

    private Context context;

    public JsonParser(String input, Context context)
    {
        input = input.substring(input.indexOf("{"), input.lastIndexOf("}") + 1);
        this.context = context;
        try
        {
            this.jsonWeather = new JSONObject(input);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Toast.makeText(context, context.getString(R.string.error_parsing), Toast.LENGTH_SHORT).show();
        }
    }

    public void populateList(String unit) throws JSONException
    {
        WeatherInfo.clearList();
        JSONArray forecast = jsonWeather.getJSONObject("query").getJSONObject("results")
                .getJSONObject("channel").getJSONObject("item").getJSONArray("forecast");
        for (int i = 0; i < forecast.length(); i++)
        {
            JSONObject object = forecast.getJSONObject(i);
            String day = WeekDays.translateDay(object.getString("day"), context);
            String date = new SimpleDateFormat("dd.MM.yyyy").format(new Date(object.getString("date")));
            int icon = WeatherIcons.translateCode(object.getInt("code"));
            String temp = object.getString("high") + unit;
            String description = WeatherIcons.translateDescription(object.getInt("code"), context);
            WeatherInfoForecastItem item = new WeatherInfoForecastItem(day, date, icon, temp, description);
            WeatherInfo.addItem(item);
        }
    }

    public void populateToday(String unit) throws JSONException
    {
        JSONObject forecast = jsonWeather.getJSONObject("query").getJSONObject("results")
                .getJSONObject("channel").getJSONObject("item").getJSONObject("condition");

        TinyDB database = new TinyDB(context);
        String city = database.getString("default_city");
        int icon = WeatherIcons.translateCode(forecast.getInt("code"));
        String temp = forecast.getString("temp") + unit;
        String description = WeatherIcons.translateDescription(forecast.getInt("code"), context);
        String ref = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date());
        ref = context.getString(R.string.last_refreshed) + ref;
        WeatherInfo.TODAY_ITEM = new WeatherInfoTodayItem(city, icon, temp, description, ref);
    }
}
