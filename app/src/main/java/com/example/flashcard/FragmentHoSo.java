package com.example.flashcard;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.flashcard.dao.AccountDao;
import com.example.flashcard.model.Account;
import com.example.flashcard.recycleView.AccountAdapter;

import java.util.List;

public class FragmentHoSo extends Fragment {


    private EditText name,email,dob,pass,repass;

    private Button btnSignEdit;
    private AccountDao accountDao;
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    private ArrayAdapter<Account> adapter;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BindingView(view);
        BindAction();
        BindData();
    }

    private void BindData() {
        Account thisAccountLoggedIn = AccountNow.thisAccount;
        name.setText(thisAccountLoggedIn.getAccountName());
        email.setText(thisAccountLoggedIn.getAccountEmail());
        dob.setText(thisAccountLoggedIn.getAccountDOB());
    }

    void BindingView(View v){
        name = v.findViewById(R.id.edtNameEdit);
        email = v.findViewById(R.id.edtEmailEdit);
        dob = v.findViewById(R.id.edtDobEdit);
        pass = v.findViewById(R.id.edtPassEdit);
        repass = v.findViewById(R.id.edtRepassEdit);
        btnSignEdit = v.findViewById(R.id.btnSignEdit);


    }
    void BindAction(){
        btnSignEdit.setOnClickListener(this::onBtnSignEditClick);
    }

    private void onBtnSignEditClick(View view) {
        Account a = AccountNow.thisAccount;
        a.setAccountName(name.getText().toString());
        a.setAccountEmail(email.getText().toString());
        a.setAccountDOB(dob.getText().toString());
        a.setAccountPassword(pass.getText().toString());
       new UpdateAccount().execute(a);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ho_so, container, false);
    }
    private class UpdateAccount extends AsyncTask<Account, Integer, Integer> {
        @Override
        protected Integer doInBackground(Account... accounts) {
             accountDao.Update(accounts[0]);
             return 1;
        }

        @Override
        protected void onPostExecute(Integer i) {
            super.onPostExecute(i);
            if(i>=0){
                Toast.makeText(context, "Update success", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "Update failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
