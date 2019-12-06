package com.example.che_ti_bleEX;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;

public class ChatActivity extends AppCompatActivity {

    private ArrayList<MainData> arrayList;
    private MainAdapter mainAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    String email;
    EditText chat_txt;
    Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            email = user.getEmail();

        }

        chat_txt = (EditText)findViewById(R.id.chat_txt);
        btn_add = (Button)findViewById(R.id.btn_add);

        recyclerView = (RecyclerView)findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);;

        arrayList = new ArrayList<>();

        mainAdapter = new MainAdapter(arrayList,email);
        recyclerView.setAdapter(mainAdapter);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stText = chat_txt.getText().toString();
                if(stText.equals("") || stText.isEmpty()){
                    Toast.makeText(ChatActivity.this,"내용을 입력해 주세요", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ChatActivity.this,email+","+stText, Toast.LENGTH_SHORT).show();

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedData = df.format(c.getTime());

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("chats").child(formattedData);

                    MainData mainData = new MainData(R.mipmap.ic_launcher,email,stText);
                    arrayList.add(mainData);
                    myRef.setValue(mainData);
                    mainAdapter.notifyDataSetChanged();
                }

            }
        });
    }
}
