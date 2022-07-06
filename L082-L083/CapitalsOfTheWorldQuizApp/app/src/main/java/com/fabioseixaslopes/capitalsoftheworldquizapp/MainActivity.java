package com.fabioseixaslopes.capitalsoftheworldquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    String linkDataSource = "https://happytowander.com/world-capitals/";
    String currentSolution, currentQuestion;
    ArrayList<String> countries = new ArrayList<>(), capitals = new ArrayList<>();
    TextView textViewTime, textViewScore, textViewQuestion, textViewFinalScore;
    Button buttonStart, buttonAnswer1, buttonAnswer2, buttonAnswer3, buttonAnswer4;
    int gameTime = 60; CountDownTimer timerGame;
    int currentScore = 0, totalQuestions = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        loadGameAnswers();
        //loadGameImages();
        gameViews(false);
    }

    private void initializeViews(){
        textViewTime = findViewById(R.id.textViewTime);
        textViewScore = findViewById(R.id.textViewScore);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        textViewFinalScore = findViewById(R.id.textViewFinalScore);
        buttonStart = findViewById(R.id.buttonPlayGame);
        buttonAnswer1 = findViewById(R.id.buttonAnswer1);
        buttonAnswer2 = findViewById(R.id.buttonAnswer2);
        buttonAnswer3 = findViewById(R.id.buttonAnswer3);
        buttonAnswer4 = findViewById(R.id.buttonAnswer4);

        buttonStart.setOnClickListener(view -> {
            gameViews(true);
            startGame();
        });

        View.OnClickListener answerListener = this::checkAnswer;

        buttonAnswer1.setOnClickListener(answerListener);
        buttonAnswer2.setOnClickListener(answerListener);
        buttonAnswer3.setOnClickListener(answerListener);
        buttonAnswer4.setOnClickListener(answerListener);
    }

    private void startGame(){
        currentScore = 0; totalQuestions = 0;
        textViewScore.setText(getString(R.string.game_score, currentScore, totalQuestions));
        timerGame = startTimer(gameTime);
        playRound();
    }

    private void finishGame(){
        gameViews(false);
        buttonStart.setText(getString(R.string.play_again));
        textViewFinalScore.setText(getString(R.string.final_score, currentScore, totalQuestions));
    }

    private void playRound(){
        generateQuestionAnswers();
    }

    private void checkAnswer(View view){
        Button selectedAnswer = findViewById(view.getId());
        String answer = selectedAnswer.getText().toString();

        if(answer.equals(currentSolution)){
            currentScore++;
            ObjectAnimator.ofArgb(selectedAnswer, "backgroundColor",  getColor(R.color.gray), Color.GREEN).setDuration(750).start();
            ObjectAnimator.ofArgb(selectedAnswer, "backgroundColor",  Color.GREEN, getColor(R.color.gray)).setDuration(750).start();
        }
        else{
            ObjectAnimator.ofArgb(selectedAnswer, "backgroundColor",  getColor(R.color.gray), Color.RED).setDuration(750).start();
            ObjectAnimator.ofArgb(selectedAnswer, "backgroundColor", Color.RED, getColor(R.color.gray)).setDuration(750).start();
        }

        totalQuestions++;
        textViewScore.setText(getString(R.string.game_score, currentScore, totalQuestions));

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(this::playRound, 250);
    }

    private void generateQuestionAnswers(){
        List<String> answersViews = new ArrayList<>();
        answersViews.add("buttonAnswer1");answersViews.add("buttonAnswer2");
        answersViews.add("buttonAnswer3");answersViews.add("buttonAnswer4");

        int randomNumber1 = new Random().nextInt(capitals.size());
        int randomNumber2 = new Random().nextInt(capitals.size());
        if (randomNumber2 == randomNumber1)
        {
            while(randomNumber2 == randomNumber1)
            {
                randomNumber2 = new Random().nextInt(capitals.size());
            }
        }
        int randomNumber3 = new Random().nextInt(capitals.size());
        if (randomNumber3 == randomNumber2 || randomNumber3 == randomNumber1)
        {
            while (randomNumber3 == randomNumber2 || randomNumber3 == randomNumber1){
                randomNumber3 = new Random().nextInt(capitals.size());
            }
        }
        int randomNumber4 = new Random().nextInt(capitals.size());
        if (randomNumber4 == randomNumber3 || randomNumber4 == randomNumber2 || randomNumber4 == randomNumber1)
        {
            while (randomNumber4 == randomNumber3 || randomNumber4 == randomNumber2 || randomNumber4 == randomNumber1){
                randomNumber4 = new Random().nextInt(capitals.size());
            }
        }

        String[] allAnswers = {capitals.get(randomNumber1),capitals.get(randomNumber2),capitals.get(randomNumber3), capitals.get(randomNumber4)};
        currentSolution = allAnswers[0];
        currentQuestion = countries.get(randomNumber1);
        textViewQuestion.setText(getString(R.string.question, currentQuestion));

        for (String answer : allAnswers)
        {
            int answerPlacement = new Random().nextInt(answersViews.toArray().length);
            TextView setAnswer = findViewById(this.getResources().getIdentifier(answersViews.get(answerPlacement),"id",this.getPackageName()));
            setAnswer.setText(String.valueOf(answer));
            answersViews.remove(answerPlacement);
        }
    }

    private void gameViews(boolean visibility){
        textViewTime.setVisibility((visibility) ? View.VISIBLE : View.GONE);
        textViewScore.setVisibility((visibility) ? View.VISIBLE : View.GONE);
        textViewQuestion.setVisibility((visibility) ? View.VISIBLE : View.GONE);
        textViewFinalScore.setVisibility((visibility) ? View.GONE : View.VISIBLE);
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

    private void loadGameAnswers(){
        //Get random names for wiki page
        Toast.makeText(this,"Loading...",Toast.LENGTH_LONG).show();
        //Download HTML data from website
        DownloadTaskTextData task = new DownloadTaskTextData();
        String result;
        try {
            result = task.execute(linkDataSource).get();

            //<p>The capital of (.*) is ([^ ,.]*)</p>
            Pattern p = Pattern.compile("<p>The capital of ([^,.]*) is ([^,.<&(]*).");
            Matcher m = p.matcher(result);
            while (m.find()){
                //correcting some data
                if(Objects.requireNonNull(m.group(1)).contains("(AKA")) {
                    String aux_1 = m.group(1);
                    assert aux_1 != null;
                    String[] aux_2 = aux_1.split(" \\(AKA ");
                    countries.add(aux_2[0]);
                }
                else if(Objects.equals(m.group(1), "the Netherlands is Amsterdam although the Hague"))
                    countries.add("Netherlands");
                else
                    countries.add(m.group(1));

                //correcting some data
                if(Objects.equals(m.group(1), "Belgium"))
                    capitals.add("Brussels");
                else if (Objects.equals(m.group(1), "Chad"))
                    capitals.add("Djamena");
                else if (Objects.equals(m.group(1), "Cyprus"))
                    capitals.add("Nicosia");
                else if (Objects.equals(m.group(1), "Djibouti"))
                    capitals.add("Djibouti");
                else if(Objects.equals(m.group(1), "the Netherlands is Amsterdam although the Hague"))
                    capitals.add("Amsterdam");
                else if(Objects.equals(m.group(1), "Ethiopia"))
                    capitals.add("Addis Ababa");
                else if(Objects.equals(m.group(1), "Myanmar"))
                    capitals.add("Naypyidaw");
                else
                    capitals.add(m.group(2));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}