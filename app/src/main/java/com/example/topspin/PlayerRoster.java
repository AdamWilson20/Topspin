package com.example.topspin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PlayerRoster extends AppCompatActivity {

    private ArrayList<Player> players;
    private Player target;
    private ListView listView;
    private RosterAdapter roster;
    boolean status = false;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_roster);

        listView = findViewById(R.id.listPlayer);
        /*
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisk(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);
        */
        new FetchRosterAsyncTask().execute();
    }

    private class FetchRosterAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Display progress bar
            progressDialog = new ProgressDialog(PlayerRoster.this);
            progressDialog.setMessage("Please wait...");
            //progressDialog.setIndeterminate(false);
            //progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            //????Debugging mode and breakpoint needed here to reload roster properly????
            getRoster();
            //Temporary solution due to the asynchronous nature of the Volley request
            while (status == false) {}
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            //Toast.makeText(getApplicationContext(),"you got here",Toast.LENGTH_LONG).show();
            status = false;
            progressDialog.dismiss();
            runOnUiThread(new Runnable() {
                public void run() {
                    populateRoster();

                }
            });
        }

    }

    private void populateRoster() {
        roster = new RosterAdapter(this, players);
        listView.setAdapter(roster);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int playerID = players.get(position).getPlayerID();
                Intent intent = new Intent(getApplicationContext(),ModifyPlayer.class);
                intent.putExtra("ID",playerID);
                //Toast.makeText(getApplicationContext(),String.valueOf(playerID),Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
        /*
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisk(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);
        */
    }

    //Pull roster from database
    public void getRoster() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_GET_ROSTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject obj = new JSONObject(response);
                            if(!obj.getBoolean("error")) {
                                players = new ArrayList<>();
                                final int num_rows = obj.getInt("num_rows");
                                for (int i = 0; i < num_rows; i++) {
                                    int playerID = obj.getInt("playerID" + i);
                                    String playerImage = obj.getString("playerImage" + i);
                                    String firstName = obj.getString("firstName" + i);
                                    String lastName = obj.getString("lastName" + i);
                                    String height = obj.getString("height" + i);
                                    String weight = obj.getString("weight" + i);
                                    String year = obj.getString("year" + i);
                                    String hometown = obj.getString("hometown" + i);

                                    target = new Player(playerID, playerImage, firstName, lastName, height, weight, year, hometown);
                                    players.add(target);
                                }
                            }
                            //Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                            status = true;
                        } catch (JSONException e) {
                            e.printStackTrace();
                            status = true;
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        status = true;
                    }
                });
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void addPlayer(View view) {
        Intent addEntry = new Intent(this, AddPlayerEntry.class);
        startActivity(addEntry);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.home:
                startActivity(new Intent(this, ViewSchedule.class));
                finish();
                break;
            case R.id.scoring_singles:
                startActivity(new Intent(this, ScoreByGameSingles.class));
                finish();
                break;
            case R.id.scoring_doubles:
                startActivity(new Intent(this, ScoreByGameDoubles.class));
                finish();
                break;
            case R.id.scores_view:
                startActivity(new Intent(this, ViewScores.class));
                finish();
                break;
            case R.id.Roster:
                recreate();
                break;
            case R.id.menuLogout:
                SharedPrefManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
        return true;
    }
}