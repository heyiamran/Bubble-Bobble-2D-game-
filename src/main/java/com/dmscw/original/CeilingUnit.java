package com.dmscw.original;
import java.awt.*;
import java.awt.geom.Point2D;

/**
 * The CeilingUnit class creates ceiling units to be used for the world.
 * A ceiling unit is a unit shaped like a square that is treated as a ceiling, with collision on all four sides.
 * The ceiling collides with any kind of game object.
 * Even if a game object is on top of a ceiling, the game object will be pushed down.
 */
public class CeilingUnit extends GameObject {
	InteractableWorld world;

	public CeilingUnit(InteractableWorld world, int colNum, int rowNum) {
		super(world, colNum, rowNum, Main.UNIT_SIZE, Main.UNIT_SIZE);
	}

	public void collideWith(GameObject obj) {
		if (this.overlaps(obj)) {
			moveBelowUnit(obj);
			obj.collideWithCeiling();
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
}
