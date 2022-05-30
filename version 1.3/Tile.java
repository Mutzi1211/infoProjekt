import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Tile  extends Actor
{
    public void act() 
    {
        // Add your action code here.
    }    
    public void SetGFX(int ID)
    {
        if (ID == 1){
        setImage("floor.png");
    }
    else if(ID == 2){
         setImage("flyingfloor.png");
    }
        World welt = (CaveMan)this.getWorld();
    }
}
