package com.falling.object;

import com.falling.FallingGame;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Surface;
import android.view.WindowManager;

/**
 * This manages information regarding the paratrooper, the hero of our game!
 * @author evan hanger, andrew huber, mark judy
 * @version 1.0 Date: 5/2/14 Time: 11:15 PM
 */
public class Trooper extends GameObject
{	
	private final static double MAX_SPEED = 20.0;
	private int orientation;
	
	public Trooper()
    {	
        super();
        alive = true;
        centerX = (windowWidth + 48) / 2;
        centerY = 200;
        speedY = 0;
        speedX = 0;
       
        SensorManager manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		manager.registerListener(listener, 
				manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), 
				SensorManager.SENSOR_DELAY_GAME);
		
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		orientation = wm.getDefaultDisplay().getRotation();
    }

    public void update()
    {
        //Move left
        if(speedX < 0)
        {
            centerX += speedX;
        }
        //Move right
        if(speedX > 0)
        {
            centerX += speedX;
        }

        //Prevent leaving left side of screen
        if(centerX + speedX <= 24)
        {
            centerX = 25;
        }
        //Prevent leaving right side of screen
        if(centerX + speedX >= windowWidth + 24)
        {
            centerX = windowWidth + 23;
        }

        //TODO handle collisions
    }
     
    private SensorEventListener listener = new SensorEventListener() {
		
		@Override
		public void onSensorChanged(SensorEvent event) {
			if(FallingGame.useAccelerometer.get() == false)
				return;			
			
			double x = Math.abs(event.values[0]);
			double y = Math.abs(event.values[1]);
			double xraw = event.values[0];
			
			if(orientation == Surface.ROTATION_90 || orientation == Surface.ROTATION_270) {
				double temp = x;
				x = y;
				y = temp;
				
				xraw = event.values[1];
			}
			
			double ang = (90 * x) / 9.8;			
			double percentage = ang / 90.0;
			
			if(orientation == Surface.ROTATION_180 || orientation == Surface.ROTATION_270)
				xraw *= -1.0;
			
			double direction = xraw / x;
			
			speedX = (int) (percentage * MAX_SPEED * direction);			
		}
		
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// Do nothing...
		}
	};
}
