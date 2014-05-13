package com.gameobjects;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Surface;
import android.view.WindowManager;

import com.example.fallinggametest.Game;
import com.example.fallinggametest.R;

public class Trooper extends GameObject {
	
	/** 
	 * The default orientation of the screen. Will be any of the values
	 * in the "See Below" section.
	 * @see Surface#ROTATION_0
	 * @see Surface#ROTATION_90
	 * @see Surface#ROTATION_180
	 * @see Surface#ROTATION_270
	 */
	private int orientation;
	
	/** The game that this Trooper will be in */
	private Game game;
	
	/** 
	 * Creates a new Trooper 
	 * @param x the x coordinate this Trooper will be at
	 * @param y the y coordinate this Trooper will be at
	 * @param game the game that this Trooper will be in
	 */
	public Trooper(int x, int y, Game game) {
		super(game.screenWidth / 2, 0, BitmapFactory.decodeResource(
				game.getResources(), R.drawable.trooper));
		
		this.game = game;
		
		spawn(x, y, 0, 0);
		
		SensorManager manager = 
				(SensorManager) game.getSystemService(Context.SENSOR_SERVICE);
		
		manager.registerListener(sensorListener, 
				manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), 
				SensorManager.SENSOR_DELAY_GAME);
		
		WindowManager wm = 
				(WindowManager) game.getSystemService(Context.WINDOW_SERVICE);
		
		orientation = wm.getDefaultDisplay().getRotation();
	}
	
	/**
	 * This method will check to see if the trooper collided with any 
	 * other game object. If it did, then these objects are gracefully 
	 * destroyed and should trigger the end of the game (since if there 
	 * was a collision, {@link GameObject#alive alive} will be {@code false})
	 */
	@Override
	public void checkForCollisions(ArrayList<GameObject> gameObjects) {
		super.checkForCollisions(gameObjects);
		
		for(GameObject obj : gameObjects) {
			if(isColliding(obj)) {
				if(obj instanceof SkyBackground)
					alive = true;
				else
					alive = false;
			}
			
			if(alive == false)
				break;
		}
	}
	
	/**
	 * Allows the trooper to be manipulated via the accelerometer
	 */
	public SensorEventListener sensorListener = new SensorEventListener() {
		
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
			double percentage = ang / 90;
			
			xraw *= -1f;
			
			double direction = xraw / x;
			
			dx = percentage * MAX_X * direction;
			
			if(x == 0)
				dx = 0; // corrects divide by 0 error when initializing "direction"
		}
		
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// Do nothing...
		}
	};

}