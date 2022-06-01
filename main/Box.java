import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Box  extends Objects
{


    /**
     * Change speed when player touches box
     */
    public void Setspeed(double v_velx, double v_vely)
    {
        if (v_velx != 0)
            speed_x = v_velx;
        if (v_vely != 0)
            speed_y = v_vely;
    }

    /**
     * Control speed
     */
    public void Controlspeed()
    {
        if (speed_x != 0)
        {
            // Was he moving left ?
            if (speed_x < 0)
                speed_x += 0.5f;
            else
            // was he moving right ?
            if (speed_x > 0)
                speed_x -= 0.5f;
            // We want to set the speed in direction x instantly to zero
            // when we're near zero
            if (speed_x <= 1.1f && speed_x >= -1.1f)
                speed_x = 0;
        }
        if (speed_y != 0)
        {
            speed_y += 0.3f;
            if (speed_y > speed_fall_MAX)
                speed_y = speed_fall_MAX; 
        }
    }

    /**
     * Test for collision
     */
    public void Collision()
    {
        if (speed_y > 0)
        {
            Actor Block = getOneObjectAtOffset(9, 10+(int)speed_y, Block.class);
            if (Block != null)
            {
                speed_y = 0;
                setLocation(getX(), Block.getY() - TILESIZE);
            }
            else
                movexy(0, speed_y);
        }
        else
        if (speed_y == 0)
        {
            Actor Block = getOneObjectAtOffset(8, 10, Block.class);
            if (Block == null)
            {
                speed_y = 0.3f;
            }
        }
    }

    /**
     * Act - do whatever the Ground wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        Controlspeed();
        Collision(); 
    }
}
