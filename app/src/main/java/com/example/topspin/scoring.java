package com.example.topspin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class scoring extends AppCompatActivity {

    TextView home_score;
    TextView away_score;
    Button home_increment;
    Button home_decrement;
    Button away_increment;
    Button awar_decrement;
    int home_index = 0;
    int away_index = 0;
    String[] scoreStrings= {"0","15","30","40"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoring);
        /**
         * Attach xmltext views to java textViews
         */
        home_score = findViewById(R.id.home_score);
        away_score = findViewById(R.id.away_score);

        /**
         *initialize score text views to '0' at the start of a game
         */
        home_score.setText(scoreStrings[home_index]);
        away_score.setText(scoreStrings[away_index]);

    }

    public void incHome(View view){
        if(home_index < 3) {
            home_index++;
            home_score.setText(scoreStrings[home_index]);
        }
    }
    public void decHome(View view){
        if(home_index > 0) {
            home_index--;
            home_score.setText(scoreStrings[home_index]);
        }
    }
    public void incAway(View view){
        if(away_index < 3){
            away_index++;
            away_score.setText(scoreStrings[away_index]);
        }

    }
    public void decAway(View view){
        if(away_index > 0){
            away_index--;
            away_score.setText(scoreStrings[away_index]);
        }
    }
}
