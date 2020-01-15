package com.example.android.quakereport;

public class Earthquake {

    private String magnitude;
    private String offset;
    private String primary;
    private String date;
    private String time;

    // parametrized constructor
    public Earthquake(String magnitude,String offset,String primary,String date,String time){

        this.magnitude = magnitude;
        this.offset = offset;
        this.primary = primary;
        this.time = time;
        this.date = date;

    }

    public String getMagnitude(){
        return this.magnitude;
    }

    public String getOffset(){
        return this.offset;
    }

    public String getPrimary(){
        return this.primary;
    }

    public String getTime(){
        return this.time;
    }

    public String getDate(){
        return this.date;
    }

}
