package com.c1rcle.weatherapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.c1rcle.weatherapp.R;
import com.c1rcle.weatherapp.ViewHolder.TodayViewHolder;

import static com.c1rcle.weatherapp.WeatherInfo.WeatherInfo.TODAY_ITEM;

public class TodayFragment extends Fragment
{
    public TodayFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_today, container, false);
        if (TODAY_ITEM != null)
        {
            TodayViewHolder holder = new TodayViewHolder(view, TODAY_ITEM);
            holder.setViews();
        }
        return view;
    }
}
