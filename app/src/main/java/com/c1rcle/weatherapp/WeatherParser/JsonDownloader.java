package com.c1rcle.weatherapp.WeatherParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonDownloader
{
    public String HTTPGetCall(String WebMethodURL) throws IOException
    {
        StringBuilder response = new StringBuilder();

        URL u = new URL(WebMethodURL);
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();

        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK)
        {
            BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()),8192);
            String line;
            while ((line = input.readLine()) != null)
            {
                response.append(line);
            }
            input.close();
        }
        return response.toString();
    }
}
