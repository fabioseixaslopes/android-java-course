package com.fabioseixaslopes.translatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    Button grid1, grid2, grid3, grid4, grid5, grid6, grid7, grid8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setButtonListeners();
    }

    private void playSound(int sound){
        mediaPlayer = MediaPlayer.create(this, sound);
        mediaPlayer.start();
    }

    private void setButtonListeners(){
        grid1 = findViewById(R.id.grid1);
        grid2 = findViewById(R.id.grid2);
        grid3 = findViewById(R.id.grid3);
        grid4 = findViewById(R.id.grid4);
        grid5 = findViewById(R.id.grid5);
        grid6 = findViewById(R.id.grid6);
        grid7 = findViewById(R.id.grid7);
        grid8 = findViewById(R.id.grid8);
        grid1.setOnClickListener(view -> playSound(R.raw.hello));
        grid2.setOnClickListener(view -> playSound(R.raw.welcome));
        grid3.setOnClickListener(view -> playSound(R.raw.please));
        grid4.setOnClickListener(view -> playSound(R.raw.mynameis));
        grid5.setOnClickListener(view -> playSound(R.raw.doyouspeakenglish));
        grid6.setOnClickListener(view -> playSound(R.raw.goodevening));
        grid7.setOnClickListener(view -> playSound(R.raw.ilivein));
        grid8.setOnClickListener(view -> playSound(R.raw.howareyou));
    }
}