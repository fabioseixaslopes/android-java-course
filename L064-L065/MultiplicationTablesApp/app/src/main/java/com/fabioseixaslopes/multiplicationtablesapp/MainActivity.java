package com.fabioseixaslopes.multiplicationtablesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> numbers;
    ArrayAdapter<Integer> arrayAdapter;
    ListView listView;
    TextView textViewValue;
    SeekBar seekBarMultiplication;
    int minValue = 1, maxValue = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                numbers);

        seekBarMultiplication = findViewById(R.id.seekBarMultiplication);
        seekBarMultiplication.setMax(maxValue);
        seekBarMultiplication.setMin(minValue);
        seekBarMultiplication.setProgress(minValue);

        textViewValue = findViewById(R.id.textViewSelectedNumber);

        updateList(minValue);

        seekBarMultiplication.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    updateList(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void updateList(int progress){
        textViewValue.setText(String.valueOf(progress));
        ArrayList<Integer> newNumbers = new ArrayList<>();
        for (int i = minValue; i <= maxValue; i++){
            newNumbers.add(i*progress);
        }
        arrayAdapter =  new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                newNumbers);
        listView.setAdapter(arrayAdapter);
    }
}