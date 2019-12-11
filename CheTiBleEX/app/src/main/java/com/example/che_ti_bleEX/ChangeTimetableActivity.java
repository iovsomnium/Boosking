package com.example.che_ti_bleEX;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ChangeTimetableActivity extends AppCompatActivity {

    InputMethodManager imm;
    Spinner date,period;
    String changedate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_timetable);

        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        date = (Spinner)findViewById(R.id.date);
        period = (Spinner)findViewById(R.id.period);

        final ArrayList datearray = new ArrayList<>();
        datearray.add("월요일");
        datearray.add("화요일");
        datearray.add("수요일");
        datearray.add("목요일");
        datearray.add("금요일");

        final ArrayList periodarray = new ArrayList<>();
        periodarray.add("1교시");
        periodarray.add("2교시");
        periodarray.add("3교시");
        periodarray.add("4교시");
        periodarray.add("5교시");
        periodarray.add("6교시");
        periodarray.add("7교시");

        ArrayAdapter dateAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,datearray);
        ArrayAdapter periodAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,periodarray);

        date.setAdapter(dateAdapter);
        period.setAdapter(periodAdapter);

        date.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                imm.hideSoftInputFromWindow(date.getWindowToken(), 0);
                String q = datearray.get(i).toString();
                switch(q) {
                    case "월요일":
                        changedate = "monday";
                        Toast.makeText(getApplicationContext(),changedate+"",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case "화요일":
                        changedate = "tuesday";
                        Toast.makeText(getApplicationContext(),changedate+"",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case "수요일":
                        changedate = "wendsday";
                        Toast.makeText(getApplicationContext(),changedate+"",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case "목요일":
                        changedate = "thuresday";
                        Toast.makeText(getApplicationContext(),changedate+"",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case "금요일":
                        changedate += "friday";
                        Toast.makeText(getApplicationContext(),changedate+"",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        period.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                imm.hideSoftInputFromWindow(period.getWindowToken(), 0);
                String r = periodarray.get(i).toString();
                switch(r) {
                    case "1교시":
                        changedate += "1";
                        Toast.makeText(getApplicationContext(),changedate+"",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case "2교시":
                        changedate += "2";
                        Toast.makeText(getApplicationContext(),changedate+"",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case "3교시":
                        changedate += "3";
                        Toast.makeText(getApplicationContext(),changedate+"",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case "4교시":
                        changedate += "4";
                        Toast.makeText(getApplicationContext(),changedate+"",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case "5교시":
                        changedate += "5";
                        Toast.makeText(getApplicationContext(),changedate+"",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case "6교시":
                        changedate += "6";
                        Toast.makeText(getApplicationContext(),changedate+"",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case "7교시":
                        changedate += "7";
                        Toast.makeText(getApplicationContext(),changedate+"",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
}
