package com.example.che_ti_bleEX;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class HomeActivity extends AppCompatActivity {

    String TAG = getClass().getSimpleName();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ImageButton likefloatingbutton;

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

        LinearLayout timetable,chat,set;

        timetable = (LinearLayout)findViewById(R.id.timetable);
        chat = (LinearLayout)findViewById(R.id.chat);
        set = (LinearLayout)findViewById(R.id.set);
        likefloatingbutton = (ImageButton)findViewById(R.id.likefloatingbutton);

        likefloatingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ct = new Intent(getApplicationContext(),ChangeTimetableActivity.class);
                startActivity(ct);
            }
        });

        timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent list = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(list);
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent timetable = new Intent(getApplicationContext(),ChatActivity.class);
                startActivity(timetable);
            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent set = new Intent(getApplicationContext(),SettingActivity.class);
                startActivity(set);
            }
        });

        final TextView monday1,tuesday1,wendsday1,thuresday1,friday1;
        final TextView monday2,tuesday2,wendsday2,thuresday2,friday2;
        final TextView monday3,tuesday3,wendsday3,thuresday3,friday3;
        final TextView monday4,tuesday4,wendsday4,thuresday4,friday4;
        final TextView monday5,tuesday5,wendsday5,thuresday5,friday5;
        final TextView monday6,tuesday6,wendsday6,thuresday6,friday6;
        final TextView monday7,tuesday7,wendsday7,thuresday7,friday7;

        monday1 = (TextView)findViewById(R.id.monday1);
        monday2 = (TextView)findViewById(R.id.monday2);
        monday3 = (TextView)findViewById(R.id.monday3);
        monday4 = (TextView)findViewById(R.id.monday4);
        monday5 = (TextView)findViewById(R.id.monday5);
        monday6 = (TextView)findViewById(R.id.monday6);
        monday7 = (TextView)findViewById(R.id.monday7);
        tuesday1 = (TextView)findViewById(R.id.tuesday1);
        tuesday2 = (TextView)findViewById(R.id.tuesday2);
        tuesday3 = (TextView)findViewById(R.id.tuesday3);
        tuesday4 = (TextView)findViewById(R.id.tuesday4);
        tuesday5 = (TextView)findViewById(R.id.tuesday5);
        tuesday6 = (TextView)findViewById(R.id.tuesday6);
        tuesday7 = (TextView)findViewById(R.id.tuesday7);
        wendsday1 = (TextView)findViewById(R.id.wendsday1);
        wendsday2 = (TextView)findViewById(R.id.wendsday2);
        wendsday3 = (TextView)findViewById(R.id.wendsday3);
        wendsday4 = (TextView)findViewById(R.id.wendsday4);
        wendsday5 = (TextView)findViewById(R.id.wendsday5);
        wendsday6 = (TextView)findViewById(R.id.wendsday6);
        wendsday7 = (TextView)findViewById(R.id.wendsday7);

        thuresday1 = (TextView)findViewById(R.id.thuresday1);
        thuresday2 = (TextView)findViewById(R.id.thuresday2);
        thuresday3 = (TextView)findViewById(R.id.thuresday3);
        thuresday4 = (TextView)findViewById(R.id.thuresday4);
        thuresday5 = (TextView)findViewById(R.id.thuresday5);
        thuresday6 = (TextView)findViewById(R.id.thuresday6);
        thuresday7 = (TextView)findViewById(R.id.thuresday7);
        friday1 = (TextView)findViewById(R.id.friday1);
        friday2 = (TextView)findViewById(R.id.friday2);
        friday3 = (TextView)findViewById(R.id.friday3);
        friday4 = (TextView)findViewById(R.id.friday4);
        friday5 = (TextView)findViewById(R.id.friday5);
        friday6 = (TextView)findViewById(R.id.friday6);





//        // Create a new user with a first and last name
//        Map<String, Object> user = new HashMap<>();
//        user.put("first", "Ada");
//        user.put("last", "Lovelace");
//        user.put("born", 1815);
//
//// Add a new document with a generated ID
//        db.collection("users")
//                .add(user)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error adding document", e);
//                    }
//                });
//
//        // Create a new user with a first, middle, and last name
//        Map<String, Object> teacher = new HashMap<>();
//        user.put("first", "Alan");
//        user.put("middle", "Mathison");
//        user.put("last", "Turing");
//        user.put("born", 1912);
//
//// Add a new document with a generated ID
//        db.collection("users")
//                .add(user)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error adding document", e);
//                    }
//                });

        db.collection("Timetable")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

        CollectionReference user = db.collection("Teachers");

        for(int i=1;i<35;i++){
            DocumentReference docRef = db.collection("Teachers").document("data"+i+"");
            final int I = i;
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            String data = document.getData().get("name").toString()+"\n"+document.getData().get("teacher1").toString()+" "+document.getData().get("teacher2").toString();
                            Log.d(TAG, "DocumentSnapshot data: " + document.getData());

                            switch(I) {
                                case 1: monday1.setText(data);
                                    break;
                                case 2: monday2.setText(data);
                                    break;
                                case 3: monday3.setText(data);
                                    break;
                                case 4: monday4.setText(data);
                                    break;
                                case 5: monday5.setText(data);
                                    break;
                                case 6: monday6.setText(data);
                                    break;
                                case 7: monday7.setText(data);
                                    break;
                                case 8: tuesday1.setText(data);
                                    break;
                                case 9: tuesday2.setText(data);
                                    break;
                                case 10: tuesday3.setText(data);
                                    break;
                                case 11: tuesday4.setText(data);
                                    break;
                                case 12: tuesday5.setText(data);
                                    break;
                                case 13: tuesday6.setText(data);
                                    break;
                                case 14: tuesday7.setText(data);
                                    break;
                                case 15: wendsday1.setText(data);
                                    break;
                                case 16: wendsday2.setText(data);
                                    break;
                                case 17: wendsday3.setText(data);
                                    break;
                                case 18: wendsday4.setText(data);
                                    break;
                                case 19: wendsday5.setText(data);
                                    break;
                                case 20: wendsday6.setText(data);
                                    break;
                                case 21: wendsday7.setText(data);
                                    break;
                                case 22: thuresday1.setText(data);
                                    break;
                                case 23: thuresday2.setText(data);
                                    break;
                                case 24: thuresday3.setText(data);
                                    break;
                                case 25: thuresday4.setText(data);
                                    break;
                                case 26: thuresday5.setText(data);
                                    break;
                                case 27: thuresday6.setText(data);
                                    break;
                                case 28: thuresday7.setText(data);
                                    break;
                                case 29: friday1.setText(data);
                                    break;
                                case 30: friday2.setText(data);
                                    break;
                                case 31: friday3.setText(data);
                                    break;
                                case 32: friday4.setText(data);
                                    break;
                                case 33: friday5.setText(data);
                                    break;
                                case 34: friday6.setText(data);
                                    break;
                            }
                        } else {
                            Log.d(TAG, "No such document");
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });
        }

//        Intent intent = new Intent(getApplicationContext(),Showtimetable.class);
//        startActivity(intent);

//        firebaseAuth = FirebaseAuth.getInstance();
//        user = firebaseAuth.getCurrentUser();
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        mPostReference = firebaseDatabase.getReference("Teacher");
//
//        teachername = (TextView)findViewById(R.id.username);
//
//        Query query = mPostReference.orderByChild("email").equalTo(user.getEmail());
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot ds: dataSnapshot.getChildren()){
//                    String name = ""+ ds.child("name").getValue();
//
//                    teachername.setText(name);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


//        chat = (ImageButton)findViewById(R.id.changeTimeTable);
//        watchTimeTable = (ImageButton)findViewById(R.id.watchTimeTable);
//        setting = (ImageButton)findViewById(R.id.setting);

//        setting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent set = new Intent(getApplicationContext(),SettingActivity.class);
//                startActivity(set);
//            }
//        });
//
//        chat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent list = new Intent(getApplicationContext(),UserActivity.class);
//                startActivity(list);
//            }
//        });
//
//        watchTimeTable.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent timetable = new Intent(getApplicationContext(),Showtimetable.class);
//                startActivity(timetable);
//            }
//        });

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
