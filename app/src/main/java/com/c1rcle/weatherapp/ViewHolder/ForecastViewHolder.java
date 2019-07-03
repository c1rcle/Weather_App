package com.c1rcle.weatherapp.ViewHolder;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.c1rcle.weatherapp.R;
import com.c1rcle.weatherapp.WeatherInfo.WeatherInfoForecastItem;

public class ForecastViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public final View mView;

    public final TextView mDay;

    public final TextView mDate;

    public final ImageView mIcon;

    public final TextView mMinTemp;

    public final TextView mMaxTemp;

    public final TextView mDescription;

    public final TextView mPressure;

    public final TextView mHumidity;

    public final TextView mWind;

    public final TextView mPrecip;

    public WeatherInfoForecastItem mItem;

    private ConstraintLayout extendedLayout;

    public ForecastViewHolder(View view)
    {
        super(view);
        itemView.setOnClickListener(this);

        mView = view;
        mDay = view.findViewById(R.id.itemDay);
        mDate = view.findViewById(R.id.itemDate);
        mIcon = view.findViewById(R.id.itemIcon);
        mMinTemp = view.findViewById(R.id.itemTempLow);
        mMaxTemp = view.findViewById(R.id.itemTempHigh);
        mDescription = view.findViewById(R.id.itemDescription);
        mPressure = view.findViewById(R.id.itemPressureValue);
        mHumidity = view.findViewById(R.id.itemHumidityValue);
        mWind = view.findViewById(R.id.itemWindValue);
        mPrecip = view.findViewById(R.id.itemPrecipValue);

        extendedLayout = view.findViewById(R.id.constraintLayoutDetails);
    }

    @Override
    public String toString()
    {
        return super.toString() + " '" + mDate.getText() + "'";
    }

    @Override
    public void onClick(View view)
    {
        if (extendedLayout.getVisibility() == View.VISIBLE)
            extendedLayout.setVisibility(View.GONE);
        else extendedLayout.setVisibility(View.VISIBLE);
    }
}
