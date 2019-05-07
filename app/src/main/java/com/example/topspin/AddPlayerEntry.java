package com.example.topspin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddPlayerEntry extends AppCompatActivity {

    private ArrayList<Player> players = new ArrayList<Player>();
    ArrayAdapter<String> adapter;
    RequestQueue requestQueue;



    private EditText firstName, lastName, classYear, hometown, height, weight;
    //private String TempName, TempClassYear, TempMajor, TempHometown, TempHeight, TempWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        final Button submitPlayer = findViewById(R.id.button11);

                requestQueue = Volley.newRequestQueue(getApplicationContext());

                submitPlayer.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {

                        firstName = (EditText) findViewById(R.id.addPlayerName);
                        lastName = (EditText) findViewById(R.id.addLastName);
                        classYear = (EditText) findViewById(R.id.addClassYear);
                        hometown = (EditText) findViewById(R.id.addHometown);
                        height = (EditText) findViewById(R.id.addHeight);
                        weight = (EditText) findViewById(R.id.addWeight);
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_SET_ROSTER, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }){
                            @Override
                            protected Map<String,String> getParams() throws AuthFailureError {
                                Map<String,String> parameters = new HashMap<String, String>();
                                parameters.put("firstName", firstName.getText().toString());
                                parameters.put("lastName",lastName.getText().toString());
                                parameters.put("classYear", classYear.getText().toString());
                                parameters.put("hometown", hometown.getText().toString());
                                parameters.put("height", height.getText().toString());
                                parameters.put("weight", weight.getText().toString());
                                return parameters;
                            }
                        };
                        requestQueue.add(stringRequest);
                    }
                } );

            }
        }

