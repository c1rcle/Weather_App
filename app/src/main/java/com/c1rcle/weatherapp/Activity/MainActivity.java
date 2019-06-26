package com.c1rcle.weatherapp.Activity;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.c1rcle.weatherapp.Fragments.CitiesFragment;
import com.c1rcle.weatherapp.Fragments.ForecastFragment;
import com.c1rcle.weatherapp.Fragments.SettingsFragment;
import com.c1rcle.weatherapp.Fragments.TodayFragment;
import com.c1rcle.weatherapp.Fragments.UpdateDialogFragment;
import com.c1rcle.weatherapp.R;
import com.c1rcle.weatherapp.Utility.City;
import com.c1rcle.weatherapp.Utility.ObjectSerialization;
import com.c1rcle.weatherapp.Utility.Themes;
import com.c1rcle.weatherapp.Utility.TinyDB;
import com.c1rcle.weatherapp.Utility.Units;
import com.c1rcle.weatherapp.WeatherParser.JsonReader;
import com.c1rcle.weatherapp.WeatherParser.RefreshTask;
import com.google.gson.JsonElement;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                   LoaderManager.LoaderCallbacks
{
    private String unit;

    private String URL;

    private int NAV_DRAWER_DELAY = 400;

    private int NAV_DRAWER_FADE_IN_OUT = 250;

    private SwipeRefreshLayout swipeRefresh;

    private boolean downloadingDataFailed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String theme = prefs.getString("pref_theme", "t");
        setTheme(Themes.getTheme(theme));
        super.onCreate(savedInstanceState);

        setTaskDescription(new ActivityManager.TaskDescription(null, null, Color.parseColor("#f5f5f5")));

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        swipeRefresh = findViewById(R.id.swipe_refresh);
        swipeRefresh.setOnRefreshListener(refreshListener);
        TypedArray a = getTheme().obtainStyledAttributes(new int[] { R.attr.colorPrimaryDark });
        int color = a.getColor(0, 0);
        swipeRefresh.setColorSchemeColors(color);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_today);

        TinyDB database = new TinyDB(this);
        if (!database.getBoolean("n_first_start"))
        {
            Thread pref = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    TinyDB database = new TinyDB(getApplicationContext());
                    database.putBoolean("n_first_start", true);
                    final Intent intent = new Intent(MainActivity.this, IntroActivity.class);
                    startActivity(intent);
                }
            });
            pref.start();
            onNavigationItemSelected(navigationView.getMenu().getItem(2));
            navigationView.setCheckedItem(R.id.nav_search);
        }
        else
        {
            if (ObjectSerialization.getObject("default_city", getApplicationContext()) != null)
            {
                preRefresh();
                refreshPressed();
            }
            else
            {
                onNavigationItemSelected(navigationView.getMenu().getItem(2));
                navigationView.setCheckedItem(R.id.nav_search);
            }
        }
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args)
    {
        return new RefreshTask(this, URL);
    }

    @Override
    public void onLoaderReset(Loader loader) {}

    @Override
    public void onLoadFinished(Loader loader, Object data)
    {
        JsonElement source = (JsonElement) data;
        if(source != null)
        {
            JsonReader parser = new JsonReader(source, this);
            try
            {
                parser.populateToday(unit);
                parser.populateList(unit);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                Toast.makeText(this, R.string.error_wrong_city, Toast.LENGTH_SHORT).show();
                NavigationView navView = findViewById(R.id.nav_view);
                onNavigationItemSelected(navView.getMenu().getItem(2));
                navView.getMenu().getItem(2).setChecked(true);
                downloadingDataFailed = true;
            }
            postRefresh();

            NavigationView navView = findViewById(R.id.nav_view);
            int selectedItemId = getSelectedItemId(navView);
            onNavigationItemSelected(navView.getMenu().getItem(selectedItemId));
            downloadingDataFailed = false;
        }
        else
        {
            Toast.makeText(this, R.string.error_data, Toast.LENGTH_SHORT).show();
            postRefresh();
            NavigationView navView = findViewById(R.id.nav_view);
            onNavigationItemSelected(navView.getMenu().getItem(2));
            navView.getMenu().getItem(2).setChecked(true);
            downloadingDataFailed = true;
        }
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.bar_context_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_refresh)
        {
            preRefresh();
            refreshPressed();
        }
        else if (id == R.id.action_update)
        {
            UpdateDialogFragment dialogFragment = new UpdateDialogFragment();
            dialogFragment.show(getFragmentManager(), "update");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        if ((ObjectSerialization.getObject("default_city", getApplicationContext()) != null
                || item.getItemId() == R.id.nav_search) && !downloadingDataFailed)
        {
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            final View mainContent = findViewById(R.id.fragmentContainer);
            final int id = item.getItemId();
            drawer.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    launchFragment(id);
                    mainContent.animate().alpha(1).setDuration(NAV_DRAWER_FADE_IN_OUT);
                }
            }, NAV_DRAWER_DELAY);

            if (mainContent != null)
            {
                mainContent.animate().alpha(0).setDuration(NAV_DRAWER_FADE_IN_OUT);
            }

            drawer.closeDrawer(GravityCompat.START);
        }
        else if (downloadingDataFailed)
        {
            Toast.makeText(this, getString(R.string.error_corrupted), Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            Toast.makeText(this, getString(R.string.error_no_city), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void launchFragment(int id)
    {
        Fragment fragment;
        if (id == R.id.nav_today)
        {
            fragment = new TodayFragment();
            replaceFragment(fragment);
        }
        else if (id == R.id.nav_forecast)
        {
            fragment = new ForecastFragment();
            replaceFragment(fragment);
        }
        else if (id == R.id.nav_search)
        {
            fragment = new CitiesFragment();
            replaceFragment(fragment);
        }
        else if (id == R.id.nav_settings)
        {
            fragment = new SettingsFragment();
            replaceFragment(fragment);
        }
    }

    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener()
    {
        @Override
        public void onRefresh()
        {
            refreshPressed();
        }
    };

    public void preRefresh()
    {
        swipeRefresh.setRefreshing(true);
    }

    public void postRefresh()
    {
        swipeRefresh.setRefreshing(false);
    }

    public void refreshPressed()
    {
        City city = ObjectSerialization.getObject("default_city", getApplicationContext());
        if (city != null && !city.getName().isEmpty())
        {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            unit = prefs.getString("pref_unit", "si");

            URL = getString(R.string.weather_url);
            URL = URL.replace("apikey", getString(R.string.DARKSKY_API_KEY));
            URL = URL.replace("lat", Double.toString(city.getCoordinates().latitude));
            URL = URL.replace("lon", Double.toString(city.getCoordinates().longitude));
            URL = URL.replace("unitSet", unit);
            unit = Units.getUnit(unit);

            Loader loader = getSupportLoaderManager().restartLoader(0, null, this);
            loader.forceLoad();
        }
        else
        {
            postRefresh();
            Toast.makeText(this, getString(R.string.error_city), Toast.LENGTH_SHORT).show();
        }
    }

    public void replaceFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commitAllowingStateLoss();
    }

    public int getSelectedItemId(NavigationView navigationView)
    {
        int selectedItemId = 0;
        for (int i = 0; i < navigationView.getMenu().size(); i++)
        {
            MenuItem item = navigationView.getMenu().getItem(i);
            if (item.isChecked()) selectedItemId = i;
        }
        return selectedItemId;
    }
}
