package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.PenaltyBox.PenaltyBox;
import com.adaptionsoft.games.Question.Question;
import com.adaptionsoft.games.Question.QuestionStorage;

public class Game {
	int[] purses  = new int[6];
	PenaltyBox penaltyBox = new PenaltyBox();

	QuestionStorage questions = new QuestionStorage();
    GameField gameField = new GameField();

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
		System.out.println(gameField.getCurrentPlayer() + " is the current player");
		System.out.println("They have rolled a " + roll);

		if (penaltyBox.isPlayerInTheBox(gameField.getCurrentPlayer())) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;

				System.out.println(gameField.getCurrentPlayer() + " is getting out of the penalty box");

				gameField.movePlayer(roll);

				System.out.println(gameField.getCurrentPlayer()
						+ "'s new location is "
						+ gameField.getPlaceForCurrentPlayer());
				System.out.println("The category is " + currentCategory());
				askQuestion();
			} else {
				System.out.println(gameField.getCurrentPlayer() + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
			}

		} else {

			gameField.movePlayer(roll);

			// TODO: 12 seems to be a default action
			System.out.println(gameField.getCurrentPlayer()
					+ "'s new location is "
					+ gameField.getPlaceForCurrentPlayer());
			System.out.println("The category is " + currentCategory());
			askQuestion();
		}

	}

	private void askQuestion() {
	    System.out.println(questions.getQuestionForCategoy(currentCategory()).getQuestion());
	}


	private String currentCategory() {
		return questions.getCategoryCurrentPosition(gameField.getPlaceForCurrentPlayer());
	}

	public boolean wasCorrectlyAnswered() {
		if (penaltyBox.isPlayerInTheBox(gameField.getCurrentPlayer())) {
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				gameField.putNewCoinsForPlayer();
                System.out.println(gameField.getCurrentPlayer()
						+ " now has "
						+ gameField.getCurrentCoins()
						+ " Gold Coins.");

				boolean winner = didPlayerWin();
				gameField.nextPlayersTurn();

				return winner;
			} else {
				gameField.nextPlayersTurn();
				return true;
			}



		} else {

			System.out.println("Answer was correct!!!!");
            gameField.putNewCoinsForPlayer();
			System.out.println(gameField.getCurrentPlayer()
					+ " now has "
					+ gameField.getCurrentCoins()
					+ " Gold Coins.");

			boolean winner = didPlayerWin();
			gameField.nextPlayersTurn();

			return winner;
		}
	}

	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(gameField.getCurrentPlayer()+ " was sent to the penalty box");
		penaltyBox.sendPlayerToTheBox(gameField.getCurrentPlayer());

		gameField.nextPlayersTurn();
		return true;
	}


	private boolean didPlayerWin() {
		return !(gameField.getCurrentCoins() == 6);
	}
}