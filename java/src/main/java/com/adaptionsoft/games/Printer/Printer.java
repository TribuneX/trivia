package com.adaptionsoft.games.Printer;

import com.adaptionsoft.games.Question.QuestionStorage;

public interface Printer {
    void playerAdded(String playerName);

    void howManyPlayers();

    void currentPlayer();

    void roll(int roll);

    void outOfPenaltyBox();

    void currentPosition();

    void currentCategory(QuestionStorage questions);

    void notOutOfPenaltyBox();

    void correctAnswer();

    void sentToPenaltyBox();
}
