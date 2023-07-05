package com.example.flashcard.dao;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface QuestionDao {
    @Query("SELECT question.quizID,question.questionContent, answer.answerContent FROM question,answer WHERE question.questionID=answer.questionID and question.quizID= :quizID")
    public Cursor getAllQuestionAnswerDisplay(int quizID);
}
