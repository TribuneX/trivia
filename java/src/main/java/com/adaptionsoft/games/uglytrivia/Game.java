package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.PenaltyBox.PenaltyBox;
import com.adaptionsoft.games.Question.Question;
import com.adaptionsoft.games.Question.QuestionStorage;

import java.util.*;

public class Game {
	ArrayList players = new ArrayList();
	int[] places = new int[6];
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

		players.add(playerName);
		gameField.addPlayer(playerName);

		places[gameField.getNumPlayers()] = 0;
		purses[gameField.getNumPlayers()] = 0;

		System.out.println(playerName + " was added");
		System.out.println("They are player number " + players.size());
		return true;
	}

	public void roll(int roll) {
		System.out.println(players.get(currentPlayer) + " is the current player");
		System.out.println("They have rolled a " + roll);

		//if (inPenaltyBox[currentPlayer]) {
		// TODO: Remove String cast
		if (penaltyBox.isPlayerInTheBox((String)players.get(currentPlayer))) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;

				System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
				places[currentPlayer] = places[currentPlayer] + roll;
				if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

				System.out.println(players.get(currentPlayer)
						+ "'s new location is "
						+ places[currentPlayer]);
				System.out.println("The category is " + currentCategory());
				askQuestion();
			} else {
				System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
			}

		} else {

			places[currentPlayer] = places[currentPlayer] + roll;
			if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

			System.out.println(players.get(currentPlayer)
					+ "'s new location is "
					+ places[currentPlayer]);
			System.out.println("The category is " + currentCategory());
			askQuestion();
		}

	}

	private void askQuestion() {
	    System.out.println(questions.getQuestionForCategoy(currentCategory()).getQuestion());
	}


	private String currentCategory() {
        return questions.getCategoryCurrentPosition(places[currentPlayer]);
	}

	public boolean wasCorrectlyAnswered() {
		//if (inPenaltyBox[currentPlayer]){
		if (penaltyBox.isPlayerInTheBox((String)players.get(currentPlayer))) {
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				purses[currentPlayer]++;
				System.out.println(players.get(currentPlayer)
						+ " now has "
						+ purses[currentPlayer]
						+ " Gold Coins.");

				boolean winner = didPlayerWin();
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;

				return winner;
			} else {
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				return true;
			}



		} else {

			System.out.println("Answer was correct!!!!");
			purses[currentPlayer]++;
			System.out.println(players.get(currentPlayer)
					+ " now has "
					+ purses[currentPlayer]
					+ " Gold Coins.");

			boolean winner = didPlayerWin();
			currentPlayer++;
			if (currentPlayer == players.size()) currentPlayer = 0;

			return winner;
		}
	}

	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(players.get(currentPlayer)+ " was sent to the penalty box");
		penaltyBox.sendPlayerToTheBox((String)players.get(currentPlayer));

		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
		return true;
	}


	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}
}