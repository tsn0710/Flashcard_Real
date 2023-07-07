package com.example.flashcard.dao;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.flashcard.model.Answer;
import com.example.flashcard.model.Question;
import com.example.flashcard.model.Quiz;

import java.util.List;

@Dao
public interface QuestionDao {
    @Query("SELECT question.quizID,question.questionContent, answer.answerContent FROM question,answer WHERE question.questionID=answer.questionID and question.quizID= :quizID")
    public Cursor getAllQuestionAnswerDisplay(int quizID);
    @Insert
    public long InsertQuiz(Quiz quiz);
    @Insert
    public List<Long> InsertQuestions(List<Question> questions);
    @Insert
    public void InsertAnswers(List<Answer> answers);
}
