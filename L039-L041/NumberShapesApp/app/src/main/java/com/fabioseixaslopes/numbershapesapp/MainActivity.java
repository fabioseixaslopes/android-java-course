package com.fabioseixaslopes.numbershapesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonCheck = findViewById(R.id.buttonCheck);
        EditText editTextNumber = findViewById(R.id.editTextNumber);

        buttonCheck.setOnClickListener(view -> {
            if (!editTextNumber.getText().toString().isEmpty())
            {
                Number number = new Number();
                number.value = Integer.parseInt(editTextNumber.getText().toString());
                String message;
                if (number.isSquare() && number.isTriangular())
                    message = "The number is square and triangular!";
                else if (number.isSquare())
                    message = "The number is square.";
                else if (number.isTriangular())
                    message = "The number is triangular.";
                else
                    message = "The number is not square nor triangular";
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
            }
        });
    }
}