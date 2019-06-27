package com.c1rcle.weatherapp.Update;

import android.os.AsyncTask;

import com.c1rcle.weatherapp.Listeners.UpdateListener;
import com.c1rcle.weatherapp.WeatherParser.JsonDownloader;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.util.ArrayList;

public class UpdateTask extends AsyncTask<String, Void, ArrayList<String>>
{
    private String lang;

    private UpdateListener listener;

    public UpdateTask(String lang, UpdateListener listener)
    {
        this.lang = lang;
        this.listener = listener;
    }

    @Override
    protected ArrayList<String> doInBackground(String... urls)
    {
        JsonDownloader downloader = new JsonDownloader();
        ArrayList<String> result = new ArrayList<>();
        try
        {
            String desc = "description" + (lang.equals("pl") ? "_pl" : "_en");
            JsonElement version = downloader.HTTPGetCall(urls[0].replace("value", "version"));
            JsonElement description = downloader.HTTPGetCall(urls[0].replace("value", desc));
            if (version.isJsonArray() && description.isJsonArray())
            {
                result.add("correct");
                result.add(version.getAsJsonArray().get(0).getAsJsonObject().get("value").getAsString());
                result.add(description.getAsJsonArray().get(0).getAsJsonObject().get("value").getAsString());

            }
            else result.add("corrupt");
        }
        catch (IOException e)
        {
            e.printStackTrace();
            result.add("http_err");
        }
        return result;
    }

    @Override
    protected void onPostExecute(ArrayList<String> result)
    {
        if (result.size() == 1) listener.onUpdateCompleted(null, null, result.get(0));
        else listener.onUpdateCompleted(result.get(1), result.get(2), result.get(0));
    }
}
