import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Box  extends Objects
{
    /**
     * Change velocity when player touches box
     */
    public void Setvelocity(double v_velx, double v_vely)
    {
        if (v_velx != 0)
        velocity_x = v_velx;
        if (v_vely != 0)
        velocity_y = v_vely;
    }
    /**
     * Control velocity
     */
    public void ControlVelocity()
    {
        if (velocity_x != 0)
        {
            // Was he moving left ?
            if (velocity_x < 0)
            velocity_x += 0.5f;
            else
            // was he moving right ?
            if (velocity_x > 0)
            velocity_x -= 0.5f;
            // We want to set the velocity in direction x instantly to zero
            // when we're near zero
            if (velocity_x <= 1.1f && velocity_x >= -1.1f)
            velocity_x = 0;
        }
        if (velocity_y != 0)
        {
            velocity_y += 0.3f;
            if (velocity_y > velocity_fall_MAX)
            velocity_y = velocity_fall_MAX; 
        }
    }
    /**
     * Test for collision
     */
    public void Collision()
    {
        if (velocity_y > 0)
        {
            Actor Block = getOneObjectAtOffset(9, 10+(int)velocity_y, Block.class);
            if (Block != null)
            {
                velocity_y = 0;
                setLocation(getX(), Block.getY() - TILESIZE);
            }
            else
            movexy(0, velocity_y);
        }
        else
        if (velocity_y == 0)
        {
            Actor Block = getOneObjectAtOffset(8, 10, Block.class);
            if (Block == null)
            {
                velocity_y = 0.3f;
            }
        }
    }
    /**
     * Act - do whatever the Ground wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        ControlVelocity();
        Collision(); 
    }
}
