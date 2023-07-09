package com.example.flashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.flashcard.dao.AccountDao;
import com.example.flashcard.dao.AppDatabase;

public class LandingActivity extends AppCompatActivity {
    private Button btnsignup, btnsignin;
    private FragmentSignIn fragmentSignIn;
    private  FragmentSignUp fragmentSignUp;
    private AccountDao accountDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        BindingView();
        BindingAction();
        if (fragmentSignIn == null) {
            fragmentSignIn = new FragmentSignIn();
        }
        fragmentSignIn.setAccountDao(accountDao);
        fragmentSignIn.setContext(this);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentForSignInAndSignUp, fragmentSignIn)
                .commit();
    }

    void BindingView(){
        btnsignin = findViewById(R.id.btnLogIn);
        btnsignup =  findViewById(R.id.btnSignUp);
        AppDatabase db =AppDatabase.getInstance(this);
        accountDao=db.accountDao();

    }
    void BindingAction(){
        btnsignin.setOnClickListener(this::ToLogin);
       btnsignup.setOnClickListener(this::ToSignUp);
    }

    private void ToLogin(View view) {
        if (fragmentSignIn == null) {
            fragmentSignIn = new FragmentSignIn();
        }
        fragmentSignIn.setAccountDao(accountDao);
        fragmentSignIn.setContext(this);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentForSignInAndSignUp, fragmentSignIn)
                .commit();
    }

    private void ToSignUp(View view) {
        if (fragmentSignUp == null) {
            fragmentSignUp = new FragmentSignUp();
        }
        fragmentSignUp.setAccountDao(accountDao);
        fragmentSignUp.setContext(this);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentForSignInAndSignUp, fragmentSignUp)
                .commit();
    }
}