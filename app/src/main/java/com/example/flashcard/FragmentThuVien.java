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
import android.widget.Spinner;

import com.example.flashcard.dao.QuizDao;
import com.example.flashcard.model.Quiz;
import com.example.flashcard.recycleView.QuizAllAdapter;

import java.util.List;

public class FragmentThuVien extends Fragment {
    private QuizDao quizDao;
    private Spinner dropdown;
    private List<Quiz> quizList;
    private QuizAllAdapter adapter;
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
        dropdown=view.findViewById(R.id.spinner);
        recyclerView=view.findViewById(R.id.rcvThuVienQuiz);
        //dropdown
        String[] items = new String[]{"Đã học gần đây", "Đã tạo"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thu_vien, container, false);

    }

}