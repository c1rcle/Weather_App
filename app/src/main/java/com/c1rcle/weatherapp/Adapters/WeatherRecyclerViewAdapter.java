package com.c1rcle.weatherapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.c1rcle.weatherapp.R;
import com.c1rcle.weatherapp.Utility.WeatherIcons;
import com.c1rcle.weatherapp.ViewHolder.ForecastViewHolder;
import com.c1rcle.weatherapp.WeatherInfo.WeatherInfoForecastItem;

import java.util.List;

public class WeatherRecyclerViewAdapter extends RecyclerView.Adapter<ForecastViewHolder>
{
    private final List<WeatherInfoForecastItem> mValues;

    public WeatherRecyclerViewAdapter(List<WeatherInfoForecastItem> items)
    {
        mValues = items;
    }

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forecast_component, parent, false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ForecastViewHolder holder, int position)
    {
        holder.mItem = mValues.get(position);
        holder.mDay.setText(mValues.get(position).getDay());
        holder.mDate.setText(mValues.get(position).getDate());
        holder.mIcon.setImageResource(mValues.get(position).getIcon());
        holder.mMinTemp.setText(mValues.get(position).getMinTemp());
        holder.mMaxTemp.setText(mValues.get(position).getMaxTemp());
        holder.mDescription.setText(mValues.get(position).getDescription());
        holder.mPressure.setText(mValues.get(position).getPressure());
        holder.mHumidity.setText(mValues.get(position).getHumidity());
        holder.mWind.setText(mValues.get(position).getWind());
        holder.mPrecip.setText(mValues.get(position).getPrecip());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}
