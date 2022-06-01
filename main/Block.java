import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Block  extends Tile
{
    public Block(int ID)
    {

        if (ID == 10){                          // normaler Boden 
            setImage("floor.png");            
        }

        else if (ID == 11){                          // normaler Boden 
            setImage("floor2.PNG");            
        }

        else if (ID == 12){                          // normaler Boden 
            setImage("floor deep.png");            
        }

        else if (ID == 13){                          // normaler Boden 
            setImage("flyingfloor.png");            
        }

        else if(ID == 20){                      // rechte Wand 
            setImage("floor right (wall).png");        
        }

        else if(ID == 21){                      // rechte Wand oben
            setImage("floor right up.png");      
        }

        else if(ID == 22){                      // linke Wand 
            setImage("floor left (wall).png");      
        }

        else if(ID == 23){                      // linke Wand oben 
            setImage("floor left up.png");       
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
