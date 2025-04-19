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
    private CaveMan m_World;
    int wnum;
    private int remove = 0;

    public Geschoss(boolean move_right, int num, CaveMan cave_world){
        dir = move_right;
        wnum = num;
        m_World = cave_world;
    }

    private void ColWithEnemy()
    {
        Actor Enemy = getOneObjectAtOffset(10, 0, Enemy.class);
        Actor Enemy2 = getOneObjectAtOffset(-10,0 , Enemy.class);
        Actor Enemy3= getOneObjectAtOffset(10, -9, Enemy.class);
        Actor Enemy4= getOneObjectAtOffset(-10, -9, Enemy.class);

        if (Enemy != null || Enemy2 != null || Enemy3 != null || Enemy4 != null)
        {
            this.getWorld().removeObject(Enemy);
            this.getWorld().removeObject(Enemy2);
            this.getWorld().removeObject(Enemy3);
            this.getWorld().removeObject(Enemy4);
            remove = 1;
        }
    }

    public void act()
    {
        Collision();
        ColWithEnemy();
        if (dir){
            this.move(10);
        }
        else{
            this.move(-10);
        }
        if(this.isAtEdge()){
            remove = -1;
        }
        if(remove == 1){
            m_World.removeE(wnum, getY());
            this.getWorld().removeObject(this);
            remove = 0;
        }
        if(remove == -1){
            this.getWorld().removeObject(this);
            remove = 0;
        }
    }

    void Collision(){

        if(getIntersectingObjects(Tile.class).size() != 0){
            remove = -1;
        }

    }
}
