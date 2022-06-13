package com.fabioseixaslopes.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView imageViewPosition11, imageViewPosition12, imageViewPosition13,
    imageViewPosition21, imageViewPosition22, imageViewPosition23,
    imageViewPosition31, imageViewPosition32, imageViewPosition33;
    LinearLayout layoutPlayAgain;
    int[][] gameBoard = new int[3][3];
    int noPlayer = 0, playerOne = 1, playerTwo = 2;
    boolean nextPlayerToPlay = true; //false player2, true player1
    long timeToAnimate = 250;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutPlayAgain = findViewById(R.id.layoutPlayAgain);
        Button buttonPlayerWon = findViewById(R.id.buttonPlayAgain);
        buttonPlayerWon.setOnClickListener(view -> {
            layoutPlayAgain.setVisibility(View.GONE);
            try {
                Thread.sleep(timeToAnimate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resetBoardViews();
            resetBoardMatrix();
            setClickListeners(true);
        });
        layoutPlayAgain.setVisibility(View.GONE);

        setImageViews();
        resetBoardViews();
        resetBoardMatrix();
        setClickListeners(true);
    }

    private void endGame(int player){
        setClickListeners(false);
        layoutPlayAgain.setVisibility(View.VISIBLE);
        TextView textViewGameEnd = findViewById(R.id.textViewGameEnd);
        if(player == 0)
           textViewGameEnd.setText(R.string.game_draw);
        else
            textViewGameEnd.setText(getString(R.string.player_won, player));
    }

    private void checkIfBoardFull(){
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(gameBoard[i][j] != 0)
                    counter++;
            }
        }
        if (counter == 9){
            endGame(0);
        }
    }

    private void checkIfPlayerWon(int player){
        //horizontal
        if (gameBoard[0][0] != noPlayer && gameBoard[0][0] == gameBoard[0][1] && gameBoard[0][1] == gameBoard[0][2])
            endGame(player);
        else if (gameBoard[1][0] != noPlayer && gameBoard[1][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[1][2])
            endGame(player);
        else if (gameBoard[2][0] != noPlayer && gameBoard[2][0] == gameBoard[2][1] && gameBoard[2][1] == gameBoard[2][2])
            endGame(player);
        //vertical
        else if (gameBoard [0][0] != noPlayer & gameBoard[0][0] == gameBoard[1][0] && gameBoard[1][0] == gameBoard[2][0])
            endGame(player);
        else if (gameBoard [0][1] != noPlayer & gameBoard[0][1] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][1])
            endGame(player);
        else if (gameBoard [0][2] != noPlayer & gameBoard[0][2] == gameBoard[1][2] && gameBoard[1][2] == gameBoard[2][2])
            endGame(player);
        //crosses
        else if (gameBoard [0][0] != noPlayer & gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2])
            endGame(player);
        else if (gameBoard[2][0] != noPlayer & gameBoard[0][2] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][0])
            endGame(player);
        else{
            checkIfBoardFull();
        }
    }

    private void userPlay(ImageView position, int positionX, int positionY){
        if(gameBoard[positionX][positionY] == 0){
            if(nextPlayerToPlay){
                position.setImageResource(R.drawable.cross);
                position.animate().alpha(1f).scaleX(0.8f).scaleY(0.8f).setDuration(timeToAnimate);
                gameBoard[positionX][positionY] = playerOne;
                nextPlayerToPlay = false;
                checkIfPlayerWon(playerOne);
            }
            else{
                position.setImageResource(R.drawable.circle);
                position.animate().alpha(1f).scaleX(1.2f).scaleY(1.2f).setDuration(timeToAnimate);
                gameBoard[positionX][positionY] = playerTwo;
                nextPlayerToPlay = true;
                checkIfPlayerWon(playerTwo);
            }
        }
    }

    private void setClickListeners(boolean activate){
        if(activate){
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
        else{
            imageViewPosition11.setOnClickListener(null);
            imageViewPosition12.setOnClickListener(null);
            imageViewPosition13.setOnClickListener(null);
            imageViewPosition21.setOnClickListener(null);
            imageViewPosition22.setOnClickListener(null);
            imageViewPosition23.setOnClickListener(null);
            imageViewPosition31.setOnClickListener(null);
            imageViewPosition32.setOnClickListener(null);
            imageViewPosition33.setOnClickListener(null);
        }


    }

    private void resetBoardMatrix(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameBoard[i][j] = noPlayer;
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