package com.example.android.quakereport;

/**
 * Created by johnmoriarty on 9/19/16.
 */
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {


    private QueryUtils() {
    }

    //Parse the JSON
    public static ArrayList<Earthquake> extractEarthquakes(String url) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(url)) {
            return null;
        }

        ArrayList<Earthquake> earthquakes = new ArrayList<>();

        try {
            JSONObject baseJsonResponse = new JSONObject(makeHttpRequest(createUrl(url)));
            JSONArray earthquakeArray = baseJsonResponse.getJSONArray("features");

            // If there are results in the features array
            for(int i=0; i < earthquakeArray.length(); i++){
                JSONObject currentEarthquake = earthquakeArray.getJSONObject(i);
                JSONObject properties = currentEarthquake.getJSONObject("properties");

                // Extract out the magnitude, place, time, and url
                double magnitude = properties.getDouble("mag");
                String place = properties.getString("place");
                Long time = properties.getLong("time");
                String url2 =properties.getString("url");

                earthquakes.add(new Earthquake(magnitude, place, time, url2));

            }


        } catch (JSONException e) {
//            Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return earthquakes;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
           return null;
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
//                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
//            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    //    public static ArrayList<Earthquake> fetchEarthquakeData(String requestUrl) {
//        // Create URL object
//        URL url = createUrl(requestUrl);
//
//        // Perform HTTP request to the URL and receive a JSON response back
//        String jsonResponse = null;
//        try {
//            jsonResponse = makeHttpRequest(url);
//        } catch (IOException e) {
////            Log.e(LOG_TAG, "Error closing input stream", e);
//        }
//
//        // Extract relevant fields from the JSON response and create an {@link Event} object
//        ArrayList<Earthquake> earthquake = extractEarthquakes(jsonResponse);
//
//        // Return the {@link Event}
//        return earthquake;
//    }

//    public static ArrayList<Earthquake> extractEarthquakes() {
//
//        ArrayList<Earthquake> earthquakes = new ArrayList<>();
//
//        try {
//            JSONObject baseJsonResponse = new JSONObject(SAMPLE_JSON_RESPONSE);
//            JSONArray earthquakeArray = baseJsonResponse.optJSONArray("features");
//
//
//            for(int i=0; i < earthquakeArray.length(); i++){
//                JSONObject currentEarthquake = earthquakeArray.getJSONObject(i);
//                JSONObject properties = currentEarthquake.getJSONObject("properties");
//
//
//                double magnitude = properties.getDouble("mag");
//                String place = properties.getString("place");
//                Long time = properties.getLong("time");
//                String url =properties.getString("url");
//
//                earthquakes.add(new Earthquake(magnitude, place, time, url));
//            }
//
//        } catch (JSONException e) {
//            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
//        }
//
//        return earthquakes;
//    }
}

