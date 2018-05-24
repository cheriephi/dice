// ICT 4361
// Original Author: Cherié Warren
// Filename: Dice.java
// NOTE: Designed per Homework 3.
package edu.du.ict4361.dice;

import java.util.Random;

/**
 * Remembers and reports (on request) its current value (that is, the number showing), using its numberShowing property.
 * Supports sequential die values from 1 to the number of sides showing.
 * @author Cherié Warren
 *
 */
public class Dice {
	private static Random randomNumber;
	private int numberShowing;
	private int numberOfSides;

	/**
	 * The group of die face values. In a standard 6-face die; the values would be {1, 2, 3, 4, 5, 6}.
	 * In a backgammon doubling cube, the values would be {2, 4, 8, 16, 32, 64}.
	 */
	// NOTE: This array makes some private fields redundant; they are preserved in the class definition to match
	// stated requirements.
	private int[] numberShowings;
	
	
	static {
		randomNumber = new Random();		
	}
	
	
	// Constructors. Responsible for putting the object in a ready state.
	public Dice() {
		// Call the other constructor
		this(6);
	}
	
	public Dice(int numberOfSides) {
		// Initialize the dice to have sequential face values from 1 to the number of sides
		int[] numberShowings = new int[numberOfSides];
		for (int i = 0; i < numberOfSides; i++) {
			numberShowings[i] = i + 1;
		}
		initialize(numberShowings);
	}
	
	/**
	 * Creates Dice using the numberShowings provided.
	 * @param numberShowings
	 */
	public Dice(int[] numberShowings)
	{
		initialize(numberShowings.clone());
	}
	
	
	/**
	 * Put the dice into an initialized state; as per the example solution in 2a.
	 * @param numberShowings
	 */
	private void initialize(int[] numberShowings) {
		this.numberShowings = numberShowings;
		this.numberOfSides = numberShowings.length;
		
		roll();
	}
	

	/** 
	 * Randomly selects a number from one of the dice's sides for the value of the dice, and remembers it.
	 * Returns the current number showing.
	 */
	public int roll() {
		// Get a pseudo-random value between 0 and the number of the dice's sides.
		// (If the seed of the random object is known; the number can be predicted.)
		// Data is approximately uniformly distributed.
		// For more information, see:
 		//     https://docs.oracle.com/javase/7/docs/api/java/util/Random.html
		//     https://www.thoughtco.com/how-to-generate-random-numbers-2034206
		/*
		 * Example distribution:
		 *     1: 166728
		 *     2: 166241
		 *     3: 166564
		 *     4: 166670
		 *     5: 166713
		 *     6: 167085
		 */
		numberShowing = numberShowings[randomNumber.nextInt(numberOfSides)];
		return numberShowing;
	}
	
	/**
	 * @return current number showing
	 */
	public int getNumberShowing() {
		return numberShowing;
	}
	
	public int getNumberOfSides() {
		return numberOfSides;
	}
	
	/**
	 * Returns Dice diagnostic information.
	 */
	@Override
	public String toString() {
		return "Dice with " + numberOfSides + " sides is showing value "  +numberShowing;
	}
}
