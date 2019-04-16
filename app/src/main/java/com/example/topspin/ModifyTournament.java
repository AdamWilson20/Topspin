package com.example.topspin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ModifyTournament extends AppCompatActivity {

    int targetID;
    int targetIndex;
    ArrayList<Tournament> tournaments;
    Tournament temp;
    EditText date;
    EditText time;
    EditText location;
    EditText opposingTeam;
    EditText homeAway;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_tournament);
        Intent fromVM = getIntent();
        targetID =fromVM.getIntExtra("ID",targetID);
        loadData();

        for(int i = 0; i < tournaments.size(); i++){
            if(tournaments.get(i).gettID() == targetID){
                targetIndex = i;
                break;
            }
        }

        date = findViewById(R.id.MtaddDate);
        time = findViewById(R.id.MtaddTime);
        location = findViewById(R.id.MtaddLocation);
        opposingTeam = findViewById(R.id.MtaddOppTeam);
        homeAway = findViewById(R.id.MtaddHomeAway);

        date.setText(tournaments.get(targetIndex).getDate());
        time.setText(tournaments.get(targetIndex).getTime());
        location.setText(tournaments.get(targetIndex).getLocation());
        opposingTeam.setText(tournaments.get(targetIndex).getOppTeam());
        homeAway.setText(tournaments.get(targetIndex).getHome().toString());

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
    public void finalizeChanges(View view){

        tournaments.get(targetIndex).setDate(date.getText().toString());
        tournaments.get(targetIndex).setTime(time.getText().toString());
        tournaments.get(targetIndex).setLocation(location.getText().toString());
        tournaments.get(targetIndex).setOppTeam(opposingTeam.getText().toString());
        if((homeAway.getText().toString().equals("home"))||(homeAway.getText().toString().equals("Home"))){
            tournaments.get(targetIndex).setHome(true);
        }else{
            tournaments.get(targetIndex).setHome(false);
        }
        saveData();
        Intent saveReturn = new Intent(this, ViewScheduleEntry.class);
        saveReturn.putExtra("ID", targetID);
        startActivity(saveReturn);
        finish();
    }
}
