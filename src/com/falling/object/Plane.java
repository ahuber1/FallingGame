package com.falling.object;

/**
 * This manages information regarding the enemy plane game object.
 * @author evan hanger, andrew huber, mark judy
 * @version 1.0 Date: 5/3/14 Time: 2:48 AM
 */
public class Plane extends GameObject
{
    public Plane()
    {
        super();
        respawn();
    }

    public void update()
    {
        centerX += speedX;

        if(centerX >= windowWidth + 50)
            alive = false;
        if(centerX <= -50)
            alive = false;

        if(!alive)
            respawn();

        //TODO Handle collision with Trooper
    }

    public void respawn()
    {
        speedY = 0;
        centerY = randomSpawnY();
        centerX = randomSpawnSide();

        if(centerX == 0)
            speedX = 20;
        else
            speedX = -20;

        alive = true;
    }
}
