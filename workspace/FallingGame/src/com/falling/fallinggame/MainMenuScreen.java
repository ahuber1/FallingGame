package com.falling.fallinggame;

import java.util.List;

import com.falling.framework.Game;
import com.falling.framework.Graphics;
import com.falling.framework.Input.TouchEvent;
import com.falling.framework.Screen;
import com.falling.object.Trooper;

/**
 * Main menu screen.
 * @author evan hanger, andrew huber, mark judy
 * @version 1.0 Date: 5/2/14 Time: 7:33 PM
 */
public class MainMenuScreen extends Screen
{
    private Trooper trooper;
	
	public MainMenuScreen(Game game)
    {
        super(game);
    }

    /**
     * Continually checks for input on the main menu.
     * @param dTime change in time
     */
    @Override
    public void update (float dTime)
    {
        Graphics g = game.getGraphics();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        for(TouchEvent e : touchEvents)
        {
            if(e.type == TouchEvent.TOUCH_UP)
            {
                if(inBounds(e, 0, 500, 800, 1000))
                {
                    game.setScreen(new GameScreen(game, trooper));
                }
            }
        }
    }

    /**
     * Checks if a touch event is within given bounds.
     * @param event the touch event
     * @param x upper left x bound
     * @param y upper left y bound
     * @param x2 lower right x bound
     * @param y2 lower right y bound
     * @return true if input is within bounds, otherwise false
     */
    private boolean inBounds(TouchEvent event, int x, int y, int x2, int y2)
    {
        return (event.x > x) && (event.x < (x + x2 - 1)) && (event.y > y) && (event.y < (y + y2 - 1));
    }

    /**
     * Draws the menu image
     * @param dTime change in time
     */
    @Override
    public void paint (float dTime)
    {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.menu, 0, 0);
    }


    @Override
    public void pause()
    {
        //TODO handle pause operation on main menu?
    }

    @Override
    public void resume()
    {
        //TODO handle resume after pause
    }

    @Override
    public void dispose()
    {
        //TODO Destroy assets for garbage collection on exit
    }

    @Override
    public void backButton()
    {
        //TODO Display Exit Game? Box
    }
}