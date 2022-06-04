package com.fabioseixaslopes.introduction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Boolean pet = true; // dog -> true, cat -> false

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickFunction(View view){
        //Toast appears on the screen, temporarily
        Toast.makeText(this, "Hello, Fábio!", Toast.LENGTH_SHORT).show();

        //both sout and log appear on "Run" @ Android Studio
        System.out.println("Console Message from " + getString(R.string.app_name) + ": Button was clicked.");
        Log.i("Info", "Log Message from " + getString(R.string.app_name) + ": Button was clicked.");
    }

    public void loginButton(View view){
        //working with EditText
        EditText name = findViewById(R.id.editTextName);
        EditText password = findViewById(R.id.editTextPassword);
        Toast.makeText(this, "Hello, " + name.getText().toString() + "!", Toast.LENGTH_LONG).show();
        System.out.println("Password for " + name.getText().toString() + " is " + password.getText().toString() + ".");
        Log.i("Username", name.getText().toString());
        Log.i("Password", password.getText().toString());
    }

    public void changePet(View view){
        ImageView petImage = findViewById(R.id.imageViewPets);
        if (pet)
        {
            petImage.setImageResource(R.drawable.cat);
            pet = false;
        }
        else {
            petImage.setImageResource(R.drawable.dog);
            pet = true;
        }
    }
}