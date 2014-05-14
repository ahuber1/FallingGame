package com.gameobjects;

import com.example.fallinggametest.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Plane extends GameObject{
	
	Bitmap spriteL, spriteR;

	public Plane(float x, float y, float dx, float dy, Context context){
		
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		
		this.spriteL = BitmapFactory.decodeResource(context.getResources(), R.drawable.planel);
		this.spriteR = BitmapFactory.decodeResource(context.getResources(), R.drawable.planer);
		
		if(dx > 0){
			sprite = spriteL;
		} else {
			sprite = spriteR;
		}
		
		createHitboxForSprite();
	}
}
