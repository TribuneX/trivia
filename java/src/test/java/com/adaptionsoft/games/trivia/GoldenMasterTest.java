package com.adaptionsoft.games.trivia;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import com.adaptionsoft.games.uglytrivia.Game;

public class GoldenMasterTest {

	@Test
	public void testGoldenMaster() throws IOException {
		ByteArrayOutputStream capture = new ByteArrayOutputStream();
		PrintStream out = System.out;
		System.setOut(new PrintStream(capture));
		Random rand = new Random(GoldenMasterCapture.RANDOM_SEED);
		for (int i = 0; i < GoldenMasterCapture.NUMBER_OF_GAMES; i++) {
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
		List<String> expectedInput = Files.readAllLines(new File("test.txt").toPath());
		List<String> expectedOutput = Arrays.asList(capture.toString().split(System.lineSeparator()));
		Assert.assertEquals(expectedInput, expectedOutput);
	}
}