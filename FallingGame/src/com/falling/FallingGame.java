package com.falling;

import android.content.Context;
import android.os.Bundle;
import com.falling.fallinggame.Assets;
import com.falling.fallinggame.LoadingScreen;
import com.falling.framework.Screen;
import com.falling.framework.implementation.AndroidGame;

/**
 * The main activity.
 * @author evan hanger, andrew huber, mark judy
 */
public class FallingGame extends AndroidGame
{
    boolean firstTimeCreate = true;
    private static Context context;


    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        context = getApplicationContext();
    }

    @Override
    public Screen getInitScreen()
    {
        if (firstTimeCreate)
        {
            //Loads sound assets if necessary?
            Assets.load(this);
            firstTimeCreate = false;
        }

        return new LoadingScreen(this);
    }

    @Override
    public void onBackPressed()
    {
        getCurrentScreen().backButton();
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    @Override
    public void onPause()
    {
        super.onPause();
    }

    /**
     * Gets activity context.
     * @return the current context.
     */
    public static Context getContext()
    {
        return context;
    }
}
