package com.example.flashcard;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.flashcard.dao.QuizDao;
import com.example.flashcard.recycleView.QuizAllAdapter;

public class FragmentTrangChu extends Fragment {
    private QuizDao quizDao;
    private QuizAllAdapter adapter;
    private FragmentTrangChu fragmentTrangChu;
    private RecyclerView recyclerView;
    private Context context;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindingView(view);
        bindingAction();
        realCategoryList();
        initRecyclerView();
    }
    public void setQuizDao(QuizDao quizDao){
        this.quizDao=quizDao;
    }
    public void setContext(Context context){
        this.context=context;
    }
    private void initRecyclerView() {
        adapter=new QuizAllAdapter(quizDao, context);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    private void realCategoryList() {
    }

    private void bindingAction() {
    }

    private void bindingView(View view) {
        recyclerView=view.findViewById(R.id.rcvTrangChuQuiz);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trang_chu, container, false);
    }
}