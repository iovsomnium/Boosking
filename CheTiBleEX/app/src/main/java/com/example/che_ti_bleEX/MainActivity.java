package com.example.che_ti_bleEX;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button signin,signup;
    private FirebaseAuth mAuth;


    public void goToSignUpForm(View view)
    {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        signin = (Button)findViewById(R.id.signin);
        signup = (Button)findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSignUpForm(view);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    private void login() {
        String email = ((EditText)findViewById(R.id.username)).getText().toString().trim();
        String password = ((EditText)findViewById(R.id.password)).getText().toString().trim();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity.this, "성공", Toast.LENGTH_SHORT).show();
                            Intent homeintent = new Intent(MainActivity.this,HomeActivity.class);
                            startActivity(homeintent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "실패", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    // Back키 2번 받는 코드
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

