package com.example.topspin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;



public class admin_main_page extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main_page);

        defineButtons();
    }
    public void defineButtons() {
        findViewById(R.id.viewEventButton).setOnClickListener(buttonClickListener);
        findViewById(R.id.button4).setOnClickListener(buttonClickListener);
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.viewEventButton:
                    startActivity(new Intent(admin_main_page.this, matchEvent.class));
                    break;
                case R.id.button4:
                    startActivity(new Intent(admin_main_page.this, scoring.class));
                    break;
            }
        }
    };
}
    /*
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main_page);

        //configureBackButton();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main_page);

        button = (Button) findViewById(R.id.viewEventButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMatchinfo();
            }
        });

    }

    public void openMatchinfo() {
        Intent intent = new Intent(this, matchEvent.class);
        startActivity(intent);
    }
}
*/
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                Intent intent1 = new Intent(this, admin_main_page.class);
                this.startActivity(intent1);
                return true;

        }
        return super.onOptionsItemSelected(item);

    }


    private void configureBackButton() {
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
*/