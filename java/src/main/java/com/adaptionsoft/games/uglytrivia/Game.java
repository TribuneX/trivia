package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.PenaltyBox.PenaltyBox;
import com.adaptionsoft.games.Printer.ConsolePrinter;
import com.adaptionsoft.games.Question.Question;
import com.adaptionsoft.games.Question.QuestionMap;

import java.util.ArrayList;
import java.util.List;

public class Game {
	PenaltyBox penaltyBox = new PenaltyBox();
	QuestionMap questions = new QuestionMap();
    GameField gameField = new GameField();

	boolean isGettingOutOfPenaltyBox;
	ConsolePrinter printer = new ConsolePrinter(gameField);

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
		printer.printPlayerAdded(playerName);
		return true;
	}

	public void roll(int roll) {

		printer.printCurrentPlayer(roll);

		if (penaltyBox.isPlayerInTheBox(gameField.getCurrentPlayer())) {
			// Player got an odd number
		    if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				printer.printOutOfPenaltyBox();

				MoveAndAskQuestion(roll);

			// Player got an even number
			} else {
				printer.printNotOutOfPenaltyBox();
				isGettingOutOfPenaltyBox = false;
			}

		} else {
			MoveAndAskQuestion(roll);
		}


	}

	private void MoveAndAskQuestion(int roll) {
		gameField.movePlayer(roll);
		// TODO: Get rid of the parameter
		printer.printCurrentStats(currentCategory());
		askQuestion();
	}

	private void askQuestion() {
		// TODO: Find a more elegant solution for the current category
		printer.printQuestion(questions.getQuestionForCategory(currentCategory()));
	}


	private String currentCategory() {
		return questions.getCategoryCurrentPosition(gameField.getPlaceForCurrentPlayer());
	}

	public boolean wasCorrectlyAnswered() {
		boolean currentPlayerInPenaltyBox = penaltyBox.isPlayerInTheBox(gameField.getCurrentPlayer());

		if (isGettingOutOfPenaltyBox || !currentPlayerInPenaltyBox) {
			return performPlayersMove();
		} else {
			gameField.nextPlayersTurn();
			return true;
		}
	}

    private boolean performPlayersMove() {
        gameField.putNewCoinsForPlayer();
        printer.printCorrectAnswer();

        boolean winner = gameField.didPlayerWin();
        gameField.nextPlayersTurn();

        return winner;
    }

    public boolean wrongAnswer(){
		printer.printSentToPenaltyBox();
		penaltyBox.sendPlayerToTheBox(gameField.getCurrentPlayer());

		gameField.nextPlayersTurn();
		return true;
	}
}