// ICT 4361
// Original Author: Michael Schwartz
// Filename: DiceViewClient.java
package edu.du.ict4361.dice;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class DiceViewClient {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton button = new JButton("Roll");
		DiceView dv = new DiceView();
		button.setActionCommand("Roll");
		button.addActionListener(dv);
		
		Dice d = new Dice();
		// To test what happens with no dice, comment out the next line
		dv.setDice(d);
		
		frame.add(BorderLayout.CENTER,dv);
		frame.add(BorderLayout.SOUTH,button);
		frame.pack();
		frame.setSize(300,300);
		frame.setVisible(true);		
	}
}
