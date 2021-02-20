package com.example.thebreakroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MakeRoom extends AppCompatActivity {

    private ImageView newRoom;
    private EditText roomName;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().getRoot();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make);
    }

    public void onStart() {
        super.onStart();

        newRoom = (ImageView) findViewById(R.id.make_makeroom_button);
        roomName = (EditText) findViewById(R.id.make_room_name_textbox);

        newRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> map = new HashMap<String, Object>();
                map.put(roomName.getText().toString(),"");
                root.updateChildren(map);
            }
        });
    }
}