package com.example.topspin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoringActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoring);

        //Left Score Edit Controls
        //Left Score Increment Button
        Button incrementButtonL = findViewById(R.id.incBtnL);
        incrementButtonL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView scoreEditLeft = findViewById(R.id.scoreEditL);
                String scoreEditLeftValue = scoreEditLeft.getText().toString();

                if (scoreEditLeftValue.equals("0"))
                {scoreEditLeft.setText(getString(R.string.ScoreFifteen));}
                if (scoreEditLeftValue.equals("15"))
                {scoreEditLeft.setText(getString(R.string.ScoreThirty));}
                if (scoreEditLeftValue.equals("30"))
                {scoreEditLeft.setText(getString(R.string.ScoreForty));}
                if (scoreEditLeftValue.equals("40"))
                {scoreEditLeft.setText(getString(R.string.ScoreAdvantage));}
                if (scoreEditLeftValue.equals("Adv"))
                {scoreEditLeft.setText(getString(R.string.ScoreWin));}
            }
        });

        //Left Score Decrement Button
        Button decrementButtonL = findViewById(R.id.decBtnL);
        decrementButtonL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView scoreEditLeft = findViewById(R.id.scoreEditL);
                String scoreEditLeftValue = scoreEditLeft.getText().toString();

                if (scoreEditLeftValue.equals("Win"))
                {scoreEditLeft.setText(getString(R.string.ScoreAdvantage));}
                if (scoreEditLeftValue.equals("Adv"))
                {scoreEditLeft.setText(getString(R.string.ScoreForty));}
                if (scoreEditLeftValue.equals("40"))
                {scoreEditLeft.setText(getString(R.string.ScoreThirty));}
                if (scoreEditLeftValue.equals("30"))
                {scoreEditLeft.setText(getString(R.string.ScoreFifteen));}
                if (scoreEditLeftValue.equals("15"))
                {scoreEditLeft.setText(getString(R.string.ScoreZero));}
            }
        });




        //Right Score Edit Controls
        //Right Score Increment Button
        Button incrementButtonR = findViewById(R.id.incBtnR);
        incrementButtonR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView scoreEditRight = findViewById(R.id.scoreEditR);
                String scoreEditRightValue = scoreEditRight.getText().toString();

                if (scoreEditRightValue.equals("0"))
                {scoreEditRight.setText(getString(R.string.ScoreFifteen));}
                if (scoreEditRightValue.equals("15"))
                {scoreEditRight.setText(getString(R.string.ScoreThirty));}
                if (scoreEditRightValue.equals("30"))
                {scoreEditRight.setText(getString(R.string.ScoreForty));}
                if (scoreEditRightValue.equals("40"))
                {scoreEditRight.setText(getString(R.string.ScoreAdvantage));}
                if (scoreEditRightValue.equals("Adv"))
                {scoreEditRight.setText(getString(R.string.ScoreWin));}
            }
        });

        //Right Score Decrement Button
        Button decrementButtonR = findViewById(R.id.decBtnR);
        decrementButtonR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView scoreEditRight = findViewById(R.id.scoreEditR);
                String scoreEditRightValue = scoreEditRight.getText().toString();

                if (scoreEditRightValue.equals("Win"))
                {scoreEditRight.setText(getString(R.string.ScoreAdvantage));}
                if (scoreEditRightValue.equals("Adv"))
                {scoreEditRight.setText(getString(R.string.ScoreForty));}
                if (scoreEditRightValue.equals("40"))
                {scoreEditRight.setText(getString(R.string.ScoreThirty));}
                if (scoreEditRightValue.equals("30"))
                {scoreEditRight.setText(getString(R.string.ScoreFifteen));}
                if (scoreEditRightValue.equals("15"))
                {scoreEditRight.setText(getString(R.string.ScoreZero));}
            }
        });



        //Major Button Controls
        //Submit Button
        Button submitButton = findViewById(R.id.submitBtn);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView scoreCurrentLeft = findViewById(R.id.scoreCurrentL);
                TextView scoreEditLeft = findViewById(R.id.scoreEditL);
                String scoreNewLeftValue = scoreEditLeft.getText().toString();

                TextView scoreCurrentRight = findViewById(R.id.scoreCurrentR);
                TextView scoreEditRight = findViewById(R.id.scoreEditR);
                String scoreNewRightValue = scoreEditRight.getText().toString();

                scoreCurrentLeft.setText(scoreNewLeftValue);
                scoreCurrentRight.setText(scoreNewRightValue);

                TextView scoreSetOneLeft = findViewById(R.id.scoreMatchL1);
                TextView scoreSetTwoLeft = findViewById(R.id.scoreMatchL2);
                TextView scoreSetThreeLeft = findViewById(R.id.scoreMatchL3);

                int scoreSetOneLeftValue = Integer.parseInt(scoreSetOneLeft.getText().toString());
                int scoreSetTwoLeftValue = Integer.parseInt(scoreSetTwoLeft.getText().toString());
                int scoreSetThreeLeftValue = Integer.parseInt(scoreSetThreeLeft.getText().toString());

                TextView scoreSetOneRight = findViewById(R.id.scoreMatchR1);
                TextView scoreSetTwoRight = findViewById(R.id.scoreMatchR2);
                TextView scoreSetThreeRight = findViewById(R.id.scoreMatchR3);

                int scoreSetOneRightValue = Integer.parseInt(scoreSetOneRight.getText().toString());
                int scoreSetTwoRightValue = Integer.parseInt(scoreSetTwoRight.getText().toString());
                int scoreSetThreeRightValue = Integer.parseInt(scoreSetThreeRight.getText().toString());
                //String scoreSetThreeRightString = scoreSetThreeRight.getText().toString();

                TextView currentSetLeft;
                TextView currentSetRight;

                int currentSetLeftValue;
                int currentSetRightValue;

                if ( (scoreSetOneLeftValue < 6 && scoreSetOneRightValue < 6) ||
                        Math.abs(scoreSetOneLeftValue - scoreSetOneRightValue) < 2) {
                    currentSetLeft = scoreSetOneLeft;
                    currentSetRight = scoreSetOneRight;
                    currentSetLeftValue = scoreSetOneLeftValue;
                    currentSetRightValue = scoreSetOneRightValue;
                }
                else if ( (scoreSetTwoLeftValue < 6 && scoreSetTwoRightValue < 6) ||
                        Math.abs(scoreSetTwoLeftValue - scoreSetTwoRightValue) < 2) {
                    currentSetLeft = scoreSetTwoLeft;
                    currentSetRight = scoreSetTwoRight;
                    currentSetLeftValue = scoreSetTwoLeftValue;
                    currentSetRightValue = scoreSetTwoRightValue;
                }
                else {
                    currentSetLeft = scoreSetThreeLeft;
                    currentSetRight = scoreSetThreeRight;
                    currentSetLeftValue = scoreSetThreeLeftValue;
                    currentSetRightValue = scoreSetThreeRightValue;
                }

                if (scoreNewLeftValue.equals((getString(R.string.ScoreZero))) &&
                        scoreNewRightValue.equals((getString(R.string.ScoreZero)))) {
                    scoreEditLeft.setText(getString(R.string.ScoreZero));
                    scoreEditRight.setText(getString(R.string.ScoreZero));
                }
                else if (scoreNewLeftValue.equals(getString(R.string.ScoreWin))) {
                    int newSetScoreInt = currentSetLeftValue + 1;
                    String newSetScoreString = newSetScoreInt + "";
                    currentSetLeft.setText(newSetScoreString);
                    scoreEditLeft.setText(getString(R.string.ScoreZero));
                    scoreEditRight.setText(getString(R.string.ScoreZero));
                }
                else if (scoreNewRightValue.equals(getString(R.string.ScoreWin))) {
                    int newSetScoreInt = currentSetRightValue + 1;
                    String newSetScoreString = newSetScoreInt + "";
                    currentSetRight.setText(newSetScoreString);
                    scoreEditLeft.setText(getString(R.string.ScoreZero));
                    scoreEditRight.setText(getString(R.string.ScoreZero));
                }
            }
        });


        //Cancel Button
        Button cancelButton = findViewById(R.id.cancelBtn);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView scoreCurrentLeft = findViewById(R.id.scoreCurrentL);
                TextView scoreEditLeft = findViewById(R.id.scoreEditL);
                String scoreCurrentLeftValue = scoreCurrentLeft.getText().toString();

                TextView scoreCurrentRight = findViewById(R.id.scoreCurrentR);
                TextView scoreEditRight = findViewById(R.id.scoreEditR);
                String scoreCurrentRightValue = scoreCurrentRight.getText().toString();

                scoreEditLeft.setText(scoreCurrentLeftValue);
                scoreEditRight.setText(scoreCurrentRightValue);
            }
        });
    }
}
