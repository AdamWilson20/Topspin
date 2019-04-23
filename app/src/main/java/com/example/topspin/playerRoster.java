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

    ListView listView;
    ArrayList<Player> players;
    Player target;
    boolean status = false;
    private ProgressDialog progressDialog;
    private PlayerListAdapater playerRoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_roster);
        listView = (ListView) findViewById(R.id.listPlayer);
        Button buttonAdd = findViewById(R.id.addPlayer);

    }

    private class FetchEventsAsyncTask extends AsyncTask<String, Void, String> {
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
        playerRoster = new PlayerListAdapater(players, this);
        listView.setAdapter(playerRoster);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int tempID = players.get(position).getPlayerID();
                Toast.makeText(getApplicationContext(), String.valueOf(tempID), Toast.LENGTH_SHORT).show();
                //Intent startViewModify;
                //startViewModify = new Intent(getApplicationContext(), ViewScheduleEntry.class);
                //startViewModify.putExtra("ID",tempID);
                //startActivity(startViewModify);

            }
        });
    }


    //Create Roster objects

        /*
        //Add the Player objects to an ArrayList
        ArrayList<Player> rosterList = new ArrayList<>();
        for(int i = 0 ;i < 20; i++){
            Player adam = new Player("Adam","Senior","Virginia","5'8","160");
            rosterList.add(adam);

        Player one = new Player("Jannik Giesse", "Sophomore", "Ketsch,Germany", "6'1", "160");
        Player two = new Player("Justin Herrera", "Junior", "Miami, Florida", "5'9", "160");
        Player three = new Player("Aziz Kijametovic", "Senior", "Zivinice, Bosinia", "6'0", "160");
        Player four = new Player("Luca Maldoner", "Freshman", "Innsbruck, Austria", "6'1", "160");
        Player five = new Player("Bradley Mittleman", "Freshman", "Leawood, Kansas", "6'0", "160");
        Player six = new Player("Francois Musitelli", "Junior", "Dijon, France", "6'0", "160");

        //Add the Player objects to an ArrayList
        //ArrayList<Player> rosterList = new ArrayList<>();
        rosterList.add(one);
        rosterList.add(two);
        rosterList.add(three);
        rosterList.add(four);
        rosterList.add(five);
        rosterList.add(six);


        }
        PlayerListAdapater adapter = new PlayerListAdapater(this, R.layout.adapter_player, rosterList);
        mListView.setAdapter(adapter);


    }


    private void openActivity() {
        Intent intent = new Intent(this, AddPlayerEntry.class);
        startActivity(intent);
    }
  */


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