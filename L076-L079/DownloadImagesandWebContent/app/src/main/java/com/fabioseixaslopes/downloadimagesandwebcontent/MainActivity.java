package com.fabioseixaslopes.downloadimagesandwebcontent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    String testLink = "https://www.fabioseixaslopes.com";
    String testLink2 = "https://en.wikipedia.org/wiki/Association_football";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Download HTML data from website
        DownloadTaskTextData downloadTaskTextData = new DownloadTaskTextData();
        String result = null;

        try {
            result = downloadTaskTextData.execute(testLink2).get();
            //with http it does
            //java.io.IOException: Cleartext HTTP traffic to www.fabioseixaslopes.com not permitted
            //had to use https
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Content of URL: " + result);
    }
}