import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Block  extends Tile
{
    public Block(int ID)
    {

        if (ID == 1){
            setImage("floor.png");              // normaler Boden
        }
        else if(ID == 2){
            setImage("flyingfloor.png");        // fliegender Boden
        }
        
        else if(ID == 3){
            setImage("flyingfloor.png");        // Wand
        }
        
        else if(ID == 4){
            setImage("flyingfloor.png");        // Kiste
        }
        
        else if(ID == 5){
            setImage("flyingfloor.png");        // Decke
        }

        else{
            
             setImage("flyingfloor.png");       // undefinerter Bloch (gut erkennbar)
        }
        
    }

    public void act() 
    {
        // Add your action code here.
    }
}
