package com.c1rcle.weatherapp.WeatherParser;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.google.gson.JsonElement;

import java.io.IOException;

public class RefreshTask extends AsyncTaskLoader<JsonElement>
{
    private String URL;

    public RefreshTask(Context context, String URL)
    {
        super(context);
        this.URL = URL;
    }

    @Override
    public JsonElement loadInBackground()
    {
        JsonDownloader downloader = new JsonDownloader();
        JsonElement data;
        try
        {
            data = downloader.HTTPGetCall(URL);
        }
        catch (IOException e)
        {
            return null;
        }
        return data;
    }
}
