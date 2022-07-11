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

    @Override
    protected String doInBackground(String... strings) {
        URL url;
        HttpURLConnection urlConnection;
        StringBuilder result = new StringBuilder();

        try{
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            int data = reader.read();
            while(data != -1)
            {
                char current = (char) data;
                result.append(current);
                data = reader.read();
            }
        }
        catch (Exception e){
            return "failed due to " + e;
        }
        return result.toString();
    }

    @Override
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