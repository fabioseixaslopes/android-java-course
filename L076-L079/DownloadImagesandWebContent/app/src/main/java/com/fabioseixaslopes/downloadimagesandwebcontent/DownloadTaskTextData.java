package com.fabioseixaslopes.downloadimagesandwebcontent;

import android.os.AsyncTask;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadTaskTextData extends AsyncTask<String, Void, String> {
    //First parameter is the parameter for the task
    //Second is for a method for progress of the task
    //Third is the type of variable returned by the task

    @Override
    protected String doInBackground(String... urls) {
        //access to internet is needed for the app
        //for that, add the permission to the manifest
        //<uses-permission android:name="android.permission.INTERNET"/>
        URL url;
        HttpURLConnection urlConnection;
        StringBuilder result = new StringBuilder();

        try{
            //gets URL
            url = new URL(urls[0]);
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
}