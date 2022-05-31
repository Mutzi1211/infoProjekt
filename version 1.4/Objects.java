import greenfoot.*;

import java.util.List;
import java.util.ArrayList;

/**
 * Animal. This is the base class for all animals. In addition to the standard Actor
 * methods, it provides the ability to move and turn.
 * 
 * @author Michael Kolling
 * @version 1.0
 */
public class Objects extends Actor
{
    // Movement
    protected double speed_x = 0;
    protected double speed_y = 0;
    protected double speed_move_MAX = 5;
    protected final double speed_jump_MAX = 6;
    protected final double speed_fall_MAX = 8;
    protected final int TILESIZE = 20;
    private static final double WALKING_SPEED = 5.0;
    
    /**
     * Constructor for Animal - nothing to do.
     */
    public Objects()
    {
    }
    /**
     * Act - empty method. Animals have no default action.
     */
    public void act()
    {
    }    
    /**
     * Turn 'angle' degrees towards the right (clockwise).
     */
    public void turn(int angle)
    {
        setRotation(getRotation() + angle);
    }    
    /**
     * Move with the desired values (speed maths)
     */
    public void movexy(double speed_x, double speed_y)
    {
        setLocation(getX() + (int)speed_x, getY() + (int)speed_y);
    }
    /**
     * Move forward in the current direction.
     */
    public void move()
    {
        double angle = Math.toRadians( getRotation() );
        int x = (int) Math.round(getX() + Math.cos(angle) * WALKING_SPEED);
        int y = (int) Math.round(getY() + Math.sin(angle) * WALKING_SPEED);
        
        setLocation(x, y);
    }   
    /**
     * Move forward in the current direction with a defined step.
     */
    public void move(double Speed)
    {
        double angle = Math.toRadians( getRotation() );
        int x = (int) Math.round(getX() + Math.cos(angle) * Speed);
        int y = (int) Math.round(getY() + Math.sin(angle) * Speed);
        
        setLocation(x, y);
    }  
    /**
     * Test if we are close to one of the edges of the world. Return true is we are.
     */
    public boolean atWorldEdge()
    {
        if(getX() < 20 || getX() > getWorld().getWidth() - 20)
           return false;
        if(getY() < 20 || getY() > getWorld().getHeight() - 20)
            return true;
        else
            return false;
    }
    public void SetSpeedLimit(int limit)
    {
        if (limit != 0)
        speed_move_MAX = limit;
    }
}