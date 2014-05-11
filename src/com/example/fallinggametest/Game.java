package com.example.fallinggametest;

import java.util.Random;

import com.collision.PhysVector;
import com.gameobjects.*;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class Game extends Activity implements OnTouchListener{

	private GameWorld gameWorld;
	
	/**
	 * Determine whether to control Trooper using touch screen or accelerometer
	 */
	private boolean useAccelerometer, useTouchscreen;
	
	public static int screenWidth, screenHeight;
	
	private GameLoop gameLoop;
	
	private Trooper trooper;
	
	private int currentScore, timeInMillis, timeSinceLastSpawn;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		screenHeight = displaymetrics.heightPixels;
		screenWidth = displaymetrics.widthPixels;
		
		// defaults
		useAccelerometer = useTouchscreen = false;
		
		// determine what controls to use for Trooper
		Bundle userControlInfo = getIntent().getExtras();
		if(userControlInfo != null){
			useAccelerometer = userControlInfo.getBoolean("useAccel");
			useTouchscreen = userControlInfo.getBoolean("useTouch");
		}
		
		this.currentScore = 0;
		this.timeInMillis = 0;
		this.timeSinceLastSpawn = 0;
		
		gameWorld = new GameWorld(this);
		setContentView(gameWorld);
		
		addInitialGameObjects();
		
		gameWorld.setOnTouchListener(this);
		
		startGameLoop();
		 
	}
	
	@Override
	public void onPause(){
		super.onPause();
		
		finish();
	}
	
	public int getCurrentScore(){
		
		return this.currentScore;
	}
	
	public int getTimeInMillis(){
		
		return timeInMillis;
	}
	
	public void incrementScore(int amount){
		
		currentScore += amount;
		
		if(gameWorld.gameObjects.size() >= 3){
			((ScoreLabel)gameWorld.gameObjects.get(2)).setScore(currentScore);
		}
		
	}
	
	public void incrementTime(int amount){
		
		timeInMillis += amount;
		timeSinceLastSpawn += amount;
		
	}
	
	public void startGameLoop(){
		
		this.gameLoop = new GameLoop(this, this.gameWorld);
		this.gameLoop.start();
		
	}
	
	public void addGameObject(GameObject obj){
		
		this.gameWorld.gameObjects.add(obj);
	}
	
	/**
	 * This is where you add the GameObjects
	 */
	public void addInitialGameObjects(){
		
		SkyBackground background = new SkyBackground( BitmapFactory.decodeResource(getResources(), R.drawable.background));
		addGameObject(background);
		
		Trooper trooper = new Trooper(350, 300, this);
		this.trooper = trooper; // store a reference to trooper
		addGameObject(trooper);
		
		ScoreLabel label = new ScoreLabel(15,25);
		addGameObject(label);
		
	
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		float xpos = event.getX();
		
		trooper.setDestination(new PhysVector(xpos, trooper.getYPos()));
		
		return true;
	}
	
	public void doCollisionTesting(){
		
		for(int i = 0; i < gameWorld.gameObjects.size(); i++){
			
			gameWorld.gameObjects.get(i).checkForCollisions(gameWorld.gameObjects);
		}
	}

	public void spawnHandling(){
		
		Random rand = new Random();
		
		int waitTimeMillis = 1500 + rand.nextInt(1000);
		
		if(timeSinceLastSpawn >= waitTimeMillis){
			
			// SPAWN BIRD
			
			boolean leftSpawn = (timeSinceLastSpawn % 2 == 0);
			
			int yPos = 400 + rand.nextInt(screenHeight);
			
			if(leftSpawn){
				Bird b = new Bird(0, yPos, 300, -300, this);
				addGameObject(b);
			} else {
				Bird b = new Bird(screenWidth, yPos, -300, -300, this);
				addGameObject(b);
			}
			
			// SPAWN HOMING MISSILE
			
			int xPos = rand.nextInt(screenWidth);
			HomingMissile hm = new HomingMissile(xPos, screenHeight, 250, trooper, this);
			addGameObject(hm);
			
			timeSinceLastSpawn = 0;
		}
	}
	
	public void removeDeadGameObjects(){
		
		for(int i = 3; i < gameWorld.gameObjects.size(); i++){
			
			GameObject temp = gameWorld.gameObjects.get(i);
			
			// if an object goes off the screen, remove it from ArrayList
			if(temp.getXPos() < 0 
					|| temp.getXPos() > screenWidth
					|| temp.getYPos() < 0 
					|| temp.getYPos() > screenHeight){
				
				gameWorld.gameObjects.remove(temp);
			}
		}
	}
	
}
