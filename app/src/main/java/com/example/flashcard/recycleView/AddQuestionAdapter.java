/*
package com.example.flashcard.recycleView;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcard.R;
import com.example.flashcard.dao.QuestionDao;
import com.example.flashcard.dao.QuizDao;
import com.example.flashcard.model.Answer;
import com.example.flashcard.model.Question;
import com.example.flashcard.model.QuestionAnswerAdd;
import com.example.flashcard.model.QuestionAnswerDisplay;
import com.example.flashcard.model.Quiz;
import com.example.flashcard.model.QuizDisplay;

import java.util.ArrayList;
import java.util.List;

public class AddQuestionAdapter extends RecyclerView.Adapter<AddQuestionViewHolder>{
    List<QuestionAnswerAdd> questionAnswerAddList;
    List<QuestionAnswerAdd> questionAnswerAddListAfterDeletedAndSave;
    private QuestionDao questionDao;
    private Context context;
    private LayoutInflater inflater;
    public AddQuestionAdapter(Context context,QuestionDao questionDao) {
        //this.questionAnswerAddList = questionAnswerAddList;
        this.context = context;
        this.inflater=LayoutInflater.from(context);
        this.questionDao=questionDao;
    }


    public void setQuestionAnswerAddList(List<QuestionAnswerAdd> questionAnswerAddList) {
        this.questionAnswerAddList = questionAnswerAddList;
    }
int i=0;
    public void addNewItem(){
        questionAnswerAddList.add(new QuestionAnswerAdd(i++,"","",false));
        this.notifyItemInserted(questionAnswerAddList.size()-1);
    }
    @NonNull
    @Override
    public AddQuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=inflater.inflate(R.layout.layout_add_question_item,parent,false);
        AddQuestionViewHolder avh = new AddQuestionViewHolder(v);

        return avh;
    }


    @Override
    public void onBindViewHolder(@NonNull AddQuestionViewHolder holder, int position) {
        holder.tvPosition.setText(""+position);
        holder.edtQuestionContent.setText(questionAnswerAddList.get(position).getQuestionContent());
        holder.edtAnswerContent.setText(questionAnswerAddList.get(position).getAnswerContent());
        if(questionAnswerAddList.get(position).isDeleted()){
            holder.isDelete=true;
            holder.edtQuestionContent.setVisibility(View.GONE);
            holder.edtAnswerContent.setVisibility(View.GONE);
            holder.tvPosition.setVisibility(View.GONE);
            holder.btnRemove.setVisibility(View.GONE);
            holder.itemView.setVisibility(View.GONE);
        }else{
            holder.isDelete=false;
            holder.edtQuestionContent.setVisibility(View.VISIBLE);
            holder.edtAnswerContent.setVisibility(View.VISIBLE);
            holder.tvPosition.setVisibility(View.VISIBLE);
            holder.btnRemove.setVisibility(View.VISIBLE);
            holder.itemView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onViewRecycled(@NonNull AddQuestionViewHolder holder) {
        int index = holder.getAdapterPosition();
        if(index>=0){
            questionAnswerAddList.get(index).setQuestionContent(holder.edtQuestionContent.getText().toString());
            questionAnswerAddList.get(index).setAnswerContent(holder.edtAnswerContent.getText().toString());
            questionAnswerAddList.get(index).setDeleted(holder.isDelete);
        }
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return questionAnswerAddList.size();
    }

    public void saveThisQuiz(String title) {
        questionAnswerAddListAfterDeletedAndSave=new ArrayList<>();
        for (QuestionAnswerAdd a: questionAnswerAddList) {
            if(a.isDeleted()==false && a.isAccept()){
                questionAnswerAddListAfterDeletedAndSave.add(a);

            }
        }
        if(questionAnswerAddListAfterDeletedAndSave.size()==0){
            return;
        }
        questionAnswerAddListAfterDeletedAndSave.add(new QuestionAnswerAdd(1,title,"aaaaahihi",true));
        new AddQuizQuestionsAnswers().execute(questionAnswerAddListAfterDeletedAndSave);
    }
    private class AddQuizQuestionsAnswers extends AsyncTask<List<QuestionAnswerAdd>, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(List<QuestionAnswerAdd>... lists) {
            String i = lists[0].get(questionAnswerAddListAfterDeletedAndSave.size()-1).getQuestionContent();
            lists[0].remove(questionAnswerAddListAfterDeletedAndSave.size()-1);
            Quiz a = new Quiz();
            a.setQuizTitle(i);
            a.setAccountID(1);
            Long id = questionDao.InsertQuiz(a);
            int id1 = id.intValue();
            List<Question>questions = new ArrayList<>();
            List<Answer>answers =new ArrayList<>();
            for(QuestionAnswerAdd a1: lists[0]){
                Question a2 = new Question(0,a1.getQuestionContent(),id1);
                questions.add(a2);
                Answer a3 = new Answer(0,a1.getAnswerContent(),-1);
                answers.add(a3);
            }
            int z=0;
            List<Long> longs = questionDao.InsertQuestions(questions);
            for(Answer a4: answers){
                a4.setQuestionID(longs.get(z).intValue());
                z++;
            }
            questionDao.InsertAnswers(answers);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean){
                Toast.makeText(context, "added", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
*/
