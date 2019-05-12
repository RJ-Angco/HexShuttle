package com.example.miguel.hexshuttle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Model.TripData;

public class TripDetailsActivity extends AppCompatActivity {

    TripData td;
    TextView locationTo;
    TextView locationFrom;
    TextView plateNum;
    TextView driver;
    List<String> namesList = new ArrayList<>();
    List<String> sidList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);

        locationFrom = findViewById(R.id.tvFrom);
        locationTo = findViewById(R.id.tvTo);
        plateNum = findViewById(R.id.tvPlateNum);
        driver = findViewById(R.id.tvDriver);
    }

    public void startScanPressed(View v) {

        CsvReaderActivity csvr = new CsvReaderActivity();
        td = new TripData(locationTo.getText().toString(), locationFrom.getText().toString(), plateNum.getText().toString(), driver.getText().toString(), csvr);



        Log.d("To ", td.getLocationTo());
        Log.d("From ", td.getLocationFrom());
        Log.d("Driver Name ", td.getDriverName());
        Log.d("Plate Number ", td.getPlateNum());


        startActivity(new Intent(this, BarcodeReaderActivity.class));

    }

}
