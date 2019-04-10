package com.example.topspin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class volunteer extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);

        defineButtons();

    }
    public void defineButtons(){
        findViewById(R.id.button10).setOnClickListener(buttonClickListener);
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view){
            switch(view.getId()) {
                case R.id.button10:
                    startActivity(new Intent(volunteer.this, scoring.class));
                    break;
            }
        }
    };
}
