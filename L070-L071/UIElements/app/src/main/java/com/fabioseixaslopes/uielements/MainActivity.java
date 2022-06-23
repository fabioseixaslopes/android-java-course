package com.fabioseixaslopes.uielements;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button show = findViewById(R.id.buttonShow);
        Button hide = findViewById(R.id.buttonHide);
        ImageView image = findViewById(R.id.imageView);

        show.setOnClickListener(view -> image.setVisibility(View.VISIBLE));

        hide.setOnClickListener(view -> image.setVisibility(View.INVISIBLE));
    }
}