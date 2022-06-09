package com.fabioseixaslopes.layoutsandanimations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView homer = findViewById(R.id.imageViewHomer);
        homer.setVisibility(View.GONE);
        ImageView bart = findViewById(R.id.imageViewBart);
        animation(bart);
    }

    public void fade(View view){
        ImageView bart = findViewById(R.id.imageViewBart);
        bart.animate().alpha(0f).setDuration(2000);
    }

    public void crossfade (View view){
        ImageView bart = findViewById(R.id.imageViewBart);
        ImageView homer = findViewById(R.id.imageViewHomer);

        if (bart.getAlpha() < 1){
            bart.animate().alpha(1f).setDuration(3000);
            homer.animate().alpha(0f).setDuration(2000);
        }
        else{
            bart.animate().alpha(0f).setDuration(2000);
            homer.animate().alpha(1f).setDuration(3000);
        }
    }
    public void animation (View view){
        //this makes the image move (translation) while rotating (rotation) and change in size (scale)
        //we can make something appear if we create it outside the screen and move it inside the screen
        ImageView bart = findViewById(R.id.imageViewBart);
        //put image off screen
        bart.setTranslationX(1000f); bart.setTranslationY(-1000f);
        bart.setScaleX(0.5f); bart.setScaleY(0.5f);
        //bring it in
        bart.animate().translationYBy(1000f).translationXBy(-1000f).
            rotation(360f).scaleX(1f).scaleY(1f)
                .setDuration(3000);
    }
}