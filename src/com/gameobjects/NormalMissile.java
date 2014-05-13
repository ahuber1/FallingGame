package com.gameobjects;

import com.collision.PhysVector;
import com.example.fallinggametest.R;

import android.content.Context;
import android.graphics.BitmapFactory;

public class NormalMissile extends Missile{

	public NormalMissile(float x, float y, float speed, PhysVector waypoint, Context context){
		
		this.alive = true;
		
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.waypoint = waypoint;
		
		this.sprite = BitmapFactory.decodeResource(context.getResources(), R.drawable.missile_sprite);
		
		// add the hitbox to this gameobject
		createHitboxForSprite();
	}
	
	@Override
	public void updatePhysics(float deltaTime){
		
		// calculate the velocity vector
		PhysVector currentPos = new PhysVector(x, y);
		PhysVector velocity = PhysVector.subtract(waypoint, currentPos);
		velocity.becomeUnitVector();
		velocity.scale(speed);
				
		dx = velocity.x;
		dy = velocity.y;
				
		x += dx * deltaTime;
		y += dy * deltaTime;
		
		hitbox.setPosition((int) this.getCenterX(), (int) this.getCenterY());
	}
	
	
}
