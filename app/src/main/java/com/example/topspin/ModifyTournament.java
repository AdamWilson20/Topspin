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

public class ModifyTournament extends AppCompatActivity {

    int targetID;
    EditText date;
    EditText time;
    EditText location;
    EditText opposingTeam;
    EditText homeAway;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_tournament);

        Intent fromVM = getIntent();
        targetID =fromVM.getIntExtra("ID",targetID);

        date = findViewById(R.id.MtaddDate);
        time = findViewById(R.id.MtaddTime);
        location = findViewById(R.id.MtaddLocation);
        opposingTeam = findViewById(R.id.MtaddOppTeam);
        homeAway = findViewById(R.id.MtaddHomeAway);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        getTournament(targetID);

    }

    private void getTournament(Integer tournamentID){
        final String tourneyID = tournamentID.toString();

        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GET_EVENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if(!obj.getBoolean("error")){
                                int tID = obj.getInt("tID");
                                date.setText(obj.getString("date"));
                                time.setText(obj.getString("time"));
                                opposingTeam.setText(obj.getString("oppTeam"));
                                location.setText(obj.getString("location"));

                                Integer thomeaway = obj.getInt("isHome");
                                if (thomeaway == 0) {
                                    homeAway.setText("Away");
                                }else{homeAway.setText("Home");}
                            }else{
                                Toast.makeText(
                                        getApplicationContext(),
                                        obj.getString("message"),
                                        Toast.LENGTH_LONG
                                ).show();
                                Intent badID = new Intent(ModifyTournament.this, ViewSchedule.class);
                                startActivity(badID);
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(
                                getApplicationContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("eventID", tourneyID);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void modifyEvent(Integer tournamentID){
        final String tourneyID = tournamentID.toString();

        final String tdate = date.getText().toString().trim();
        final String ttime = time.getText().toString().trim();
        final String tlocation = location.getText().toString().trim();
        final String topposingteam = opposingTeam.getText().toString().trim();
        final String thomeaway = homeAway.getText().toString().trim();
        final String haConversion;

        if(thomeaway.equals("home")||thomeaway.equals("Home")){
            haConversion = "1";
        }else{
            haConversion = "0";
        }

        progressDialog.setMessage("Modifying event...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_UPDATE_EVENT,
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
                params.put("eventID", tourneyID);
                params.put("date", tdate);
                params.put("time", ttime);
                params.put("location", tlocation);
                params.put("opposingTeam", topposingteam);
                params.put("homeOrAway", haConversion);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void finalizeChanges(View view){
        modifyEvent(targetID);
        Intent saveReturn = new Intent(this, ViewScheduleEntry.class);
        saveReturn.putExtra("ID", targetID);
        startActivity(saveReturn);
        finish();
    }
}
