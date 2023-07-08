/*
package com.example.flashcard.recycleView;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcard.R;
import com.example.flashcard.model.QuestionAnswerAdd;

public class AddQuestionViewHolder extends RecyclerView.ViewHolder{
    public EditText edtQuestionContent;
    public EditText edtAnswerContent;
    public TextView tvPosition;
    public int position;
    public Button btnRemove;
    public boolean isDelete=false;
    public AddQuestionViewHolder(@NonNull View itemView) {
        super(itemView);
        bindingView(itemView);
        bindingAction(itemView);
    }
    public QuestionAnswerAdd getQuestionAnswerAdd(){
        String question = edtQuestionContent.getText().toString().trim();
        String answer = edtAnswerContent.getText().toString().trim();
        if(question.length()==0 && answer.length()==0){
            return null;
        }
        return new QuestionAnswerAdd(position,edtQuestionContent.getText().toString(),edtAnswerContent.getText().toString(),isDelete);
    }
    private void bindingView(View itemView) {
        edtQuestionContent=itemView.findViewById(R.id.edtQuestion1);
        edtAnswerContent=itemView.findViewById(R.id.edtAnswer1);
        btnRemove=itemView.findViewById(R.id.btnRemove1);
        tvPosition=itemView.findViewById(R.id.tvPosition);
    }

    private void bindingAction(View itemView) {
        btnRemove.setOnClickListener(this::onBtnDeleteClick);
        this.itemView.setOnClickListener(this::onItemViewClick);
    }

    private void onItemViewClick(View view) {
        this.setIsRecyclable(false);
    }

    private void onBtnDeleteClick(View view) {
        //phan nay t ko biet cach xoa holder di kieu j
        //nen la co gang lam cho no bien mat
        //va danh dau de ko cho no vao database
        edtQuestionContent.setVisibility(View.GONE);
        edtAnswerContent.setVisibility(View.GONE);
        tvPosition.setVisibility(View.GONE);
        btnRemove.setVisibility(View.GONE);
        itemView.setVisibility(View.GONE);
        isDelete=true;
       */
/* if (callback != null) {
            callback.onClick(this);
        }*//*

    }
    */
/*public interface OnBtnDeleteClick {
        void onClick(AddQuestionViewHolder avh);
    }
    private AddQuestionViewHolder.OnBtnDeleteClick callback;
    public void setOnBtnDeleteClickListener(OnBtnDeleteClick callback) {
        this.callback = callback;
    }*//*

}
*/
