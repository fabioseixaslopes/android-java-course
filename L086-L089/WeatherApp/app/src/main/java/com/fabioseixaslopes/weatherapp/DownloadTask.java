package com.fabioseixaslopes.weatherapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

public class DownloadTask extends AsyncTask<String, Void, String> {

    @SuppressLint("StaticFieldLeak")
    private TextView cityWeather;

    public DownloadTask(TextView cityWeather) {
        this.cityWeather = cityWeather;
    }

    @Override
    protected String doInBackground(String... strings) {
        URL url;
        HttpURLConnection urlConnection;
        StringBuilder result = new StringBuilder();

        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            int data = reader.read();
            while (data != -1) {
                char current = (char) data;
                result.append(current);
                data = reader.read();
            }
        } catch (Exception e) {
            return "failed due to " + e;
        }
        return result.toString();
    }

    @Override
    //the doInBackground does not interact with the UI, for that use onPostExecute
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        try {
            JSONObject jsonObject = new JSONObject(s);
            String weatherInfo = jsonObject.get("weather").toString();
            String tempInfo = jsonObject.get("main").toString();
            String windInfo = jsonObject.get("wind").toString();
            String visibilityInfo = jsonObject.get("visibility").toString();
            String cloudsInfo = jsonObject.get("clouds").toString();
            String cityInfo = jsonObject.get("name").toString();
            JSONObject jsonArrayClouds = new JSONObject(cloudsInfo);
            JSONObject jsonArrayTemp = new JSONObject(tempInfo);
            JSONObject jsonArrayWind = new JSONObject(windInfo);
            JSONArray jsonArrayWeather = new JSONArray(weatherInfo);
            for (int i = 0; i < jsonArrayWeather.length(); i++) {
                JSONObject jsonPart = jsonArrayWeather.getJSONObject(i);
                if (!jsonPart.getString("main").equals("") &&
                        !jsonPart.getString("description").equals("")) {
                    cityWeather.setText("Weather: " +
                            jsonPart.getString("main") + "\n"
                            + "Description: " + jsonPart.getString("description") + "\n"
                            + "Temperature: " + df.format(kelvinToCelsius(Float.parseFloat(jsonArrayTemp.getString("temp")))) + "ºC" + "\n"
                            + "Humidity: " + jsonArrayTemp.getString("humidity") + "%" + "\n"
                            + "Visibility: " + visibilityInfo + " meters" + "\n"
                            + "Wind: " + jsonArrayWind.get("speed") + " m/s" + "\n"
                            + "Clouds: " + jsonArrayClouds.getString("all") + "%" + "\n"
                            + "City: " + cityInfo);
                }
            }
        } catch (JSONException e) {
            cityWeather.setText(R.string.empty_result);
            e.printStackTrace();
        }
    }

    private float kelvinToCelsius(float kelvin) {
        return kelvin - 273.15F;
    }
}
