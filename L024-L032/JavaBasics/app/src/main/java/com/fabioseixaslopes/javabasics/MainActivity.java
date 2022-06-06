package com.fabioseixaslopes.javabasics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Views
        TextView helloWorld = findViewById(R.id.textViewHelloWorld);

        //HelloWorld class
        HelloWorld helloWorldClass = new HelloWorld();
        helloWorld.setText(helloWorldClass.main());

        //variables
        int favouriteNumber = 21; //whole number
        double favouriteNumber2 = 7.5; //double the memory of the float, float is a whole or decimal number
        //primitive types -> boolean, char, byte, short, int, long, float, double
        //other variable types are built on top of primitive types
        //primitive types start with lower case, non-primitive are capitalized because they are a class (ex. String)
        //variables have different size (check them before using) and occupy a different number of bytes
        //less bytes, less space, usually more efficiency
        boolean userLikesPizza = true;
        String myName = "Bob"; //a String is made of chars.
        String userName = "Marley";
        int userAge = 89;
        double numberA = 6.7, numberB = 3.2;
        //testing
        System.out.println("This is my favourite number: " + favouriteNumber);
        System.out.println("This is also my favourite number: " + favouriteNumber2);
        System.out.println("Do I like pizza? Most certainly " + userLikesPizza + ".");
        System.out.println("My name is " + myName + "!");
        System.out.println("The user name is " + userName + " and the user is " + userAge + " years old.");
        System.out.println("Lucky numbers are: " + (numberA*numberB) + ", " + (numberA+numberB) + ", " + (numberA-numberB) + ", "
                + (numberA/numberB) + ", " + (numberA%numberB));
        System.out.println("The size of this String is " + userName.length()); //length is a method, thus the ".()"
        System.out.println("The number of characters in my full name is " + (userName.length() + myName.length()));

        //arrays
        int[] primeNumbers = {2,3,5,7,11,13};//collection of integers
        System.out.println(primeNumbers[0] + " " + primeNumbers.length); //2 6
        //arrays does not allow to add or take away elements
        //for that we use lists
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(5);
        list.remove(2);//removes 5
        System.out.println(list.get(1));//3
        System.out.println(list);//[2,3]
        List<String> countries = new ArrayList<>();
        countries.add("Portugal"); countries.add("Spain"); countries.add("France");
        countries.remove(1);
        countries.add("Japan");
        System.out.println(countries);//[Portugal, France, Japan]
        //maps
        Map<String, String> map = new HashMap<>();
        map.put("Father","Rob");
        map.put("Mother","Kirsten");
        map.put("Son","John");
        map.remove("Son");
        System.out.println(map.get("Father"));//Rob
        System.out.println(map);//{Mother=Kirsten, Father=Rob}
        System.out.println(map.size());//2

        //if statements
        int gradeBob = 10;
        if (gradeBob >= 11) {
            System.out.println("Bob passed the test.");
        }
        else if (gradeBob == 10)
        {
            System.out.println("Wow, Bob was lucky.");
        }
        else {
            System.out.println("Bob did not passed the test.");
        }
        int[] numberArray = {5,7,7};
        int greatest = 0;
        if(numberArray[0] >= numberArray[1] && numberArray[0] >= numberArray[2])
            greatest = 1;
        else if (numberArray[1] >= numberArray[2])
            greatest = 2;
        else if (numberArray[2] >= numberArray[1])
            greatest = 3;
        System.out.println("The element number " + greatest + ", with value '" + numberArray[greatest-1] + "' is greater or equal to all others.");
    }
}