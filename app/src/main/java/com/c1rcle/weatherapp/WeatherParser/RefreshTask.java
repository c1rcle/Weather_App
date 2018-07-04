package com.c1rcle.weatherapp.WeatherParser;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.io.IOException;

public class RefreshTask extends AsyncTaskLoader<String>
{
    private String URL;

    public RefreshTask(Context context, String URL)
    {
        super(context);
        this.URL = URL;
    }

    @Override
    public String loadInBackground()
    {
        JsonDownloader downloader = new JsonDownloader();
        String data;
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
