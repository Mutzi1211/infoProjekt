
import greenfoot.*;

public class Player extends Objects
{

    private boolean jumplock = true;
    private int jumphigh = 0;
    private final int jumpmax = 30;
    private boolean dir = true;
    private boolean eDown = false;
    private boolean rDown = false;
    private int wnum = 0;
    private boolean dev = false;
    int texture;
    int timer;
    private GreenfootSound music = new GreenfootSound("Prime_Property.wav");
    private CaveMan m_World;

    String pressed_characters;

    public Player(CaveMan m_parent)
    {
        m_World = m_parent;
    }

    private void ColWithEnemy()
    {

        Actor Enemy = getOneObjectAtOffset(10, 0, Enemy.class);
        Actor Enemy2 = getOneObjectAtOffset(-10,0 , Enemy.class);
        Actor Enemy3= getOneObjectAtOffset(10, -9, Enemy.class);
        Actor Enemy4= getOneObjectAtOffset(-10, -9, Enemy.class);

        if (Enemy != null || Enemy2 != null || Enemy3 != null || Enemy4 != null)
        {
            if( dev == false){
                Die();
            }
        }
    }

    private void Die(){

        m_World.deathscreen();

    }

    /**
     * Test for collision
     */
    public void Collision()
    {
        if (speed_x > 0)  
        {

            Actor Tile = getOneObjectAtOffset(10, 0, Tile.class);

            if (Tile != null)
            {
                speed_x = 0; 
            }
            else       
            {
                int X = getX() + 10 + (int)speed_x;
                int Y = getY() - 9;
                int Yend = getY() + 9;
                boolean free = true;
                while ( Y <= Yend )
                {
                    if (m_World.isSolid( X ,Y, wnum)){
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
        if (speed_x < 0)        // alles nochmal f¸r Bewegung nach links
        {

            Actor Tile = getOneObjectAtOffset(-10, 0, Tile.class);

            if (Tile != null) //etwas im Weg
            {
                speed_x = 0; 
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

            Actor Tile         = getOneObjectAtOffset(9, 10+(int)speed_y, Tile.class);
            Actor Tile2        = getOneObjectAtOffset(-9, 10+(int)speed_y, Tile.class);

            if (Tile != null)
            {
                speed_y = 0;
                jumplock = false;
                setLocation(getX(),Tile.getY() - TILESIZE);
            }
            else
            if (Tile2 != null)
            {
                speed_y = 0;
                jumplock = false;
                setLocation(getX(),Tile2.getY() - TILESIZE);
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
            wnum = m_World.GenerateLevel(wnum, false);

        }

        //rechte Welt laden
        if(this.getX() >= getWorld().getWidth() - 10){

            this.setLocation(11, this.getY()); 
            wnum = wnum +1;
            wnum = m_World.GenerateLevel(wnum, false);

        }

    }

    /**
     * Get Inputs
     */
    public void GetInput()
    {
        boolean move_right = true,
        move_left  = true;

        // Tasten

        // rechts bewegen

        if (Greenfoot.isKeyDown("D"))
        {
            if (speed_x >= 0)
            {
                speed_x += 0.2f;
            }
            else if (speed_x < 0)
            {
                speed_x += 0.5f;
            }
            // Geschw ist kleiner als maxSpeed
            if (speed_x+0.3f > speed_move_MAX)
            {
                speed_x = speed_move_MAX;
            }
            // Geschw ist grˆﬂer als maxSpeed
            else if (speed_x > speed_move_MAX)
            {
                speed_x -= 0.4f;
            }
            dir = true;
        }
        else{
            move_right = false;
        }

        if (Greenfoot.isKeyDown("A"))
        {   
            if (speed_x <= 0)
            {
                speed_x -= 0.2f;
            }
            else if (speed_x > 0)
            {
                speed_x -= 0.5f;
            }
            // GEschw ist kleiner als Move Max
            if (speed_x-0.3f < -speed_move_MAX)
            {
                speed_x = -speed_move_MAX;
            }
            // Geschw ist grˆﬂer als Move_Max
            else if (speed_x < -speed_move_MAX)
            {
                speed_x += 0.4f;
            }

            dir = false;
        }
        else
        {
            move_left = false;
        }

        // wenn er sich noch bewegt aber keine Tasteneingabe vorliegt

        if (!move_left && !move_right)
        {
            if (speed_x < 0){
                speed_x += 0.75f;
            }
            else if (speed_x > 0){
                speed_x -= 0.75f;
            }
            if (speed_x <= 1.1f && speed_x >= -1.1f){
                speed_x = 0;
            }
        }

        // entwickler Modus
        if (Greenfoot.isKeyDown("w") && dev){
            speed_y = -5f;
        }
        else if (Greenfoot.isKeyDown("s") && dev){
            speed_y = 5f;
        }

        // springen
        if (Greenfoot.isKeyDown("space") && !jumplock)
        {
            if (jumphigh == 0)
                jumphigh = getY() - jumpmax;

            if (getY() <= jumphigh)
            {
                jumphigh = 0; 
                jumplock = true;
            }

            speed_y -= 0.4f;
            if (speed_y < -speed_jump_MAX)
                speed_y = -speed_jump_MAX;

        }
        else if (!Greenfoot.isKeyDown("space") && speed_y < 0 && !jumplock)
        {
            jumplock = true;
        }

        // ist er grad am springen/fallen
        if (jumplock)
        {
            speed_y += 0.3f;
            if (speed_y > speed_fall_MAX)
                speed_y = speed_fall_MAX;
        }

        // schieﬂen
        if(eDown!= Greenfoot.isKeyDown("e")){
            eDown= !eDown;
            if(eDown && getIntersectingObjects(endscreen.class).size() == 0){

                this.getWorld().addObject(new Geschoss(dir, wnum, m_World), this.getX(), this.getY());
            }

        }

        // neustarten
        if(rDown!= Greenfoot.isKeyDown("r")){
            rDown= !rDown;
            if(rDown){
                m_World.GenerateLevel(wnum, true);
            }

        }

        // easter egg
        String k = Greenfoot.getKey();
        if(k != null){
            k = k.toLowerCase();

            if("primeproperty".contains(k)){
                pressed_characters = pressed_characters + k;
                if(pressed_characters.contains("primeproperty")){
                    pressed_characters = "";
                    music.play();
                    dev = !dev;
                }
            }
        }

        if(Greenfoot.isKeyDown("j") && dev){
            String input = Greenfoot.ask("Level (1-12) ");

            try{
                wnum = Integer.parseInt(input) - 1;
            }

            catch (NumberFormatException ex){

            } 
            if (wnum < 0 || wnum > 11){
                wnum = 0;
            }
            m_World.GenerateLevel(wnum, true);
        }
    }

    public void act()
    {
        timer++;
        if(timer == 60){
            timer = 0;
        }
        if(dev){
            if(timer == 0 || timer == 5 || timer == 10 || timer == 15 || timer == 20 || timer == 25 || timer == 30 || timer == 35 || timer == 40 || timer == 45 || timer == 50 || timer == 55) {
                texture++;
                if(texture >= 12){
                    texture = 1;
                }
                String texturename = "Player"+texture+".png";
                setImage(texturename);
            }
        }
        if(texture != 1 && !dev){
            texture = 1;
            setImage("Player1.png");
        }

        GetInput();
        Collision();
        ColWithEnemy();
    }
}

