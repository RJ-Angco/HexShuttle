package com.example.miguel.hexshuttle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import Model.StudentData;

public class CsvReaderActivity extends AppCompatActivity {

    ListView lvStudentList;
    InputStream inputStr;
    private List<StudentData> data = new ArrayList<>();
    private ListView theList;
    private List<String> namesList = new ArrayList<String>();
    private List<String> sidList = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csv_reader);

        lvStudentList = (ListView)findViewById(R.id.lvStudentList);
        InputStream is = getResources().openRawResource(R.raw.sample);
        readData(is);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, namesList);
        lvStudentList.setAdapter(adapter);
        //lvStudentList.setOnItemClickListener(this);
    }

    public void readData(InputStream is) {

        // InputStream is = getResources().openRawResource(R.raw.sample);
        BufferedReader reader = new BufferedReader (new InputStreamReader(is, Charset.forName("UTF-8")));

        String line;
        try{

            while((line = reader.readLine()) != null) {

                String[] tokens = line.split(",");
                StudentData sData = new StudentData();
                sData.setName(tokens[0]);
                namesList.add(tokens[0]);
                sData.setSid(tokens[1]);
                sidList.add(tokens[1]);
                data.add(sData);

                Log.d("MyActivity", "Created: " + sData);

            }

        } catch (IOException ioe) {

            Log.wtf("Main Activity", "Error Reading File on Line ", ioe);
            ioe.printStackTrace();

        }

    }



    public List<String> getNamesList() {
        return namesList;
    }

    public void setNamesList(List<String> namesList) {
        this.namesList = namesList;
    }

    public List<String> getSidList() {
        return sidList;
    }

    public void setSidList(List<String> sidList) {
        this.sidList = sidList;
    }
}


