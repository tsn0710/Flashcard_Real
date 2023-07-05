package com.example.flashcard.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.flashcard.model.QuizAccount;

@Dao
public interface QuizAccountDao {
    @Insert
    public void insertQuizAccount(QuizAccount q);
}
