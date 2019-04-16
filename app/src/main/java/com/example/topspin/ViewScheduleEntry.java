package com.example.topspin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ViewScheduleEntry extends AppCompatActivity implements android.support.v7.widget.PopupMenu.OnMenuItemClickListener {

    ArrayList<Tournament> tournaments;

    int targetIndex;
    Integer targetID;
    TextView date;
    TextView time;
    TextView vsat;
    TextView oppTeam;
    TextView location;
    boolean tournFound = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule_entry);
        loadData();
        Intent pullId = getIntent();
        targetID = pullId.getIntExtra("ID", -1);

        date = findViewById(R.id.VETournamentDate);
        time = findViewById(R.id.VETournamentTime);
        vsat = findViewById(R.id.VEVSorAT);
        oppTeam = findViewById(R.id.VETournamentOppTeam);
        location = findViewById(R.id.VETournamentLocation);

        for(int i =0; i < tournaments.size(); i++){
            if(tournaments.get(i).gettID()== targetID) {
                targetIndex = i;
                tournFound = true;
                break;
            }
        }

        if((targetID == -1) || (!tournFound)){
            Intent badID = new Intent(this, ViewSchedule.class);
            Toast.makeText(this, "Error in locating selected Tournament, returning to schedule view.", Toast.LENGTH_SHORT).show();
            startActivity(badID);
            finish();
        }else {
            date.setText(tournaments.get(targetIndex).getDate());
            time.setText(tournaments.get(targetIndex).getTime());
            oppTeam.setText(tournaments.get(targetIndex).getOppTeam());
            location.setText(tournaments.get(targetIndex).getLocation());

            if(tournaments.get(targetIndex).getHome()) {
                vsat.setText("VS");
            }else{vsat.setText("AT");}
        }

    }




    public void showMenu(View view){

        PopupMenu tournMenu = new PopupMenu(this,view);
        tournMenu.setOnMenuItemClickListener(this);
        tournMenu.inflate(R.menu.view_schedule_entry_menu);
        tournMenu.show();

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
                tournaments.remove(targetIndex);
                saveData();
                Intent backToViewSchedule= new Intent(getApplicationContext(),ViewSchedule.class);
                startActivity(backToViewSchedule);
                finish();
                return true;}

            default:
                return false;

        }
    }
}

