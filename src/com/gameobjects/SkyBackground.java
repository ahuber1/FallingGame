package com.gameobjects;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Config;

public class SkyBackground extends GameObject{
	
	private Bitmap sprite2;
	private float sprite2x, sprite2y;
	private int screenWidth;
	private int screenHeight;

	public SkyBackground(Bitmap sprite, int screenWidth, int screenHeight) {
		
		x = y = 0;
		sprite2x = 0;
		sprite2y = sprite.getHeight();
		
		dx = 0;
		dy = screenHeight / -100f;
		
		this.sprite = sprite;
		this.sprite2 = sprite;
		
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
	}
	
	public void updatePhysics(float deltaTime){
		
		x += dx * deltaTime;
		y += dy * deltaTime;
		
		sprite2x += dx * deltaTime;
		sprite2y += dy * deltaTime;
		
		if(y <= -sprite.getHeight() + 20){
			y = sprite.getHeight();
		}
		
		if(sprite2y <= -sprite.getHeight() + 20){
			sprite2y = sprite.getHeight();
		}
		
		
	}
	
	
	public void draw(Canvas canvas){
		float sx = (screenWidth * 1.0f) / this.sprite.getWidth();
		float sy = (screenHeight * 1.0f) / this.sprite.getHeight();
		canvas.scale(sx, sy);
		canvas.drawBitmap(sprite, x,y, null);
		canvas.drawBitmap(sprite2, sprite2x, sprite2y, null);
	}
	
	

}
