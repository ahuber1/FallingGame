package com.example.fallinggametest;

import java.util.concurrent.TimeUnit;

import android.util.Log;

public class GameLoop implements Runnable {

	/**
	 * references to Game 
	 */
	private Game game;
	
	private boolean running;
	
	public GameLoop(Game game, GameWorld gameWorld){
		
		this.game = game;
	}
	
	
	@Override
	public void run() {
		running = true;
				
		
		while (running) {
//			try {				
				long start = System.currentTimeMillis();
				
				// do everything that needs to be done in the game
				// TODO
				//game.updatePhysics(GameObject.DELAY / 1000f);
				game.doCollisionTesting();
				game.checkForStopCondition();
				game.spawnHandling();
				game.removeDeadGameObjects();
				game.incrementScore(1);
				game.incrementTime(33);
				
				// force a redraw on the screen
				game.redrawCanvas();
				
				long end = System.currentTimeMillis();
				long diff = end - start;
				
				// TODO
//				if(diff - GameObject.DELAY > 0)
//					TimeUnit.MILLISECONDS.sleep(GameObject.DELAY - (end - start));

				
				// TODO
//			} catch (InterruptedException ie) {
//				running = false;
//				notifyAll();
//			}
		}
	}
	
	public void stop() {
		running = false;
	}
	
	public boolean isRunning(){
		
		return running;
	}
	
}
