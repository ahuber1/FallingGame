package com.falling.object;

/**
 * This class serves as a basis for the various enemy missile types.
 * @author evan hanger, andrew huber, mark judy
 * @version 1.0 Date: 5/3/14 Time: 2:42 AM
 */
public abstract class Missile extends GameObject
{
    public Missile()
    {
        super();
    }

    public abstract void update();

    public abstract void respawn();
}
