package com.gameobjects;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.Surface;
import android.view.WindowManager;

import com.collision.PhysVector;
import com.example.fallinggametest.Game;
import com.example.fallinggametest.R;

public class Trooper extends GameObject{
	
	private int orientation;
	
	private Game game;
	
	public Trooper(float x, float y, Game game) {
		
		this.x = x;
		this.y = y;
		this.game = game;
		this.maxx = Game.screenWidth / 3f;
		
		dx = dy = dx2 = dy2 = 0;
		
		this.alive = true;
		
		this.sprite = BitmapFactory.decodeResource(game.getResources(), R.drawable.trooper);
		createHitboxForSprite();
		
		SensorManager manager = (SensorManager) game.getSystemService(Context.SENSOR_SERVICE);
		manager.registerListener(listener, manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
		
		WindowManager wm = (WindowManager) game.getSystemService(Context.WINDOW_SERVICE);
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
	
	@Override
	public void updatePhysics(float deltaTime){
		
		super.updatePhysics(deltaTime);
		
//		Log.i("Trooper's updatePhysics()", String.format("%3s = %3.2f", "x", x));
//		Log.i("Trooper's updatePhysics()", String.format("%3s = %3.2f", "y", y));
//		Log.i("Trooper's updatePhysics()", String.format("%3s = %3.2f", "dx", dx));
//		Log.i("Trooper's updatePhysics()", String.format("%3s = %3.2f", "dy", dy));
//		Log.i("Trooper's updatePhysics()", String.format("%3s = %3.2f", "dx2", dx2));
//		Log.i("Trooper's updatePhysics()", String.format("%3s = %3.2f", "dy2", dy2));
//		Log.i("Trooper's updatePhysics()", "--------------------");
		
		
	}
	 
	public boolean isAlive(){
		
		return this.alive;
	}
	
private SensorEventListener listener = new SensorEventListener() {

		@Override
		public void onSensorChanged(SensorEvent event) {
			if(game.useAccelerometer == false)
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
			
			dx = (float) (percentage * maxx * direction);
			
			if(x == 0.0)
				dx = 0;			
		}
		
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// Do nothing...
		}
	};
	
}
