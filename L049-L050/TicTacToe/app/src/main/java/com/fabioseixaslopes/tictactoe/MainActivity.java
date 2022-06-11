package com.fabioseixaslopes.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageViewPosition11, imageViewPosition12, imageViewPosition13,
    imageViewPosition21, imageViewPosition22, imageViewPosition23,
    imageViewPosition31, imageViewPosition32, imageViewPosition33;
    int[][] gameBoard = new int[3][3]; //0 empty, 1 player1, 2 player2
    boolean nextPlayerToPlay = true; //false player2, true player1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setImageViews();
        resetBoardViews();
        resetBoardMatrix();
        setClickListeners();
    }

    private void playerWon(int player){
        //TODO
        //TODO show messages & reset game
    }

    private void checkIfPlayerWon(int player){
        //horizontal
        if (gameBoard[0][0] != 0 && gameBoard[0][0] == gameBoard[0][1] && gameBoard[0][1] == gameBoard[0][2])
            playerWon(player);
        if (gameBoard[1][0] != 0 && gameBoard[1][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[1][2])
            playerWon(player);
        if (gameBoard[2][0] != 0 && gameBoard[2][0] == gameBoard[2][1] && gameBoard[2][1] == gameBoard[2][2])
            playerWon(player);
        //vertical
        //TODO
        //crosses
        //TODO
    }

    private void userPlay(ImageView position, int positionX, int positionY){
        if(nextPlayerToPlay){
            position.setImageResource(R.drawable.cross);
            position.animate().alpha(1f).scaleX(0.8f).scaleY(0.8f).setDuration(1500);
            gameBoard[positionX][positionY] = 1;
            nextPlayerToPlay = false;
            checkIfPlayerWon(1);
        }
        else{
            position.setImageResource(R.drawable.circle);
            position.animate().alpha(1f).scaleX(1.2f).scaleY(1.2f).setDuration(1500);
            gameBoard[positionX][positionY] = 2;
            nextPlayerToPlay = true;
            checkIfPlayerWon(2);
        }
    }

    private void setClickListeners(){
        imageViewPosition11.setOnClickListener(view -> userPlay(imageViewPosition11, 0, 0));
        imageViewPosition12.setOnClickListener(view -> userPlay(imageViewPosition12, 0, 1));
        imageViewPosition13.setOnClickListener(view -> userPlay(imageViewPosition13, 0, 2));
        imageViewPosition21.setOnClickListener(view -> userPlay(imageViewPosition21, 1, 0));
        imageViewPosition22.setOnClickListener(view -> userPlay(imageViewPosition22, 1, 1));
        imageViewPosition23.setOnClickListener(view -> userPlay(imageViewPosition23, 1, 2));
        imageViewPosition31.setOnClickListener(view -> userPlay(imageViewPosition31, 2, 0));
        imageViewPosition32.setOnClickListener(view -> userPlay(imageViewPosition32, 2, 1));
        imageViewPosition33.setOnClickListener(view -> userPlay(imageViewPosition33, 2, 2));
    }

    private void resetBoardMatrix(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameBoard[i][j] = 0;
            }
        }
    }

    private void resetBoardViews(){
        imageViewPosition11.setAlpha(0f);
        imageViewPosition12.setAlpha(0f);
        imageViewPosition13.setAlpha(0f);
        imageViewPosition21.setAlpha(0f);
        imageViewPosition22.setAlpha(0f);
        imageViewPosition23.setAlpha(0f);
        imageViewPosition31.setAlpha(0f);
        imageViewPosition32.setAlpha(0f);
        imageViewPosition33.setAlpha(0f);
    }

    private void setImageViews(){
        imageViewPosition11 = findViewById(R.id.imageViewPosition11);
        imageViewPosition12 = findViewById(R.id.imageViewPosition12);
        imageViewPosition13 = findViewById(R.id.imageViewPosition13);
        imageViewPosition21 = findViewById(R.id.imageViewPosition21);
        imageViewPosition22 = findViewById(R.id.imageViewPosition22);
        imageViewPosition23 = findViewById(R.id.imageViewPosition23);
        imageViewPosition31 = findViewById(R.id.imageViewPosition31);
        imageViewPosition32 = findViewById(R.id.imageViewPosition32);
        imageViewPosition33 = findViewById(R.id.imageViewPosition33);
    }
}