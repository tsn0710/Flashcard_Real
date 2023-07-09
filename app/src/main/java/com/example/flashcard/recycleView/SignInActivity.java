package com.example.flashcard.recycleView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.flashcard.MainActivity;
import com.example.flashcard.R;
import com.example.flashcard.dao.AccountDao;
import com.example.flashcard.model.Account;

public class SignInActivity extends AppCompatActivity {
 private EditText name, pass;
 private Button btnLog;
    private AccountAdapter db;
    private AccountDao dao;
    private ArrayAdapter<Account> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        BindingView();
        BindAction();
        db= Room.databaseBuilder(this, AccountAdapter.class,"flashcardApp.db")
                .allowMainThreadQueries().build();
        dao= db.CreateAccountDAO();
    }
    void BindingView(){
        name = findViewById(R.id.edtNameLog);

        pass = findViewById(R.id.edtPasslLog);

        btnLog = findViewById(R.id.btnLog);

    }
    void BindAction(){


        btnLog.setOnClickListener(this::LogIn);
    }

    private void LogIn(View view) {
        String username = name.getText().toString();
        String password = pass.getText().toString();
        Account a = dao.GetAccount(username,password);
        if(a!= null){
            Intent signup = new Intent(this, MainActivity.class);
            signup.setData(Uri.parse("username: " + username));
            signup.setData(Uri.parse("password: " + password));
            startActivity(signup);
        }
    }
}