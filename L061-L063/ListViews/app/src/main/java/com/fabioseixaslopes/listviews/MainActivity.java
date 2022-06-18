package com.fabioseixaslopes.listviews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);

        ArrayList<String> players = new ArrayList<>(asList("Ronaldo","Ronaldinho","Maradona","Eusebio"));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                players);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener((adapterView, item, position, l) -> Toast.makeText(getApplicationContext(), "Hello " + players.get(position) + "!",Toast.LENGTH_SHORT).show());
    }
}