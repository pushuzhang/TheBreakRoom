package com.example.thebreakroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class NewRoom extends AppCompatActivity {

    private ImageView joinButton;
    private ImageView makeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_room);
    }

    public void onStart() {
        super.onStart();

        makeButton = (ImageView) findViewById(R.id.newroom_make_button);
        makeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewRoom.this,
                        MakeRoom.class));
            }
        });
        joinButton = (ImageView) findViewById(R.id.newroom_join_button);
        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewRoom.this,
                        JoinRoom.class));
            }
        });

    }

}