package com.dmscw.original;



import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * GameObjects are the objects on the Interactable World screen.
 * Every GameObject has a velocity, acceleration, position, direction, and dimensions.
 * GameObjects can detect if they are overlapping another GameObject and
 * must implement methods for collisions with every type of Unit.
 */
public abstract class GameObject {
	private static final double STATIC_FRICTION = 0.1;
	protected static final int GRAVITY = 1;
	private static final int TERMINAL_FALL_SPEED = 20;
	Image image;
	public InteractableWorld world;
	public int x, y;
	public int width, height;
	
	public double xVelocity, yVelocity;
	public double xAccel, yAccel;
	public int terminal_xVelocity, terminal_yVelocity;
	
	public boolean canRemove;
	public int direction;
	
	public GameObject(InteractableWorld world, int colNum, int rowNum, int width, int height) {
		this.world = world;
		x = colNum * Main.UNIT_SIZE;
		y = rowNum * Main.UNIT_SIZE;
		this.width = width;
		this.height = height;
		
		xVelocity = 0;
		yVelocity = 0;
		xAccel = 0;
		yAccel = GRAVITY;
		terminal_xVelocity = 0;
		terminal_yVelocity = TERMINAL_FALL_SPEED;
		canRemove = false;
		direction = -1;
	}
	
	public GameObject(int x, int y, int width, int height, InteractableWorld world) {
		//initializes the game object
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.world = world;
		
		xVelocity = 0;
		yVelocity = 0;
		xAccel = 0;
		yAccel = GRAVITY;
		terminal_xVelocity = 0;
		terminal_yVelocity = TERMINAL_FALL_SPEED;
		canRemove = false;
		direction = -1;
	}
	
	public abstract void drawOn(Graphics2D g);
	public abstract void collideWithFloor();
	public abstract void collideWithCeiling();
	public abstract void collideWithWall();
	
	protected void update() {
		//general update method of every game object
		if (Math.abs(xVelocity) < terminal_xVelocity) {
			xVelocity += xAccel;
		}
		if (Math.abs(xVelocity) > STATIC_FRICTION) {  // 滑动
			if (xVelocity < 0) {
				xVelocity += 1;
			} else {
				xVelocity -= 1;
			}
			x += xVelocity;
		}
		
		if (yVelocity < terminal_yVelocity) {
			yVelocity += yAccel;
		}
		y += yVelocity;
		
		if (isOffScreen()) {  // 有问题这边
			if (y > world.getHeight()) {
				y = 0;
			} else {
				y = world.getHeight();
			}
		}
	}
	
	protected void reverseDirection() {
		//reverses game object's direction
		xAccel *= -1;
		direction *= -1;
	}
	
	public double getX() {
		//returns x coordinate of upper left corner
		return x;
	}
	public double getY() {
		//returns y coordinate of upper left corner
		return y;
	}
	public double getWidth() {
		//returns width
		return width;
	}
	public double getHeight() {
		//returns height
		return height;
	}
	
	protected void markToRemove() {
		//sets whether or not something can be removed
		canRemove = true;
	}
	
	public Rectangle2D.Double getHitbox(){
		//sets hitbox for each game object
		return new Rectangle2D.Double(x, y, width, height);
	}
	
	protected boolean overlaps(GameObject obj) {
		//checks if two objects overlap or collide
		return getHitbox().intersects(obj.getHitbox());
	}
	
	protected boolean isOffScreen() {
		//checks if something is offscreen
		boolean xLow = x + width < 0;
		boolean xHigh = x > world.getWidth();
		boolean yLow = y + height < 0;
		boolean yHigh = y > world.getHeight();
		return xLow || xHigh || yLow || yHigh;
	}
	
	public void moveTo(Point2D point) {
		//moves object to a point
		x = (int) point.getX();
		y = (int) point.getY();
	}

}
