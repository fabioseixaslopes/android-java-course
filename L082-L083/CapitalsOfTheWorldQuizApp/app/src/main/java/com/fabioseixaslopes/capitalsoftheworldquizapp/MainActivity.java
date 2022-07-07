package com.fabioseixaslopes.capitalsoftheworldquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    String currentSolution, currentQuestion;
    ArrayList<String> countries = new ArrayList<>(), capitals = new ArrayList<>(), imageLinks = new ArrayList<>();
    TextView textViewTime, textViewScore, textViewQuestion, textViewFinalScore;
    Button buttonStart, buttonAnswer1, buttonAnswer2, buttonAnswer3, buttonAnswer4;
    ImageView imageViewQuestion;
    LinearLayout layoutImage, layoutGameInfo;
    int gameTime = 60; CountDownTimer timerGame;
    int currentScore = 0, totalQuestions = 0;
    List<Integer> uniqueQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        loadGameData();
        gameViews(false);

        Testing testing = new Testing();
        testing.testCountryCapitalImageAssociation(countries, capitals, imageLinks);
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
        layoutImage = findViewById(R.id.layoutImage);
        layoutGameInfo = findViewById(R.id.layoutGameInfo);
        imageViewQuestion = findViewById(R.id.imageViewQuestion);

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
        uniqueQuestions = setUniqueQuestionsArray();
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

    private List<Integer> setUniqueQuestionsArray(){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < capitals.size(); i++){
            list.set(i, i);
        }
        return list;
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

        // generate random answers and ensure none is the same
        int randomNumber1 = new Random().nextInt(capitals.size());
        // ensure each quizz has unique questions
        while (uniqueQuestions.get(randomNumber1) == 0){
            randomNumber1 = new Random().nextInt(capitals.size());
        }
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
        downloadImage(imageLinks.get(randomNumber1));
        uniqueQuestions.set(randomNumber1, 0);


        for (String answer : allAnswers)
        {
            int answerPlacement = new Random().nextInt(answersViews.toArray().length);
            TextView setAnswer = findViewById(this.getResources().getIdentifier(answersViews.get(answerPlacement),"id",this.getPackageName()));
            setAnswer.setText(String.valueOf(answer));
            answersViews.remove(answerPlacement);
        }
    }

    private void downloadImage(String linkDataSource){
        DownloadTaskImage downloadTaskImage = new DownloadTaskImage();
        Bitmap image;
        try {
            image = downloadTaskImage.execute(linkDataSource).get();
            imageViewQuestion.setImageBitmap(image);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void gameViews(boolean visibility){
        layoutGameInfo.setVisibility((visibility) ? View.VISIBLE : View.GONE);
        layoutImage.setVisibility((visibility) ? View.VISIBLE : View.GONE);
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
            }
            @Override
            public void onFinish() {
                finishGame();
            }
        }.start();
    }

    private void loadGameData() {
        //Get random names for wiki page
        Toast.makeText(this, "Loading...", Toast.LENGTH_LONG).show();
        //Download HTML data from website
        DownloadTaskTextData downloadTaskTextData = new DownloadTaskTextData();
        String result;
        String linkDataSource = "https://happytowander.com/world-capitals/";
        try {
            result = downloadTaskTextData.execute(linkDataSource).get();

            String[] noImage = {"Bangui", "Brazzaville", "Kinshasa", "Libreville", "Kingston",
                    "Maseru", "Monrovia", "Lilongwe", "Palikir", "Naypyidaw", "No official capital - de facto capital is Yaren",
                    "Sri Jayewardenepura Kotte", "Dodoma", "Lusaka", "Skopje", "Monaco (City State)", "Harare"};

            Pattern p = Pattern.compile("<td class=\"column-1\">(.*?)</td><td class=\"column-2\">(.*?)</td>");
            Matcher m = p.matcher(result);

            while (m.find()) {

                // remove capitals without image
                boolean ignoreCapital = false;
                for (String capital : noImage){
                    if(Objects.equals(m.group(2), capital))
                        ignoreCapital = true;
                }

                //if it has image
                if (!ignoreCapital){
                    // add to arrays
                    countries.add(m.group(1));
                    capitals.add(m.group(2));

                    // add capitals with image not picked up by regex
                    if(Objects.equals(m.group(2), "Tirana")){
                        countries.add("Algeria");
                        capitals.add("Algiers");
                    }
                    if(Objects.equals(m.group(2), "Thimphu")){
                        countries.add("Bolivia");
                        capitals.add("La Paz");
                    }
                    if(Objects.equals(m.group(2), "San Jose")){
                        countries.add("Cote d'Ivoire");
                        capitals.add("Yamoussoukro");
                    }
                    if(Objects.equals(m.group(2), "Tashkent")){
                        countries.add("Vanuatu");
                        capitals.add("Port-Vila");
                    }
                    if(Objects.equals(m.group(2), "Abuja")){
                        countries.add("Macedonia");
                        capitals.add("Skopje");
                    }
                    if(Objects.equals(m.group(2), "Mogadishu")){
                        countries.add("South Africa");
                        capitals.add("Pretoria");
                    }
                    if(Objects.equals(m.group(2), "London")) {
                        countries.add("United States of America");
                        capitals.add("Washington D.C.");
                    }
                }
            }

            // remove empty table spaces at the end
            countries.remove(countries.size()-1);countries.remove(countries.size()-1);countries.remove(countries.size()-1);
            capitals.remove(capitals.size()-1);capitals.remove(capitals.size()-1);capitals.remove(capitals.size()-1);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        DownloadTaskTextData downloadTaskTextDataImage = new DownloadTaskTextData();

        try {
            result = downloadTaskTextDataImage.execute(linkDataSource).get();
            Pattern pImage = Pattern.compile("data-lazy-srcset=\"([^ ]*)");
            Matcher mImage = pImage.matcher(result);
            while(mImage.find()){
                imageLinks.add(mImage.group(1));
            }
            // remove unwanted images
            imageLinks.remove(0);
            imageLinks.remove(imageLinks.size()-1);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}