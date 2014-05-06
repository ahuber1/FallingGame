package com.falling.object;

/**
 * This manages information regarding the paratrooper, the hero of our game!
 * @author evan hanger, andrew huber, mark judy
 * @version 1.0 Date: 5/2/14 Time: 11:15 PM
 */
public class Trooper extends GameObject
{
    public Trooper()
    {
        super();
        alive = true;
        centerX = (windowWidth + 48) / 2;
        centerY = 200;
        speedY = 0;
        speedX = 0;
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
}
