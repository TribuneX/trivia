package com.adaptionsoft.games.Printer;

import com.adaptionsoft.games.Question.Question;

public interface Printer {
    void printPlayerAdded(String playerName);

    void printCurrentPlayer();

    void printRoll(int roll);

    void printOutOfPenaltyBox();

    void printCurrentPosition();

    void printCurrentCategory(String category);

    void printNotOutOfPenaltyBox();

    void printCorrectAnswer();

    void printSentToPenaltyBox();

    void printQuestion(Question question);

}
