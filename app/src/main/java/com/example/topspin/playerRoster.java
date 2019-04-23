package com.example.topspin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class playerRoster extends AppCompatActivity {

    private ArrayList<Player> players;
    private Player target;
    private ListView listView;
    private RosterAdapter roster;
    boolean status = false;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_roster);

        listView = (ListView) findViewById(R.id.listPlayer);
        new playerRoster.FetchRosterAsyncTask().execute();
        Button buttonAdd = findViewById(R.id.addPlayer);
    }

    private class FetchRosterAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Display progress bar
            progressDialog = new ProgressDialog(playerRoster.this);
            progressDialog.setMessage("Please wait...");
            //progressDialog.setIndeterminate(false);
            //progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            getRoster();
            while (status == false) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            status = false;
            progressDialog.dismiss();
            runOnUiThread(new Runnable() {
                public void run() {
                    populateRoster();
                }
            });
        }

    }

    private void populateRoster() {
        roster = new RosterAdapter(this, players);
        listView.setAdapter(roster);
    }

    public void getRoster() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_GET_ROSTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                players = new ArrayList<>();
                                final int num_rows = obj.getInt("num_rows");
                                for (int i = 0; i < num_rows; i++) {
                                    int playerID = obj.getInt("playerID" + i);
                                    String firstName = obj.getString("firstName" + i);
                                    String lastName = obj.getString("lastName" + i);
                                    String height = obj.getString("height" + i);
                                    String weight = obj.getString("weight" + i);
                                    String year = obj.getString("year" + i);
                                    String hometown = obj.getString("hometown" + i);

                                    target = new Player(playerID, firstName, lastName, height, weight, year, hometown);
                                    players.add(target);
                                }
                            }
                            //Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                            status = true;
                        } catch (JSONException e) {
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
}