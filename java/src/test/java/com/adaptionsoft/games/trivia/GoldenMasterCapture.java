package com.adaptionsoft.games.trivia;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;

public class GoldenMasterCapture {

	public static final long RANDOM_SEED = 424242;
	public static final int NUMBER_OF_GAMES = 1000;

	public static void main(String[] args) {
		ByteArrayOutputStream capture = new ByteArrayOutputStream();
		PrintStream out = System.out;
		System.setOut(new PrintStream(capture));
		Random rand = new Random(RANDOM_SEED);
		for (int i = 0; i < NUMBER_OF_GAMES; i++) {
			Game aGame = new Game();

			boolean notAWinner = false;

			aGame.add("Chet");
			aGame.add("Pat");
			aGame.add("Sue");

			do {

				aGame.roll(rand.nextInt(5) + 1);

				if (rand.nextInt(9) == 7) {
					notAWinner = aGame.wrongAnswer();
				} else {
					notAWinner = aGame.wasCorrectlyAnswered();
				}

			} while (notAWinner);
		}
		System.setOut(out);
		try {
			Files.write(new File("test.txt").toPath(), capture.toByteArray(), StandardOpenOption.CREATE_NEW);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}