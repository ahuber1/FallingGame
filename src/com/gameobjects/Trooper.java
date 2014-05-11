package com.gameobjects;

import java.util.ArrayList;

import com.collision.PhysVector;
import com.example.fallinggametest.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Trooper extends GameObject{
	
	private PhysVector destination;
	
	private static final float TROOPER_SPEED = 200;
	
	public Trooper(float x, float y, Context context){
		
		this.x = x;
		this.y = y;
		
		dx = dy = dx2 = dy2 = 0;
		
		this.alive = true;
		
		this.sprite = BitmapFactory.decodeResource(context.getResources(), R.drawable.trooper);
		createHitboxForSprite();
	}
	
	public void checkForCollisions(ArrayList<GameObject> gameObjects){
		
		for(int i = 0; i < gameObjects.size(); i++){
			
			GameObject obj = gameObjects.get(i);
			
			if(isColliding(obj)){
				
				if(obj instanceof Bird 
						|| obj instanceof UFO 
						|| obj instanceof Balloon
						|| obj instanceof NormalMissile
						|| obj instanceof HomingMissile ){
					
					
					this.alive = false;
				}
			}
		}
	}
	
	public void setDestination(PhysVector dest){
		
		this.destination = dest;
	}
	
	@Override
	public void updatePhysics(float deltaTime){
		
		super.updatePhysics(deltaTime);
		
		if(destination != null){
			
			PhysVector pos = new PhysVector(x, y);
			float distance = PhysVector.distance(pos, destination);
			
			// test if trooper should stop, give it a variance of 3 units
			if(distance <20){
				
				dx = dy = dx2 = dy2 = 0;
			} else {
				
				
				float speed = TROOPER_SPEED;
				PhysVector newVelocity = PhysVector.subtract(destination, pos);
				newVelocity.becomeUnitVector();
				
				newVelocity.scale(speed);
				
				dx = newVelocity.x;
				dy = newVelocity.y;
			}
		}
	}
	 
	public boolean isAlive(){
		
		return this.alive;
	}
	
}
