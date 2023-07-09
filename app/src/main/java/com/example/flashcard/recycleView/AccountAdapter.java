package com.example.flashcard.recycleView;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.flashcard.dao.AccountDao;
import com.example.flashcard.model.Account;

@Database(entities = {Account.class}, version = 1)
public abstract class AccountAdapter extends RoomDatabase {
    public abstract AccountDao CreateAccountDAO();

}
