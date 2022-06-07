package com.fabioseixaslopes.javabasics2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //while loop
        int x = 1;
        while (x <= 5){
            System.out.println(2*x);
            x++;
        }

        //for loop
        for(int i = 1; i<=5; i++){
            System.out.println(2*i);
        }

        //reversed for loop
        for(int y = 5; y > 0; y--){
            System.out.println(2*y);
        }

        //10 first multiples of 3
        for(int a = 1; a <= 10; a++){
            System.out.println(3*a);
        }

        //first 10 triangular numbers with for loop
        int n = 10;
        for (int z = 1; z <= n; z++){
            System.out.println(z*(z+1)/2);
        }
        //first 10 triangular numbers with while loop
        int m = 1;
        while(m <= 10){
            System.out.println(m*(m+1)/2);
            m++;
        }

        //for each loop with Array
        String[] family = {"Tony", "Edna", "Tommy", "John", "Layla"};
        for (String name : family){
            System.out.println(name); //prints each member
        }
        System.out.println(Arrays.toString(family)); //prints all members
        //for each loop with ArrayList
        List<String> familyList = new ArrayList<>();
        //<String> specifies the type of ArrayList
        //otherwise for each with Strings would not work
        //because the predefined type is Object
        familyList.add("Tony"); familyList.add("Edna"); familyList.add("Ralph");
        for (String name : familyList){
            System.out.println(name); //each one of them
        }
        System.out.println(familyList);
        //[Tony, Edna, Ralph]

        class User{
            //classes are capitalized
            //classes have methods and variables
            int score;
            public void hasWon(){
                if (score>=100) {
                    System.out.println("Bob has won.");
                }
                else {
                    System.out.println("Bob has not won.");
                }
            }
        }

        User bob = new User();
        bob.score = 10;
        System.out.println("Bob score is: " + bob.score);
        bob.hasWon();

        class Number{
            int number;
            public void isPositive(){
                if (number>0)
                {
                    System.out.println("Number is positive.");
                }
                else if (number<0)
                {
                    System.out.println("Number is negative.");
                }
                else
                {
                    System.out.println("Number is neither positive or negative.");
                }
            }
        }

        Number number = new Number();
        number.number = 0;
        number.isPositive();
    }
}