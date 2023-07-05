package com.example.flashcard.model;

public class QuizDisplay {
    private int quizID;
    private String title;
    private int numberOfQuestion;
    private String authorName;

    public QuizDisplay(int quizID,String title, String authorName) {
        this.quizID=quizID;
        this.title = title;
        this.authorName = authorName;
    }

    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfQuestion() {
        return numberOfQuestion;
    }

    public void setNumberOfQuestion(int numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
