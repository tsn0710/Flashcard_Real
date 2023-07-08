package com.example.flashcard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.flashcard.dao.AccountDao;
import com.example.flashcard.model.Account;
import com.example.flashcard.recycleView.AccountAdapter;

public class FragmentHoSo extends Fragment {

    private EditText name,email,dob,pass,repass;

    private Button btnSignUp;
    private AccountAdapter db;
    private AccountDao dao;
    private ArrayAdapter<Account> adapter;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*BindingView(view);
        BindAction();*/
        //db= Room.databaseBuilder(this,AccountAdapter.class,"flashcardApp.db");
//        dao= db.CreateAccountDAO();
    }
    void BindingView(View v){
        name = v.findViewById(R.id.edtName);
        email = v.findViewById(R.id.edtEmail);
        dob = v.findViewById(R.id.edtDob);
        pass = v.findViewById(R.id.edtPass);
        repass = v.findViewById(R.id.edtRepass);
        btnSignUp = v.findViewById(R.id.btnSign);


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
        dao.Update(a);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ho_so, container, false);
    }

}