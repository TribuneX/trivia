package com.adaptionsoft.games.Question;

import java.util.*;

public class QuestionMap implements QuestionStorage {

    Map<String, LinkedList<Question>> questionStorage;
    Set<String> categoriesSet;

    public QuestionMap() {
        this.questionStorage = new HashMap<>();
        this.categoriesSet = new LinkedHashSet<>();
    }

    @Override
    public void addQuestion(Question q) {

        // If category is not yet stored, create a new list
        if (questionStorage.get(q.getCategory()) == null){
            questionStorage.put(q.getCategory(),new LinkedList<>());
        }

        questionStorage.get(q.getCategory()).add(q);
        categoriesSet.add(q.getCategory());

    }

    @Override
    public Question getQuestionForCategory(String category) {
        if (questionStorage.get(category).isEmpty()){
            return null;
        } else {
            return questionStorage.get(category).removeFirst();
        }
    }

    public String getCategoryCurrentPosition(int currentPlayer) {
        return categoriesSet.toArray(new String[categoriesSet.size()])[currentPlayer%categoriesSet.size()];
    }
}
