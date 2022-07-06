package com.dmscw.original;

import javax.swing.JFrame;

/**
 * Main creates a JFrame and adds a main.GamePanel to that frame.
 * The size of the GamePanel is determined here.
 */
public class Main {
	public static final int UNIT_SIZE = 20;
	static final int WIDTH = 40;
	static final int HEIGHT = 34;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setResizable(false);
		frame.add(new GamePanel(WIDTH * UNIT_SIZE, HEIGHT * UNIT_SIZE));
		
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
