package com.c1rcle.weatherapp.Utility;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectSerialization
{
    public static <T> T getObject(String filename, Context context)
    {
        try
        {
            FileInputStream inputStream = context.openFileInput(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            @SuppressWarnings("unchecked")
            T returnObject = (T) objectInputStream.readObject();

            objectInputStream.close();
            inputStream.close();
            return returnObject;
        }
        catch (FileNotFoundException e)
        {
            return null;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> void writeObject(T object, String filename, Context context)
    {
        try
        {
            FileOutputStream outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(object);

            objectOutputStream.close();
            outputStream.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
