package com.adaptionsoft.games.Question;

public class Question implements QuestionInterface{

    private String category;
    private String question;

    public Question(String category, int content){
        this.question = category + " Question " + content;
        this.category = category;
    }

    @Override
    public String toString(){
        return this.question;
    }

    public String getCategory(){
        return this.category;
    }

}
