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
import com.c1rcle.weatherapp.Utility.City;
import com.google.android.gms.common.api.Api;

import java.util.List;
import java.util.Optional;

public class CitiesListViewAdapter extends ArrayAdapter<City>
{
    private City mDefaultCity;

    public CitiesListViewAdapter(Context context, int resource, List<City> values, City defaultCity)
    {
        super(context, resource, values);
        if (defaultCity == null) defaultCity = new City("", null);
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

        City value = getItem(position);

        if (value != null)
        {
            TextView cityText = view.findViewById(R.id.citiesListText);
            ImageView cityImage = view.findViewById(R.id.citiesListImage);

            if (cityText != null)
            {
                cityText.setText(value.getName());
                if (cityText.getText().toString().equals(mDefaultCity.getName()))
                    cityImage.setVisibility(View.VISIBLE);
            }
        }
        return view;
    }

    public void setDefaultCity(City city)
    {
        mDefaultCity = city;
    }
}
