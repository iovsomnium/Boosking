package com.example.che_ti_bleEX;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button chat,watchTimeTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        chat = (Button)findViewById(R.id.changeTimeTable);
        watchTimeTable = (Button)findViewById(R.id.watchTimeTable);

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent list = new Intent(getApplicationContext(),ChatActivity.class);
                startActivity(list);
            }
        });

        watchTimeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent timetable = new Intent(getApplicationContext(),Showtimetable.class);
                startActivity(timetable);
            }
        });
    }
}
