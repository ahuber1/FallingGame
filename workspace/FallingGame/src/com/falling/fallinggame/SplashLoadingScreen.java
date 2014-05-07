package com.falling.fallinggame;

import com.falling.framework.Game;
import com.falling.framework.Graphics;
import com.falling.framework.Screen;
import com.falling.object.Trooper;

/**
 * Splash screen during loading operation.
 * @author evan hanger, andrew huber, mark judy
 * @version 1.0 Date: 5/2/14 Time: 9:25 PM
 */
public class SplashLoadingScreen extends Screen
{	
	public SplashLoadingScreen(Game game) {
        super(game);
    }

    /**
     * Draws splash screen during loading operation.
     * @param deltaTime change in time
     */
    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.splash= g.newImage("falling.jpg", Graphics.ImageFormat.RGB565);

        game.setScreen(new LoadingScreen(game));

    }

    //The following are only necessary for extending the parent class
    @Override
    public void paint(float deltaTime) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        //May be needed if we want to quit during loading operation.
    }

    @Override
    public void backButton() {
        //May be needed if we want to quit during loading operation.
    }
}