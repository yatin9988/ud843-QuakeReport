package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
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

        GradientDrawable drawable = (GradientDrawable) magnitude.getBackground();
        int color = getColor(earthquake.getMagnitude());
        drawable.setColor(color);

        magnitude.setText(earthquake.getMagnitude());
        offset.setText(earthquake.getOffset());
        primary.setText(earthquake.getPrimary());
        date.setText(earthquake.getTime()+"");

        return convertView;
    }

    public int getColor(String magnitude){

        double magi = Double.parseDouble(magnitude);
        int mag = (int) Math.floor(magi);
        int color = 0;

        switch (mag){

            case 1:color = R.color.magnitude1;
            break;
            case 2:color = R.color.magnitude2;
                break;
            case 3:color = R.color.magnitude3;
                break;
            case 4:color = R.color.magnitude4;
                break;
            case 5:color = R.color.magnitude5;
                break;
            case 6:color = R.color.magnitude6;
                break;
            case 7:color = R.color.magnitude7;
                break;
            case 8:color = R.color.magnitude8;
                break;
            case 9:color = R.color.magnitude9;
                break;
            case 10:color = R.color.magnitude10plus;
                break;

        }
            // converts the color from resouce id to an actual color value
            return ContextCompat.getColor(getContext(),color);
    }

}
