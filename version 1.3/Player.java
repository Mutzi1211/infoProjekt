import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

public class Player extends Objects
{
    // von anfang an soll Gravitation auf den Spieler wirken
    private boolean jumplock = true;

    private int     jumphigh = 0;
    private final int JumpMAX = 60;
    private boolean freeze = false;
    private int TILE_SIZE = 20;
    private boolean dir = true;
    private boolean down;
    private int wnum = 0;

    private CaveMan m_World;

    public Player(CaveMan m_parent)
    {
        m_World = m_parent;
    }

    /**
     * Test for collision
     */
    public void Collision()
    {
        if (velocity_x > 0)
        {
            Actor Box = getOneObjectAtOffset(10, 0, Box.class);

            if (Box != null)
            {
                Box box = (Box)Box;
                setLocation(Box.getX() - TILESIZE,getY());
                box.Setvelocity(velocity_x, 0);
                velocity_x = 0;
            }
            else
            {
                int X = getX() + 10 + (int)velocity_x;
                int Y = getY() - 9;
                int Yend = getY() + 9;
                boolean free = true;
                while ( Y <= Yend )
                {
                    if (m_World.isSolid( X ,Y, wnum))                        {
                        setLocation(X/TILESIZE * TILESIZE - 10,getY());
                        free = false;
                    }

                    Y += 18;
                }

                if (free)
                    movexy(velocity_x, 0);
                else
                    velocity_x = 0;
            }

        }
        else
        if (velocity_x < 0)
        {

            Actor Box = getOneObjectAtOffset(-10, 0, Box.class);

            if (Box != null)
            {
                // Convert the actor into an Box-object in order to use our selfmade functions
                Box box = (Box)Box;
                setLocation(Box.getX() + TILESIZE,getY());
                box.Setvelocity(velocity_x, 0);
                velocity_x = 0;
            }
            else
            {
                int X = getX() - 10 + (int)velocity_x;
                int Y = getY() - 9;
                int Yend = getY() + 9;
                boolean free = true;
                while ( Y <= Yend )
                {
                    if (m_World.isSolid( X ,Y, wnum))
                    {
                        setLocation((X/TILESIZE + 1) * TILESIZE + 10,getY());
                        free = false;
                    }

                    Y += 18;
                }
                if (free)
                    movexy(velocity_x, 0);
                else
                    velocity_x = 0;
            }
        }
        // Fallend
        if (velocity_y > 0)
        {
            jumphigh = 0; 

            Actor Box         = getOneObjectAtOffset(9, 10+(int)velocity_y, Box.class);
            Actor Box2        = getOneObjectAtOffset(-9, 10+(int)velocity_y, Box.class);

            if (Box != null)
            {
                velocity_y = 0;
                jumplock = false;
                setLocation(getX(),Box.getY() - TILESIZE);
            }
            else
            if (Box2 != null)
            {
                velocity_y = 0;
                jumplock = false;
                setLocation(getX(),Box2.getY() - TILESIZE);
            }
            else
            {
                int Y = getY() + 10 + (int)velocity_y;
                int X = getX() - 9;
                int Xend = getX() + 9;

                boolean free = true;

                while ( X <= Xend )
                {
                    if (m_World.isSolid( X ,Y, wnum)) // Konvertierung nicht vergessen !
                    {

                        setLocation(getX(),(Y/TILESIZE) * TILESIZE - 10);
                        free = false;
                        jumplock = false;
                    }

                    X += 18;
                }

                if (free)
                    movexy(0, velocity_y);
                else
                    velocity_y = 0;
            }
        }
        else
        if (velocity_y < 0)
        {

            int Y = getY() - 10 + (int)velocity_y;
            int X = getX() - 9;
            int Xend = getX() + 9;

            boolean free = true;

            while ( X <= Xend )
            {
                if (m_World.isSolid( X ,Y, wnum)) // Konvertierung nicht vergessen !
                {
                    setLocation(getX(),(Y/TILESIZE + 1) * TILESIZE + 10);
                    free = false;
                    jumplock = true;
                }

                X += 18;
            }

            if (free)
                movexy(0, velocity_y);
            else
                velocity_y = 0.1;
        }
        else
        // Haben wir Boden ???
        if (velocity_y == 0)
        {
            boolean left  = m_World.isSolid(getX() - 9, getY() + 11, wnum);
            boolean right = m_World.isSolid(getX() + 9, getY() + 11, wnum);

            if (!left && !right)
            {
                velocity_y = 0.3f;
                jumplock = true;
            }
        }
        // linke Welt laden
        if(this.getX() <= 10){
            this.setLocation(getWorld().getWidth() - 11, this.getY()); 
            wnum = wnum -1;
            m_World.GenerateLevel(wnum);

        }

        //rechte Welt laden
        if(this.getX() >= getWorld().getWidth() - 10){

            this.setLocation(11, this.getY()); 
            wnum = wnum +1;
            m_World.GenerateLevel(wnum);
        }

    }

    /**
     * Get Inputs
     */
    public void GetInput()
    {
        boolean move_right = true,
        move_left  = true;

        // Tasten einnehmen

        if (Greenfoot.isKeyDown("D"))
        {
            if (velocity_x >= 0)
                velocity_x += 0.2f;

            else
            if (velocity_x < 0)
                velocity_x += 0.5f;

            // GEschw ist größer als -Move_MAX
            if (velocity_x+0.3f > velocity_move_MAX)
                velocity_x = velocity_move_MAX;
            else
            // GEschw ist kleiner als -Move_MAX
            if (velocity_x > velocity_move_MAX)
                velocity_x -= 0.4f;

            dir = false;
        }
        else
            move_left = false;

        if (Greenfoot.isKeyDown("A"))
        {   
            if (velocity_x <= 0)
                velocity_x -= 0.2f;
            else
            if (velocity_x > 0)
                velocity_x -= 0.5f;
            // GEschw ist kleiner als Move_MAX
            if (velocity_x-0.3f < -velocity_move_MAX)
                velocity_x = -velocity_move_MAX;
            else
            // GEschw ist größer als Move_MAX
            if (velocity_x < -velocity_move_MAX)
                velocity_x += 0.4f;

            dir = true;
        }
        else
            move_right = false;

        if (Greenfoot.isKeyDown("w"))
            velocity_y -= 0.4f;
        else
        if (Greenfoot.isKeyDown("s"))
            velocity_y -= 0.3f;

        if (Greenfoot.isKeyDown("space") && !jumplock)
        {
            if (jumphigh == 0)
                jumphigh = getY() - JumpMAX;

            if (getY() <= jumphigh)
            {
                jumphigh = 0; 
                jumplock = true;
            }

            velocity_y -= 0.4f;
            if (velocity_y < -velocity_jump_MAX)
                velocity_y = -velocity_jump_MAX;

        }
        else   
        if (!Greenfoot.isKeyDown("space") && velocity_y < 0 && !jumplock)
        {
            jumplock = true;
        }

        if (jumplock)
        {
            velocity_y += 0.3f;
            if (velocity_y > velocity_fall_MAX)
                velocity_y = velocity_fall_MAX;
        }
        if (!move_left && !move_right)
        {
            // Was he moving left ?
            if (velocity_x < 0)
                velocity_x += 0.75f;
            else
            // was he moving right ?
            if (velocity_x > 0)
                velocity_x -= 0.75f;

            // We want to set the velocity in direction x instantly to zero
            // when we're near zero
            if (velocity_x <= 1.1f && velocity_x >= -1.1f)
                velocity_x = 0;
        }
        // When retry is pressed we reload the map in order to let the
        // Player retry the current map

        if(down != Greenfoot.isKeyDown("e")){
            down = ! down;
            if(down){
                this.getWorld().addObject(new Geschoss(dir, wnum), this.getX(), this.getY());
            }

        }
    }

    public void act()
    {
        // 1. Tasten einnehmen
        GetInput();
        // 3. Kollission also Position errechnen
        // if (velocity_x != 0 || velocity_y != 0)
        Collision();
    }
}

