package com.adaptionsoft.games.Question;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class QuestionStorage implements QuestionStorageInterface {

    Map<String, LinkedList<Question>> questionStorage = new HashMap<String, LinkedList<Question>>();


    @Override
    public void addQuestion(Question q) {

        // If category is not yet stored, create a new list
        if (questionStorage.get(q.getCategory()) == null){
            questionStorage.put(q.getCategory(),new LinkedList<>());
        }

        questionStorage.get(q.getCategory()).add(q);

    }

    @Override
    public Question getQuestionForCategoy(String category) {
        if (questionStorage.get(category).isEmpty()){
            return null;
        } else {
            return questionStorage.get(category).removeFirst();
        }
    }
}
