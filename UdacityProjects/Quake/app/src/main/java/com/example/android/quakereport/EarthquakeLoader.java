package com.example.android.quakereport;

import android.content.Context;
import android.content.AsyncTaskLoader;

import java.util.List;

/**
 * Created by johnmoriarty on 10/4/16.
 */
public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    //Query url
    private String mUrl;

    //Constructs a new {@link EarthquakeLoader
    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    //This runs on a background thread
    @Override
    public List<Earthquake> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        //Perform the network request, parse the response, and extract a list of earthquakes
        List<Earthquake> earthquakes = QueryUtils.extractEarthquakes(mUrl);
        return earthquakes;
    }

}
