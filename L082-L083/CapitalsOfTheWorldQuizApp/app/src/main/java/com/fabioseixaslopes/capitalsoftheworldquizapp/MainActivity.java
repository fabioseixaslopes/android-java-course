package com.fabioseixaslopes.capitalsoftheworldquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String linkDataSource = "https://happytowander.com/world-capitals/";
    TextView textViewTime, textViewScore;
    Button buttonStart, buttonAnswer1, buttonAnswer2, buttonAnswer3, buttonAnswer4;
    int gameTime = 10; CountDownTimer timerGame;
    int currentScore = 0, totalQuestions = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        gameViews(false);
    }

    private void initializeViews(){
        textViewTime = findViewById(R.id.textViewTime);
        textViewScore = findViewById(R.id.textViewScore);
        buttonStart = findViewById(R.id.buttonPlayGame);
        buttonAnswer1 = findViewById(R.id.buttonAnswer1);
        buttonAnswer2 = findViewById(R.id.buttonAnswer2);
        buttonAnswer3 = findViewById(R.id.buttonAnswer3);
        buttonAnswer4 = findViewById(R.id.buttonAnswer4);

        buttonStart.setOnClickListener(view -> {
            gameViews(true);
            startGame();
        });
    }

    private void startGame(){
        currentScore = 0; totalQuestions = 0;
        textViewScore.setText(getString(R.string.game_score, currentScore, totalQuestions));
        timerGame = startTimer(gameTime);
    }

    private void finishGame(){}

    private void gameViews(boolean visibility){
        textViewTime.setVisibility((visibility) ? View.VISIBLE : View.GONE);
        textViewScore.setVisibility((visibility) ? View.VISIBLE : View.GONE);
        buttonStart.setVisibility((visibility) ? View.GONE : View.VISIBLE);
        buttonAnswer1.setVisibility((visibility) ? View.VISIBLE : View.GONE);
        buttonAnswer2.setVisibility((visibility) ? View.VISIBLE : View.GONE);
        buttonAnswer3.setVisibility((visibility) ? View.VISIBLE : View.GONE);
        buttonAnswer4.setVisibility((visibility) ? View.VISIBLE : View.GONE);
    }

    private CountDownTimer startTimer(int time){
        return new CountDownTimer(time* 1000L +1000,1000){
            @Override
            public void onTick(long millisecondsUntilDone) {
                textViewTime.setText(getString(R.string.game_time,millisecondsUntilDone/1000));
                System.out.println("I'm Counting. 1 second has passed. " + millisecondsUntilDone/1000 + " seconds until done.");
            }
            @Override
            public void onFinish() {
                finishGame();
                System.out.println("Finished Countdown.");
            }
        }.start();
    }
}