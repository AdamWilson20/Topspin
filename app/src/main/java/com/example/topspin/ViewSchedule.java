package com.example.topspin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewSchedule extends AppCompatActivity {

    private ArrayList<Tournament> tournaments;
    private Tournament target;
    private ListView scheduleList;
    private ScheduleAdapter schedule;
    private Boolean status = false;

    private ProgressDialog progressDialog;

    private String eventID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule);

        scheduleList = findViewById(R.id.scheduleList);
        new FetchEventsAsyncTask().execute();
    }

    /**
     *
     */
    private class FetchEventsAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Display progress bar
            progressDialog = new ProgressDialog(ViewSchedule.this);
            progressDialog.setMessage("Please wait...");
            //progressDialog.setIndeterminate(false);
            //progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            getSchedule();
            //Temporary solution due to the asynchronous nature of the Volley request
            while (status == false){}
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            status = false;
            progressDialog.dismiss();
            runOnUiThread(new Runnable() {
                public void run() {
                    populateSchedule();
                }
            });
        }

    }

    /**
     * Updating parsed JSON data into ListView
     * */
    private void populateSchedule() {
        schedule = new ScheduleAdapter(this, tournaments);
        scheduleList.setAdapter(schedule);

        scheduleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int tempID = tournaments.get(position).gettID();
                //Toast.makeText(getApplicationContext(), String.valueOf(tempID), Toast.LENGTH_SHORT).show();
                Intent startViewModify;
                startViewModify = new Intent(getApplicationContext(), ViewScheduleEntry.class);
                startViewModify.putExtra("ID",tempID);
                startActivity(startViewModify);

            }
        });
    }

    // Query the data base and populate the results in ArrayList<Tournament> tournaments
    public void getSchedule(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_GET_EVENTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject obj = new JSONObject(response);
                            if(!obj.getBoolean("error")) {
                                tournaments = new ArrayList<>();
                                final int num_rows = obj.getInt("num_rows");
                                for (int i = 0; i < num_rows; i++) {
                                    int tID = obj.getInt("tID" + i);
                                    String tdate = obj.getString("date" + i);
                                    String ttime = obj.getString("time" + i);
                                    String tlocation = obj.getString("location" + i);
                                    String topposingteam = obj.getString("oppTeam" + i);
                                    Integer thomeaway = obj.getInt("isHome" + i);
                                    Boolean boolHomeAway = true;
                                    if (thomeaway == 0) {
                                        boolHomeAway = false;
                                    }
                                    target = new Tournament(tID, topposingteam, tlocation, tdate, ttime, boolHomeAway);
                                    tournaments.add(target);
                                }
                            }
                            //Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                            status = true;
                        } catch (JSONException e){
                            e.printStackTrace();
                            status = true;
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        status = true;
                    }
                });
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void addEntry(View view) {
        Intent addEntry = new Intent(this, AddScheduleEntry.class);
        startActivity(addEntry);
        finish();
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
                recreate();
                break;
            case R.id.scoring_singles:
                startActivity(new Intent(this, ScoreByGameSingles.class));
                break;
            case R.id.scoring_doubles:
                startActivity(new Intent(this, ScoreByGameDoubles.class));
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


    /*public void launchMatch(View view){
        Intent goNext = new Intent(getApplicationContext(), ScoreByGame.class);
        goNext.putExtra("eventID", eventID);

        startActivity(goNext);
    }*/
}

