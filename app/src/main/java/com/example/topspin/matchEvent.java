package com.example.topspin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class matchEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matchinfo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                Intent intent1 = new Intent(this, LoginActivity.class);
                this.startActivity(intent1);
                return true;
            case R.id.myAccount:
                Intent intent2 = new Intent(this, admin_main_page.class);
                this.startActivity(intent2);
                return true;
            case R.id.VolunteerPage:
                Intent intent3 = new Intent(this, volunteer.class);
                this.startActivity(intent3);
                return true;


        }
        return super.onOptionsItemSelected(item);

    }
}


