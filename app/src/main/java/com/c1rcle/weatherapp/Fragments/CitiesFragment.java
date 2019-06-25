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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.c1rcle.weatherapp.Activity.MainActivity;
import com.c1rcle.weatherapp.Adapters.CitiesListViewAdapter;
import com.c1rcle.weatherapp.R;
import com.c1rcle.weatherapp.Utility.City;
import com.c1rcle.weatherapp.Utility.ObjectSerialization;
import com.c1rcle.weatherapp.Utility.TinyDB;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.util.ArrayList;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class CitiesFragment extends Fragment
{
    private static ArrayList<City> mValues = new ArrayList<>();

    private ListView listView;

    private CitiesListViewAdapter adapter;

    private TextView citiesHint;

    private ImageView citiesImage;

    private boolean firstCityAdded;

    private boolean placesActivityRunning = false;

    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

    private ViewTreeObserver.OnPreDrawListener listListener = new ViewTreeObserver.OnPreDrawListener()
    {
        @Override
        public boolean onPreDraw()
        {
            listView.getViewTreeObserver().removeOnPreDrawListener(this);

            if (mValues.size() == 1 && firstCityAdded)
            {
                ObjectSerialization.writeObject(mValues.get(0), "default_city", Objects.requireNonNull(getContext()));
                ((MainActivity) Objects.requireNonNull(getActivity())).preRefresh();
                ((MainActivity) getActivity()).refreshPressed();
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

        ArrayList<City> savedState = ObjectSerialization.getObject("cities_list", Objects.requireNonNull(getContext()));
        if (savedState != null) mValues = savedState;
        City mDefaultCity = ObjectSerialization.getObject("default_city", getContext());

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
                    if (placesActivityRunning)
                    {
                        Toast.makeText(getContext(), getString(R.string.error_places_activity), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    placesActivityRunning = true;
                    AutocompleteFilter filter = new AutocompleteFilter.Builder()
                            .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                            .build();
                    Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                            .setFilter(filter)
                            .build(Objects.requireNonNull(getActivity()));
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                }
                catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e)
                {
                    Toast.makeText(getContext(), getString(R.string.error_play_services), Toast.LENGTH_SHORT).show();
                    placesActivityRunning = false;
                }
                return false;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        placesActivityRunning = false;
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE)
        {
            if (resultCode == RESULT_OK)
            {
                Place place = PlaceAutocomplete.getPlace(Objects.requireNonNull(getContext()), data);
                onPlaceSelected(place);
            }
            else if (resultCode == PlaceAutocomplete.RESULT_ERROR)
                Toast.makeText(getContext(), getString(R.string.error_city_edit), Toast.LENGTH_SHORT).show();
        }
    }

    public void onPlaceSelected(Place place)
    {
        City selectedCity = new City(place.getName().toString(), place.getLatLng());
        mValues.add(selectedCity);
        listView.setAdapter(adapter);
        listView.getViewTreeObserver().addOnPreDrawListener(listListener);
        ObjectSerialization.writeObject(mValues, "cities_list", Objects.requireNonNull(getContext()));
        if (mValues.size() == 1) firstCityAdded = true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = Objects.requireNonNull(getActivity()).getMenuInflater();
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
            ObjectSerialization.writeObject(mValues.get(info.position) ,"default_city", Objects.requireNonNull(getContext()));
            ((MainActivity) Objects.requireNonNull(getActivity())).preRefresh();
            ((MainActivity) getActivity()).refreshPressed();
        }
        else
        {
            City city = ObjectSerialization.getObject("default_city", Objects.requireNonNull(getContext()));
            if (city != null && text.getText().equals(city.getName()))
            {
                getContext().deleteFile("default_city");
                adapter.setDefaultCity(new City("", null));
            }
            mValues.remove(info.position);
            if (mValues.size() == 0) setHintsVisible();
            ObjectSerialization.writeObject(mValues, "cities_list", Objects.requireNonNull(getContext()));
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