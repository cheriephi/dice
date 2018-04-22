// ICT 4361
// Original Author: Cherié Warren
// Filename: DiceStatistics.java
// NOTE: Designed per Homework 3b.
package edu.du.ict4361.hw3b;

/**
 * Creates/ replaces Dice, and invokes roll() on it many times.
 * Prints the number of times each number is shown.
 * @author Cherié Warren
 *
 */
public class DiceStatistics {
	private Dice[] diceArray;
	
	/**
	 * The occurrence of the sum of all dice.
	 *     The index of the array is the dice sum in question. 
	 *     The corresponding value is its occurrence. 
	 */
	private int[] totals;
	
	/**
	 * Initializes DiceStatistics for the numDice requested.
	 * @param numDice the number of dice to be created
	 */
	public DiceStatistics(int numDice) {
		// Create the dice requested.
		diceArray = new Dice[numDice];
		for (int i = 0; i < numDice; i++) {
			diceArray[i] = new Dice();
		}
		
		initStats();
	}
	
	/**
	 * Stores the input dice into memory at the input diceNum index.
	 * Resets totals.
	 * @param diceNum
	 * @param dice
	 * @return the previous entry if it exists else null.
	 */
	public Dice replaceDice(int diceNum, Dice dice) {
		// Don't do anything if the input parameters are invalid.
		if (diceNum < 0 || diceNum > diceArray.length) { return null; }
		if (dice == null) { return null; }

		Dice oldDice = diceArray[diceNum]; 
		initStats();
		diceArray[diceNum] = dice;
		
		return oldDice;
	}
	
	/**
	 * Resets the statistics.
	 */
	public void initStats() {
		// Re-create an empty counter. This gets adjusted as the values come in.
		totals = new int[0];
	}
	
	/**
	 * Rolls each Dice and sums them. Updates statistics.
	 * @return the sum of all dice
	 */
	public int rollOnce() {
		int total = rollAllDice();
		updateStatistics(total);
		return total;
	}
	
	/**
	 * Roll and sum all the dice.
	 * @return the sum of all dice
	 */
	private int rollAllDice() {
		int total = 0;
		for (int i = 0; i < diceArray.length; i++) {
			// Sum
			total += diceArray[i].roll();
		}
		return total;
	}
	
	/**
	 * Update totals, expanding the number of slots if a new numberShowing arises that is larger
	 * than ones we have seen before.
	 * By expanding the slots, we can handle non-sequential dice.
	 * @param total
	 */
	private void updateStatistics(int total) {
		if ((total + 1) <= totals.length)
		{
			totals[total]++;
		} else {
			int[] newTotals = new int[total + 1];

			System.arraycopy(totals, 0, newTotals, 0, totals.length);
			newTotals[total]++;
			totals = newTotals.clone();
		}
	}
	
	/**
	 * Returns the tally for a particular sum of dice values coming up.
	 * @return
	 */
	public int[] getTotals() {
		return totals.clone();
	}
	
	/**
	 * Returns the tally for a particular sum of dice coming up.
	 * @param index the index corresponding to the dice value sum
	 * @return its corresponding tally
	 */
	public int getTotals(int index) {
		return totals[index];
	}
	
	/**
	 * Prints any occurrences of a particular sum to the standard output (System.out).
	 * 		Number (Two-digit; zero padding): Result (thousand precision)
	 * For example: 
	 * 		02: 12,573
	 */
	public void printStatistics() {
		System.out.println("Dice results:");

		for (int i = 0; i < totals.length; i++){
			// Because Dice does not expose its possible numbersShowing,
			// DiceStatistics has no way of knowing whether a particular numberShowing is possible.
			// Only print those totals that have occurred.
			if (totals[i] > 0) {
				System.out.format("\t" + "%02d" + ": " + "%,8d%n", (i), totals[i]);
			}
		}
	}

	/**
	 * Gathers and prints various statistic test cases.
	 * @param args
	 */
	public static void main(String[] args) {
		DiceStatistics stats = new DiceStatistics(2);
		captureStatistics(stats);
		
		// Capture statistics for for three Dice of 3, 4, and 5 sides.
		stats = new DiceStatistics(3);
		for (int i = 0; i <= 2; i++) {
			Dice dice = new Dice(i + 3);
			stats.replaceDice(i, dice);
		}
		captureStatistics(stats);
	}
	
	/**
	 * Helper test method to gather and print statistics.
	 * We expect higher totals where dice can combine in multiple ways to achieve the same result.
	 * For example with two standard six-sided dice:
	 *   a 2 total can only come up one way (1 and 1; a 1 in 12 chance).
	 *   a 4 total can come up several ways (1 and 3, 2 and 2, 3, and 1; a 3 in 12 chance).
	 * @param stats
	 */
	private static void captureStatistics(DiceStatistics stats) {
		// Accumulate statistics
		for (int i = 0; i < 100_000; i++) {
			stats.rollOnce();
		}
		
		stats.printStatistics();
	}
}
