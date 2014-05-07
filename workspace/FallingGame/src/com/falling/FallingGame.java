package com.falling;

import java.util.concurrent.atomic.AtomicBoolean;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.view.GestureDetector.SimpleOnGestureListener;

import com.example.FallingGame.R;
import com.falling.fallinggame.Assets;
import com.falling.fallinggame.LoadingScreen;
import com.falling.framework.Screen;
import com.falling.framework.implementation.AndroidGame;
import com.falling.object.Trooper;

/**
 * The main activity.
 * @author evan hanger, andrew huber, mark judy
 */
public class FallingGame extends AndroidGame
{
    private boolean firstTimeCreate = true;
    private static Context context;
    private static String USE_ACCELEROMETER = "USE_ACCELEROMETER";
    private Bundle bundle;
    public static AtomicBoolean useAccelerometer;
    private Point screenSize;
    public static Trooper trooper;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        trooper = new Trooper();
        bundle = savedInstanceState;
        useAccelerometer = new AtomicBoolean();
        enableMenuButtons(null);
        screenSize = new Point();
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        wm.getDefaultDisplay().getSize(screenSize);
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	super.onTouchEvent(event);
    	Log.i("Falling Game", "Touching...");
    	return true;
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	getMenuInflater().inflate(R.menu.falling_game_menu, menu);
    	enableMenuButtons(menu);    	
    	return true;
    }
    
    private void enableMenuButtons(Menu menu) {
    	useAccelerometer.set(bundle != null && bundle.containsKey(USE_ACCELEROMETER) ? bundle.getBoolean(USE_ACCELEROMETER) : true);
    	
    	if(useAccelerometer.get() && menu != null) {
    		menu.getItem(0).setEnabled(false);
    		menu.getItem(1).setEnabled(true);
    	}
    	else if(menu != null) {
    		menu.getItem(0).setEnabled(true);
    		menu.getItem(1).setEnabled(false);
    	}
	}   

	@Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
    	// TODO Auto-generated method stub
    	return super.onMenuItemSelected(featureId, item);
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
