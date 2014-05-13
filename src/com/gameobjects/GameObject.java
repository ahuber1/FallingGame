package com.gameobjects;

import java.util.ArrayList;

import com.collision.Hitbox;

import android.graphics.Bitmap;
import android.graphics.Canvas;


public abstract class GameObject {

	/** variables for position, velocity, acceleration */
	public float x, y, dx, dy, dx2, dy2;
	
	protected int screenHeight, screenWidth;
	
	protected Hitbox hitbox;
	
	public boolean alive;
	
	protected Bitmap sprite;
	
	public void draw(Canvas canvas){
		
		if(sprite != null){
			
			canvas.drawBitmap(sprite, x, y, null);
		}
	}
	
	public void createHitboxForSprite(){
		
		if(sprite != null){
			this.hitbox = new Hitbox((int)x, (int)y, sprite.getWidth(), sprite.getHeight());
		}
	}	
	
	public float getCenterX() {
		return x + sprite.getWidth() / 2;
	}
	
	public float getCenterY() {
		return y + sprite.getHeight() / 2;
	}
	
	public void updatePhysics(float deltaTime){
		
		// update velocity
		dx += dx2 * deltaTime;
		dy += dy2 * deltaTime;

		// update position
		x += dx * deltaTime;
		y += dy * deltaTime;
		
		//update the position of the hitbox
		if(hitbox != null){
			
			hitbox.setPosition((int) x, (int) y);
		}
		
		
	}
	
	public void checkForCollisions(ArrayList<GameObject> gameObjects){
		// do nothing unless overwritten
	}
		
	public boolean isColliding(GameObject other){
	
		if(this.hitbox != null && other.hitbox != null){
			return this.hitbox.isColliding(other.hitbox);
		} else {
			return false;
		}
	}
		
	
	
}
