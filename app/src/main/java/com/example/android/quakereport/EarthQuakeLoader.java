package com.example.android.quakereport;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import java.net.URL;
import java.util.List;

public class EarthQuakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    // public constructor od this class should call the constructor of the superclass
    public EarthQuakeLoader(Context context){
        super(context);
    }

    // forceLoad() calls the loadInBackground method
    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    // this method is run on the background thread,hence prevents the UI from blocking
    @Override
    public List<Earthquake> loadInBackground() {

        URL url = QueryUtils.makeURL(EarthquakeActivity.URL);
        QueryUtils.makeHttpUrlConnection(url);
        return QueryUtils.extractEarthQuakes();

    }
}
