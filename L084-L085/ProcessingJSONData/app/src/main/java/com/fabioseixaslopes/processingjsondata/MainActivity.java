package com.fabioseixaslopes.processingjsondata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;

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
    }
}