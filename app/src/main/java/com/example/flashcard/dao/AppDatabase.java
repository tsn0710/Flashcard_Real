package com.example.flashcard.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.flashcard.model.Account;
import com.example.flashcard.model.Answer;
import com.example.flashcard.model.Question;
import com.example.flashcard.model.Quiz;
import com.example.flashcard.model.QuizAccount;

@Database(entities = {Account.class, Answer.class, Question.class, Quiz.class, QuizAccount.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;
    public abstract AccountDao accountDao();
    public abstract AnswerDao answerDao();
    public abstract  QuestionDao questionDao();
    public abstract QuizDao quizDao();
    public abstract  QuizAccountDao quizAccountDao();
    public static AppDatabase getInstance(Context context){
        if(appDatabase==null){
            appDatabase= Room.databaseBuilder(context,
                    AppDatabase.class,"flashcardApp").addCallback(rdc).build();
        }
        return appDatabase;
    }
    static RoomDatabase.Callback rdc = new RoomDatabase.Callback() {
        public void onCreate (SupportSQLiteDatabase db) {
            String sql = "INSERT INTO account(accountName, accountPassword)\n" +
                    "VALUES\n" +
                    "('Nhat','nhat'),\n" +
                    "('Dung','dung'),\n" +
                    "('Duc','duc'),\n" +
                    "('Duy','duy'),\n" +
                    "('Nguyet','nguyet');";
            db.execSQL(sql);
            String sql1 = "INSERT INTO quiz(quizTitle,accountID)\n" +
                    "VALUES\n" +
                    "('Toan',1),\n" +
                    "('Ly',1),\n" +
                    "('Tin',1),\n" +
                    "('SQL',2),\n" +
                    "('Java',2),\n" +
                    "('C sharp',3),\n" +
                    "('Unity',4);";
            db.execSQL(sql1);
            String sql2 = "INSERT INTO question(questionContent,quizID)\n" +
                    "VALUES\n" +
                    "('cong',1),\n" +
                    "('tru',1),\n" +
                    "('nhan',1),\n" +
                    "('luc',2),\n" +
                    "('ban phim',3),\n" +
                    "('man hinh',3),\n" +
                    "('cau truy van',4),\n" +
                    "('sap xep tang dan',4),\n" +
                    "('chon gioi han',4),\n" +
                    "('Hat dau Java doanh nghiep',5),\n" +
                    "('PRJ301',5),\n" +
                    "('PRN211',6),\n" +
                    "('winform',6),\n" +
                    "('WPF',6),\n" +
                    "('Sprite & Animation',7),\n" +
                    "('Game Development',7);";
            db.execSQL(sql2);
            String sql3 = "INSERT INTO answer(answerContent,questionID)\n" +
                    "VALUES\n" +
                    "('+',1),\n" +
                    "('-',1),\n" +
                    "('*',1),\n" +
                    "('F',2),\n" +
                    "('keyboard',3),\n" +
                    "('monitor',3),\n" +
                    "('query',4),\n" +
                    "('ASC',4),\n" +
                    "('Limit',4),\n" +
                    "('Enterprise Java Beans',5),\n" +
                    "('servlet',5),\n" +
                    "('C# hoc ky 5',6),\n" +
                    "('window form C#',6),\n" +
                    "('windows presentation foundation',6),\n" +
                    "('png',7),\n" +
                    "('Game 2d',7);";
            db.execSQL(sql3);
            String sql4 = "INSERT INTO quizAccount(accountID,quizID,lastTimeJoin)\n" +
                    "VALUES\n" +
                    "(1,1,'2023-07-05 10:00:00'),\n" +
                    "(1,4,'2023-07-05 11:00:00'),\n" +
                    "(1,5,'2023-07-05 09:00:00'),\n" +
                    "(1,7,'2023-07-04 09:00:00'),\n" +
                    "(3,1,'2023-07-05 09:00:00'),\n" +
                    "(3,2,'2023-07-05 08:00:00'),\n" +
                    "(3,3,'2023-07-05 07:00:00'),\n" +
                    "(3,4,'2023-07-05 05:00:00'),\n" +
                    "(3,5,'2023-07-05 06:00:00'),\n" +
                    "(3,6,'2023-07-05 02:00:00'),\n" +
                    "(3,7,'2023-07-05 10:00:00'),\n" +
                    "(3,4,'2023-07-05 06:00:00'),\n" +
                    "(2,4,'2023-07-05 10:00:00');";
            db.execSQL(sql4);
        }
        public void onOpen (SupportSQLiteDatabase db) {
            // do something every time database is open

        }
    };
}
