package com.example.fallinggametest;

import java.util.ArrayList;
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

/**
 * 
 * This is the class which handles all the main functions of the game. 
 * 
 * @author Evan Hanger, Andrew Huber, Mark Judy
 *
 */
public class Game extends Activity implements OnTouchListener{

	/** The view in which everything is visible */
	private GameWorld gameWorld;
	
	
	/** The class which contains the necessary looping for calling 
	 * the Game's methods. 
	 */
	private GameLoop gameLoop;
	
	
	/**
	 * Determine whether to control Trooper using touch screen or accelerometer
	 */
	private boolean useAccelerometer, useTouchscreen;
	
	public static int screenWidth, screenHeight;
	
	private ArrayList<GameObject> gameObjects;
	
	private Trooper trooper;
	
	private ScoreLabel scoreLabel;
	
	private SkyBackground skyBackground;
	
	private int currentScore, timeInMillis, timeSinceLastSpawn;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		
		// this will get the screen dimensions 
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		screenHeight = displaymetrics.heightPixels;
		screenWidth = displaymetrics.widthPixels;
		
		// both default to false but will be changed in the code below
		useAccelerometer = useTouchscreen = false;
		
		// determine what controls to use for Trooper, 
		// this info is passed from MainMenu
		Bundle userControlInfo = getIntent().getExtras();
		if(userControlInfo != null){
			useAccelerometer = userControlInfo.getBoolean("useAccel");
			useTouchscreen = userControlInfo.getBoolean("useTouch");
		}
		
		// start these variables at 0
		this.currentScore = 0;
		this.timeInMillis = 0;
		this.timeSinceLastSpawn = 0;
		
		// create ArrayList of GameObjects
		this.gameObjects = new ArrayList<GameObject>();
		
		// create the GameWorld and make it the main view
		gameWorld = new GameWorld(this, gameObjects);
		setContentView(gameWorld);
		
		/* Add the initial game objects which are always going to be present:
		 * -Trooper
		 * -SkyBackground
		 * -ScoreLabel
		 */
		addInitialGameObjects();
		
		// give gameWorld a touch listener
		gameWorld.setOnTouchListener(this);
		
		// create and start the GameLoop Runnable class
		startGameLoop();
		 
	}
	
	
	/**
	 * Handles TouchEvents that occur on screen
	 */
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		float xpos = event.getX();
		
		trooper.setDestination(new PhysVector(xpos, trooper.getYPos()));
		
		return true;
	}
	
	
	/**
	 * Called when the game goes into background
	 */
	@Override
	public void onPause(){
		super.onPause();
		
		// stop the gameLoop, end the game activity
		gameLoop.safeStop();
		finish();
	}
	
	public void redrawCanvas(){
		
		this.gameWorld.postInvalidate();
	}
	
	public int getCurrentScore(){
		
		return this.currentScore;
	}
	
	public int getTimeInMillis(){
		
		return timeInMillis;
	}
	
	/**
	 * Increases the score, and changes the ScoreLabel to display this new score
	 * @param amount
	 */
	public void incrementScore(int amount){
		
		currentScore += amount;
		
		if(scoreLabel != null){
			
			scoreLabel.setScore(currentScore);
		}
		
	}
	
	/**
	 * Increments both variables keeping track of a time
	 * @param amount
	 */
	public void incrementTime(int amount){
		
		timeInMillis += amount;
		timeSinceLastSpawn += amount;
		
	}
	
	/**
	 * Instantiates the GameLoop object and starts its thread
	 */
	public void startGameLoop(){
		
		this.gameLoop = new GameLoop(this, this.gameWorld);
		this.gameLoop.start();
		
	}
	
	/**
	 * Adds a GameObject to the ArrayList containing GameObjects
	 * @param object to be added
	 */
	public void addGameObject(GameObject obj){
		
		this.gameObjects.add(obj);
	}
	
	/**
	 * This is where you add the GameObjects that are initially present in the game
	 * 
	 * DON'T spawn enemies here, that should happen in spawnHandling()
	 */
	public void addInitialGameObjects(){
		
		SkyBackground background = new SkyBackground( BitmapFactory.decodeResource(getResources(), R.drawable.background));
		this.skyBackground = background; // store a reference to skyBackground
		addGameObject(background);
		
		Trooper trooper = new Trooper(350, 300, this, useAccelerometer);
		this.trooper = trooper; // store a reference to trooper
		addGameObject(trooper);
		
		ScoreLabel label = new ScoreLabel(15,25);
		this.scoreLabel = label; // store a reference to scoreLabel
		addGameObject(label);
		
	
	}

	
	
	/**
	 * Tests collision on every single GameObject present in the game
	 */
	public void doCollisionTesting(){
		
		for(int i = 0; i < gameObjects.size(); i++){
			
			gameObjects.get(i).checkForCollisions(gameObjects);
		}
	}
	
	/**
	 * Updates the physics of the game by calling every 
	 * single GameObject's updatePhysics() method
	 * @param deltaTime
	 */
	public void updatePhysics(float deltaTime){
		
		for(int i = 0; i < gameObjects.size(); i++){
			
			gameObjects.get(i).updatePhysics(deltaTime);
		}
		
	}

	/** 
	 * TODO
	 * (NOT YET COMPLETE)
	 * This is where enemies in the game are spawned
	 *
	 */
	public void spawnHandling(){
		
		Random rand = new Random();
		
		// the amount of time to wait before spawning a new enemy
		int waitTimeMillis = 1500 + rand.nextInt(1000);
		
		if(timeSinceLastSpawn >= waitTimeMillis){
			
			// ----- SPAWN BIRD -----
			
			//randomly choose whether to spawn on left or on right
			//depending on time since last spawn
			boolean leftSpawn = (timeSinceLastSpawn % 2 == 0);
			
			int yPos = 400 + rand.nextInt(screenHeight);
			
			if(leftSpawn){
				Bird b = new Bird(0, yPos, 300, -300, this);
				addGameObject(b);
			} else {
				Bird b = new Bird(screenWidth, yPos, -300, -300, this);
				addGameObject(b);
			}
			
			// ----- SPAWN HOMING MISSILE -----
			
			int xPos = rand.nextInt(screenWidth);
			HomingMissile hm = new HomingMissile(xPos, screenHeight, 250, trooper, this);
			addGameObject(hm);
			
			// after an item is spawned, this should be reset to 0
			timeSinceLastSpawn = 0;
		}
	}
	
	
	/**
	 * Removes GameObjects from that game that have gone off the screen
	 */
	public void removeDeadGameObjects(){
		
		// start iterating at i=3, to avoid trooper, skyBackground, scoreLabel
		for(int i = 3; i < gameObjects.size(); i++){
			
			GameObject temp = gameObjects.get(i);
			
			// if an object goes off the screen, remove it from ArrayList
			if(temp.getXPos() < 0 
					|| temp.getXPos() > screenWidth
					|| temp.getYPos() < 0 
					|| temp.getYPos() > screenHeight){
				
				gameObjects.remove(temp);
			}
		}
	}
	
	public void checkForStopCondition(){
		
		if(trooper.isAlive() == false){
			
			// end the game, stop the gameLoop, start the GameOver Activity
		}
	}
		
	
		
	
}
