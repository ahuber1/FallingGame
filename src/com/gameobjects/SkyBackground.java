package com.gameobjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class SkyBackground extends GameObject {
	
	/** 
	 * Since this sky background scrolls , this is the
	 * second Bitmap that gets pushed under the first
	 */
	protected Bitmap sprite2;
	
	/**
	 * The width of the screen
	 */
	public final int SCREEN_WIDTH;
	
	/** The height of the screen */
	public final int SCREEN_HEIGHT;
	
	/** The x position of the second sprite */
	protected int sprite2x;
	
	/** The y position of the second sprite */
	protected int sprite2y;
	
	public SkyBackground(Bitmap sprite, int screenWidth, int screenHeight) {
		super(0, screenHeight / -4, sprite);
		this.SCREEN_WIDTH = screenWidth;
		this.SCREEN_HEIGHT = screenHeight;
		this.sprite2 = sprite;
		this.sprite2x = 0;
		this.sprite2y = sprite.getHeight();
		spawn(0, 0, 0, MAX_Y);
	}
	
	
	@Override
	public void updatePhysics(double deltaTime) {
		x += dx * deltaTime;
		x += dy * deltaTime;
		
		sprite2x += dx * deltaTime;
		sprite2y += dy * deltaTime;
		
		if(y <= -sprite.getHeight()) {
			y = sprite.getHeight();
		}
		
		if(sprite2y <= -sprite.getHeight()) {
			sprite2y = sprite.getHeight();
		}
	}
	
	@Override
	public void draw(Canvas canvas) {
		float sx = (SCREEN_WIDTH * 1f) / sprite.getWidth();
		float sy = (SCREEN_HEIGHT * 1f) / sprite.getHeight();
		
		canvas.scale(sx, sy);
		canvas.drawBitmap(sprite, x, y, null);
		canvas.drawBitmap(sprite2, sprite2x, sprite2y, null);
	}
	
}
