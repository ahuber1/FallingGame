package com.falling.object;

/**
 * This manages information regarding the bird enemy game object.
 * @author evan hanger, andrew huber, mark judy
 * @version 1.0 Date: 5/3/14 Time: 2:31 AM
 */
public class Bird extends GameObject
{
    public Bird()
    {
        super();
        respawn();
    }

    public void update()
    {
        centerX += speedX;
        centerY += speedY;

        if(centerX >= windowWidth + 100)
            alive = false;
        if(centerX <= -100)
            alive = false;

        if(!alive)
            respawn();

        //TODO Handle collision with Trooper
    }

    private void respawn()
    {
        speedY = -10;
        centerY = randomSpawnY();
        centerX = randomSpawnSide();

        if(centerX <= 0)
        {
            speedX = 10;
            centerX -= 100;
        }
        else
        {
            speedX = -10;
            centerX += 100;
        }

        alive = true;
    }
}
