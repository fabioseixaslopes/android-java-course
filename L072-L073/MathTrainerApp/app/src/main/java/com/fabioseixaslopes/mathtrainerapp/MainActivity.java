package com.fabioseixaslopes.mathtrainerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textViewGo, textViewTimer, textViewFeedback, textViewScore, textViewQuestions;
    TextView textViewAnswer1, textViewAnswer2, textViewAnswer3, textViewAnswer4;
    LinearLayout layoutGame;
    Button buttonPlayAgain;
    CountDownTimer timerGame;
    int gameTime = 30, maxValue = 99;
    int numberOfOperators = 3; //1 is only + operations; 2 is +,-; 3 is +,-,*; 4 is +,-,*,/
    int totalQuestions, currentScore, currentSolution;
    boolean gameIsRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewAnswer1 = findViewById(R.id.textViewAnswer1);
        textViewAnswer2 = findViewById(R.id.textViewAnswer2);
        textViewAnswer3 = findViewById(R.id.textViewAnswer3);
        textViewAnswer4 = findViewById(R.id.textViewAnswer4);
        textViewQuestions = findViewById(R.id.textViewQuestion);

        textViewGo = findViewById(R.id.textViewGo);
        textViewGo.setVisibility(View.VISIBLE);
        textViewTimer = findViewById(R.id.textViewTime);
        textViewFeedback = findViewById(R.id.textViewFeedback);
        textViewScore = findViewById(R.id.textViewScore);

        layoutGame = findViewById(R.id.layoutGame);
        layoutGame.setVisibility(View.GONE);

        buttonPlayAgain = findViewById(R.id.buttonPlayAgain);
        buttonPlayAgain.setVisibility(View.GONE);

        textViewGo.setOnClickListener(view -> {
            textViewGo.setVisibility(View.GONE);
            layoutGame.setVisibility(View.VISIBLE);
            startGame();
        });

        buttonPlayAgain.setOnClickListener(view -> startGame());

        View.OnClickListener answerListener = view -> {
            if(gameIsRunning){
                checkAnswer(view);
                playRound();
            }
        };
        textViewAnswer1.setOnClickListener(answerListener);
        textViewAnswer2.setOnClickListener(answerListener);
        textViewAnswer3.setOnClickListener(answerListener);
        textViewAnswer4.setOnClickListener(answerListener);
    }

    private void startGame(){
        buttonPlayAgain.setVisibility(View.GONE);
        gameIsRunning = true;
        totalQuestions = 0;
        currentScore = 0;
        timerGame = startTimer(gameTime);
        textViewScore.setText(getString(R.string.score,currentScore,totalQuestions));

        playRound();
    }

    private void finishGame(){
        buttonPlayAgain.setVisibility(View.VISIBLE);
        textViewFeedback.setText(getString(R.string.final_message, currentScore, totalQuestions));
        gameIsRunning = false;
    }

    private void checkAnswer(View view){
        TextView selectedAnswer = findViewById(view.getId());
        int answer = Integer.parseInt(selectedAnswer.getText().toString());

        if(answer == currentSolution){
            currentScore++;
            textViewFeedback.setText(getString(R.string.correct_answer));
        }
        else
            textViewFeedback.setText(getString(R.string.wrong_answer));

        totalQuestions++;
        textViewScore.setText(getString(R.string.score,currentScore,totalQuestions));
    }

    private void playRound(){
        int randomNumber1 = new Random().nextInt(maxValue);
        int randomNumber2 = new Random().nextInt(maxValue);
        int randomOperator = new Random().nextInt(numberOfOperators);

        currentSolution = calculator(randomNumber1,randomNumber2,randomOperator);

        textViewQuestions.setText(getString(R.string.question,randomNumber1, getOperatorString(randomOperator), randomNumber2));
        generateAnswers(currentSolution);
    }

    private int calculator(int number1, int number2, int operator){
        switch (operator) {
            case 0:
                return number1 + number2;
            case 1:
                return number1 - number2;
            case 2:
                return number1 * number2;
            default:
                return number1 / number2; // integer division
        }
    }

    private String getOperatorString(int number){
        if (number == 0)
            return "+";
        else if (number == 1)
            return "-";
        else if (number == 2)
            return "x";
        else if (number == 3)
            return "/";
        else
            return null;
    }

    private void generateAnswers(int solution){
        List<String> answersViews = new ArrayList<>();
        answersViews.add("textViewAnswer1");answersViews.add("textViewAnswer2");
        answersViews.add("textViewAnswer3");answersViews.add("textViewAnswer4");

        int fakeAnswer1 = solution + new Random().nextInt(maxValue);
        int fakeAnswer2 = solution - new Random().nextInt(maxValue);
        int fakeAnswer3 = solution + new Random().nextInt(maxValue) - new Random().nextInt(maxValue);

        if(fakeAnswer1 == solution)
            fakeAnswer1++;
        if(fakeAnswer2 == solution)
            fakeAnswer1++;
        if(fakeAnswer3 == solution)
            fakeAnswer1++;

        int[] allAnswers = {fakeAnswer1,fakeAnswer2,fakeAnswer3, solution};

        for (int answer : allAnswers)
        {
            int answerPlacement = new Random().nextInt(answersViews.toArray().length);
            TextView setAnswer = findViewById(this.getResources().getIdentifier(answersViews.get(answerPlacement),"id",this.getPackageName()));
            setAnswer.setText(String.valueOf(answer));
            answersViews.remove(answerPlacement);
        }
    }

    private CountDownTimer startTimer(int time){
        return new CountDownTimer(time* 1000L +1000,1000){
            @Override
            public void onTick(long millisecondsUntilDone) {
                textViewTimer.setText(getString(R.string.game_time,millisecondsUntilDone/1000));
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