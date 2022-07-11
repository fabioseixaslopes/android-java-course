package com.fabioseixaslopes.processingjsondata;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadTask extends AsyncTask<String, Void, String> {
    //First parameter is the parameter for the task
    //Second is for a method for progress of the task
    //Third is the type of variable returned by the task

    @Override
    protected String doInBackground(String... strings) {
        //access to internet is needed for the app
        //for that, add the permission to the manifest
        //<uses-permission android:name="android.permission.INTERNET"/>
        URL url;
        HttpURLConnection urlConnection = null;
        StringBuilder result = new StringBuilder();

        try{
            //gets URL
            url = new URL(strings[0]);
            //opens connection
            urlConnection = (HttpURLConnection) url.openConnection();
            //setting up readers
            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            //read first character
            int data = reader.read();
            //loop through all characters and add them to result
            while(data != -1)
            {
                char current = (char) data;
                //add character to result
                result.append(current);
                //go to next character
                data = reader.read();
            }
        }
        catch (Exception e){
            return "failed due to " + e;
        }
        return result.toString();
    }

    @Override
    //the doInBackground does not interact with the UI, for that use onPostExecute
    protected void onPostExecute(String s){
        super.onPostExecute(s);
        try {
            //gets object
            JSONObject jsonObject = new JSONObject(s);
            //gets interesting part
            String weatherInfo = jsonObject.get("weather").toString();
            //get various objects if there are more than 1
            JSONArray jsonArray = new JSONArray(weatherInfo);
            for (int i = 0; i < jsonArray.length(); i++){
                //prints each objects' main field, which is the weather type.
                JSONObject jsonPart = jsonArray.getJSONObject(i);
                System.out.println("The weather is " + jsonPart.getString("main") + ".");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}