package com.example.che_ti_bleEX;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    String TAG = getClass().getSimpleName();
    Button chat,watchTimeTable,logout,setting;

    private DatabaseReference mPostReference;
    private FirebaseAuth.AuthStateListener mAuthListener;


    private FirebaseAuth firebaseAuth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;

    TextView teachername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        mPostReference = firebaseDatabase.getReference("Teacher");

        teachername = (TextView)findViewById(R.id.username);

        Query query = mPostReference.orderByChild("email").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    String name = ""+ ds.child("name").getValue();

                    teachername.setText(name);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        chat = (Button)findViewById(R.id.changeTimeTable);
        watchTimeTable = (Button)findViewById(R.id.watchTimeTable);
        setting = (Button)findViewById(R.id.setting);

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent set = new Intent(getApplicationContext(),SettingActivity.class);
                startActivity(set);
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent list = new Intent(getApplicationContext(),UserActivity.class);
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
    private boolean backKeyPressedTwice = false;

    @Override
    public void onBackPressed(){
        if(backKeyPressedTwice) {
            super.onBackPressed();
            return;
        }

        backKeyPressedTwice = true;
        Toast.makeText(this, "한번 더 누를시 종료됩니다.", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                backKeyPressedTwice=false;
            }
        }, 2000);
    }
}
