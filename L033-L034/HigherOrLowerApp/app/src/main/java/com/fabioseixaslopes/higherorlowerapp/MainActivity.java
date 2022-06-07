package com.fabioseixaslopes.higherorlowerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int numberToGuess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextUserGuess = findViewById(R.id.editTextNumberGuess);
        Button buttonUserGuess = findViewById(R.id.buttonNumberGuess);
        numberToGuess = generateNewNumber();

        buttonUserGuess.setOnClickListener(view -> {
            int guessedNumber = Integer.parseInt(editTextUserGuess.getText().toString());
            String response;
            if (guessedNumber == numberToGuess){
                response = "You guessed the right number! Try again!";
                numberToGuess = generateNewNumber();
            }
            else if (guessedNumber < numberToGuess){
                response = "Higher!";
            }
            else{
                response = "Lower!";
            }
            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
        });
    }

    public int generateNewNumber(){
        Random random = new Random();
        return random.nextInt(20) + 1;
    }
}