package com.c1rcle.weatherapp.WeatherParser;

import android.os.AsyncTask;

import com.google.gson.JsonElement;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class UpdateTask extends AsyncTask<URL, Void, ArrayList<String>>
{
    @Override
    protected ArrayList<String> doInBackground(URL... urls)
    {
        JsonDownloader downloader = new JsonDownloader();
        ArrayList<String> returnList = new ArrayList<>();
        try
        {
            JsonElement json = downloader.HTTPGetCall(urls[0].toString());
            if (json.isJsonObject())
            {
                returnList.add(json.getAsJsonObject().get("version").getAsString());
                returnList.add(json.getAsJsonObject().get("description").getAsString());
            }
            return returnList;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return returnList;
    }

    @Override
    protected void onPostExecute(ArrayList<String> strings)
    {
        super.onPostExecute(strings);
    }
}
