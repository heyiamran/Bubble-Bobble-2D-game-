package com.dmscw.original;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * The WallUnit class creates wall units to be used for the world.
 * A wall unit is an unit shaped like a square that is treated as a wall,
 * with collision on all four sides.
 * The wall collides with any kind of game object.
 */
public class WallUnit extends GameObject {
	InteractableWorld world;

	public WallUnit(InteractableWorld world, int colNum, int rowNum) {
		super(world, colNum, rowNum, Main.UNIT_SIZE, Main.UNIT_SIZE);
	}

	public void collideWith(GameObject obj) {
		double center = obj.getHitbox().getCenterX();
		if (this.overlaps(obj)) {
			if (center > this.getHitbox().getCenterX()) {
				moveRightOfUnit(obj);
				obj.collideWithWall();
			} else if (center < this.getHitbox().getCenterX()){
				moveLeftOfUnit(obj);
				obj.collideWithWall();
			} else {
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
