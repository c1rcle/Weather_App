package com.c1rcle.weatherapp.WeatherParser;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonDownloader
{
    public JsonElement HTTPGetCall(String WebMethodURL) throws IOException
    {
        InputStreamReader response;

        URL u = new URL(WebMethodURL.replace(" ","%20"));
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();
        conn.connect();

        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK)
        {
            response = new InputStreamReader((InputStream) conn.getContent());
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(response);
            response.close();
            return element;
        }
        return null;
    }
}
