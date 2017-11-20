package com.adaptionsoft.games.Question;

public interface QuestionStorage {

    void addQuestion(QuestionInterface q);

    QuestionInterface getQuestionForCategory(String c);

}
