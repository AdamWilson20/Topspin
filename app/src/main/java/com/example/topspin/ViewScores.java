package com.example.topspin;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.Timer;
import java.util.TimerTask;

public class ViewScores extends AppCompatActivity {

    private int eventID = 1;
    private int setNumberm1;
    private int setNumberm2;
    private int setNumberm3;
    private int setNumberm4;
    private int setNumberm5;
    private int setNumberm6;


    private boolean status = false;

    private Matches match1, match2, match3, match4, match5, match6, match7, match8, match9;
    private MatchSet m1set1, m1set2, m1set3;
    private MatchSet m2set1, m2set2, m2set3;
    private MatchSet m3set1, m3set2, m3set3;
    private MatchSet m4set1, m4set2, m4set3;
    private MatchSet m5set1, m5set2, m5set3;
    private MatchSet m6set1, m6set2, m6set3;
    private MatchSet m7set1, m7set2, m7set3;
    private MatchSet m8set1, m8set2, m8set3;
    private MatchSet m9set1, m9set2, m9set3;

    private TextView m1hp1, m1ap1, m1hp2, m1ap2;
    private TextView m2hp1, m2ap1, m2hp2, m2ap2;
    private TextView m3hp1, m3ap1, m3hp2, m3ap2;
    private TextView m4hp, m4ap;
    private TextView m5hp, m5ap;
    private TextView m6hp, m6ap;
    private TextView m7hp, m7ap;
    private TextView m8hp, m8ap;
    private TextView m9hp, m9ap;

    private Timer timer;
    private TimerTask timerTask;
    private Handler handler = new Handler();

    private TextView m1s1h, m1s2h, m1s3h, m1s1a, m1s2a, m1s3a;
    private TextView m2s1h, m2s2h, m2s3h, m2s1a, m2s2a, m2s3a;
    private TextView m3s1h, m3s2h, m3s3h, m3s1a, m3s2a, m3s3a;
    private TextView m4s1h, m4s2h, m4s3h, m4s1a, m4s2a, m4s3a;
    private TextView m5s1h, m5s2h, m5s3h, m5s1a, m5s2a, m5s3a;
    private TextView m6s1h, m6s2h, m6s3h, m6s1a, m6s2a, m6s3a;
    private TextView m7s1h, m7s2h, m7s3h, m7s1a, m7s2a, m7s3a;
    private TextView m8s1h, m8s2h, m8s3h, m8s1a, m8s2a, m8s3a;
    private TextView m9s1h, m9s2h, m9s3h, m9s1a, m9s2a, m9s3a;

    private TextView m1result;
    private TextView m2result;
    private TextView m3result;
    private TextView m4result;
    private TextView m5result;
    private TextView m6result;
    private TextView m7result;
    private TextView m8result;
    private TextView m9result;

    private TextView m1resultHead;
    private TextView m2resultHead;
    private TextView m3resultHead;
    private TextView m4resultHead;
    private TextView m5resultHead;
    private TextView m6resultHead;
    private TextView m7resultHead;
    private TextView m8resultHead;
    private TextView m9resultHead;

    private TextView singlesHeader;
    private TextView m1Header;
    private TextView m2Header;
    private TextView m3Header;
    private TextView m4Header;
    private TextView m5Header;
    private TextView m6Header;
    private TextView m7Header;
    private TextView m8Header;
    private TextView m9Header;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_scores);

        Intent getEventID = getIntent();
        getEventID.getIntExtra("eventID", 1);

        //Set player Views 'm' = match number, 'h' = home, 'a' = away, 'p' = player
        m1hp1 = findViewById(R.id.Match1HomePlayer1); m1ap1 = findViewById(R.id.Match1AwayPlayer1);
        m1hp2 = findViewById(R.id.Match1HomePlayer2); m1ap2 = findViewById(R.id.Match1AwayPlayer2);

        m2hp1 = findViewById(R.id.M2HomePlayer1); m2ap1 = findViewById(R.id.M2AwayPlayer1);
        m2hp2 = findViewById(R.id.M2HomePlayer2); m2ap2 = findViewById(R.id.M2AwayPlayer2);

        m3hp1 = findViewById(R.id.M3HomePlayer1); m3ap1 = findViewById(R.id.M3AwayPlayer1);
        m3hp2 = findViewById(R.id.M3HomePlayer2); m3ap2 = findViewById(R.id.M3AwayPlayer2);

        m4hp = findViewById(R.id.M4HomePlayer); m4ap = findViewById(R.id.M4AwayPlayer);

        m5hp = findViewById(R.id.M5HomePlayer); m5ap = findViewById(R.id.M5AwayPlayer);

        m6hp = findViewById(R.id.M6HomePlayer); m6ap = findViewById(R.id.M6AwayPlayer);

        m7hp = findViewById(R.id.M7HomePlayer); m7ap = findViewById(R.id.M7AwayPlayer);

        m8hp = findViewById(R.id.M8HomePlayer); m8ap = findViewById(R.id.M8AwayPlayer);

        m9hp = findViewById(R.id.M9HomePlayer); m9ap = findViewById(R.id.M9AwayPlayer);


        //set set column views 'm' = match number, 'h' = home, 'a' = away, 's' = set number

        m1s1h = findViewById(R.id.M1S1H); m1s2h = findViewById(R.id.M1S2H); m1s3h = findViewById(R.id.M1S3H);
        m1s1a = findViewById(R.id.M1S1A); m1s2a = findViewById(R.id.M1S2A); m1s3a = findViewById(R.id.M1S3A);

        m2s1h = findViewById(R.id.M2S1H); m2s2h = findViewById(R.id.M2S2H); m2s3h = findViewById(R.id.M2S3H);
        m2s1a = findViewById(R.id.M2S1A); m2s2a = findViewById(R.id.M2S2A); m2s3a = findViewById(R.id.M2S3A);

        m3s1h = findViewById(R.id.M3S1H); m3s2h = findViewById(R.id.M3S2H); m3s3h = findViewById(R.id.M3S3H);
        m3s1a = findViewById(R.id.M3S1A); m3s2a = findViewById(R.id.M3S2A); m3s3a = findViewById(R.id.M3S3A);

        m4s1h = findViewById(R.id.M4S1H); m4s2h = findViewById(R.id.M4S2H); m4s3h = findViewById(R.id.M4S3H);
        m4s1a = findViewById(R.id.M4S1A); m4s2a = findViewById(R.id.M4S2A); m4s3a = findViewById(R.id.M4S3A);

        m5s1h = findViewById(R.id.M5S1H); m5s2h = findViewById(R.id.M5S2H); m5s3h = findViewById(R.id.M5S3H);
        m5s1a = findViewById(R.id.M5S1A); m5s2a = findViewById(R.id.M5S2A); m5s3a = findViewById(R.id.M5S3A);

        m6s1h = findViewById(R.id.M6S1H); m6s2h = findViewById(R.id.M6S2H); m6s3h = findViewById(R.id.M6S3H);
        m6s1a = findViewById(R.id.M6S1A); m6s2a = findViewById(R.id.M6S2A); m6s3a = findViewById(R.id.M6S3A);

        m7s1h = findViewById(R.id.M7S1H); m7s2h = findViewById(R.id.M7S2H); m7s3h = findViewById(R.id.M7S3H);
        m7s1a = findViewById(R.id.M7S1A); m7s2a = findViewById(R.id.M7S2A); m7s3a = findViewById(R.id.M7S3A);

        m8s1h = findViewById(R.id.M8S1H); m8s2h = findViewById(R.id.M8S2H); m8s3h = findViewById(R.id.M8S3H);
        m8s1a = findViewById(R.id.M8S1A); m8s2a = findViewById(R.id.M8S2A); m8s3a = findViewById(R.id.M8S3A);

        m9s1h = findViewById(R.id.M9S1H); m9s2h = findViewById(R.id.M9S2H); m9s3h = findViewById(R.id.M9S3H);
        m9s1a = findViewById(R.id.M9S1A); m9s2a = findViewById(R.id.M9S2A); m9s3a = findViewById(R.id.M9S3A);


        m1result = findViewById(R.id.M1Result);
        m2result = findViewById(R.id.M2Result);
        m3result = findViewById(R.id.M3Result);
        m4result = findViewById(R.id.M4Result);
        m5result = findViewById(R.id.M5Result);
        m6result = findViewById(R.id.M6Result);
        m7result = findViewById(R.id.M7Result);
        m8result = findViewById(R.id.M8Result);
        m9result = findViewById(R.id.M9Result);

        m1resultHead = findViewById(R.id.M1ResultHead);
        m2resultHead = findViewById(R.id.M2ResultHead);
        m3resultHead = findViewById(R.id.M3ResultHead);
        m4resultHead = findViewById(R.id.M4ResultHead);
        m5resultHead = findViewById(R.id.M5ResultHead);
        m6resultHead = findViewById(R.id.M6ResultHead);
        m7resultHead = findViewById(R.id.M7ResultHead);
        m8resultHead = findViewById(R.id.M8ResultHead);
        m9resultHead = findViewById(R.id.M9ResultHead);

        m1resultHead.setVisibility(View.INVISIBLE); m1result.setVisibility(View.INVISIBLE);
        m2resultHead.setVisibility(View.INVISIBLE); m2result.setVisibility(View.INVISIBLE);
        m3resultHead.setVisibility(View.INVISIBLE); m3result.setVisibility(View.INVISIBLE);
        m4resultHead.setVisibility(View.INVISIBLE); m4result.setVisibility(View.INVISIBLE);
        m5resultHead.setVisibility(View.INVISIBLE); m5result.setVisibility(View.INVISIBLE);
        m6resultHead.setVisibility(View.INVISIBLE); m6result.setVisibility(View.INVISIBLE);
        m7resultHead.setVisibility(View.INVISIBLE); m7result.setVisibility(View.INVISIBLE);
        m8resultHead.setVisibility(View.INVISIBLE); m8result.setVisibility(View.INVISIBLE);
        m9resultHead.setVisibility(View.INVISIBLE); m9result.setVisibility(View.INVISIBLE);

        singlesHeader = findViewById(R.id.SinglesHeader);
        m1Header = findViewById(R.id.Match1Header);
        m2Header = findViewById(R.id.Match2header);
        m3Header = findViewById(R.id.Match3header);
        m4Header = findViewById(R.id.Match4header);
        m5Header = findViewById(R.id.Match5header);
        m6Header = findViewById(R.id.Match6header);
        m7Header = findViewById(R.id.Match7header);
        m8Header = findViewById(R.id.Match8header);
        m9Header = findViewById(R.id.Match9header);





        // Load dummy matches, will be removed or commented out when database connected
        match1 = new Matches(0 , eventID, "Doubles", null, null, null,null, 0, 0, "In Progress");
        match2 = new Matches(0, eventID, "Doubles", null, null, null,null, 0, 0, "In Progress");
        match3 = new Matches(0 , eventID, "Doubles", null, null, null,null, 0, 0, "In Progress");
        match4 = new Matches(0, eventID, "Singles", null, null, null,null, 0, 0, "In Progress");
        match5 = new Matches(0, eventID, "Singles", null, null, null,null, 0, 0, "In Progress");
        match6 = new Matches(0, eventID, "Singles", null, null, null,null, 0, 0, "In Progress");
        match7 = new Matches(0, eventID, "Singles", null, null, null,null, 0, 0, "In Progress");
        match8 = new Matches(0, eventID, "Singles", null, null, null,null, 0, 0, "In Progress");
        match9 = new Matches(0, eventID, "Singles", null, null, null,null, 0, 0, "In Progress");

        m1set1 = new  MatchSet(0,0, 0,0,"In Progress");
        m1set2 = new MatchSet(0,0,0,0,"In Progress");
        m1set3 = new MatchSet(0,0,0,0,"In Progress");

        m2set1 = new MatchSet(0,0,0,0,"In Progress");
        m2set2 = new MatchSet(0 ,0, 0,0,"In Progress");
        m2set3 = new MatchSet(0, 0,0,0,"In Progress");

        m3set1 = new MatchSet(0,0, 0,0,"In Progress");
        m3set2 = new MatchSet(0,0, 0,0,"In Progress");
        m3set3 = new MatchSet(0,0, 0,0,"In Progress");

        m4set1 = new MatchSet(0, 0, 0,0,"In Progress");
        m4set2 = new MatchSet(0,0, 0,0,"In Progress");
        m4set3 = new MatchSet(0,0, 0,0,"In Progress");

        m5set1 = new MatchSet(0,0, 0,0,"In Progress");
        m5set2 = new MatchSet(0,0, 0,0,"In Progress");
        m5set3 = new MatchSet(0,0, 0,0,"In Progress");

        m6set1 = new MatchSet(0,0, 0,0,"In Progress");
        m6set2 = new MatchSet(0,0, 0,0,"In Progress");
        m6set3 = new MatchSet(0,0, 0,0,"In Progress");

        m7set1 = new MatchSet(0,0, 0,0,"In Progress");
        m7set2 = new MatchSet(0,0, 0,0,"In Progress");
        m7set3 = new MatchSet(0,0, 0,0,"In Progress");

        m8set1 = new MatchSet(0,0, 0,0,"In Progress");
        m8set2 = new MatchSet(0,0, 0,0,"In Progress");
        m8set3 = new MatchSet(0,0, 0,0,"In Progress");

        m9set1 = new MatchSet(0,0, 0,0,"In Progress");
        m9set2 = new MatchSet(0,0, 0,0,"In Progress");
        m9set3 = new MatchSet(0,0, 0,0,"In Progress");

        startTimer();


    }


    private class getMatchesAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Display progress bar
            //progressDialog = new ProgressDialog(ViewSchedule.this);
            //progressDialog.setMessage("Please wait...");
            //progressDialog.setIndeterminate(false);
            //progressDialog.setCancelable(false);
            //  progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            getMatches();
            //Temporary solution due to the asynchronous nature of the Volley request
            while (status == false){}
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            status = false;
            //progressDialog.dismiss();
            runOnUiThread(new Runnable() {
                public void run() {
                    getSetsM1();
                    getSetsM2();
                    getSetsM3();
                    getSetsM4();
                    getSetsM5();
                    getSetsM6();
                    getSetsM7();
                    getSetsM8();
                    getSetsM9();

                    setHeaders();
                    setResults();



                }
            });
        }

    }

    @Override
    public void onBackPressed(){
        stopTimer();
        finish();
    }



    //To start timer
    private void startTimer(){
        timer = new Timer();
        timerTask = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run(){
                        new getMatchesAsyncTask().execute();
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, 10000);
    }

    //To stop timer
    private void stopTimer(){
        if(timer != null){
            timer.cancel();
            timer.purge();
        }
    }

    public void setResults(){

        ConstraintLayout.LayoutParams newLayoutParams;

        String result1 = getResults(match1);
        newLayoutParams = (ConstraintLayout.LayoutParams) m2Header.getLayoutParams();
        if(!result1.equals("In Progress")){
            m1resultHead.setVisibility(View.VISIBLE);m1result.setVisibility(View.VISIBLE);
            m1result.setText(result1);
            newLayoutParams.topMargin = 300;
            m2Header.setLayoutParams(newLayoutParams);
        }else{
            newLayoutParams.topMargin = 48;
            m2Header.setLayoutParams(newLayoutParams);
        }


        String result2 = getResults(match2);
        newLayoutParams = (ConstraintLayout.LayoutParams) m3Header.getLayoutParams();
        if(!result2.equals("In Progress")){
            m2resultHead.setVisibility(View.VISIBLE);m2result.setVisibility(View.VISIBLE);
            m2result.setText(result2);
            newLayoutParams.topMargin = 300;
            m3Header.setLayoutParams(newLayoutParams);
        }else{
            newLayoutParams.topMargin = 48;
            m3Header.setLayoutParams(newLayoutParams);
        }


        String result3 = getResults(match3);
        newLayoutParams = (ConstraintLayout.LayoutParams) singlesHeader.getLayoutParams();
        if(!result3.equals("In Progress")){
            m3resultHead.setVisibility(View.VISIBLE);m3result.setVisibility(View.VISIBLE);
            m3result.setText(result3);
            newLayoutParams.topMargin = 300;
            singlesHeader.setLayoutParams(newLayoutParams);
        }else{
            newLayoutParams.topMargin = 64;
            singlesHeader.setLayoutParams(newLayoutParams);
        }


        String result4 = getResults(match4);
        newLayoutParams = (ConstraintLayout.LayoutParams) m5Header.getLayoutParams();
        if(!result4.equals("In Progress")){
            m4resultHead.setVisibility(View.VISIBLE);m4result.setVisibility(View.VISIBLE);
            m4result.setText(result4);
            newLayoutParams.topMargin = 300;
            m5Header.setLayoutParams(newLayoutParams);
        }else{
            newLayoutParams.topMargin = 48;
            m5Header.setLayoutParams(newLayoutParams);
        }


        String result5 = getResults(match5);
        newLayoutParams = (ConstraintLayout.LayoutParams) m6Header.getLayoutParams();
        if(!result5.equals("In Progress")){
            m5resultHead.setVisibility(View.VISIBLE);m5result.setVisibility(View.VISIBLE);
            m5result.setText(result5);
            newLayoutParams.topMargin = 300;
            m6Header.setLayoutParams(newLayoutParams);
        }else{
            newLayoutParams.topMargin = 48;
            m6Header.setLayoutParams(newLayoutParams);
        }


        String result6 = getResults(match6);
        newLayoutParams = (ConstraintLayout.LayoutParams) m7Header.getLayoutParams();
        if(!result6.equals("In Progress")){
            m6resultHead.setVisibility(View.VISIBLE);m6result.setVisibility(View.VISIBLE);
            m6result.setText(result6);
            newLayoutParams.topMargin = 300;
            m7Header.setLayoutParams(newLayoutParams);
        }else{
            newLayoutParams.topMargin = 48;
            m7Header.setLayoutParams(newLayoutParams);
        }


        String result7 = getResults(match7);
        newLayoutParams = (ConstraintLayout.LayoutParams) m8Header.getLayoutParams();
        if(!result7.equals("In Progress")){
            m7resultHead.setVisibility(View.VISIBLE);m7result.setVisibility(View.VISIBLE);
            m7result.setText(result7);
            newLayoutParams.topMargin = 300;
            m8Header.setLayoutParams(newLayoutParams);
        }else{
            newLayoutParams.topMargin = 48;
            m8Header.setLayoutParams(newLayoutParams);
        }


        String result8 = getResults(match8);
        newLayoutParams = (ConstraintLayout.LayoutParams) m9Header.getLayoutParams();
        if(!result8.equals("In Progress")){
            m8resultHead.setVisibility(View.VISIBLE);m8result.setVisibility(View.VISIBLE);
            m8result.setText(result8);
            newLayoutParams.topMargin = 300;
            m9Header.setLayoutParams(newLayoutParams);
        }else{
            newLayoutParams.topMargin = 48;
            m9Header.setLayoutParams(newLayoutParams);
        }


        String result9 = getResults(match9);
        if(!result9.equals("In Progress")){
            m9resultHead.setVisibility(View.VISIBLE);m9result.setVisibility(View.VISIBLE);
            m9result.setText(result9);
        }

    }

    public String getResults(Matches match){
        String result = match.getResult();
        if(match.getResult().equals("Complete - Home")){
            if (match.getMatchType().equals("Doubles")){
                result = (match.getHomePlayer1() + " and " + match.getHomePlayer2());
            }else{
                result = match.getHomePlayer1();
            }
        }else if(match.getResult().equals("Complete - Away")){
            if (match.getMatchType().equals("Doubles")){
                result = (match.getAwayPlayer1() + " and " + match.getAwayPlayer2());
            }else{
                result = match.getAwayPlayer1();
            }

        }
        return result;
    }

    public void setHeaders(){
        if(match1.getResult().equals("In Progress")){
            m1Header.setBackgroundColor(Color.parseColor("#0fff00"));
            m1resultHead.setVisibility(View.INVISIBLE);m1result.setVisibility(View.INVISIBLE);
        }else{m1Header.setBackgroundColor(Color.parseColor("#ff0000"));
        }

        if(match2.getResult().equals("In Progress")){
            m2Header.setBackgroundColor(Color.parseColor("#0fff00"));
            m2resultHead.setVisibility(View.INVISIBLE);m2result.setVisibility(View.INVISIBLE);
        }else{m2Header.setBackgroundColor(Color.parseColor("#ff0000"));
        }

        if(match3.getResult().equals("In Progress")){
            m3Header.setBackgroundColor(Color.parseColor("#0fff00"));
            m3resultHead.setVisibility(View.INVISIBLE);m3result.setVisibility(View.INVISIBLE);
        }else{m3Header.setBackgroundColor(Color.parseColor("#ff0000"));
        }

        if(match4.getResult().equals("In Progress")){
            m4Header.setBackgroundColor(Color.parseColor("#0fff00"));
            m4resultHead.setVisibility(View.INVISIBLE);m4result.setVisibility(View.INVISIBLE);
        }else{m4Header.setBackgroundColor(Color.parseColor("#ff0000"));
        }

        if(match5.getResult().equals("In Progress")){
            m5Header.setBackgroundColor(Color.parseColor("#0fff00"));
            m5resultHead.setVisibility(View.INVISIBLE);m5result.setVisibility(View.INVISIBLE);
        }else{m5Header.setBackgroundColor(Color.parseColor("#ff0000"));
        }

        if(match6.getResult().equals("In Progress")){
            m6Header.setBackgroundColor(Color.parseColor("#0fff00"));
            m6resultHead.setVisibility(View.INVISIBLE); m6result.setVisibility(View.INVISIBLE);
        }else{m6Header.setBackgroundColor(Color.parseColor("#ff0000"));
        }

        if(match7.getResult().equals("In Progress")){
            m7Header.setBackgroundColor(Color.parseColor("#0fff00"));
            m7resultHead.setVisibility(View.INVISIBLE);m7result.setVisibility(View.INVISIBLE);
        }else{m7Header.setBackgroundColor(Color.parseColor("#ff0000"));
        }

        if(match8.getResult().equals("In Progress")){
            m8Header.setBackgroundColor(Color.parseColor("#0fff00"));
            m8resultHead.setVisibility(View.INVISIBLE);m8result.setVisibility(View.INVISIBLE);
        }else{m8Header.setBackgroundColor(Color.parseColor("#ff0000"));
        }

        if(match9.getResult().equals("In Progress")){
            m9Header.setBackgroundColor(Color.parseColor("#0fff00"));
            m9resultHead.setVisibility(View.INVISIBLE);m9result.setVisibility(View.INVISIBLE);
        }else{m9Header.setBackgroundColor(Color.parseColor("#ff0000"));
        }

    }

    public void  getMatches(){



        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GET_MATCHES_VIEW,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.i("tagconvertstr", "["+response+"]");
                            JSONArray array = new JSONArray(response);
                            ArrayList<Matches> matchList = new ArrayList<>();
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

                            match1 = matchList.get(0);
                            match2 = matchList.get(1);
                            match3 = matchList.get(2);
                            match4 = matchList.get(3);
                            match5 = matchList.get(4);
                            match6 = matchList.get(5);
                            match7 = matchList.get(6);
                            match8 = matchList.get(7);
                            match9 = matchList.get(8);

                            m1hp1.setText(match1.getHomePlayer1()); m1ap1.setText(match1.getAwayPlayer1());
                            m1hp2.setText(match1.getHomePlayer2()); m1ap2.setText(match1.getAwayPlayer2());

                            m2hp1.setText(match2.getHomePlayer1()); m2ap1.setText(match2.getAwayPlayer1());
                            m2hp2.setText(match2.getHomePlayer2()); m2ap2.setText(match2.getAwayPlayer2());

                            m3hp1.setText(match3.getHomePlayer1()); m3ap1.setText(match3.getAwayPlayer1());
                            m3hp2.setText(match3.getHomePlayer2()); m3ap2.setText(match3.getAwayPlayer2());

                            m4hp.setText(match4.getHomePlayer1()); m4ap.setText(match4.getAwayPlayer1());

                            m5hp.setText(match5.getHomePlayer1()); m5ap.setText(match5.getAwayPlayer1());

                            m6hp.setText(match6.getHomePlayer1()); m6ap.setText(match6.getAwayPlayer1());

                            m7hp.setText(match7.getHomePlayer1()); m7ap.setText(match7.getAwayPlayer1());

                            m8hp.setText(match8.getHomePlayer1()); m8ap.setText(match8.getAwayPlayer1());

                            m9hp.setText(match9.getHomePlayer1()); m9ap.setText(match9.getAwayPlayer1());

                            status = true;


                        } catch (JSONException e) {

                            status = true;
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("eventID", String.valueOf(eventID));
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
                        try {
                            Log.i("tagconvertstr", "["+response+"]");
                            JSONArray array = new JSONArray(response);
                            ArrayList<MatchSet> setList = new ArrayList<>();
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
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("matchID", String.valueOf(match1.getMatchID()));
                params.put("matchType", "Doubles");



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
                        try {
                            Log.i("tagconvertstr", "["+response+"]");
                            JSONArray array = new JSONArray(response);

                            ArrayList<MatchSet> setList = new ArrayList<>();
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
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("matchID", String.valueOf(match2.getMatchID()));
                params.put("matchType", "Doubles");



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
                        try {
                            Log.i("tagconvertstr", "["+response+"]");
                            JSONArray array = new JSONArray(response);
                            ArrayList<MatchSet> setList = new ArrayList<>();
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
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("matchID", String.valueOf(match3.getMatchID()));
                params.put("matchType", "Doubles");



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
                        try {
                            Log.i("tagconvertstr", "["+response+"]");
                            JSONArray array = new JSONArray(response);
                            ArrayList<MatchSet> setList = new ArrayList<>();
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
                        try {
                            Log.i("tagconvertstr", "["+response+"]");
                            JSONArray array = new JSONArray(response);
                            ArrayList<MatchSet> setList = new ArrayList<>();
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
                        try {
                            Log.i("tagconvertstr", "["+response+"]");
                            JSONArray array = new JSONArray(response);
                            ArrayList<MatchSet> setList = new ArrayList<>();
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

    public void getSetsM7(){
        //   progressDialog.show();



        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GET_SETS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.i("tagconvertstr", "["+response+"]");
                            JSONArray array = new JSONArray(response);
                            ArrayList<MatchSet> setList = new ArrayList<>();
                            for(int i = 0; i < array.length(); i++){
                                JSONObject obj = array.getJSONObject(i);
                                MatchSet temp = new MatchSet(obj.getInt("setID"),
                                        obj.getInt("matchID"),
                                        obj.getInt("homeScore"),
                                        obj.getInt("awayScore"),
                                        obj.getString("result"));
                                setList.add(temp);}

                            m7set1 = setList.get(0); m7set2 = setList.get(1); m7set3 = setList.get(2);

                            m7s1h.setText(String.valueOf(m7set1.getHomeScore())); m7s1a.setText(String.valueOf(m7set1.getAwayScore()));
                            m7s2h.setText(String.valueOf(m7set2.getHomeScore())); m7s2a.setText(String.valueOf(m7set2.getAwayScore()));
                            m7s3h.setText(String.valueOf(m7set3.getHomeScore())); m7s3a.setText(String.valueOf(m7set3.getAwayScore()));




                        } catch (JSONException e) {
                            //Toast.makeText(getApplicationContext(), "here",Toast.LENGTH_LONG).show();

                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("matchID", String.valueOf(match7.getMatchID()));
                params.put("matchType", "Singles");



                return params;
            }
        };



        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }

    public void getSetsM8(){
        //   progressDialog.show();



        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GET_SETS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.i("tagconvertstr", "["+response+"]");
                            JSONArray array = new JSONArray(response);
                            ArrayList<MatchSet> setList = new ArrayList<>();
                            for(int i = 0; i < array.length(); i++){
                                JSONObject obj = array.getJSONObject(i);
                                MatchSet temp = new MatchSet(obj.getInt("setID"),
                                        obj.getInt("matchID"),
                                        obj.getInt("homeScore"),
                                        obj.getInt("awayScore"),
                                        obj.getString("result"));
                                setList.add(temp);}

                            m8set1 = setList.get(0); m8set2 = setList.get(1); m8set3 = setList.get(2);

                            m8s1h.setText(String.valueOf(m8set1.getHomeScore())); m8s1a.setText(String.valueOf(m8set1.getAwayScore()));
                            m8s2h.setText(String.valueOf(m8set2.getHomeScore())); m8s2a.setText(String.valueOf(m8set2.getAwayScore()));
                            m8s3h.setText(String.valueOf(m8set3.getHomeScore())); m8s3a.setText(String.valueOf(m8set3.getAwayScore()));




                        } catch (JSONException e) {
                            //Toast.makeText(getApplicationContext(), "here",Toast.LENGTH_LONG).show();

                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("matchID", String.valueOf(match8.getMatchID()));
                params.put("matchType", "Singles");



                return params;
            }
        };



        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }

    public void getSetsM9(){
        //   progressDialog.show();



        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GET_SETS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.i("tagconvertstr", "["+response+"]");
                            JSONArray array = new JSONArray(response);
                            ArrayList<MatchSet> setList = new ArrayList<>();
                            for(int i = 0; i < array.length(); i++){
                                JSONObject obj = array.getJSONObject(i);
                                MatchSet temp = new MatchSet(obj.getInt("setID"),
                                        obj.getInt("matchID"),
                                        obj.getInt("homeScore"),
                                        obj.getInt("awayScore"),
                                        obj.getString("result"));
                                setList.add(temp);}

                            m9set1 = setList.get(0); m9set2 = setList.get(1); m9set3 = setList.get(2);

                            m9s1h.setText(String.valueOf(m9set1.getHomeScore())); m9s1a.setText(String.valueOf(m9set1.getAwayScore()));
                            m9s2h.setText(String.valueOf(m9set2.getHomeScore())); m9s2a.setText(String.valueOf(m9set2.getAwayScore()));
                            m9s3h.setText(String.valueOf(m9set3.getHomeScore())); m9s3a.setText(String.valueOf(m9set3.getAwayScore()));




                        } catch (JSONException e) {
                            //Toast.makeText(getApplicationContext(), "here",Toast.LENGTH_LONG).show();

                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("matchID", String.valueOf(match9.getMatchID()));
                params.put("matchType", "Singles");



                return params;
            }
        };



        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }

    public void toastTry(){
        Toast.makeText(getApplicationContext(), "getting new scores", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.home:
                startActivity(new Intent(this, ViewSchedule.class));
                finish();
                break;
            case R.id.scoring_singles:
                startActivity(new Intent(this, ScoreByGameSingles.class));
                finish();
                break;
            case R.id.scoring_doubles:
                startActivity(new Intent(this, ScoreByGameDoubles.class));
                finish();
                break;
            case R.id.scores_view:
                recreate();
                finish();
                break;
            case R.id.Roster:
                startActivity(new Intent(this, PlayerRoster.class));
                break;
            case R.id.menuLogout:
                SharedPrefManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
        return true;
    }

}
