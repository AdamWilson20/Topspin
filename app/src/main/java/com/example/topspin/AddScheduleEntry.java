package com.example.topspin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.lang.Math.abs;

public class AddScheduleEntry extends AppCompatActivity {

    private ArrayList<Tournament> tournaments;
    private Tournament temp;
    private EditText date, time, location, opposingTeam, homeAway;
    private Boolean vSAT = false;
    private int tID;
    private boolean uniqueID = true;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule_entry);

        date = findViewById(R.id.taddDate);
        time = findViewById(R.id.taddTime);
        location = findViewById(R.id.taddLocation);
        opposingTeam = findViewById(R.id.taddOppTeam);
        homeAway = findViewById(R.id.taddHomeAway);

        progressDialog = new ProgressDialog(this);

        loadData();

    }

    private void createEvent(){

        final String tdate = date.getText().toString().trim();
        final String ttime = time.getText().toString().trim();
        final String tlocation = location.getText().toString().trim();
        final String topposingteam = opposingTeam.getText().toString().trim();
        final String thomeaway = homeAway.getText().toString().trim();

        progressDialog.setMessage("Creating event...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_CREATE_EVENT,
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
                params.put("date", tdate);
                params.put("time", ttime);
                params.put("location", tlocation);
                params.put("opposingTeam", topposingteam);
                params.put("homeOrAway", thomeaway);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void submitEntry(View view){
        createEvent();
        Intent backToSchedule= new Intent(getApplicationContext(),ViewSchedule.class);

        final String tdate = date.getText().toString().trim();
        final String ttime = time.getText().toString().trim();
        final String tlocation = location.getText().toString().trim();
        final String topposingteam = opposingTeam.getText().toString().trim();
        final String thomeaway = homeAway.getText().toString().trim();

        //Remove once database is integrated into view schedule
        Random rand = new Random();
        tID=rand.nextInt();
        while(!uniqueID){
            tID=abs(rand.nextInt());
            for(int i = 0; i < tournaments.size(); i++){
                if (tournaments.get(i).gettID() == tID){
                    uniqueID = false;
                    break;
                }
            }
            //Toast.makeText(this,String.valueOf(tID),Toast.LENGTH_SHORT).show();
        }
        if((thomeaway.equals("home")) || (thomeaway.equals("Home"))){
            vSAT = true;
        }

        temp = new Tournament(tID, topposingteam, tlocation, tdate, ttime, vSAT);
        tournaments.add(temp);
        saveData();

        startActivity(backToSchedule);
        finish();

    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(tournaments);
        editor.putString("tournament schedule", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("tournament schedule", null);
        Type type = new TypeToken<ArrayList<Tournament>>() {
        }.getType();
        tournaments = gson.fromJson(json, type);

        if (tournaments == null) {
            tournaments = new ArrayList<>();
        }
    }
    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, ViewSchedule.class));
        finish();
    }
}

