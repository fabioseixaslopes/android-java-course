package com.fabioseixaslopes.mathtrainerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textViewGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewGo = findViewById(R.id.textViewGo);

        textViewGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Make it disappear
            }
        });
    }
}