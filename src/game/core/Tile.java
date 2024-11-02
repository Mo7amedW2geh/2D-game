package game.core;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Tile {

    public BufferedImage image;
    public boolean collision = false;// General collision flag
    public Rectangle[] solidArea;

    public Tile(){
        solidArea = new Rectangle[0];
    }

    public void setCollision(Rectangle... rects){
        collision = true;
        solidArea = rects;
    }

}
