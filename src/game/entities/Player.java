package game.entities;

import game.core.Camera;
import game.graphics.Animation;
import game.graphics.Screen;
import game.core.Game;
import game.input.KeyHandler;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    Game game;
    KeyHandler keyHandler;
    public Camera camera = new Camera(this);
    int hasKey = 0;

    public Animation walkAnimation = new Animation(6, 32, 32, 0, 0, "/player/spr_player_walk.png");
    public Animation idleAnimation = new Animation(12, 32, 32, 0, 0, "/player/spr_player_idle.png");
    public Animation rollAnimation = new Animation(7, 32, 32, 0, 0, "/player/spr_player_roll.png");

    public Player(Game game, KeyHandler keyHandler) {
        int scale = Screen.scale;

        solidArea = new Rectangle(scale * 10, scale * 24, scale * 13, scale * 6);

        this.game = game;
        this.keyHandler = keyHandler;

        setDefaultValues();
    }

    public void setDefaultValues(){
        speed = 4;
        isIdle = true;
        isRolling = false;
        direction = Direction.FRONT;
        worldX = Screen.tileSize * 19;
        worldY = Screen.tileSize * 38;
    }

    public void update(){

        handleMovement(speed); // speed : 4
        handleRoll((speed * 2) - 1); // roll speed : 7
        objectInteract(game.collisionChecker.getIndex());

    }

    public void draw(Graphics2D g2d){
        BufferedImage image = null;

        if(isIdle) {
            image = idleAnimation.getCurrentAnimationFrame(this);
        }else if(isRolling){
            image = rollAnimation.getCurrentAnimationFrame(this);
        }else{
            image = walkAnimation.getCurrentAnimationFrame(this);
        }

        int drawX = Math.round(camera.getDrawingXPosition());
        int drawY = Math.round(camera.getDrawingYPosition());

        if(Screen.showSolidArea) {
            g2d.setColor(Color.red);
            g2d.drawRect(drawX + solidArea.x, drawY + solidArea.y, solidArea.width, solidArea.height);
        }
        g2d.drawImage(image, drawX, drawY, 32*3, 32*3, null);
    }

    // Handle all directional movements
    private void handleMovement(float speed) {
        float diagonalSpeed = (float) (speed / Math.sqrt(2));
        boolean moveUp = keyHandler.upPressed, moveDown = keyHandler.downPressed,
                moveLeft = keyHandler.leftPressed, moveRight = keyHandler.rightPressed, roll = keyHandler.spacePressed;

        if (moveUp || moveDown || moveRight || moveLeft || roll) {
            isIdle = false;

            if (moveUp && moveRight) {
                moveDiagonally(Direction.BACK, Direction.RIGHT, diagonalSpeed);
            } else if (moveUp && moveLeft) {
                moveDiagonally(Direction.BACK, Direction.LEFT, diagonalSpeed);
            } else if (moveDown && moveRight) {
                moveDiagonally(Direction.FRONT, Direction.RIGHT, diagonalSpeed);
            } else if (moveDown && moveLeft) {
                moveDiagonally(Direction.FRONT, Direction.LEFT, diagonalSpeed);
            } else {
                moveStraight(moveUp, moveDown, moveLeft, moveRight);
            }
        } else {
            isIdle = true;
        }
    }

    // Handle straight (non-diagonal) movement
    private void moveStraight(boolean moveUp, boolean moveDown, boolean moveLeft, boolean moveRight) {
        if (moveUp && game.collisionChecker.canMove(this, Direction.BACK, speed)) {
            worldY -= speed;
            direction = Direction.BACK;
        } else if (moveDown && game.collisionChecker.canMove(this, Direction.FRONT, speed)) {
            worldY += speed;
            direction = Direction.FRONT;
        } else if (moveRight && game.collisionChecker.canMove(this, Direction.RIGHT, speed)) {
            worldX += speed;
            direction = Direction.RIGHT;
        } else if (moveLeft && game.collisionChecker.canMove(this, Direction.LEFT, speed)) {
            worldX -= speed;
            direction = Direction.LEFT;
        }
    }

    // Handle diagonal movement
    private void moveDiagonally(Direction verticalDirection, Direction horizontalDirection, float diagonalSpeed) {
        if (game.collisionChecker.canMove(this, verticalDirection, diagonalSpeed)) {
            worldY += (verticalDirection == Direction.BACK ? -diagonalSpeed : diagonalSpeed);
        }
        if (game.collisionChecker.canMove(this, horizontalDirection, diagonalSpeed)) {
            worldX += (horizontalDirection == Direction.LEFT ? -diagonalSpeed : diagonalSpeed);
        }
        direction = (direction == Direction.LEFT || direction == Direction.RIGHT)
                ? horizontalDirection : verticalDirection;
    }

    // Handle rolling
    private void handleRoll(float speed) {
        boolean roll = keyHandler.spacePressed;

        if (roll) {
            isRolling = true;
            handleMovement(speed);
        }else {
            isRolling = false;
        }
    }

    // Handle attacking
    private void handleAttack() {

    }

    private void objectInteract(int index){

        if(index != 999){

            String objectName = game.objects[index].name;
            switch(objectName) {
                case "Key":
                    hasKey++;
                    game.soundManager.playSoundEffect(1);
                    System.out.println("key found");
                    game.objects[index] = null;
                    break;
                case "Chest":
                    if(hasKey > 0) {
                        hasKey--;
                        game.soundManager.playSoundEffect(3);
                        game.objects[index] = null;
                    }
                    break;
            }
        }

    }

}
