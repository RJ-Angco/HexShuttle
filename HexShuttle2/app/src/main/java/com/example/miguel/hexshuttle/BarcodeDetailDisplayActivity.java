package com.example.miguel.hexshuttle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import Model.TripData;

public class BarcodeDetailDisplayActivity extends AppCompatActivity {

    TripData td = new TripData();
    public String name;
    public String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_detail_display);

        TextView tvName = (TextView) findViewById(R.id.tvStudName);
        TextView tvSid = (TextView) findViewById(R.id.tvStudID);
        Bundle b = this.getIntent().getExtras();
        String sName = b.getString("studentName");
        String sId = b.getString("studentSid");
        this.name = sName;
        this.id = sId;
        tvName.setText(sName);
        tvSid.setText(sId);

    }

    public void btnClicked(View v) {

        switch (v.getId()) {

            case R.id.btnAdd:
                td.addStudent(id);
                Toast.makeText(this, "Student Added to List", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnScan:
                startActivity(new Intent(this, BarcodeReaderActivity.class));
                break;

            case R.id.btnFinish:
                startActivity(new Intent(this, StartTripActivity.class));
                break;


        }

    }
}
