package game.entities;


import java.awt.Rectangle;

public class Entity {
    public float worldX, worldY;
    public float speed;

    public Direction direction;
    public MoveDirection moveDirection;
    public boolean isIdle;
    public Rectangle solidArea;
    public boolean collisionOn = false;

    public int spriteCounter = 0;
    public int spriteNum = 0;

    public enum Direction{
        BACK, FRONT, RIGHT, LEFT
    }

    public enum MoveDirection{
        BACK, FRONT, RIGHT, LEFT, BACK_RIGHT, BACK_LEFT, FRONT_RIGHT, FRONT_LEFT
    }

}
