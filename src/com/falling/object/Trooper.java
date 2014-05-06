package com.falling.object;

import java.util.Date;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * This manages information regarding the paratrooper, the hero of our game!
 * @author evan hanger, andrew huber, mark judy
 * @version 1.0 Date: 5/2/14 Time: 11:15 PM
 */
public class Trooper extends GameObject
{	
	private SensorManager manager;
	
	public Trooper(Context context)
    {
        super();
        alive = true;
        centerX = (windowWidth + 48) / 2;
        centerY = 200;
        speedY = 0;
        speedX = 0;
        manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		manager.registerListener(listener, 
				manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), 
				SensorManager.SENSOR_DELAY_GAME);
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
			double accelx = event.values[0];
			double accely = event.values[1];
			double accelz = event.values[2];
			
			double gravx = 0;
			double gravy = accelz > accely ? 0 : 9.8;
			double gravz = accelz > accely ? 9.8 : 0;
			
			double accelmag = Math.sqrt(Math.pow(accelx, 2) + Math.pow(accely, 2) + Math.pow(accelz, 2));
			double gravmag = 9.8;
			
			double num = accelx * gravx + accely * gravy + accelz * gravz;
			double den = accelmag * gravmag;
			
			double angle = Math.toDegrees(Math.acos(num / den));
			boolean left = angle > 0;
			double percentage = Math.abs(angle) / 90.0;
		}
		
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// Do nothing...
		}
	};
}
