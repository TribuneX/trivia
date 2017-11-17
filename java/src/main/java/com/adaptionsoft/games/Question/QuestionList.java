package com.adaptionsoft.games.Question;

import java.util.LinkedList;


// TODO: Storing and reading is not separated here
public class QuestionList {

    private String category;
    private LinkedList<Question> questions = new LinkedList<>();

    public QuestionList(String category){
        this.category = category;
    }

    public Question getNextQuestion(){
        if (!questions.isEmpty()) {
            return questions.removeFirst();
        } else {
            return null;
        }
    }

    public boolean hasMoreQuestions(){
        return !questions.isEmpty();
    }

    public void storeQuestion(Question q){
        this.questions.add(q);
    }

}
