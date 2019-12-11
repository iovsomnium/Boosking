package com.example.che_ti_bleEX;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    EditText chatTxt;
    ImageButton btn_add;
    String email;
    List<Chat> mChat;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        database = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            email = user.getEmail();
        }

        Intent in = getIntent();
        final String stChatid = in.getStringExtra("UserUid");

        chatTxt = (EditText)findViewById(R.id.chat_txt);
        btn_add = (ImageButton)findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stText = chatTxt.getText().toString();
                if (stText.equals("") || stText.isEmpty()) {
                    Toast.makeText(ChatActivity.this,"내용을 입력해주세요", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(ChatActivity.this,email+","+ stText, Toast.LENGTH_SHORT).show();

                    Calendar c = Calendar.getInstance().getInstance();

                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HHLmm:ss");
                    String formattedDate = df.format(c.getTime());


                    DatabaseReference myRef = database.getReference().child(stChatid).child("chat").child(formattedDate);

                    Hashtable<String, String> chat = new Hashtable<String, String>();
                    chat.put("email",email);
                    chat.put("text",stText);

                    myRef.setValue(chat);
                    chatTxt.setText("");
                }


            }
        });


        ImageButton btnFinish = (ImageButton)findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mRecyclerView = (RecyclerView)findViewById(R.id.chat_rv);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mChat = new ArrayList<>();
        mAdapter = new ChatAdapter(mChat,email);
        mRecyclerView.setAdapter(mAdapter);

        DatabaseReference myRef2 = database.getReference().child(stChatid).child("chat");
        myRef2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Chat chat = dataSnapshot.getValue(Chat.class);

                // [START_EXCLUDE]
                // Update RecyclerView
                mChat.add(chat);
                mRecyclerView.scrollToPosition(mChat.size()-1);
                mAdapter.notifyItemInserted(mChat.size() - 1);
                // [END_EXCLUDE]
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
