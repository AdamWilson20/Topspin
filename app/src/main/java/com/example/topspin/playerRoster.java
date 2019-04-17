package com.example.topspin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class playerRoster extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_roster);
        ListView mListView = (ListView) findViewById(R.id.listPlayer);

        //Create Roster objects
        Player adam = new Player("Adam","Senior","Virginia","5'8","160");

        //Add the Player objects to an ArrayList
        ArrayList<Player> rosterList = new ArrayList<>();
        rosterList.add(adam);

        PlayerListAdapater adapter = new PlayerListAdapater(this, R.layout.adapter_player, rosterList);
        mListView.setAdapter(adapter);



    }
}
