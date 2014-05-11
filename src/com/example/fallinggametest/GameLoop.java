package com.example.fallinggametest;

import java.util.concurrent.TimeUnit;

public class GameLoop extends Thread {

	private GameWorld gameWorld;
	private Game game;
	
	private volatile boolean running = true;
	
	public GameLoop(Game game, GameWorld gameWorld){
		
		this.gameWorld = gameWorld;
		this.game = game;
	}
	
	public void run() {
		while (running) {
			try {
				
				TimeUnit.MILLISECONDS.sleep(33);
				
				gameWorld.updatePhysics(.033f);
				game.doCollisionTesting();
				game.spawnHandling();
				game.removeDeadGameObjects();
				game.incrementScore(1);
				game.incrementTime(33);
				gameWorld.postInvalidate();

			} catch (InterruptedException ie) {
				running = false;
			}
		}
	}
	
	public void safeStop() {
		running = false;
		interrupt();
	}
	
	public boolean isRunning(){
		
		return running;
	}
	
}
