package com.dmscw.original;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * The GamePanel is where the entire game is constantly updated.
 * After every few milliseconds, GamePanel calls the methods that update its InteractableWorld,
 * then repaints the window to display the new drawn graphics.
 */
public class GamePanel extends JPanel {
	InteractableWorld world;

	public GamePanel(int width, int height) {
		world = new InteractableWorld(width, height);

		this.add(world);

		world.startGame();

		Timer repaintTimer = new Timer(1000 / 60, new ActionListener() {
			//handles updating the game, going to next frame
			public void actionPerformed(ActionEvent arg0) {
				world.updatePosition();
				repaint();
			}
		});
		repaintTimer.start();
	}
}
