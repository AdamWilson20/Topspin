package com.example.topspin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class ModifyPlayer extends AppCompatActivity {

    private EditText editFirstName, editLastName, editYear, editHometown, editHeight, editWeight;
    private Button updatePlayer;
    int playerID;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_player);

        Intent fromVM = getIntent();
        playerID =fromVM.getIntExtra("ID",0);

        //updatePlayer = (Button) findViewById(R.id.updatePlayer);

        editFirstName = (EditText) findViewById(R.id.addPlayerName);
        editLastName = (EditText) findViewById(R.id.addLastName);
        editYear = (EditText) findViewById(R.id.addClassYear);
        editHometown = (EditText) findViewById(R.id.addHometown);
        editHeight = (EditText) findViewById(R.id.addHeight);
        editWeight = (EditText) findViewById(R.id.addWeight);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        getRoster(playerID);

    }

    private void getRoster(Integer playerID){
        final String playersID = playerID.toString();

        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_GET_PLAYER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if(!obj.getBoolean("error")){
                               // int pID = obj.getInt("playerID");
                                editFirstName.setText(obj.getString("firstName"));
                                editLastName.setText(obj.getString("lastName"));
                                editYear.setText(obj.getString("Year"));
                                editHometown.setText(obj.getString("Hometown"));
                                editHeight.setText(obj.getString("Height"));
                                editWeight.setText(obj.getString("Weight"));

                            }else{
                                Toast.makeText(
                                        getApplicationContext(),
                                        obj.getString("message"),
                                        Toast.LENGTH_LONG
                                ).show();
                                //Intent badID = new Intent(ModifyPlayer.this, PlayerRoster.class);
                                //startActivity(badID);

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
                params.put("playerID", playersID);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void modifyPlayer(Integer playerID){

        final String playersID = playerID.toString();

        final String tfirstName = editFirstName.getText().toString().trim();
        final String tlastName = editLastName.getText().toString().trim();
        final String tYear = editYear.getText().toString().trim();
        final String tHometown = editHometown.getText().toString().trim();
        final String tHeight = editHeight.getText().toString().trim();
        final String tWeight = editWeight.getText().toString().trim();

        progressDialog.setMessage("Modifying player...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_UPDATE_ROSTER,
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
                params.put("player_ID", playersID);
                params.put("firstName", tfirstName);
                params.put("lastName", tlastName);
                params.put("year", tYear);
                params.put("hometown", tHometown);
                params.put("height", tHeight);
                params.put("weight", tWeight);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void deletePlayer(Integer playerID){
        final String playersID = playerID.toString();

        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_DELETE_PLAYER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
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
                params.put("player_ID", playersID);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void submitPlayer(View view) {
            modifyPlayer(playerID);
            Intent intent = new Intent(this, PlayerRoster.class);
            startActivity(intent);
            finish();

    }

    public void deletePlayer(View view) {
        deletePlayer(playerID);
        Intent intent = new Intent(this, PlayerRoster.class);
        startActivity(intent);
        
    }


}
