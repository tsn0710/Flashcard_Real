package com.example.flashcard.recycleView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcard.R;
import com.example.flashcard.dao.QuizDao;
import com.example.flashcard.model.QuizDisplay;

import java.util.ArrayList;
import java.util.List;

public class QuizAllAdapter extends RecyclerView.Adapter<QuizViewHolder>{
    private QuizDao quizDao;
    private List<QuizDisplay> listQuiz;
    private Context context;
    private LayoutInflater inflater;
    public QuizAllAdapter(QuizDao quizDao, Context context) {
        //this.realQuizList=categoryList;
        this.quizDao = quizDao;
        new LoadListQuiz().execute(quizDao);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=inflater.inflate(R.layout.layout_quiz_item,parent,false);
        QuizViewHolder cvh = new QuizViewHolder(v,context);
        cvh.setOnBtnShowQuizClickListener(callback);
        return cvh;
    }
    private QuizViewHolder.OnBtnShowQuizClick callback;

    public void setOnBtnShowDataClickListener(QuizViewHolder.OnBtnShowQuizClick callback) {
        this.callback = callback;
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {

        QuizDisplay c = listQuiz.get(position);
        holder.bind(c.getQuizID(),c.getTitle(),c.getNumberOfQuestion(),c.getAuthorName());
    }

    @Override
    public int getItemCount() {
        return listQuiz.size();
    }
    private class LoadListQuiz extends AsyncTask<QuizDao, Integer, List<QuizDisplay>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            listQuiz=new ArrayList<>();
        }

        @Override
        protected List<QuizDisplay> doInBackground(QuizDao... quizDaos) {
            QuizDao quizDao1 = quizDaos[0];
            Cursor c = quizDao1.getAllQuizDisplay();
            Cursor n=quizDao1.getNumberOfQuestion();
            List<QuizDisplay> listQuiz2= new ArrayList<>();
            if (c.moveToFirst()) {
                do {
                    @SuppressLint("Range") int quizID = c.getInt(c.getColumnIndex("quizID"));
                    @SuppressLint("Range") String title = c.getString(c.getColumnIndex("quizTitle"));
                    @SuppressLint("Range") String authorName = c.getString(c.getColumnIndex("accountName"));
                    QuizDisplay ca = new QuizDisplay(quizID,title,authorName);
                    listQuiz2.add(ca);
                } while (c.moveToNext());
            }
            int i=0;
            if (n.moveToFirst()) {
                do {

                    @SuppressLint("Range") int number = n.getInt(0);
                    listQuiz2.get(i).setNumberOfQuestion(number);
                    i++;
                } while (n.moveToNext());
            }
            return listQuiz2;
        }

        @Override
        protected void onPostExecute(List<QuizDisplay> quizzes) {
            super.onPostExecute(quizzes);
            listQuiz=quizzes;
            QuizAllAdapter.this.notifyDataSetChanged();
        }
    }
}
