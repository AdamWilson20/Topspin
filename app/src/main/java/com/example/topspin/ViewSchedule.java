package com.example.topspin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Target;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ViewSchedule extends AppCompatActivity {

    ArrayList<Tournament> tournaments;
    ScheduleAdapter schedule;
    ListView scheduleList;
    Tournament target;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule);

        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        loadData();
        //sortTourns(tournaments);
        saveData();
        schedule = new ScheduleAdapter(this, tournaments);
        scheduleList = findViewById(R.id.scheduleList);
        scheduleList.setAdapter(schedule);



        /*if(tournaments.size() < 1) {
            for (int i = 0; i < 6; i++) {
                Tournament temp = new Tournament("test", "test", "test", "test", true);
                tournaments.add(temp);
                schedule.notifyDataSetChanged();
            }//*/

        //    }

        scheduleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int tempID = tournaments.get(position).gettID();
                Toast.makeText(getApplicationContext(), String.valueOf(tempID), Toast.LENGTH_SHORT).show();
                Intent startViewModify;
                startViewModify = new Intent(getApplicationContext(), ViewScheduleEntry.class);
                startViewModify.putExtra("ID",tempID);
                startActivity(startViewModify);

            }
        });


    }

    public void addEntry(View view){
        Intent addEntry = new Intent(this, AddScheduleEntry.class);
        saveData();
        startActivity(addEntry);
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

    public void sortTourns(ArrayList<Tournament> tournaments){
        int targetIndex;
        for(int i = 1; i < tournaments.size(); i++){
            targetIndex = i;
            for(int j = i -1; i>=0; j--){

                //  if(tournaments.getPosition(i).getDate.isAfter(tournaments.getPosition(j).getDate){
                if((tournaments.get(i).getDate().compareTo(tournaments.get(j).getDate())) > 0){
                    break;
                }else{
                    targetIndex--;
                }

                if(targetIndex!=i){
                    target = tournaments.get(i);
                    tournaments.set(i,tournaments.get(targetIndex));
                    tournaments.set(targetIndex,target);
                }
            }

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.scoring:
                startActivity(new Intent(this, ScoreByGame.class));
                break;
            case R.id.menuLogout:
                SharedPrefManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;
                //This settings menu is just a placeholder for Player Roster.
            case R.id.Settings:
                startActivity(new Intent(this, playerRoster.class));
                break;

        }
        return true;
    }
}

