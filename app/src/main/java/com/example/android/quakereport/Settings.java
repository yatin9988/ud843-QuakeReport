package com.example.android.quakereport;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.RadioGroup;

public class Settings extends AppCompatActivity {

    private EditText count;
    private EditText startDate;
    private EditText endDate;
    private EditText magnitude;

    private RadioGroup time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        count = (EditText) findViewById(R.id.count);
        startDate = (EditText) findViewById(R.id.starttime);
        endDate = (EditText) findViewById(R.id.endtime);
        magnitude = (EditText) findViewById(R.id.min_magnitude);

        time = (RadioGroup) findViewById(R.id.time);

        int c = Integer.parseInt(count.getText().toString());
        String sdate = startDate.getText().toString();
        String edate = endDate.getText().toString();
        int m = Integer.parseInt(magnitude.getText().toString());

        

    }
}
