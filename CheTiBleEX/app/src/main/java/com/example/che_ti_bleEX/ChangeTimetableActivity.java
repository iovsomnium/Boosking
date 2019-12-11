package com.example.che_ti_bleEX;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChangeTimetableActivity extends AppCompatActivity {

    String TAG = getClass().getSimpleName();
    Spinner date,period,changesubject;
    String changedate;
    String changetime;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button change;

    private DatabaseReference mPostReference;
    private FirebaseAuth firebaseAuth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    String name;
    String tc1,tc2,classtime,s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_timetable);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        mPostReference = firebaseDatabase.getReference("Teacher");

        date = (Spinner)findViewById(R.id.date);
        period = (Spinner)findViewById(R.id.period);
        changesubject = (Spinner) findViewById(R.id.changesubject);
        change = (Button)findViewById(R.id.change);


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

        final ArrayList subdarray = new ArrayList<>();
        subdarray.add("C#");
        subdarray.add("DB");
        subdarray.add("문A");
        subdarray.add("국사");
        subdarray.add("문B");
        subdarray.add("직A");
        subdarray.add("직B");
        subdarray.add("미술");
        subdarray.add("앱프");
        subdarray.add("영어");
        subdarray.add("자습");
        subdarray.add("진로");
        subdarray.add("체육");
        subdarray.add("확통");


        ArrayAdapter dateAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,datearray);
        ArrayAdapter periodAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,periodarray);
        ArrayAdapter subAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,subdarray);

        date.setAdapter(dateAdapter);
        period.setAdapter(periodAdapter);
        changesubject.setAdapter(subAdapter);

        date.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String q = datearray.get(i).toString();
                switch(q) {
                    case "월요일":
                        changedate = "monday";
                        break;
                    case "화요일":
                        changedate = "tuesday";
                        break;
                    case "수요일":
                        changedate = "wendsday";
                        break;
                    case "목요일":
                        changedate = "thuresday";
                        break;
                    case "금요일":
                        changedate = "friday";
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
                String r = periodarray.get(i).toString();
                switch(r) {
                    case "1교시":
                        classtime = changedate;
                        changetime = "1";
                        classtime = changedate+changetime;
                        break;
                    case "2교시":
                        classtime =changedate;
                        changetime = "2";
                        break;
                    case "3교시":
                        classtime =changedate;
                        changetime = "3";
                        break;
                    case "4교시":
                        classtime =changedate;
                        changetime = "4";
                        break;
                    case "5교시":
                        classtime =changedate;
                        changetime = "5";
                        break;
                    case "6교시":
                        classtime =changedate;
                        changetime = "6";
                        break;
                    case "7교시":
                        classtime =changedate;
                        changetime = "7";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        changesubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                s = subdarray.get(i).toString();
                DocumentReference docRef2 = db.collection("Teacher-sub").document(s);
                docRef2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                tc1 = document.getData().get("teacher1").toString();
                                tc2 = document.getData().get("teacher2").toString();
                                Log.d(TAG, "DocumentSnapshot data: " + tc1+tc2);
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CollectionReference timetable = db.collection("ChangeTimetable");

                switch (changedate){
                    case "monday" :
                        Map<String, Object> data1 = new HashMap<>();
                        data1.put("date",classtime);
                        data1.put("name", s);
                        data1.put("teacher1", tc1);
                        data1.put("teacher2", tc2);
                        timetable.document("data"+changetime).set(data1);
                        break;
                    case "tuesday" :
                        int two = 7+Integer.parseInt(changetime);
                        Map<String, Object> data2 = new HashMap<>();
                        data2.put("date",classtime);
                        data2.put("name", s);
                        data2.put("teacher1", tc1);
                        data2.put("teacher2", tc2);
                        timetable.document("data"+two).set(data2);
                        break;
                    case "wendsday" :
                        int three = 14+Integer.parseInt(changetime);
                        Map<String, Object> data3 = new HashMap<>();
                        data3.put("date",classtime);
                        data3.put("name", s);
                        data3.put("teacher1", tc1);
                        data3.put("teacher2", tc2);
                        timetable.document("data"+three).set(data3);
                        break;
                    case "thuresday" :
                        int four = 21+Integer.parseInt(changetime);
                        Map<String, Object> data4 = new HashMap<>();
                        data4.put("date",classtime);
                        data4.put("name", s);
                        data4.put("teacher1", tc1);
                        data4.put("teacher2", tc2);
                        timetable.document("data"+four).set(data4);
                        break;
                    case "friday" :
                        int five = 28+Integer.parseInt(changetime);
                        Map<String, Object> data5 = new HashMap<>();
                        data5.put("date",classtime);
                        data5.put("name", s);
                        data5.put("teacher1", tc1);
                        data5.put("teacher2", tc2);
                        timetable.document("data"+five).set(data5);
                        break;
                }

                Toast.makeText(getApplicationContext(),"정상적으로 시간표가 변경되었습니다",
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
