package com.falling.fallinggame;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import com.falling.FallingGame;
import com.falling.framework.Game;
import com.falling.framework.Graphics;
import com.falling.framework.Input.TouchEvent;
import com.falling.framework.Screen;
import com.falling.object.*;

import java.util.List;

/**
 * This is the Big Kahuna holding most of the game logic and handling the drawing of assets.
 * @author evan hanger, andrew huber, mark judy
 * @version 1.0 Date: 5/2/14 Time: 7:55 PM
 */
public class GameScreen extends Screen
{
    //Enum describing various game states
    enum GameState
    {
        Ready, Running, Paused, GameOver
    }
    //Enum for choosing bird sprite
    enum BirdSide
    {
        Left, Right
    }

    GameState state = GameState.Ready;
    BirdSide birdSide;
    private Context context;
    private int windowWidth, windowHeight;

    private static Background bg1, bg2;
    private static Trooper trooper;
    private static Bird bird;
//    private static NormalMissile missile;
    private static Balloon balloon;

    boolean alive = true;
    Paint paint;

    /**
     * Instantiates assets for the game instance
     * @param game instance of the game
     */
    public GameScreen(Game game)
    {
        super(game);
        setWindowDimensions();

        //Two tiled backgrounds to repeat.
        bg1 = new Background(0,0);
        bg2 = new Background(0, 1373);
        trooper = new Trooper();
        bird = new Bird();
//        missile = new NormalMissile();
        balloon = new Balloon();

        paint = new Paint();
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
    }

    /**
     * Gets window dimensions from context for scaling.
     */
    private void setWindowDimensions()
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
     * Handles game updates based on current game state.
     * @param dTime change in time
     */
    @Override
    public void update (float dTime)
    {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        if(state == GameState.Ready)
            updateReady(touchEvents);
        if(state == GameState.Running)
            updateRunning(touchEvents, dTime);
        if(state == GameState.Paused)
            updatePaused(touchEvents, dTime);
        if(state == GameState.GameOver)
            updateGameOver(touchEvents);
    }

    /**
     * Handles updates for ready state, listens for touch events.
     * @param touchEvents list of touch events
     */
    private void updateReady(List<TouchEvent> touchEvents)
    {
        if (touchEvents.size() > 0)
            state = GameState.Running;
    }

    /**
     * Handles updates for running state. Listens for touch/accelerometer events. Updates game objects.
     * @param touchEvents list of touch events
     * @param dTime change in time
     */
    private void updateRunning(List<TouchEvent> touchEvents, float dTime)
    {
        //TODO Handle touch events/accelerometer readings

        if(!alive)
            state = GameState.GameOver;

        //TODO Handle GameObject updates
        trooper.update();
        bg1.update();
        bg2.update();
        bird.update();
//        missile.update();
        balloon.update();

        //Set side to choose bird sprite
        if(bird.getSpeedX() > 0)
            birdSide = BirdSide.Left;
        if(bird.getSpeedX() < 0)
            birdSide = BirdSide.Right;
    }

    /**
     * Handles updates for paused state. Listens for touch event to return to running state.
     * @param touchEvents list of touch events
     * @param dTime change in time
     */
    private void updatePaused(List<TouchEvent> touchEvents, float dTime)
    {
        //TODO Make pretty pause menu,
        for (TouchEvent e : touchEvents)
        {
            if (e.type == TouchEvent.TOUCH_UP)
            {
                if(e.x > 300 && e.x < 980 && e.y > 100 && e.y < 500)
                {
                    state = GameState.Running;
                    update(dTime);
                    return;
                }
            }
        }
    }

    /**
     * Handles game over updates. Returns to main menu on touch event.
     * @param touchEvents list of touch events
     */
    private void updateGameOver(List<TouchEvent> touchEvents)
    {
        for (TouchEvent e : touchEvents)
        {
            if (e.type == TouchEvent.TOUCH_UP)
            {
                if(e.x > 300 && e.x < 980 && e.y > 100 && e.y < 500)
                {
                    nullify();
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }
    }

    /**
     * Destroys game objects and sends them to garbage collector. Prevents memory leaks on new games.
     */
    private void nullify()
    {
        //Set ALL variables to null.
        paint = null;
        bg1 = null;
        bg2 = null;
        trooper = null;
        context = null;
        bird = null;
//        missile = null;
        balloon = null;

        System.gc();
    }

    /**
     * Draws game assets.
     * @param dTime change in time
     */
    @Override
    public void paint(float dTime)
    {
        Graphics g = game.getGraphics();

        //draw background first
        g.drawImage(Assets.background, bg1.getBgX(), bg1.getBgY());
        g.drawImage(Assets.background, bg2.getBgX(), bg2.getBgY());

        //draw game objects
        g.drawImage(Assets.trooper, trooper.getCenterX(), trooper.getCenterY());
        g.drawImage(Assets.balloon, balloon.getCenterX(), balloon.getCenterY());

        //draw sprites based on starting side
        if(birdSide == BirdSide.Left)
        {
            g.drawImage(Assets.birdL, bird.getCenterX(), bird.getCenterY());
        }
        if(birdSide == BirdSide.Right)
        {
            g.drawImage(Assets.birdR, bird.getCenterX(), bird.getCenterY());
        }
        //TODO Draw game elements


        //Draw UI on top.
        if (state == GameState.Ready)
            drawReadyUI();
        if (state == GameState.Running)
            drawRunningUI();
        if (state == GameState.Paused)
            drawPausedUI();
        if (state == GameState.GameOver)
            drawGameOverUI();
    }

    /**
     * Draws ready UI
     */
    private void drawReadyUI()
    {
        Graphics g = game.getGraphics();
    }

    /**
     * Draws running UI
     */
    private void drawRunningUI() {
        Graphics g = game.getGraphics();

    }

    /**
     * Draws paused UI
     */
    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        // Darken the entire screen so you can display the Paused screen.
        g.drawARGB(155, 0, 0, 0);

    }

    /**
     * Draws game over UI
     */
    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        g.drawRect(0, 0, 1281, 801, Color.BLACK);
        g.drawString("GAME OVER.", 640, 300, paint);

    }

    /**
     * Handles paused game state from backButton.
     */
    @Override
    public void pause()
    {
        if (state == GameState.Running)
            state = GameState.Paused;
    }

    @Override
    public void resume()
    {
        //TODO resume game from paused?
    }

    @Override
    public void dispose()
    {
        //TODO kill game assets on game exit?
    }

    @Override
    public void backButton()
    {
        //Ask for exit dialog if pressed again?
        pause();
    }
}
