package com.example.topspin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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

public class ScoreByGame extends AppCompatActivity {

    private ProgressDialog progressDialog;

    private int eventID = 1;
    private int setNumberm1;
    private int setNumberm2;
    private int setNumberm3;

    private String matchType = "Singles";


    ArrayList<TextView> views = new ArrayList<>();
    ArrayList<Matches> matchList = new ArrayList<>();
    ArrayList<MatchSet> setList = new ArrayList<>();



   private Matches match1, match2, match3;
   private MatchSet m1set1, m1set2, m1set3;
   private MatchSet m2set1, m2set2, m2set3;
   private MatchSet m3set1, m3set2, m3set3;

    private TextView m1hp, m1ap;
    private TextView m2hp, m2ap;
    private TextView m3hp, m3ap;

    private TextView m1s1h, m1s2h, m1s3h, m1s1a, m1s2a, m1s3a;
    private TextView m2s1h, m2s2h, m2s3h, m2s1a, m2s2a, m2s3a;
    private TextView m3s1h, m3s2h, m3s3h, m3s1a, m3s2a, m3s3a;

    private Button m1hi, m1hd, m1ai, m1ad;
    private Button m2hi, m2hd, m2ai, m2ad;
    private Button m3hi, m3hd, m3ai, m3ad;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_by_game);
        /*
        //Get the eventID from the calling activity
        Intent getID = getIntent();
        eventID = getID.getIntExtra("EVENTID", 0);
        matchType = getID.getStringExtra("MATCHTYPE");//*/


        //Set player Views 'm' = match number, 'h' = home, 'a' = away, 'p' = player
        m1hp = findViewById(R.id.Match1HomePlayer); m1ap = findViewById(R.id.Match1AwayPlayer);
        m2hp = findViewById(R.id.M2HomePlayer); m2ap = findViewById(R.id.M2AwayPlayer);
        m3hp = findViewById(R.id.M3HomePlayer); m3ap = findViewById(R.id.M3AwayPlayer);

        //set set column views 'm' = match number, 'h' = home, 'a' = away, 's' = set number
        m1s1h = findViewById(R.id.M1S1H); m1s2h = findViewById(R.id.M1S2H); m1s3h = findViewById(R.id.M1S3H);
        m1s1a = findViewById(R.id.M1S1A); m1s2a = findViewById(R.id.M1S2A); m1s3a = findViewById(R.id.M1S3A);

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



        progressDialog = new ProgressDialog(this);

        // Load dummy matches, will be removed or commented out when database connected
        match1 = new Matches(1, 1, "Singles", "", "", "","", 0, 0, "In Progress");
        match2 = new Matches(2, 1, "Singles", "", "", "","", 0, 0, "In Progress");
        match3 = new Matches(3, 1, "Singles", "", "", "","", 0, 0, "In Progress");

        m1set1 = new  MatchSet(1,1, 0,0,"In Progress");
        m1set2 = new MatchSet(1,2,0,0,"In Progress");
        m1set3 = new MatchSet(1,3,0,0,"In Progress");

        m2set1 = new MatchSet(2,4,0,0,"In Progress");
        m2set2 = new MatchSet(2,5, 0,0,"In Progress");
        m2set3 = new MatchSet(2,6,0,0,"In Progress");

        m3set1 = new MatchSet(3,7, 0,0,"In Progress");
        m3set2 = new MatchSet(3,8, 0,0,"In Progress");
        m3set3 = new MatchSet(3,9, 0,0,"In Progress");




        getMatches();
        getSetsM1();
        getSetsM2();
        getSetsM3();

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
//


        updateMatch(match1);
        updateMatch(match2);
        updateMatch(match3);

        updateSet(m1set1);
        updateSet(m1set2);
        updateSet(m1set3);

        updateSet(m2set1);
        updateSet(m2set2);
        updateSet(m2set3);

        updateSet(m3set1);
        updateSet(m3set2);
        updateSet(m3set3);




        /**
         * This is the start of Match 1's icrement listeners
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


        /**
         * This is the start of match 2's increment listeners
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
        });//*/


        //Start of Decrement Listeners
        //match1
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

        ///Match2

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


        ///Match3

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
                    changedSet1.setResult(changedMatch.getAwayPlayer1());
                    updateSet(changedSet1);
                    updateMatch(changedMatch);

                }else if(changedSet1.getAwayScore()==5){
                    if(changedSet1.getHomeScore() < 5){
                        //set won
                        changedMatch.setAwayTeamSets(changedMatch.getAwayTeamSets()+1);
                        changedSet1.setAwayScore(changedSet1.getAwayScore()+1);
                        changedSet1.setResult(changedMatch.getAwayPlayer1());
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
                    changedSet2.setResult(changedMatch.getAwayPlayer1());
                    if(checkMatchWin(changedMatch)){
                        changedMatch.setResult(changedMatch.getAwayPlayer1());
                    }
                    updateSet(changedSet2);
                    updateMatch(changedMatch);

                }else if(changedSet2.getAwayScore()==5){
                    if(changedSet2.getHomeScore() < 5){
                        //set won
                        changedMatch.setAwayTeamSets(changedMatch.getAwayTeamSets()+1);
                        changedSet2.setAwayScore(changedSet2.getAwayScore()+1);
                        changedSet2.setResult(changedMatch.getAwayPlayer1());
                        if(checkMatchWin(changedMatch)){
                            changedMatch.setResult(changedMatch.getAwayPlayer1());
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
                    changedSet3.setResult(changedMatch.getAwayPlayer1());
                    changedMatch.setResult(changedMatch.getAwayPlayer1());

                    updateSet(changedSet3);
                    updateMatch(changedMatch);

                }else if(changedSet3.getAwayScore()==5){
                    if(changedSet3.getHomeScore()< 5){
                        //set won
                        changedMatch.setAwayTeamSets(changedMatch.getAwayTeamSets()+1);
                        changedSet3.setAwayScore(changedSet3.getAwayScore()+1);
                        changedSet3.setResult(changedMatch.getAwayPlayer1());
                        changedMatch.setResult(changedMatch.getAwayPlayer1());

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
                    changedSet1.setResult(changedMatch.getHomePlayer1());
                    updateSet(changedSet1);
                    updateMatch(changedMatch);

                }else if(changedSet1.getHomeScore()==5){
                    if(changedSet1.getAwayScore() < 5){
                        //set won
                        changedMatch.setHomeTeamSets(changedMatch.getHomeTeamSets()+1);
                        changedSet1.setHomeScore(changedSet1.getHomeScore()+1);
                        changedSet1.setResult(changedMatch.getHomePlayer1());
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
                    changedSet2.setResult(changedMatch.getHomePlayer1());
                    if(checkMatchWin(changedMatch)){
                        changedMatch.setResult(changedMatch.getHomePlayer1());
                    }
                    updateSet(changedSet2);
                    updateMatch(changedMatch);

                }else if(changedSet2.getHomeScore()==5){
                    if(changedSet2.getAwayScore() < 5){
                        //set won
                        changedMatch.setHomeTeamSets(changedMatch.getHomeTeamSets()+1);
                        changedSet2.setHomeScore(changedSet2.getHomeScore()+1);
                        changedSet2.setResult(changedMatch.getHomePlayer1());
                        if(checkMatchWin(changedMatch)){
                            changedMatch.setResult(changedMatch.getHomePlayer1());
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
                    changedSet3.setResult(changedMatch.getHomePlayer1());
                    changedMatch.setResult(changedMatch.getHomePlayer1());

                    updateSet(changedSet3);
                    updateMatch(changedMatch);

                }else if(changedSet3.getHomeScore()==5){
                    if(changedSet3.getAwayScore()< 5){
                        //set won
                        changedMatch.setHomeTeamSets(changedMatch.getHomeTeamSets()+1);
                        changedSet3.setHomeScore(changedSet3.getHomeScore()+1);
                        changedSet3.setResult(changedMatch.getHomePlayer1());
                        changedMatch.setResult(changedMatch.getHomePlayer1());

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

        if(changedMatch.getResult().equals(changedMatch.getAwayPlayer1())){
            setNumber = 0;
        }

        switch(setNumber){
            case 1:{
                if((changedSet1.getHomeScore()== 5)&& (changedSet1.getAwayScore() == 6)){
                    changedSet1.setHomeScore(changedSet1.getHomeScore()-1);
                    changedSet1.setResult(changedMatch.getAwayPlayer1());
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
                    changedSet2.setResult(changedMatch.getAwayPlayer1());
                    changedMatch.setAwayTeamSets(changedMatch.getAwayTeamSets()+1);
                    updateSet(changedSet2);
                    updateMatch(changedMatch);

                }else if(changedSet2.getHomeScore() > 0){
                    changedSet2.setHomeScore(changedSet2.getHomeScore()-1);
                    updateSet(changedSet2);
                }else if(changedSet2.getHomeScore()==  0){
                    if(changedSet1.getResult().equals(changedMatch.getHomePlayer1())){
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
                    changedSet3.setResult(changedMatch.getAwayPlayer1());
                    updateSet(changedSet3);
                    updateMatch(changedMatch);
                }else if(changedSet3.getHomeScore() > 0){
                    changedSet3.setHomeScore(changedSet3.getHomeScore()-1);
                    updateSet(changedSet3);
                }else if(changedSet3.getHomeScore()==0){
                    if(changedSet2.getResult().equals(changedMatch.getHomePlayer1())){
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

        if(changedMatch.getResult().equals(changedMatch.getHomePlayer1())){
            setNumber = 0;
        }

        switch(setNumber){
            case 1:{
                if((changedSet1.getAwayScore()== 5)&& (changedSet1.getHomeScore() == 6)){
                    changedSet1.setAwayScore(changedSet1.getAwayScore()-1);
                    changedSet1.setResult(changedMatch.getHomePlayer1());
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
                    changedSet2.setResult(changedMatch.getHomePlayer1());
                    changedMatch.setHomeTeamSets(changedMatch.getHomeTeamSets()+1);
                    updateSet(changedSet2);
                    updateMatch(changedMatch);

                }else if(changedSet2.getAwayScore() > 0){
                    changedSet2.setAwayScore(changedSet2.getAwayScore()-1);
                    updateSet(changedSet2);
                }else if(changedSet2.getAwayScore()==  0){
                    if(changedSet1.getResult().equals(changedMatch.getAwayPlayer1())){
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
                    changedSet3.setResult(changedMatch.getHomePlayer1());
                    updateSet(changedSet3);
                    updateMatch(changedMatch);
                }else if(changedSet3.getAwayScore() > 0){
                    changedSet3.setAwayScore(changedSet3.getAwayScore()-1);
                    updateSet(changedSet3);
                }else if(changedSet3.getAwayScore()==0){
                    if(changedSet2.getResult().equals(changedMatch.getAwayPlayer1())){
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

                                m1hp.setText(match1.getHomePlayer1()); m1ap.setText(match1.getAwayPlayer1());


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

                            m1hp.setText(match1.getHomePlayer1()); m1ap.setText(match1.getAwayPlayer1());
                            m2hp.setText(match2.getHomePlayer1()); m2ap.setText(match2.getAwayPlayer1());
                            m3hp.setText(match3.getHomePlayer1()); m3ap.setText(match3.getAwayPlayer1());


                            m1hp.setText(match1.getHomePlayer1()); m1ap.setText(match1.getAwayPlayer1());


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




                            m1hp.setText(match1.getHomePlayer1()); m1ap.setText(match1.getAwayPlayer1());


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
                params.put("matchID", String.valueOf(1));
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



                            m1hp.setText(match1.getHomePlayer1()); m1ap.setText(match1.getAwayPlayer1());


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
                params.put("matchID", String.valueOf(2));
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


                            m1hp.setText(match1.getHomePlayer1()); m1ap.setText(match1.getAwayPlayer1());


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
                params.put("matchID", String.valueOf(3));
                params.put("matchType", "Singles");



                return params;
            }
        };



        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }

}
