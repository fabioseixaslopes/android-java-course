package com.fabioseixaslopes.processingjsondata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    // coordinates
    double lat = 38.704845100250225;
    double lon = -8.974290060273573;

    String key = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get key
        try {
            Resources res = getResources();
            InputStream in_s = res.openRawResource(R.raw.weather_key);
            Properties properties = new Properties();
            properties.load(in_s);
            key = properties.getProperty("api");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // download HTML data from website
        DownloadTask task = new DownloadTask();
        String result = null;
        try {
            result = task.execute("https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=" + key).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Content of URL: " + result);
        reportWeather(result);
    }

    private double fahrenheitToCelsius(double fahrenheit)
    {
        return (( 5 *(fahrenheit - 32.0)) / 9.0);
    }

    private void reportWeather(String content){
        String message = "";
        TextView editTextWeatherMessage = findViewById(R.id.textViewWeather);

        try {
            //gets object
            JSONObject jsonObject = new JSONObject(content);
            //gets interesting part
            String weatherInfo = jsonObject.get("weather").toString();
            //get various objects if there are more than 1
            JSONArray jsonArray = new JSONArray(weatherInfo);
            for (int i = 0; i < jsonArray.length(); i++) {
                //prints each objects' main field, which is the weather type.
                JSONObject jsonPart = jsonArray.getJSONObject(i);
                message = "The weather is " + jsonPart.getString("main") + ", " +
                        jsonPart.getString("description") + ". ";

            }
            //TODO finish this
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(message);
        editTextWeatherMessage.setText(message);
    }
}