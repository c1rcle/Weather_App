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

    private final float mScaleDp;

    public WeatherRecyclerViewAdapter(List<WeatherInfoForecastItem> items, float scaleDp)
    {
        mValues = items;
        mScaleDp = scaleDp;
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
        holder.mIcon.setText(mValues.get(position).getIcon());
        holder.mTemp.setText(mValues.get(position).getTemp());
        holder.mDescription.setText(mValues.get(position).getDescription());
        if (WeatherIcons.neutralIcons.contains(holder.mIcon.getText().toString()))
        {
            float marginTop = 8 * (mScaleDp / 160f);
            float marginBot = 20 * (mScaleDp / 160f);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) holder.mIcon.getLayoutParams();
            params.setMargins(0, (int) marginTop, 0, (int) marginBot);
        }
        else if (WeatherIcons.lowerIcons.contains(holder.mIcon.getText().toString()))
        {
            float marginTop = 8 * (mScaleDp / 160f);
            float marginBot = 10 * (mScaleDp / 160f);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) holder.mIcon.getLayoutParams();
            params.setMargins(0, (int) marginTop, 0, (int) marginBot);
        }
        else
        {
            float marginTop = 19 * (mScaleDp / 160f);
            float marginBot = 8 * (mScaleDp / 160f);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) holder.mIcon.getLayoutParams();
            params.setMargins(0, (int) marginTop, 0, (int) marginBot);
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}
