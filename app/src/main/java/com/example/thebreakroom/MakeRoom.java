package com.example.thebreakroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MakeRoom extends AppCompatActivity {

    private ImageView cancelButton;
    private ImageView newRoom;
    private EditText roomName;
    private DatabaseReference root = FirebaseDatabase.getInstance("https://thebreakroom-32cd2-default-rtdb.firebaseio.com").getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make);
    }

    public void onStart() {
        super.onStart();

        cancelButton = (ImageView) findViewById(R.id.make_cancel_button);
        newRoom = (ImageView) findViewById(R.id.make_makeroom_button);
        roomName = (EditText) findViewById(R.id.make_room_name_textbox);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MakeRoom.this, HomePage.class));
            }
        });
        newRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> roomID = new HashMap<String, Object>();
                String RID = UUID.randomUUID().toString();
                roomID.put(RID,"");
                root.child("Rooms").updateChildren(roomID);

                Map<String,Object> messages = new HashMap<String, Object>();
                messages.put("Messages: ","");
                root.child("Rooms").child(RID).updateChildren(messages);

                Map<String,Object> rName = new HashMap<String, Object>();
                rName.put("Name",roomName.getText().toString());
                root.child("Rooms").child(RID).updateChildren(rName);

                Map<String,Object> rUsers = new HashMap<String, Object>();
                rUsers.put("Users","");
                root.child("Rooms").child(RID).updateChildren(rUsers);

                Map<String,Object> currentUID = new HashMap<String, Object>();
                String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                currentUID.put("UID", UID);
                root.child("Rooms").child(RID).child("Users").updateChildren(currentUID);

                Map<String,Object> userID = new HashMap<String, Object>();
                userID.put(UID,"");
                root.child("User").updateChildren(userID);

                Map<String,Object> groupID = new HashMap<String, Object>();
                groupID.put("Group ID",RID);
                root.child("User").child(UID).updateChildren(groupID);

                Intent intent = new Intent(MakeRoom.this, CreateSuccess.class);
                intent.putExtra("RoomCode", roomID.toString());
                startActivity(intent);

            }
        });
    }
}