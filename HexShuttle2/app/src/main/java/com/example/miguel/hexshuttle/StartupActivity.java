package com.example.miguel.hexshuttle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
    }
    public void btnClicked(View v) {

        switch(v.getId()) {
            case R.id.btnStart:
                startActivity(new Intent(this, TripDetailsActivity.class));
                break;

            case R.id.btnStats:
                startActivity(new Intent(this, CsvReaderActivity.class));
                break;
        }

    }
}
