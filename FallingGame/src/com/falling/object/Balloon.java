package com.falling.object;

/**
 * This manages information regarding the hot air balloon game object.
 * @author evan hanger, andrew huber, mark judy
 * @version 1.0 Date: 5/3/14 Time: 2:50 AM
 */
public class Balloon extends GameObject
{
    public Balloon()
    {
        super();
        respawn();
        speedX = 0;
    }

    public void update()
    {
        centerY += speedY;
        System.out.println("CenterY: " + centerY);

        if(centerY <= -50)
            alive = false;

        if(!alive)
            respawn();

        //TODO Handle collision with Trooper
    }

    public void respawn()
    {
        centerX = randomSpawnX();
        centerY = windowHeight + 100;
        speedY = -10;
        alive = true;
    }
}
