package com.dmscw.original;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * The FloorUnit class creates floor units to be used for the world.
 * A floor unit is a unit shaped like a square that is treated as a floor,
 * with collision on the top, left, and right sides.
 * The floor collides with any kind of game object.
 * When an enemy is bubbled, the enemy will still be stopped by a floor unit above it.
 */
public class FloorUnit extends GameObject {
	InteractableWorld world;

	public FloorUnit(InteractableWorld world, int colNum, int rowNum) {
		super(world, colNum, rowNum, Main.UNIT_SIZE, Main.UNIT_SIZE);
	}

	public void collideWith(GameObject obj) {
		double top = obj.getY();
		double bottom = top + obj.getHeight();
		if (this.overlaps(obj) && obj.yVelocity > 0) {
			if (bottom < y + height) {
				moveAboveUnit(obj);
				obj.collideWithFloor();
			}
			if (top > y){
				moveBelowUnit(obj);
				obj.collideWithCeiling();
			}
		}
	}

	@Override
	public void drawOn(Graphics2D g) {
		g.fillRect(x, y, width, height);
	}

	void moveAboveUnit(GameObject obj) {
		obj.moveTo(new Point2D.Double(obj.getX(), y - obj.getHeight()));
	}

	void moveBelowUnit(GameObject obj) {
		obj.moveTo(new Point2D.Double(obj.getX(), y + height));
	}

	void moveLeftOfUnit(GameObject obj) {
		obj.moveTo(new Point2D.Double(x - obj.getWidth(), obj.getY()));
	}

	void moveRightOfUnit(GameObject obj) {
		obj.moveTo(new Point2D.Double(x + width, obj.getY()));
	}


	@Override
	public void collideWithFloor() {

	}

	@Override
	public void collideWithCeiling() {

	}

	@Override
	public void collideWithWall() {

	}
}
