package com.example.fallinggametest;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.transition.Visibility;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.View.OnClickListener;

import com.collision.PhysVector;
import com.gameobjects.BouncyBall;
import com.gameobjects.GameObject;
import com.gameobjects.Trooper;


public class GameWorld extends SurfaceView {
	
	SurfaceHolder holder;
	
	ArrayList<GameObject> gameObjects;
	
	public GameWorld(Context context){
		
		super(context);
		
		holder = this.getHolder();
		
		gameObjects = new ArrayList<GameObject>();
		
		this.setBackgroundColor(Color.WHITE);
		this.setFocusableInTouchMode(true);
		
	}
	
	public void updatePhysics(float deltaTime){
		
		// this is where you obtain the height and width of the View
		// I couldn't find a better place to put this code because
		// you have to wait until the SurfaceView actually is rendered
		// for it to have height and width attributes. Putting it in a 
		// constructor didn't work.
		/*Game.screenHeight = getHeight();
		Game.screenWidth = getWidth();*/
		
		for(int i = 0; i < gameObjects.size(); i++){
			
			gameObjects.get(i).updatePhysics(deltaTime);
		}
		
	}
	
	
	public boolean stopConditionPresent(){
		
		Trooper trooper = (Trooper)gameObjects.get(1);
		
		return (trooper.isAlive() == false);
	}

	public void onDraw(Canvas canvas){
		
		for(int i = 0; i < gameObjects.size(); i++){
			
			gameObjects.get(i).draw(canvas);
		}
		
	}
	
	
	
}
