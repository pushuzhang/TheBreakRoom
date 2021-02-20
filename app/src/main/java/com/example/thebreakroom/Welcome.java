package com.example.thebreakroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Welcome extends AppCompatActivity {

    private Button welcome;

    private FireBaseAuth mAuth;
    private FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void onStart() {
        super.onStart()
    }
}
