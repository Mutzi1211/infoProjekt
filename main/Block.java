import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Block  extends Tile
{
    public Block(int ID)
    {
        if (ID == 10){                          // normaler Boden 
            setImage("floor.png");            
        }

        else if (ID == 11){                          // normaler Boden 
            setImage("floor.png");            
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

        else if(ID == 300){                     
            setImage("confused.png");       
        }

        else if(ID == 301){                     
            setImage("301.png");       
        }

        else if(ID == 302){                     
            setImage("302.png");       
        }

        else if(ID == 303){                     
            setImage("303.png");       
        }

        else if(ID == 304){                     
            setImage("304.png");       
        }

        else if(ID == 305){                     
            setImage("305.png");       
        }

        else if(ID == 306){                     
            setImage("306.png");       
        }

        else if(ID == 307){                     
            setImage("307.png");       
        }

        else if(ID == 308){                     
            setImage("308.png");       
        }

        else if(ID == 309){                     
            setImage("309.png");       
        }

        else if(ID == 310){                     
            setImage("310.png");       
        }

        else if(ID == 311){                     
            setImage("311.png");       
        }

        else if(ID == 312){                     
            setImage("312.png");       
        }

        else if(ID == 313){                     
            setImage("313.png");       
        }

        else if(ID == 314){                     
            setImage("314.png");       
        }

        else if(ID == 315){                     
            setImage("315.png");       
        }

        else if (ID== 316){
            setImage("316.png");
        }

        else if (ID== 317){
            setImage("306_emerald.png");
        }

        else if (ID== 318){
            setImage("306_stone.png");
        }

        else if (ID== 319){
            setImage("314_2.png");
        }

        else if(ID == 320){                     
            setImage("signal.png");       
        }

        else if(ID == 321){                     
            setImage("302_2.png");       
        }

        else if(ID == 322){                     
            setImage("302_3_lefttrans.png");       
        }

        else if(ID == 323){                     
            setImage("302_4_righttrans.png");       
        }

        else if(ID == 324){                     
            setImage("314_3_lefttrans.png");       
        }

        else if(ID == 325){                     
            setImage("314_4_righttrans.png");       
        }

        else if(ID == 40){
            setImage("background brick.png");
        }
        else if(ID == 326){
            setImage("301_righttrans.png");
        }

        else if(ID == 327){
            setImage("303_lefttrans.png");
        }

        else if(ID == 328){
            setImage("302_Htrans.png");
        }

        else if(ID == 329){
            setImage("313_Righttrans.png");
        }

        else if(ID == 330){
            setImage("315_lefttrans.png");
        }

        else if(ID == 331){
            setImage("314_Htrans.png");
        }

        else if(ID <= 180){
            ID = ID + 201;
            String texturename = ID +".png";
            setImage(texturename);
        }
        else{
            setImage("undefined.png");       // undefinerter Bloch (gut erkennbar)
        }

    }  

    public void act() 
    {
        // Add your action code here.
    }
}
