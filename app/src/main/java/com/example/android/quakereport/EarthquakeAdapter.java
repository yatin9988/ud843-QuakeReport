package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Context context, int id, ArrayList<Earthquake> earthquakes){
        super(context,0,earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item,parent,false);
        }

        TextView magnitude = (TextView) convertView.findViewById(R.id.magnitude);
        TextView offset = (TextView) convertView.findViewById(R.id.offset);
        TextView primary = (TextView) convertView.findViewById(R.id.primary);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView time = (TextView) convertView.findViewById(R.id.time);

        Earthquake earthquake = getItem(position);

        magnitude.setText(earthquake.getMagnitude());
        offset.setText(earthquake.getOffset());
        primary.setText(earthquake.getPrimary());
        date.setText(earthquake.getTime()+"");

        return convertView;
    }
}
