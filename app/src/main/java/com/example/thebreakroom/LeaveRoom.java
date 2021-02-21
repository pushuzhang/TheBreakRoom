package com.example.thebreakroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class LeaveRoom extends AppCompatActivity {

    private ImageView cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_room);
    }

    public void onStart() {
        super.onStart();

        cancelButton = (ImageView) findViewById(R.id.join_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LeaveRoom.this,
                        HomePage.class));
            }
        });
    }

}