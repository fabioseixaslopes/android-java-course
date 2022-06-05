package com.fabioseixaslopes.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void convertToEur(View view){
        EditText editTextUSD = findViewById(R.id.editTextUsd);
        if (!editTextUSD.getText().toString().equals("")){
            double usdValue = Double.parseDouble(editTextUSD.getText().toString());
            EditText editTextExchangeRate = findViewById(R.id.editTextExchangeRate);
            double exchangeRate = Double.parseDouble(editTextExchangeRate.getText().toString());
            double eurValue = usdValue * exchangeRate;
            Toast.makeText(this, String.format(Locale.getDefault(),"%.2f", eurValue) + " €",Toast.LENGTH_LONG).show();
        }
    }
}