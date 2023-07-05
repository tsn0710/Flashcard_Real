package com.example.flashcard.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName= "quizAccount",primaryKeys = {"accountID","quizID"})
public class QuizAccount {
    @NotNull
    @ColumnInfo(name = "accountID")
    private int accountID;
    @NotNull
    @ColumnInfo(name = "quizID")
    private int quizID;
    @NotNull
    @ColumnInfo(name = "lastTimeJoin")
    private String lastTimeJoin;

    public QuizAccount(int accountID, int quizID, @NotNull String lastTimeJoin) {
        this.accountID = accountID;
        this.quizID = quizID;
        this.lastTimeJoin = lastTimeJoin;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    @NotNull
    public String getLastTimeJoin() {
        return lastTimeJoin;
    }

    public void setLastTimeJoin(@NotNull String lastTimeJoin) {
        this.lastTimeJoin = lastTimeJoin;
    }

    @Override
    public String toString() {
        return "QuizAccount{" +
                "accountID=" + accountID +
                ", quizID=" + quizID +
                ", lastTimeJoin='" + lastTimeJoin + '\'' +
                '}';
    }
}
