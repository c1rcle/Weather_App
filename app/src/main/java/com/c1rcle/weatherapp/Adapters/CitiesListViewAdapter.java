package com.c1rcle.weatherapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.c1rcle.weatherapp.R;

import java.util.List;

public class CitiesListViewAdapter extends ArrayAdapter<String>
{
    private String mDefaultCity;

    public CitiesListViewAdapter(Context context, int resource, List<String> values, String defaultCity)
    {
        super(context, resource, values);
        mDefaultCity = defaultCity;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View view = convertView;

        if (view == null)
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.cities_component, null);
        }

        String value = getItem(position);

        if (value != null)
        {
            TextView cityText = view.findViewById(R.id.citiesListText);
            ImageView cityImage = view.findViewById(R.id.citiesListImage);

            if (cityText != null)
            {
                cityText.setText(value);
                if (cityText.getText().toString().equals(mDefaultCity))
                    cityImage.setVisibility(View.VISIBLE);
            }
        }
        return view;
    }

    public void setDefaultCity(String city)
    {
        mDefaultCity = city;
    }
}
