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

import static game.entities.Entity.Direction.*;

public class Player extends Entity {

    public Camera camera = new Camera(this);
    KeyHandler keyHandler;
    Game game;
    public int hasKey = 0;
    int rollCounter, rollCooldown = 0;

    //Animations
    public Animation walkAnimation = new Animation(6, 10, 32, 32, 0, 0, "/player/spr_player_walk.png");
    public Animation idleAnimation = new Animation(12, 10, 32, 32, 0, 0, "/player/spr_player_idle.png");
    public Animation rollAnimation = new Animation(7, 4, 32, 32, 0, 0, "/player/spr_player_roll.png");

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
        direction = FRONT;
        worldX = Screen.tileSize * 19;
        worldY = Screen.tileSize * 38;
    }

    //Update Method
    public void update(){

        handleMovement(speed); // speed : 4
        handleRoll((speed * 2) - 1); // roll speed : 7
        objectInteract(game.collisionChecker.getIndex());

    }

    //Draw Method
    public void draw(Graphics2D g2d){
        BufferedImage image;

        if(isIdle) {
            image = idleAnimation.getCurrentAnimationFrame(this);
        }else if(isRolling){
            image = rollAnimation.getCurrentAnimationFrame(this);
        }else{
            image = walkAnimation.getCurrentAnimationFrame(this);
        }

        int drawX = Math.round(camera.getDrawingXPosition());
        int drawY = Math.round(camera.getDrawingYPosition());

        if(Game.showSolidArea) {
            g2d.setColor(Color.red);
            g2d.drawRect(drawX + solidArea.x, drawY + solidArea.y, solidArea.width, solidArea.height);
        }
        g2d.drawImage(image, drawX, drawY, null);
    }

    // Handle all directional movements
    private void handleMovement(float speed) {
        float diagonalSpeed = (float) (speed / Math.sqrt(2));
        boolean moveUp = keyHandler.upPressed, moveDown = keyHandler.downPressed,
                moveLeft = keyHandler.leftPressed, moveRight = keyHandler.rightPressed;

        if (moveUp || moveDown || moveRight || moveLeft || isRolling) {
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
        if (moveUp) {
            if (game.collisionChecker.canMove(this, BACK, speed))
                worldY -= speed;
            direction = BACK;
        } else if (moveDown) {
            if (game.collisionChecker.canMove(this, FRONT, speed))
                worldY += speed;
            direction = FRONT;
        } else if (moveRight) {
            if (game.collisionChecker.canMove(this, RIGHT, speed))
                worldX += speed;
            direction = RIGHT;
        } else if (moveLeft) {
            if (game.collisionChecker.canMove(this, LEFT, speed))
                worldX -= speed;
            direction = LEFT;
        }
    }

    // Handle diagonal movement
    private void moveDiagonally(Direction verticalDirection, Direction horizontalDirection, float diagonalSpeed) {
        if (game.collisionChecker.canMove(this, verticalDirection, diagonalSpeed)) {
            worldY += (verticalDirection == BACK ? -diagonalSpeed : diagonalSpeed);
        }
        if (game.collisionChecker.canMove(this, horizontalDirection, diagonalSpeed)) {
            worldX += (horizontalDirection == LEFT ? -diagonalSpeed : diagonalSpeed);
        }
        direction = (direction == LEFT || direction == RIGHT)
                ? horizontalDirection : verticalDirection;
    }

    // Handle rolling
    private void handleRoll(float speed) {
        if (keyHandler.spacePressed && !isRolling && rollCooldown == 0 &&!isIdle) {
            isRolling = true;
            rollCounter = 0;
            rollAnimation.spriteNum = 0;
        }

        if (isRolling) {
            rollCounter++; // Countdown each frame

            // Move in the direction player is currently facing
            handleMovement(speed);

            // Check if roll duration is complete
            if (rollCounter >= 34) {
                isRolling = false; // End roll
                rollCounter = 0;
                rollCooldown = 20;
                rollAnimation.spriteNum = 0;
            }
        }

        if(rollCooldown > 0){
            rollCooldown--;
        }
    }

    // Handle attacking
    private void handleAttack() {

    }

    //Handle objects interactions
    private void objectInteract(int index){

        if(index != 999){

            String objectName = game.objects[index].name;
            switch(objectName) {
                case "Key":
                    hasKey++;
                    game.soundManager.playSoundEffect(1);
                    game.ui.displayMessage("Key Found", Color.white);
                    game.objects[index] = null;
                    break;
                case "Chest":
                    if(hasKey > 0) {
                        hasKey--;
                        game.soundManager.stopMusic();
                        game.soundManager.playSoundEffect(3);
                        game.soundManager.playSoundEffect(4);
                        game.ui.displayMessage("You opened a chest!", Color.yellow);
                        game.objects[index] = null;
                    }else{
                        game.ui.displayMessage("You don't have a key", Color.white);
                    }
                    break;
            }
        }

    }

}
