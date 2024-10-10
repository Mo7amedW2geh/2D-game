package entity;


import java.awt.Rectangle;

public class Entity {
    public int worldX, worldY;
    public int speed;

    public String direction;
    public boolean isIdle;
    public Rectangle solidArea;
    public boolean collisionOn = false;

    public int spriteCounter = 0;
    public int spriteNum = 0;

}
