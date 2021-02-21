package com.example.thebreakroom;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ChatRoom extends AppCompatActivity {

    private ImageView sendButton;
    private EditText inputMsg;
    private TextView chatConversation;
    private ImageView backButton;
    private ImageView leaveRoom;

    private String roomName, roomID, userID;
    private DatabaseReference root;
    private String tempKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
    }

    public void onStart() {
        super.onStart();

        sendButton = (ImageView) findViewById(R.id.imageView17);
        inputMsg = (EditText) findViewById(R.id.editTextTextMultiLine2);
        chatConversation = (TextView) findViewById(R.id.textView17);
        backButton = (ImageView) findViewById(R.id.imageView15);
        leaveRoom = (ImageView) findViewById(R.id.imageView16);

        roomName = getIntent().getExtras().get("Name").toString();
        roomID = getIntent().getExtras().get("ID").toString();
        userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        TextView title = (TextView) findViewById(R.id.textView24);
        title.setText(roomName);

        root = FirebaseDatabase.getInstance("https://thebreakroom-32cd2-default-rtdb.firebaseio.com").getReference().child("Rooms").child(roomID).child("Messages");

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> map = new HashMap<String, Object>();
                tempKey = root.push().getKey();
                root.updateChildren(map);

                DatabaseReference message_root = root.child(tempKey);
                Map<String,Object> map2 = new HashMap<String, Object>();
                map2.put("UID", userID);
                map2.put("Message", inputMsg.getText().toString());

                message_root.updateChildren(map2);
            }
        });

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                append_chat_conversation(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                append_chat_conversation(dataSnapshot);
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

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChatRoom.this, HomePage.class));
            }
        });

        leaveRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatRoom.this, LeaveRoom.class);
                startActivity(intent);
            }
        });
    }

    private String chatMsg, chatUserID;
    private void append_chat_conversation(DataSnapshot dataSnapshot) {
        Iterator i = dataSnapshot.getChildren().iterator();

        while (i.hasNext()) {
            chatMsg = (String) ((DataSnapshot)i.next()).getValue();
            chatUserID = (String) ((DataSnapshot)i.next()).getValue();

            chatConversation.append(chatUserID +" : "+chatMsg +" \n");
        }
    }
}