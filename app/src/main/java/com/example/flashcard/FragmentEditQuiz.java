package com.example.flashcard;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.flashcard.dao.QuestionDao;
import com.example.flashcard.model.QuestionAnswerAdd;
import com.example.flashcard.recycleView.AAddQuestionAdapter;
import com.example.flashcard.recycleView.AEditQuestionAdapter;

import java.util.List;

public class FragmentEditQuiz extends Fragment {
    private Button btnSaveQuiz;
    private Button btnAddQuestionAnswer;
    private EditText edtQuizTitle;
    private RecyclerView recyclerView;
    private LinearLayout llAddQuestionAnswer;
    private Context context;
    private QuestionDao questionDao;
    public void setQuestionDao(QuestionDao questionDao){
        this.questionDao=questionDao;
    }
    AEditQuestionAdapter aEditQuestionAdapter;
    List<QuestionAnswerAdd> questionAnswerAddList;
    int quizID;
            String quizTitle;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindingView(view);
        bindingAction();
        initLinearLayout();
    }
    public void setQuiz(int quizID, String quizTitle){
        this.quizID=quizID;
        this.quizTitle=quizTitle;
    }

    private void initLinearLayout() {
        aEditQuestionAdapter=new AEditQuestionAdapter(context,questionDao);
        aEditQuestionAdapter.setLlAddQuestionAnswer(llAddQuestionAnswer);
        aEditQuestionAdapter.setLayout_add_question_item(R.layout.layout_add_question_item);
        aEditQuestionAdapter.setQuizID(quizID);

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        edtQuizTitle.setText(quizTitle);
    }

    private void bindingAction() {
        btnAddQuestionAnswer.setOnClickListener(this::btnAddNewItem);
        btnSaveQuiz.setOnClickListener(this::btnSaveQuizClick);
    }

    private void btnSaveQuizClick(View view) {
        String title = edtQuizTitle.getText().toString().trim();
        if(title.length()==0){
            Toast.makeText(context, "Quiz title không thể để trống", Toast.LENGTH_SHORT).show();
        }else{
            aEditQuestionAdapter.saveThisQuiz(quizID,title);
        }

    }

    private void btnAddNewItem(View view) {
        aEditQuestionAdapter.addNewItem();
    }

    private void bindingView(View view) {
        llAddQuestionAnswer=view.findViewById(R.id.llAddQuestionAnswer);
        btnSaveQuiz=view.findViewById(R.id.btnSaveQuizAdd);
        btnAddQuestionAnswer=view.findViewById(R.id.btnAddNewItem);
        edtQuizTitle=view.findViewById(R.id.edtQuizTitle);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_new_quiz_hai, container, false);
    }

    public void setContext(Context mainActivity) {
        this.context=mainActivity;

}}