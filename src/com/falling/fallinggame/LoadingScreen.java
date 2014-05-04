package com.falling.fallinggame;

import com.falling.framework.Game;
import com.falling.framework.Graphics;
import com.falling.framework.Screen;

/**
 * Handles operations during loading of game assets.
 * @author evan hanger, andrew huber, mark judy
 * @version 1.0 Date: 5/2/14 Time: 7:21 PM
 */
public class LoadingScreen extends Screen
{
    public LoadingScreen(Game game)
    {
        super(game);
    }

    /**
     * Loads game assets.
     * @param dTime change in time
     */
    @Override
    public void update(float dTime)
    {
        Graphics g = game.getGraphics();
        Assets.menu = g.newImage("menu.png", Graphics.ImageFormat.RGB565);
        Assets.background = g.newImage("background.png", Graphics.ImageFormat.RGB565);
        Assets.trooper = g.newImage("trooper.png", Graphics.ImageFormat.ARGB4444);
        Assets.birdL = g.newImage("birdL.png", Graphics.ImageFormat.ARGB4444);
        Assets.birdR = g.newImage("birdR.png", Graphics.ImageFormat.ARGB4444);
//        Assets.missile = g.newImage("missile", Graphics.ImageFormat.ARGB4444);
        Assets.balloon = g.newImage("balloon.png", Graphics.ImageFormat.ARGB4444);

        game.setScreen(new MainMenuScreen(game));
    }

    /**
     * Draws the splash screen during loading.
     * @param dTime change in time
     */
    @Override
    public void paint(float dTime)
    {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.splash, 0, 0);
    }

    //The following are only necessary for extending the parent class
    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void dispose()
    {

    }

    @Override
    public void backButton()
    {

    }
}
