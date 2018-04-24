package edu.du.ict4361.hw3b;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

// TODO: Tests don't seem to run properly in parallel. Is this a problem with static?
class DiceStatisticsTest {

	/**
	 * replaceDice tests. Invalid conditions. We expect the statistics will remain, null will be returned, no dice will be changed.
	 */
	@Test
	// TODO: Move to parameterized test? See https://www.youtube.com/watch?v=uuCNaNvkIDE
	void testReplaceDiceNegativeIndex() {
		testInvalidDice(-1, new Dice());		
	}

	@Test
	void testReplaceDiceHighIndex() {
		testInvalidDice(1_000, new Dice());		
	}	
	
	@Test
	void testReplaceDiceNullDice() {
		Dice newDice = null;
		testInvalidDice(1, newDice);		
	}
	
	private void testInvalidDice(int diceNum, Dice dice) {
		// Set up
		DiceStatistics stats = new DiceStatistics(2);
		int rollOnceResult = stats.rollOnce();
		System.out.println("testInvalidDice rollOnceResult: " + rollOnceResult);
		int[] actualTotals = stats.getTotals();
		// Because the totals returned will differ in length based on the random
		// output of the roll; sum them all. We are trying to prove that the statistics 
		// doesn't get reset.
		int grandTotal = IntStream.of(actualTotals).sum();
		System.out.println("testInvalidDice grandTotal: " + grandTotal);
		assertEquals(1, grandTotal);
		
		// Replace
		Dice oldDice;
		oldDice = stats.replaceDice(diceNum, dice);		
		assertTrue(oldDice == null);
		
		rollOnceResult = stats.rollOnce();
		System.out.println("testInvalidDice rollOnceResult: " + rollOnceResult);
		actualTotals = stats.getTotals();
		grandTotal = IntStream.of(actualTotals).sum();
		System.out.println("testInvalidDice grandTotal: " + grandTotal);
		assertEquals(2, grandTotal);
	}
	
	// TODO: Refactor to not repeat with testInvalidDice
	@Test
	void testReplaceDice() {
		// Set up
		DiceStatistics stats = new DiceStatistics(2);
		int rollOnceResult = stats.rollOnce();
		System.out.println("testReplaceDice rollOnceResult: " + rollOnceResult);
		int[] actualTotals = stats.getTotals();
		// Because the totals returned will differ in length based on the random
		// output of the roll; sum them all. We are trying to prove that the statistics 
		// doesn't get reset.
		int grandTotal = IntStream.of(actualTotals).sum();
		System.out.println("testReplaceDice grandTotal: " + grandTotal);
		assertEquals(1, grandTotal);
		
		// Replace
		Dice oldDice;
		Dice newDice = new Dice(5);
		oldDice = stats.replaceDice(1, newDice);		
		assertTrue(oldDice != null);
		
		rollOnceResult = stats.rollOnce();
		System.out.println("testReplaceDice rollOnceResult: " + rollOnceResult);
		actualTotals = stats.getTotals();
		grandTotal = IntStream.of(actualTotals).sum();
		System.out.println("testReplaceDice grandTotal: " + grandTotal);
		assertEquals(1, grandTotal);
	}
	
	/**
	 * Tests the getTotals interface.
	 */
	// Tests both the getTotals array and getTotals index.
	// Uses dice of differing numbers of sides, rolling them a few times to ensure sufficient test coverage.
	@Test
	void testGetTotals() {
		fail("Manual test");
	}
	
	/**
	 * Tests that rollOnce returns a value within the possible sums of the dice.
	 */
	@Test
	void testRollOnce() {
		// Create non-sequential dice
		DiceStatistics stats = new DiceStatistics(2);
		int[] diceOneNumberShowings = {3, 6, 9, 13};
		int[] diceTwoNumberShowings = {7, 15};
		Dice diceOne = new Dice(diceOneNumberShowings);
		Dice diceTwo = new Dice(diceTwoNumberShowings);
		stats.replaceDice(0, diceOne);
		stats.replaceDice(1, diceTwo);
		
		// Determine the possible correct combinations
		int[] expectedNumberShowings = new int[diceOneNumberShowings.length * diceTwoNumberShowings.length];
		int expectedNumberShowingsIndex = 0;
		for (int diceOneNumberShowing: diceOneNumberShowings) {
			for (int diceTwoNumberShowing : diceTwoNumberShowings) {
				System.out.println("expectedNumberShowingsIndex: " + expectedNumberShowingsIndex 
						+ "; diceOneNumberShowing: " + diceOneNumberShowing 
						+ "; diceTwoNumberShowing: " + diceTwoNumberShowing);

				expectedNumberShowings[expectedNumberShowingsIndex] = diceOneNumberShowing + diceTwoNumberShowing;
				expectedNumberShowingsIndex++;				
			}
		}
		
		// Roll the dice and validate results.
		// Test it a bunch of times because this is not conclusive testing.
		int enoughRollsToProveThisCodeWorks = 1_000;
		for (int i = 0; i < enoughRollsToProveThisCodeWorks; i++) {
			int rollResult = stats.rollOnce();
			System.out.println("Rolled " + rollResult);

			// Check that the roll result is possible, given the number showings
			assertTrue(Arrays.stream(expectedNumberShowings).anyMatch(x -> x == rollResult));
		}
	}
/*
 * TODO: Tests
 * Test constructor 2 args; 4 args. Manual test via print statistics to see that has right number of dice?
 * * does replacedice return the previous dice does it return null if null does it handle an negative index, a null dice, a grater than index, does it reset stats
 * does print stats print something?
 * does init stats reset the stats?
* 3 times, testing each; create 3 dice
 * test encapsulation
 * */
}
