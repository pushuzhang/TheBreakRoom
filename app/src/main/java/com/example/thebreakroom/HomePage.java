package com.example.thebreakroom;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomePage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ImageView newButton;
    private ListView rooms;

    ArrayList<Map<String, String>> roomList;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        rooms = findViewById(R.id._dynamic);

        roomList = new ArrayList<Map<String, String>>();


        initializeListView();

    }

    private void initializeListView(){
        final SimpleAdapter adapter = new SimpleAdapter(this, roomList, android.R.layout.simple_list_item_2, new String[] {"Name", "ID"}, new int[] {android.R.id.text1, android.R.id.text2});
        ref = FirebaseDatabase.getInstance("https://thebreakroom-32cd2-default-rtdb.firebaseio.com").getReference().child("Rooms");

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                HashMap<String, Object> map = (HashMap<String, Object>)dataSnapshot.getValue();
                Map<String, String> r = new HashMap<String, String>(2);
                r.put("Name", String.valueOf(map.get("Name")));
                r.put("ID",String.valueOf(dataSnapshot.getKey()));
                roomList.add(r);
                adapter.notifyDataSetChanged();
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
        rooms.setAdapter(adapter);

        rooms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HomePage.this, ChatRoom.class);
                TextView textView1 = view.findViewById(android.R.id.text1);
                TextView textView2 = view.findViewById(android.R.id.text2);
                intent.putExtra("Name", textView1.getText().toString());
                intent.putExtra("ID", textView2.getText().toString());
                startActivity(intent);
            }
        });
    }

    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            Intent startIntent = new Intent(HomePage.this, Welcome.class);
            startActivity(startIntent);
            finish();
        } else {
            newButton = findViewById(R.id.imageView18);
            newButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    startActivity(new Intent(HomePage.this, NewRoom.class));
                }
            });
        }
    }
}