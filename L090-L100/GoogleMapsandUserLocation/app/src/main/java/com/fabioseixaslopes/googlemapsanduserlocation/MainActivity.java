package com.fabioseixaslopes.googlemapsanduserlocation;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // start Maps activity
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}