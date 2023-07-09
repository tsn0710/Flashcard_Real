package com.example.flashcard.recycleView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.flashcard.MainActivity;
import com.example.flashcard.R;
import com.example.flashcard.dao.AccountDao;
import com.example.flashcard.dao.AppDatabase;
import com.example.flashcard.model.Account;

import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    private EditText name,email,dob,pass,repass;
    private ListView listView;
    private Button btnSignUp;
    private AccountAdapter db;
    private AccountDao dao;
    private ArrayAdapter<Account> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        BindingView();
        BindAction();
        db= Room.databaseBuilder(this, AccountAdapter.class,"flashcardApp.db")
                .allowMainThreadQueries().build();
        dao= db.CreateAccountDAO();
        load();
    }
    void BindingView(){
        name = findViewById(R.id.edtName);
        email = findViewById(R.id.edtEmail);
        dob = findViewById(R.id.edtDob);
        pass = findViewById(R.id.edtPass);
        repass = findViewById(R.id.edtRepass);
        btnSignUp = findViewById(R.id.btnSign);
        listView =  findViewById(R.id.listAcount);

    }
    void load(){
        List<Account> listA = dao.GetAccounts();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listA);
        listView.setAdapter(adapter);
    }
    void BindAction(){
        btnSignUp.setOnClickListener(this::SignUp);
    }

    private void SignUp(View view) {


        Account a = new Account();
        a.setAccountName(name.getText().toString());
        a.setAccountEmail(email.getText().toString());
        a.setAccountDOB(dob.getText().toString());
        a.setAccountPassword(pass.getText().toString());
        String username = name.getText().toString();
        String password = pass.getText().toString();
      /*  try {*/
            /*a = dao.GetAccount(username,password);
            if(a == null) {*/
                dao.Insert(a);
                load();
        /*    }
        }catch (Exception E){

        }*/



    }
}