package com.example.miguel.hexshuttle;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.Result;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;


public class BarcodeReaderActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView zXingScannerView;
    private static int camId = Camera.CameraInfo.CAMERA_FACING_BACK;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_reader);
        zXingScannerView = new ZXingScannerView(this);
        setContentView(zXingScannerView);
        int currentApiVersion = Build.VERSION.SDK_INT;

        if(currentApiVersion >=  Build.VERSION_CODES.M)
        {
            if(checkPermission())
            {
                Toast.makeText(getApplicationContext(), "Permission already granted!", Toast.LENGTH_LONG).show();
            }
            else
            {
                requestPermission();
            }
        }
    }

    public void scan(View view){
        zXingScannerView =new ZXingScannerView(getApplicationContext());
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();

    }

    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        //Toast.makeText(getApplicationContext(),result.getText(),Toast.LENGTH_SHORT).show();
        processBarcode(result.getText());


    }
    private boolean checkPermission()
    {
        return (ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission()
    {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, REQUEST_CAMERA);
    }

    @Override
    public void onResume() {
        super.onResume();

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
            if (checkPermission()) {
                if(zXingScannerView == null) {
                    zXingScannerView = new ZXingScannerView(this);
                    setContentView(zXingScannerView);
                }
                zXingScannerView.setResultHandler(this);
                zXingScannerView.startCamera();
            } else {
                requestPermission();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        zXingScannerView.stopCamera();
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA:
                if (grantResults.length > 0) {

                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted){
                        Toast.makeText(getApplicationContext(), "Permission Granted, Now you can access camera", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(), "Permission Denied, You cannot access and camera", Toast.LENGTH_LONG).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(CAMERA)) {
                                showMessageOKCancel("You need to allow access to both the permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{CAMERA},
                                                            REQUEST_CAMERA);
                                                }
                                            }
                                        });
                                return;
                            }
                        }
                    }
                }
                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new android.support.v7.app.AlertDialog.Builder(BarcodeReaderActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    // CHECKING FUNCTIONS

    private void processBarcode(String id) {

        List<String> namesList = new ArrayList<String>();
        List<String> sidList = new ArrayList<String>();
        String[] scannedStudentData = new String[2];
        boolean isStudent = false;
        int index = 0;
        InputStream is = getResources().openRawResource(R.raw.sample);
        CsvReaderActivity cra = new CsvReaderActivity();
        cra.readData(is);
        namesList = cra.getNamesList();
        sidList = cra.getSidList();

        for (String sid : sidList) {

            if (sid.equals(id)) {
                isStudent = true;
                break;
            }
            index++;
        }

        if (isStudent) {

            scannedStudentData[0] = namesList.get(index);
            scannedStudentData[1] = sidList.get(index);
            Toast.makeText(this, "Student ID recognized.", Toast.LENGTH_LONG).show();
            Bundle b = new Bundle();
            b.putString("studentName", scannedStudentData[0]);
            b.putString("studentSid", scannedStudentData[1]);
            Intent i = new Intent(this, BarcodeDetailDisplayActivity.class);
            i.putExtras(b);
            startActivity(i);

        } else {

            Toast.makeText(this, "Student ID does not exist on file.", Toast.LENGTH_LONG ).show();
            zXingScannerView.resumeCameraPreview(this);

        }

    }

}

