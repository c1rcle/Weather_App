package com.c1rcle.weatherapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.c1rcle.weatherapp.Activity.MainActivity;
import com.c1rcle.weatherapp.Adapters.CitiesListViewAdapter;
import com.c1rcle.weatherapp.R;
import com.c1rcle.weatherapp.Utility.TinyDB;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class CitiesFragment extends Fragment
{
    private static ArrayList<String> mValues = new ArrayList<>();

    private ListView listView;

    private CitiesListViewAdapter adapter;

    private TextView citiesHint;

    private ImageView citiesImage;

    private boolean firstCityAdded;

    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

    private ViewTreeObserver.OnPreDrawListener listListener = new ViewTreeObserver.OnPreDrawListener()
    {
        @Override
        public boolean onPreDraw()
        {
            listView.getViewTreeObserver().removeOnPreDrawListener(this);
            TinyDB database = new TinyDB(getContext());
            if (mValues.size() == 1 && firstCityAdded)
            {
                ViewGroup viewGroup = (ViewGroup) listView.getChildAt(0);
                TextView text = (TextView) getTextView(viewGroup);
                database.putString("default_city", text.getText().toString());
                ((MainActivity)getActivity()).preRefresh();
                ((MainActivity)getActivity()).refreshPressed();
                firstCityAdded = false;
            }
            return false;
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_cities, container, false);
        final TinyDB database = new TinyDB(getContext());
        listView = view.findViewById(R.id.citiesList);
        citiesHint = view.findViewById(R.id.citiesHint);
        citiesImage = view.findViewById(R.id.citiesImage);

        registerForContextMenu(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                listView.showContextMenuForChild(view);
            }
        });

        mValues = database.getListString("cities_list");
        String mDefaultCity = database.getString("default_city");
        if (mValues.size() > 0) setHintsInvisible();
        adapter  = new CitiesListViewAdapter(getContext(), R.layout.cities_component, mValues, mDefaultCity);
        listView.setAdapter(adapter);

        if (this.isResumed()) listView.getViewTreeObserver().addOnPreDrawListener(listListener);
        return view;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        listView.getViewTreeObserver().addOnPreDrawListener(listListener);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem item = menu.add("Search");
        item.setIcon(R.drawable.ic_search_icon_action);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem)
            {
                try
                {
                    AutocompleteFilter filter = new AutocompleteFilter.Builder()
                            .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                            .build();
                    Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                            .setFilter(filter)
                            .build(getActivity());
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                }
                catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e)
                {
                    Toast.makeText(getContext(), getString(R.string.error_play_services), Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE)
        {
            if (resultCode == RESULT_OK)
            {
                Place place = PlaceAutocomplete.getPlace(getContext(), data);
                onPlaceSelected(place);
            }
            else if (resultCode == PlaceAutocomplete.RESULT_ERROR)
                Toast.makeText(getContext(), getString(R.string.error_city_edit), Toast.LENGTH_SHORT).show();
        }
    }

    public void onPlaceSelected(Place place)
    {
        TinyDB database = new TinyDB(getContext());
        mValues.add(place.getName().toString());
        listView.setAdapter(adapter);
        listView.getViewTreeObserver().addOnPreDrawListener(listListener);
        database.putListString("cities_list", mValues);
        if (mValues.size() == 1) firstCityAdded = true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.cities_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        ViewGroup viewGroup = (ViewGroup) info.targetView;
        TextView text = (TextView) getTextView(viewGroup);

        TinyDB database = new TinyDB(getContext());

        int id = item.getItemId();
        if (id == R.id.context_default)
        {
            database.putString("default_city", text.getText().toString());
            ((MainActivity)getActivity()).preRefresh();
            ((MainActivity)getActivity()).refreshPressed();
        }
        else
        {
            if (text.getText().equals(database.getString("default_city")))
            {
                database.remove("default_city");
                adapter.setDefaultCity("");
            }
            mValues.remove(info.position);
            if (mValues.size() == 0) setHintsVisible();
            database.putListString("cities_list", mValues);
            listView.setAdapter(adapter);
        }
        return super.onContextItemSelected(item);
    }

    private View getTextView(ViewGroup group)
    {
        return group.getChildAt(0);
    }

    private void setHintsVisible()
    {
        citiesHint.setVisibility(View.VISIBLE);
        citiesImage.setVisibility(View.VISIBLE);
    }

    private void setHintsInvisible()
    {
        citiesHint.setVisibility(View.GONE);
        citiesImage.setVisibility(View.GONE);
    }
}