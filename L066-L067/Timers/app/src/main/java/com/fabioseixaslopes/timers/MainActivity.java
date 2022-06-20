package com.fabioseixaslopes.timers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Countdown
        new CountDownTimer(10000,1000){
            @Override
            public void onTick(long millisecondsUntilDone) {
                System.out.println("I'm Counting. 1 second has passed. " + millisecondsUntilDone/1000 + " seconds until done.");
            }
            @Override
            public void onFinish() {
                System.out.println("Finished Countdown.");
            }
        }.start();

        //Timer repeating
        Handler handler = new Handler();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                //do code
                System.out.println("I've ran!");
                //repeat
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(run);
    }
}