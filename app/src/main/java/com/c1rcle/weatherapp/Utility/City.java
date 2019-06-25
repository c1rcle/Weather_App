package com.c1rcle.weatherapp.Utility;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class City implements Serializable
{
    private String name;

    private transient LatLng coordinates;

    public City(String name, LatLng coordinates)
    {
        this.name = name;
        this.coordinates = coordinates;
    }

    private void writeObject(ObjectOutputStream outputStream) throws IOException
    {
        outputStream.defaultWriteObject();
        outputStream.writeDouble(coordinates.latitude);
        outputStream.writeDouble(coordinates.longitude);
    }

    private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException
    {
        inputStream.defaultReadObject();
        coordinates = new LatLng(inputStream.readDouble(), inputStream.readDouble());
    }

    public String getName()
    {
        return name;
    }

    public LatLng getCoordinates()
    {
        return coordinates;
    }
}
