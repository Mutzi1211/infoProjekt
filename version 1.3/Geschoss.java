import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Geschoss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Geschoss extends Objects
{
    /**
     * Act - do whatever the Geschoss wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public boolean dir;
    private int wnum;
    private CaveMan m_World;
    public Geschoss(boolean dirnew, int num){
        dir = dirnew;
        wnum = num;
    }

    public void act()
    {
        Collision1();
        if (dir){
            this.move(-10);
        }
        else{
            this.move(10);
        }
        if(this.isAtEdge()){
            this.getWorld().removeObject(this);
        }
    }

    void Collision1(){
        Actor Box = getOneObjectAtOffset(10, 0, Box.class);

        if (Box != null){
            this.getWorld().removeObject(this);
        }
    }
}
