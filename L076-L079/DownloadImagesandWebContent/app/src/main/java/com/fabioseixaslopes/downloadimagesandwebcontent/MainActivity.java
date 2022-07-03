package com.fabioseixaslopes.downloadimagesandwebcontent;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    String testLink = "https://www.fabioseixaslopes.com";
    String testLink2 = "https://en.wikipedia.org/wiki/Association_football";
    String testLink3 = "https://static.wikia.nocookie.net/simpsons/images/6/65/Bart_Simpson.png/revision/latest?cb=20190409004756";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Download Image from link
        Button downloadImageButton = findViewById(R.id.buttonDownloadImage);
        ImageView imageViewDownloaded = findViewById(R.id.imageViewDownloaded);
        downloadImageButton.setOnClickListener(view -> {
            DownloadTaskImage downloadTask = new DownloadTaskImage();
            Bitmap image;
            try {
                image = downloadTask.execute(testLink3).get();
                imageViewDownloaded.setImageBitmap(image);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Download HTML data from website
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