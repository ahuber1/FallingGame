package com.falling.object;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import com.falling.FallingGame;

import java.util.Random;

/**
 * @author evan hanger, andrew huber, mark judy
 * @version 1.0 Date: 5/2/14 Time: 10:43 PM
 */
public abstract class GameObject
{
    protected int centerX, centerY, speedX, speedY, windowWidth, windowHeight;
    protected boolean alive;
    protected Context context;

    public GameObject()
    {
        setWindowDimensions();
    }

    public abstract void update();

    /**
     * Gets window dimensions from context and sets their values for use by game objects.
     */
    protected void setWindowDimensions()
    {
        context = FallingGame.getContext();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        windowWidth = size.x;
        windowHeight = size.y;
    }

    /**
     * Spawns game object at random height (hopefully below the parachuter)
     * @return height location of game object
     */
    protected int randomSpawnY()
    {
        Random rand = new Random();
        int newY;

        newY = rand.nextInt(windowHeight - 300) + 300;

        return newY;
    }

    /**
     * Spawns game object at random x location
     * @return x coordinate of game object
     */
    protected int randomSpawnX()
    {
        Random rand = new Random();
        int newX;
        
        newX = rand.nextInt(windowWidth - 200) + 100;
        
        return newX;
    }

    /**
     * Spawns game object at random side
     * @return 0 for left side, window width for right side
     */
    protected int randomSpawnSide()
    {
        Random rand = new Random();
        boolean side;
        
        side = rand.nextBoolean();
        
        if(side)
            return 0;
        else
            return windowWidth;
    }


    protected void destroy()
    {

    }

    public int getCenterX()
    {
        return centerX;
    }

    public int getCenterY()
    {
        return centerY;
    }

    public int getSpeedX()
    {
        return speedX;
    }

    public int getSpeedY()
    {
        return speedY;
    }

    public void setCenterX(int centerX)
    {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY)
    {
        this.centerY = centerY;
    }

    public void setSpeedX(int speedX)
    {
        this.speedX = speedX;
    }

    public void setSpeedY(int speedY)
    {
        this.speedY = speedY;
    }
}
