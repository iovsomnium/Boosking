package com.example.che_ti_bleEX;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChangeTimetableActivity extends AppCompatActivity {

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
    String s;


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
                String r = periodarray.get(i).toString();
                switch(r) {
                    case "1교시":
                        changetime = changedate;
                        changetime += "1";
                        Toast.makeText(getApplicationContext(),changetime+"",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case "2교시":
                        changetime =changedate;
                        changetime += "2";
                        Toast.makeText(getApplicationContext(),changetime+"",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case "3교시":
                        changetime =changedate;
                        changetime += "3";
                        Toast.makeText(getApplicationContext(),changetime+"",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case "4교시":
                        changetime =changedate;
                        changetime += "4";
                        Toast.makeText(getApplicationContext(),changetime+"",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case "5교시":
                        changetime =changedate;
                        changetime += "5";
                        Toast.makeText(getApplicationContext(),changetime+"",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case "6교시":
                        changetime =changedate;
                        changetime += "6";
                        Toast.makeText(getApplicationContext(),changetime+"",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case "7교시":
                        changetime =changedate;
                        changetime += "7";
                        Toast.makeText(getApplicationContext(),changetime+"",
                                Toast.LENGTH_SHORT).show();
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //db 연결
        CollectionReference timetable = db.collection("Teacher-sub");

        //db 값 넣기
        Map<String, Object> 문A = new HashMap<>();
        문A.put("name", "문A");
        문A.put("teacher1", "김우리");
        문A.put("teacher2", "");
        timetable.document("문A").set(문A);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query query = mPostReference.orderByChild("subject").equalTo(s);
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds: dataSnapshot.getChildren()){
                            name = ""+ ds.child("name").getValue();
                            Toast.makeText(getApplicationContext(),name+"",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


    }
}
