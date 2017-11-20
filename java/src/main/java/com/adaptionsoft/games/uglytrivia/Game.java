package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.PenaltyBox.PenaltyBox;
import com.adaptionsoft.games.Printer.ConsolePrinter;
import com.adaptionsoft.games.Printer.Printer;
import com.adaptionsoft.games.Question.Question;
import com.adaptionsoft.games.Question.QuestionStorage;

import java.util.ArrayList;
import java.util.List;

public class Game {
	PenaltyBox penaltyBox = new PenaltyBox();

	QuestionStorage questions = new QuestionStorage();
    GameField gameField = new GameField();

	boolean isGettingOutOfPenaltyBox;

	Printer printer = new ConsolePrinter(gameField);

	public Game(){
		List<String> categories = new ArrayList<>();
		categories.add("Pop");
		categories.add("Science");
		categories.add("Sports");
		categories.add("Rock");
		createQuestions(categories);
	}

	private void createQuestions(List<String> categories) {
		for (int i = 0; i < 50; i++) {
			for (String category: categories){
				questions.addQuestion(new Question(category,i));
			}
		}
	}

	public boolean add(String playerName) {
		gameField.addPlayer(playerName);
		printer.playerAdded(playerName);
		return true;
	}

	public void roll(int roll) {

		printer.currentPlayer();
		printer.roll(roll);

		if (penaltyBox.isPlayerInTheBox(gameField.getCurrentPlayer())) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;

				printer.outOfPenaltyBox();

				gameField.movePlayer(roll);

				printer.currentPosition();

				// TODO: Get rid of the parameter
				printer.currentCategory(questions.getCategoryCurrentPosition(gameField.getPlaceForCurrentPlayer()));
				askQuestion();
			} else {
				printer.notOutOfPenaltyBox();
				isGettingOutOfPenaltyBox = false;
			}

		} else {

			gameField.movePlayer(roll);

			printer.currentPosition();
			printer.currentCategory(questions.getCategoryCurrentPosition(gameField.getPlaceForCurrentPlayer()));
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
				gameField.putNewCoinsForPlayer();
				printer.correctAnswer();


				boolean winner = gameField.didPlayerWin();
				gameField.nextPlayersTurn();

				return winner;
			} else {
				gameField.nextPlayersTurn();
				return true;
			}

		} else {
			gameField.putNewCoinsForPlayer();
            printer.correctAnswer();

			boolean winner = gameField.didPlayerWin();
			gameField.nextPlayersTurn();

			return winner;
		}
	}

	public boolean wrongAnswer(){
		printer.sentToPenaltyBox();
		penaltyBox.sendPlayerToTheBox(gameField.getCurrentPlayer());

		gameField.nextPlayersTurn();
		return true;
	}
}