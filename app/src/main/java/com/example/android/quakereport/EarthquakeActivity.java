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
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>> {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    public static final String URL="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=3.2&limit=100";
    private static EarthquakeAdapter earthquakeAdapter;
    private static ListView listView;
    private static ProgressBar progressBar;
    private static TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        textView = (TextView) findViewById(R.id.no_internet);
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();
        if (!isConnected) {
            textView.setText("NO INTERNET CONNECTION");
        } else {
            listView = (ListView) findViewById(R.id.list);
            earthquakeAdapter = new EarthquakeAdapter(EarthquakeActivity.this, 0, new ArrayList<Earthquake>());
            listView.setAdapter(earthquakeAdapter);

            progressBar = (ProgressBar) findViewById(R.id.spinner);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Earthquake earthquake = (Earthquake) adapterView.getItemAtPosition(i);
                    String url = earthquake.getUrl();
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });

            // during the entire lifecycle of the loader
            // a loader with id 0 is only created once
            // it is one of the reasons of using asyncloader over asynctask
            getSupportLoaderManager().initLoader(0, null, this);
        }
    }


    @Override
    public Loader<List<Earthquake>> onCreateLoader(int id, Bundle args) {
        return new EarthQuakeLoader(EarthquakeActivity.this);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> data) {
        // check if valid data is returned
        if(data!=null && !data.isEmpty()) {
            progressBar.setVisibility(View.GONE);
            earthquakeAdapter.addAll(data);
        }

        // if after the load due to some erroe from the server or any error in the code
        // or if there are no recent earthquakes an empty view is set
        else{
            progressBar.setVisibility(View.GONE);
            View view = findViewById(R.id.noearthquake);
            listView.setEmptyView(view);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        // frees the memory resources of the adapater or add a new blank arraylist
        // both options work
        earthquakeAdapter.clear();
    }
}
