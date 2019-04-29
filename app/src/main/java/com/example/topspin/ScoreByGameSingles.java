package com.example.topspin;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScoreByGameSingles extends AppCompatActivity {

    private ProgressDialog progressDialog;

    private int eventID = 1;
    private int setNumberm1;
    private int setNumberm2;
    private int setNumberm3;
    private int setNumberm4;
    private int setNumberm5;
    private int setNumberm6;


    private String matchType = "Singles";

    ArrayList<Matches> matchList = new ArrayList<>();
    ArrayList<MatchSet> setList = new ArrayList<>();



    private Matches match1, match2, match3, match4, match5, match6;
    private MatchSet m1set1, m1set2, m1set3;
    private MatchSet m2set1, m2set2, m2set3;
    private MatchSet m3set1, m3set2, m3set3;
    private MatchSet m4set1, m4set2, m4set3;
    private MatchSet m5set1, m5set2, m5set3;
    private MatchSet m6set1, m6set2, m6set3;

    private TextView m1hp, m1ap;
    private TextView m2hp, m2ap;
    private TextView m3hp, m3ap;
    private TextView m4hp, m4ap;
    private TextView m5hp, m5ap;
    private TextView m6hp, m6ap;

    private TextView m1s1h, m1s2h, m1s3h, m1s1a, m1s2a, m1s3a;
    private TextView m2s1h, m2s2h, m2s3h, m2s1a, m2s2a, m2s3a;
    private TextView m3s1h, m3s2h, m3s3h, m3s1a, m3s2a, m3s3a;
    private TextView m4s1h, m4s2h, m4s3h, m4s1a, m4s2a, m4s3a;
    private TextView m5s1h, m5s2h, m5s3h, m5s1a, m5s2a, m5s3a;
    private TextView m6s1h, m6s2h, m6s3h, m6s1a, m6s2a, m6s3a;

    private Button m1hi, m1hd, m1ai, m1ad;
    private Button m2hi, m2hd, m2ai, m2ad;
    private Button m3hi, m3hd, m3ai, m3ad;
    private Button m4hi, m4hd, m4ai, m4ad;
    private Button m5hi, m5hd, m5ai, m5ad;
    private Button m6hi, m6hd, m6ai, m6ad;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_by_game_singles);
/*
        //Get the eventID from the calling activity
        Intent getID = getIntent();
        eventID = getID.getIntExtra("eventID", 1);
        matchType = getID.getStringExtra("MATCHTYPE");*/


        //Set player Views 'm' = match number, 'h' = home, 'a' = away, 'p' = player
        m1hp = findViewById(R.id.M4HomePlayer); m1ap = findViewById(R.id.M4AwayPlayer);

        m2hp = findViewById(R.id.M5HomePlayer); m2ap = findViewById(R.id.M5AwayPlayer);

        m3hp = findViewById(R.id.M6HomePlayer); m3ap = findViewById(R.id.M6AwayPlayer);

        m4hp = findViewById(R.id.M7HomePlayer); m4ap = findViewById(R.id.M7AwayPlayer);

        m5hp = findViewById(R.id.M8HomePlayer); m5ap = findViewById(R.id.M8AwayPlayer);

        m6hp = findViewById(R.id.M9HomePlayer); m6ap = findViewById(R.id.M9AwayPlayer);


        //set set column views 'm' = match number, 'h' = home, 'a' = away, 's' = set number
        m1s1h = findViewById(R.id.M4S1H); m1s2h = findViewById(R.id.M4S2H); m1s3h = findViewById(R.id.M4S3H);
        m1s1a = findViewById(R.id.M4S1A); m1s2a = findViewById(R.id.M4S2A); m1s3a = findViewById(R.id.M4S3A);

        m2s1h = findViewById(R.id.M5S1H); m2s2h = findViewById(R.id.M5S2H); m2s3h = findViewById(R.id.M5S3H);
        m2s1a = findViewById(R.id.M5S1A); m2s2a = findViewById(R.id.M5S2A); m2s3a = findViewById(R.id.M5S3A);

        m3s1h = findViewById(R.id.M6S1H); m3s2h = findViewById(R.id.M6S2H); m3s3h = findViewById(R.id.M6S3H);
        m3s1a = findViewById(R.id.M6S1A); m3s2a = findViewById(R.id.M6S2A); m3s3a = findViewById(R.id.M6S3A);

        m4s1h = findViewById(R.id.M7S1H); m4s2h = findViewById(R.id.M7S2H); m4s3h = findViewById(R.id.M7S3H);
        m4s1a = findViewById(R.id.M7S1A); m4s2a = findViewById(R.id.M7S2A); m4s3a = findViewById(R.id.M7S3A);

        m5s1h = findViewById(R.id.M8S1H); m5s2h = findViewById(R.id.M8S2H); m5s3h = findViewById(R.id.M8S3H);
        m5s1a = findViewById(R.id.M8S1A); m5s2a = findViewById(R.id.M8S2A); m5s3a = findViewById(R.id.M8S3A);

        m6s1h = findViewById(R.id.M9S1H); m6s2h = findViewById(R.id.M9S2H); m6s3h = findViewById(R.id.M9S3H);
        m6s1a = findViewById(R.id.M9S1A); m6s2a = findViewById(R.id.M9S2A); m6s3a = findViewById(R.id.M9S3A);

        //Set increment/decrement buttons 'm' = match number, 'h' = home, 'a' = away, 'i' = increment, 'd' = decrement
        m1hi = findViewById(R.id.M4HI); m1hd = findViewById(R.id.M4HD);
        m1ai = findViewById(R.id.M4AI); m1ad = findViewById(R.id.M4AD);

        m2hi = findViewById(R.id.M5HI); m2hd = findViewById(R.id.M5HD);
        m2ai = findViewById(R.id.M5AI); m2ad = findViewById(R.id.M5AD);

        m3hi = findViewById(R.id.M6HI); m3hd = findViewById(R.id.M6HD);
        m3ai = findViewById(R.id.M6AI); m3ad = findViewById(R.id.M6AD);

        m4hi = findViewById(R.id.M7HI); m4hd = findViewById(R.id.M7HD);
        m4ai = findViewById(R.id.M7AI); m4ad = findViewById(R.id.M7AD);

        m5hi = findViewById(R.id.M8HI); m5hd = findViewById(R.id.M8HD);
        m5ai = findViewById(R.id.M8AI); m5ad = findViewById(R.id.M8AD);

        m6hi = findViewById(R.id.M9HI); m6hd = findViewById(R.id.M9HD);
        m6ai = findViewById(R.id.M9AI); m6ad = findViewById(R.id.M9AD);



        progressDialog = new ProgressDialog(this);

        // Load dummy matches, will be removed or commented out when database connected
        match1 = new Matches(4, 1, "Singles", null, null, null,null, 0, 0, "In Progress");
        match2 = new Matches(5, 1, "Singles", null, null, null,null, 0, 0, "In Progress");
        match3 = new Matches(6, 1, "Singles", null, null, null,null, 0, 0, "In Progress");
        match4 = new Matches(7, 1, "Singles", null, null, null,null, 0, 0, "In Progress");
        match5 = new Matches(8, 1, "Singles", null, null, null,null, 0, 0, "In Progress");
        match6 = new Matches(9, 1, "Singles", null, null, null,null, 0, 0, "In Progress");

        m1set1 = new  MatchSet(4,10, 0,0,"In Progress");
        m1set2 = new MatchSet(4,11,0,0,"In Progress");
        m1set3 = new MatchSet(4,12,0,0,"In Progress");

        m2set1 = new MatchSet(5,13,0,0,"In Progress");
        m2set2 = new MatchSet(5,14, 0,0,"In Progress");
        m2set3 = new MatchSet(5,15,0,0,"In Progress");

        m3set1 = new MatchSet(6,16, 0,0,"In Progress");
        m3set2 = new MatchSet(6,17, 0,0,"In Progress");
        m3set3 = new MatchSet(6,18, 0,0,"In Progress");

        m4set1 = new MatchSet(7,19, 0,0,"In Progress");
        m4set2 = new MatchSet(7,20, 0,0,"In Progress");
        m4set3 = new MatchSet(7,21, 0,0,"In Progress");

        m5set1 = new MatchSet(8,22, 0,0,"In Progress");
        m5set2 = new MatchSet(8,23, 0,0,"In Progress");
        m5set3 = new MatchSet(8,24, 0,0,"In Progress");

        m6set1 = new MatchSet(9,25, 0,0,"In Progress");
        m6set2 = new MatchSet(9,26, 0,0,"In Progress");
        m6set3 = new MatchSet(9,27, 0,0,"In Progress");




        getMatches();
        getSetsM1();
        getSetsM2();
        getSetsM3();
        getSetsM4();
        getSetsM5();
        getSetsM6();

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

        m4s1h.setText(String.valueOf(m4set1.getHomeScore())); m4s1a.setText(String.valueOf(m4set1.getAwayScore()));
        m4s2h.setText(String.valueOf(m4set2.getHomeScore())); m4s2a.setText(String.valueOf(m4set2.getAwayScore()));
        m4s3h.setText(String.valueOf(m4set3.getHomeScore())); m4s3a.setText(String.valueOf(m4set3.getAwayScore()));

        m5s1h.setText(String.valueOf(m5set1.getHomeScore())); m5s1a.setText(String.valueOf(m5set1.getAwayScore()));
        m5s2h.setText(String.valueOf(m5set2.getHomeScore())); m5s2a.setText(String.valueOf(m5set2.getAwayScore()));
        m5s3h.setText(String.valueOf(m5set3.getHomeScore())); m5s3a.setText(String.valueOf(m5set3.getAwayScore()));

        m6s1h.setText(String.valueOf(m6set1.getHomeScore())); m6s1a.setText(String.valueOf(m6set1.getAwayScore()));
        m6s2h.setText(String.valueOf(m6set2.getHomeScore())); m6s2a.setText(String.valueOf(m6set2.getAwayScore()));
        m6s3h.setText(String.valueOf(m6set3.getHomeScore())); m6s3a.setText(String.valueOf(m6set3.getAwayScore()));
//



        updateMatch(match1);
        updateMatch(match2);
        updateMatch(match3);
        updateMatch(match4);
        updateMatch(match5);
        updateMatch(match6);

        updateSet(m1set1);
        updateSet(m1set2);
        updateSet(m1set3);

        updateSet(m2set1);
        updateSet(m2set2);
        updateSet(m2set3);

        updateSet(m3set1);
        updateSet(m3set2);
        updateSet(m3set3);

        updateSet(m4set1);
        updateSet(m4set2);
        updateSet(m4set3);

        updateSet(m5set1);
        updateSet(m5set2);
        updateSet(m5set3);

        updateSet(m6set1);
        updateSet(m6set2);
        updateSet(m6set3);




        /**
         * Match 1's listeners
         */
        m1hi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumberm1 = findSetNumber(m1set1,m1set2,m1set3);

                incrementScoreHome(match1,m1set1,m1set2,m1set3,setNumberm1);

                switch(setNumberm1){
                    case 1:{
                        m1s1h.setText(String.valueOf(m1set1.getHomeScore()));
                        break;
                    }
                    case 2:{
                        m1s2h.setText(String.valueOf(m1set2.getHomeScore()));
                        break;
                    }
                    case 3:{
                        m1s3h.setText(String.valueOf(m1set3.getHomeScore()));
                        break;
                    }
                }
            }

        });


        m1ai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setNumberm1 = findSetNumber(m1set1,m1set2,m1set3);

                incrementScoreAway(match1,m1set1,m1set2,m1set3,setNumberm1);

                switch(setNumberm1){
                    case 1:{
                        m1s1a.setText(String.valueOf(m1set1.getAwayScore()));
                        break;
                    }
                    case 2:{
                        m1s2a.setText(String.valueOf(m1set2.getAwayScore()));
                        break;
                    }
                    case 3:{
                        m1s3a.setText(String.valueOf(m1set3.getAwayScore()));
                        break;
                    }
                }
            }
        });

        m1hd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumberm1 = findSetNumber(m1set1, m1set2, m1set3);
                decrementScoreHome(match1,m1set1,m1set2, m1set3, setNumberm1);
                m1s1h.setText(String.valueOf(m1set1.getHomeScore()));
                m1s1a.setText(String.valueOf(m1set1.getAwayScore()));

                m1s2h.setText(String.valueOf(m1set2.getHomeScore()));
                m1s2a.setText(String.valueOf(m1set2.getAwayScore()));

                m1s3h.setText(String.valueOf(m1set3.getHomeScore()));
                m1s3a.setText(String.valueOf(m1set3.getAwayScore()));
            }
        });

        m1ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumberm1 = findSetNumber(m1set1, m1set2, m1set3);
                decrementScoreAway(match1,m1set1,m1set2, m1set3, setNumberm1);
                m1s1h.setText(String.valueOf(m1set1.getHomeScore()));
                m1s1a.setText(String.valueOf(m1set1.getAwayScore()));

                m1s2h.setText(String.valueOf(m1set2.getHomeScore()));
                m1s2a.setText(String.valueOf(m1set2.getAwayScore()));

                m1s3h.setText(String.valueOf(m1set3.getHomeScore()));
                m1s3a.setText(String.valueOf(m1set3.getAwayScore()));
            }
        });


        /**
         * Match 2's listeners
         */

        m2hi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumberm2 = findSetNumber(m2set1,m2set2,m2set3);

                incrementScoreHome(match2,m2set1,m2set2,m2set3,setNumberm2);

                switch(setNumberm2){
                    case 1:{
                        m2s1h.setText(String.valueOf(m2set1.getHomeScore()));
                        break;
                    }
                    case 2:{
                        m2s2h.setText(String.valueOf(m2set2.getHomeScore()));
                        break;
                    }
                    case 3:{
                        m2s3h.setText(String.valueOf(m2set3.getHomeScore()));
                        break;
                    }
                }

            }
        });


        m2ai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setNumberm2 = findSetNumber(m2set1,m2set2,m2set3);

                incrementScoreAway(match2,m2set1,m2set2,m2set3,setNumberm2);

                switch(setNumberm2){
                    case 1:{
                        m2s1a.setText(String.valueOf(m2set1.getAwayScore()));
                        break;
                    }
                    case 2:{
                        m2s2a.setText(String.valueOf(m2set2.getAwayScore()));
                        break;
                    }
                    case 3:{
                        m2s3a.setText(String.valueOf(m2set3.getAwayScore()));
                        break;
                    }
                }

            }
        });

        m2hd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumberm2 = findSetNumber(m2set1, m2set2, m2set3);
                decrementScoreHome(match2,m2set1,m2set2, m2set3, setNumberm2);
                m2s1h.setText(String.valueOf(m2set1.getHomeScore()));
                m2s1a.setText(String.valueOf(m2set1.getAwayScore()));

                m2s2h.setText(String.valueOf(m2set2.getHomeScore()));
                m2s2a.setText(String.valueOf(m2set2.getAwayScore()));

                m2s3h.setText(String.valueOf(m2set3.getHomeScore()));
                m2s3a.setText(String.valueOf(m2set3.getAwayScore()));
            }
        });

        m2ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumberm2 = findSetNumber(m2set1, m2set2, m2set3);
                decrementScoreAway(match2,m2set1,m2set2, m2set3, setNumberm2);
                m2s1h.setText(String.valueOf(m2set1.getHomeScore()));
                m2s1a.setText(String.valueOf(m2set1.getAwayScore()));

                m2s2h.setText(String.valueOf(m2set2.getHomeScore()));
                m2s2a.setText(String.valueOf(m2set2.getAwayScore()));

                m2s3h.setText(String.valueOf(m2set3.getHomeScore()));
                m2s3a.setText(String.valueOf(m2set3.getAwayScore()));
            }
        });


        /**
         *  Match 3's listeners
         */
        m3hi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumberm3 = findSetNumber(m3set1,m3set2,m3set3);

                incrementScoreHome(match3,m3set1,m3set2,m3set3,setNumberm3);

                switch(setNumberm3){
                    case 1:{
                        m3s1h.setText(String.valueOf(m3set1.getHomeScore()));
                        break;
                    }
                    case 2:{
                        m3s2h.setText(String.valueOf(m3set2.getHomeScore()));
                        break;
                    }
                    case 3:{
                        m3s3h.setText(String.valueOf(m3set3.getHomeScore()));
                        break;
                    }
                }

            }
        });


        m3ai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setNumberm3 = findSetNumber(m3set1,m3set2,m3set3);

                incrementScoreAway(match3,m3set1,m3set2,m3set3,setNumberm3);

                switch(setNumberm3){
                    case 1:{
                        m3s1a.setText(String.valueOf(m3set1.getAwayScore()));
                        break;
                    }
                    case 2:{
                        m3s2a.setText(String.valueOf(m3set2.getAwayScore()));
                        break;
                    }
                    case 3:{
                        m3s3a.setText(String.valueOf(m3set3.getAwayScore()));
                        break;
                    }
                }

            }
        });


        m3hd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumberm3 = findSetNumber(m3set1, m3set2, m3set3);
                decrementScoreHome(match3,m3set1,m3set2, m3set3, setNumberm3);
                m3s1h.setText(String.valueOf(m3set1.getHomeScore()));
                m3s1a.setText(String.valueOf(m3set1.getAwayScore()));

                m3s2h.setText(String.valueOf(m3set2.getHomeScore()));
                m3s2a.setText(String.valueOf(m3set2.getAwayScore()));

                m3s3h.setText(String.valueOf(m3set3.getHomeScore()));
                m3s3a.setText(String.valueOf(m3set3.getAwayScore()));
            }
        });

        m3ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumberm3 = findSetNumber(m3set1, m3set2, m3set3);
                decrementScoreAway(match3,m3set1,m3set2, m3set3, setNumberm3);
                m3s1h.setText(String.valueOf(m3set1.getHomeScore()));
                m3s1a.setText(String.valueOf(m3set1.getAwayScore()));

                m3s2h.setText(String.valueOf(m3set2.getHomeScore()));
                m3s2a.setText(String.valueOf(m3set2.getAwayScore()));

                m3s3h.setText(String.valueOf(m3set3.getHomeScore()));
                m3s3a.setText(String.valueOf(m3set3.getAwayScore()));
            }
        });


        /**
         * Match 4's listeners
         */
        m4hi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumberm4 = findSetNumber(m4set1,m4set2,m4set3);

                incrementScoreHome(match4,m4set1,m4set2,m4set3,setNumberm4);

                switch(setNumberm4){
                    case 1:{
                        m4s1h.setText(String.valueOf(m4set1.getHomeScore()));
                        break;
                    }
                    case 2:{
                        m4s2h.setText(String.valueOf(m4set2.getHomeScore()));
                        break;
                    }
                    case 3:{
                        m4s3h.setText(String.valueOf(m4set3.getHomeScore()));
                        break;
                    }
                }
            }

        });


        m4ai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setNumberm4 = findSetNumber(m4set1,m4set2,m4set3);

                incrementScoreAway(match4,m4set1,m4set2,m4set3,setNumberm4);

                switch(setNumberm4){
                    case 1:{
                        m4s1a.setText(String.valueOf(m4set1.getAwayScore()));
                        break;
                    }
                    case 2:{
                        m4s2a.setText(String.valueOf(m4set2.getAwayScore()));
                        break;
                    }
                    case 3:{
                        m4s3a.setText(String.valueOf(m4set3.getAwayScore()));
                        break;
                    }
                }
            }
        });

        m4hd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumberm4 = findSetNumber(m4set1, m4set2, m4set3);
                decrementScoreHome(match4,m4set1,m4set2, m4set3, setNumberm4);
                m4s1h.setText(String.valueOf(m4set1.getHomeScore()));
                m4s1a.setText(String.valueOf(m4set1.getAwayScore()));

                m4s2h.setText(String.valueOf(m4set2.getHomeScore()));
                m4s2a.setText(String.valueOf(m4set2.getAwayScore()));

                m4s3h.setText(String.valueOf(m4set3.getHomeScore()));
                m4s3a.setText(String.valueOf(m4set3.getAwayScore()));
            }
        });

        m4ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumberm4 = findSetNumber(m4set1, m4set2, m4set3);
                decrementScoreAway(match4,m4set1,m4set2, m4set3, setNumberm4);
                m4s1h.setText(String.valueOf(m4set1.getHomeScore()));
                m4s1a.setText(String.valueOf(m4set1.getAwayScore()));

                m4s2h.setText(String.valueOf(m4set2.getHomeScore()));
                m4s2a.setText(String.valueOf(m4set2.getAwayScore()));

                m4s3h.setText(String.valueOf(m4set3.getHomeScore()));
                m4s3a.setText(String.valueOf(m4set3.getAwayScore()));
            }
        });

        /**
         * Match 5's listeners
         */
        m5hi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumberm5 = findSetNumber(m5set1,m5set2,m5set3);

                incrementScoreHome(match5,m5set1,m5set2,m5set3,setNumberm5);

                switch(setNumberm5){
                    case 1:{
                        m5s1h.setText(String.valueOf(m5set1.getHomeScore()));
                        break;
                    }
                    case 2:{
                        m5s2h.setText(String.valueOf(m5set2.getHomeScore()));
                        break;
                    }
                    case 3:{
                        m5s3h.setText(String.valueOf(m5set3.getHomeScore()));
                        break;
                    }
                }
            }

        });


        m5ai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setNumberm5 = findSetNumber(m5set1,m5set2,m5set3);

                incrementScoreAway(match5,m5set1,m5set2,m5set3,setNumberm5);

                switch(setNumberm5){
                    case 1:{
                        m5s1a.setText(String.valueOf(m5set1.getAwayScore()));
                        break;
                    }
                    case 2:{
                        m5s2a.setText(String.valueOf(m5set2.getAwayScore()));
                        break;
                    }
                    case 3:{
                        m5s3a.setText(String.valueOf(m5set3.getAwayScore()));
                        break;
                    }
                }
            }
        });

        m5hd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumberm5 = findSetNumber(m5set1, m5set2, m5set3);
                decrementScoreHome(match5,m5set1,m5set2, m5set3, setNumberm5);
                m5s1h.setText(String.valueOf(m5set1.getHomeScore()));
                m5s1a.setText(String.valueOf(m5set1.getAwayScore()));

                m5s2h.setText(String.valueOf(m5set2.getHomeScore()));
                m5s2a.setText(String.valueOf(m5set2.getAwayScore()));

                m5s3h.setText(String.valueOf(m5set3.getHomeScore()));
                m5s3a.setText(String.valueOf(m5set3.getAwayScore()));
            }
        });

        m5ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumberm5 = findSetNumber(m5set1, m5set2, m5set3);
                decrementScoreAway(match5,m5set1,m5set2, m5set3, setNumberm5);
                m5s1h.setText(String.valueOf(m5set1.getHomeScore()));
                m5s1a.setText(String.valueOf(m5set1.getAwayScore()));

                m5s2h.setText(String.valueOf(m5set2.getHomeScore()));
                m5s2a.setText(String.valueOf(m5set2.getAwayScore()));

                m5s3h.setText(String.valueOf(m5set3.getHomeScore()));
                m5s3a.setText(String.valueOf(m5set3.getAwayScore()));
            }
        });

        /**
         * Match 6's listeners
         */
        m6hi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumberm6 = findSetNumber(m6set1,m6set2,m6set3);

                incrementScoreHome(match6,m6set1,m6set2,m6set3,setNumberm6);

                switch(setNumberm6){
                    case 1:{
                        m6s1h.setText(String.valueOf(m6set1.getHomeScore()));
                        break;
                    }
                    case 2:{
                        m6s2h.setText(String.valueOf(m6set2.getHomeScore()));
                        break;
                    }
                    case 3:{
                        m6s3h.setText(String.valueOf(m6set3.getHomeScore()));
                        break;
                    }
                }
            }

        });


        m6ai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setNumberm6 = findSetNumber(m6set1,m6set2,m6set3);

                incrementScoreAway(match6,m6set1,m6set2,m6set3,setNumberm6);

                switch(setNumberm6){
                    case 1:{
                        m6s1a.setText(String.valueOf(m6set1.getAwayScore()));
                        break;
                    }
                    case 2:{
                        m6s2a.setText(String.valueOf(m6set2.getAwayScore()));
                        break;
                    }
                    case 3:{
                        m6s3a.setText(String.valueOf(m6set3.getAwayScore()));
                        break;
                    }
                }
            }
        });

        m6hd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumberm1 = findSetNumber(m6set1, m6set2, m6set3);
                decrementScoreHome(match6,m6set1,m6set2, m6set3, setNumberm6);
                m6s1h.setText(String.valueOf(m6set1.getHomeScore()));
                m6s1a.setText(String.valueOf(m6set1.getAwayScore()));

                m6s2h.setText(String.valueOf(m6set2.getHomeScore()));
                m6s2a.setText(String.valueOf(m6set2.getAwayScore()));

                m6s3h.setText(String.valueOf(m6set3.getHomeScore()));
                m6s3a.setText(String.valueOf(m6set3.getAwayScore()));
            }
        });

        m6ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumberm6 = findSetNumber(m6set1, m6set2, m6set3);
                decrementScoreAway(match6,m6set1,m6set2, m6set3, setNumberm6);
                m6s1h.setText(String.valueOf(m6set1.getHomeScore()));
                m6s1a.setText(String.valueOf(m6set1.getAwayScore()));

                m6s2h.setText(String.valueOf(m6set2.getHomeScore()));
                m6s2a.setText(String.valueOf(m6set2.getAwayScore()));

                m6s3h.setText(String.valueOf(m6set3.getHomeScore()));
                m6s3a.setText(String.valueOf(m6set3.getAwayScore()));
            }
        });


    }

    public void updateSet( final MatchSet changedSet){

        progressDialog.setMessage("Updating Set...");
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_UPDATE_SET,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("setID", String.valueOf(changedSet.getSetID()));
                params.put("matchID", String.valueOf(changedSet.getMatchID()));
                params.put("homeScore", String.valueOf(changedSet.getHomeScore()));
                params.put("awayScore", String.valueOf(changedSet.getAwayScore()));
                params.put("result", changedSet.getResult());
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void updateMatch(final Matches changedMatch){

        progressDialog.setMessage("Updating Match...");
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_UPDATE_MATCH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), " It didn't Work", Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("matchID", String.valueOf(changedMatch.getMatchID()));
                params.put("eventID", String.valueOf(changedMatch.getEventID()));
                params.put("homeTeamSets", String.valueOf(changedMatch.getHomeTeamSets()));
                params.put("awayTeamSets", String.valueOf(changedMatch.getAwayTeamSets()));
                params.put("result", changedMatch.getResult());
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }

    public boolean checkMatchWin(Matches target){
        if((target.getHomeTeamSets() == 2)||(target.getAwayTeamSets()==2)){
            return true;
        }else{
            return false;
        }
    }

    private void incrementScoreAway(Matches changedMatch, MatchSet changedSet1, MatchSet changedSet2, MatchSet changedSet3, int setNumber){

        if(checkMatchWin(changedMatch)){
            setNumber = 0;
            // Toast.makeText(getApplicationContext(), " Game is over, winner: " + changedMatch.getResult(), Toast.LENGTH_LONG).show();
        }
        switch(setNumber){
            case 1:{
                if (changedSet1.getAwayScore() == 6){
                    //set won
                    changedMatch.setAwayTeamSets(changedMatch.getAwayTeamSets()+1);
                    changedSet1.setAwayScore(changedSet1.getAwayScore()+1);
                    changedSet1.setResult("Complete - Away");
                    updateSet(changedSet1);
                    updateMatch(changedMatch);

                }else if(changedSet1.getAwayScore()==5){
                    if(changedSet1.getHomeScore() < 5){
                        //set won
                        changedMatch.setAwayTeamSets(changedMatch.getAwayTeamSets()+1);
                        changedSet1.setAwayScore(changedSet1.getAwayScore()+1);
                        changedSet1.setResult("Complete - Away");
                        updateSet(changedSet1);
                        updateMatch(changedMatch);
                    }else{
                        //increment
                        changedSet1.setAwayScore(changedSet1.getAwayScore()+1);
                        updateSet(changedSet1);
                    }
                }else{ //increment
                    changedSet1.setAwayScore(changedSet1.getAwayScore()+1);
                    updateSet(changedSet1);
                }
                break;
            }
            case 2:{

                if (changedSet2.getAwayScore() == 6){
                    //set won
                    changedMatch.setAwayTeamSets(changedMatch.getAwayTeamSets()+1);
                    changedSet2.setAwayScore(changedSet2.getAwayScore()+1);
                    changedSet2.setResult("Complete - Away");
                    if(checkMatchWin(changedMatch)){
                        changedMatch.setResult("Complete - Away");
                    }
                    updateSet(changedSet2);
                    updateMatch(changedMatch);

                }else if(changedSet2.getAwayScore()==5){
                    if(changedSet2.getHomeScore() < 5){
                        //set won
                        changedMatch.setAwayTeamSets(changedMatch.getAwayTeamSets()+1);
                        changedSet2.setAwayScore(changedSet2.getAwayScore()+1);
                        changedSet2.setResult("Complete - Away");
                        if(checkMatchWin(changedMatch)){
                            changedMatch.setResult("Complete - Away");
                        }
                        updateSet(changedSet2);
                        updateMatch(changedMatch);
                    }else{
                        //increment
                        changedSet2.setAwayScore(changedSet2.getAwayScore()+1);
                        updateSet(changedSet2);
                    }
                }else{ //increment
                    changedSet2.setAwayScore(changedSet2.getAwayScore()+1);
                    updateSet(changedSet2);
                }
                break;
            }
            case 3:{
                if (changedSet3.getAwayScore() == 6){
                    //set won
                    changedMatch.setAwayTeamSets(changedMatch.getAwayTeamSets()+1);
                    changedSet3.setAwayScore(changedSet3.getAwayScore()+1);
                    changedSet3.setResult("Complete - Away");
                    changedMatch.setResult("Complete - Away");

                    updateSet(changedSet3);
                    updateMatch(changedMatch);

                }else if(changedSet3.getAwayScore()==5){
                    if(changedSet3.getHomeScore()< 5){
                        //set won
                        changedMatch.setAwayTeamSets(changedMatch.getAwayTeamSets()+1);
                        changedSet3.setAwayScore(changedSet3.getAwayScore()+1);
                        changedSet3.setResult("Complete - Away");
                        changedMatch.setResult("Complete - Away");

                        updateSet(changedSet3);
                        updateMatch(changedMatch);
                    }else{
                        //increment
                        changedSet3.setAwayScore(changedSet3.getAwayScore()+1);
                        updateSet(changedSet3);

                    }
                }else{
                    //increment
                    changedSet3.setAwayScore(changedSet3.getAwayScore()+1);
                    updateSet(changedSet3);
                }
                break;

            }
        }
    }

    public void incrementScoreHome(Matches changedMatch, MatchSet changedSet1, MatchSet changedSet2, MatchSet changedSet3, int setNumber){

        if(checkMatchWin(changedMatch)){
            setNumber = 0;
            // Toast.makeText(getApplicationContext(), " Game is over, winner: " + changedMatch.getResult(), Toast.LENGTH_LONG).show();
        }
        switch(setNumber){
            case 1:{
                if (changedSet1.getHomeScore() == 6){
                    //set won
                    changedMatch.setHomeTeamSets(changedMatch.getHomeTeamSets()+1);
                    changedSet1.setHomeScore(changedSet1.getHomeScore()+1);
                    changedSet1.setResult("Complete - Home");
                    updateSet(changedSet1);
                    updateMatch(changedMatch);

                }else if(changedSet1.getHomeScore()==5){
                    if(changedSet1.getAwayScore() < 5){
                        //set won
                        changedMatch.setHomeTeamSets(changedMatch.getHomeTeamSets()+1);
                        changedSet1.setHomeScore(changedSet1.getHomeScore()+1);
                        changedSet1.setResult("Complete - Home");
                        updateSet(changedSet1);
                        updateMatch(changedMatch);
                    }else{
                        //increment
                        changedSet1.setHomeScore(changedSet1.getHomeScore()+1);
                        updateSet(changedSet1);
                    }
                }else{ //increment
                    changedSet1.setHomeScore(changedSet1.getHomeScore()+1);
                    updateSet(changedSet1);
                }
                break;
            }
            case 2:{

                if (changedSet2.getHomeScore() == 6){
                    //set won
                    changedMatch.setHomeTeamSets(changedMatch.getHomeTeamSets()+1);
                    changedSet2.setHomeScore(changedSet2.getHomeScore()+1);
                    changedSet2.setResult("Complete - Home");
                    if(checkMatchWin(changedMatch)){
                        changedMatch.setResult("Complete - Home");
                    }
                    updateSet(changedSet2);
                    updateMatch(changedMatch);

                }else if(changedSet2.getHomeScore()==5){
                    if(changedSet2.getAwayScore() < 5){
                        //set won
                        changedMatch.setHomeTeamSets(changedMatch.getHomeTeamSets()+1);
                        changedSet2.setHomeScore(changedSet2.getHomeScore()+1);
                        changedSet2.setResult("Complete - Home");
                        if(checkMatchWin(changedMatch)){
                            changedMatch.setResult("Complete - Home");
                        }
                        updateSet(changedSet2);
                        updateMatch(changedMatch);
                    }else{
                        //increment
                        changedSet2.setHomeScore(changedSet2.getHomeScore()+1);
                        updateSet(changedSet2);
                    }
                }else{ //increment
                    changedSet2.setHomeScore(changedSet2.getHomeScore()+1);
                    updateSet(changedSet2);
                }
                break;
            }
            case 3:{
                if (changedSet3.getHomeScore() == 6){
                    //set won
                    changedMatch.setHomeTeamSets(changedMatch.getHomeTeamSets()+1);
                    changedSet3.setHomeScore(changedSet3.getHomeScore()+1);
                    changedSet3.setResult("Complete - Home");
                    changedMatch.setResult("Complete - Home");

                    updateSet(changedSet3);
                    updateMatch(changedMatch);

                }else if(changedSet3.getHomeScore()==5){
                    if(changedSet3.getAwayScore()< 5){
                        //set won
                        changedMatch.setHomeTeamSets(changedMatch.getHomeTeamSets()+1);
                        changedSet3.setHomeScore(changedSet3.getHomeScore()+1);
                        changedSet3.setResult("Complete - Home");
                        changedMatch.setResult("Complete - Home");

                        updateSet(changedSet3);
                        updateMatch(changedMatch);
                    }else{
                        //increment
                        changedSet3.setHomeScore(changedSet3.getHomeScore()+1);
                        updateSet(changedSet3);

                    }
                }else{
                    //increment
                    changedSet3.setHomeScore(changedSet3.getHomeScore()+1);
                    updateSet(changedSet3);
                }
                break;

            }
        }
    }

    public void decrementScoreHome(Matches changedMatch, MatchSet changedSet1, MatchSet changedSet2, MatchSet changedSet3, int setNumber){

        if(changedMatch.getResult().equals("Complete - Away")){
            setNumber = 0;
        }

        switch(setNumber){
            case 1:{
                if((changedSet1.getHomeScore()== 5)&& (changedSet1.getAwayScore() == 6)){
                    changedSet1.setHomeScore(changedSet1.getHomeScore()-1);
                    changedSet1.setResult("Complete - Away");
                    updateSet(changedSet1);

                }else if(changedSet1.getHomeScore() > 0){
                    changedSet1.setHomeScore(changedSet1.getHomeScore()-1);
                    updateSet(changedSet1);
                }
                break;
            }
            case 2:{
                if((changedSet2.getHomeScore()== 5)&& (changedSet2.getAwayScore() == 6)){
                    changedSet2.setHomeScore(changedSet2.getHomeScore()-1);
                    changedSet2.setResult("Complete - Away");
                    changedMatch.setAwayTeamSets(changedMatch.getAwayTeamSets()+1);
                    updateSet(changedSet2);
                    updateMatch(changedMatch);

                }else if(changedSet2.getHomeScore() > 0){
                    changedSet2.setHomeScore(changedSet2.getHomeScore()-1);
                    updateSet(changedSet2);
                }else if(changedSet2.getHomeScore()==  0){
                    if(changedSet1.getResult().equals("Complete - Home")){
                        changedMatch.setHomeTeamSets(changedMatch.getHomeTeamSets()-1);
                        changedSet1.setResult("In Progress");
                        changedSet1.setHomeScore(changedSet1.getHomeScore()-1);
                        changedSet2.setAwayScore(0);
                        updateSet(changedSet1);
                        updateSet(changedSet2);
                        updateMatch(changedMatch);
                    }
                }
                break;
            }
            case 3:{
                if((changedSet3.getHomeScore()== 5)&& (changedSet3.getAwayScore() == 6)){
                    changedSet3.setHomeScore(changedSet3.getHomeScore()-1);
                    changedSet3.setResult("Complete - Away");
                    updateSet(changedSet3);
                    updateMatch(changedMatch);
                }else if(changedSet3.getHomeScore() > 0){
                    changedSet3.setHomeScore(changedSet3.getHomeScore()-1);
                    updateSet(changedSet3);
                }else if(changedSet3.getHomeScore()==0){
                    if(changedSet2.getResult().equals("Complete - Home")){
                        changedMatch.setHomeTeamSets(changedMatch.getHomeTeamSets()-1);
                        changedSet2.setResult("In Progress");
                        changedSet2.setHomeScore(changedSet2.getHomeScore()-1);
                        changedSet3.setAwayScore(0);
                        updateSet(changedSet2);
                        updateSet(changedSet3);
                        updateMatch(changedMatch);
                    }
                }
            }
        }

    }

    public void decrementScoreAway(Matches changedMatch, MatchSet changedSet1, MatchSet changedSet2, MatchSet changedSet3, int setNumber){

        if(changedMatch.getResult().equals("Complete - Home")){
            setNumber = 0;
        }

        switch(setNumber){
            case 1:{
                if((changedSet1.getAwayScore()== 5)&& (changedSet1.getHomeScore() == 6)){
                    changedSet1.setAwayScore(changedSet1.getAwayScore()-1);
                    changedSet1.setResult("Complete - Home");
                    updateSet(changedSet1);

                }else if(changedSet1.getAwayScore() > 0){
                    changedSet1.setAwayScore(changedSet1.getAwayScore()-1);
                    updateSet(changedSet1);
                }
                break;
            }
            case 2:{
                if((changedSet2.getAwayScore()== 5)&& (changedSet2.getHomeScore() == 6)){
                    changedSet2.setAwayScore(changedSet2.getAwayScore()-1);
                    changedSet2.setResult("Complete - Home");
                    changedMatch.setHomeTeamSets(changedMatch.getHomeTeamSets()+1);
                    updateSet(changedSet2);
                    updateMatch(changedMatch);

                }else if(changedSet2.getAwayScore() > 0){
                    changedSet2.setAwayScore(changedSet2.getAwayScore()-1);
                    updateSet(changedSet2);
                }else if(changedSet2.getAwayScore()==  0){
                    if(changedSet1.getResult().equals("Complete - Away")){
                        changedMatch.setAwayTeamSets(changedMatch.getAwayTeamSets()-1);
                        changedSet1.setResult("In Progress");
                        changedSet1.setAwayScore(changedSet1.getAwayScore()-1);
                        changedSet2.setHomeScore(0);
                        updateSet(changedSet1);
                        updateSet(changedSet2);
                        updateMatch(changedMatch);
                    }
                }
                break;
            }
            case 3:{
                if((changedSet3.getAwayScore()== 5)&& (changedSet3.getHomeScore() == 6)){
                    changedSet3.setAwayScore(changedSet3.getAwayScore()-1);
                    changedSet3.setResult("Complete - Home");
                    updateSet(changedSet3);
                    updateMatch(changedMatch);
                }else if(changedSet3.getAwayScore() > 0){
                    changedSet3.setAwayScore(changedSet3.getAwayScore()-1);
                    updateSet(changedSet3);
                }else if(changedSet3.getAwayScore()==0){
                    if(changedSet2.getResult().equals("Complete - Away")){
                        changedMatch.setAwayTeamSets(changedMatch.getAwayTeamSets()-1);
                        changedSet2.setResult("In Progress");
                        changedSet2.setAwayScore(changedSet2.getAwayScore()-1);
                        changedSet3.setHomeScore(0);
                        updateSet(changedSet2);
                        updateSet(changedSet3);
                        updateMatch(changedMatch);
                    }
                }
            }
        }

    }

    public int findSetNumber( MatchSet set1, MatchSet set2, MatchSet set3){
        int number = 0;
        if(set1.getResult().equals("In Progress")){
            number = 1;
        }else if(set2.getResult().equals("In Progress")){
            number = 2;
        }else if(set3.getResult().equals("In Progress")){
            number = 3;
        }
        return number;
    }

    public void getMatch(){
        //   progressDialog.show();



        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GET_MATCH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            Log.i("tagconvertstr", "["+response+"]");
                            JSONObject obj = new JSONObject(response);
                            if(!obj.getBoolean("error")){




                                match1.setMatchType(obj.getString("matchType"));
                                match1.setHomePlayer1(obj.getString("homePlayer1"));
                                match1.setHomePlayer2(obj.getString("homePlayer2"));
                                match1.setAwayPlayer1(obj.getString("awayPlayer1"));
                                match1.setAwayPlayer2(obj.getString("awayPlayer2"));
                                match1.setHomeTeamSets(obj.getInt("homeTeamSets"));
                                match1.setAwayTeamSets(obj.getInt("awayTeamSets"));
                                match1.setResult(obj.getString("result"));
                                // Toast.makeText(getApplicationContext(), match1.getHomePlayer1(),Toast.LENGTH_LONG).show();



                            }else{
                                Toast.makeText(getApplicationContext(), "error occurred", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "here",Toast.LENGTH_LONG).show();

                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("matchID", String.valueOf(match1.getMatchID()));
                params.put("eventID", String.valueOf(match1.getEventID()));
                return params;
            }
        };



        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void  getMatches(){
        progressDialog.show();



        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GET_MATCHES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            Log.i("tagconvertstr", "["+response+"]");
                            JSONArray array = new JSONArray(response);

                            for(int i = 0; i < array.length(); i++){
                                JSONObject obj = array.getJSONObject(i);
                                Matches temp = new Matches(obj.getInt("matchID"),
                                        obj.getInt("eventID"),
                                        obj.getString("matchType"),
                                        obj.getString("homePlayer1"),
                                        obj.getString("homePlayer2"),
                                        obj.getString("awayPlayer1"),
                                        obj.getString("awayPlayer2"),
                                        obj.getInt("homeTeamSets"),
                                        obj.getInt("awayTeamSets"),
                                        obj.getString("result"));
                                matchList.add(temp);}



                              /*  for(int i = 0; i < matchList.size(); i++){
                                    matchNum = String.valueOf(i+1);
                                    matchPlayerSet = "m" + matchNum + "hp";
                                    matchPlayerSet.toString().setText("");
                                }*/

                            match1 = matchList.get(0);
                            match2 = matchList.get(1);
                            match3 = matchList.get(2);
                            match4 = matchList.get(3);
                            match5 = matchList.get(4);
                            match6 = matchList.get(5);

                            m1hp.setText(match1.getHomePlayer1()); m1ap.setText(match1.getAwayPlayer1());

                            m2hp.setText(match2.getHomePlayer1()); m2ap.setText(match2.getAwayPlayer1());

                            m3hp.setText(match3.getHomePlayer1()); m3ap.setText(match3.getAwayPlayer1());

                            m4hp.setText(match4.getHomePlayer1()); m4ap.setText(match4.getAwayPlayer1());

                            m5hp.setText(match5.getHomePlayer1()); m5ap.setText(match5.getAwayPlayer1());

                            m6hp.setText(match6.getHomePlayer1()); m6ap.setText(match6.getAwayPlayer1());




                        } catch (JSONException e) {
                            //Toast.makeText(getApplicationContext(), "here",Toast.LENGTH_LONG).show();

                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("eventID", String.valueOf(eventID));
                params.put("matchType", matchType);
                return params;
            }
        };



        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }
    public void getSetsM1(){
        //   progressDialog.show();



        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GET_SETS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            Log.i("tagconvertstr", "["+response+"]");
                            JSONArray array = new JSONArray(response);

                            for(int i = 0; i < array.length(); i++){
                                JSONObject obj = array.getJSONObject(i);
                                MatchSet temp = new MatchSet(obj.getInt("setID"),
                                        obj.getInt("matchID"),
                                        obj.getInt("homeScore"),
                                        obj.getInt("awayScore"),
                                        obj.getString("result"));
                                setList.add(temp);}

                            m1set1 = setList.get(0); m1set2 = setList.get(1); m1set3 = setList.get(2);

                            m1s1h.setText(String.valueOf(m1set1.getHomeScore())); m1s1a.setText(String.valueOf(m1set1.getAwayScore()));
                            m1s2h.setText(String.valueOf(m1set2.getHomeScore())); m1s2a.setText(String.valueOf(m1set2.getAwayScore()));
                            m1s3h.setText(String.valueOf(m1set3.getHomeScore())); m1s3a.setText(String.valueOf(m1set3.getAwayScore()));




                        } catch (JSONException e) {
                            //Toast.makeText(getApplicationContext(), "here",Toast.LENGTH_LONG).show();

                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("matchID", String.valueOf(match1.getMatchID()));
                params.put("matchType", "Singles");



                return params;
            }
        };



        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }

    public void getSetsM2(){
        //   progressDialog.show();



        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GET_SETS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            Log.i("tagconvertstr", "["+response+"]");
                            JSONArray array = new JSONArray(response);

                            for(int i = 0; i < array.length(); i++){
                                JSONObject obj = array.getJSONObject(i);
                                MatchSet temp = new MatchSet(obj.getInt("setID"),
                                        obj.getInt("matchID"),
                                        obj.getInt("homeScore"),
                                        obj.getInt("awayScore"),
                                        obj.getString("result"));
                                setList.add(temp);}

                            m2set1 = setList.get(0); m2set2 = setList.get(1); m2set3 = setList.get(2);


                            m2s1h.setText(String.valueOf(m2set1.getHomeScore())); m2s1a.setText(String.valueOf(m2set1.getAwayScore()));
                            m2s2h.setText(String.valueOf(m2set2.getHomeScore())); m2s2a.setText(String.valueOf(m2set2.getAwayScore()));
                            m2s3h.setText(String.valueOf(m2set3.getHomeScore())); m2s3a.setText(String.valueOf(m2set3.getAwayScore()));


                        } catch (JSONException e) {
                            //Toast.makeText(getApplicationContext(), "here",Toast.LENGTH_LONG).show();

                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("matchID", String.valueOf(match2.getMatchID()));
                params.put("matchType", "Singles");



                return params;
            }
        };



        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }

    public void getSetsM3(){
        //   progressDialog.show();



        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GET_SETS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            Log.i("tagconvertstr", "["+response+"]");
                            JSONArray array = new JSONArray(response);

                            for(int i = 0; i < array.length(); i++){
                                JSONObject obj = array.getJSONObject(i);
                                MatchSet temp = new MatchSet(obj.getInt("setID"),
                                        obj.getInt("matchID"),
                                        obj.getInt("homeScore"),
                                        obj.getInt("awayScore"),
                                        obj.getString("result"));
                                setList.add(temp);}


                            m3set1 = setList.get(0); m3set2 = setList.get(1); m3set3 = setList.get(2);



                            m3s1h.setText(String.valueOf(m3set1.getHomeScore())); m3s1a.setText(String.valueOf(m3set1.getAwayScore()));
                            m3s2h.setText(String.valueOf(m3set2.getHomeScore())); m3s2a.setText(String.valueOf(m3set2.getAwayScore()));
                            m3s3h.setText(String.valueOf(m3set3.getHomeScore())); m3s3a.setText(String.valueOf(m3set3.getAwayScore()));


                        } catch (JSONException e) {
                            //Toast.makeText(getApplicationContext(), "here",Toast.LENGTH_LONG).show();

                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("matchID", String.valueOf(match3.getMatchID()));
                params.put("matchType", "Singles");



                return params;
            }
        };



        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }

    public void getSetsM4(){
        //   progressDialog.show();



        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GET_SETS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            Log.i("tagconvertstr", "["+response+"]");
                            JSONArray array = new JSONArray(response);

                            for(int i = 0; i < array.length(); i++){
                                JSONObject obj = array.getJSONObject(i);
                                MatchSet temp = new MatchSet(obj.getInt("setID"),
                                        obj.getInt("matchID"),
                                        obj.getInt("homeScore"),
                                        obj.getInt("awayScore"),
                                        obj.getString("result"));
                                setList.add(temp);}


                            m4set1 = setList.get(0); m4set2 = setList.get(1); m4set3 = setList.get(2);



                            m4s1h.setText(String.valueOf(m4set1.getHomeScore())); m4s1a.setText(String.valueOf(m4set1.getAwayScore()));
                            m4s2h.setText(String.valueOf(m3set2.getHomeScore())); m4s2a.setText(String.valueOf(m4set2.getAwayScore()));
                            m4s3h.setText(String.valueOf(m4set3.getHomeScore())); m4s3a.setText(String.valueOf(m4set3.getAwayScore()));


                        } catch (JSONException e) {
                            //Toast.makeText(getApplicationContext(), "here",Toast.LENGTH_LONG).show();

                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("matchID", String.valueOf(match4.getMatchID()));
                params.put("matchType", "Singles");



                return params;
            }
        };



        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }

    public void getSetsM5(){
        //   progressDialog.show();



        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GET_SETS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            Log.i("tagconvertstr", "["+response+"]");
                            JSONArray array = new JSONArray(response);

                            for(int i = 0; i < array.length(); i++){
                                JSONObject obj = array.getJSONObject(i);
                                MatchSet temp = new MatchSet(obj.getInt("setID"),
                                        obj.getInt("matchID"),
                                        obj.getInt("homeScore"),
                                        obj.getInt("awayScore"),
                                        obj.getString("result"));
                                setList.add(temp);}


                            m5set1 = setList.get(0); m5set2 = setList.get(1); m5set3 = setList.get(2);

                            m5s1h.setText(String.valueOf(m5set1.getHomeScore())); m5s1a.setText(String.valueOf(m5set1.getAwayScore()));
                            m5s2h.setText(String.valueOf(m5set2.getHomeScore())); m5s2a.setText(String.valueOf(m5set2.getAwayScore()));
                            m5s3h.setText(String.valueOf(m5set3.getHomeScore())); m5s3a.setText(String.valueOf(m5set3.getAwayScore()));



                        } catch (JSONException e) {
                            //Toast.makeText(getApplicationContext(), "here",Toast.LENGTH_LONG).show();

                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("matchID", String.valueOf(match5.getMatchID()));
                params.put("matchType", "Singles");



                return params;
            }
        };



        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }

    public void getSetsM6(){
        //   progressDialog.show();



        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GET_SETS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            Log.i("tagconvertstr", "["+response+"]");
                            JSONArray array = new JSONArray(response);

                            for(int i = 0; i < array.length(); i++){
                                JSONObject obj = array.getJSONObject(i);
                                MatchSet temp = new MatchSet(obj.getInt("setID"),
                                        obj.getInt("matchID"),
                                        obj.getInt("homeScore"),
                                        obj.getInt("awayScore"),
                                        obj.getString("result"));
                                setList.add(temp);}


                            m6set1 = setList.get(0); m6set2 = setList.get(1); m6set3 = setList.get(2);


                            m6s1h.setText(String.valueOf(m6set1.getHomeScore())); m6s1a.setText(String.valueOf(m6set1.getAwayScore()));
                            m6s2h.setText(String.valueOf(m6set2.getHomeScore())); m6s2a.setText(String.valueOf(m6set2.getAwayScore()));
                            m6s3h.setText(String.valueOf(m6set3.getHomeScore())); m6s3a.setText(String.valueOf(m6set3.getAwayScore()));


                        } catch (JSONException e) {
                            //Toast.makeText(getApplicationContext(), "here",Toast.LENGTH_LONG).show();

                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("matchID", String.valueOf(match6.getMatchID()));
                params.put("matchType", "Singles");



                return params;
            }
        };



        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }

}



