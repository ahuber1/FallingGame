package com.example.fallinggametest;

import java.util.ArrayList;
import java.util.HashMap;

import com.collision.PhysVector;
import com.gameobjects.*;
import java.util.Random;

public class SpawnHandler {

	private Game game;
	
	private float speedMultiplier;
	
	public SpawnHandler(Game game){
		
		this.game = game;
		
		// defaults to 1
		this.speedMultiplier = 1;
		
	}
	
	public void setSpeedMultiplier(float mul){
		
		this.speedMultiplier = mul;
	}
	
	/**
	 * Spawns a game object using a different probability for each object.
	 * NormalMissile: 35% chance
	 * Birds: 30% chance
	 * Plane: 10% chance
	 * UFO: 10% chance
	 * HomingMissile: 10% chance
	 * Balloon: 5% chance
	 * 
	 * @param timeSinceLastSpawn
	 * @return If a GameObject is spawned, return 0. Otherwise, return the time
	 * since last spawned. 
	 */
	public int spawnGameObject(int timeSinceLastSpawn){
		
		Random rand = new Random();
		
		// get a random wait time
		int waitTimeMillis = 500 + rand.nextInt(1000);
		
		// do the spawning
		if(timeSinceLastSpawn >= waitTimeMillis){
		
			// generate a random integer between 0 and 9999
			int randomSpawnKey = rand.nextInt(10000);
			
			if(randomSpawnKey < 3500){
				
				spawnNormalMissile(rand);
			} else if(randomSpawnKey < 6500){
				
				spawnBird(rand);
			} else if(randomSpawnKey < 7500){
			
				spawnPlane(rand);
			} else if(randomSpawnKey < 8500){
				
				spawnUFO(rand);
			} else if(randomSpawnKey < 9500){
				
				spawnHomingMissile(rand);
			} else {
	
				spawnBalloon(rand);
			}
			
			return 0;
			
		} else {
			
			return timeSinceLastSpawn;
		}
	}
	
	
	/**
	 * Spawns a Bird in the Game
	 * @param rand
	 */
	private void spawnBird(Random rand){
		
		// get a random y position
		int ypos = (game.screenHeight / 2) + rand.nextInt(game.screenHeight / 2);
		
		// use random number to determine which side of screen to spawn
		boolean leftSide = (ypos % 2 == 0);
		
		if(leftSide){
			
			Bird bird = new Bird(0,ypos, speedMultiplier * 200, speedMultiplier * 50, game );
			game.addGameObject(bird);
		} else {
			
			Bird bird = new Bird(0,ypos, speedMultiplier * -200, speedMultiplier * 50, game );
			game.addGameObject(bird);
		}
	}
	
	/**
	 * Spawns a Plane in the Game
	 * @param rand
	 */
	private void spawnPlane(Random rand){

		// get a random y position
		int ypos = (game.screenHeight / 2) + rand.nextInt(game.screenHeight / 2);
				
		// use random number to determine which side of screen to spawn
		boolean leftSide = (ypos % 2 == 0);
				
		if(leftSide){
					
			Plane plane = new Plane(0,ypos, speedMultiplier * 500, speedMultiplier * 300, game );
			game.addGameObject(plane);
		} else {
					
			Plane plane = new Plane(0,ypos, speedMultiplier * -500, speedMultiplier * 300, game );
			game.addGameObject(plane);
		}
	

	}
	
	private void spawnUFO(Random rand){
		
		// get a random y position
		int ypos = (game.screenHeight / 2) + rand.nextInt(game.screenHeight / 2);
						
		// use random number to determine which side of screen to spawn
		boolean leftSide = (ypos % 2 == 0);
						
		if(leftSide){
							
			UFO ufo = new UFO(0,ypos, speedMultiplier * 400, speedMultiplier * 400, game );
			game.addGameObject(ufo);
		} else {
							
			UFO ufo = new UFO(0,ypos, speedMultiplier * -400, speedMultiplier * 400, game );
			game.addGameObject(ufo);
		}
	}
	
	/**
	 * Spawn a balloon at a random position on the bottom of the screen. 
	 * @param rand
	 */
	private void spawnBalloon(Random rand){
		
		int xpos = rand.nextInt(game.screenWidth);
		
		Balloon balloon = new Balloon(xpos, game.screenHeight, speedMultiplier * 0, speedMultiplier * 500, game);
		game.addGameObject(balloon);
	}
	
	/**
	 * Spawn a Normal Missile at a random position on the bottom of the screen
	 * @param rand
	 */
	private void spawnNormalMissile(Random rand){
		
		int xpos = rand.nextInt(game.screenWidth);
		
		int targetX = rand.nextInt(game.screenWidth);
		
		int randomSpeed = 400 + rand.nextInt(400);
		
		PhysVector targetLocation = new PhysVector(targetX, 0);
		
		
		NormalMissile normMissile = new NormalMissile(xpos, game.screenHeight, speedMultiplier * randomSpeed, targetLocation, game);
		game.addGameObject(normMissile);
	}
	
	private void spawnHomingMissile(Random rand){
		
		int xpos = rand.nextInt(game.screenWidth);
		
		int randomSpeed = 200 + rand.nextInt(100);
		
		HomingMissile homingMissile = new HomingMissile(xpos, game.screenHeight, speedMultiplier * randomSpeed,game.screenWidth , game );
		
		game.lockMissileOnTrooper(homingMissile);
		
		game.addGameObject(homingMissile);
		
	}
	
}
