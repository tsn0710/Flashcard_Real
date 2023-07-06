package com.example.flashcard;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.flashcard.dao.QuestionDao;
import com.example.flashcard.dao.QuizDao;
import com.example.flashcard.model.QuestionAnswerDisplay;
import com.example.flashcard.model.Quiz;
import com.example.flashcard.recycleView.QuestionOfAQuizAdapter;
import com.example.flashcard.recycleView.QuizAllAdapter;
import com.example.flashcard.recycleView.QuizCreatedAdapter;
import com.example.flashcard.recycleView.QuizRecentAdapter;
import com.example.flashcard.recycleView.QuizViewHolder;

import java.util.List;

public class FragmentDetailQuiz extends Fragment {

    private QuestionDao questionDao;
    private int quizID;
    public QuestionOfAQuizAdapter questionOfAQuizAdapter;
    private RecyclerView recyclerView;
    private Button btnLearning;
    private Button btnFlashCard;
    private Context context;

    public void setQuestionDao(QuestionDao questionDao){
        this.questionDao=questionDao;
    }
    public void setQuizID(int quizID){
        this.quizID = quizID;
    }
    public void setContext(Context context){
        this.context=context;
    }
    private void initRecyclerView() {
        questionOfAQuizAdapter=new QuestionOfAQuizAdapter(questionDao, context);
        questionOfAQuizAdapter.setQuizID(quizID);
        recyclerView.setAdapter(questionOfAQuizAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }


    private void bindingAction() {
        btnFlashCard.setOnClickListener(this::onLearnFlashCardClick);
        btnLearning.setOnClickListener(this::onLearnClick);
    }

    private void onLearnClick(View view) {
        if (callback2 != null) {
            callback2.onClick(questionOfAQuizAdapter.getListQuestionAnswer());
        }
    }
    public interface OnBtnLearnClick {
        void onClick(List<QuestionAnswerDisplay> questionAnswerDisplays);
    }

    private FragmentDetailQuiz.OnBtnLearnClick callback2;
    public void setOnBtnLearnClickListener(FragmentDetailQuiz.OnBtnLearnClick callback) {
        this.callback2 = callback;
    }

    private void onLearnFlashCardClick(View view) {

        if (callback != null) {
            callback.onClick(questionOfAQuizAdapter.getListQuestionAnswer());
        }
    }
    public interface OnBtnLearnFlashCardClick {
        void onClick(List<QuestionAnswerDisplay> questionAnswerDisplays);
    }

    private FragmentDetailQuiz.OnBtnLearnFlashCardClick callback;
    public void setOnBtnLearnFlashCardClickListener(FragmentDetailQuiz.OnBtnLearnFlashCardClick callback) {
        this.callback = callback;
    }
    private void bindingView(View view) {
        recyclerView=view.findViewById(R.id.rcvDetailQuiz);
        btnLearning=view.findViewById(R.id.btnLearning);
        btnFlashCard=view.findViewById(R.id.btnFlashCard);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindingView(view);
        bindingAction();
        initRecyclerView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_quiz, container, false);
    }
}