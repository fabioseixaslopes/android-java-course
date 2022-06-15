package com.fabioseixaslopes.audio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    SeekBar volumeControl;
    SeekBar timeControl;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this,R.raw.sound);

        volumeControl = findViewById(R.id.seekBarVolume);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        volumeControl.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        volumeControl.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

        timeControl = findViewById(R.id.seekBarLength);
        timeControl.setMax(mediaPlayer.getDuration());
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeControl.setProgress(mediaPlayer.getCurrentPosition());
            }
        }, 0, 1000);

        timeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser)
                    mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if(mediaPlayer.isPlaying())
                    mediaPlayer.pause();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.start();
            }
        });

        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    public void play(View view){
        mediaPlayer.start();
    }
    public void pause(View view){
        mediaPlayer.pause();
    }
}