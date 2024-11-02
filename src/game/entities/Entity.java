package game.entities;


import java.awt.Rectangle;

public class Entity {
    public float worldX, worldY;
    public float speed;

    public Direction direction;
    public boolean isIdle, isRolling;
    public Rectangle solidArea;
    public boolean collisionOn = false;

    public enum Direction{
        BACK, FRONT, RIGHT, LEFT
    }

}
