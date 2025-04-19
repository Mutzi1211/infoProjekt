import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Write a description of class deathscreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class deathscreen extends screens
{
    /**
     * Act - do whatever the deathscreen wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public deathscreen() {
        Random rand = new Random();
        int int_random = rand.nextInt(6);

        if (int_random == 0) {
            this.setImage("d1.png");
        } 
        else if (int_random == 1){

            this.setImage("d2.png");
        }
        else if (int_random == 2){

            this.setImage("d3.png");

        }else if (int_random == 3){

            this.setImage("d4.png");
        }
        else if (int_random == 4){

            this.setImage("d5.png");
        }
        else if (int_random == 5){

            this.setImage("d6.png");
        }
    }

    public void act()
    {
        // Add your action code here.
    }
}
