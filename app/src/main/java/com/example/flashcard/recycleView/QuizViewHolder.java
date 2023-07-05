package com.example.flashcard.recycleView;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcard.R;
import com.example.flashcard.dao.QuizDao;
import com.example.flashcard.model.Quiz;

public class QuizViewHolder extends RecyclerView.ViewHolder{
    private Context context;
    private TextView tvQuizTitle;
    private TextView tvNumberOfQuestion;
    private TextView tvAccountName;
    private int quizID;

    public QuizViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context=context;
        bindingView();
        bindingAction();
    }

    private void bindingAction() {
        itemView.setOnClickListener(this::onItemViewClick);
    }
    private void onItemViewClick(View view) {
        if (callback != null) {
            callback.onClick(quizID);
        }
    }
    public interface OnBtnShowQuizClick {
        void onClick(int id);
    }

    private OnBtnShowQuizClick callback;
    public void setOnBtnShowQuizClickListener(OnBtnShowQuizClick callback) {
        this.callback = callback;
    }

    private void bindingView() {
        tvQuizTitle=itemView.findViewById(R.id.tvQuizTitle);
        tvNumberOfQuestion=itemView.findViewById(R.id.tvNumberOfQuestion);
        tvAccountName=itemView.findViewById(R.id.tvAccountName);
    }
    public void bind(int quizID,String quizTitle ,int numberOfQuestion ,String accountName ) {
        this.quizID=quizID;
        tvQuizTitle.setText(quizTitle);
        tvNumberOfQuestion.setText(""+numberOfQuestion+" thuật ngữ");
        tvAccountName.setText(accountName);
    }
}
