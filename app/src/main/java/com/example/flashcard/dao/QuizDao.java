package com.example.flashcard.dao;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.flashcard.model.Quiz;
import com.example.flashcard.model.QuizDisplay;

import java.util.List;
@Dao
public interface QuizDao {
    @Query("SELECT * FROM quiz WHERE quizID= :quizID LIMIT 1")
    Quiz getQuiz(int quizID);

    @Query("SELECT * FROM quiz")
    public List<Quiz> getAllQuiz();

    @Insert
    public  long insertQuiz(Quiz q);
    @Update
    public int updateQuiz(Quiz q);
    @Delete
    public void deleteQuiz(Quiz q);
    @Query("DELETE  FROM quiz WHERE quizID IN (:quizID)")
    public int deleteQuizWhereIDIsIn(List<Integer> quizID );
    @Query("SELECT * FROM quiz WHERE quizID= :quizID ")
    public Cursor selectWhereIdIs(int quizID);
    @Query("SELECT quizID,quizTitle,accountID FROM quiz ORDER BY :sortOrder")
    public Cursor selectAll( String sortOrder);
    @Query("SELECT COUNT(*) FROM question GROUP BY quizID")
    public Cursor getNumberOfQuestion( );
    @Query("SELECT quizID,quizTitle, accountName FROM quiz,account WHERE quiz.accountID=account.accountID")
   public Cursor getAllQuizDisplay();
}
