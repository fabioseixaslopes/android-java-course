package com.fabioseixaslopes.capitalsoftheworldquizapp;

import java.util.ArrayList;

public class Testing {

    protected void testCountryCapitalImageAssociation(ArrayList<String> countries, ArrayList<String> capitals, ArrayList<String> imageLinks){
        System.out.println(countries.size() + " " + capitals.size() + " " + imageLinks.size());
        for (int i=0;i<capitals.size();i++){
            System.out.println(countries.get(i));
            System.out.println(capitals.get(i));
            System.out.println(imageLinks.get(i));
        }
    }
}
