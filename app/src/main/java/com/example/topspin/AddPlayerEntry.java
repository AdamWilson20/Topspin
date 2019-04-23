package com.example.topspin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AddPlayerEntry extends AppCompatActivity {

    private ArrayList<Player> players = new ArrayList<Player>();
    ArrayAdapter<String> adapter;



    private EditText firstName, lastName, classYear, major, hometown, height, weight;
    private String TempName, TempClassYear, TempMajor, TempHometown, TempHeight, TempWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        Button submitPlayer = findViewById(R.id.button11);
        submitPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //adapter = new ArrayAdapter<Player>(this,android.R.layout.simple_1ist_item_1, players);
                openActivity2();
            }
        });

        firstName = findViewById(R.id.addPlayerName);
        classYear = findViewById(R.id.addClassYear);
        major = findViewById(R.id.addMajor);
        hometown = findViewById(R.id.addHometown);
        height = findViewById(R.id.addHeight);
        weight = findViewById(R.id.addWeight);

    }



    /*
    public void getData() {

        TempName = firstName.getText().toString();
        TempClassYear = classYear.getText().toString();
        TempMajor = major.getText().toString();
        TempHometown = hometown.getText().toString();
        TempHeight = height.getText().toString();
        TempWeight = weight.getText().toString();

    }
*/
    private void openActivity2() {
        Intent intent2 = new Intent(this, AddPlayerEntry.class);
        startActivity(intent2);
    }

}

