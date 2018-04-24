package edu.du.ict4361.hw3b;

import java.util.Arrays;
import java.util.stream.IntStream;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

class DiceTest {
	@Test
	void testDefaultConstructor() {
		int[] numberShowings = IntStream.range(1, 7).toArray();
		Dice dice = new Dice();
		helper (dice, numberShowings);
	}
	
	@Test
	void testInputConstructor() {
		int[] numberShowings = IntStream.range(1, 43).toArray();
		Dice dice = new Dice(numberShowings.length);
		helper (dice, numberShowings);
	}
	
	@Test
	void testBackgammonDice() {
		int[] numberShowings = {2, 4, 8, 16, 32, 64};
		Dice dice = new Dice(numberShowings);
		helper (dice, numberShowings);
	}
	
	private void helper(Dice dice, int[] numberShowings) {
		assertEquals(numberShowings.length, dice.getNumberOfSides());

		// Roll a bunch of times and check values to give statistical confidence given the undeterministic
		// nature of the random roll.
		int enoughRollsToProveThisCodeWorks = 100;
		for (int rollCount = 0; rollCount < enoughRollsToProveThisCodeWorks; rollCount++) {
			int rollResult = dice.roll();
			// DEBUG: 
			// System.out.println("Rolled " + rollResult);

			// Check that the roll result is possible, given the number showings
			assertTrue(Arrays.stream(numberShowings).anyMatch(x -> x == rollResult));
			
			String diceString = dice.toString();
			int numberShowing = dice.getNumberShowing();
			
			assertEquals(rollResult, numberShowing);
			assertTrue(diceString.contains(Integer.toString(rollResult)));
		}
	}	
}
