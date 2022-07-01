package com.fabioseixaslopes.tryandcatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] array = new int[3]; //room for 3 numbers

        try{
            for(int i=0;i<4;i++){ //try to populate it with 4 numbers -> Crash
                array[i] = i;
            }
        }
        catch (ArrayIndexOutOfBoundsException e){//Catch specific exception
            System.out.println("Got index out of bounds.");
        }
        catch (Exception e) //Catch any exception
        {
            System.out.println("Got an error: " + e);
        }
        // still shows array with first 3 numbers
        System.out.println(Arrays.toString(array));
    }
}