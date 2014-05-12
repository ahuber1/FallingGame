package com.gameobjects;

import java.util.ArrayList;

import com.collision.PhysVector;
import com.example.fallinggametest.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Surface;
import android.view.WindowManager;

public class Trooper extends GameObject{
	
	private PhysVector destination;
	
	public static final float MAX_SPEED = 200;
	
	private int orientation;
	private boolean useAccelerometer;
	
	public Trooper(float x, float y, Context context, boolean useAccelerometer) {
		
		this.x = x;
		this.y = y;
		
		dx = dy = dx2 = dy2 = 0;
		
		this.alive = true;
		
		this.sprite = BitmapFactory.decodeResource(context.getResources(), R.drawable.trooper);
		createHitboxForSprite();
		
		SensorManager manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		manager.registerListener(listener, manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
		
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		orientation = wm.getDefaultDisplay().getRotation();
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
				
				
				float speed = MAX_SPEED;
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
	
private SensorEventListener listener = new SensorEventListener() {

		@Override
		public void onSensorChanged(SensorEvent event) {
			if(!useAccelerometer)
				return;
			
			double x = Math.abs(event.values[0]);
			double y = Math.abs(event.values[1]);
			double xraw = event.values[0];
			
			if(orientation == Surface.ROTATION_90 || orientation == Surface.ROTATION_270) {
				double temp = x;
				x = y;
				y = temp;
				
				xraw = event.values[1];
			}
			
			double ang = (90 * x) / 9.8;			
			double percentage = ang / 90.0;
	
			xraw *= -1.0;
			
			double direction = xraw / x;
			
			dx = (float) (percentage * MAX_SPEED * direction);
		}
		
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// Do nothing...
		}
	};
	
}
