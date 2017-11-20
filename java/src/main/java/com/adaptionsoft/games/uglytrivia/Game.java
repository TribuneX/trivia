package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.PenaltyBox.PenaltyBox;
import com.adaptionsoft.games.Question.Question;
import com.adaptionsoft.games.Question.QuestionStorage;

import java.util.*;

public class Game {
	int[] purses  = new int[6];
	PenaltyBox penaltyBox = new PenaltyBox();

	QuestionStorage questions = new QuestionStorage();
    GameField gameField = new GameField();

	int currentPlayer = 0;
	boolean isGettingOutOfPenaltyBox;


	public  Game(){
		for (int i = 0; i < 50; i++) {
			questions.addQuestion(new Question("Pop", i));
			questions.addQuestion(new Question("Science", i));
			questions.addQuestion(new Question("Sports", i));
			questions.addQuestion(new Question("Rock", i));
		}
	}

	public boolean add(String playerName) {

		gameField.addPlayer(playerName);

		purses[gameField.getNumPlayers()] = 0;

		System.out.println(playerName + " was added");
		System.out.println("They are player number " + gameField.getNumPlayers());
		return true;
	}

	public void roll(int roll) {
		System.out.println(gameField.getCurrentPlayer(currentPlayer) + " is the current player");
		System.out.println("They have rolled a " + roll);

		if (penaltyBox.isPlayerInTheBox(gameField.getCurrentPlayer(currentPlayer))) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;

				System.out.println(gameField.getCurrentPlayer(currentPlayer) + " is getting out of the penalty box");

				gameField.movePlayerForward(currentPlayer,roll);
				if (gameField.getPlaceForCurrentPlayer(currentPlayer) > 11) gameField.movePlayerBackward(currentPlayer,12);

				System.out.println(gameField.getCurrentPlayer(currentPlayer)
						+ "'s new location is "
						+ gameField.getPlaceForCurrentPlayer(currentPlayer));
				System.out.println("The category is " + currentCategory());
				askQuestion();
			} else {
				System.out.println(gameField.getCurrentPlayer(currentPlayer) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
			}

		} else {

			gameField.movePlayerForward(currentPlayer, roll);

			// TODO: 12 seems to be a default action
			if (gameField.getPlaceForCurrentPlayer(currentPlayer) > 11) gameField.movePlayerBackward(currentPlayer,12);
			System.out.println(gameField.getCurrentPlayer(currentPlayer)
					+ "'s new location is "
					+ gameField.getPlaceForCurrentPlayer(currentPlayer));
			System.out.println("The category is " + currentCategory());
			askQuestion();
		}

	}

	private void askQuestion() {
	    System.out.println(questions.getQuestionForCategoy(currentCategory()).getQuestion());
	}


	private String currentCategory() {
		return questions.getCategoryCurrentPosition(gameField.getPlaceForCurrentPlayer(currentPlayer));
	}

	public boolean wasCorrectlyAnswered() {
		if (penaltyBox.isPlayerInTheBox(gameField.getCurrentPlayer(currentPlayer))) {
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				purses[currentPlayer]++;
				System.out.println(gameField.getCurrentPlayer(currentPlayer)
						+ " now has "
						+ purses[currentPlayer]
						+ " Gold Coins.");

				boolean winner = didPlayerWin();
				currentPlayer++;
				if (currentPlayer == gameField.getNumPlayers()) currentPlayer = 0;

				return winner;
			} else {
				currentPlayer++;
				if (currentPlayer == gameField.getNumPlayers()) currentPlayer = 0;
				return true;
			}



		} else {

			System.out.println("Answer was correct!!!!");
			purses[currentPlayer]++;
			System.out.println(gameField.getCurrentPlayer(currentPlayer)
					+ " now has "
					+ purses[currentPlayer]
					+ " Gold Coins.");

			boolean winner = didPlayerWin();
			currentPlayer++;
			if (currentPlayer == gameField.getNumPlayers()) currentPlayer = 0;

			return winner;
		}
	}

	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(gameField.getCurrentPlayer(currentPlayer)+ " was sent to the penalty box");
		penaltyBox.sendPlayerToTheBox(gameField.getCurrentPlayer(currentPlayer));

		currentPlayer++;
		if (currentPlayer == gameField.getNumPlayers()) currentPlayer = 0;
		return true;
	}


	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}
}