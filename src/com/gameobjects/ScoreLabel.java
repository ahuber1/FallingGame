package com.gameobjects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public class ScoreLabel extends GameObject{
	
	public int score;
	public int gameHeight;

	public ScoreLabel(float x, float y, int gameHeight){
		
		this.x = x;
		this.y = y;
		this.score = 0;
		this.gameHeight = gameHeight;
	}
	
	public void setScore(int score){
		
		this.score = score;
	}
	
	public void draw(Canvas canvas){
		
		String text = String.format("Score: %d", score);
		
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStyle(Style.FILL);
		paint.setTextSize(gameHeight / 30);
		
		canvas.drawText(text, x, y, paint);
		
	}
}
