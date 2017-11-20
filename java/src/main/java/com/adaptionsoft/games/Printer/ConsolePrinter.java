package com.adaptionsoft.games.Printer;

import com.adaptionsoft.games.Question.QuestionStorage;
import com.adaptionsoft.games.uglytrivia.GameField;

public class ConsolePrinter implements Printer {

    GameField gameField;

    public ConsolePrinter(GameField gameField) {
        this.gameField = gameField;
    }

    @Override
    public void playerAdded(String playerName) {
        System.out.println(playerName + " was added");
    }


    @Override
    public void howManyPlayers() {
        System.out.println("They are player number " + gameField.getNumPlayers());
    }

    @Override
    public void currentPlayer() {
        System.out.println(gameField.getCurrentPlayer() + " is the current player");
    }

    @Override
    public void roll(int roll) {
        System.out.println("They have rolled a " + roll);
    }

    @Override
    public void outOfPenaltyBox() {
        System.out.println(gameField.getCurrentPlayer() + " is getting out of the penalty box");
    }

    @Override
    public void currentPosition() {
        System.out.println(gameField.getCurrentPlayer()
                + "'s new location is "
                + gameField.getPlaceForCurrentPlayer());
    }

    @Override
    public void currentCategory(QuestionStorage questions) {
        System.out.println("The category is " + questions.getCategoryCurrentPosition(gameField.getPlaceForCurrentPlayer()));
    }

    @Override
    public void notOutOfPenaltyBox() {
        System.out.println(gameField.getCurrentPlayer() + " is not getting out of the penalty box");
    }

    @Override
    public void correctAnswer() {
        System.out.println("Answer was correct!!!!");
        currentCoins();
    }

    private void currentCoins() {
        System.out.println(gameField.getCurrentPlayer()
                + " now has "
                + gameField.getCurrentCoins()
                + " Gold Coins.");
    }

    @Override
    public void sentToPenaltyBox() {
        System.out.println("Question was incorrectly answered");
        System.out.println(gameField.getCurrentPlayer()+ " was sent to the penalty box");
    }
}
