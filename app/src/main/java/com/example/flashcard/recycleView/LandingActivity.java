package com.example.flashcard.recycleView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.flashcard.R;

public class LandingActivity extends AppCompatActivity {
    private Button btnsignup, btnsignin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        BindingView();
        BindingAction();
    }

    void BindingView(){
        btnsignin = findViewById(R.id.btnLogIn);
        btnsignup =  findViewById(R.id.btnSignUp);
    }
    void BindingAction(){
        btnsignin.setOnClickListener(this::ToLogin);
       btnsignup.setOnClickListener(this::ToSignUp);
    }

    private void ToLogin(View view) {
        Intent signup = new Intent(LandingActivity.this, SignInActivity.class);
        startActivity(signup);
    }

    private void ToSignUp(View view) {
        Intent signup = new Intent(LandingActivity.this, SignUpActivity.class);
        startActivity(signup);
    }
}