package com.example.topspin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddPlayerEntry extends AppCompatActivity implements View.OnClickListener {

    private EditText editFirstName, editLastName, editYear, editHometown, editHeight, editWeight;
    private Button submitPlayer;
    private Button updatePlayer;
    ListView listView;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        submitPlayer = (Button) findViewById(R.id.button11);
        updatePlayer = (Button) findViewById(R.id.updatePlayer);
        listView = (ListView) findViewById(R.id.listPlayer);


        editFirstName = (EditText) findViewById(R.id.addPlayerName);
        editLastName = (EditText) findViewById(R.id.addLastName);
        editYear = (EditText) findViewById(R.id.addClassYear);
        editHometown = (EditText) findViewById(R.id.addHometown);
        editHeight = (EditText) findViewById(R.id.addHeight);
        editWeight = (EditText) findViewById(R.id.addWeight);

        progressDialog = new ProgressDialog(this);

        submitPlayer.setOnClickListener(this);



    }



    private void addPlayer(){
        final String firstName = editFirstName.getText().toString().trim();
        final String lastName = editLastName.getText().toString().trim();
        final String year = editYear.getText().toString().trim();
        final String hometown = editHometown.getText().toString().trim();
        final String height = editHeight.getText().toString().trim();
        final String weight = editWeight.getText().toString().trim();

        progressDialog.setMessage("Adding Player...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_SET_ROSTER,
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
                Map<String, String> parameters = new HashMap<>();
                parameters.put("firstName", firstName);
                parameters.put("lastName", lastName);
                parameters.put("year", year);
                parameters.put("hometown", hometown);
                parameters.put("height", height);
                parameters.put("weight", weight);
                return parameters;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View view) {
        if (view == submitPlayer)
            addPlayer();
            Intent intent = new Intent(this, PlayerRoster.class);
            startActivity(intent);


    }
}

