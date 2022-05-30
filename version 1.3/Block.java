import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Block  extends Tile
{

    private int GFX = 0;
    public Block(int ID)
    {

        if (ID == 1){
            setImage("floor.png");
        }
        else if(ID == 2){
            setImage("flyingfloor.png");
        }

    }

    public void act() 
    {
        // Add your action code here.
    }

    public void Setgfx(int gfx)
    {
        setImage("flyingfloor.png");
        
        setImage("floor.png");
        GFX = gfx;
    }
}
