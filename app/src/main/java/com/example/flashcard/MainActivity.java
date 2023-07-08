package com.example.flashcard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentContainerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.flashcard.dao.AppDatabase;
import com.example.flashcard.dao.QuestionDao;
import com.example.flashcard.dao.QuizAccountDao;
import com.example.flashcard.dao.QuizDao;
import com.example.flashcard.model.QuestionAnswerDisplay;
import com.example.flashcard.model.QuizAccount;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private QuizDao quizDao;
    private QuestionDao questionDao;
    private Button btnTrangChu;
    private Button btnThuVien;
    private Button btnHoSo;
    private FragmentContainerView fragmentContainerView;
    private FragmentTrangChu fragmentTrangChu;
    private FragmentThuVien fragmentThuVien;
    private FragmentHoSo fragmentHoSo;
    private FragmentDetailQuiz fragmentDetailQuiz;
    private FragmentFlashcard fragmentFlashcard;
    private FragmentLearn fragmentLearn;
    private FragmentAddNewQuiz fragmentAddNewQuiz;
    private FragmentEditQuiz fragmentEditQuiz;
    private ConstraintLayout cl;
    private QuizAccount quizAccount;
    private QuizAccountDao quizAccountDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindingView();
        bindingAction();
        realQuizList();
    }

    private void realQuizList() {

    }

    private void bindingAction() {
        btnTrangChu.setOnClickListener(this::onBtnTrangChuClick);
        btnThuVien.setOnClickListener(this::onBtnThuVienClick);
        btnHoSo.setOnClickListener(this::onBtnHoSoClick);
        cl.setOnClickListener(this::layoutClick);
    }
    private void layoutClick(View view) {
        //hide Android soft keyboard
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void onBtnHoSoClick(View view) {
        if (fragmentHoSo == null) {
            fragmentHoSo = new FragmentHoSo();
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView, fragmentHoSo)
                .commit();
    }

    private void onBtnThuVienClick(View view) {
        if (fragmentThuVien == null) {
            fragmentThuVien = new FragmentThuVien();
        }
        fragmentThuVien.setQuizDao(quizDao);
        fragmentThuVien.setContext(this);
        fragmentThuVien.setOnBtnEditQuizClickListener(this::onBtnEditQuizClick);
        fragmentThuVien.setOnBtnShowQuizClickListener(this::onBtnShowQuizClick);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView, fragmentThuVien)
                .commit();
    }

    private void onBtnShowQuizClick(int quizId) {
        //get quizaccount to add to history
        quizAccount.setQuizID(quizId);
        quizAccount.setAccountID(1);
        quizAccount.setLastTimeJoin(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        //add to history synctask
        new AddQuizToHistory().execute(quizAccountDao);
        //replace fragment
        if (fragmentDetailQuiz == null) {
            fragmentDetailQuiz = new FragmentDetailQuiz();
        }
        fragmentDetailQuiz.setQuestionDao(questionDao);
        fragmentDetailQuiz.setContext(this);
        fragmentDetailQuiz.setQuizID(quizId);
        fragmentDetailQuiz.setOnBtnLearnFlashCardClickListener(this:: onBtnLearnFlashCardClick);
        fragmentDetailQuiz.setOnBtnLearnClickListener(this:: onBtnLearnClick);
        //fragmentDetailQuiz.setOnBtnShowQuizClickListener(this::onBtnShowQuizClick);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView, fragmentDetailQuiz)
                .commit();
    }

    private void onBtnLearnClick(List<QuestionAnswerDisplay> questionAnswerDisplays) {
        //replace fragment
        if (fragmentLearn == null) {
            fragmentLearn = new FragmentLearn();
        }
        fragmentLearn.setQuestionAnswerDisplayList(questionAnswerDisplays);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView, fragmentLearn)
                .commit();
    }

    private void onBtnLearnFlashCardClick(List<QuestionAnswerDisplay> questionAnswerDisplays) {
        //replace fragment
        if (fragmentFlashcard == null) {
            fragmentFlashcard = new FragmentFlashcard();
        }
        fragmentFlashcard.setContext(this);
        fragmentFlashcard.setQuestionAnswerDisplays(questionAnswerDisplays);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView, fragmentFlashcard)
                .commit();
    }


    private void onBtnTrangChuClick(View view) {
        if (fragmentTrangChu == null) {
            fragmentTrangChu = new FragmentTrangChu();
        }
        fragmentTrangChu.setQuizDao(quizDao);
        fragmentTrangChu.setContext(this);
        fragmentTrangChu.setOnBtnEditQuizClickListener(this::onBtnEditQuizClick);
        fragmentTrangChu.setOnBtnShowQuizClickListener(this::onBtnShowQuizClick);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView, fragmentTrangChu)
                .commit();
    }

    private void bindingView() {
        btnTrangChu=findViewById(R.id.btnTrangChu);
        btnThuVien=findViewById(R.id.btnThuVien);
        btnHoSo=findViewById(R.id.btnHoSo);
        fragmentContainerView=findViewById(R.id.fragmentContainerView);
        cl=findViewById(R.id.constraintLayout);
        quizAccount=new QuizAccount();

        //DAO
        AppDatabase db =AppDatabase.getInstance(this);
        quizDao=db.quizDao();
        quizAccountDao=db.quizAccountDao();
        questionDao=db.questionDao();
        //them fragment trang chu luc mo activity
        if (fragmentTrangChu == null) {
            fragmentTrangChu = new FragmentTrangChu();
        }
        fragmentTrangChu.setQuizDao(quizDao);
        fragmentTrangChu.setContext(this);
        fragmentTrangChu.setOnBtnEditQuizClickListener(this::onBtnEditQuizClick);
        fragmentTrangChu.setOnBtnShowQuizClickListener(this::onBtnShowQuizClick);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView, fragmentTrangChu)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuuu, menu);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.optSearch).getActionView();
        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //adapter.changeList(query);
                fragmentTrangChu.changeAdapter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //adapter.resetList();
                fragmentTrangChu.resetAdapter();
                return false;
            }
        });
        searchView.setOnSearchClickListener(this::onSearchClick);
        return super.onCreateOptionsMenu(menu);
    }

    private void onSearchClick(View view) {
        if (fragmentTrangChu == null) {
            fragmentTrangChu = new FragmentTrangChu();
        }
        fragmentTrangChu.setQuizDao(quizDao);
        fragmentTrangChu.setContext(this);
        fragmentTrangChu.setOnBtnEditQuizClickListener(this::onBtnEditQuizClick);
        fragmentTrangChu.setOnBtnShowQuizClickListener(this::onBtnShowQuizClick);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView, fragmentTrangChu)
                .commit();
    }

    private void onBtnEditQuizClick(int quizID, String quizTitle) {
        if (fragmentEditQuiz == null) {
            fragmentEditQuiz = new FragmentEditQuiz();
        }
        fragmentEditQuiz.setContext(this);
        fragmentEditQuiz.setQuestionDao(questionDao);
        fragmentEditQuiz.setQuiz(quizID,quizTitle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView, fragmentEditQuiz)
                .commit();

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId==R.id.optClose){
            finish();
        }
        if(itemId==R.id.optAddNewQuiz){
            if (fragmentAddNewQuiz == null) {
                fragmentAddNewQuiz = new FragmentAddNewQuiz();
            }
            fragmentAddNewQuiz.setContext(this);
            fragmentAddNewQuiz.setQuestionDao(questionDao);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainerView, fragmentAddNewQuiz)
                    .commit();

        }
        if(itemId==R.id.optLogOut){
            /*AccountNow.thisAccount=null;
            getSharedPreferences("MyPref", MODE_PRIVATE).edit().clear().commit();
            //open login activity
            Intent intentToLoginActivity = new Intent(this,Login.class);
            this.startActivity(intentToLoginActivity);
            finish();*/
        }
        return super.onOptionsItemSelected(item);
    }
    private class AddQuizToHistory extends AsyncTask<QuizAccountDao, Integer, Boolean> {


        @Override
        protected Boolean doInBackground(QuizAccountDao... quizAccountDaos) {
            quizAccountDaos[0].insertQuizAccount(quizAccount);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean){
                //Toast.makeText(MainActivity.this, "Success: added to history", Toast.LENGTH_SHORT).show();
            }else{
                //Toast.makeText(MainActivity.this, "Error: Not added to history", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
