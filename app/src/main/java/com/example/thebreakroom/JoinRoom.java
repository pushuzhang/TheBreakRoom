package com.example.thebreakroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class JoinRoom extends AppCompatActivity {
<<<<<<< HEAD
=======

    private ImageView cancelButton;

>>>>>>> f86a181765a5f8c3a392f12bfbcd7f0dacfd368e
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
    }

    public void onStart() {
        super.onStart();

        cancelButton = (ImageView) findViewById(R.id.join_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JoinRoom.this,
                        HomePage.class));
            }
        });
    }

}