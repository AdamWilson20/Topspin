package com.example.topspin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ViewSchedule extends AppCompatActivity {

    ArrayList<Tournament> tournaments;
    ScheduleAdapter schedule;
    ListView scheduleList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule);
        loadData();
        schedule = new ScheduleAdapter(this, tournaments);
        scheduleList = findViewById(R.id.scheduleList);
        scheduleList.setAdapter(schedule);



        if(tournaments.size() < 1) {
            for (int i = 0; i < 6; i++) {
                Tournament temp = new Tournament("test", "test", "test", "test", true);
                tournaments.add(temp);
                schedule.notifyDataSetChanged();
            }//*/

        }
        scheduleList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                tournaments.remove(position);
                schedule.notifyDataSetChanged();
                saveData();
                return false;
            }
        });

        saveData();
    }

    public void addEntry(View view){
        Intent addEntry = new Intent(this, AddScheduleEntry.class);
        saveData();
        startActivity(addEntry);
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(tournaments);
        editor.putString("tournament schedule list", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("tournament schedule list", null);
        Type type = new TypeToken<ArrayList<Tournament>>() {
        }.getType();
        tournaments = gson.fromJson(json, type);

        if (tournaments == null) {
            tournaments = new ArrayList<>();
        }
    }
}
