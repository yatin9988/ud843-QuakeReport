package com.example.android.quakereport;

import android.graphics.drawable.GradientDrawable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public final class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getName();

    private static String SAMPLE_JSON_RESPONSE = "";


    // no one should initialise the object of this class
    // had to add explicitly because a default public constructor is added by itself
     private QueryUtils(){

     }

     public static ArrayList<Earthquake> extractEarthQuakes(){

         ArrayList<Earthquake> earthquakes = new ArrayList<>();
         try{

             JSONObject root = new JSONObject(SAMPLE_JSON_RESPONSE);
             JSONArray features = root.getJSONArray("features");
             for(int i=0;i<features.length();i++){

                 JSONObject object = features.getJSONObject(i);
                 JSONObject properties = object.getJSONObject("properties");
                 double mag = properties.getDouble("mag");
                 String location = properties.getString("place");
                 long time = properties.getLong("time");
                 String url = properties.getString("url");

                 DecimalFormat decimalFormat = new DecimalFormat("0.0");
                 String formatted = decimalFormat.format(mag);

                 int index = location.indexOf(" of ");
                 String offset = offset_function(location,index);
                 String primary = primary_function(location,index);

                 Date dateObj = new Date(time);
                 String dates = date(dateObj);
                 String times = time(dateObj);

                 Earthquake earthquake = new Earthquake(formatted,offset,primary,dates,times,url);
                 earthquakes.add(earthquake);

             }

         }catch (Exception e){
             Log.i(LOG_TAG,"yatin");
             e.printStackTrace();
         }
         return earthquakes;
     }

     public static String date(Date date){

         SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
         return dateFormat.format(date);
     }

     public static String time(Date date){

         SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
         return timeFormat.format(date);
     }

     public static String offset_function(String location,int index){

         if(index == -1)
             return "Near the";

         else
             return location.substring(0,index);
     }

     public static String primary_function(String location,int index){

         if(index == -1)
             return location;
         else
             return location.substring(index+4);
     }

     public static URL makeURL(String string){

         URL url = null;
         try{
             url = new URL(string);
         }catch (Exception e){
             e.printStackTrace();
         }
         return url;

     }

     public static void makeHttpUrlConnection(URL url){

         if(url==null)
             return;

         try{
             HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
             urlConnection.setConnectTimeout(10000);
             urlConnection.setReadTimeout(10000);
             urlConnection.setRequestMethod("GET");
             urlConnection.connect();

             if(urlConnection.getResponseCode()==200) {
                 InputStream inputStream = urlConnection.getInputStream();
                 SAMPLE_JSON_RESPONSE = readFromStream(inputStream);
             }

         }catch (Exception e){
             e.printStackTrace();
         }

     }

     public static String readFromStream(InputStream inputStream){

         if(inputStream == null)
             return "";
         StringBuilder sb = new StringBuilder();

         try {
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
             String line = bufferedReader.readLine();
             while(line!=null){
                 sb.append(line);
                 line = bufferedReader.readLine();
             }
         }catch (Exception e){
             e.printStackTrace();
         }
         return sb.toString();
     }

}
