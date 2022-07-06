package com.dmscw.original;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * The EnemyProjectile class handles the specificities with the projectile being shot from an enemy.
 * For example, the enemy's projectile has a different color than the projectile of a hero.
 * It also can only hurt a hero.
 */
public class EnemyProjectile extends GameObject {
	private static final int SIZE = 20;
	private static final int SPEED = 15;
	private static final int TERMINAL_VELOCITY_Y = 5;

	private boolean isActive;
	private int activeFrames;
	private int timer;

	public EnemyProjectile(InteractableWorld world, int x, int y, int direction) {
		super(x, y, SIZE, SIZE, world);
		this.direction = direction;

		xVelocity = SPEED;
		yAccel = 0;

		isActive = true;
		activeFrames = 20;
		timer = activeFrames;
	}
	
	@Override
	public void drawOn(Graphics2D g) {
		if (isActive) {
			g.setColor(new Color(0, 102, 0));
		} else {
			g.setColor(new Color(0, 102, 0, 40));
		}
		g.fillOval(x, y, width, height);
		g.setColor(Color.BLACK);
	}

	@Override
	public void update() {
		y += yVelocity;
		x += xVelocity * direction;
		updateVelocity();

		if(y < 25) {
			y = 25;
		}

		if (timer < 0) {
			isActive = false;
		}

		if (timer < -200) {
			markToRemove();
		}
		timer -= 1;
	}

	private void updateVelocity() {
		if (xVelocity > 0) {
			xVelocity -= 0.25;
		} else {
			xVelocity = 0;
		}

		if (Math.abs(yVelocity) < TERMINAL_VELOCITY_Y && !isActive) {
			yVelocity -= 0.1;
		}
	}

	@Override
	public void collideWithFloor() {
		// Nothing happen
	}

	@Override
	public void collideWithCeiling() {
		// Nothing happen
	}

	@Override
	public void collideWithWall() {
		// Nothing happen
	}

	public void collideWith(Hero hero) {
		if(this.overlaps(hero) && isActive) {
			hero.collideWithProjectile();
		}
	}

	public void collideWith(Enemy enemy) {
		//Nothing happens
	}
}
