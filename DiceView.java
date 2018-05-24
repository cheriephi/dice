// ICT 4361
// Original Author: Cherié Warren
// Filename: DiceView.java
package edu.du.ict4361.dice;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class DiceView extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private Dice myDice;
	
	/**
	 * Changes the Dice associated with this DiceView.
	 * @param dice
	 */
	public void setDice(Dice dice) {
		myDice = dice;
	}
	
	/**
	 * Rolls the Dice.
	 */
	public void roll() {
		if (myDice != null)
		{
			myDice.roll();
		}
	}
	
	/**
	 * Returns the Dice's numberShowing.
	 * @return the number showing on the Dice
	 */
	public int getNumberShowing() {
		int numberShowing = (myDice != null) ? myDice.roll() : -1;
		return numberShowing;
	}

	/**
	 * Handles the Roll event by rolling the Dice and repainting the view.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() != "Roll") { return; }
		
		roll();
		repaint();
	}
	
	/**
	 * Draws the Dice inside the input graphics object.
	 */
	@Override
	public void paintComponent(Graphics graphics) {
		Color backgroundColor = Color.GRAY;
		Color diceColor = Color.WHITE;
		Color textColor = Color.BLACK;
        Font font = new Font("Arial", Font.BOLD, 18);

        // Draw the background.
		Dimension size = getSize();
		int backgroundWidth = (int)size.getWidth();
		int backgroundHeight = (int)size.getHeight();
		graphics.clearRect(0, 0, backgroundWidth, backgroundHeight);
		graphics.setColor(backgroundColor);
		graphics.fillRect(0, 0, backgroundWidth, backgroundHeight);

		// Draw a square Dice (size it based on the smallest side of the background.
		int largestDimensionSide = backgroundWidth < backgroundHeight ? backgroundWidth : backgroundHeight;
		int diceDimensionSide = (int)(largestDimensionSide * 0.75);
		int diceX = (int)((backgroundWidth - diceDimensionSide) / 2);
		int diceY = (int)((backgroundHeight - diceDimensionSide) / 2);
		graphics.setColor(diceColor);
		int diceArc = (int)(diceDimensionSide * 0.05);
		graphics.fillRoundRect(diceX, diceY, diceDimensionSide, diceDimensionSide, diceArc, diceArc);
		
		// Draw the Dice text in the center.
		String text = (myDice != null) ? Integer.toString(getNumberShowing()) : "No Dice";
		FontMetrics fontMetrics = graphics.getFontMetrics();
		int totalTextWidth = (fontMetrics.stringWidth(text) * 2) + 4;
        int textX = (backgroundWidth - totalTextWidth) / 2;
        int textY = ((backgroundHeight - fontMetrics.getHeight() + fontMetrics.getDescent() + fontMetrics.getAscent()) / 2);
        graphics.setColor(textColor);
        graphics.setFont(font);
        graphics.drawString(text, textX, textY);
	}
}
