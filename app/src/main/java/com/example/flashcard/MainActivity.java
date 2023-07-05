package com.example.flashcard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.example.flashcard.dao.AppDatabase;
import com.example.flashcard.dao.QuizDao;

public class MainActivity extends AppCompatActivity {
    private QuizDao quizDao;
    private Button btnTrangChu;
    private Button btnThuVien;
    private Button btnHoSo;
    private FragmentContainerView fragmentContainerView;
    private FragmentTrangChu fragmentTrangChu;
    private FragmentThuVien fragmentThuVien;
    private FragmentHoSo fragmentHoSo;
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
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView, fragmentThuVien)
                .commit();
    }


    private void onBtnTrangChuClick(View view) {
        if (fragmentTrangChu == null) {
            fragmentTrangChu = new FragmentTrangChu();
        }
        fragmentTrangChu.setQuizDao(quizDao);
        fragmentTrangChu.setContext(this);
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
        //DAO
        AppDatabase db =AppDatabase.getInstance(this);
        quizDao=db.quizDao();
        //them fragment trang chu luc mo activity
        if (fragmentTrangChu == null) {
            fragmentTrangChu = new FragmentTrangChu();
        }
        fragmentTrangChu.setQuizDao(quizDao);
        fragmentTrangChu.setContext(this);
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
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //adapter.resetList();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId==R.id.optClose){
            finish();
        }
        if(itemId==R.id.optAddNewQuiz){
            //Intent intentToCartActivity = new Intent(this,AddNewQuiz.class);
            //this.startActivity(intentToCartActivity);
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
}