package com.dmscw.original;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * The main.Bubble class handles everything with the main.Hero's special ability, named the bubble.
 * It begins at the hero, and grows covering the whole screen.
 * Once it collides with an enemy, that enemy is bubbled.
 */
public class Bubble extends GameObject {
	private int accel;
	
	public Bubble(InteractableWorld world, int x, int y) {
		super(x, y, 0, 0, world);
		accel = 1;
	}

	@Override
	public void update() {
		if (width >= 2500) {
			markToRemove();
		}
		x -= accel / 2;
		y -= accel / 2;
		width += accel;
		height += accel;
		accel += 1;
	}
	
	@Override
	public void drawOn(Graphics2D g) {
		if (width <= 2500) {
			g.setColor(new Color(255, 204, 102, 255 - (int) (width * ((double) 255 / 2500))));
		} else {
			g.setColor(new Color(255, 204, 102, 0));
		}
		g.fillOval(x, y, width, height);
		g.setColor(Color.BLACK);
	}

	@Override
	public void collideWithFloor() {
		// Nothing happens
	}

	@Override
	public void collideWithCeiling() {
		// Nothing happens
	}

	@Override
	public void collideWithWall() {
		// Nothing happens
	}
	
	public void collideWith(Enemy enemy) {
		if (this.overlaps(enemy)) {
			enemy.collideWithProjectile();
		}
	}

}
