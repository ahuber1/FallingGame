package com.gameobjects;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Config;

public class SkyBackground extends GameObject{
	
	Bitmap sprite2;
	float sprite2x, sprite2y;

	public SkyBackground(Bitmap sprite){
		
		x = y = 0;
		sprite2x = 0;
		sprite2y = sprite.getHeight();
		
		dx = 0;
		dy = -400;
		
		this.sprite = sprite;
		this.sprite2 = sprite;
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
		
		canvas.drawBitmap(sprite, x,y, null);
		canvas.drawBitmap(sprite2, sprite2x, sprite2y, null);
	}
	
	

}
