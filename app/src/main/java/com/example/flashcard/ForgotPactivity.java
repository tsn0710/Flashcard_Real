package com.example.flashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flashcard.dao.AccountDao;
import com.example.flashcard.model.Account;

public class ForgotPactivity extends AppCompatActivity {
    private AccountDao accountDao;
    private TextView textView9;
    private EditText mail;
    private Button getP;
    public Account signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pactivity);
        mail = findViewById(R.id.editTextText);
        getP = findViewById(R.id.button);
      /*  textView9 = findViewById(R.id.textView7);*/
        getP.setOnClickListener(this::GetPassword);

    }

    private void GetPassword(View view) {
        getAccount();

//        textView9.setText("Your Password is"+signup.getAccountPassword());
//        textView9.setVisibility(View.VISIBLE);
        if (signup != null) {
            Toast.makeText(this, "Your Password is" + signup.getAccountPassword(), Toast.LENGTH_SHORT).show();
        }
    }

    private void getAccount() {
        new AAccount().execute(mail.getText().toString());
    }

    private class AAccount extends AsyncTask<String, Integer, Account> {
        @Override
        protected Account doInBackground(String... integers) {
            return accountDao.GetAccountByMail(integers[0]);

        }

        @Override
        protected void onPostExecute(Account accounts) {
            super.onPostExecute(accounts);
            signup = accounts;

        }
    }
}