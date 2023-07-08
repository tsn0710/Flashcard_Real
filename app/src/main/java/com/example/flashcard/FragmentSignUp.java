package com.example.flashcard;

import android.content.Context;
import android.os.AsyncTask;
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

import com.example.flashcard.dao.AccountDao;
import com.example.flashcard.dao.QuizDao;
import com.example.flashcard.model.Account;
import com.example.flashcard.recycleView.AccountAdapter;

import java.util.List;

public class FragmentSignUp extends Fragment {
    private EditText name,email,dob,pass,repass;
    private ListView listView;
    private Button btnSignUp;
    private AccountAdapter db;
    private AccountDao accountDao;
    private ArrayAdapter<Account> adapter;
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }
    public void setAccountDao(AccountDao accountDao){
        this.accountDao=accountDao;
    }

    void BindingView(View view){
        name = view.findViewById(R.id.edtName);
        email = view.findViewById(R.id.edtEmail);
        dob = view.findViewById(R.id.edtDob);
        pass = view.findViewById(R.id.edtPass);
        repass = view.findViewById(R.id.edtRepass);
        btnSignUp = view.findViewById(R.id.btnSign);
        listView =  view.findViewById(R.id.listAcount);

    }
    void load(){
        new AllAccount().execute(07102001);
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
        accountDao.Insert(a);
        load();
        /*    }
        }catch (Exception E){

        }*/



    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BindingView(view);
        BindAction();
        load();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }
    private class AllAccount extends AsyncTask<Integer, Integer, List<Account>> {
        @Override
        protected List<Account> doInBackground(Integer... integers) {
            return accountDao.GetAccounts();

        }

        @Override
        protected void onPostExecute(List<Account> accounts) {
            super.onPostExecute(accounts);
            adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1,accounts);
            listView.setAdapter(adapter);
        }
    }
}