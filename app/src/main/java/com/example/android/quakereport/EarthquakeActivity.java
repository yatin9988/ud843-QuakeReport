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
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
        fakedata();
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
                return true;

            default: return super.onOptionsItemSelected(item);
        }

    }

    public void fakedata(){

        ArrayList<Earthquake> earthquakes = new ArrayList<>();


        earthquakes.add(new Earthquake((3.5f),"San Francisco","Jan 20,2020"));
        earthquakes.add(new Earthquake((4.9f),"London","Dec 24,2012"));
        earthquakes.add(new Earthquake((2.4f),"Mexico City","Feb 20,2010"));
        earthquakes.add(new Earthquake((4.0f),"Mumbai","Jan 20,2019"));
        earthquakes.add(new Earthquake((2.5f),"Abu Dhabi","Mar 20,2017"));
        earthquakes.add(new Earthquake((1.9f),"France","Apr 20,2013"));
        earthquakes.add(new Earthquake((3.7f),"Texas","Jun 20,2015"));
        earthquakes.add(new Earthquake((5.0f),"Berlin","Nov 20,2018"));
        earthquakes.add(new Earthquake((2.2f),"Sydney","Oct 20,2020"));
        earthquakes.add(new Earthquake((3.5f),"Delhi","May 20,2019"));

        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        EarthquakeAdapter earthquakeAdapter = new EarthquakeAdapter(EarthquakeActivity.this,0,earthquakes);
        earthquakeListView.setAdapter(earthquakeAdapter);

    }

}
