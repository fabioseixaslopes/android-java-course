package com.fabioseixaslopes.capitalsoftheworldquizapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadTaskImage extends AsyncTask<String, Void, Bitmap> {
    //First parameter is the parameter for the task
    //Second is for a method for progress of the task
    //Third is the type of variable returned by the task

    @Override
    protected Bitmap doInBackground(String... urls) {
        //access to internet is needed for the app
        //for that, add the permission to the manifest
        //<uses-permission android:name="android.permission.INTERNET"/>
        URL url;
        HttpURLConnection urlConnection;
        Bitmap bitmap = null;

        try{
            //gets URL
            url = new URL(urls[0]);
            //opens connection
            urlConnection = (HttpURLConnection) url.openConnection();
            //connects, gets input stream and converts to bitmap
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        }
        catch (Exception e){
            System.out.println("Error Exception: " + e);
        }
        return bitmap;
    }
}