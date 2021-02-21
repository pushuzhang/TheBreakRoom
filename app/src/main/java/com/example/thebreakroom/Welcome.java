package com.example.thebreakroom;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Welcome extends AppCompatActivity {

    private ImageView mWelcome;

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mAuth = FirebaseAuth.getInstance();
    }

    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        mCurrentUser = mAuth.getCurrentUser();

        if(mCurrentUser == null){
            mAuth.signInAnonymously().addOnCompleteListener(task -> {
                if(task.isSuccessful()){

                    mWelcome = (ImageView) findViewById(R.id.welcome_button);

                    mWelcome.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v){
                            startActivity(new Intent(Welcome.this, HomePage.class));
                        }
                    });

                }
                else{}
            });
        }
    }
}