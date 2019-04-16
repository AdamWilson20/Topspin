package com.example.topspin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ScoreByGame extends AppCompatActivity {


    private int setNumberm1;
    private int setNumberm2;
    private int setNumberm3;

    Matches match1, match2, match3;
    MatchSet m1set1, m1set2, m1set3;
    MatchSet m2set1, m2set2, m2set3;
    MatchSet m3set1, m3set2, m3set3;

    private TextView m1hp, m1ap;
    private TextView m2hp, m2ap;
    private TextView m3hp, m3ap;

    private TextView m1s1h, m1s2h, m1s3h, m1s1a, m1s2a, m1s3a;
    private TextView m2s1h, m2s2h, m2s3h, m2s1a, m2s2a, m2s3a;
    private TextView m3s1h, m3s2h, m3s3h, m3s1a, m3s2a, m3s3a;

    private ImageButton m1hi, m1hd, m1ai, m1ad;
    private ImageButton m2hi, m2hd, m2ai, m2ad;
    private ImageButton m3hi, m3hd, m3ai, m3ad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_by_game);

        //Set player Views 'm' = match number, 'h' = home, 'a' = away, 'p' = player
        m1hp = findViewById(R.id.Match1HomePlayer); m1ap = findViewById(R.id.Match1AwayPlayer);
        m2hp = findViewById(R.id.M2HomePlayer); m2ap = findViewById(R.id.M2AwayPlayer);
        m3hp = findViewById(R.id.M3HomePlayer); m3ap = findViewById(R.id.M3AwayPlayer);

        //set set column views 'm' = match number, 'h' = home, 'a' = away, 's' = set number
        m1s1h = findViewById(R.id.M1S1H); m1s2h = findViewById(R.id.M1S2H); m1s3h = findViewById(R.id.M1S3H);
        m1s1a = findViewById(R.id.M1S1A); m1s2a = findViewById(R.id.M1S2A); m1s3a = findViewById(R.id.M1SA3);

        m2s1h = findViewById(R.id.M2S1H); m2s2h = findViewById(R.id.M2S2H); m2s3h = findViewById(R.id.M2S3H);
        m2s1a = findViewById(R.id.M2S1A); m2s2a = findViewById(R.id.M2S2A); m2s3a = findViewById(R.id.M2S3A);

        m3s1h = findViewById(R.id.M3S1H); m3s2h = findViewById(R.id.M3S2H); m3s3h = findViewById(R.id.M3S3H);
        m3s1a = findViewById(R.id.M3S1A); m3s2a = findViewById(R.id.M3S2A); m3s3a = findViewById(R.id.M3S3A);

        //Set increment/decrement buttons 'm' = match number, 'h' = home, 'a' = away, 'i' = increment, 'd' = decrement
        m1hi = findViewById(R.id.M1HI); m1hd = findViewById(R.id.M1HD);
        m1ai = findViewById(R.id.M1AI); m1ad = findViewById(R.id.M1AD);

        m2hi = findViewById(R.id.M2HI); m2hd = findViewById(R.id.M2HD);
        m2ai = findViewById(R.id.M2AI); m2ad = findViewById(R.id.M2AD);

        m3hi = findViewById(R.id.M3HI); m3hd = findViewById(R.id.M3HD);
        m3ai = findViewById(R.id.M3AI); m3ad = findViewById(R.id.M3AD);

        // Load dummy matches, will be removed or commented out when database connected
        match1 = new Matches(1, 2, "Jim Halpert", "none", "Dwight Schrute","none", 0, 0, "In progress");
        match2 = new Matches(2, 2, "Pam Halpert", "none", "Ryan Howard","none", 0, 0, "In progress");
        match3 = new Matches(3, 2, "Michael scott", "none", "Toby Flenderson","none", 0, 0, "In progress");

        m1set1 = new  MatchSet(1,1, 1,0,0,"In Progress");
        m1set2 = new MatchSet(1,2,2,0,0,"In Progress");
        m1set3 = new MatchSet(1,3,3,0,0,"In Progress");

        m2set1 = new MatchSet(2,1,1,0,0,"In Progress");
        m2set2 = new MatchSet(2,2,2, 0,0,"In Progress");
        m2set3 = new MatchSet(2,3,3,0,0,"In Progress");

        m3set1 = new MatchSet(3,1,1, 0,0,"In Progress");
        m3set2 = new MatchSet(3,2,2, 0,0,"In Progress");
        m3set3 = new MatchSet(3,3,3, 0,0,"In Progress");

        //Set textViews
        m1hp.setText(match1.getHomePlayer1()); m1ap.setText(match1.getAwayPlayer1());
        m2hp.setText(match2.getHomePlayer1()); m2ap.setText(match2.getAwayPlayer1());
        m3hp.setText(match3.getHomePlayer1()); m3ap.setText(match3.getAwayPlayer1());

        m1s1h.setText(String.valueOf(m1set1.getHomeScore())); m1s1a.setText(String.valueOf(m1set1.getAwayScore()));
        m1s2h.setText(String.valueOf(m1set2.getHomeScore())); m1s2a.setText(String.valueOf(m1set2.getAwayScore()));
        m1s3h.setText(String.valueOf(m1set3.getHomeScore())); m1s3a.setText(String.valueOf(m1set3.getAwayScore()));

        m2s1h.setText(String.valueOf(m2set1.getHomeScore())); m2s1a.setText(String.valueOf(m2set1.getAwayScore()));
        m2s2h.setText(String.valueOf(m2set2.getHomeScore())); m2s2a.setText(String.valueOf(m2set2.getAwayScore()));
        m2s3h.setText(String.valueOf(m2set3.getHomeScore())); m2s3a.setText(String.valueOf(m2set3.getAwayScore()));

        m3s1h.setText(String.valueOf(m3set1.getHomeScore())); m3s1a.setText(String.valueOf(m3set1.getAwayScore()));
        m3s2h.setText(String.valueOf(m3set2.getHomeScore())); m3s2a.setText(String.valueOf(m3set2.getAwayScore()));
        m3s3h.setText(String.valueOf(m3set3.getHomeScore())); m3s3a.setText(String.valueOf(m3set3.getAwayScore()));

        m1hi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getApplicationContext(),match1.getResult().toString(), Toast.LENGTH_LONG).show();
                if(m1set1.getResult().equals("In Progress")){
                    setNumberm1 = 1;
                }else if(m1set2.getResult().equals("In Progress")){
                    setNumberm1 = 2;
                }else if(m1set3.getResult().equals("In Progress")){
                    setNumberm1 = 3;
                }

                if(checkMatchWin(match1)){
                    setNumberm1 = 0;
                    Toast.makeText(getApplicationContext(), " Game is over, winner: " + match1.getResult(), Toast.LENGTH_LONG).show();
                }

                switch(setNumberm1){
                    case 1:{
                        if (m1set1.getHomeScore() == 6){
                            //set won
                            match1.setHomeTeamSets(match1.getHomeTeamSets()+1);
                            m1set1.setHomeScore(m1set1.getHomeScore()+1);
                            m1set1.setResult(match1.getHomePlayer1());
                            //updateSet(match1, m1set1,'h');

                        }else if(m1set1.getHomeScore()==5){
                            if(m1set1.getAwayScore() < 5){
                                //set won
                                match1.setHomeTeamSets(match1.getHomeTeamSets()+1);
                                m1set1.setHomeScore(m1set1.getHomeScore()+1);
                                m1set1.setResult(match1.getHomePlayer1());
                                //  updateSet(match1, m1set1,'h');
                            }else{
                                //increment
                                m1set1.setHomeScore(m1set1.getHomeScore()+1);
                                // upDateGame(m1set1, 'h');
                            }
                        }else{ //increment
                            m1set1.setHomeScore(m1set1.getHomeScore()+1);
                            // upDateGame(m1set1, 'h');
                        }
                        m1s1h.setText(String.valueOf(m1set1.getHomeScore()));
                        break;
                    }
                    case 2:{
                        if(checkMatchWin(match1)){
                            break;
                        }
                        if (m1set2.getHomeScore() == 6){
                            //set won
                            match1.setHomeTeamSets(match1.getHomeTeamSets()+1);
                            m1set2.setHomeScore(m1set2.getHomeScore()+1);
                            m1set2.setResult(match1.getHomePlayer1());
                            if(checkMatchWin(match1)){
                                match1.setResult(match1.getHomePlayer1());
                            }
                            //updateSet(match1, m1set1,'h');

                        }else if(m1set2.getHomeScore()==5){
                            if(m1set2.getAwayScore() < 5){
                                //set won
                                match1.setHomeTeamSets(match1.getHomeTeamSets()+1);
                                m1set2.setHomeScore(m1set2.getHomeScore()+1);
                                m1set2.setResult(match1.getHomePlayer1());
                                if(checkMatchWin(match1)){
                                    match1.setResult(match1.getHomePlayer1());
                                }

                                //  updateSet(match1, m1set1,'h');
                            }else{
                                //increment
                                m1set2.setHomeScore(m1set2.getHomeScore()+1);
                                // upDateGame(m1set1, 'h');
                            }
                        }else{
                            //increment
                            m1set2.setHomeScore(m1set2.getHomeScore()+1);
                            // upDateGame(m1set1, 'h');
                        }

                        m1s2h.setText(String.valueOf(m1set2.getHomeScore()));
                        break;

                    }
                    case 3:{
                        if (m1set3.getHomeScore() == 6){
                            //set won
                            match1.setHomeTeamSets(match1.getHomeTeamSets()+1);
                            m1set3.setHomeScore(m1set3.getHomeScore()+1);
                            m1set3.setResult(match1.getHomePlayer1());
                            match1.setResult(match1.getHomePlayer1());
                            //updateSet(match1, m1set3,'h');

                        }else if(m1set3.getHomeScore()==5){
                            if(m1set3.getAwayScore()< 5){
                                //set won
                                match1.setHomeTeamSets(match1.getHomeTeamSets()+1);
                                m1set3.setHomeScore(m1set3.getHomeScore()+1);
                                m1set3.setResult(match1.getHomePlayer1());
                                match1.setResult(match1.getHomePlayer1());
                                // updateSet(match1, m1set3,'h');
                            }else{
                                //increment
                                m1set3.setHomeScore(m1set3.getHomeScore()+1);
                                // upDateGame(m1set3, 'h');
                            }
                        }else{
                            //increment
                            m1set3.setHomeScore(m1set3.getHomeScore()+1);
                            // upDateGame(m1set3, 'h');
                        }

                        m1s3h.setText(String.valueOf(m1set3.getHomeScore()));
                        break;

                    }
                }

            }
        });


        m1ai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(m1set1.getResult().equals("In Progress")){
                    setNumberm1 = 1;
                }else if(m1set2.getResult().equals("In Progress")){
                    setNumberm1 = 2;
                }else if(m1set3.getResult().equals("In Progress")){
                    setNumberm1 = 3;
                }


                if(checkMatchWin(match1)){
                    setNumberm1 = 0;
                    Toast.makeText(getApplicationContext(), " Game is over, winner: " + match1.getResult(), Toast.LENGTH_LONG).show();
                }
                switch(setNumberm1){
                    case 1:{
                        if (m1set1.getAwayScore() == 6){
                            //set won
                            match1.setAwayTeamSets(match1.getAwayTeamSets()+1);
                            m1set1.setAwayScore(m1set1.getAwayScore()+1);
                            m1set1.setResult(match1.getAwayPlayer1());
                            //updateSet(match1, m1set1,'h');

                        }else if(m1set1.getAwayScore()==5){
                            if(m1set1.getHomeScore() < 5){
                                //set won
                                match1.setAwayTeamSets(match1.getAwayTeamSets()+1);
                                m1set1.setAwayScore(m1set1.getAwayScore()+1);
                                m1set1.setResult(match1.getAwayPlayer1());
                                //  updateSet(match1, m1set1,'h');
                            }else{
                                //increment
                                m1set1.setAwayScore(m1set1.getAwayScore()+1);
                                // upDateGame(m1set1, 'h');
                            }
                        }else{ //increment
                            m1set1.setAwayScore(m1set1.getAwayScore()+1);
                            // upDateGame(m1set1, 'h');
                        }
                        m1s1a.setText(String.valueOf(m1set1.getAwayScore()));
                        break;
                    }
                    case 2:{
                        if(checkMatchWin(match1)){
                            break;
                        }
                        if (m1set2.getAwayScore() == 6){
                            //set won
                            match1.setAwayTeamSets(match1.getAwayTeamSets()+1);
                            m1set2.setAwayScore(m1set2.getAwayScore()+1);
                            m1set2.setResult(match1.getAwayPlayer1());
                            if(checkMatchWin(match1)){
                                match1.setResult(match1.getAwayPlayer1());
                            }
                            //updateSet(match1, m1set1,'h');

                        }else if(m1set2.getAwayScore()==5){
                            if(m1set2.getHomeScore() < 5){
                                //set won
                                match1.setAwayTeamSets(match1.getAwayTeamSets()+1);
                                m1set2.setAwayScore(m1set2.getAwayScore()+1);
                                m1set2.setResult(match1.getAwayPlayer1());
                                if(checkMatchWin(match1)){
                                    match1.setResult(match1.getAwayPlayer1());
                                }
                                //  updateSet(match1, m1set1,'h');
                            }else{
                                //increment
                                m1set2.setAwayScore(m1set2.getAwayScore()+1);
                                // upDateGame(m1set1, 'h');
                            }
                        }else{
                            //increment
                            m1set2.setAwayScore(m1set2.getAwayScore()+1);
                            // upDateGame(m1set1, 'h');
                        }

                        m1s2a.setText(String.valueOf(m1set2.getAwayScore()));
                        break;

                    }
                    case 3:{
                        if (m1set3.getAwayScore() == 6){
                            //set won
                            match1.setAwayTeamSets(match1.getAwayTeamSets()+1);
                            m1set3.setAwayScore(m1set3.getAwayScore()+1);
                            m1set3.setResult(match1.getAwayPlayer1());
                            match1.setResult(match1.getAwayPlayer1());
                            //updateSet(match1, m1set3,'h');

                        }else if(m1set3.getAwayScore()==5){
                            if(m1set3.getHomeScore()< 5){
                                //set won
                                match1.setAwayTeamSets(match1.getAwayTeamSets()+1);
                                m1set3.setAwayScore(m1set3.getAwayScore()+1);
                                m1set3.setResult(match1.getAwayPlayer1());
                                match1.setResult(match1.getAwayPlayer1());
                                // updateSet(match1, m1set3,'h');
                            }else{
                                //increment
                                m1set3.setAwayScore(m1set3.getAwayScore()+1);
                                // upDateGame(m1set3, 'h');
                            }
                        }else{
                            //increment
                            m1set3.setAwayScore(m1set3.getAwayScore()+1);
                            // upDateGame(m1set3, 'h');
                        }

                        m1s3a.setText(String.valueOf(m1set3.getAwayScore()));
                        break;

                    }
                }

            }
        });


    }

    /////Data change methods
    public void upDateGame(MatchSet set, char team){

    }
    public void updateSet(Matches match, MatchSet set, char team){


    }

    public boolean checkMatchWin(Matches target){
        if((target.getHomeTeamSets() == 2)||(target.getAwayTeamSets()==2)){
            return true;
        }else{
            return false;
        }
    }

}
