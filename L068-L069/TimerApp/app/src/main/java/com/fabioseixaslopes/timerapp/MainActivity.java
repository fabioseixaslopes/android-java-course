package com.fabioseixaslopes.timerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBarTimer;
    TextView textViewTimer;
    MediaPlayer mediaPlayer;
    Button buttonStartStop;
    int minValue = 1, maxValue = 60*60;
    int defaultValue = maxValue/2;
    int timeForTimer;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTimer = findViewById(R.id.textViewTimer);
        seekBarTimer = findViewById(R.id.seekBarTimer);
        buttonStartStop = findViewById(R.id.buttonStartStop);

        seekBarTimer.setMax(maxValue);
        seekBarTimer.setMin(minValue);
        seekBarTimer.setProgress(defaultValue);
        updateTimerText(defaultValue);
        timeForTimer = defaultValue;
        buttonStartStop.setText(R.string.button_start);
        mediaPlayer = MediaPlayer.create(this,R.raw.horn);

        seekBarTimer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimerText(progress);
                timeForTimer = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        buttonStartStop.setOnClickListener(view -> {
            if(seekBarTimer.isEnabled()) {
                seekBarTimer.setEnabled(false);
                timer = setTimer(timeForTimer);
                buttonStartStop.setText(R.string.button_stop);
            }
            else{
                seekBarTimer.setEnabled(true);
                timer.cancel();
                buttonStartStop.setText(R.string.button_start);
            }
        });
    }

    private void updateTimerText(int value){
        int hours, minutes, seconds;
        hours = value/3600;
        minutes = (value%3600)/60;
        seconds = (value%3600)%60;
        textViewTimer.setText(getString(R.string.time_timer,hours,minutes,seconds));
    }

    private CountDownTimer setTimer(int time){
        return new CountDownTimer((time + 1) * 1000L,1000){
            @Override
            public void onTick(long millisecondsUntilDone) {
                seekBarTimer.setProgress((int) millisecondsUntilDone/1000);
                System.out.println("I'm Counting, 1 second has passed. " + (int) millisecondsUntilDone/1000 + " seconds until done.");
            }
            @Override
            public void onFinish() {
                mediaPlayer.start();
                resetTimer(defaultValue);
                System.out.println("Finished Timer.");
            }
        }.start();
    }

    private void resetTimer(int time){
        seekBarTimer.setEnabled(true);
        seekBarTimer.setProgress(time);
        buttonStartStop.setText(R.string.button_start);
    }
}