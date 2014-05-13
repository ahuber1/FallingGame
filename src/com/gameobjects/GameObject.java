package com.gameobjects;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.collision.Hitbox;

/**
 * A basic object that will appear in a 2D game
 * @author Andrew Huber, Evan Hanger, Mark Judy
 *
 */
public abstract class GameObject {
	/** The maximum velocity in the x direction */
	public final int MAX_X;
	
	/** The maximum velocity in the y direction */
	public final int MAX_Y;
	
	/** This game object's x position */
	public int x;
	
	/** This game object's y position */
	public int y;
	
	/** This game object's velocity in the x direction */
	public double dx;
	
	/** This game object's velocity in the y direction */
	public double dy;
	
	/** Is this object alive? */
	public boolean alive;
	
	/** This object's hitbox; used to check for collisions */
	public Hitbox hitbox;
	
	/** This object's sprite */
	public Bitmap sprite;
	
	/**
	 * Creates a new game object
	 * @param maxX maximum velocity in the x direction
	 * @param maxY maximum velocity in the y direction
	 * @param sprite the sprite that represents this game object
	 */
	public GameObject(int maxX, int maxY, Bitmap sprite) {
		this.MAX_X = maxX;
		this.MAX_Y = maxY;
		this.sprite = sprite;
		this.alive = false;
	}
	
	/**
	 * Assigns an initial location for this {@code GameObject}
	 * @param x the x position
	 * @param y the y position
	 * @param dx initial velocity in the x direction
	 * @param dy initial velocity in the y direction
	 */
	public void spawn(int x, int y, double dx, double dy) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.hitbox = new Hitbox(x, y, sprite.getWidth(), sprite.getHeight());
		this.alive = true;
	}
	
	/**
	 * Draws a {@link GameObject#sprite} (if there is one) on the provided
	 * Canvas at position ({@link GameObject#x x}, {@link GameObject#x y}) 
	 * @param canvas The canvas to draw on
	 */
	public void draw(Canvas canvas) {
		
		if(sprite != null)
			canvas.drawBitmap(sprite, x, y, null);
		
	}
	
	/**
	 * Returns the center coordinate of this {@code GameObject} along the
	 * x axis
	 * @return the center coordinate of this {@code GameObject} along the
	 * x axis
	 */
	public int getCenterX() {
		if(alive == false) {
			throw new IllegalStateException("You must spawn this object before "
					+ "you can find where it is.");
		}
		
		return x + sprite.getWidth() / 2;
	}
	
	/**
	 * Returns the center coordinate of this {@code GameObject} along the
	 * y axis
	 * @return the center coordinate of this {@code GameObject} along the
	 * y axis
	 */
	public int getCenterY() {
		if(alive == false) {
			throw new IllegalStateException("You must spawn this object before "
					+ "you can find where it is.");
		}
		
		return x + sprite.getHeight() / 2;
	}
	
	/**
	 * Updates the velocity and position of this game object at a particular time
	 * @param deltaTime The amount of time that has passed since either spawning or
	 * since the last time this method was called
	 */
	public void updatePhysics(double deltaTime) {
		
		if(alive == false) {
			throw new IllegalStateException("You must spawn this object before "
					+ "you can upadte a game object's position");
		}
		
		x += dx * deltaTime;
		y += dy * deltaTime;
		hitbox.setPosition(getCenterX(), getCenterY());		
	}
	
	/**
	 * <b>For now, this method only checks if it is possible to check for collisions
	 * (i.e. if this game object has been spawned). You need to provide your own
	 * implementation of this in order for this to work properly.</b><br><br>
	 * This method will check to see if this game object is colliding with any other
	 * game object. If it is, then these colliding objects are gracefully destroyed.
	 * @param gameObjects The other objects to check for collisions
	 */
	public void checkForCollisions(ArrayList<GameObject> gameObjects) {
		
		if(alive == false) {
			throw new IllegalStateException("You must spawn this object before "
					+ "you can check for collisions");
		}
		
	}
	
	// TODO
	/**
	 * Is this game object colliding with another game object?
	 * @param other The other game object
	 * @return {@code true} if this game object is colliding with the other game object,
	 * {@code false} otherwise
	 */
	public boolean isColliding(GameObject other) {
		
		if(alive == false) {
			throw new IllegalStateException("You must spawn this object before "
					+ "you can check for collisions");
		}
		
		return this.hitbox.isColliding(other.hitbox);		
	}
}