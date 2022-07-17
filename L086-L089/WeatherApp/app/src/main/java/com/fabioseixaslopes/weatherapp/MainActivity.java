package com.fabioseixaslopes.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    String key;
    public TextView cityWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get key
        try {
            Resources res = getResources();
            InputStream inputStream = res.openRawResource(R.raw.weather_key);
            Properties properties = new Properties();
            properties.load(inputStream);
            key = properties.getProperty("api");
        } catch (Exception e) {
            e.printStackTrace();
        }

        EditText cityEditText = findViewById(R.id.editTextTextCity);
        cityWeather = findViewById(R.id.textViewWeather);
        Button button = findViewById(R.id.buttonSubmitCity);

        button.setOnClickListener(view -> {
            String cityText = cityEditText.getText().toString();
            if (cityText.equals("")) {
                cityWeather.setText(getString(R.string.empty_input));
            }
            else {
                //Remove Spaces / Encode (in example San Francisco encodes to San%20Francisco)
                String encodedCityName = "";
                try {
                    encodedCityName = URLEncoder.encode(cityText, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                DownloadTask task = new DownloadTask(cityWeather);
                String result = null;
                try {
                    result = task.execute("https://api.openweathermap.org/data/2.5/weather?q=" + encodedCityName + "&appid=" + key).get();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(result);
            }
        });
    }
}