package com.adaptionsoft.games.Question;

public interface QuestionStorage {

    void addQuestion(Question q);

    Question getQuestionForCategory(String c);

}
