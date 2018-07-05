package com.c1rcle.weatherapp.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.c1rcle.weatherapp.R;
import com.c1rcle.weatherapp.Utility.WeatherIcons;
import com.c1rcle.weatherapp.WeatherInfo.WeatherInfoForecastItem;

public class ForecastViewHolder extends RecyclerView.ViewHolder
{
    public final View mView;

    public final TextView mDay;

    public final TextView mDate;

    public final ImageView mIcon;

    public final TextView mTemp;

    public final TextView mDescription;

    public WeatherInfoForecastItem mItem;

    public ForecastViewHolder(View view)
    {
        super(view);
        mView = view;
        mDay = view.findViewById(R.id.itemDay);
        mDate = view.findViewById(R.id.itemDate);
        mIcon = view.findViewById(R.id.itemIcon);
        mTemp = view.findViewById(R.id.itemTemp);
        mDescription = view.findViewById(R.id.itemDescription);
    }

    @Override
    public String toString()
    {
        return super.toString() + " '" + mDate.getText() + "'";
    }
}
