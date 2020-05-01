package com.example.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0= zero, 1= cross

    int actionPlayer = 0;

    boolean gameIsActive = true;

    // 2 means unplayed

    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && gameIsActive) {

            gameState[tappedCounter] = actionPlayer;
            counter.setTranslationY(-1000f);

            if(actionPlayer==0) {

                counter.setImageResource(R.drawable.zero);
                actionPlayer = 1;

            }
            else {

                counter.setImageResource(R.drawable.cross);
                actionPlayer = 0;

            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
            for(int[] winningPosition : winningPositions) {

                if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                   gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                   gameState[winningPosition[0]]!=2) {

                    // Someone has won;
                    gameIsActive = false;
                    String winner = "cross";
                    if (gameState[winningPosition[0]] == 0) {
                        winner = "zero";
                    }

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner + " has won!");
                    LinearLayout Layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    Layout.setVisibility(View.VISIBLE);
                }
                else {

                    boolean gameIsOver = true;
                    for (int counterState:gameState) {

                        if (counterState == 2) gameIsOver = false;

                    }
                    if (gameIsOver) {
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText("It's a Draw!");
                        LinearLayout Layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        Layout.setVisibility(View.VISIBLE);
                    }

                }

            }

        }
    }

    public void playAgain(View view) {

        gameIsActive = true;
        LinearLayout Layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        Layout.setVisibility(View.INVISIBLE);

        actionPlayer = 0;
        for (int i=0; i<gameState.length; i++) {

            gameState[i] = 2;

        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i=0; i<gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
