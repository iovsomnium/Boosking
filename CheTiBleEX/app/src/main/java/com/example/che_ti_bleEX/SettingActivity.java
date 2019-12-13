package com.example.che_ti_bleEX;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {

    LinearLayout settimetable,setchat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        settimetable = (LinearLayout)findViewById(R.id.settimetable);
        setchat = (LinearLayout)findViewById(R.id.setchat);

        settimetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settime = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(settime);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_in_right);
            }
        });

        setchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent setchat = new Intent(getApplicationContext(),UserActivity.class);
                startActivity(setchat);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_in_right);
            }
        });

    }

    private boolean backKeyPressedTwice = false;

    @Override
    public void onBackPressed(){
        if(backKeyPressedTwice) {
            super.onBackPressed();
            finishAffinity();
            System.runFinalization();
            System.exit(0);
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
