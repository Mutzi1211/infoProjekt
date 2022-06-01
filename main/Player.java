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
        if (speed_x > 0)  // wenn Bewegung nach rechts
        {
            Actor Box = getOneObjectAtOffset(10, 0, Box.class);

            if (Box != null) //etwas im Weg
            {
                // keine Bewegung
            }
            else        // nix im Weg
            {
                int X = getX() + 10 + (int)speed_x;
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
                    movexy(speed_x, 0);
                else
                    speed_x = 0;
            }

        }
        else
        if (speed_x < 0)        // alles nochmal für Bewegung nach links
        {

            Actor Box = getOneObjectAtOffset(-10, 0, Box.class);

            if (Box != null) //etwas im Weg
            {
                // keine Bewegung
            }
            else
            {
                int X = getX() - 10 + (int)speed_x;
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
                    movexy(speed_x, 0);
                else
                    speed_x = 0;
            }
        }

        if (speed_y > 0)     // Fallend
        {
            jumphigh = 0; 

            Actor Box         = getOneObjectAtOffset(9, 10+(int)speed_y, Box.class);
            Actor Box2        = getOneObjectAtOffset(-9, 10+(int)speed_y, Box.class);

            if (Box != null)
            {
                speed_y = 0;
                jumplock = false;
                setLocation(getX(),Box.getY() - TILESIZE);
            }
            else
            if (Box2 != null)
            {
                speed_y = 0;
                jumplock = false;
                setLocation(getX(),Box2.getY() - TILESIZE);
            }
            else
            {
                int Y = getY() + 10 + (int)speed_y;
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
                    movexy(0, speed_y);
                else
                    speed_y = 0;
            }
        }
        else
        if (speed_y < 0)
        {

            int Y = getY() - 10 + (int)speed_y;
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
                movexy(0, speed_y);
            else
                speed_y = 0.1;
        }
        else
        // Haben wir Boden ???
        if (speed_y == 0)
        {
            boolean left  = m_World.isSolid(getX() - 9, getY() + 11, wnum);
            boolean right = m_World.isSolid(getX() + 9, getY() + 11, wnum);

            if (!left && !right)
            {
                speed_y = 0.3f;
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
            if (speed_x >= 0)
                speed_x += 0.2f;

            else
            if (speed_x < 0)
                speed_x += 0.5f;

            // GEschw ist größer als -Move_MAX
            if (speed_x+0.3f > speed_move_MAX)
                speed_x = speed_move_MAX;
            else
            // GEschw ist kleiner als -Move_MAX
            if (speed_x > speed_move_MAX)
                speed_x -= 0.4f;

            dir = true;
        }
        else
            move_left = false;

        if (Greenfoot.isKeyDown("A"))
        {   
            if (speed_x <= 0)
                speed_x -= 0.2f;
            else
            if (speed_x > 0)
                speed_x -= 0.5f;
            // GEschw ist kleiner als Move_MAX
            if (speed_x-0.3f < -speed_move_MAX)
                speed_x = -speed_move_MAX;
            else
            // GEschw ist größer als Move_MAX
            if (speed_x < -speed_move_MAX)
                speed_x += 0.4f;

            dir = false;
        }
        else
            move_right = false;

        if (Greenfoot.isKeyDown("w"))
            speed_y -= 0.4f;
        else
        if (Greenfoot.isKeyDown("s"))
            speed_y -= 0.3f;

        if (Greenfoot.isKeyDown("space") && !jumplock)
        {
            if (jumphigh == 0)
                jumphigh = getY() - JumpMAX;

            if (getY() <= jumphigh)
            {
                jumphigh = 0; 
                jumplock = true;
            }

            speed_y -= 0.4f;
            if (speed_y < -speed_jump_MAX)
                speed_y = -speed_jump_MAX;

        }
        else   
        if (!Greenfoot.isKeyDown("space") && speed_y < 0 && !jumplock)
        {
            jumplock = true;
        }

        if (jumplock)
        {
            speed_y += 0.3f;
            if (speed_y > speed_fall_MAX)
                speed_y = speed_fall_MAX;
        }
        if (!move_left && !move_right)
        {
            // Was he moving left ?
            if (speed_x < 0)
                speed_x += 0.75f;
            else
            // was he moving right ?
            if (speed_x > 0)
                speed_x -= 0.75f;

            // We want to set the speed in direction x instantly to zero
            // when we're near zero
            if (speed_x <= 1.1f && speed_x >= -1.1f)
                speed_x = 0;
        }
        // When retry is pressed we reload the map in order to let the
        // Player retry the current map

        if(down != Greenfoot.isKeyDown("e")){
            down = ! down;
            if(down){
                this.getWorld().addObject(new Geschoss(dir, wnum), this.getX(), this.getY());
            }

        }

        if(Greenfoot.isKeyDown("r")){

            setLocation(m_World.saveSpotY(wnum), m_World.saveSpotX(wnum));

        }
    }

    public void act()
    {
        // 1. Tasten einnehmen
        GetInput();
        // 3. Kollission also Position errechnen
        // if (speed_x != 0 || speed_y != 0)
        Collision();
    }
}

