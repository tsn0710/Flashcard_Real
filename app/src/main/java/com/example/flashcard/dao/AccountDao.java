package com.example.flashcard.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.flashcard.model.Account;

import java.util.List;

@Dao
public interface AccountDao {
    @Insert
    void Insert(Account...accounts);
    @Delete
    void Delete(Account account);
    @Update
    void Update(Account account);

    @Query("SELECT * FROM account")
    List<Account> GetAccounts();


    @Query("SELECT * FROM account WHERE accountName = :accountName OR accountEmail = :pass   ")
    Account GetAccount(String accountName, String pass);

    @Query("SELECT * FROM account WHERE  accountEmail = :pass   ")
    Account GetAccountByMail(String pass);





}
