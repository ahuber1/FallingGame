package com.example.fallinggametest;

import java.util.concurrent.TimeUnit;

public class GameLoop extends Thread {

	/**
	 * references to Game 
	 */
	private Game game;
	
	private volatile boolean running = true;
	
	
	
	public GameLoop(Game game, GameWorld gameWorld){
		
		this.game = game;
	}
	
	
	
	public void run() {
		while (running) {
			try {
				
				// 1000 / 33 = 30fps
				TimeUnit.MILLISECONDS.sleep(33);
				
				// do everything that needs to be done in the game
				game.updatePhysics(.033f);
				game.doCollisionTesting();
				game.checkForStopCondition();
				game.spawnHandling();
				game.removeDeadGameObjects();
				game.incrementScore(1);
				game.incrementTime(33);
				
				// force a redraw on the screen
				game.redrawCanvas();

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
