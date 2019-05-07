package com.example.topspin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
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

public class ViewScheduleEntry extends AppCompatActivity implements android.support.v7.widget.PopupMenu.OnMenuItemClickListener {

    Integer targetID;
    TextView date;
    TextView time;
    TextView vsat;
    TextView oppTeam;
    TextView location;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule_entry);
        Intent pullId = getIntent();
        targetID = pullId.getIntExtra("ID", -1);

        date = findViewById(R.id.VETournamentDate);
        time = findViewById(R.id.VETournamentTime);
        vsat = findViewById(R.id.VEVSorAT);
        oppTeam = findViewById(R.id.VETournamentOppTeam);
        location = findViewById(R.id.VETournamentLocation);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        if(targetID == -1){
            Intent badID = new Intent(this, ViewSchedule.class);
            startActivity(badID);
            finish();
        }else {
            getTournament(targetID);
        }

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
                                oppTeam.setText(obj.getString("oppTeam"));
                                location.setText(obj.getString("location"));

                                Integer thomeaway = obj.getInt("isHome");
                                Boolean boolHomeAway = true;
                                if (thomeaway == 0) {
                                    boolHomeAway = false;
                                }
                                if(boolHomeAway) {
                                    vsat.setText("VS");
                                }else{vsat.setText("AT");}
                            }else{
                                Toast.makeText(
                                        getApplicationContext(),
                                        obj.getString("message"),
                                        Toast.LENGTH_LONG
                                ).show();
                                Intent badID = new Intent(ViewScheduleEntry.this, ViewSchedule.class);
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

    public void showMenu(View view){

        PopupMenu tournMenu = new PopupMenu(this,view);
        tournMenu.setOnMenuItemClickListener(this);
        tournMenu.inflate(R.menu.view_schedule_entry_menu);
        tournMenu.show();

    }
    /*
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(tournaments);
        editor.putString("tournament schedule", json);
        editor.apply();
    }
    */
    /*
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
    */
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()){
            case R.id.VEmodify:{
                Toast.makeText(this,"Modify Selected", Toast.LENGTH_SHORT).show();
                Intent modPage = new Intent(getApplicationContext(), ModifyTournament.class);
                modPage.putExtra("ID",targetID);
                startActivity(modPage);
                finish();

                return true;}

            case R.id.VEdelete: {
                Toast.makeText(this,"Delete Selected", Toast.LENGTH_SHORT).show();
                //tournaments.remove(targetIndex);
                //saveData();
                Intent backToViewSchedule= new Intent(getApplicationContext(),ViewSchedule.class);
                startActivity(backToViewSchedule);
                finish();
                return true;}

            default:
                return false;

        }
    }
}

