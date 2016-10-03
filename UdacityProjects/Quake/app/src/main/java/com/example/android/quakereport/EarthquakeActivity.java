/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>> {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    /** URL for earthquake data from the USGS dataset */
    private static final String USGS_REQUEST_URL =
            "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int id, Bundle args) {
        return new EarthquakeLoader(this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, final List<Earthquake> earthquakes) {

        final ListView earthquakeListView = (ListView) findViewById(R.id.list);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = earthquakes.get(position).getUrl();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

        EarthquakeAdapter adapter = new EarthquakeAdapter(getApplicationContext(), R.layout.earthquake_list_item, earthquakes);

        earthquakeListView.setAdapter(adapter);

    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {

    }


    public static class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

        private String mUrl;
        public EarthquakeLoader(Context context, String url) {
            super(context);
            mUrl = url;
        }

        @Override
        public List<Earthquake> loadInBackground() {
            return QueryUtils.extractEarthquakes(mUrl);
        }
    }





//    private void updateUi(List earthquake) {
////        ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();
//        List<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(USGS_REQUEST_URL);
//
//        // Find a reference to the {@link ListView} in the layout
//        ListView earthquakeListView = (ListView) findViewById(R.id.list);
//
//        // Create a new {@link ArrayAdapter} of earthquakes
//        final EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakes);
//
//        // Set the adapter on the {@link ListView}
//        // so the list can be populated in the user interface
//        earthquakeListView.setAdapter(adapter);
//
//        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//
//                //Find the current earthquake that was clicked on
//                Earthquake currentEarthquake = adapter.getItem(position);
//
//                //Convert the String URL into URI object (to pass into the Intent constructor)
//                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());
//
//                //Create a new intent to view the earthquake URI
//                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
//
//                //Send the intent to launch a new activity
//                startActivity(websiteIntent);
//            }
//        });
//    }
//
//    /**
//     * {@link AsyncTask} to perform the network request on a background thread, and then
//     * update the UI with the first earthquake in the response.
//     */
//    private class EarthquakeAsyncTask extends AsyncTask<String, Void, String> {
//
//        /**
//         * This method is invoked (or called) on a background thread, so we can perform
//         * long-running operations like making a network request.
//         *
//         * It is NOT okay to update the UI from a background thread, so we just return an
//         * {@link Event} object as the result.
//         */
//        @Override
//        protected String doInBackground(String... url) {
//
//            //Don't perform the request if there are no URLs, or the first URL is null.
//            if (url.length < 1 || url[0] == null){
//                return null;
//            }
//
//            // Perform the HTTP request for earthquake data and process the response.
//            List result = QueryUtils.fetchEarthquakeData(url[0]);
//            return result;
//        }
//
//        /**
//         * This method is invoked on the main UI thread after the background work has been
//         * completed.
//         *
//         * It IS ok to modify the UI within this method. We take the {@link Event} object
//         * (which was returned from the doInBackground() method) and update the views on the screen.
//         */
//        @Override
//        protected void onPostExecute(String result) {
//            //If there is no result, do nothing.
//            if (result == null) {
//                return;
//            }
//
//            updateUi(result);
//        }
//    }

}
