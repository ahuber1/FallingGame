package com.falling.object;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import com.falling.FallingGame;

/**
 * This manages information relating to the game's background image.
 * @author evan hanger, andrew huber, mark judy
 * @version 1.0 Date: 5/2/14 Time: 11:55 PM
 */
public class Background
{
    private int bgX, bgY, speedY, windowWidth, windowHeight;
    private Context context;

    public Background (int x, int y)
    {
        bgX = x;
        bgY = y;
        speedY = -10;
    }

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

    public void update()
    {
        bgY += speedY;

        //TODO Reset background
        if(bgY <= -1373)
            bgY = 1373;
    }

    public int getBgX()
    {
        return bgX;
    }

    public int getBgY()
    {
        return bgY;
    }

    public int getSpeedY()
    {
        return speedY;
    }

    public void setBgX(int bgX) {
        this.bgX = bgX;
    }

    public void setBgY(int bgY) {
        this.bgY = bgY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }
}
