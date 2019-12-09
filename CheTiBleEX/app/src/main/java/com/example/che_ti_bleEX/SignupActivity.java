package com.example.che_ti_bleEX;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Hashtable;

public class SignupActivity extends AppCompatActivity {

    private RecyclerView uRecyclerView;
    private RecyclerView.Adapter uAdapter;
    private RecyclerView.LayoutManager uLayoutManager;

    private FirebaseAuth mAuth;
    Button btn1;
    EditText Name,subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btn1 = (Button)findViewById(R.id.create);
        mAuth = FirebaseAuth.getInstance();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = ((EditText)findViewById(R.id.Name)).getText().toString();
                String subject = ((EditText)findViewById(R.id.subject)).getText().toString();

                if (name.equals("") || name.isEmpty() && subject.equals("") || subject.isEmpty()) {
                    Toast.makeText(SignupActivity.this,"내용을 입력해주세요", Toast.LENGTH_SHORT).show();

                }else {
                    register();
                }
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    private void register() {
        String email = ((EditText)findViewById(R.id.email)).getText().toString();
        String password = ((EditText)findViewById(R.id.password)).getText().toString().trim();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String name = ((EditText)findViewById(R.id.Name)).getText().toString();
                            String subject = ((EditText)findViewById(R.id.subject)).getText().toString();
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("Teacher").push();

                            Hashtable<String, String> tc_user = new Hashtable<String, String>();
                            tc_user.put("name",name);
                            tc_user.put("subject",subject);
                            myRef.setValue(tc_user);
                            Toast.makeText(SignupActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                            finish();
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignupActivity.this, "회원가입 실패", Toast.LENGTH_SHORT).show();
                            Toast.makeText(SignupActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
}
