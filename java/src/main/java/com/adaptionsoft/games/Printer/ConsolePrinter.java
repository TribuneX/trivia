package com.adaptionsoft.games.Printer;

import com.adaptionsoft.games.Question.Question;
import com.adaptionsoft.games.uglytrivia.GameField;

public class ConsolePrinter {

    GameField gameField;

    public ConsolePrinter(GameField gameField) {
        this.gameField = gameField;
    }

  
    public void printPlayerAdded(String playerName) {
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + gameField.getNumPlayers());
    }

  
    public void printCurrentPlayer() {
        System.out.println(gameField.getCurrentPlayer() + " is the current player");
    }

  
    public void printRoll(int roll) {
        System.out.println("They have rolled a " + roll);
    }

  
    public void printOutOfPenaltyBox() {
        System.out.println(gameField.getCurrentPlayer() + " is getting out of the penalty box");
    }

  
    public void printCurrentPosition() {
        System.out.println(gameField.getCurrentPlayer()
                + "'s new location is "
                + gameField.getPlaceForCurrentPlayer());
    }

  
    public void printCurrentCategory(String category) {
        System.out.println("The category is " + category);
    }

  
    public void printNotOutOfPenaltyBox() {
        System.out.println(gameField.getCurrentPlayer() + " is not getting out of the penalty box");
    }

  
    public void printCorrectAnswer() {
        System.out.println("Answer was correct!!!!");
        currentCoins();
    }

    private void currentCoins() {
        System.out.println(gameField.getCurrentPlayer()
                + " now has "
                + gameField.getCurrentCoins()
                + " Gold Coins.");
    }

  
    public void printSentToPenaltyBox() {
        System.out.println("Question was incorrectly answered");
        System.out.println(gameField.getCurrentPlayer()+ " was sent to the penalty box");
    }

  
    public void printQuestion(Question question) {
        System.out.println(question.toString());
    }
}
