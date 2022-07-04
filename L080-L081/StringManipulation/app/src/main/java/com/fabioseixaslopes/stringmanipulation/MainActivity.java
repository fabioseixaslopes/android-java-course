package com.fabioseixaslopes.stringmanipulation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String names = "Rob x Kirsten x Tommy x Ralphie";
        String[] splitString = names.split(" x ");
        System.out.println(Arrays.toString(splitString));
        // [Rob, Kirsten, Tommy, Ralphie]

        String river = "Mississippi";
        splitString = river.split("s");
        System.out.println(Arrays.toString(splitString));
        // [Mi, , i, , ippi]
        // empty space are due to 2 consecutive 's'

        String riverPart = river.substring(2,5);
        System.out.println(riverPart);
        // ssi
        riverPart = river.substring(4,8);
        System.out.println(riverPart);
        // issi

        // regex (regular expression)
        String riverTwo = "MississippiMississippi";
        Pattern pattern = Pattern.compile("Mi(.*?)pi");
        Matcher matcher = pattern.matcher(riverTwo);
        while(matcher.find()){
            System.out.println(matcher.group(1));
            // ssissip
            // ssissip
        }

        // using a website bit
        String html = "<div class=\"footer-logo\"><a href=\"#header\"><img src=\"img/logo-footer.png\" alt=\"\" data-uk-smooth-scroll/></a></div>";
        pattern = Pattern.compile("<img src=\"(.*?)\"");
        matcher = pattern.matcher(html);
        while(matcher.find()){
            System.out.println(matcher.group(1));
        }
    }
}