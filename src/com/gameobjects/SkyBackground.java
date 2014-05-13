package com.gameobjects;

import com.example.fallinggametest.GameLoop;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

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
	
	/**
	 * Creates a background of the sky
	 * @param sprite The sprite that will be used to represent the background
	 * @param screenWidth The width of the screen this sprite will be displayed on
	 * @param screenHeight The height of the screen this sprite will be displayed on
	 */
	public SkyBackground(Bitmap sprite, int screenWidth, int screenHeight) {
		super(0, screenHeight / -4, sprite);
		
		Log.i("SkyBackground", "" + screenHeight);
		Log.i("SkyBackground", "" + sprite.getHeight());
		
		this.SCREEN_WIDTH = screenWidth;
		this.SCREEN_HEIGHT = screenHeight;
		this.sprite2 = sprite;
		this.sprite2x = 0;
		this.sprite2y = sprite2.getHeight();
		spawn(0, 0, 0, MAX_Y);
	}
	
	private byte top = 1;
		
	@Override
	public void updatePhysics(double deltaTime) {
		int bottom1 = sprite.getHeight() - Math.abs(y) - 5; // 5 compensates for white border in image
		int bottom2 = sprite.getHeight() - Math.abs(sprite2y) - 5; // 5 compensates for white border in image
		
		if(bottom1 <= 0)
			top = 2;
		else if(bottom2 <= 0)
			top = 1;
		
		if(top == 1) {
			y += dy * deltaTime;
			sprite2y = bottom1;
		}
		else {
			sprite2y += dy * deltaTime;
			y = bottom2;
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
