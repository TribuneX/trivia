package com.adaptionsoft.games.Question;

public class SimpleQuestion implements Question {

    private String category;
    private String question;

    public SimpleQuestion(String category, int content){
        this.question = category + " Question " + content;
        this.category = category;
    }

    @Override
    public String toString(){
        return this.question;
    }

    @Override
    public String getCategory(){
        return this.category;
    }

}
