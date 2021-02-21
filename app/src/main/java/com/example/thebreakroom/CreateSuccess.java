package com.example.thebreakroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CreateSuccess extends AppCompatActivity {

    private ImageView Finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_success);
    }

    public void onStart() {
        super.onStart();

        Finish = (ImageView) findViewById(R.id.imageView14);
        Finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateSuccess.this,
                        HomePage.class));
            }
        });
    }
}