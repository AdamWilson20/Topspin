package com.example.topspin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.abs;

public class AddScheduleEntry extends AppCompatActivity {

    ArrayList<Tournament> tournaments;
    Tournament temp;
    EditText date;
    EditText time;
    EditText location;
    EditText opposingTeam;
    EditText homeAway;
    Boolean vSAT = false;
    int tID;
    boolean uniqueID = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule_entry);

        loadData();

    }
    public void submitEntry(View view){
        Intent backToSchedule= new Intent(getApplicationContext(),ViewSchedule.class);
        date = findViewById(R.id.taddDate);
        time = findViewById(R.id.taddTime);
        location = findViewById(R.id.taddLocation);
        opposingTeam = findViewById(R.id.taddOppTeam);
        homeAway = findViewById(R.id.taddHomeAway);

        String tdate = date.getText().toString();
        String ttime = time.getText().toString();
        String tlocation = location.getText().toString();
        String topposingteam = opposingTeam.getText().toString();
        String thomeaway = homeAway.getText().toString();

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
            Toast.makeText(this,String.valueOf(tID),Toast.LENGTH_SHORT).show();
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
}

