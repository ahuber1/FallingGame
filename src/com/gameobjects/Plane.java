package com.gameobjects;

import com.example.fallinggametest.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Defines the airplane game object for Falling.
 */
public class Plane extends GameObject{
	
	Bitmap spriteL, spriteR;

	package com.gameobjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.example.fallinggametest.R;

/**
 * Defines the UFO game object for Falling.
 */
public class UFO extends GameObject {

    /**
     * Constructs a new airplane game object.
     * @param x the starting x coordinate
     * @param y the starting y coordinate
     * @param dx the velocity on the x axis
     * @param dy the velocity on the y axis
     * @param context the application environment of this object
     */
    public UFO(float x, float y, float dx, float dy, Context context)
    {

    	this.alive = true;
    	
        this.x = x;
        this.y = y;

        this.dx = dx;
        this.dy = dy;

        this.sprite = BitmapFactory.decodeResource(context.getResources(), R.drawable.ufo);
   
        createHitboxForSprite();
    }

}
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
