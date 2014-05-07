package com.falling.object;

/**
 * This manages the information regarding the normal enemy missile game objects.
 * @author evan hanger, andrew huber, mark judy
 * @version 1.0 Date: 5/3/14 Time: 2:44 AM
 */
public class NormalMissile extends Missile
{
    public NormalMissile()
    {
        super();
        respawn();
    }

    public void update()
    {
        centerX += speedX;
        centerY += speedY;

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
        centerY = windowHeight + 50;
        centerX = randomSpawnX();
        speedX = 15;
        speedY = 15;
        alive = true;
    }
}
