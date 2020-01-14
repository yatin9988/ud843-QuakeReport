package com.example.android.quakereport;

public class Earthquake {

    private float magnitude;
    private String location;
    private String time;

    // default constructor
    public Earthquake(){

    }

    // parametrized constructor
    public Earthquake(float magnitude,String location,String time){

        this.magnitude = magnitude;
        this.location = location;
        this.time = time;
    }

    public void setMagnitude(int magnitude){
        this.magnitude = magnitude;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public void setTime(String time){
        this.time = time;
    }

    public float getMagnitude(){
        return this.magnitude;
    }

    public String getLocation(){
        return this.location;
    }

    public String getTime(){
        return this.time;
    }

}
