package com.falling.object;

/**
 * This handles information regarding the enemy UFO game object
 * @author evan hanger, andrew huber, mark judy
 * @version 1.0 Date: 5/3/14 Time: 2:54 AM
 */
public class UFO extends GameObject
{
    public UFO()
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
    }

    public void respawn()
    {
        centerX = randomSpawnSide();
        centerY = randomSpawnY();

        if(centerX == 0)
            speedX = 40;
        if(centerX == windowWidth)
            speedX = -40;

        alive = true;
    }
}
