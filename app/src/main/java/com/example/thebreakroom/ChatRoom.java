package com.example.thebreakroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ChatRoom extends AppCompatActivity {

    private ImageView sendButton;
    private EditText inputMsg;
    private TextView chatConversation;

    private DatabaseReference root;

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

        //root = FirebaseDatabase.getInstance().getReference().child();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> blah = new HashMap<String, Object>();

                Map<String,Object> currentUID = new HashMap<String, Object>();
                String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                //currentUID.put("UID")
            }
        });
    }
}