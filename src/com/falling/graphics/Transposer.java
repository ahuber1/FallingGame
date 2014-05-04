package com.falling.graphics;

import android.content.Context;
import android.graphics.Canvas;

import com.falling.object.GameObject;

/**
 * Transposes game coordinates to screen coordinates and draws game objects appropriately
 * @author andrew_huber, evan_hanger
 *
 */
public class Transposer {
	
	/** The context of this app */
	private Context context;
	
	/** The canvas in which we will draw our game objects */
	private Canvas canvas;
	
	/** The camera for this game */
	private Camera camera;
	
	/** The game object whose coordinates we are transposing and who we will subsequently draw */
	private GameObject object;
	
	/** The height the main charcter is off the ground */
	private int gameHeight;
	
	public Transposer(Context context, Canvas canvas, Camera camera, int gameHeight, GameObject object) {
		this.context = context;
		this.canvas = canvas;
		this.camera = camera;
		this.object = object;
		this.gameHeight = gameHeight;
	}
	
	public void transpose() {
		
	}
}
