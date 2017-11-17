package com.adaptionsoft.games.Question;

public class Question {

    private String category;
    private String question;

    public Question(String question, String category){
        this.question = question;
        this.category = category;
    }

    public String getQuestion(){
        return this.question;
    }

    public String getCategory(){
        return this.category;
    }

}
