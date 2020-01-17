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
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private static final String URL="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    private class EarthQuakeAsyncTask extends AsyncTask<String,Integer,ArrayList<Earthquake>>{

        @Override
        protected ArrayList<Earthquake> doInBackground(String... strings) {

            URL url = QueryUtils.makeURL(strings[0]);
            QueryUtils.makeHttpUrlConnection(url);
            return QueryUtils.extractEarthQuakes();

        }

        @Override
        protected void onPostExecute(ArrayList<Earthquake> earthquakes) {

            EarthquakeAdapter adapter = new EarthquakeAdapter(EarthquakeActivity.this,0,earthquakes);
            ListView listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int index, long l) {

                    Earthquake earthquake = (Earthquake) parent.getItemAtPosition(index);
                    Uri uri = Uri.parse(earthquake.getUrl());
                    // provided action and source
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(intent);

                }
            });


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
        new EarthQuakeAsyncTask().execute(URL);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.dark_scheme:
                return true;

            case R.id.quit:
                finish();
                return true;

            case R.id.count:
                Intent intent = new Intent(EarthquakeActivity.this,Settings.class);
                startActivity(intent);
                return true;

            default: return super.onOptionsItemSelected(item);
        }

    }

}
